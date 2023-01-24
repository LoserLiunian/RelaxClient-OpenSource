/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.server.ISPacketEntityVelocity;
import net.ccbluex.liquidbounce.api.minecraft.potion.PotionType;
import net.ccbluex.liquidbounce.event.BlockBBEvent;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="Velocity", description="Edit your velocity", category=ModuleCategory.COMBAT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0007J\b\u00103\u001a\u000200H\u0016J\u0010\u00104\u001a\u0002002\u0006\u00101\u001a\u000205H\u0007J\u0010\u00106\u001a\u0002002\u0006\u00101\u001a\u000207H\u0007J\u0010\u00108\u001a\u0002002\u0006\u00101\u001a\u000209H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/Velocity;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "aac4XZReducerValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "aacPushXZReducerValue", "aacPushYReducerValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "block", "Lnet/ccbluex/liquidbounce/api/minecraft/client/block/IBlock;", "getBlock", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/block/IBlock;", "setBlock", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/block/IBlock;)V", "canCancelJump", "", "canCleanJump", "cobwebValue", "customC06FakeLag", "customX", "customY", "customYStart", "customZ", "horizontalValue", "hytGround", "hytpacketaset", "hytpacketbset", "jump", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "newaac4XZReducerValue", "noFireValue", "reverse2StrengthValue", "reverseHurt", "reverseStrengthValue", "tag", "", "getTag", "()Ljava/lang/String;", "velocityInput", "velocityTick", "", "velocityTickValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "velocityTimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "verticalValue", "onBlockBB", "", "event", "Lnet/ccbluex/liquidbounce/event/BlockBBEvent;", "onDisable", "onJump", "Lnet/ccbluex/liquidbounce/event/JumpEvent;", "onPacket", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class Velocity
extends Module {
    private final FloatValue horizontalValue = new FloatValue("Horizontal", 0.0f, 0.0f, 1.0f);
    private final FloatValue verticalValue = new FloatValue("Vertical", 0.0f, 0.0f, 1.0f);
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Custom", "AAC4", "Simple", "SimpleFix", "AAC", "AACPush", "AACZero", "Reverse", "SmoothReverse", "Jump", "AAC5Reduce", "HytPacketA", "Glitch", "HytCancel", "HytTick", "Vanilla", "HytTest", "HytNewTest", "HytPacket", "NewAAC4", "FeiLe", "HytMotion", "NewHytMotion", "HytPacketB", "HytMotionB", "HytPacketFix", "S27", "LatestTestHyt"}, "Vanilla");
    private final FloatValue aac4XZReducerValue = new FloatValue("AAC4XZReducer", 1.36f, 1.0f, 3.0f);
    private final FloatValue newaac4XZReducerValue = new FloatValue("NewAAC4XZReducer", 0.45f, 0.0f, 1.0f);
    private final IntegerValue velocityTickValue = new IntegerValue("VelocityTick", 1, 0, 10);
    private final FloatValue reverseStrengthValue = new FloatValue("ReverseStrength", 1.0f, 0.1f, 1.0f);
    private final FloatValue reverse2StrengthValue = new FloatValue("SmoothReverseStrength", 0.05f, 0.02f, 0.1f);
    private final FloatValue hytpacketaset = new FloatValue("HytPacketASet", 0.35f, 0.1f, 1.0f);
    private final FloatValue hytpacketbset = new FloatValue("HytPacketBSet", 0.5f, 1.0f, 1.0f);
    private final FloatValue aacPushXZReducerValue = new FloatValue("AACPushXZReducer", 2.0f, 1.0f, 3.0f);
    private final BoolValue aacPushYReducerValue = new BoolValue("AACPushYReducer", true);
    @Nullable
    private IBlock block;
    private final BoolValue noFireValue = new BoolValue("noFire", false);
    private final BoolValue cobwebValue = new BoolValue("NoCobweb", true);
    private final BoolValue hytGround = new BoolValue("HytOnlyGround", true);
    private final FloatValue customX = new FloatValue("CustomX", 0.0f, 0.0f, 1.0f);
    private final BoolValue customYStart = new BoolValue("CanCustomY", false);
    private final FloatValue customY = new FloatValue("CustomY", 1.0f, 1.0f, 2.0f);
    private final FloatValue customZ = new FloatValue("CustomZ", 0.0f, 0.0f, 1.0f);
    private final BoolValue customC06FakeLag = new BoolValue("CustomC06FakeLag", false);
    private MSTimer velocityTimer = new MSTimer();
    private boolean velocityInput;
    private boolean canCleanJump;
    private int velocityTick;
    private boolean reverseHurt;
    private boolean jump;
    private boolean canCancelJump;

    @Nullable
    public final IBlock getBlock() {
        return this.block;
    }

    public final void setBlock(@Nullable IBlock iBlock) {
        this.block = iBlock;
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.modeValue.get();
    }

    @Override
    public void onDisable() {
        block0: {
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) break block0;
            iEntityPlayerSP.setSpeedInAir(0.02f);
        }
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (thePlayer.isInWater() || thePlayer.isInLava() || thePlayer.isInWeb()) {
            return;
        }
        if (((Boolean)this.noFireValue.get()).booleanValue()) {
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP2.isBurning()) {
                return;
            }
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
            case "jump": {
                if (thePlayer.getHurtTime() <= 0 || !thePlayer.getOnGround()) break;
                thePlayer.setMotionY(0.42);
                float yaw = thePlayer.getRotationYaw() * ((float)Math.PI / 180);
                IEntityPlayerSP iEntityPlayerSP3 = thePlayer;
                double d = iEntityPlayerSP3.getMotionX();
                IEntityPlayerSP iEntityPlayerSP4 = iEntityPlayerSP3;
                boolean bl2 = false;
                float f = (float)Math.sin(yaw);
                iEntityPlayerSP4.setMotionX(d - (double)f * 0.2);
                IEntityPlayerSP iEntityPlayerSP5 = thePlayer;
                d = iEntityPlayerSP5.getMotionZ();
                iEntityPlayerSP4 = iEntityPlayerSP5;
                bl2 = false;
                f = (float)Math.cos(yaw);
                iEntityPlayerSP4.setMotionZ(d + (double)f * 0.2);
                break;
            }
            case "latesttesthyt": {
                if (((Boolean)this.hytGround.get()).booleanValue()) {
                    if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getHurtTime() > 5 || !thePlayer.getOnGround()) break;
                    IEntityPlayerSP iEntityPlayerSP6 = thePlayer;
                    iEntityPlayerSP6.setMotionX(iEntityPlayerSP6.getMotionX() * 0.35);
                    IEntityPlayerSP iEntityPlayerSP7 = thePlayer;
                    iEntityPlayerSP7.setMotionZ(iEntityPlayerSP7.getMotionZ() * 0.35);
                    IEntityPlayerSP iEntityPlayerSP8 = thePlayer;
                    iEntityPlayerSP8.setMotionY(iEntityPlayerSP8.getMotionY() * 0.001);
                    IEntityPlayerSP iEntityPlayerSP9 = thePlayer;
                    iEntityPlayerSP9.setMotionY(iEntityPlayerSP9.getMotionY() / (double)0.01f);
                    break;
                }
                if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getHurtTime() > 5) break;
                IEntityPlayerSP iEntityPlayerSP10 = thePlayer;
                iEntityPlayerSP10.setMotionX(iEntityPlayerSP10.getMotionX() * 0.35);
                IEntityPlayerSP iEntityPlayerSP11 = thePlayer;
                iEntityPlayerSP11.setMotionZ(iEntityPlayerSP11.getMotionZ() * 0.35);
                IEntityPlayerSP iEntityPlayerSP12 = thePlayer;
                iEntityPlayerSP12.setMotionY(iEntityPlayerSP12.getMotionY() * 0.001);
                IEntityPlayerSP iEntityPlayerSP13 = thePlayer;
                iEntityPlayerSP13.setMotionY(iEntityPlayerSP13.getMotionY() / (double)0.01f);
                break;
            }
            case "glitch": {
                thePlayer.setNoClip(this.velocityInput);
                if (thePlayer.getHurtTime() == 7) {
                    thePlayer.setMotionY(0.4);
                }
                this.velocityInput = false;
                break;
            }
            case "feile": {
                if (!thePlayer.getOnGround()) break;
                this.canCleanJump = true;
                thePlayer.setMotionY(1.5);
                thePlayer.setMotionZ(1.2);
                thePlayer.setMotionX(1.5);
                if (!thePlayer.getOnGround() || this.velocityTick <= 2) break;
                this.velocityInput = false;
                break;
            }
            case "aac5reduce": {
                if (thePlayer.getHurtTime() > 1 && this.velocityInput) {
                    IEntityPlayerSP iEntityPlayerSP14 = thePlayer;
                    iEntityPlayerSP14.setMotionX(iEntityPlayerSP14.getMotionX() * 0.81);
                    IEntityPlayerSP iEntityPlayerSP15 = thePlayer;
                    iEntityPlayerSP15.setMotionZ(iEntityPlayerSP15.getMotionZ() * 0.81);
                }
                if (!this.velocityInput || thePlayer.getHurtTime() >= 5 && !thePlayer.getOnGround() || !this.velocityTimer.hasTimePassed(120L)) break;
                this.velocityInput = false;
                break;
            }
            case "hyttick": {
                if (this.velocityTick > ((Number)this.velocityTickValue.get()).intValue()) {
                    if (thePlayer.getMotionY() > 0.0) {
                        thePlayer.setMotionY(0.0);
                    }
                    thePlayer.setMotionX(0.0);
                    thePlayer.setMotionZ(0.0);
                    thePlayer.setJumpMovementFactor(-1.0E-5f);
                    this.velocityInput = false;
                }
                if (!thePlayer.getOnGround() || this.velocityTick <= 1) break;
                this.velocityInput = false;
                break;
            }
            case "reverse": {
                if (!this.velocityInput) {
                    return;
                }
                if (!thePlayer.getOnGround()) {
                    MovementUtils.strafe(MovementUtils.INSTANCE.getSpeed() * ((Number)this.reverseStrengthValue.get()).floatValue());
                    break;
                }
                if (!this.velocityTimer.hasTimePassed(80L)) break;
                this.velocityInput = false;
                break;
            }
            case "aac4": {
                if (!thePlayer.getOnGround()) {
                    if (!this.velocityInput) break;
                    thePlayer.setSpeedInAir(0.02f);
                    IEntityPlayerSP iEntityPlayerSP16 = thePlayer;
                    iEntityPlayerSP16.setMotionX(iEntityPlayerSP16.getMotionX() * 0.6);
                    IEntityPlayerSP iEntityPlayerSP17 = thePlayer;
                    iEntityPlayerSP17.setMotionZ(iEntityPlayerSP17.getMotionZ() * 0.6);
                    break;
                }
                if (!this.velocityTimer.hasTimePassed(80L)) break;
                this.velocityInput = false;
                thePlayer.setSpeedInAir(0.02f);
                break;
            }
            case "newaac4": {
                if (thePlayer.getHurtTime() <= 0 || thePlayer.getOnGround()) break;
                float reduce = ((Number)this.newaac4XZReducerValue.get()).floatValue();
                IEntityPlayerSP iEntityPlayerSP18 = thePlayer;
                iEntityPlayerSP18.setMotionX(iEntityPlayerSP18.getMotionX() * (double)reduce);
                IEntityPlayerSP iEntityPlayerSP19 = thePlayer;
                iEntityPlayerSP19.setMotionZ(iEntityPlayerSP19.getMotionZ() * (double)reduce);
                break;
            }
            case "smoothreverse": {
                if (!this.velocityInput) {
                    thePlayer.setSpeedInAir(0.02f);
                    return;
                }
                if (thePlayer.getHurtTime() > 0) {
                    this.reverseHurt = true;
                }
                if (!thePlayer.getOnGround()) {
                    if (!this.reverseHurt) break;
                    thePlayer.setSpeedInAir(((Number)this.reverse2StrengthValue.get()).floatValue());
                    break;
                }
                if (!this.velocityTimer.hasTimePassed(80L)) break;
                this.velocityInput = false;
                this.reverseHurt = false;
                break;
            }
            case "aac": {
                if (!this.velocityInput || !this.velocityTimer.hasTimePassed(80L)) break;
                IEntityPlayerSP iEntityPlayerSP20 = thePlayer;
                iEntityPlayerSP20.setMotionX(iEntityPlayerSP20.getMotionX() * ((Number)this.horizontalValue.get()).doubleValue());
                IEntityPlayerSP iEntityPlayerSP21 = thePlayer;
                iEntityPlayerSP21.setMotionZ(iEntityPlayerSP21.getMotionZ() * ((Number)this.horizontalValue.get()).doubleValue());
                this.velocityInput = false;
                break;
            }
            case "hytpacket": {
                if (((Boolean)this.hytGround.get()).booleanValue()) {
                    if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getHurtTime() > 5 || !thePlayer.getOnGround()) break;
                    IEntityPlayerSP iEntityPlayerSP22 = thePlayer;
                    iEntityPlayerSP22.setMotionX(iEntityPlayerSP22.getMotionX() * 0.5);
                    IEntityPlayerSP iEntityPlayerSP23 = thePlayer;
                    iEntityPlayerSP23.setMotionZ(iEntityPlayerSP23.getMotionZ() * 0.5);
                    IEntityPlayerSP iEntityPlayerSP24 = thePlayer;
                    iEntityPlayerSP24.setMotionY(iEntityPlayerSP24.getMotionY() / (double)1.781145f);
                    break;
                }
                if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getHurtTime() > 5) break;
                IEntityPlayerSP iEntityPlayerSP25 = thePlayer;
                iEntityPlayerSP25.setMotionX(iEntityPlayerSP25.getMotionX() * 0.5);
                IEntityPlayerSP iEntityPlayerSP26 = thePlayer;
                iEntityPlayerSP26.setMotionZ(iEntityPlayerSP26.getMotionZ() * 0.5);
                IEntityPlayerSP iEntityPlayerSP27 = thePlayer;
                iEntityPlayerSP27.setMotionY(iEntityPlayerSP27.getMotionY() / (double)1.781145f);
                break;
            }
            case "hytmotion": {
                if (((Boolean)this.hytGround.get()).booleanValue()) {
                    if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getHurtTime() > 5 || !thePlayer.getOnGround()) break;
                    IEntityPlayerSP iEntityPlayerSP28 = thePlayer;
                    iEntityPlayerSP28.setMotionX(iEntityPlayerSP28.getMotionX() * 0.4);
                    IEntityPlayerSP iEntityPlayerSP29 = thePlayer;
                    iEntityPlayerSP29.setMotionZ(iEntityPlayerSP29.getMotionZ() * 0.4);
                    IEntityPlayerSP iEntityPlayerSP30 = thePlayer;
                    iEntityPlayerSP30.setMotionY(iEntityPlayerSP30.getMotionY() * (double)0.381145f);
                    IEntityPlayerSP iEntityPlayerSP31 = thePlayer;
                    iEntityPlayerSP31.setMotionY(iEntityPlayerSP31.getMotionY() / (double)1.781145f);
                    break;
                }
                if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getHurtTime() > 5) break;
                IEntityPlayerSP iEntityPlayerSP32 = thePlayer;
                iEntityPlayerSP32.setMotionX(iEntityPlayerSP32.getMotionX() * 0.4);
                IEntityPlayerSP iEntityPlayerSP33 = thePlayer;
                iEntityPlayerSP33.setMotionZ(iEntityPlayerSP33.getMotionZ() * 0.4);
                IEntityPlayerSP iEntityPlayerSP34 = thePlayer;
                iEntityPlayerSP34.setMotionY(iEntityPlayerSP34.getMotionY() * (double)0.381145f);
                IEntityPlayerSP iEntityPlayerSP35 = thePlayer;
                iEntityPlayerSP35.setMotionY(iEntityPlayerSP35.getMotionY() / (double)1.781145f);
                break;
            }
            case "hytmotionb": {
                if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getOnGround() || thePlayer.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.MOVE_SPEED))) break;
                IEntityPlayerSP iEntityPlayerSP36 = thePlayer;
                iEntityPlayerSP36.setMotionX(iEntityPlayerSP36.getMotionX() * (double)0.451145f);
                IEntityPlayerSP iEntityPlayerSP37 = thePlayer;
                iEntityPlayerSP37.setMotionZ(iEntityPlayerSP37.getMotionZ() * (double)0.451145f);
                break;
            }
            case "newhytmotion": {
                if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead() || thePlayer.getOnGround()) break;
                if (!thePlayer.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.MOVE_SPEED))) {
                    IEntityPlayerSP iEntityPlayerSP38 = thePlayer;
                    iEntityPlayerSP38.setMotionX(iEntityPlayerSP38.getMotionX() * 0.47188);
                    IEntityPlayerSP iEntityPlayerSP39 = thePlayer;
                    iEntityPlayerSP39.setMotionZ(iEntityPlayerSP39.getMotionZ() * 0.47188);
                    if (thePlayer.getMotionY() != 0.42 && !(thePlayer.getMotionY() > 0.42)) break;
                    IEntityPlayerSP iEntityPlayerSP40 = thePlayer;
                    iEntityPlayerSP40.setMotionY(iEntityPlayerSP40.getMotionY() * 0.4);
                    break;
                }
                IEntityPlayerSP iEntityPlayerSP41 = thePlayer;
                iEntityPlayerSP41.setMotionX(iEntityPlayerSP41.getMotionX() * 0.65025);
                IEntityPlayerSP iEntityPlayerSP42 = thePlayer;
                iEntityPlayerSP42.setMotionZ(iEntityPlayerSP42.getMotionZ() * 0.65025);
                if (thePlayer.getMotionY() != 0.42 && !(thePlayer.getMotionY() > 0.42)) break;
                IEntityPlayerSP iEntityPlayerSP43 = thePlayer;
                iEntityPlayerSP43.setMotionY(iEntityPlayerSP43.getMotionY() * 0.4);
                break;
            }
            case "aacpush": {
                if (this.jump) {
                    if (thePlayer.getOnGround()) {
                        this.jump = false;
                    }
                } else {
                    if (thePlayer.getHurtTime() > 0 && thePlayer.getMotionX() != 0.0 && thePlayer.getMotionZ() != 0.0) {
                        thePlayer.setOnGround(true);
                    }
                    if (thePlayer.getHurtResistantTime() > 0 && ((Boolean)this.aacPushYReducerValue.get()).booleanValue()) {
                        Module module = LiquidBounce.INSTANCE.getModuleManager().get(Speed.class);
                        if (module == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!module.getState()) {
                            IEntityPlayerSP iEntityPlayerSP44 = thePlayer;
                            iEntityPlayerSP44.setMotionY(iEntityPlayerSP44.getMotionY() - 0.014999993);
                        }
                    }
                }
                if (thePlayer.getHurtResistantTime() < 19) break;
                float reduce = ((Number)this.aacPushXZReducerValue.get()).floatValue();
                IEntityPlayerSP iEntityPlayerSP45 = thePlayer;
                iEntityPlayerSP45.setMotionX(iEntityPlayerSP45.getMotionX() / (double)reduce);
                IEntityPlayerSP iEntityPlayerSP46 = thePlayer;
                iEntityPlayerSP46.setMotionZ(iEntityPlayerSP46.getMotionZ() / (double)reduce);
                break;
            }
            case "custom": {
                if (thePlayer.getHurtTime() <= 0 || thePlayer.isDead()) break;
                IEntityPlayerSP iEntityPlayerSP47 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP47 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP47.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.MOVE_SPEED))) break;
                IEntityPlayerSP iEntityPlayerSP48 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP48 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP48.isInWater()) break;
                IEntityPlayerSP iEntityPlayerSP49 = thePlayer;
                iEntityPlayerSP49.setMotionX(iEntityPlayerSP49.getMotionX() * ((Number)this.customX.get()).doubleValue());
                IEntityPlayerSP iEntityPlayerSP50 = thePlayer;
                iEntityPlayerSP50.setMotionZ(iEntityPlayerSP50.getMotionZ() * ((Number)this.customZ.get()).doubleValue());
                if (((Boolean)this.customYStart.get()).booleanValue()) {
                    IEntityPlayerSP iEntityPlayerSP51 = thePlayer;
                    iEntityPlayerSP51.setMotionY(iEntityPlayerSP51.getMotionY() / ((Number)this.customY.get()).doubleValue());
                }
                if (!((Boolean)this.customC06FakeLag.get()).booleanValue()) break;
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerPosLook(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ(), thePlayer.getRotationYaw(), thePlayer.getRotationPitch(), thePlayer.getOnGround()));
                break;
            }
            case "aaczero": {
                if (thePlayer.getHurtTime() > 0) {
                    if (!this.velocityInput || thePlayer.getOnGround() || thePlayer.getFallDistance() > 2.0f) {
                        return;
                    }
                    IEntityPlayerSP iEntityPlayerSP52 = thePlayer;
                    iEntityPlayerSP52.setMotionY(iEntityPlayerSP52.getMotionY() - 1.0);
                    thePlayer.setAirBorne(true);
                    thePlayer.setOnGround(true);
                    break;
                }
                this.velocityInput = false;
                break;
            }
        }
    }

    @EventTarget
    public final void onBlockBB(@NotNull BlockBBEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.block = event.getBlock();
    }

    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        IPacket packet = event.getPacket();
        if (MinecraftInstance.classProvider.isSPacketEntityVelocity(packet)) {
            Object object;
            ISPacketEntityVelocity packetEntityVelocity = packet.asSPacketEntityVelocity();
            if (((Boolean)this.noFireValue.get()).booleanValue()) {
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP2.isBurning()) {
                    return;
                }
            }
            if ((object = MinecraftInstance.mc.getTheWorld()) == null || (object = object.getEntityByID(packetEntityVelocity.getEntityID())) == null) {
                return;
            }
            if (Intrinsics.areEqual(object, thePlayer) ^ true) {
                return;
            }
            this.velocityTimer.reset();
            String string = (String)this.modeValue.get();
            boolean bl = false;
            String string2 = string;
            if (string2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string3 = string2.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
            switch (string3) {
                case "vanilla": {
                    event.cancelEvent();
                    break;
                }
                case "s27": {
                    if (MinecraftInstance.classProvider.isSPacketExplosion(packet)) {
                        event.cancelEvent();
                    }
                    float horizontal = ((Number)this.horizontalValue.get()).floatValue();
                    float vertical = ((Number)this.verticalValue.get()).floatValue();
                    packetEntityVelocity.setMotionX((int)((float)packetEntityVelocity.getMotionX() * horizontal));
                    packetEntityVelocity.setMotionZ((int)((float)packetEntityVelocity.getMotionZ() * horizontal));
                    break;
                }
                case "simple": {
                    float horizontal = ((Number)this.horizontalValue.get()).floatValue();
                    float vertical = ((Number)this.verticalValue.get()).floatValue();
                    if (horizontal == 0.0f && vertical == 0.0f) {
                        event.cancelEvent();
                    }
                    packetEntityVelocity.setMotionX((int)((float)packetEntityVelocity.getMotionX() * horizontal));
                    packetEntityVelocity.setMotionY((int)((float)packetEntityVelocity.getMotionY() * vertical));
                    packetEntityVelocity.setMotionZ((int)((float)packetEntityVelocity.getMotionZ() * horizontal));
                    break;
                }
                case "hytpacketfix": {
                    if (thePlayer.getHurtTime() > 0 && !thePlayer.isDead()) {
                        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                        if (iEntityPlayerSP3 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!iEntityPlayerSP3.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.MOVE_SPEED))) {
                            IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                            if (iEntityPlayerSP4 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (!iEntityPlayerSP4.isInWater()) {
                                IEntityPlayerSP iEntityPlayerSP5 = thePlayer;
                                iEntityPlayerSP5.setMotionX(iEntityPlayerSP5.getMotionX() * 0.4);
                                IEntityPlayerSP iEntityPlayerSP6 = thePlayer;
                                iEntityPlayerSP6.setMotionZ(iEntityPlayerSP6.getMotionZ() * 0.4);
                                IEntityPlayerSP iEntityPlayerSP7 = thePlayer;
                                iEntityPlayerSP7.setMotionY(iEntityPlayerSP7.getMotionY() / (double)1.45f);
                            }
                        }
                    }
                    if (thePlayer.getHurtTime() < 1) {
                        packetEntityVelocity.setMotionY(0);
                    }
                    if (thePlayer.getHurtTime() >= 5) break;
                    packetEntityVelocity.setMotionX(0);
                    packetEntityVelocity.setMotionZ(0);
                    break;
                }
                case "hyttest": {
                    if (!thePlayer.getOnGround()) break;
                    this.canCancelJump = false;
                    packetEntityVelocity.setMotionX((int)0.985114);
                    packetEntityVelocity.setMotionY((int)0.885113);
                    packetEntityVelocity.setMotionZ((int)0.785112);
                    IEntityPlayerSP iEntityPlayerSP8 = thePlayer;
                    iEntityPlayerSP8.setMotionX(iEntityPlayerSP8.getMotionX() / 1.75);
                    IEntityPlayerSP iEntityPlayerSP9 = thePlayer;
                    iEntityPlayerSP9.setMotionZ(iEntityPlayerSP9.getMotionZ() / 1.75);
                    break;
                }
                case "hytnewtest": {
                    if (!thePlayer.getOnGround()) break;
                    this.velocityInput = true;
                    float yaw = thePlayer.getRotationYaw() * ((float)Math.PI / 180);
                    packetEntityVelocity.setMotionX((int)((double)packetEntityVelocity.getMotionX() * 0.75));
                    packetEntityVelocity.setMotionZ((int)((double)packetEntityVelocity.getMotionZ() * 0.75));
                    IEntityPlayerSP iEntityPlayerSP10 = thePlayer;
                    double d = iEntityPlayerSP10.getMotionX();
                    IEntityPlayerSP iEntityPlayerSP11 = iEntityPlayerSP10;
                    boolean vertical = false;
                    float f = (float)Math.sin(yaw);
                    iEntityPlayerSP11.setMotionX(d - (double)f * 0.2);
                    IEntityPlayerSP iEntityPlayerSP12 = thePlayer;
                    d = iEntityPlayerSP12.getMotionZ();
                    iEntityPlayerSP11 = iEntityPlayerSP12;
                    vertical = false;
                    f = (float)Math.cos(yaw);
                    iEntityPlayerSP11.setMotionZ(d + (double)f * 0.2);
                    break;
                }
                case "hytpacketa": {
                    packetEntityVelocity.setMotionX((int)((double)((float)packetEntityVelocity.getMotionX() * ((Number)this.hytpacketaset.get()).floatValue()) / 1.5));
                    packetEntityVelocity.setMotionY((int)0.7);
                    packetEntityVelocity.setMotionZ((int)((double)((float)packetEntityVelocity.getMotionZ() * ((Number)this.hytpacketaset.get()).floatValue()) / 1.5));
                    event.cancelEvent();
                    break;
                }
                case "hytpacketb": {
                    packetEntityVelocity.setMotionX((int)((double)((float)packetEntityVelocity.getMotionX() * ((Number)this.hytpacketbset.get()).floatValue()) / 2.5));
                    packetEntityVelocity.setMotionY((int)((double)((float)packetEntityVelocity.getMotionY() * ((Number)this.hytpacketbset.get()).floatValue()) / 2.5));
                    packetEntityVelocity.setMotionZ((int)((double)((float)packetEntityVelocity.getMotionZ() * ((Number)this.hytpacketbset.get()).floatValue()) / 2.5));
                    break;
                }
                case "aac": 
                case "aaczero": 
                case "reverse": 
                case "aac4": 
                case "smoothreverse": 
                case "aac5reduce": {
                    this.velocityInput = true;
                    break;
                }
                case "hyttick": {
                    this.velocityInput = true;
                    float horizontal = 0.0f;
                    float vertical = 0.0f;
                    event.cancelEvent();
                    break;
                }
                case "glitch": {
                    if (!thePlayer.getOnGround()) {
                        return;
                    }
                    this.velocityInput = true;
                    event.cancelEvent();
                    break;
                }
                case "hytcancel": {
                    event.cancelEvent();
                    break;
                }
            }
        }
    }

    @EventTarget
    public final void onJump(@NotNull JumpEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP thePlayer = MinecraftInstance.mc.getThePlayer();
        if (thePlayer == null || thePlayer.isInWater() || thePlayer.isInLava() || thePlayer.isInWeb()) {
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
            case "aacpush": {
                this.jump = true;
                if (thePlayer.isCollidedVertically()) break;
                event.cancelEvent();
                break;
            }
            case "aac4": {
                if (thePlayer.getHurtTime() <= 0) break;
                event.cancelEvent();
                break;
            }
            case "aaczero": {
                if (thePlayer.getHurtTime() <= 0) break;
                event.cancelEvent();
                break;
            }
        }
    }
}

