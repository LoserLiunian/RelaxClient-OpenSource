/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer;

import java.lang.reflect.Constructor;
import java.util.List;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.DefaultExtensions;
import org.spongepowered.asm.mixin.transformer.IMixinTransformer;
import org.spongepowered.asm.mixin.transformer.MixinClassGenerator;
import org.spongepowered.asm.mixin.transformer.MixinProcessor;
import org.spongepowered.asm.mixin.transformer.SyntheticClassRegistry;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.IExtensionRegistry;
import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;
import org.spongepowered.asm.transformers.TreeTransformer;
import org.spongepowered.asm.util.asm.ASM;

class MixinTransformer
extends TreeTransformer
implements IMixinTransformer {
    private static final String MIXIN_AGENT_CLASS = "org.spongepowered.tools.agent.MixinAgent";
    private final SyntheticClassRegistry syntheticClassRegistry;
    private final Extensions extensions;
    private final IHotSwap hotSwapper;
    private final MixinProcessor processor;
    private final MixinClassGenerator generator;

    public MixinTransformer() {
        MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        Object globalMixinTransformer = environment.getActiveTransformer();
        if (globalMixinTransformer instanceof IMixinTransformer) {
            throw new MixinException("Terminating MixinTransformer instance " + this);
        }
        environment.setActiveTransformer(this);
        this.syntheticClassRegistry = new SyntheticClassRegistry();
        this.extensions = new Extensions(this.syntheticClassRegistry);
        this.hotSwapper = this.initHotSwapper(environment);
        this.processor = new MixinProcessor(environment, this.extensions, this.hotSwapper);
        this.generator = new MixinClassGenerator(environment, this.extensions);
        DefaultExtensions.create(environment, this.extensions, this.syntheticClassRegistry);
    }

    private IHotSwap initHotSwapper(MixinEnvironment environment) {
        if (!environment.getOption(MixinEnvironment.Option.HOT_SWAP)) {
            return null;
        }
        try {
            MixinProcessor.logger.info("Attempting to load Hot-Swap agent");
            Class<?> clazz = Class.forName(MIXIN_AGENT_CLASS);
            Constructor<?> ctor = clazz.getDeclaredConstructor(IMixinTransformer.class);
            return (IHotSwap)ctor.newInstance(this);
        }
        catch (Throwable th) {
            MixinProcessor.logger.info("Hot-swap agent could not be loaded, hot swapping of mixins won't work. {}: {}", new Object[]{th.getClass().getSimpleName(), th.getMessage()});
            return null;
        }
    }

    @Override
    public IExtensionRegistry getExtensions() {
        return this.extensions;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isDelegationExcluded() {
        return true;
    }

    @Override
    public void audit(MixinEnvironment environment) {
        this.processor.audit(environment);
    }

    @Override
    public List<String> reload(String mixinClass, ClassNode classNode) {
        return this.processor.reload(mixinClass, classNode);
    }

    @Override
    public byte[] transformClassBytes(String name, String transformedName, byte[] basicClass) {
        if (transformedName == null) {
            return basicClass;
        }
        MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        if (basicClass == null) {
            return this.generateClass(environment, transformedName);
        }
        return this.transformClass(environment, transformedName, basicClass);
    }

    public byte[] transformClass(MixinEnvironment environment, String name, byte[] classBytes) {
        ClassNode classNode = this.readClass(classBytes);
        if (this.processor.applyMixins(environment, name, classNode)) {
            return this.writeClass(classNode);
        }
        return classBytes;
    }

    public boolean transformClass(MixinEnvironment environment, String name, ClassNode classNode) {
        return this.processor.applyMixins(environment, name, classNode);
    }

    public byte[] generateClass(MixinEnvironment environment, String name) {
        ClassNode classNode = MixinTransformer.createEmptyClass(name);
        if (this.generator.generateClass(environment, name, classNode)) {
            return this.writeClass(classNode);
        }
        return null;
    }

    public boolean generateClass(MixinEnvironment environment, String name, ClassNode classNode) {
        return this.generator.generateClass(environment, name, classNode);
    }

    private static ClassNode createEmptyClass(String name) {
        ClassNode classNode = new ClassNode(ASM.API_VERSION);
        classNode.name = name.replace('.', '/');
        classNode.version = MixinEnvironment.getCompatibilityLevel().classVersion();
        classNode.superName = "java/lang/Object";
        return classNode;
    }
}

