/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.ActivityStack;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class InvalidInjectionException
extends InvalidMixinException {
    private static final long serialVersionUID = 2L;
    private final InjectionInfo info;

    public InvalidInjectionException(IMixinContext context, String message) {
        super(context, message);
        this.info = null;
    }

    public InvalidInjectionException(IMixinContext context, String message, ActivityStack activityContext) {
        super(context, message, activityContext);
        this.info = null;
    }

    public InvalidInjectionException(InjectionInfo info, String message) {
        super(info.getContext(), message);
        this.info = info;
    }

    public InvalidInjectionException(InjectionInfo info, String message, ActivityStack activityContext) {
        super(info.getContext(), message, activityContext);
        this.info = info;
    }

    public InvalidInjectionException(IMixinContext context, Throwable cause) {
        super(context, cause);
        this.info = null;
    }

    public InvalidInjectionException(IMixinContext context, Throwable cause, ActivityStack activityContext) {
        super(context, cause, activityContext);
        this.info = null;
    }

    public InvalidInjectionException(InjectionInfo info, Throwable cause) {
        super(info.getContext(), cause);
        this.info = info;
    }

    public InvalidInjectionException(InjectionInfo info, Throwable cause, ActivityStack activityContext) {
        super(info.getContext(), cause, activityContext);
        this.info = info;
    }

    public InvalidInjectionException(IMixinContext context, String message, Throwable cause) {
        super(context, message, cause);
        this.info = null;
    }

    public InvalidInjectionException(IMixinContext context, String message, Throwable cause, ActivityStack activityContext) {
        super(context, message, cause, activityContext);
        this.info = null;
    }

    public InvalidInjectionException(InjectionInfo info, String message, Throwable cause) {
        super(info.getContext(), message, cause);
        this.info = info;
    }

    public InvalidInjectionException(InjectionInfo info, String message, Throwable cause, ActivityStack activityContext) {
        super(info.getContext(), message, cause, activityContext);
        this.info = info;
    }

    public InjectionInfo getInjectionInfo() {
        return this.info;
    }
}

