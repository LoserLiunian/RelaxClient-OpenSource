/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.api.minecraft.util;

import kotlin.Metadata;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0007\u0010\u0005\"\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/util/ITimer;", "", "renderPartialTicks", "", "getRenderPartialTicks", "()F", "timerSpeed", "getTimerSpeed", "setTimerSpeed", "(F)V", "Relaxed"})
public interface ITimer {
    public float getTimerSpeed();

    public void setTimerSpeed(float var1);

    public float getRenderPartialTicks();
}

