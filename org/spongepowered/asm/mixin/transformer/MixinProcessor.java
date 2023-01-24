/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.UUID;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.throwables.ClassAlreadyLoadedException;
import org.spongepowered.asm.mixin.throwables.MixinApplyError;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.throwables.MixinPrepareError;
import org.spongepowered.asm.mixin.transformer.Config;
import org.spongepowered.asm.mixin.transformer.MixinConfig;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.MixinPostProcessor;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionCheckClass;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionClassExporter;
import org.spongepowered.asm.mixin.transformer.throwables.IllegalClassLoadError;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.mixin.transformer.throwables.ReEntrantTransformerError;
import org.spongepowered.asm.service.IMixinAuditTrail;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.ReEntranceLock;
import org.spongepowered.asm.util.perf.Profiler;

public class MixinProcessor {
    static final Logger logger = LogManager.getLogger((String)"mixin");
    private final IMixinService service = MixinService.getService();
    private final List<MixinConfig> configs = new ArrayList<MixinConfig>();
    private final List<MixinConfig> pendingConfigs = new ArrayList<MixinConfig>();
    private final ReEntranceLock lock;
    private final String sessionId = UUID.randomUUID().toString();
    private final Extensions extensions;
    private final IHotSwap hotSwapper;
    private final MixinPostProcessor postProcessor;
    private final Profiler profiler;
    private final IMixinAuditTrail auditTrail;
    private MixinEnvironment currentEnvironment;
    private Level verboseLoggingLevel = Level.DEBUG;
    private boolean errorState = false;
    private int transformedCount = 0;

    MixinProcessor(MixinEnvironment environment, Extensions extensions, IHotSwap hotSwapper) {
        this.lock = this.service.getReEntranceLock();
        this.extensions = extensions;
        this.hotSwapper = hotSwapper;
        this.postProcessor = new MixinPostProcessor(this.sessionId);
        this.profiler = MixinEnvironment.getProfiler();
        this.auditTrail = this.service.getAuditTrail();
    }

    public void audit(MixinEnvironment environment) {
        HashSet<String> unhandled = new HashSet<String>();
        for (MixinConfig config : this.configs) {
            unhandled.addAll(config.getUnhandledTargets());
        }
        Logger auditLogger = LogManager.getLogger((String)"mixin/audit");
        for (String target : unhandled) {
            try {
                auditLogger.info("Force-loading class {}", new Object[]{target});
                this.service.getClassProvider().findClass(target, true);
            }
            catch (ClassNotFoundException ex) {
                auditLogger.error("Could not force-load " + target, (Throwable)ex);
            }
        }
        for (MixinConfig config : this.configs) {
            for (String target : config.getUnhandledTargets()) {
                ClassAlreadyLoadedException ex = new ClassAlreadyLoadedException(target + " was already classloaded");
                auditLogger.error("Could not force-load " + target, (Throwable)ex);
            }
        }
        if (environment.getOption(MixinEnvironment.Option.DEBUG_PROFILER)) {
            this.profiler.printSummary();
        }
    }

    synchronized boolean applyMixins(MixinEnvironment environment, String name, ClassNode targetClassNode) {
        if (name == null || this.errorState) {
            return false;
        }
        boolean locked = this.lock.push().check();
        Profiler.Section mixinTimer = this.profiler.begin("mixin");
        if (locked) {
            for (MixinConfig config : this.pendingConfigs) {
                if (!config.hasPendingMixinsFor(name)) continue;
                ReEntrantTransformerError error = new ReEntrantTransformerError("Re-entrance error.");
                logger.warn("Re-entrance detected during prepare phase, this will cause serious problems.", (Throwable)error);
                throw error;
            }
        } else {
            try {
                this.checkSelect(environment);
            }
            catch (Exception ex) {
                this.lock.pop();
                mixinTimer.end();
                throw new MixinException(ex);
            }
        }
        boolean success = false;
        try {
            if (this.postProcessor.canProcess(name)) {
                if (this.auditTrail != null) {
                    this.auditTrail.onPostProcess(name);
                }
                Profiler.Section postTimer = this.profiler.begin("postprocessor");
                success = this.postProcessor.processClass(name, targetClassNode);
                postTimer.end();
                this.extensions.export(environment, name, false, targetClassNode);
                boolean error = success;
                return error;
            }
            TreeSet<MixinInfo> mixins = null;
            MixinConfig packageOwnedByConfig = null;
            for (MixinConfig config : this.configs) {
                if (config.packageMatch(name)) {
                    int packageLen;
                    int n = packageLen = packageOwnedByConfig != null ? packageOwnedByConfig.getMixinPackage().length() : 0;
                    if (config.getMixinPackage().length() <= packageLen) continue;
                    packageOwnedByConfig = config;
                    continue;
                }
                if (!config.hasMixinsFor(name)) continue;
                if (mixins == null) {
                    mixins = new TreeSet<MixinInfo>();
                }
                mixins.addAll(config.getMixinsFor(name));
            }
            if (packageOwnedByConfig != null) {
                throw new IllegalClassLoadError(this.getInvalidClassError(name, targetClassNode, packageOwnedByConfig));
            }
            if (mixins != null) {
                if (locked) {
                    ReEntrantTransformerError error = new ReEntrantTransformerError("Re-entrance error.");
                    logger.warn("Re-entrance detected, this will cause serious problems.", (Throwable)error);
                    throw error;
                }
                if (this.hotSwapper != null) {
                    this.hotSwapper.registerTargetClass(name, targetClassNode);
                }
                try {
                    Profiler.Section timer = this.profiler.begin("read");
                    TargetClassContext context = new TargetClassContext(environment, this.extensions, this.sessionId, name, targetClassNode, mixins);
                    timer.end();
                    this.applyMixins(environment, context);
                    ++this.transformedCount;
                    success = true;
                }
                catch (InvalidMixinException th) {
                    this.dumpClassOnFailure(name, targetClassNode, environment);
                    this.handleMixinApplyError(name, th, environment);
                }
            }
        }
        catch (MixinTransformerError er) {
            throw er;
        }
        catch (Throwable th) {
            th.printStackTrace();
            this.dumpClassOnFailure(name, targetClassNode, environment);
            throw new MixinTransformerError("An unexpected critical error was encountered", th);
        }
        finally {
            this.lock.pop();
            mixinTimer.end();
        }
        return success;
    }

