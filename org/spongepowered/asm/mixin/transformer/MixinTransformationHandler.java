/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.serviceapi.ILaunchPluginService$Phase
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import java.util.EnumSet;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.launch.IClassProcessor;
import org.spongepowered.asm.launch.Phases;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.service.ISyntheticClassInfo;
import org.spongepowered.asm.service.ISyntheticClassRegistry;

public class MixinTransformationHandler
implements IClassProcessor {
    private final Object initialisationLock = new Object();
    private MixinTransformer transformer;
    private ISyntheticClassRegistry registry;

    @Override
    public EnumSet<ILaunchPluginService.Phase> handlesClass(Type classType, boolean isEmpty, String reason) {
        if (!isEmpty) {
            return Phases.AFTER_ONLY;
        }
        if (this.registry == null) {
            return null;
        }
        ISyntheticClassInfo syntheticClass = this.registry.findSyntheticClass(classType.getClassName());
        return syntheticClass != null ? Phases.AFTER_ONLY : null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized boolean processClass(ILaunchPluginService.Phase phase, ClassNode classNode, Type classType, String reason) {
        if (phase == ILaunchPluginService.Phase.BEFORE) {
            return false;
        }
        MixinTransformer transformer = null;
        if (this.transformer == null) {
            Object object = this.initialisationLock;
            synchronized (object) {
                transformer = this.transformer;
                if (transformer == null) {
                    transformer = this.transformer = new MixinTransformer();
                    this.registry = transformer.getExtensions().getSyntheticClassRegistry();
                }
            }
        } else {
            transformer = this.transformer;
        }
        MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        ISyntheticClassInfo syntheticClass = this.registry.findSyntheticClass(classType.getClassName());
        if (syntheticClass != null) {
            return transformer.generateClass(environment, classType.getClassName(), classNode);
        }
        return transformer.transformClass(environment, classType.getClassName(), classNode);
    }
}

