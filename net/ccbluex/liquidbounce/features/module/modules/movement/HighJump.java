/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
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
import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="HighJump", description="Allows you to jump higher.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0012H\u0007J\u0012\u0010\u0013\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0015"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/HighJump;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "glassValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "heightValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "tag", "", "getTag", "()Ljava/lang/String;", "onJump", "", "event", "Lnet/ccbluex/liquidbounce/event/JumpEvent;", "onMove", "Lnet/ccbluex/liquidbounce/event/MoveEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class HighJump
extends Module {
    private final FloatValue heightValue = new FloatValue("Height", 2.0f, 1.1f, 5.0f);
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Vanilla", "Damage", "AACv3", "DAC", "Mineplex"}, "Vanilla");
    private final BoolValue glassValue = new BoolValue("OnlyGlassPane", false);

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onUpdate(@Nullable UpdateEvent event) {
        Object object;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (((Boolean)this.glassValue.get()).booleanValue()) {
            IBlock iBlock;
            void blockPos$iv;
            object = new WBlockPos(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ());
            IClassProvider iClassProvider = MinecraftInstance.classProvider;
            boolean $i$f$getBlock = false;
            Object object2 = MinecraftInstance.mc.getTheWorld();
            IBlock iBlock2 = object2 != null && (object2 = object2.getBlockState((WBlockPos)blockPos$iv)) != null ? object2.getBlock() : (iBlock = null);
            if (!iClassProvider.isBlockPane(iBlock)) {
                return;
            }
        }
        object = (String)this.modeValue.get();
        boolean bl = false;
        String string = object;
        if (string == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.String).toLowerCase()");
        switch (string2) {
            case "damage": {
                if (thePlayer.getHurtTime() <= 0 || !thePlayer.getOnGround()) break;
                IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
                iEntityPlayerSP2.setMotionY(iEntityPlayerSP2.getMotionY() + (double)(0.42f * ((Number)this.heightValue.get()).floatValue()));
                break;
            }
            case "aacv3": {
                if (thePlayer.getOnGround()) break;
                IEntityPlayerSP iEntityPlayerSP3 = thePlayer;
                iEntityPlayerSP3.setMotionY(iEntityPlayerSP3.getMotionY() + 0.059);
                break;
            }
            case "dac": {
                if (thePlayer.getOnGround()) break;
                IEntityPlayerSP iEntityPlayerSP4 = thePlayer;
                iEntityPlayerSP4.setMotionY(iEntityPlayerSP4.getMotionY() + 0.049999);
                break;
            }
            case "mineplex": {
                if (thePlayer.getOnGround()) break;
                MovementUtils.strafe(0.35f);
                break;
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onMove(@Nullable MoveEvent event) {
        String string;
        Object object;
        Object object2;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (((Boolean)this.glassValue.get()).booleanValue()) {
            void blockPos$iv;
            object2 = new WBlockPos(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ());
            object = MinecraftInstance.classProvider;
            boolean $i$f$getBlock = false;
            Object object3 = MinecraftInstance.mc.getTheWorld();
            Object object4 = object3 != null && (object3 = object3.getBlockState((WBlockPos)blockPos$iv)) != null ? object3.getBlock() : (string = null);
            if (!object.isBlockPane(string)) {
                return;
            }
        }
        if (!thePlayer.getOnGround()) {
            object2 = (String)this.modeValue.get();
            object = "mineplex";
            boolean bl = false;
            Object object5 = object2;
            if (object5 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string2 = ((String)object5).toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.String).toLowerCase()");
            string = string2;
            if (Intrinsics.areEqual(object, string)) {
                IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
                iEntityPlayerSP2.setMotionY(iEntityPlayerSP2.getMotionY() + (thePlayer.getFallDistance() == 0.0f ? 0.0499 : 0.05));
            }
        }
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @EventTarget
    public final void onJump(@NotNull JumpEvent event) {
        Object object;
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (((Boolean)this.glassValue.get()).booleanValue()) {
            IBlock iBlock;
            void blockPos$iv;
            object = new WBlockPos(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ());
            IClassProvider iClassProvider = MinecraftInstance.classProvider;
            boolean $i$f$getBlock = false;
            Object object2 = MinecraftInstance.mc.getTheWorld();
            IBlock iBlock2 = object2 != null && (object2 = object2.getBlockState((WBlockPos)blockPos$iv)) != null ? object2.getBlock() : (iBlock = null);
            if (!iClassProvider.isBlockPane(iBlock)) {
                return;
            }
        }
        object = (String)this.modeValue.get();
        boolean bl = false;
        String string = object;
        if (string == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.String).toLowerCase()");
        object = string2;
        switch (((String)object).hashCode()) {
            case -1362669950: {
                if (!((String)object).equals("mineplex")) return;
                break;
            }
            case 233102203: {
                if (!((String)object).equals("vanilla")) return;
                event.setMotion(event.getMotion() * ((Number)this.heightValue.get()).floatValue());
                return;
            }
        }
        event.setMotion(0.47f);
        return;
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.modeValue.get();
    }
}

