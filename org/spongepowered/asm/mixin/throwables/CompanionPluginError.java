/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.throwables;

public class CompanionPluginError
extends LinkageError {
    private static final long serialVersionUID = 1L;

    public CompanionPluginError() {
    }

    public CompanionPluginError(String message) {
        super(message);
    }

    public CompanionPluginError(String message, Throwable cause) {
        super(message, cause);
    }
}

