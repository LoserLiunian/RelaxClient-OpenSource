/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;

public class IllegalClassLoadError
extends MixinTransformerError {
    private static final long serialVersionUID = 1L;

    public IllegalClassLoadError(String message) {
        super(message);
    }

    public IllegalClassLoadError(Throwable cause) {
        super(cause);
    }

    public IllegalClassLoadError(String message, Throwable cause) {
        super(message, cause);
    }
}

