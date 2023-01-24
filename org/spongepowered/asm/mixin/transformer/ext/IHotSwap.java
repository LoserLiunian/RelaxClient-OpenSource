/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer.ext;

import org.objectweb.asm.tree.ClassNode;

public interface IHotSwap {
    public void registerMixinClass(String var1);

    public void registerTargetClass(String var1, ClassNode var2);
}

