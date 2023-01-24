/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.service;

import java.io.IOException;
import org.objectweb.asm.tree.ClassNode;

public interface IClassBytecodeProvider {
    public ClassNode getClassNode(String var1) throws ClassNotFoundException, IOException;

    public ClassNode getClassNode(String var1, boolean var2) throws ClassNotFoundException, IOException;
}

