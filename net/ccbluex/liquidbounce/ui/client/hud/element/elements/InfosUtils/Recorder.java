/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.handshake.client.C00Handshake
 *  net.minecraft.network.play.server.SPacketChat
 *  net.minecraft.network.play.server.SPacketTitle
 *  net.minecraft.util.text.ITextComponent
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements.InfosUtils;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.event.AttackEvent;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\u0010\u0010$\u001a\u00020!2\u0006\u0010\"\u001a\u00020%H\u0003J\u0010\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020'H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b\u00a8\u0006("}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/InfosUtils/Recorder;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "()V", "ban", "", "getBan", "()I", "setBan", "(I)V", "killCounts", "getKillCounts", "setKillCounts", "startTime", "", "getStartTime", "()J", "setStartTime", "(J)V", "syncEntity", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "getSyncEntity", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "setSyncEntity", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;)V", "totalPlayed", "getTotalPlayed", "setTotalPlayed", "win", "getWin", "setWin", "handleEvents", "", "onAttack", "", "event", "Lnet/ccbluex/liquidbounce/event/AttackEvent;", "onPacket", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class Recorder
implements Listenable {
    @Nullable
    private static IEntityLivingBase syncEntity;
    private static int killCounts;
    private static int totalPlayed;
    private static int win;
    private static int ban;
    private static long startTime;
    public static final Recorder INSTANCE;

    @Nullable
    public final IEntityLivingBase getSyncEntity() {
        return syncEntity;
    }

    public final void setSyncEntity(@Nullable IEntityLivingBase iEntityLivingBase) {
        syncEntity = iEntityLivingBase;
    }

    public final int getKillCounts() {
        return killCounts;
    }

    public final void setKillCounts(int n) {
        killCounts = n;
    }

    public final int getTotalPlayed() {
        return totalPlayed;
    }

    public final void setTotalPlayed(int n) {
        totalPlayed = n;
    }

    public final int getWin() {
        return win;
    }

    public final void setWin(int n) {
        win = n;
    }

    public final int getBan() {
        return ban;
    }

    public final void setBan(int n) {
        ban = n;
    }

    public final long getStartTime() {
        return startTime;
    }

    public final void setStartTime(long l) {
        startTime = l;
    }

    @EventTarget
    public final void onAttack(@NotNull AttackEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        syncEntity = (IEntityLivingBase)event.getTargetEntity();
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityLivingBase iEntityLivingBase = syncEntity;
        if (iEntityLivingBase == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityLivingBase.isDead()) {
            ++killCounts;
            syncEntity = null;
        }
    }

    @EventTarget
    private final void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof C00Handshake) {
            startTime = System.currentTimeMillis();
        }
        IPacket iPacket = event.getPacket();
        if (iPacket == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.minecraft.network.play.server.SPacketChat");
        }
        ITextComponent iTextComponent = ((SPacketChat)iPacket).func_148915_c();
        Intrinsics.checkExpressionValueIsNotNull(iTextComponent, "(event.packet as SPacketChat).chatComponent");
        String message = iTextComponent.func_150260_c();
        IPacket packet = event.getPacket();
        if (packet instanceof SPacketTitle) {
            int n;
            String title;
            ITextComponent iTextComponent2 = ((SPacketTitle)packet).func_179805_b();
            if (iTextComponent2 == null) {
                return;
            }
            String string = title = iTextComponent2.func_150254_d();
            Intrinsics.checkExpressionValueIsNotNull(string, "title");
            if (StringsKt.startsWith$default(string, "\u00a76\u00a7l", false, 2, null) && StringsKt.endsWith$default(title, "\u00a7r", false, 2, null) || StringsKt.startsWith$default(title, "\u00a7c\u00a7lYOU", false, 2, null) && StringsKt.endsWith$default(title, "\u00a7r", false, 2, null) || StringsKt.startsWith$default(title, "\u00a7c\u00a7lGame", false, 2, null) && StringsKt.endsWith$default(title, "\u00a7r", false, 2, null) || StringsKt.startsWith$default(title, "\u00a7c\u00a7lWITH", false, 2, null) && StringsKt.endsWith$default(title, "\u00a7r", false, 2, null) || StringsKt.startsWith$default(title, "\u00a7c\u00a7lYARR", false, 2, null) && StringsKt.endsWith$default(title, "\u00a7r", false, 2, null)) {
                n = totalPlayed;
                totalPlayed = n + 1;
            }
            if (StringsKt.startsWith$default(title, "\u00a76\u00a7l", false, 2, null) && StringsKt.endsWith$default(title, "\u00a7r", false, 2, null)) {
                n = win;
                win = n + 1;
            }
        }
        String string = message;
        Intrinsics.checkExpressionValueIsNotNull(string, "message");
        if (StringsKt.contains$default((CharSequence)string, "Reason", false, 2, null)) {
            int n = ban;
            ban = n + 1;
        }
    }

    @Override
    public boolean handleEvents() {
        return true;
    }

    private Recorder() {
    }

    static {
        Recorder recorder;
        INSTANCE = recorder = new Recorder();
        startTime = System.currentTimeMillis();
    }
}