    private String getInvalidClassError(String name, ClassNode targetClassNode, MixinConfig ownedByConfig) {
        MixinInfo.Variant variant;
        if (ownedByConfig.getClasses().contains(name)) {
            return String.format("Illegal classload request for %s. Mixin is defined in %s and cannot be referenced directly", name, ownedByConfig);
        }
        AnnotationNode mixin = Annotations.getInvisible(targetClassNode, Mixin.class);
        if (mixin != null && (variant = MixinInfo.getVariant(targetClassNode)) == MixinInfo.Variant.ACCESSOR) {
            return String.format("Illegal classload request for accessor mixin %s. The mixin is missing from %s which owns package %s* and the mixin has not been applied.", name, ownedByConfig, ownedByConfig.getMixinPackage());
        }
        return String.format("%s is in a defined mixin package %s* owned by %s and cannot be referenced directly", name, ownedByConfig.getMixinPackage(), ownedByConfig);
    }

    public List<String> reload(String mixinClass, ClassNode classNode) {
        if (this.lock.getDepth() > 0) {
            throw new MixinApplyError("Cannot reload mixin if re-entrant lock entered");
        }
        ArrayList<String> targets = new ArrayList<String>();
        for (MixinConfig config : this.configs) {
            targets.addAll(config.reloadMixin(mixinClass, classNode));
        }
        return targets;
    }

    private void checkSelect(MixinEnvironment environment) {
        if (this.currentEnvironment != environment) {
            this.select(environment);
            return;
        }
        int unvisitedCount = Mixins.getUnvisitedCount();
        if (unvisitedCount > 0 && this.transformedCount == 0) {
            this.select(environment);
        }
    }

    private void select(MixinEnvironment environment) {
        Level level = this.verboseLoggingLevel = environment.getOption(MixinEnvironment.Option.DEBUG_VERBOSE) ? Level.INFO : Level.DEBUG;
        if (this.transformedCount > 0) {
            logger.log(this.verboseLoggingLevel, "Ending {}, applied {} mixins", new Object[]{this.currentEnvironment, this.transformedCount});
        }
        String action = this.currentEnvironment == environment ? "Checking for additional" : "Preparing";
        logger.log(this.verboseLoggingLevel, "{} mixins for {}", new Object[]{action, environment});
        this.profiler.setActive(true);
        this.profiler.mark(environment.getPhase().toString() + ":prepare");
        Profiler.Section prepareTimer = this.profiler.begin("prepare");
        this.selectConfigs(environment);
        this.extensions.select(environment);
        int totalMixins = this.prepareConfigs(environment);
        this.currentEnvironment = environment;
        this.transformedCount = 0;
        prepareTimer.end();
        long elapsedMs = prepareTimer.getTime();
        double elapsedTime = prepareTimer.getSeconds();
        if (elapsedTime > 0.25) {
            long loadTime = this.profiler.get("class.load").getTime();
            long transformTime = this.profiler.get("class.transform").getTime();
            long pluginTime = this.profiler.get("mixin.plugin").getTime();
            String elapsed = new DecimalFormat("###0.000").format(elapsedTime);
            String perMixinTime = new DecimalFormat("###0.0").format((double)elapsedMs / (double)totalMixins);
            logger.log(this.verboseLoggingLevel, "Prepared {} mixins in {} sec ({}ms avg) ({}ms load, {}ms transform, {}ms plugin)", new Object[]{totalMixins, elapsed, perMixinTime, loadTime, transformTime, pluginTime});
        }
        this.profiler.mark(environment.getPhase().toString() + ":apply");
        this.profiler.setActive(environment.getOption(MixinEnvironment.Option.DEBUG_PROFILER));
    }

