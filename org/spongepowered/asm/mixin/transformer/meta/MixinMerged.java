/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface MixinMerged {
    public String mixin();

    public int priority();
}

