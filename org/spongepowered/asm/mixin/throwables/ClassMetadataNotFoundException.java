/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.throwables;

import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.ActivityStack;

public class ClassMetadataNotFoundException
extends MixinException {
    private static final long serialVersionUID = 1L;

    public ClassMetadataNotFoundException(String message) {
        super(message);
    }

    public ClassMetadataNotFoundException(String message, ActivityStack context) {
        super(message, context);
    }

    public ClassMetadataNotFoundException(Throwable cause) {
        super(cause);
    }

    public ClassMetadataNotFoundException(Throwable cause, ActivityStack context) {
        super(cause, context);
    }

    public ClassMetadataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassMetadataNotFoundException(String message, Throwable cause, ActivityStack context) {
        super(message, cause, context);
    }
}

