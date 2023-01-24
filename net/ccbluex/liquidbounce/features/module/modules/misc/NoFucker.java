/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.IExtractedFunctions;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.world.Fucker;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import net.ccbluex.liquidbounce.value.BlockValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="NoFucker", description="CNM", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/misc/NoFucker;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "blockValue", "Lnet/ccbluex/liquidbounce/value/BlockValue;", "pos", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "targetId", "", "getTargetId", "()I", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class NoFucker
extends Module {
    private final BlockValue blockValue = new BlockValue("Block", 26);
    private final int targetId = ((Number)this.blockValue.get()).intValue();
    private WBlockPos pos;

    public final int getTargetId() {
        return this.targetId;
    }

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        block7: {
            block6: {
                void blockPos$iv;
                IBlock iBlock;
                Intrinsics.checkParameterIsNotNull(event, "event");
                if (this.pos == null) break block6;
                WBlockPos wBlockPos = this.pos;
                if (wBlockPos == null) {
                    Intrinsics.throwNpe();
                }
                WBlockPos wBlockPos2 = wBlockPos;
                IExtractedFunctions iExtractedFunctions = MinecraftInstance.functions;
                boolean $i$f$getBlock = false;
                Object object = MinecraftInstance.mc.getTheWorld();
                IBlock iBlock2 = iBlock = object != null && (object = object.getBlockState((WBlockPos)blockPos$iv)) != null ? object.getBlock() : null;
                if (iBlock2 == null) {
                    Intrinsics.throwNpe();
                }
                if (iExtractedFunctions.getIdFromBlock(iBlock2) != this.targetId) break block6;
                WBlockPos wBlockPos3 = Fucker.INSTANCE.getPos();
                if (wBlockPos3 == null) {
                    Intrinsics.throwNpe();
                }
                if (!(BlockUtils.getCenterDistance(wBlockPos3) > (double)7)) break block7;
            }
            this.pos = Fucker.INSTANCE.find(this.targetId);
        }
    }
}

