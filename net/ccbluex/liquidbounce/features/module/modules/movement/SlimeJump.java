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
import net.ccbluex.liquidbounce.api.IClassProvider;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="SlimeJump", description="Allows you to to jump higher on slime blocks.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/SlimeJump;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "motionValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "onJump", "", "event", "Lnet/ccbluex/liquidbounce/event/JumpEvent;", "Relaxed"})
public final class SlimeJump
extends Module {
    private final FloatValue motionValue = new FloatValue("Motion", 0.42f, 0.2f, 1.0f);
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Set", "Add"}, "Add");

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @EventTarget
    public final void onJump(@NotNull JumpEvent event) {
        IBlock iBlock;
        void blockPos$iv;
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (MinecraftInstance.mc.getThePlayer() == null || MinecraftInstance.mc.getTheWorld() == null) return;
        Object object = thePlayer.getPosition().down();
        IClassProvider iClassProvider = MinecraftInstance.classProvider;
        boolean $i$f$getBlock = false;
        Object object2 = MinecraftInstance.mc.getTheWorld();
        IBlock iBlock2 = object2 != null && (object2 = object2.getBlockState((WBlockPos)blockPos$iv)) != null ? object2.getBlock() : (iBlock = null);
        if (!iClassProvider.isBlockSlime(iBlock)) return;
        event.cancelEvent();
        object = (String)this.modeValue.get();
        boolean bl = false;
        Object object3 = object;
        if (object3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string = ((String)object3).toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string, "(this as java.lang.String).toLowerCase()");
        object = string;
        switch (((String)object).hashCode()) {
            case 96417: {
                if (!((String)object).equals("add")) return;
                break;
            }
            case 113762: {
                if (!((String)object).equals("set")) return;
                thePlayer.setMotionY(((Number)this.motionValue.get()).floatValue());
                return;
            }
        }
        IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
        iEntityPlayerSP2.setMotionY(iEntityPlayerSP2.getMotionY() + ((Number)this.motionValue.get()).doubleValue());
        return;
    }
}

