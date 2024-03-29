/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.ActivityStack;

public class InvalidMixinException
extends MixinException {
    private static final long serialVersionUID = 2L;
    private final IMixinInfo mixin;

    public InvalidMixinException(IMixinInfo mixin, String message) {
        super(message);
        this.mixin = mixin;
    }

    public InvalidMixinException(IMixinInfo mixin, String message, ActivityStack activityContext) {
        super(message, activityContext);
        this.mixin = mixin;
    }

    public InvalidMixinException(IMixinContext context, String message) {
        this(context.getMixin(), message);
    }

    public InvalidMixinException(IMixinContext context, String message, ActivityStack activityContext) {
        this(context.getMixin(), message, activityContext);
    }

    public InvalidMixinException(IMixinInfo mixin, Throwable cause) {
        super(cause);
        this.mixin = mixin;
    }

    public InvalidMixinException(IMixinInfo mixin, Throwable cause, ActivityStack activityContext) {
        super(cause, activityContext);
        this.mixin = mixin;
    }

    public InvalidMixinException(IMixinContext context, Throwable cause) {
        this(context.getMixin(), cause);
    }

    public InvalidMixinException(IMixinContext context, Throwable cause, ActivityStack activityContext) {
        this(context.getMixin(), cause, activityContext);
    }

    public InvalidMixinException(IMixinInfo mixin, String message, Throwable cause) {
        super(message, cause);
        this.mixin = mixin;
    }

    public InvalidMixinException(IMixinInfo mixin, String message, Throwable cause, ActivityStack activityContext) {
        super(message, cause, activityContext);
        this.mixin = mixin;
    }

    public InvalidMixinException(IMixinContext context, String message, Throwable cause) {
        super(message, cause);
        this.mixin = context.getMixin();
    }

    public InvalidMixinException(IMixinContext context, String message, Throwable cause, ActivityStack activityContext) {
        super(message, cause, activityContext);
        this.mixin = context.getMixin();
    }

    public IMixinInfo getMixin() {
        return this.mixin;
    }
}

