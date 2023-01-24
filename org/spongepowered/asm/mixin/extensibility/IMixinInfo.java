/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.extensibility;

import java.util.List;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;

public interface IMixinInfo {
    public IMixinConfig getConfig();

    public String getName();

    public String getClassName();

    public String getClassRef();

    public byte[] getClassBytes();

    public boolean isDetachedSuper();

    public ClassNode getClassNode(int var1);

    public List<String> getTargetClasses();

    public int getPriority();

    public MixinEnvironment.Phase getPhase();
}

