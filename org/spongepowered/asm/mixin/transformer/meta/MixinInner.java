/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={})
@Retention(value=RetentionPolicy.CLASS)
public @interface MixinInner {
    public String mixin();

    public String name();
}

