/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.movement;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.event.StrafeEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Strafe", description="Allows you to freely move in mid air.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0016H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/Strafe;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "allDirectionsJumpValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "jump", "", "noMoveStopValue", "onGroundStrafeValue", "strengthValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "wasDown", "getMoveYaw", "", "onEnable", "", "onJump", "event", "Lnet/ccbluex/liquidbounce/event/JumpEvent;", "onStrafe", "Lnet/ccbluex/liquidbounce/event/StrafeEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class Strafe
extends Module {
    private FloatValue strengthValue = new FloatValue("Strength", 0.5f, 0.0f, 1.0f);
    private BoolValue noMoveStopValue = new BoolValue("NoMoveStop", false);
    private BoolValue onGroundStrafeValue = new BoolValue("OnGroundStrafe", false);
    private BoolValue allDirectionsJumpValue = new BoolValue("AllDirectionsJump", false);
    private boolean wasDown;
    private boolean jump;

    @EventTarget
    public final void onJump(@NotNull JumpEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (this.jump) {
            event.cancelEvent();
        }
    }

    @Override
    public void onEnable() {
        this.wasDown = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        block19: {
            block20: {
                Intrinsics.checkParameterIsNotNull(event, "event");
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                if (!iEntityPlayerSP.getOnGround() || !MinecraftInstance.mc.getGameSettings().getKeyBindJump().isKeyDown() || !((Boolean)this.allDirectionsJumpValue.get()).booleanValue()) break block19;
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP2.getMovementInput().getMoveForward() != 0.0f) break block20;
                IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP3 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP3.getMovementInput().getMoveStrafe() == 0.0f) break block19;
            }
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (!iEntityPlayerSP.isInWater()) {
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                if (!iEntityPlayerSP4.isInLava()) {
                    IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP5 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!iEntityPlayerSP5.isOnLadder()) {
                        IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
                        if (iEntityPlayerSP6 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!iEntityPlayerSP6.isInWeb()) {
                            if (MinecraftInstance.mc.getGameSettings().getKeyBindJump().isKeyDown()) {
                                MinecraftInstance.mc.getGameSettings().getKeyBindJump().setPressed(false);
                                this.wasDown = true;
                            }
                            IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                            if (iEntityPlayerSP7 == null) {
                                Intrinsics.throwNpe();
                            }
                            float yaw = iEntityPlayerSP7.getRotationYaw();
                            IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
                            if (iEntityPlayerSP8 == null) {
                                Intrinsics.throwNpe();
                            }
                            iEntityPlayerSP8.setRotationYaw(this.getMoveYaw());
                            IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
                            if (iEntityPlayerSP9 == null) {
                                Intrinsics.throwNpe();
                            }
                            iEntityPlayerSP9.jump();
                            IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
                            if (iEntityPlayerSP10 == null) {
                                Intrinsics.throwNpe();
                            }
                            iEntityPlayerSP10.setRotationYaw(yaw);
                            this.jump = true;
                            if (!this.wasDown) return;
                            MinecraftInstance.mc.getGameSettings().getKeyBindJump().setPressed(true);
                            this.wasDown = false;
                            return;
                        }
                    }
                }
            }
        }
        this.jump = false;
    }

    @EventTarget
    public final void onStrafe(@NotNull StrafeEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        double d = iEntityPlayerSP.getMotionX();
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        double d2 = d * iEntityPlayerSP2.getMotionX();
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        double d3 = iEntityPlayerSP3.getMotionZ();
        IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP4 == null) {
            Intrinsics.throwNpe();
        }
        double d4 = d2 + d3 * iEntityPlayerSP4.getMotionZ();
        boolean bl = false;
        double shotSpeed = Math.sqrt(d4);
        double speed = shotSpeed * ((Number)this.strengthValue.get()).doubleValue();
        IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP5 == null) {
            Intrinsics.throwNpe();
        }
        double motionX = iEntityPlayerSP5.getMotionX() * (double)(1.0f - ((Number)this.strengthValue.get()).floatValue());
        IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP6 == null) {
            Intrinsics.throwNpe();
        }
        double motionZ = iEntityPlayerSP6.getMotionZ() * (double)(1.0f - ((Number)this.strengthValue.get()).floatValue());
        IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP7 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP7.getMovementInput().getMoveForward() == 0.0f) {
            IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP8 == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP8.getMovementInput().getMoveStrafe() == 0.0f) {
                if (((Boolean)this.noMoveStopValue.get()).booleanValue()) {
                    IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP9 == null) {
                        Intrinsics.throwNpe();
                    }
                    iEntityPlayerSP9.setMotionX(0.0);
                    IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP10 == null) {
                        Intrinsics.throwNpe();
                    }
                    iEntityPlayerSP10.setMotionZ(0.0);
                }
                return;
            }
        }
        IEntityPlayerSP iEntityPlayerSP11 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP11 == null) {
            Intrinsics.throwNpe();
        }
        if (!iEntityPlayerSP11.getOnGround() || ((Boolean)this.onGroundStrafeValue.get()).booleanValue()) {
            float yaw = this.getMoveYaw();
            IEntityPlayerSP iEntityPlayerSP12 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP12 == null) {
                Intrinsics.throwNpe();
            }
            double d5 = Math.toRadians(yaw);
            IEntityPlayerSP iEntityPlayerSP13 = iEntityPlayerSP12;
            boolean bl2 = false;
            double d6 = Math.sin(d5);
            iEntityPlayerSP13.setMotionX(-d6 * speed + motionX);
            IEntityPlayerSP iEntityPlayerSP14 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP14 == null) {
                Intrinsics.throwNpe();
            }
            d5 = Math.toRadians(yaw);
            iEntityPlayerSP13 = iEntityPlayerSP14;
            bl2 = false;
            d6 = Math.cos(d5);
            iEntityPlayerSP13.setMotionZ(d6 * speed + motionZ);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final float getMoveYaw() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        float moveYaw = iEntityPlayerSP.getRotationYaw();
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP2.getMoveForward() != 0.0f) {
            IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP3.getMoveStrafing() == 0.0f) {
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                return moveYaw += (float)(iEntityPlayerSP4.getMoveForward() > 0.0f ? 0 : 180);
            }
        }
        IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP5 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP5.getMoveForward() != 0.0f) {
            IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP6 == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP6.getMoveStrafing() != 0.0f) {
                IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP7 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP7.getMoveForward() > 0.0f) {
                    IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP8 == null) {
                        Intrinsics.throwNpe();
                    }
                    moveYaw += (float)(iEntityPlayerSP8.getMoveStrafing() > 0.0f ? -45 : 45);
                } else {
                    IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP9 == null) {
                        Intrinsics.throwNpe();
                    }
                    moveYaw -= (float)(iEntityPlayerSP9.getMoveStrafing() > 0.0f ? -45 : 45);
                }
                IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP10 == null) {
                    Intrinsics.throwNpe();
                }
                return moveYaw += (float)(iEntityPlayerSP10.getMoveForward() > 0.0f ? 0 : 180);
            }
        }
        IEntityPlayerSP iEntityPlayerSP11 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP11 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP11.getMoveStrafing() == 0.0f) return moveYaw;
        IEntityPlayerSP iEntityPlayerSP12 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP12 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP12.getMoveForward() != 0.0f) return moveYaw;
        IEntityPlayerSP iEntityPlayerSP13 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP13 == null) {
            Intrinsics.throwNpe();
        }
        moveYaw += (float)(iEntityPlayerSP13.getMoveStrafing() > 0.0f ? -90 : 90);
        return moveYaw;
    }
}

