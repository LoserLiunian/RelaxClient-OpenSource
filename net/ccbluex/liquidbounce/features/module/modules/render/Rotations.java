/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayer;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.combat.BowAimbot;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.world.ChestAura;
import net.ccbluex.liquidbounce.features.module.modules.world.CivBreak;
import net.ccbluex.liquidbounce.features.module.modules.world.Fucker;
import net.ccbluex.liquidbounce.features.module.modules.world.Nuker;
import net.ccbluex.liquidbounce.features.module.modules.world.Scaffold;
import net.ccbluex.liquidbounce.features.module.modules.world.Tower;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.RotationUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Rotations", description="Allows you to see server-sided head rotations.", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0015H\u0007J\b\u0010\u0016\u001a\u00020\rH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000b\u00a8\u0006\u0017"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/Rotations;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "bodyValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "getBodyValue", "()Lnet/ccbluex/liquidbounce/value/BoolValue;", "headValue", "getHeadValue", "playerYaw", "", "Ljava/lang/Float;", "getState", "", "module", "Ljava/lang/Class;", "onPacket", "", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onRender3D", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "shouldRotate", "Relaxed"})
public final class Rotations
extends Module {
    @NotNull
    private final BoolValue headValue = new BoolValue("Head", true);
    @NotNull
    private final BoolValue bodyValue = new BoolValue("Body", true);
    private Float playerYaw;

    @NotNull
    public final BoolValue getHeadValue() {
        return this.headValue;
    }

    @NotNull
    public final BoolValue getBodyValue() {
        return this.bodyValue;
    }

    @EventTarget
    public final void onRender3D(@NotNull Render3DEvent event) {
        block1: {
            Intrinsics.checkParameterIsNotNull(event, "event");
            if (RotationUtils.serverRotation == null || ((Boolean)this.bodyValue.get()).booleanValue() || !((Boolean)this.headValue.get()).booleanValue()) break block1;
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP != null) {
                iEntityPlayerSP.setRotationYawHead(RotationUtils.serverRotation.getYaw());
            }
        }
    }

    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (!((Boolean)this.bodyValue.get()).booleanValue() || !this.shouldRotate() || MinecraftInstance.mc.getThePlayer() == null) {
            return;
        }
        IPacket packet = event.getPacket();
        if (MinecraftInstance.classProvider.isCPacketPlayerPosLook(packet) || MinecraftInstance.classProvider.isCPacketPlayerLook(packet)) {
            ICPacketPlayer packetPlayer = packet.asCPacketPlayer();
            this.playerYaw = Float.valueOf(packetPlayer.getYaw());
            if (((Boolean)this.bodyValue.get()).booleanValue()) {
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP.setRenderYawOffset(packetPlayer.getYaw());
            }
            if (((Boolean)this.headValue.get()).booleanValue()) {
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP.setRotationYawHead(packetPlayer.getYaw());
            }
        } else {
            if (this.playerYaw != null && ((Boolean)this.bodyValue.get()).booleanValue()) {
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                Float f = this.playerYaw;
                if (f == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP.setRenderYawOffset(f.floatValue());
            }
            if (((Boolean)this.headValue.get()).booleanValue()) {
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP.setRotationYawHead(iEntityPlayerSP2.getRenderYawOffset());
            }
        }
    }

    private final boolean getState(Class<?> module) {
        return LiquidBounce.INSTANCE.getModuleManager().get(module).getState();
    }

    private final boolean shouldRotate() {
        Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(KillAura.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
        }
        KillAura killAura = (KillAura)module;
        return this.getState(Scaffold.class) || this.getState(Tower.class) || this.getState(KillAura.class) && killAura.getTarget() != null || this.getState(BowAimbot.class) || this.getState(Fucker.class) || this.getState(CivBreak.class) || this.getState(Nuker.class) || this.getState(ChestAura.class);
    }
}

