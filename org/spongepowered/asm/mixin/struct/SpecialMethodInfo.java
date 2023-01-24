/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodNode
 */
package org.spongepowered.asm.mixin.struct;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.IInjectionPointContext;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.asm.MethodNodeEx;

public abstract class SpecialMethodInfo
implements IInjectionPointContext {
    protected final AnnotationNode annotation;
    protected final String annotationType;
    protected final ClassNode classNode;
    protected final MethodNode method;
    protected final String methodName;
    protected final MixinTargetContext mixin;

    public SpecialMethodInfo(MixinTargetContext mixin, MethodNode method, AnnotationNode annotation) {
        this.mixin = mixin;
        this.method = method;
        this.annotation = annotation;
        this.annotationType = this.annotation != null ? "@" + Bytecode.getSimpleName(this.annotation) : "Undecorated injector";
        this.classNode = mixin.getTargetClassNode();
        this.methodName = MethodNodeEx.getName(method);
    }

    @Override
    public final IMixinContext getContext() {
        return this.mixin;
    }

    @Override
    public final AnnotationNode getAnnotation() {
        return this.annotation;
    }

    public final ClassNode getClassNode() {
        return this.classNode;
    }

    public final ClassInfo getClassInfo() {
        return this.mixin.getClassInfo();
    }

    @Override
    public final MethodNode getMethod() {
        return this.method;
    }

    public String getMethodName() {
        return this.methodName;
    }
}

