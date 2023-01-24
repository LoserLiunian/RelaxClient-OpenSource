/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.gson.Gson
 *  com.google.gson.annotations.SerializedName
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.spongepowered.asm.launch.MixinInitialisationError;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.mixin.refmap.RemappingReferenceMapper;
import org.spongepowered.asm.mixin.transformer.Config;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.PluginHandle;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.VersionNumber;

final class MixinConfig
implements Comparable<MixinConfig>,
IMixinConfig {
    private static int configOrder = 0;
    private static final Set<String> globalMixinList = new HashSet<String>();
    private final Logger logger = LogManager.getLogger((String)"mixin");
    private final transient Map<String, List<MixinInfo>> mixinMapping = new HashMap<String, List<MixinInfo>>();
    private final transient Set<String> unhandledTargets = new HashSet<String>();
    private final transient List<MixinInfo> pendingMixins = new ArrayList<MixinInfo>();
    private final transient List<MixinInfo> mixins = new ArrayList<MixinInfo>();
    private transient Config handle;
    private transient MixinConfig parent;
    @SerializedName(value="parent")
    private String parentName;
    @SerializedName(value="target")
    private String selector;
    @SerializedName(value="minVersion")
    private String version;
    @SerializedName(value="compatibilityLevel")
    private String compatibility;
    @SerializedName(value="required")
    private Boolean requiredValue;
    private transient boolean required;
    @SerializedName(value="priority")
    private int priority = -1;
    @SerializedName(value="mixinPriority")
    private int mixinPriority = -1;
    @SerializedName(value="package")
    private String mixinPackage;
    @SerializedName(value="mixins")
    private List<String> mixinClasses;
    @SerializedName(value="client")
    private List<String> mixinClassesClient;
    @SerializedName(value="server")
    private List<String> mixinClassesServer;
    @SerializedName(value="setSourceFile")
    private boolean setSourceFile = false;
    @SerializedName(value="refmap")
    private String refMapperConfig;
    @SerializedName(value="verbose")
    private boolean verboseLogging;
    private final transient int order = configOrder++;
    private final transient List<IListener> listeners = new ArrayList<IListener>();
    private transient IMixinService service;
    private transient MixinEnvironment env;
    private transient String name;
    @SerializedName(value="plugin")
    private String pluginClassName;
    @SerializedName(value="injectors")
    private InjectorOptions injectorOptions;
    @SerializedName(value="overwrites")
    private OverwriteOptions overwriteOptions;
    private transient PluginHandle plugin;
    private transient IReferenceMapper refMapper;
    private transient boolean initialised = false;
    private transient boolean prepared = false;
    private transient boolean visited = false;

    private MixinConfig() {
    }

    private boolean onLoad(IMixinService service, String name, MixinEnvironment fallbackEnvironment) {
        this.service = service;
        this.name = name;
        if (!Strings.isNullOrEmpty((String)this.parentName)) {
            return true;
        }
        this.env = this.parseSelector(this.selector, fallbackEnvironment);
        this.required = this.requiredValue != null && this.requiredValue != false && !this.env.getOption(MixinEnvironment.Option.IGNORE_REQUIRED);
        this.initPriority(1000, 1000);
        if (this.injectorOptions == null) {
            this.injectorOptions = new InjectorOptions();
        }
        if (this.overwriteOptions == null) {
            this.overwriteOptions = new OverwriteOptions();
        }
        return this.postInit();
    }

    String getParentName() {
        return this.parentName;
    }

    boolean assignParent(Config parentConfig) {
        if (this.parent != null) {
            throw new MixinInitialisationError("Mixin config " + this.name + " was already initialised");
        }
        if (parentConfig.get() == this) {
            throw new MixinInitialisationError("Mixin config " + this.name + " cannot be its own parent");
        }
        this.parent = parentConfig.get();
        if (!this.parent.initialised) {
            throw new MixinInitialisationError("Mixin config " + this.name + " attempted to assign uninitialised parent config. This probably means that there is an indirect loop in the mixin configs: child -> parent -> child");
        }
        this.env = this.parseSelector(this.selector, this.parent.env);
        this.required = this.requiredValue == null ? this.parent.required : this.requiredValue != false && !this.env.getOption(MixinEnvironment.Option.IGNORE_REQUIRED);
        this.initPriority(this.parent.priority, this.parent.mixinPriority);
        if (this.injectorOptions == null) {
            this.injectorOptions = this.parent.injectorOptions;
        } else {
            this.injectorOptions.mergeFrom(this.parent.injectorOptions);
        }
        if (this.overwriteOptions == null) {
            this.overwriteOptions = this.parent.overwriteOptions;
        } else {
            this.overwriteOptions.mergeFrom(this.parent.overwriteOptions);
        }
        this.setSourceFile |= this.parent.setSourceFile;
        this.verboseLogging |= this.parent.verboseLogging;
        return this.postInit();
    }

    private void initPriority(int defaultPriority, int defaultMixinPriority) {
        if (this.priority < 0) {
            this.priority = defaultPriority;
        }
        if (this.mixinPriority < 0) {
            this.mixinPriority = defaultMixinPriority;
        }
    }

    private boolean postInit() throws MixinInitialisationError {
        if (this.initialised) {
            throw new MixinInitialisationError("Mixin config " + this.name + " was already initialised.");
        }
        this.initialised = true;
        this.initCompatibilityLevel();
        this.initInjectionPoints();
        return this.checkVersion();
    }

    private void initCompatibilityLevel() {
        MixinEnvironment.CompatibilityLevel current;
        if (this.compatibility == null) {
            return;
        }
        MixinEnvironment.CompatibilityLevel level = MixinEnvironment.CompatibilityLevel.valueOf(this.compatibility.trim().toUpperCase(Locale.ROOT));
        if (level == (current = MixinEnvironment.getCompatibilityLevel())) {
            return;
        }
        if (current.isAtLeast(level) && !current.canSupport(level)) {
            throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + (Object)((Object)level) + " which is too old");
        }
        if (!current.canElevateTo(level)) {
            throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + (Object)((Object)level) + " which is prohibited by " + (Object)((Object)current));
        }
        MixinEnvironment.setCompatibilityLevel(level);
    }

    private MixinEnvironment parseSelector(String target, MixinEnvironment fallbackEnvironment) {
        if (target != null) {
            String[] selectors;
            for (String sel : selectors = target.split("[&\\| ]")) {
                sel = sel.trim();
                Pattern environmentSelector = Pattern.compile("^@env(?:ironment)?\\(([A-Z]+)\\)$");
                Matcher environmentSelectorMatcher = environmentSelector.matcher(sel);
                if (!environmentSelectorMatcher.matches()) continue;
                return MixinEnvironment.getEnvironment(MixinEnvironment.Phase.forName(environmentSelectorMatcher.group(1)));
            }
            MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(target);
            if (phase != null) {
                return MixinEnvironment.getEnvironment(phase);
            }
        }
        return fallbackEnvironment;
    }

    private void initInjectionPoints() {
        if (this.injectorOptions.injectionPoints == null) {
            return;
        }
        for (String injectionPointClassName : this.injectorOptions.injectionPoints) {
            this.initInjectionPoint(injectionPointClassName);
        }
    }

    public void initInjectionPoint(String className) {
        try {
            Class<?> injectionPointClass = null;
            try {
                injectionPointClass = this.service.getClassProvider().findClass(className, true);
            }
            catch (ClassNotFoundException cnfe) {
                this.logger.error("Unable to register injection point {} for {}, the specified class was not found", new Object[]{className, this, cnfe});
                return;
            }
            if (!InjectionPoint.class.isAssignableFrom(injectionPointClass)) {
                this.logger.error("Unable to register injection point {} for {}, class must extend InjectionPoint", new Object[]{className, this});
                return;
            }
            try {
                injectionPointClass.getDeclaredMethod("find", String.class, InsnList.class, Collection.class);
            }
            catch (NoSuchMethodException cnfe) {
                this.logger.error("Unable to register injection point {} for {}, the class is not compatible with this version of Mixin", new Object[]{className, this, cnfe});
                return;
            }
            InjectionPoint.register(injectionPointClass);
        }
        catch (Throwable th) {
            this.logger.catching(th);
        }
    }

    private boolean checkVersion() throws MixinInitialisationError {
        VersionNumber curVersion;
        VersionNumber minVersion;
        if (this.version == null) {
            if (this.parent != null && this.parent.version != null) {
                return true;
            }
            this.logger.error("Mixin config {} does not specify \"minVersion\" property", new Object[]{this.name});
        }
        if ((minVersion = VersionNumber.parse(this.version)).compareTo(curVersion = VersionNumber.parse(this.env.getVersion())) > 0) {
            this.logger.warn("Mixin config {} requires mixin subsystem version {} but {} was found. The mixin config will not be applied.", new Object[]{this.name, minVersion, curVersion});
            if (this.required) {
                throw new MixinInitialisationError("Required mixin config " + this.name + " requires mixin subsystem version " + minVersion);
            }
            return false;
        }
        return true;
    }

    void addListener(IListener listener) {
        this.listeners.add(listener);
    }

    void onSelect() {
        this.plugin = new PluginHandle(this, this.service, this.pluginClassName);
        this.plugin.onLoad(this.mixinPackage);
        if (!this.mixinPackage.endsWith(".")) {
            this.mixinPackage = this.mixinPackage + ".";
        }
        boolean suppressRefMapWarning = false;
        if (this.refMapperConfig == null) {
            this.refMapperConfig = this.plugin.getRefMapperConfig();
            if (this.refMapperConfig == null) {
                suppressRefMapWarning = true;
                this.refMapperConfig = "mixin.refmap.json";
            }
        }
        this.refMapper = ReferenceMapper.read(this.refMapperConfig);
        this.verboseLogging |= this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
        if (!suppressRefMapWarning && this.refMapper.isDefault() && !this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP)) {
            this.logger.warn("Reference map '{}' for {} could not be read. If this is a development environment you can ignore this message", new Object[]{this.refMapperConfig, this});
        }
        if (this.env.getOption(MixinEnvironment.Option.REFMAP_REMAP)) {
            this.refMapper = RemappingReferenceMapper.of(this.env, this.refMapper);
        }
    }

    void prepare() {
        if (this.prepared) {
            return;
        }
        this.prepared = true;
        this.prepareMixins(this.mixinClasses, false);
        switch (this.env.getSide()) {
            case CLIENT: {
                this.prepareMixins(this.mixinClassesClient, false);
                break;
            }
            case SERVER: {
                this.prepareMixins(this.mixinClassesServer, false);
                break;
            }
            default: {
                this.logger.warn("Mixin environment was unable to detect the current side, sided mixins will not be applied");
            }
        }
    }

    void postInitialise() {
        if (this.plugin != null) {
            List<String> pluginMixins = this.plugin.getMixins();
            this.prepareMixins(pluginMixins, true);
        }
        Iterator<MixinInfo> iter = this.mixins.iterator();
        while (iter.hasNext()) {
            MixinInfo mixin = iter.next();
            try {
                mixin.validate();
                for (IListener listener : this.listeners) {
                    listener.onInit(mixin);
                }
            }
            catch (InvalidMixinException ex) {
                this.logger.error(ex.getMixin() + ": " + ex.getMessage(), (Throwable)ex);
                this.removeMixin(mixin);
                iter.remove();
            }
            catch (Exception ex) {
                this.logger.error(ex.getMessage(), (Throwable)ex);
                this.removeMixin(mixin);
                iter.remove();
            }
        }
    }

    private void removeMixin(MixinInfo remove) {
        for (List<MixinInfo> mixinsFor : this.mixinMapping.values()) {
            Iterator<MixinInfo> iter = mixinsFor.iterator();
            while (iter.hasNext()) {
                if (remove != iter.next()) continue;
                iter.remove();
            }
        }
    }

    private void prepareMixins(List<String> mixinClasses, boolean ignorePlugin) {
        if (mixinClasses == null) {
            return;
        }
        for (String mixinClass : mixinClasses) {
            Iterator<IListener> fqMixinClass = this.mixinPackage + mixinClass;
            if (mixinClass == null || globalMixinList.contains(fqMixinClass)) continue;
            MixinInfo mixin = null;
            try {
                mixin = new MixinInfo(this.service, this, mixinClass, this.plugin, ignorePlugin);
                this.pendingMixins.add(mixin);
                globalMixinList.add((String)((Object)fqMixinClass));
            }
            catch (InvalidMixinException ex) {
                if (this.required) {
                    throw ex;
                }
                this.logger.error(ex.getMessage(), (Throwable)ex);
            }
            catch (Exception ex) {
                if (this.required) {
                    throw new InvalidMixinException((IMixinInfo)mixin, "Error initialising mixin " + mixin + " - " + ex.getClass() + ": " + ex.getMessage(), (Throwable)ex);
                }
                this.logger.error(ex.getMessage(), (Throwable)ex);
            }
        }
        for (MixinInfo mixin : this.pendingMixins) {
            try {
                mixin.parseTargets();
                if (mixin.getTargetClasses().size() <= 0) continue;
                for (String targetClass : mixin.getTargetClasses()) {
                    String targetClassName = targetClass.replace('/', '.');
                    this.mixinsFor(targetClassName).add(mixin);
                    this.unhandledTargets.add(targetClassName);
                }
                for (IListener listener : this.listeners) {
                    listener.onPrepare(mixin);
                }
                this.mixins.add(mixin);
            }
            catch (InvalidMixinException ex) {
                if (this.required) {
                    throw ex;
                }
                this.logger.error(ex.getMessage(), (Throwable)ex);
            }
            catch (Exception ex) {
                if (this.required) {
                    throw new InvalidMixinException((IMixinInfo)mixin, "Error initialising mixin " + mixin + " - " + ex.getClass() + ": " + ex.getMessage(), (Throwable)ex);
                }
                this.logger.error(ex.getMessage(), (Throwable)ex);
            }
        }
        this.pendingMixins.clear();
    }

    void postApply(String transformedName, ClassNode targetClass) {
        this.unhandledTargets.remove(transformedName);
    }

    public Config getHandle() {
        if (this.handle == null) {
            this.handle = new Config(this);
        }
        return this.handle;
    }

    @Override
    public boolean isRequired() {
        return this.required;
    }

    @Override
    public MixinEnvironment getEnvironment() {
        return this.env;
    }

    MixinConfig getParent() {
        return this.parent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMixinPackage() {
        return this.mixinPackage;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    public int getDefaultMixinPriority() {
        return this.mixinPriority;
    }

    public int getDefaultRequiredInjections() {
        return this.injectorOptions.defaultRequireValue;
    }

    public String getDefaultInjectorGroup() {
        String defaultGroup = this.injectorOptions.defaultGroup;
        return defaultGroup != null && !defaultGroup.isEmpty() ? defaultGroup : "default";
    }

    public boolean conformOverwriteVisibility() {
        return this.overwriteOptions.conformAccessModifiers;
    }

    public boolean requireOverwriteAnnotations() {
        return this.overwriteOptions.requireOverwriteAnnotations;
    }

    public int getMaxShiftByValue() {
        return Math.min(Math.max(this.injectorOptions.maxShiftBy, 0), 5);
    }

    public boolean select(MixinEnvironment environment) {
        this.visited = true;
        return this.env == environment;
    }

    boolean isVisited() {
        return this.visited;
    }

    int getDeclaredMixinCount() {
        return MixinConfig.getCollectionSize(this.mixinClasses, this.mixinClassesClient, this.mixinClassesServer);
    }

    int getMixinCount() {
        return this.mixins.size();
    }

    public List<String> getClasses() {
        ImmutableList.Builder list = ImmutableList.builder();
        for (List classes : new List[]{this.mixinClasses, this.mixinClassesClient, this.mixinClassesServer}) {
            if (classes == null) continue;
            for (String className : classes) {
                list.add((Object)(this.mixinPackage + className));
            }
        }
        return list.build();
    }

    public boolean shouldSetSourceFile() {
        return this.setSourceFile;
    }

    public IReferenceMapper getReferenceMapper() {
        if (this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP)) {
            return ReferenceMapper.DEFAULT_MAPPER;
        }
        this.refMapper.setContext(this.env.getRefmapObfuscationContext());
        return this.refMapper;
    }

    String remapClassName(String className, String reference) {
        return this.getReferenceMapper().remap(className, reference);
    }

    @Override
    public IMixinConfigPlugin getPlugin() {
        return this.plugin.get();
    }

    @Override
    public Set<String> getTargets() {
        return Collections.unmodifiableSet(this.mixinMapping.keySet());
    }

    public Set<String> getUnhandledTargets() {
        return Collections.unmodifiableSet(this.unhandledTargets);
    }

    public Level getLoggingLevel() {
        return this.verboseLogging ? Level.INFO : Level.DEBUG;
    }

    public boolean packageMatch(String className) {
        return className.startsWith(this.mixinPackage);
    }

    public boolean hasMixinsFor(String targetClass) {
        return this.mixinMapping.containsKey(targetClass);
    }

    boolean hasPendingMixinsFor(String targetClass) {
        if (this.packageMatch(targetClass)) {
            return false;
        }
        for (MixinInfo pendingMixin : this.pendingMixins) {
            if (!pendingMixin.hasDeclaredTarget(targetClass)) continue;
            return true;
        }
        return false;
    }

    public List<MixinInfo> getMixinsFor(String targetClass) {
        return this.mixinsFor(targetClass);
    }

    private List<MixinInfo> mixinsFor(String targetClass) {
        List<MixinInfo> mixins = this.mixinMapping.get(targetClass);
        if (mixins == null) {
            mixins = new ArrayList<MixinInfo>();
            this.mixinMapping.put(targetClass, mixins);
        }
        return mixins;
    }

    public List<String> reloadMixin(String mixinClass, ClassNode classNode) {
        for (MixinInfo mixin : this.mixins) {
            if (!mixin.getClassName().equals(mixinClass)) continue;
            mixin.reloadMixin(classNode);
            return mixin.getTargetClasses();
        }
        return Collections.emptyList();
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(MixinConfig other) {
        if (other == null) {
            return 0;
        }
        if (other.priority == this.priority) {
            return this.order - other.order;
        }
        return this.priority - other.priority;
    }

    static Config create(String configFile, MixinEnvironment outer) {
        try {
            IMixinService service = MixinService.getService();
            InputStream resource = service.getResourceAsStream(configFile);
            if (resource == null) {
                throw new IllegalArgumentException(String.format("The specified resource '%s' was invalid or could not be read", configFile));
            }
            MixinConfig config = (MixinConfig)new Gson().fromJson((Reader)new InputStreamReader(resource), MixinConfig.class);
            if (config.onLoad(service, configFile, outer)) {
                return config.getHandle();
            }
            return null;
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(String.format("The specified resource '%s' was invalid or could not be read", configFile), ex);
        }
    }

    private static int getCollectionSize(Collection<?> ... collections) {
        int total = 0;
        for (Collection<?> collection : collections) {
            if (collection == null) continue;
            total += collection.size();
        }
        return total;
    }

    static interface IListener {
        public void onPrepare(MixinInfo var1);

        public void onInit(MixinInfo var1);
    }

    static class OverwriteOptions {
        @SerializedName(value="conformVisibility")
        boolean conformAccessModifiers;
        @SerializedName(value="requireAnnotations")
        boolean requireOverwriteAnnotations;

        OverwriteOptions() {
        }

        void mergeFrom(OverwriteOptions parent) {
            this.conformAccessModifiers |= parent.conformAccessModifiers;
            this.requireOverwriteAnnotations |= parent.requireOverwriteAnnotations;
        }
    }

    static class InjectorOptions {
        @SerializedName(value="defaultRequire")
        int defaultRequireValue = 0;
        @SerializedName(value="defaultGroup")
        String defaultGroup = "default";
        @SerializedName(value="injectionPoints")
        List<String> injectionPoints;
        @SerializedName(value="maxShiftBy")
        int maxShiftBy = 0;

        InjectorOptions() {
        }

        void mergeFrom(InjectorOptions parent) {
            if (this.defaultRequireValue == 0) {
                this.defaultRequireValue = parent.defaultRequireValue;
            }
            if ("default".equals(this.defaultGroup)) {
                this.defaultGroup = parent.defaultGroup;
            }
            if (this.maxShiftBy == 0) {
                this.maxShiftBy = parent.maxShiftBy;
            }
        }
    }
}

