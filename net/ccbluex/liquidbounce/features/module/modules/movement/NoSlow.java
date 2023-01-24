/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.INetHandlerPlayServer
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.util.EnumFacing
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.movement;

import java.util.Collection;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import me.utils.Debug;
import me.utils.PacketUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.enums.EnumFacingType;
import net.ccbluex.liquidbounce.api.enums.WEnumHand;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.item.IItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketAnimation;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayer;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerBlockPlacement;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerDigging;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketUseEntity;
import net.ccbluex.liquidbounce.api.minecraft.util.IEnumFacing;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventState;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MotionEvent;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.SlowDownEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="NoSlow", description="Cancels slowness effects caused by soulsand and using items.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0002J\u000e\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000205J\u001a\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020\u0011H\u0002J\u0006\u0010;\u001a\u00020\u0011J\b\u0010<\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020=2\u0006\u00100\u001a\u000201H\u0007J\u0010\u0010?\u001a\u00020=2\u0006\u00100\u001a\u00020@H\u0007J\u0010\u0010A\u001a\u00020=2\u0006\u00100\u001a\u00020BH\u0007J\u0010\u0010C\u001a\u00020=2\u0006\u00100\u001a\u00020DH\u0007JB\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u0002012\u0006\u0010G\u001a\u00020\u00112\u0006\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020\u00112\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00112\b\b\u0002\u0010M\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0016\u0010'\u001a\u0004\u0018\u00010(8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u0011\u0010+\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006N"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/NoSlow;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "Timer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "blockForwardMultiplier", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "blockStrafeMultiplier", "bowForwardMultiplier", "bowStrafeMultiplier", "consumeForwardMultiplier", "consumeStrafeMultiplier", "customDelayValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "customOnGround", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "isBlocking", "", "()Z", "killAura", "Lnet/ccbluex/liquidbounce/features/module/modules/combat/KillAura;", "getKillAura", "()Lnet/ccbluex/liquidbounce/features/module/modules/combat/KillAura;", "lastBlockingStat", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "msTimer", "nextTemp", "packetBuf", "Ljava/util/LinkedList;", "Lnet/minecraft/network/Packet;", "Lnet/minecraft/network/play/INetHandlerPlayServer;", "packetValue", "getPacketValue", "()Lnet/ccbluex/liquidbounce/value/BoolValue;", "pendingFlagApplyPacket", "sendBuf", "soulsandValue", "getSoulsandValue", "tag", "", "getTag", "()Ljava/lang/String;", "timer", "getTimer", "()Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "waitC03", "OnPost", "event", "Lnet/ccbluex/liquidbounce/event/MotionEvent;", "OnPre", "fuckKotline", "value", "", "getMultiplier", "", "item", "Lnet/ccbluex/liquidbounce/api/minecraft/item/IItem;", "isForward", "isBlock", "onDisable", "", "onMotion", "onPacket", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onSlowDown", "Lnet/ccbluex/liquidbounce/event/SlowDownEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "sendPacket", "Event", "SendC07", "SendC08", "Delay", "DelayValue", "", "onGround", "Hypixel", "Relaxed"})
public final class NoSlow
extends Module {
    private final ListValue modeValue = new ListValue("PacketMode", new String[]{"None", "HuaYuTing", "Vanilla", "NoPacket", "AAC", "AAC5", "Matrix", "Vulcan", "Custom"}, "AntiCheat");
    private final FloatValue blockForwardMultiplier = new FloatValue("BlockForwardMultiplier", 1.0f, 0.2f, 1.0f);
    private final FloatValue blockStrafeMultiplier = new FloatValue("BlockStrafeMultiplier", 1.0f, 0.2f, 1.0f);
    private final FloatValue consumeForwardMultiplier = new FloatValue("ConsumeForwardMultiplier", 1.0f, 0.2f, 1.0f);
    private final FloatValue consumeStrafeMultiplier = new FloatValue("ConsumeStrafeMultiplier", 1.0f, 0.2f, 1.0f);
    private final FloatValue bowForwardMultiplier = new FloatValue("BowForwardMultiplier", 1.0f, 0.2f, 1.0f);
    private final FloatValue bowStrafeMultiplier = new FloatValue("BowStrafeMultiplier", 1.0f, 0.2f, 1.0f);
    private final BoolValue customOnGround = new BoolValue("CustomOnGround", false);
    private final IntegerValue customDelayValue = new IntegerValue("CustomDelay", 60, 10, 200);
    @NotNull
    private final BoolValue packetValue = new BoolValue("Renderer", true);
    @NotNull
    private final BoolValue soulsandValue = new BoolValue("Soulsand", true);
    @NotNull
    private final MSTimer timer = new MSTimer();
    private final MSTimer Timer = new MSTimer();
    private boolean pendingFlagApplyPacket;
    private final MSTimer msTimer = new MSTimer();
    private boolean sendBuf;
    private LinkedList<Packet<INetHandlerPlayServer>> packetBuf = new LinkedList();
    private boolean nextTemp;
    private boolean waitC03;
    private boolean lastBlockingStat;
    @NotNull
    private final KillAura killAura;

    @NotNull
    public final BoolValue getPacketValue() {
        return this.packetValue;
    }

    @NotNull
    public final BoolValue getSoulsandValue() {
        return this.soulsandValue;
    }

    @NotNull
    public final MSTimer getTimer() {
        return this.timer;
    }

    @NotNull
    public final KillAura getKillAura() {
        return this.killAura;
    }

    public final boolean isBlock() {
        Boolean bl = Debug.thePlayerisBlocking;
        Intrinsics.checkExpressionValueIsNotNull(bl, "thePlayerisBlocking");
        return bl != false || this.killAura.getBlockingStatus();
    }

    public final boolean fuckKotline(int value) {
        return value == 1;
    }

    private final boolean OnPre(MotionEvent event) {
        return event.getEventState() == EventState.PRE;
    }

    private final boolean OnPost(MotionEvent event) {
        return event.getEventState() == EventState.POST;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isBlocking() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (!iEntityPlayerSP.isUsingItem()) {
            Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
            if (module == null) {
                throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
            }
            if (!((KillAura)module).getBlockingStatus()) return false;
        }
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP2.getHeldItem() == null) return false;
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        IItemStack iItemStack = iEntityPlayerSP3.getHeldItem();
        if (iItemStack == null) {
            Intrinsics.throwNpe();
        }
        if (!(iItemStack.getItem() instanceof ItemSword)) return false;
        return true;
    }

    @Override
    public void onDisable() {
        this.Timer.reset();
        this.msTimer.reset();
        this.pendingFlagApplyPacket = false;
        this.sendBuf = false;
        this.packetBuf.clear();
        this.nextTemp = false;
        this.waitC03 = false;
    }

    private final void sendPacket(MotionEvent Event2, boolean SendC07, boolean SendC08, boolean Delay, long DelayValue, boolean onGround, boolean Hypixel) {
        Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
        }
        KillAura aura = (KillAura)module;
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (enumFacing == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.api.minecraft.util.IEnumFacing");
        }
        IPacket digging = MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.RELEASE_USE_ITEM, new WBlockPos(-1, -1, -1), (IEnumFacing)enumFacing);
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        ICPacketPlayerBlockPlacement blockPlace = MinecraftInstance.classProvider.createCPacketPlayerBlockPlacement((IItemStack)((Object)Integer.valueOf(iEntityPlayerSP.getInventory().getCurrentItem())));
        WBlockPos wBlockPos = new WBlockPos(-1, -1, -1);
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        ICPacketPlayerBlockPlacement blockMent = MinecraftInstance.classProvider.createCPacketPlayerBlockPlacement(wBlockPos, 255, (IItemStack)((Object)Integer.valueOf(iEntityPlayerSP2.getInventory().getCurrentItem())), 0.0f, 0.0f, 0.0f);
        if (onGround) {
            IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            if (!iEntityPlayerSP3.getOnGround()) {
                return;
            }
        }
        if (SendC07 && this.OnPre(Event2)) {
            if (Delay && this.Timer.hasTimePassed(DelayValue)) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(digging);
            } else if (!Delay) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(digging);
            }
        }
        if (SendC08 && this.OnPost(Event2)) {
            if (Delay && this.Timer.hasTimePassed(DelayValue) && !Hypixel) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(blockPlace);
                this.Timer.reset();
            } else if (!Delay && !Hypixel) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(blockPlace);
            } else if (Hypixel) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(blockMent);
            }
        }
    }

    static /* synthetic */ void sendPacket$default(NoSlow noSlow, MotionEvent motionEvent, boolean bl, boolean bl2, boolean bl3, long l, boolean bl4, boolean bl5, int n, Object object) {
        if ((n & 0x40) != 0) {
            bl5 = false;
        }
        noSlow.sendPacket(motionEvent, bl, bl2, bl3, l, bl4, bl5);
    }

    @EventTarget
    public final void onMotion(@NotNull MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        boolean test = this.fuckKotline(iEntityPlayerSP2.getTicksExisted() & 1);
        IItemStack heldItem = thePlayer.getHeldItem();
        if (!MovementUtils.isMoving()) {
            return;
        }
        String string = (String)this.modeValue.get();
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        switch (string3) {
            case "custom": {
                NoSlow.sendPacket$default(this, event, true, true, true, ((Number)this.customDelayValue.get()).intValue(), (Boolean)this.customOnGround.get(), false, 64, null);
                break;
            }
            case "vanilla": {
                IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP3 == null) {
                    Intrinsics.throwNpe();
                }
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP3.setMotionX(iEntityPlayerSP4.getMotionX());
                IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP5 == null) {
                    Intrinsics.throwNpe();
                }
                IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP6 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP5.setMotionY(iEntityPlayerSP6.getMotionY());
                IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP7 == null) {
                    Intrinsics.throwNpe();
                }
                IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP8 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP7.setMotionZ(iEntityPlayerSP8.getMotionZ());
                break;
            }
            case "hauyuting": {
                if (event.getEventState() != EventState.PRE) break;
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.RELEASE_USE_ITEM, WBlockPos.Companion.getORIGIN(), MinecraftInstance.classProvider.getEnumFacing(EnumFacingType.DOWN)));
                IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP9 == null) {
                    Intrinsics.throwNpe();
                }
                IItemStack iItemStack = iEntityPlayerSP9.getInventory().getCurrentItemInHand();
                if (iItemStack == null) {
                    throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.api.minecraft.item.IItemStack");
                }
                iINetHandlerPlayClient.addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerBlockPlacement(iItemStack));
                break;
            }
            case "aac": {
                IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP10 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP10.getTicksExisted() % 3 == 0) {
                    NoSlow.sendPacket$default(this, event, true, false, false, 0L, false, false, 64, null);
                    break;
                }
                NoSlow.sendPacket$default(this, event, false, true, false, 0L, false, false, 64, null);
                break;
            }
            case "aac5": {
                WEnumHand hand$iv;
                IEntityPlayerSP iEntityPlayerSP11 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP11 == null) {
                    Intrinsics.throwNpe();
                }
                if (!iEntityPlayerSP11.isUsingItem()) {
                    IEntityPlayerSP iEntityPlayerSP12 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP12 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!iEntityPlayerSP12.isBlocking() && !this.isBlock()) break;
                }
                IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                IEntityPlayerSP iEntityPlayerSP13 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP13 == null) {
                    Intrinsics.throwNpe();
                }
                IItemStack iItemStack = iEntityPlayerSP13.getInventory().getCurrentItemInHand();
                WEnumHand wEnumHand = WEnumHand.MAIN_HAND;
                IINetHandlerPlayClient iINetHandlerPlayClient2 = iINetHandlerPlayClient;
                boolean $i$f$createUseItemPacket = false;
                IPacket iPacket = WrapperImpl.INSTANCE.getClassProvider().createCPacketTryUseItem(hand$iv);
                iINetHandlerPlayClient2.addToSendQueue(iPacket);
                IINetHandlerPlayClient iINetHandlerPlayClient3 = MinecraftInstance.mc.getNetHandler();
                IEntityPlayerSP iEntityPlayerSP14 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP14 == null) {
                    Intrinsics.throwNpe();
                }
                IItemStack itemStack$iv = iEntityPlayerSP14.getInventory().getCurrentItemInHand();
                hand$iv = WEnumHand.OFF_HAND;
                iINetHandlerPlayClient2 = iINetHandlerPlayClient3;
                $i$f$createUseItemPacket = false;
                iPacket = WrapperImpl.INSTANCE.getClassProvider().createCPacketTryUseItem(hand$iv);
                iINetHandlerPlayClient2.addToSendQueue(iPacket);
                break;
            }
        }
    }

    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IPacket packet = event.getPacket();
        if (this.modeValue.equals("Matrix") || this.modeValue.equals("Vulcan") && this.nextTemp) {
            if ((packet instanceof CPacketPlayerDigging || packet instanceof ICPacketPlayerBlockPlacement) && this.isBlocking()) {
                event.cancelEvent();
            }
            event.cancelEvent();
        } else if (packet instanceof CPacketPlayer || packet instanceof CPacketAnimation || packet instanceof CPacketEntityAction || packet instanceof CPacketUseEntity || packet instanceof CPacketPlayerDigging || packet instanceof ICPacketPlayerBlockPlacement) {
            if (this.modeValue.equals("Vulcan") && this.waitC03 && packet instanceof ICPacketPlayer) {
                this.waitC03 = false;
                return;
            }
            this.packetBuf.add((Packet<INetHandlerPlayServer>)((Packet)packet));
        }
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if ((this.modeValue.equals("Matrix") || this.modeValue.equals("Vulcan")) && (this.lastBlockingStat || this.isBlocking())) {
            if (this.msTimer.hasTimePassed(230L) && this.nextTemp) {
                this.nextTemp = false;
                EnumFacing enumFacing = EnumFacing.DOWN;
                if (enumFacing == null) {
                    throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.api.minecraft.util.IEnumFacing");
                }
                MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.RELEASE_USE_ITEM, new WBlockPos(-1, -1, -1), (IEnumFacing)enumFacing);
                Collection collection = this.packetBuf;
                boolean bl = false;
                if (!collection.isEmpty()) {
                    boolean canAttack = false;
                    for (Packet packet : this.packetBuf) {
                        if (packet instanceof CPacketPlayer) {
                            canAttack = true;
                        }
                        if ((packet instanceof ICPacketUseEntity || packet instanceof ICPacketAnimation) && !canAttack) continue;
                        Packet packet2 = packet;
                        Intrinsics.checkExpressionValueIsNotNull(packet2, "packet");
                        PacketUtils.sendPacketNoEvent((Packet<INetHandlerPlayServer>)packet2);
                    }
                    this.packetBuf.clear();
                }
            }
            if (!this.nextTemp) {
                this.lastBlockingStat = this.isBlocking();
                if (!this.isBlocking()) {
                    return;
                }
                WBlockPos wBlockPos = new WBlockPos(-1, -1, -1);
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                MinecraftInstance.classProvider.createCPacketPlayerBlockPlacement(wBlockPos, 255, (IItemStack)((Object)Integer.valueOf(iEntityPlayerSP.getInventory().getCurrentItem())), 0.0f, 0.0f, 0.0f);
                this.nextTemp = true;
                this.waitC03 = this.modeValue.equals("Vulcan");
                this.msTimer.reset();
            }
        }
    }

    @EventTarget
    public final void onSlowDown(@NotNull SlowDownEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IItemStack iItemStack = iEntityPlayerSP.getHeldItem();
        IItem heldItem = iItemStack != null ? iItemStack.getItem() : null;
        event.setForward(this.getMultiplier(heldItem, true));
        event.setStrafe(this.getMultiplier(heldItem, false));
    }

    private final float getMultiplier(IItem item, boolean isForward) {
        return MinecraftInstance.classProvider.isItemFood(item) || MinecraftInstance.classProvider.isItemPotion(item) || MinecraftInstance.classProvider.isItemBucketMilk(item) ? (isForward ? ((Number)this.consumeForwardMultiplier.get()).floatValue() : ((Number)this.consumeStrafeMultiplier.get()).floatValue()) : (MinecraftInstance.classProvider.isItemSword(item) ? (isForward ? ((Number)this.blockForwardMultiplier.get()).floatValue() : ((Number)this.blockStrafeMultiplier.get()).floatValue()) : (MinecraftInstance.classProvider.isItemBow(item) ? (isForward ? ((Number)this.bowForwardMultiplier.get()).floatValue() : ((Number)this.bowStrafeMultiplier.get()).floatValue()) : 0.2f));
    }

    @Override
    @Nullable
    public String getTag() {
        return (String)this.modeValue.get();
    }

    public NoSlow() {
        Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
        }
        this.killAura = (KillAura)module;
    }
}

