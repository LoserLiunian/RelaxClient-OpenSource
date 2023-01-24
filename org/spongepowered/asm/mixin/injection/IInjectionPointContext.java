/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.MethodNode
 */
package org.spongepowered.asm.mixin.injection;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public interface IInjectionPointContext {
    public IMixinContext getContext();

    public MethodNode getMethod();

    public AnnotationNode getAnnotation();
}

