/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016\u00a8\u0006\u0006"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/speeds/other/BlocksMCBhop;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/speeds/SpeedMode;", "()V", "onDisable", "", "onMotion", "Relaxed"})
public final class BlocksMCBhop
extends SpeedMode {
    @Override
    public void onMotion() {
        if (MovementUtils.isMoving()) {
            Speed speed = (Speed)LiquidBounce.INSTANCE.getModuleManager().getModule(Speed.class);
            if (speed == null) {
                return;
            }
            Speed speed2 = speed;
            SpeedMode.mc.getTimer().setTimerSpeed(1.0f);
            IEntityPlayerSP iEntityPlayerSP = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP.getOnGround()) {
                MovementUtils.strafe(0.65f);
                IEntityPlayerSP iEntityPlayerSP2 = SpeedMode.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP2.setMotionY(0.2);
            } else if (((Boolean)speed2.customStrafeValue.get()).booleanValue()) {
                MovementUtils.strafe(0.65f);
            } else {
                MovementUtils.strafe$default(0.0f, 1, null);
            }
        } else {
            IEntityPlayerSP iEntityPlayerSP = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            iEntityPlayerSP.setMotionZ(0.0);
            IEntityPlayerSP iEntityPlayerSP3 = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            IEntityPlayerSP iEntityPlayerSP4 = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP4 == null) {
                Intrinsics.throwNpe();
            }
            iEntityPlayerSP3.setMotionX(iEntityPlayerSP4.getMotionZ());
        }
    }

    @Override
    public void onDisable() {
        MovementUtils.strafe$default(0.0f, 1, null);
        SpeedMode.mc.getTimer().setTimerSpeed(1.0f);
    }

    public BlocksMCBhop() {
        super("BlocksMcBHop");
    }
}

