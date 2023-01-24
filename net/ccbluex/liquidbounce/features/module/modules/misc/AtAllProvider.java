/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketChatMessage;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.utils.timer.TimeUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="AtAllProvider", description="Automatically mentions everyone on the server when using '@a' in your message.", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/misc/AtAllProvider;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "delay", "", "maxDelayValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "minDelayValue", "msTimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "retryQueue", "", "", "retryValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "sendQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "onDisable", "", "onPacket", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class AtAllProvider
extends Module {
    private final IntegerValue minDelayValue = new IntegerValue(this, "MinDelay", 500, 0, 20000){
        final /* synthetic */ AtAllProvider this$0;

        protected void onChanged(int oldValue, int newValue) {
            int i = ((Number)AtAllProvider.access$getMaxDelayValue$p(this.this$0).get()).intValue();
            if (i < newValue) {
                this.set(i);
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final IntegerValue maxDelayValue = new IntegerValue(this, "MaxDelay", 1000, 0, 20000){
        final /* synthetic */ AtAllProvider this$0;

        protected void onChanged(int oldValue, int newValue) {
            int i = ((Number)AtAllProvider.access$getMinDelayValue$p(this.this$0).get()).intValue();
            if (i > newValue) {
                this.set(i);
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final BoolValue retryValue = new BoolValue("Retry", false);
    private final LinkedBlockingQueue<String> sendQueue = new LinkedBlockingQueue();
    private final List<String> retryQueue = new ArrayList();
    private final MSTimer msTimer = new MSTimer();
    private long delay = TimeUtils.randomDelay(((Number)this.minDelayValue.get()).intValue(), ((Number)this.maxDelayValue.get()).intValue());

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onDisable() {
        Collection<String> collection = this.sendQueue;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (collection) {
            boolean bl3 = false;
            this.sendQueue.clear();
            Unit unit = Unit.INSTANCE;
        }
        collection = this.retryQueue;
        bl = false;
        boolean bl4 = false;
        synchronized (collection) {
            boolean bl5 = false;
            this.retryQueue.clear();
            Unit unit = Unit.INSTANCE;
        }
        super.onDisable();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public final void onUpdate(@Nullable UpdateEvent event) {
        if (!this.msTimer.hasTimePassed(this.delay)) {
            return;
        }
        try {
            LinkedBlockingQueue<String> linkedBlockingQueue = this.sendQueue;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (linkedBlockingQueue) {
                boolean bl3 = false;
                if (this.sendQueue.isEmpty()) {
                    if (!((Boolean)this.retryValue.get()).booleanValue() || this.retryQueue.isEmpty()) {
                        return;
                    }
                    this.sendQueue.addAll((Collection<String>)this.retryQueue);
                }
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                String string = this.sendQueue.take();
                Intrinsics.checkExpressionValueIsNotNull(string, "sendQueue.take()");
                iEntityPlayerSP.sendChatMessage(string);
                this.msTimer.reset();
                this.delay = TimeUtils.randomDelay(((Number)this.minDelayValue.get()).intValue(), ((Number)this.maxDelayValue.get()).intValue());
                Unit unit = Unit.INSTANCE;
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        ICPacketChatMessage packetChatMessage;
        String message;
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (MinecraftInstance.classProvider.isCPacketChatMessage(event.getPacket()) && StringsKt.contains$default((CharSequence)(message = (packetChatMessage = event.getPacket().asCPacketChatMessage()).getMessage()), "@a", false, 2, null)) {
            LinkedBlockingQueue<String> linkedBlockingQueue = this.sendQueue;
            boolean bl = false;
            boolean bl2 = false;
            synchronized (linkedBlockingQueue) {
                boolean bl3 = false;
                for (INetworkPlayerInfo playerInfo : MinecraftInstance.mc.getNetHandler().getPlayerInfoMap()) {
                    String playerName = playerInfo.getGameProfile().getName();
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Intrinsics.areEqual(playerName, iEntityPlayerSP.getName())) continue;
                    String string = playerName;
                    Intrinsics.checkExpressionValueIsNotNull(string, "playerName");
                    this.sendQueue.add(StringsKt.replace$default(message, "@a", string, false, 4, null));
                }
                if (((Boolean)this.retryValue.get()).booleanValue()) {
                    List<String> list = this.retryQueue;
                    boolean bl4 = false;
                    boolean bl5 = false;
                    synchronized (list) {
                        void $this$toTypedArray$iv;
                        boolean bl6 = false;
                        this.retryQueue.clear();
                        Collection collection = this.sendQueue;
                        List<String> list2 = this.retryQueue;
                        boolean $i$f$toTypedArray = false;
                        void thisCollection$iv = $this$toTypedArray$iv;
                        String[] stringArray = thisCollection$iv.toArray(new String[0]);
                        if (stringArray == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                        String[] stringArray2 = stringArray;
                        bl5 = list2.addAll((Collection<String>)CollectionsKt.listOf(Arrays.copyOf(stringArray2, stringArray2.length)));
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            event.cancelEvent();
        }
    }

    public static final /* synthetic */ IntegerValue access$getMaxDelayValue$p(AtAllProvider $this) {
        return $this.maxDelayValue;
    }

    public static final /* synthetic */ IntegerValue access$getMinDelayValue$p(AtAllProvider $this) {
        return $this.minDelayValue;
    }
}

