/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.movement;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.enums.StatType;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayer;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.StepConfirmEvent;
import net.ccbluex.liquidbounce.event.StepEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.exploit.Phase;
import net.ccbluex.liquidbounce.features.module.modules.movement.Fly;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Step", description="Allows you to step up blocks.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\bH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\"H\u0007J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020$H\u0007J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020&H\u0007J\u0010\u0010'\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020(H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/Step;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "delayValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "heightValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "isAACStep", "", "isStep", "jumpHeightValue", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "ncpNextStep", "", "spartanSwitch", "stepX", "", "stepY", "stepZ", "tag", "", "getTag", "()Ljava/lang/String;", "timer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "couldStep", "fakeJump", "", "onDisable", "onMove", "event", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "onPacket", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onStep", "Lnet/ccbluex/liquidbounce/event/StepEvent;", "onStepConfirm", "Lnet/ccbluex/liquidbounce/event/StepConfirmEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class Step
extends Module {
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Vanilla", "Jump", "NCP", "MotionNCP", "OldNCP", "AAC", "LAAC", "AAC3.3.4", "Spartan", "Rewinside"}, "NCP");
    private final FloatValue heightValue = new FloatValue("Height", 1.0f, 0.6f, 10.0f);
    private final FloatValue jumpHeightValue = new FloatValue("JumpHeight", 0.42f, 0.37f, 0.42f);
    private final IntegerValue delayValue = new IntegerValue("Delay", 0, 0, 500);
    private boolean isStep;
    private double stepX;
    private double stepY;
    private double stepZ;
    private int ncpNextStep;
    private boolean spartanSwitch;
    private boolean isAACStep;
    private final MSTimer timer = new MSTimer();

    @Override
    public void onDisable() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        thePlayer.setStepHeight(0.5f);
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        block7: {
            block9: {
                IEntityPlayerSP thePlayer;
                String mode;
                block8: {
                    block6: {
                        Intrinsics.checkParameterIsNotNull(event, "event");
                        mode = (String)this.modeValue.get();
                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                        if (iEntityPlayerSP == null) {
                            return;
                        }
                        thePlayer = iEntityPlayerSP;
                        if (!StringsKt.equals(mode, "jump", true) || !thePlayer.isCollidedHorizontally() || !thePlayer.getOnGround() || MinecraftInstance.mc.getGameSettings().getKeyBindJump().isKeyDown()) break block6;
                        this.fakeJump();
                        thePlayer.setMotionY(((Number)this.jumpHeightValue.get()).floatValue());
                        break block7;
                    }
                    if (!StringsKt.equals(mode, "laac", true)) break block8;
                    if (!(!thePlayer.isCollidedHorizontally() || thePlayer.isOnLadder() || thePlayer.isInWater() || thePlayer.isInLava() || thePlayer.isInWeb())) {
                        if (thePlayer.getOnGround() && this.timer.hasTimePassed(((Number)this.delayValue.get()).intValue())) {
                            this.isStep = true;
                            this.fakeJump();
                            IEntityPlayerSP iEntityPlayerSP = thePlayer;
                            iEntityPlayerSP.setMotionY(iEntityPlayerSP.getMotionY() + 0.620000001490116);
                            float f = thePlayer.getRotationYaw() * ((float)Math.PI / 180);
                            IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
                            double d = iEntityPlayerSP2.getMotionX();
                            IEntityPlayerSP iEntityPlayerSP3 = iEntityPlayerSP2;
                            boolean bl = false;
                            float f2 = (float)Math.sin(f);
                            iEntityPlayerSP3.setMotionX(d - (double)f2 * 0.2);
                            IEntityPlayerSP iEntityPlayerSP4 = thePlayer;
                            d = iEntityPlayerSP4.getMotionZ();
                            iEntityPlayerSP3 = iEntityPlayerSP4;
                            bl = false;
                            f2 = (float)Math.cos(f);
                            iEntityPlayerSP3.setMotionZ(d + (double)f2 * 0.2);
                            this.timer.reset();
                        }
                        thePlayer.setOnGround(true);
                    } else {
                        this.isStep = false;
                    }
                    break block7;
                }
                if (!StringsKt.equals(mode, "aac3.3.4", true)) break block7;
                if (!thePlayer.isCollidedHorizontally() || !MovementUtils.isMoving()) break block9;
                if (thePlayer.getOnGround() && this.couldStep()) {
                    IEntityPlayerSP iEntityPlayerSP = thePlayer;
                    iEntityPlayerSP.setMotionX(iEntityPlayerSP.getMotionX() * 1.26);
                    IEntityPlayerSP iEntityPlayerSP5 = thePlayer;
                    iEntityPlayerSP5.setMotionZ(iEntityPlayerSP5.getMotionZ() * 1.26);
                    thePlayer.jump();
                    this.isAACStep = true;
                }
                if (!this.isAACStep) break block7;
                IEntityPlayerSP iEntityPlayerSP = thePlayer;
                iEntityPlayerSP.setMotionY(iEntityPlayerSP.getMotionY() - 0.015);
                if (!thePlayer.isUsingItem() && thePlayer.getMovementInput().getMoveStrafe() == 0.0f) {
                    thePlayer.setJumpMovementFactor(0.3f);
                }
                break block7;
            }
            this.isAACStep = false;
        }
    }

    @EventTarget
    public final void onMove(@NotNull MoveEvent event) {
        block1: {
            block3: {
                block2: {
                    Intrinsics.checkParameterIsNotNull(event, "event");
                    String mode = (String)this.modeValue.get();
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        return;
                    }
                    IEntityPlayerSP thePlayer = iEntityPlayerSP;
                    if (!StringsKt.equals(mode, "motionncp", true) || !thePlayer.isCollidedHorizontally() || MinecraftInstance.mc.getGameSettings().getKeyBindJump().isKeyDown()) break block1;
                    if (!thePlayer.getOnGround() || !this.couldStep()) break block2;
                    this.fakeJump();
                    thePlayer.setMotionY(0.0);
                    event.setY(0.41999998688698);
                    this.ncpNextStep = 1;
                    break block1;
                }
                if (this.ncpNextStep != 1) break block3;
                event.setY(0.33319999363422);
                this.ncpNextStep = 2;
                break block1;
            }
            if (this.ncpNextStep != 2) break block1;
            double yaw = MovementUtils.getDirection();
            event.setY(0.24813599859094704);
            MoveEvent moveEvent = event;
            boolean bl = false;
            double d = Math.sin(yaw);
            moveEvent.setX(-d * 0.7);
            moveEvent = event;
            bl = false;
            d = Math.cos(yaw);
            moveEvent.setZ(d * 0.7);
            this.ncpNextStep = 0;
        }
    }

    @EventTarget
    public final void onStep(@NotNull StepEvent event) {
        String flyMode;
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        Module module = LiquidBounce.INSTANCE.getModuleManager().get(Phase.class);
        if (module == null) {
            Intrinsics.throwNpe();
        }
        if (module.getState()) {
            event.setStepHeight(0.0f);
            return;
        }
        Module module2 = LiquidBounce.INSTANCE.getModuleManager().get(Fly.class);
        if (module2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.movement.Fly");
        }
        Fly fly = (Fly)module2;
        if (fly.getState() && (StringsKt.equals(flyMode = (String)fly.getModeValue().get(), "Hypixel", true) || StringsKt.equals(flyMode, "OtherHypixel", true) || StringsKt.equals(flyMode, "LatestHypixel", true) || StringsKt.equals(flyMode, "Rewinside", true) || StringsKt.equals(flyMode, "Mineplex", true) && thePlayer.getInventory().getCurrentItemInHand() == null)) {
            event.setStepHeight(0.0f);
            return;
        }
        String mode = (String)this.modeValue.get();
        if (!thePlayer.getOnGround() || !this.timer.hasTimePassed(((Number)this.delayValue.get()).intValue()) || StringsKt.equals(mode, "Jump", true) || StringsKt.equals(mode, "MotionNCP", true) || StringsKt.equals(mode, "LAAC", true) || StringsKt.equals(mode, "AAC3.3.4", true)) {
            thePlayer.setStepHeight(0.5f);
            event.setStepHeight(0.5f);
            return;
        }
        float height = ((Number)this.heightValue.get()).floatValue();
        thePlayer.setStepHeight(height);
        event.setStepHeight(height);
        if (event.getStepHeight() > 0.5f) {
            this.isStep = true;
            this.stepX = thePlayer.getPosX();
            this.stepY = thePlayer.getPosY();
            this.stepZ = thePlayer.getPosZ();
        }
    }

    @EventTarget(ignoreCondition=true)
    public final void onStepConfirm(@NotNull StepConfirmEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP thePlayer = MinecraftInstance.mc.getThePlayer();
        if (thePlayer == null || !this.isStep) {
            return;
        }
        if (thePlayer.getEntityBoundingBox().getMinY() - this.stepY > 0.5) {
            String mode = (String)this.modeValue.get();
            if (StringsKt.equals(mode, "NCP", true) || StringsKt.equals(mode, "AAC", true)) {
                this.fakeJump();
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.41999998688698, this.stepZ, false));
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.7531999805212, this.stepZ, false));
                this.timer.reset();
            } else if (StringsKt.equals(mode, "Spartan", true)) {
                this.fakeJump();
                if (this.spartanSwitch) {
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.41999998688698, this.stepZ, false));
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.7531999805212, this.stepZ, false));
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 1.001335979112147, this.stepZ, false));
                } else {
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.6, this.stepZ, false));
                }
                this.spartanSwitch = !this.spartanSwitch;
                this.timer.reset();
            } else if (StringsKt.equals(mode, "Rewinside", true)) {
                this.fakeJump();
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.41999998688698, this.stepZ, false));
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 0.7531999805212, this.stepZ, false));
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosition(this.stepX, this.stepY + 1.001335979112147, this.stepZ, false));
                this.timer.reset();
            }
        }
        this.isStep = false;
        this.stepX = 0.0;
        this.stepY = 0.0;
        this.stepZ = 0.0;
    }

    @EventTarget(ignoreCondition=true)
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IPacket packet = event.getPacket();
        if (MinecraftInstance.classProvider.isCPacketPlayer(packet) && this.isStep && StringsKt.equals((String)this.modeValue.get(), "OldNCP", true)) {
            ICPacketPlayer iCPacketPlayer = packet.asCPacketPlayer();
            iCPacketPlayer.setY(iCPacketPlayer.getY() + 0.07);
            this.isStep = false;
        }
    }

    private final void fakeJump() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        thePlayer.setAirBorne(true);
        thePlayer.triggerAchievement(MinecraftInstance.classProvider.getStatEnum(StatType.JUMP_STAT));
    }

    private final boolean couldStep() {
        double yaw = MovementUtils.getDirection();
        boolean bl = false;
        double x = -Math.sin(yaw) * 0.4;
        boolean bl2 = false;
        double z = Math.cos(yaw) * 0.4;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        return iWorldClient.getCollisionBoxes(iEntityPlayerSP.getEntityBoundingBox().offset(x, 1.001335979112147, z)).isEmpty();
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.modeValue.get();
    }
}

