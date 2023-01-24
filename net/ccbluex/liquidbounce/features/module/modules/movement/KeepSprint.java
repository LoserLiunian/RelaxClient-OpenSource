/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.movement;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.util.IMovingObjectPosition;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3;
import net.ccbluex.liquidbounce.event.AttackEvent;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.injection.backend.PacketImpl;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.network.play.client.CPacketEntityAction;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="KeepSprint", category=ModuleCategory.MOVEMENT, description="das")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0012"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/KeepSprint;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "motionValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "tag", "", "getTag", "()Ljava/lang/String;", "onAttack", "", "attackEvent", "Lnet/ccbluex/liquidbounce/event/AttackEvent;", "onPacket", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "Relaxed"})
public final class KeepSprint
extends Module {
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Vanilla", "Motion"}, "Vanilla");
    private final FloatValue motionValue = new FloatValue("SlowDown", 100.0f, 20.0f, 100.0f);

    @EventTarget
    public final void onAttack(@NotNull AttackEvent attackEvent) {
        IEntityLivingBase entity;
        Intrinsics.checkParameterIsNotNull(attackEvent, "attackEvent");
        IEntityLivingBase iEntityLivingBase = entity = attackEvent.getTargetEntity() instanceof IEntityLivingBase ? (IEntityLivingBase)attackEvent.getTargetEntity() : null;
        if (((String)this.modeValue.get()).equals("Vanilla")) {
            return;
        }
        if (entity != null) {
            double dist = 0.0;
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (!iEntityPlayerSP.getCapabilities().isCreativeMode()) {
                IMovingObjectPosition iMovingObjectPosition = MinecraftInstance.mc.getObjectMouseOver();
                if (iMovingObjectPosition == null) {
                    Intrinsics.throwNpe();
                }
                WVec3 wVec3 = iMovingObjectPosition.getHitVec();
                IEntity iEntity = MinecraftInstance.mc.getRenderViewEntity();
                if (iEntity == null) {
                    Intrinsics.throwNpe();
                }
                dist = wVec3.distanceTo(iEntity.getPositionEyes(1.0f));
                double val = 0.0;
                val = dist > 3.0 ? (100.0 - ((Number)this.motionValue.get()).doubleValue()) / 100.0 : 0.6;
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP2.setMotionX(iEntityPlayerSP2.getMotionX() * val);
                IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP3 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP3.setMotionZ(iEntityPlayerSP3.getMotionZ() * val);
            } else {
                dist = (100.0 - ((Number)this.motionValue.get()).doubleValue()) / 100.0;
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP4.setMotionX(iEntityPlayerSP4.getMotionX() * dist);
                IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP5 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP5.setMotionZ(iEntityPlayerSP5.getMotionZ() * dist);
            }
        }
    }

    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IPacket $this$unwrap$iv = event.getPacket();
        boolean $i$f$unwrap = false;
        IPacket iPacket = $this$unwrap$iv;
        if (iPacket == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.injection.backend.PacketImpl<*>");
        }
        Object packet = ((PacketImpl)iPacket).getWrapped();
        if (!((String)this.modeValue.get()).equals("Vanilla")) {
            return;
        }
        if (packet instanceof CPacketEntityAction && ((CPacketEntityAction)packet).func_180764_b() == CPacketEntityAction.Action.STOP_SPRINTING) {
            event.cancelEvent();
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    @NotNull
    public String getTag() {
        block5: {
            var1_1 = (String)this.modeValue.get();
            v0 = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(v0, "Locale.getDefault()");
            var2_2 = v0;
            var3_3 = false;
            v1 = var1_1;
            if (v1 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            v2 = v1.toLowerCase(var2_2);
            Intrinsics.checkExpressionValueIsNotNull(v2, "(this as java.lang.String).toLowerCase(locale)");
            var1_1 = v2;
            switch (var1_1.hashCode()) {
                case -1068318794: {
                    if (!var1_1.equals("motion")) ** break;
                    break;
                }
                case 233102203: {
                    if (!var1_1.equals("vanilla")) ** break;
                    v3 = "Vanilla";
                    break block5;
                }
            }
            v3 = "% " + ((Number)this.motionValue.get()).floatValue();
            break block5;
            v3 = (String)this.modeValue.get();
        }
        return v3;
    }
}

