/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ActivityStack;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class MixinApplicatorException
extends InvalidMixinException {
    private static final long serialVersionUID = 1L;

    public MixinApplicatorException(IMixinInfo context, String message) {
        super(context, message, (ActivityStack)null);
    }

    public MixinApplicatorException(IMixinInfo context, String message, ActivityStack activityContext) {
        super(context, message, activityContext);
    }

    public MixinApplicatorException(IMixinContext context, String message) {
        super(context, message, (ActivityStack)null);
    }

    public MixinApplicatorException(IMixinContext context, String message, ActivityStack activityContext) {
        super(context, message, activityContext);
    }

    public MixinApplicatorException(IMixinInfo mixin, String message, Throwable cause) {
        super(mixin, message, cause, (ActivityStack)null);
    }

    public MixinApplicatorException(IMixinInfo mixin, String message, Throwable cause, ActivityStack activityContext) {
        super(mixin, message, cause, activityContext);
    }

    public MixinApplicatorException(IMixinContext mixin, String message, Throwable cause) {
        super(mixin, message, cause, (ActivityStack)null);
    }

    public MixinApplicatorException(IMixinContext mixin, String message, Throwable cause, ActivityStack activityContext) {
        super(mixin, message, cause, activityContext);
    }

    public MixinApplicatorException(IMixinInfo mixin, Throwable cause) {
        super(mixin, cause, (ActivityStack)null);
    }

    public MixinApplicatorException(IMixinInfo mixin, Throwable cause, ActivityStack activityContext) {
        super(mixin, cause, activityContext);
    }

    public MixinApplicatorException(IMixinContext mixin, Throwable cause) {
        super(mixin, cause, (ActivityStack)null);
    }

    public MixinApplicatorException(IMixinContext mixin, Throwable cause, ActivityStack activityContext) {
        super(mixin, cause, activityContext);
    }
}

