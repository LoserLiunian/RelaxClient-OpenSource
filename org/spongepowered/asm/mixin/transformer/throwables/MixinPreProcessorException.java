/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.ActivityStack;

public class MixinPreProcessorException
extends MixinException {
    private static final long serialVersionUID = 1L;

    public MixinPreProcessorException(String message, ActivityStack context) {
        super(message, context);
    }

    public MixinPreProcessorException(String message, Throwable cause, ActivityStack context) {
        super(message, cause, context);
    }
}

