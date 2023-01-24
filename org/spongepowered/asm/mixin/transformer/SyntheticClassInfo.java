/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Preconditions;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.service.ISyntheticClassInfo;

public abstract class SyntheticClassInfo
implements ISyntheticClassInfo {
    protected final IMixinInfo mixin;
    protected final String name;

    protected SyntheticClassInfo(IMixinInfo mixin, String name) {
        Preconditions.checkNotNull((Object)mixin, (Object)"parent");
        Preconditions.checkNotNull((Object)name, (Object)"name");
        this.mixin = mixin;
        this.name = name.replace('.', '/');
    }

    @Override
    public final IMixinInfo getMixin() {
        return this.mixin;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getClassName() {
        return this.name.replace('/', '.');
    }
}

