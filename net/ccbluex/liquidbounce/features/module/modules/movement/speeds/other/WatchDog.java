/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;

import java.util.Random;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\rJ\b\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0018H\u0016J\u0016\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/speeds/other/WatchDog;", "Lnet/ccbluex/liquidbounce/features/module/modules/movement/speeds/SpeedMode;", "()V", "lastDist", "", "getLastDist", "()D", "setLastDist", "(D)V", "speed", "getSpeed", "setSpeed", "stage", "", "getStage", "()I", "setStage", "(I)V", "stage1", "", "timer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "getBaseMoveSpeed", "getHypixelBest", "", "getJumpEffect", "onDisable", "onEnable", "onMotion", "onMove", "event", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "onUpdate", "setMotion", "e", "Relaxed"})
public final class WatchDog
extends SpeedMode {
    private double lastDist;
    private double speed;
    private int stage = 1;
    private boolean stage1;
    private final MSTimer timer = new MSTimer();

    public final double getLastDist() {
        return this.lastDist;
    }

    public final void setLastDist(double d) {
        this.lastDist = d;
    }

    public final double getSpeed() {
        return this.speed;
    }

    public final void setSpeed(double d) {
        this.speed = d;
    }

    public final int getStage() {
        return this.stage;
    }

    public final void setStage(int n) {
        this.stage = n;
    }

    public final double getBaseMoveSpeed() {
        double baseSpeed = 0.3;
        EntityPlayerSP entityPlayerSP = SpeedMode.mc2.field_71439_g;
        if (entityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (entityPlayerSP.func_70644_a(MobEffects.field_76424_c)) {
            MovementUtils.strafe(0.48f);
        }
        return baseSpeed;
    }

    public final int getJumpEffect() {
        int n;
        if (SpeedMode.mc2.field_71439_g.func_70644_a(MobEffects.field_76430_j)) {
            EntityPlayerSP entityPlayerSP = SpeedMode.mc2.field_71439_g;
            if (entityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            PotionEffect potionEffect = entityPlayerSP.func_70660_b(MobEffects.field_76430_j);
            if (potionEffect == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(potionEffect, "mc2.player!!.getActivePo\u2026(MobEffects.JUMP_BOOST)!!");
            n = potionEffect.func_76458_c() + 1;
        } else {
            n = 0;
        }
        return n;
    }

    public final void setMotion(@NotNull MoveEvent e, double speed) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        IEntityPlayerSP iEntityPlayerSP = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        double forward = iEntityPlayerSP.getMovementInput().getMoveForward();
        IEntityPlayerSP iEntityPlayerSP2 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        double strafe = iEntityPlayerSP2.getMovementInput().getMoveStrafe();
        IEntityPlayerSP iEntityPlayerSP3 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        float rotationYaw = iEntityPlayerSP3.getRotationYaw();
        IEntityPlayerSP iEntityPlayerSP4 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP4 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP4.getMoveForward() < 0.0f) {
            rotationYaw += 180.0f;
        }
        IEntityPlayerSP iEntityPlayerSP5 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP5 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP5.getMoveStrafing() > 0.0f) {
            rotationYaw -= (float)((double)90.0f * forward);
        }
        IEntityPlayerSP iEntityPlayerSP6 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP6 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP6.getMoveStrafing() < 0.0f) {
            rotationYaw += (float)((double)90.0f * forward);
        }
        IEntityPlayerSP iEntityPlayerSP7 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP7 == null) {
            Intrinsics.throwNpe();
        }
        double yaw = iEntityPlayerSP7.getRotationYaw();
        if (forward == 0.0 && strafe == 0.0) {
            IEntityPlayerSP iEntityPlayerSP8 = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP8 == null) {
                Intrinsics.throwNpe();
            }
            iEntityPlayerSP8.setMotionX(0.0);
            IEntityPlayerSP iEntityPlayerSP9 = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP9 == null) {
                Intrinsics.throwNpe();
            }
            iEntityPlayerSP9.setMotionZ(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (double)(forward > 0.0 ? -44 : 44);
                } else if (strafe < 0.0) {
                    yaw += (double)(forward > 0.0 ? 44 : -44);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            e.setX(forward * speed * Math.cos(Math.toRadians(yaw + (double)90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + (double)90.0f)));
            e.setZ(forward * speed * Math.sin(Math.toRadians(yaw + (double)90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + (double)90.0f)));
        }
    }

    @Override
    public void onEnable() {
        this.stage = 2;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.speed = this.getBaseMoveSpeed();
        this.stage = 2;
    }

    @Override
    public void onMotion() {
        IEntityPlayerSP iEntityPlayerSP = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        double xDist = thePlayer.getPosX() - thePlayer.getPrevPosX();
        double zDist = thePlayer.getPosZ() - thePlayer.getPrevPosZ();
        this.lastDist = Math.sqrt(xDist * xDist + zDist * zDist);
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onMove(@NotNull MoveEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(Speed.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.movement.Speed");
        }
        Speed speed1 = (Speed)module;
        if (this.stage1) {
            IEntityPlayerSP iEntityPlayerSP2 = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            if ((double)iEntityPlayerSP2.getFallDistance() <= 0.1) {
                SpeedMode.mc.getTimer().setTimerSpeed(((Number)speed1.HypixelTimerValue.get()).floatValue());
            }
            if (this.timer.hasTimePassed(600L)) {
                this.timer.reset();
                this.stage1 = !this.stage1;
            }
        } else {
            IEntityPlayerSP iEntityPlayerSP3 = SpeedMode.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            if ((double)iEntityPlayerSP3.getFallDistance() > 0.1) {
                IEntityPlayerSP iEntityPlayerSP4 = SpeedMode.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                if ((double)iEntityPlayerSP4.getFallDistance() < 1.3) {
                    SpeedMode.mc.getTimer().setTimerSpeed(((Number)speed1.HypixelDealyTimerValue.get()).floatValue());
                }
            }
            if (this.timer.hasTimePassed(400L)) {
                this.timer.reset();
                this.stage1 = !this.stage1;
            }
        }
        IEntityPlayerSP iEntityPlayerSP5 = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP5 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP5.getFallDistance() >= 1.3f) {
            SpeedMode.mc.getTimer().setTimerSpeed(1.0f);
        }
        if (this.stage > 0 && !thePlayer.isInWater()) {
            boolean stoptick = false;
            if (this.stage == 1 && thePlayer.getOnGround() && MovementUtils.isMoving()) {
                ++this.stage;
            }
            if (this.stage == 2 && thePlayer.getOnGround() && MovementUtils.isMoving()) {
                double d = 0.399999986886975 + (double)this.getJumpEffect() * 0.1;
                MoveEvent moveEvent = event;
                boolean bl = false;
                boolean bl2 = false;
                double it = d;
                boolean bl3 = false;
                IEntityPlayerSP iEntityPlayerSP6 = SpeedMode.mc.getThePlayer();
                if (iEntityPlayerSP6 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP6.setMotionY(it);
                double d2 = d;
                moveEvent.setY(d2);
            } else if (this.stage >= 3) {
                IEntityPlayerSP iEntityPlayerSP7 = SpeedMode.mc.getThePlayer();
                if (iEntityPlayerSP7 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP7.isCollidedVertically()) {
                    this.speed = this.getBaseMoveSpeed();
                    this.lastDist = 0.0;
                    this.stage = 0;
                }
            }
            this.getHypixelBest();
        } else {
            this.stage = 0;
        }
        if (MovementUtils.isMoving()) {
            this.setMotion(event, this.speed);
        } else {
            this.setMotion(event, 0.0);
            this.stage = 0;
        }
        WatchDog watchDog = this;
        ++watchDog.stage;
        int cfr_ignored_0 = watchDog.stage;
    }

    private final void getHypixelBest() {
        IEntityPlayerSP iEntityPlayerSP = SpeedMode.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        boolean slowdown = false;
        switch (this.stage) {
            case 1: {
                this.stage = 2;
                break;
            }
            case 2: {
                if (!thePlayer.getOnGround() || !MovementUtils.isMoving()) break;
                this.speed *= 1.7;
                break;
            }
            case 3: {
                this.speed += new Random().nextDouble() / (double)4799;
                double difference = 0.66 * (this.lastDist - this.getBaseMoveSpeed());
                this.speed = this.lastDist - difference;
                this.speed -= new Random().nextDouble() / (double)4799;
                break;
            }
            default: {
                slowdown = (double)thePlayer.getFallDistance() > 0.0;
                IWorldClient iWorldClient = SpeedMode.mc.getTheWorld();
                if (iWorldClient == null) {
                    Intrinsics.throwNpe();
                }
                if (!iWorldClient.getCollidingBoundingBoxes(thePlayer, thePlayer.getEntityBoundingBox().offset(0.0, thePlayer.getMotionY(), 0.0)).isEmpty() || thePlayer.isCollidedVertically() && thePlayer.getOnGround()) {
                    this.stage = 1;
                }
                this.speed = this.lastDist - this.lastDist / (double)159;
            }
        }
        this.speed = Math.max(this.speed - (slowdown ? Math.sqrt(this.lastDist * this.lastDist + this.speed * this.speed) * 0.012 : 0.02 * this.lastDist), this.getBaseMoveSpeed());
        if (slowdown) {
            this.speed *= 1.0 - this.lastDist / (double)50;
        }
    }

    public WatchDog() {
        super("WatchDog");
    }
}