    private void selectConfigs(MixinEnvironment environment) {
        Iterator<Config> iter = Mixins.getConfigs().iterator();
        while (iter.hasNext()) {
            Config handle = iter.next();
            try {
                MixinConfig config = handle.get();
                if (!config.select(environment)) continue;
                iter.remove();
                logger.log(this.verboseLoggingLevel, "Selecting config {}", new Object[]{config});
                config.onSelect();
                this.pendingConfigs.add(config);
            }
            catch (Exception ex) {
                logger.warn(String.format("Failed to select mixin config: %s", handle), (Throwable)ex);
            }
        }
        Collections.sort(this.pendingConfigs);
    }

    private int prepareConfigs(MixinEnvironment environment) {
        String message;
        int totalMixins = 0;
        final IHotSwap hotSwapper = this.hotSwapper;
        for (MixinConfig config : this.pendingConfigs) {
            config.addListener(this.postProcessor);
            if (hotSwapper == null) continue;
            config.addListener(new MixinConfig.IListener(){

                @Override
                public void onPrepare(MixinInfo mixin) {
                    hotSwapper.registerMixinClass(mixin.getClassName());
                }

                @Override
                public void onInit(MixinInfo mixin) {
                }
            });
        }
        for (MixinConfig config : this.pendingConfigs) {
            try {
                logger.log(this.verboseLoggingLevel, "Preparing {} ({})", new Object[]{config, config.getDeclaredMixinCount()});
                config.prepare();
                totalMixins += config.getMixinCount();
            }
            catch (InvalidMixinException ex) {
                this.handleMixinPrepareError(config, ex, environment);
            }
            catch (Exception ex) {
                message = ex.getMessage();
                logger.error("Error encountered whilst initialising mixin config '" + config.getName() + "': " + message, (Throwable)ex);
            }
        }
        for (MixinConfig config : this.pendingConfigs) {
            IMixinConfigPlugin plugin = config.getPlugin();
            if (plugin == null) continue;
            HashSet<String> otherTargets = new HashSet<String>();
            for (MixinConfig otherConfig : this.pendingConfigs) {
                if (otherConfig.equals(config)) continue;
                otherTargets.addAll(otherConfig.getTargets());
            }
            plugin.acceptTargets(config.getTargets(), Collections.unmodifiableSet(otherTargets));
        }
        for (MixinConfig config : this.pendingConfigs) {
            try {
                config.postInitialise();
            }
            catch (InvalidMixinException ex) {
                this.handleMixinPrepareError(config, ex, environment);
            }
            catch (Exception ex) {
                message = ex.getMessage();
                logger.error("Error encountered during mixin config postInit step'" + config.getName() + "': " + message, (Throwable)ex);
            }
        }
        this.configs.addAll(this.pendingConfigs);
        Collections.sort(this.configs);
        this.pendingConfigs.clear();
        return totalMixins;
    }

    private void applyMixins(MixinEnvironment environment, TargetClassContext context) {
        Profiler.Section timer = this.profiler.begin("preapply");
        this.extensions.preApply(context);
        timer = timer.next("apply");
        context.applyMixins();
        timer = timer.next("postapply");
        boolean export = false;
        try {
            this.extensions.postApply(context);
            export = true;
        }
        catch (ExtensionCheckClass.ValidationFailedException ex) {
            logger.info(ex.getMessage());
            export |= context.isExportForced() || environment.getOption(MixinEnvironment.Option.DEBUG_EXPORT);
        }
        timer.end();
        if (export) {
            this.extensions.export(this.currentEnvironment, context.getClassName(), context.isExportForced(), context.getClassNode());
        }
        for (InvalidMixinException suppressed : context.getSuppressedExceptions()) {
            this.handleMixinApplyError(context.getClassName(), suppressed, environment);
        }
    }

    private void handleMixinPrepareError(MixinConfig config, InvalidMixinException ex, MixinEnvironment environment) throws MixinPrepareError {
        this.handleMixinError(config.getName(), ex, environment, ErrorPhase.PREPARE);
    }

    private void handleMixinApplyError(String targetClass, InvalidMixinException ex, MixinEnvironment environment) throws MixinApplyError {
        this.handleMixinError(targetClass, ex, environment, ErrorPhase.APPLY);
    }

