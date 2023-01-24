/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.Resources
 *  cpw.mods.modlauncher.api.IEnvironment
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$ITransformerLoader
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$Phase
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.launch;

import com.google.common.io.Resources;
import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.launch.IClassProcessor;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.launch.Phases;
import org.spongepowered.asm.launch.platform.CommandLineOptions;
import org.spongepowered.asm.service.IClassBytecodeProvider;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.service.modlauncher.MixinServiceModLauncher;
import org.spongepowered.asm.service.modlauncher.ModLauncherAuditTrail;

public class MixinLaunchPlugin
implements ILaunchPluginService,
IClassBytecodeProvider {
    public static final String NAME = "mixin";
    private final List<IClassProcessor> processors = new ArrayList<IClassProcessor>();
    private List<String> commandLineMixins;
    private ILaunchPluginService.ITransformerLoader transformerLoader;
    private MixinServiceModLauncher service;
    private ModLauncherAuditTrail auditTrail;

    public String name() {
        return NAME;
    }

    public EnumSet<ILaunchPluginService.Phase> handlesClass(Type classType, boolean isEmpty) {
        throw new IllegalStateException("Outdated ModLauncher");
    }

    public boolean processClass(ILaunchPluginService.Phase phase, ClassNode classNode, Type classType) {
        throw new IllegalStateException("Outdated ModLauncher");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public EnumSet<ILaunchPluginService.Phase> handlesClass(Type classType, boolean isEmpty, String reason) {
        if (NAME.equals(reason)) {
            return Phases.NONE;
        }
        EnumSet<ILaunchPluginService.Phase> phases = EnumSet.noneOf(ILaunchPluginService.Phase.class);
        List<IClassProcessor> list = this.processors;
        synchronized (list) {
            for (IClassProcessor postProcessor : this.processors) {
                EnumSet<ILaunchPluginService.Phase> processorVote = postProcessor.handlesClass(classType, isEmpty, reason);
                if (processorVote == null) continue;
                phases.addAll(processorVote);
            }
        }
        return phases;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean processClass(ILaunchPluginService.Phase phase, ClassNode classNode, Type classType, String reason) {
        if (NAME.equals(reason)) {
            return false;
        }
        boolean processed = false;
        List<IClassProcessor> list = this.processors;
        synchronized (list) {
            for (IClassProcessor postProcessor : this.processors) {
                processed |= postProcessor.processClass(phase, classNode, classType, reason);
            }
        }
        return processed;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void init(IEnvironment environment, List<String> commandLineMixins) {
        IMixinService service = MixinService.getService();
        if (!(service instanceof MixinServiceModLauncher)) {
            throw new IllegalStateException("Unsupported service type for ModLauncher Mixin Service");
        }
        this.service = (MixinServiceModLauncher)service;
        this.auditTrail = (ModLauncherAuditTrail)this.service.getAuditTrail();
        List<IClassProcessor> list = this.processors;
        synchronized (list) {
            this.processors.addAll(this.service.getProcessors());
        }
        this.commandLineMixins = commandLineMixins;
        this.service.onInit(this);
    }

    public void customAuditConsumer(String className, Consumer<String[]> auditDataAcceptor) {
        if (this.auditTrail != null) {
            this.auditTrail.setConsumer(className, auditDataAcceptor);
        }
    }

    @Deprecated
    public void addResource(Path resource, String name) {
        this.service.getPrimaryContainer().addResource(name, resource);
    }

    public void addResources(List<Map.Entry<String, Path>> resources) {
        this.service.getPrimaryContainer().addResources(resources);
    }

    public <T> T getExtension() {
        return null;
    }

    public void initializeLaunch(ILaunchPluginService.ITransformerLoader transformerLoader, Path[] specialPaths) {
        this.transformerLoader = transformerLoader;
        MixinBootstrap.doInit(CommandLineOptions.of(this.commandLineMixins));
        MixinBootstrap.inject();
        this.service.onStartup();
    }

    @Override
    public ClassNode getClassNode(String name) throws ClassNotFoundException, IOException {
        return this.getClassNode(name, true);
    }

    @Override
    public ClassNode getClassNode(String name, boolean runTransformers) throws ClassNotFoundException, IOException {
        byte[] classBytes;
        if (!runTransformers) {
            throw new IllegalArgumentException("ModLauncher service does not currently support retrieval of untransformed bytecode");
        }
        try {
            classBytes = this.transformerLoader.buildTransformedClassNodeFor(name);
        }
        catch (ClassNotFoundException ex) {
            URL url = Thread.currentThread().getContextClassLoader().getResource(name.replace('.', '/') + ".class");
            if (url == null) {
                throw ex;
            }
            try {
                classBytes = Resources.asByteSource((URL)url).read();
            }
            catch (IOException ioex) {
                throw ex;
            }
        }
        if (classBytes == null) {
            throw new ClassNotFoundException(name.replace('/', '.'));
        }
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(classBytes);
        classReader.accept((ClassVisitor)classNode, 8);
        return classNode;
    }
}

