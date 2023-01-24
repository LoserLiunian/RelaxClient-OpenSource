/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketClickWindow
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.server.SPacketChat
 *  net.minecraft.network.play.server.SPacketOpenWindow
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.player;

import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.sound.SoundPlayer;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.WorldEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.player.AutoPlay;
import net.ccbluex.liquidbounce.injection.backend.PacketImpl;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.InfosUtils.Recorder;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.MiscUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.ccbluex.liquidbounce.value.TextValue;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketOpenWindow;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="AutoPlay", category=ModuleCategory.PLAYER, description="idk")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u001bH\u0007J\u0016\u0010\u001c\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/player/AutoPlay;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "Clientname", "Lnet/ccbluex/liquidbounce/value/TextValue;", "autogg", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "clickState", "", "clicking", "", "delayValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "queued", "tag", "", "getTag", "()Ljava/lang/String;", "handleEvents", "onEnable", "", "onPacket", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onWorld", "Lnet/ccbluex/liquidbounce/event/WorldEvent;", "queueAutoPlay", "runnable", "Lkotlin/Function0;", "Relaxed"})
public final class AutoPlay
extends Module {
    private int clickState;
    private final BoolValue autogg = new BoolValue("AutoGG", true);
    private final TextValue Clientname = new TextValue("Clientname", "Relaxed");
    private final ListValue modeValue = new ListValue("Server", new String[]{"RedeSky", "Minemora", "HuaYuTing"}, "HuaYuTingGG");
    private final IntegerValue delayValue = new IntegerValue("JoinDelay", 3, 0, 7);
    private boolean clicking;
    private boolean queued;

    @Override
    public void onEnable() {
        this.clickState = 0;
        this.clicking = false;
        this.queued = false;
    }

    /*
     * Unable to fully structure code
     */
    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        $this$unwrap$iv = event.getPacket();
        $i$f$unwrap = false;
        v0 = $this$unwrap$iv;
        if (v0 == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.injection.backend.PacketImpl<*>");
        }
        packet = ((PacketImpl)v0).getWrapped();
        $this$unwrap$iv = (String)this.modeValue.get();
        var4_3 = false;
        v1 = $this$unwrap$iv;
        if (v1 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        v2 = v1.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(v2, "(this as java.lang.String).toLowerCase()");
        $this$unwrap$iv = v2;
        switch ($this$unwrap$iv.hashCode()) {
            case 1381910549: {
                if (!$this$unwrap$iv.equals("hypixel")) ** break;
                break;
            }
            case 1083223725: {
                if (!$this$unwrap$iv.equals("redesky")) ** break;
                if (this.clicking && (packet instanceof CPacketClickWindow || packet instanceof CPacketPlayerDigging)) {
                    event.cancelEvent();
                    return;
                }
                if (this.clickState != 2 || !(packet instanceof SPacketOpenWindow)) ** break;
                event.cancelEvent();
                ** break;
            }
        }
        if (this.clickState == 1 && packet instanceof SPacketOpenWindow) {
            event.cancelEvent();
            ** break;
        }
lbl32:
        // 8 sources

        if (packet instanceof SPacketChat) {
            v3 = ((SPacketChat)packet).func_148915_c();
            Intrinsics.checkExpressionValueIsNotNull(v3, "packet.chatComponent");
            text = v3.func_150260_c();
            var4_4 = (String)this.modeValue.get();
            var5_6 = 0;
            v4 = var4_4;
            if (v4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            v5 = v4.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(v5, "(this as java.lang.String).toLowerCase()");
            var4_4 = v5;
            tmp = -1;
            switch (var4_4.hashCode()) {
                case -1362756060: {
                    if (!var4_4.equals("minemora")) break;
                    tmp = 1;
                    break;
                }
                case -1777040898: {
                    if (!var4_4.equals("huayuting")) break;
                    tmp = 2;
                    break;
                }
            }
            switch (tmp) {
                case 1: {
                    v6 = text;
                    Intrinsics.checkExpressionValueIsNotNull(v6, "text");
                    if (!StringsKt.contains((CharSequence)v6, "w", true)) break;
                    this.queueAutoPlay(onPacket.1.INSTANCE);
                    break;
                }
                case 2: {
                    v7 = text;
                    Intrinsics.checkExpressionValueIsNotNull(v7, "text");
                    if (StringsKt.contains((CharSequence)v7, "      \u559c\u6b22      \u4e00\u822c      \u4e0d\u559c\u6b22", true)) {
                        LiquidBounce.INSTANCE.getHud().addNotification(new Notification(this.getName(), "Game Over", NotifyType.INFO, 0, 0, 24, null));
                        new SoundPlayer().playSound(SoundPlayer.SoundType.VICTORY, LiquidBounce.INSTANCE.getModuleManager().getToggleVolume());
                        if (((Boolean)this.autogg.get()).booleanValue()) {
                            v8 = MinecraftInstance.mc.getThePlayer();
                            if (v8 == null) {
                                Intrinsics.throwNpe();
                            }
                            v8.sendChatMessage("[" + (String)this.Clientname.get() + "] GG  ");
                        }
                        new SoundPlayer().playSound(SoundPlayer.SoundType.VICTORY, LiquidBounce.INSTANCE.getModuleManager().getToggleVolume());
                        v9 = Recorder.INSTANCE;
                        var5_6 = v9.getTotalPlayed();
                        v9.setTotalPlayed(var5_6 + 1);
                        break;
                    }
                    if (StringsKt.contains((CharSequence)text, "\u4f60\u73b0\u5728\u662f\u89c2\u5bdf\u8005\u72b6\u6001. \u6309E\u6253\u5f00\u83dc\u5355.", true)) {
                        LiquidBounce.INSTANCE.getHud().addNotification(new Notification(this.getName(), "Game Over", NotifyType.INFO, 0, 0, 24, null));
                        if (((Boolean)this.autogg.get()).booleanValue()) {
                            v10 = MinecraftInstance.mc.getThePlayer();
                            if (v10 == null) {
                                Intrinsics.throwNpe();
                            }
                            v10.sendChatMessage("[" + (String)this.Clientname.get() + "] GG");
                        }
                        new SoundPlayer().playSound(SoundPlayer.SoundType.VICTORY, LiquidBounce.INSTANCE.getModuleManager().getToggleVolume());
                        v11 = Recorder.INSTANCE;
                        var5_6 = v11.getTotalPlayed();
                        v11.setTotalPlayed(var5_6 + 1);
                        break;
                    }
                    if (!StringsKt.contains((CharSequence)text, "[\u8d77\u5e8a\u6218\u4e89] Game \u7ed3\u675f\uff01\u611f\u8c22\u60a8\u7684\u53c2\u4e0e\uff01", true)) break;
                    LiquidBounce.INSTANCE.getHud().addNotification(new Notification(this.getName(), "Game Over", NotifyType.INFO, 0, 0, 24, null));
                    if (((Boolean)this.autogg.get()).booleanValue()) {
                        v12 = MinecraftInstance.mc.getThePlayer();
                        if (v12 == null) {
                            Intrinsics.throwNpe();
                        }
                        v12.sendChatMessage("[" + (String)this.Clientname.get() + "] GG ");
                    }
                    new SoundPlayer().playSound(SoundPlayer.SoundType.VICTORY, LiquidBounce.INSTANCE.getModuleManager().getToggleVolume());
                    v13 = Recorder.INSTANCE;
                    var5_6 = v13.getTotalPlayed();
                    v13.setTotalPlayed(var5_6 + 1);
                    break;
                }
            }
        }
    }

    private final void queueAutoPlay(Function0<Unit> runnable) {
        if (this.queued) {
            return;
        }
        this.queued = true;
        if (this.getState()) {
            Timer timer = new Timer();
            long l = (long)((Number)this.delayValue.get()).intValue() * (long)1000;
            boolean bl = false;
            boolean bl2 = false;
            TimerTask timerTask2 = new TimerTask(this, runnable){
                final /* synthetic */ AutoPlay this$0;
                final /* synthetic */ Function0 $runnable$inlined;
                {
                    this.this$0 = autoPlay;
                    this.$runnable$inlined = function0;
                }

                public void run() {
                    TimerTask $this$schedule = this;
                    boolean bl = false;
                    if (this.this$0.getState()) {
                        this.$runnable$inlined.invoke();
                    }
                }
            };
            timer.schedule(timerTask2, l);
            new MiscUtils().playSound(MiscUtils.SoundType.VICTORY, -8.0f);
            LiquidBounce.INSTANCE.getHud().addNotification(new Notification(this.getName(), "Sending you to next game in " + ((Number)this.delayValue.get()).intValue() + "s...", NotifyType.INFO, 0, 0, 24, null));
        }
    }

    @EventTarget
    public final void onWorld(@NotNull WorldEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.clicking = false;
        this.clickState = 0;
        this.queued = false;
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.modeValue.get();
    }

    @Override
    public boolean handleEvents() {
        return true;
    }
}

