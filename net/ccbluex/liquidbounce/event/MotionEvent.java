/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.event;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.Event;
import net.ccbluex.liquidbounce.event.EventState;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u000e"}, d2={"Lnet/ccbluex/liquidbounce/event/MotionEvent;", "Lnet/ccbluex/liquidbounce/event/Event;", "eventState", "Lnet/ccbluex/liquidbounce/event/EventState;", "onGround", "", "(Lnet/ccbluex/liquidbounce/event/EventState;Z)V", "getEventState", "()Lnet/ccbluex/liquidbounce/event/EventState;", "getOnGround", "()Z", "setOnGround", "(Z)V", "isPre", "Relaxed"})
public final class MotionEvent
extends Event {
    @NotNull
    private final EventState eventState;
    private boolean onGround;

    public final boolean isPre() {
        return this.eventState == EventState.PRE;
    }

    @NotNull
    public final EventState getEventState() {
        return this.eventState;
    }

    public final boolean getOnGround() {
        return this.onGround;
    }

    public final void setOnGround(boolean bl) {
        this.onGround = bl;
    }

    public MotionEvent(@NotNull EventState eventState, boolean onGround) {
        Intrinsics.checkParameterIsNotNull((Object)eventState, "eventState");
        this.eventState = eventState;
        this.onGround = onGround;
    }
}

