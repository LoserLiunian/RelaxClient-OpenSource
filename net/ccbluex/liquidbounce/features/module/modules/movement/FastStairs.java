/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.movement;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.IClassProvider;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="FastStairs", description="Allows you to climb up stairs faster.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/FastStairs;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "canJump", "", "longJumpValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "tag", "", "getTag", "()Ljava/lang/String;", "walkingDown", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class FastStairs
extends Module {
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Step", "NCP", "AAC3.1.0", "AAC3.3.6", "AAC3.3.13"}, "NCP");
    private final BoolValue longJumpValue = new BoolValue("LongJump", false);
    private boolean canJump;
    private boolean walkingDown;

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        void blockPos$iv;
        IBlock iBlock;
        IEntityPlayerSP thePlayer;
        block19: {
            block18: {
                Intrinsics.checkParameterIsNotNull(event, "event");
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    return;
                }
                thePlayer = iEntityPlayerSP;
                if (!MovementUtils.isMoving()) break block18;
                Module module = LiquidBounce.INSTANCE.getModuleManager().get(Speed.class);
                if (module == null) {
                    Intrinsics.throwNpe();
                }
                if (!module.getState()) break block19;
            }
            return;
        }
        if (thePlayer.getFallDistance() > 0.0f && !this.walkingDown) {
            this.walkingDown = true;
        } else if (thePlayer.getPosY() > thePlayer.getPrevChasingPosY()) {
            this.walkingDown = false;
        }
        String mode = (String)this.modeValue.get();
        if (!thePlayer.getOnGround()) {
            return;
        }
        WBlockPos blockPos = new WBlockPos(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY(), thePlayer.getPosZ());
        IClassProvider iClassProvider = MinecraftInstance.classProvider;
        boolean $i$f$getBlock = false;
        Object object = MinecraftInstance.mc.getTheWorld();
        IBlock iBlock2 = object != null && (object = object.getBlockState(blockPos)) != null ? object.getBlock() : (iBlock = null);
        if (iClassProvider.isBlockStairs(iBlock) && !this.walkingDown) {
            thePlayer.setPosition(thePlayer.getPosX(), thePlayer.getPosY() + 0.5, thePlayer.getPosZ());
            double motion = StringsKt.equals(mode, "NCP", true) ? 1.4 : (StringsKt.equals(mode, "AAC3.1.0", true) ? 1.5 : (StringsKt.equals(mode, "AAC3.3.13", true) ? 1.2 : 1.0));
            IEntityPlayerSP iEntityPlayerSP = thePlayer;
            iEntityPlayerSP.setMotionX(iEntityPlayerSP.getMotionX() * motion);
            IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
            iEntityPlayerSP2.setMotionZ(iEntityPlayerSP2.getMotionZ() * motion);
        }
        WBlockPos motion = blockPos.down();
        iClassProvider = MinecraftInstance.classProvider;
        boolean $i$f$getBlock2 = false;
        Object object2 = MinecraftInstance.mc.getTheWorld();
        IBlock iBlock3 = object2 != null && (object2 = object2.getBlockState((WBlockPos)blockPos$iv)) != null ? object2.getBlock() : (iBlock = null);
        if (iClassProvider.isBlockStairs(iBlock)) {
            if (this.walkingDown) {
                if (StringsKt.equals(mode, "NCP", true)) {
                    thePlayer.setMotionY(-1.0);
                } else if (StringsKt.equals(mode, "AAC3.3.13", true)) {
                    IEntityPlayerSP iEntityPlayerSP = thePlayer;
                    iEntityPlayerSP.setMotionY(iEntityPlayerSP.getMotionY() - 0.014);
                }
                return;
            }
            double motion2 = StringsKt.equals(mode, "NCP", true) ? 1.3 : (StringsKt.equals(mode, "AAC3.1.0", true) ? 1.3 : (StringsKt.equals(mode, "AAC3.3.6", true) ? 1.48 : (StringsKt.equals(mode, "AAC3.3.13", true) ? 1.52 : 1.3)));
            IEntityPlayerSP iEntityPlayerSP = thePlayer;
            iEntityPlayerSP.setMotionX(iEntityPlayerSP.getMotionX() * motion2);
            IEntityPlayerSP iEntityPlayerSP3 = thePlayer;
            iEntityPlayerSP3.setMotionZ(iEntityPlayerSP3.getMotionZ() * motion2);
            this.canJump = true;
        } else if (StringsKt.startsWith(mode, "AAC", true) && this.canJump) {
            if (((Boolean)this.longJumpValue.get()).booleanValue()) {
                thePlayer.jump();
                IEntityPlayerSP iEntityPlayerSP = thePlayer;
                iEntityPlayerSP.setMotionX(iEntityPlayerSP.getMotionX() * 1.35);
                IEntityPlayerSP iEntityPlayerSP4 = thePlayer;
                iEntityPlayerSP4.setMotionZ(iEntityPlayerSP4.getMotionZ() * 1.35);
            }
            this.canJump = false;
        }
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.modeValue.get();
    }
}