    private void handleMixinError(String context, InvalidMixinException ex, MixinEnvironment environment, ErrorPhase errorPhase) throws Error {
        IMixinErrorHandler.ErrorAction action;
        this.errorState = true;
        IMixinInfo mixin = ex.getMixin();
        if (mixin == null) {
            logger.error("InvalidMixinException has no mixin!", (Throwable)ex);
            throw ex;
        }
        IMixinConfig config = mixin.getConfig();
        MixinEnvironment.Phase phase = mixin.getPhase();
        IMixinErrorHandler.ErrorAction errorAction = action = config.isRequired() ? IMixinErrorHandler.ErrorAction.ERROR : IMixinErrorHandler.ErrorAction.WARN;
        if (environment.getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            new PrettyPrinter().wrapTo(160).add("Invalid Mixin").centre().hr('-').kvWidth(10).kv("Action", errorPhase.name()).kv("Mixin", mixin.getClassName()).kv("Config", config.getName()).kv("Phase", phase).hr('-').add("    %s", ex.getClass().getName()).hr('-').addWrapped("    %s", ex.getMessage()).hr('-').add(ex, 8).log(action.logLevel);
        }
        for (IMixinErrorHandler handler : this.getErrorHandlers(mixin.getPhase())) {
            IMixinErrorHandler.ErrorAction newAction = errorPhase.onError(handler, context, ex, mixin, action);
            if (newAction == null) continue;
            action = newAction;
        }
        logger.log(action.logLevel, errorPhase.getLogMessage(context, ex, mixin), (Throwable)ex);
        this.errorState = false;
        if (action == IMixinErrorHandler.ErrorAction.ERROR) {
            throw new MixinApplyError(errorPhase.getErrorMessage(mixin, config, phase), ex);
        }
    }

    private List<IMixinErrorHandler> getErrorHandlers(MixinEnvironment.Phase phase) {
        ArrayList<IMixinErrorHandler> handlers = new ArrayList<IMixinErrorHandler>();
        for (String handlerClassName : Mixins.getErrorHandlerClasses()) {
            try {
                logger.info("Instancing error handler class {}", new Object[]{handlerClassName});
                Class<?> handlerClass = this.service.getClassProvider().findClass(handlerClassName, true);
                IMixinErrorHandler handler = (IMixinErrorHandler)handlerClass.newInstance();
                if (handler == null) continue;
                handlers.add(handler);
            }
            catch (Throwable throwable) {}
        }
        return handlers;
    }

    private void dumpClassOnFailure(String className, ClassNode classNode, MixinEnvironment env) {
        if (env.getOption(MixinEnvironment.Option.DUMP_TARGET_ON_FAILURE)) {
            ExtensionClassExporter exporter = (ExtensionClassExporter)this.extensions.getExtension(ExtensionClassExporter.class);
            exporter.dumpClass(className.replace('.', '/') + ".target", classNode);
        }
    }

    static enum ErrorPhase {
        PREPARE{

            @Override
            IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler handler, String context, InvalidMixinException ex, IMixinInfo mixin, IMixinErrorHandler.ErrorAction action) {
                try {
                    return handler.onPrepareError(mixin.getConfig(), ex, mixin, action);
                }
                catch (AbstractMethodError ame) {
                    return action;
                }
            }

            @Override
            protected String getContext(IMixinInfo mixin, String context) {
                return String.format("preparing %s in %s", mixin.getName(), context);
            }
        }
        ,
        APPLY{

            @Override
            IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler handler, String context, InvalidMixinException ex, IMixinInfo mixin, IMixinErrorHandler.ErrorAction action) {
                try {
                    return handler.onApplyError(context, ex, mixin, action);
                }
                catch (AbstractMethodError ame) {
                    return action;
                }
            }

            @Override
            protected String getContext(IMixinInfo mixin, String context) {
                return String.format("%s -> %s", mixin, context);
            }
        };

        private final String text = this.name().toLowerCase(Locale.ROOT);

        abstract IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler var1, String var2, InvalidMixinException var3, IMixinInfo var4, IMixinErrorHandler.ErrorAction var5);

        protected abstract String getContext(IMixinInfo var1, String var2);

        public String getLogMessage(String context, InvalidMixinException ex, IMixinInfo mixin) {
            return String.format("Mixin %s failed %s: %s %s", this.text, this.getContext(mixin, context), ex.getClass().getName(), ex.getMessage());
        }

        public String getErrorMessage(IMixinInfo mixin, IMixinConfig config, MixinEnvironment.Phase phase) {
            return String.format("Mixin [%s] from phase [%s] in config [%s] FAILED during %s", mixin, phase, config, this.name());
        }
    }
}

