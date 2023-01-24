/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.movement;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.IClassProvider;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="AirLadder", description="Allows you to climb up ladders/vines without touching them.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/AirLadder;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class AirLadder
extends Module {
    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        block5: {
            IEntityPlayerSP thePlayer;
            block4: {
                IBlock iBlock;
                WBlockPos blockPos$iv;
                Intrinsics.checkParameterIsNotNull(event, "event");
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    return;
                }
                thePlayer = iEntityPlayerSP;
                WBlockPos wBlockPos = new WBlockPos(thePlayer.getPosX(), thePlayer.getPosY() + 1.0, thePlayer.getPosZ());
                IClassProvider iClassProvider = MinecraftInstance.classProvider;
                boolean $i$f$getBlock = false;
                Object object = MinecraftInstance.mc.getTheWorld();
                IBlock iBlock2 = object != null && (object = object.getBlockState(blockPos$iv)) != null ? object.getBlock() : (iBlock = null);
                if (iClassProvider.isBlockLadder(iBlock) && thePlayer.isCollidedHorizontally()) break block4;
                blockPos$iv = new WBlockPos(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ());
                iClassProvider = MinecraftInstance.classProvider;
                $i$f$getBlock = false;
                Object object2 = MinecraftInstance.mc.getTheWorld();
                IBlock iBlock3 = object2 != null && (object2 = object2.getBlockState(blockPos$iv)) != null ? object2.getBlock() : (iBlock = null);
                if (iClassProvider.isBlockVine(iBlock)) break block4;
                blockPos$iv = new WBlockPos(thePlayer.getPosX(), thePlayer.getPosY() + 1.0, thePlayer.getPosZ());
                iClassProvider = MinecraftInstance.classProvider;
                $i$f$getBlock = false;
                Object object3 = MinecraftInstance.mc.getTheWorld();
                IBlock iBlock4 = object3 != null && (object3 = object3.getBlockState(blockPos$iv)) != null ? object3.getBlock() : (iBlock = null);
                if (!iClassProvider.isBlockVine(iBlock)) break block5;
            }
            thePlayer.setMotionY(0.15);
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
        }
    }
}

