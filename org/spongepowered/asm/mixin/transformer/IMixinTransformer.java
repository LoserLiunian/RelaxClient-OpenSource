/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.List;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ext.IExtensionRegistry;

public interface IMixinTransformer {
    public void audit(MixinEnvironment var1);

    public List<String> reload(String var1, ClassNode var2);

    public byte[] transformClassBytes(String var1, String var2, byte[] var3);

    public IExtensionRegistry getExtensions();
}

