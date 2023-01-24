/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.service;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.ITransformer;

public interface ITreeClassTransformer
extends ITransformer {
    public boolean transformClassNode(String var1, String var2, ClassNode var3);
}

