/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.MethodNode
 */
package org.spongepowered.asm.mixin.injection.struct;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.invoke.ModifyArgsInjector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

@InjectionInfo.AnnotationType(value=ModifyArgs.class)
@InjectionInfo.HandlerPrefix(value="args")
public class ModifyArgsInjectionInfo
extends InjectionInfo {
    public ModifyArgsInjectionInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        super(mixin, method, annotation);
    }

    @Override
    protected Injector parseInjector(AnnotationNode injectAnnotation) {
        return new ModifyArgsInjector(this);
    }

    @Override
    protected String getDescription() {
        return "Multi-argument modifier method";
    }
}

