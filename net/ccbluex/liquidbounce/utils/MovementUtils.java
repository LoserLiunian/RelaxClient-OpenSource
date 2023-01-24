/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Keyboard
 */
package net.ccbluex.liquidbounce.utils;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.movement.TargetStrafe;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.RotationUtils;
import net.minecraft.entity.EntityLivingBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004H\u0007J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001cJ\u0018\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0007J\b\u0010 \u001a\u00020\u0004H\u0007J\u0006\u0010!\u001a\u00020\rJ\u000e\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0004J\u0016\u0010\"\u001a\u00020\r2\u0006\u0010$\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0004J&\u0010&\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0014J0\u0010&\u001a\u00020\u00182\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0007J\u001a\u0010&\u001a\u00020\u00182\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\u0004H\u0007J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0012\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007J\u0006\u0010/\u001a\u00020\u0018R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u00048FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0006R\u001a\u0010\f\u001a\u00020\r8FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\f\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u00148F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u00a8\u00060"}, d2={"Lnet/ccbluex/liquidbounce/utils/MovementUtils;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "()V", "bps", "", "getBps", "()D", "setBps", "(D)V", "direction", "direction$annotations", "getDirection", "isMoving", "", "isMoving$annotations", "()Z", "lastX", "lastY", "lastZ", "speed", "", "getSpeed", "()F", "forward", "", "length", "getBlockSpeed", "entityIn", "Lnet/minecraft/entity/EntityLivingBase;", "getScaffoldRotation", "yaw", "strafe", "getSpeed1", "hasMotion", "isOnGround", "height", "entity", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "setSpeed", "strafing", "moveEvent", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "moveSpeed", "pseudoYaw", "pseudoStrafe", "pseudoForward", "setSpeed1", "updateBlocksPerSecond", "Relaxed"})
public final class MovementUtils
extends MinecraftInstance {
    private static double bps;
    private static double lastX;
    private static double lastY;
    private static double lastZ;
    public static final MovementUtils INSTANCE;

    public final double getBps() {
        return bps;
    }

    public final void setBps(double d) {
        bps = d;
    }

    public final float getSpeed() {
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
        return (float)Math.sqrt(d4);
    }

    @JvmStatic
    public static /* synthetic */ void isMoving$annotations() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean isMoving() {
        if (MinecraftInstance.mc.getThePlayer() == null) return false;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP.getMovementInput().getMoveForward() != 0.0f) return true;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP2.getMovementInput().getMoveStrafe() == 0.0f) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean hasMotion() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP.getMotionX() == 0.0) return false;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP2.getMotionZ() == 0.0) return false;
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP3.getMotionY() == 0.0) return false;
        return true;
    }

    @JvmStatic
    public static final void setSpeed(@Nullable MoveEvent moveEvent, double moveSpeed) {
        MoveEvent moveEvent2 = moveEvent;
        if (moveEvent2 == null) {
            Intrinsics.throwNpe();
        }
        MovementUtils.setSpeed(moveEvent2, moveSpeed, MinecraftInstance.mc2.field_71439_g.field_70177_z, MinecraftInstance.mc2.field_71439_g.field_71158_b.field_78902_a, MinecraftInstance.mc2.field_71439_g.field_71158_b.field_192832_b);
    }

    @JvmStatic
    public static final double getSpeed1() {
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
        return (float)Math.sqrt(d2 + d3 * iEntityPlayerSP4.getMotionZ());
    }

    @JvmStatic
    public static final void setSpeed(@NotNull MoveEvent moveEvent, double moveSpeed, float pseudoYaw, double pseudoStrafe, double pseudoForward) {
        Intrinsics.checkParameterIsNotNull(moveEvent, "moveEvent");
        double forward = pseudoForward;
        double strafe = pseudoStrafe;
        float yaw = pseudoYaw;
        if (forward == 0.0 && strafe == 0.0) {
            moveEvent.setZ(0.0);
            moveEvent.setX(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (float)(forward > 0.0 ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += (float)(forward > 0.0 ? 45 : -45);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            double cos = Math.cos(Math.toRadians(yaw + 90.0f));
            double sin = Math.sin(Math.toRadians(yaw + 90.0f));
            moveEvent.setX(forward * moveSpeed * cos + strafe * moveSpeed * sin);
            moveEvent.setZ(forward * moveSpeed * sin - strafe * moveSpeed * cos);
        }
    }

    @JvmStatic
    @JvmOverloads
    public static final void strafe(float speed) {
        IEntityPlayerSP thePlayer;
        if (!MovementUtils.isMoving()) {
            return;
        }
        double yaw = MovementUtils.getDirection();
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP iEntityPlayerSP2 = thePlayer = iEntityPlayerSP;
        boolean bl = false;
        double d = Math.sin(yaw);
        iEntityPlayerSP2.setMotionX(-d * (double)speed);
        iEntityPlayerSP2 = thePlayer;
        bl = false;
        d = Math.cos(yaw);
        iEntityPlayerSP2.setMotionZ(d * (double)speed);
    }

    public static /* synthetic */ void strafe$default(float f, int n, Object object) {
        if ((n & 1) != 0) {
            f = INSTANCE.getSpeed();
        }
        MovementUtils.strafe(f);
    }

    @JvmStatic
    @JvmOverloads
    public static final void strafe() {
        MovementUtils.strafe$default(0.0f, 1, null);
    }

    @JvmStatic
    public static final void setSpeed1(double speed) {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP player = iEntityPlayerSP;
        if (MovementUtils.isMoving()) {
            Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(TargetStrafe.class);
            if (module == null) {
                throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.movement.TargetStrafe");
            }
            TargetStrafe targetStrafe = (TargetStrafe)module;
            Module module2 = LiquidBounce.INSTANCE.getModuleManager().getModule(KillAura.class);
            if (module2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
            }
            KillAura ka = (KillAura)module2;
            if (targetStrafe.getState() && (!((Boolean)targetStrafe.getHoldSpaceValue().get()).booleanValue() || Keyboard.isKeyDown((int)57))) {
                IEntityLivingBase target = ka.getTarget();
                if (ka.getState() && target != null) {
                    float radius;
                    float dist;
                    IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP2 == null) {
                        Intrinsics.throwNpe();
                    }
                    INSTANCE.setSpeed(speed, (double)(dist = iEntityPlayerSP2.getDistanceToEntity(target)) <= (double)(radius = ((Number)targetStrafe.getRadiusValue().get()).floatValue()) + 1.0E-4 ? 0.0f : 1.0f, (double)dist <= (double)radius + 1.0 ? (float)targetStrafe.getDirection() : 0.0f, RotationUtils.getYawToEntity(target));
                    return;
                }
            }
            INSTANCE.setSpeed(speed, player.getMoveForward(), player.getMoveStrafing(), player.getRotationYaw());
        }
    }

    public final void setSpeed(double speed, float forward, float strafing, float yaw) {
        boolean reversed;
        float yaw2 = yaw;
        boolean bl = reversed = forward < 0.0f;
        float strafingYaw = 90.0f * (forward > 0.0f ? 0.5f : (reversed ? -0.5f : 1.0f));
        if (reversed) {
            yaw2 += 180.0f;
        }
        if (strafing > 0.0f) {
            yaw2 -= strafingYaw;
        } else if (strafing < 0.0f) {
            yaw2 += strafingYaw;
        }
        double x = Math.cos(Math.toRadians(yaw2 + 90.0f));
        double z = Math.cos(Math.toRadians(yaw2));
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        iEntityPlayerSP.setMotionX(x * speed);
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        iEntityPlayerSP2.setMotionZ(z * speed);
    }

    @JvmStatic
    public static final float getScaffoldRotation(float yaw, float strafe) {
        float rotationYaw = yaw;
        rotationYaw += 180.0f;
        float forward = -0.5f;
        if (strafe < 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (strafe > 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return rotationYaw;
    }

    @JvmStatic
    public static final void forward(double length) {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        double yaw = Math.toRadians(thePlayer.getRotationYaw());
        double d = thePlayer.getPosX();
        IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
        boolean bl = false;
        double d2 = Math.sin(yaw);
        double d3 = d + -d2 * length;
        double d4 = thePlayer.getPosZ();
        d2 = thePlayer.getPosY();
        d = d3;
        bl = false;
        double d5 = Math.cos(yaw);
        iEntityPlayerSP2.setPosition(d, d2, d4 + d5 * length);
    }

    public final void updateBlocksPerSecond() {
        block9: {
            block8: {
                if (MinecraftInstance.mc.getThePlayer() == null) break block8;
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP.getTicksExisted() >= 1) break block9;
            }
            bps = 0.0;
        }
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        double distance = iEntityPlayerSP.getDistance(lastX, lastY, lastZ);
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        lastX = iEntityPlayerSP2.getPosX();
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        lastY = iEntityPlayerSP3.getPosY();
        IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP4 == null) {
            Intrinsics.throwNpe();
        }
        lastZ = iEntityPlayerSP4.getPosZ();
        bps = distance * (double)((float)20 * MinecraftInstance.mc.getTimer().getTimerSpeed());
    }

    public final double getBlockSpeed(@NotNull EntityLivingBase entityIn) {
        Intrinsics.checkParameterIsNotNull(entityIn, "entityIn");
        return BigDecimal.valueOf(Math.sqrt(Math.pow(entityIn.field_70165_t - entityIn.field_70169_q, 2.0) + Math.pow(entityIn.field_70161_v - entityIn.field_70166_s, 2.0)) * (double)20).setScale(1, 4).doubleValue();
    }

    public final boolean isOnGround(double height) {
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntity iEntity = iEntityPlayerSP;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        return !iWorldClient.getCollidingBoundingBoxes(iEntity, iEntityPlayerSP2.getEntityBoundingBox().offset(0.0, -height, 0.0)).isEmpty();
    }

    public final boolean isOnGround(@NotNull IEntity entity, double height) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        return !iWorldClient.getCollidingBoundingBoxes(entity, entity.getEntityBoundingBox().offset(0.0, -height, 0.0)).isEmpty();
    }

    @JvmStatic
    public static /* synthetic */ void direction$annotations() {
    }

    public static final double getDirection() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        float rotationYaw = thePlayer.getRotationYaw();
        if (thePlayer.getMoveForward() < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (thePlayer.getMoveForward() < 0.0f) {
            forward = -0.5f;
        } else if (thePlayer.getMoveForward() > 0.0f) {
            forward = 0.5f;
        }
        if (thePlayer.getMoveStrafing() > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (thePlayer.getMoveStrafing() < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    private MovementUtils() {
    }

    static {
        MovementUtils movementUtils;
        INSTANCE = movementUtils = new MovementUtils();
    }
}

