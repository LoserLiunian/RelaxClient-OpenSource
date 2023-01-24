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
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="NoWeb", description="Prevents you from getting slowed down in webs.", category=ModuleCategory.MOVEMENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/movement/NoWeb;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "tag", "", "getTag", "()Ljava/lang/String;", "usedTimer", "", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class NoWeb
extends Module {
    private final ListValue modeValue = new ListValue("Mode", new String[]{"None", "AAC", "LAAC", "Rewi", "Matrix", "Spartan", "AAC5"}, "None");
    private boolean usedTimer;

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (this.usedTimer) {
            MinecraftInstance.mc.getTimer().setTimerSpeed(1.0f);
            this.usedTimer = false;
        }
        if (!thePlayer.isInWeb()) {
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
            case "none": {
                thePlayer.setInWeb(false);
                break;
            }
            case "aac": {
                thePlayer.setJumpMovementFactor(0.59f);
                if (MinecraftInstance.mc.getGameSettings().getKeyBindSneak().isKeyDown()) break;
                thePlayer.setMotionY(0.0);
                break;
            }
            case "laac": {
                thePlayer.setJumpMovementFactor(thePlayer.getMovementInput().getMoveStrafe() != 0.0f ? 1.0f : 1.21f);
                if (!MinecraftInstance.mc.getGameSettings().getKeyBindSneak().isKeyDown()) {
                    thePlayer.setMotionY(0.0);
                }
                if (!thePlayer.getOnGround()) break;
                thePlayer.jump();
                break;
            }
            case "rewi": {
                thePlayer.setJumpMovementFactor(0.42f);
                if (!thePlayer.getOnGround()) break;
                thePlayer.jump();
                break;
            }
            case "spartan": {
                MovementUtils.strafe(0.27f);
                MinecraftInstance.mc.getTimer().setTimerSpeed(3.7f);
                if (!MinecraftInstance.mc.getGameSettings().getKeyBindSneak().isKeyDown()) {
                    IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iEntityPlayerSP2.setMotionY(0.0);
                }
                IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP3 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP3.getTicksExisted() % 2 == 0) {
                    MinecraftInstance.mc.getTimer().setTimerSpeed(1.7f);
                }
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP4.getTicksExisted() % 40 == 0) {
                    MinecraftInstance.mc.getTimer().setTimerSpeed(3.0f);
                }
                this.usedTimer = true;
                break;
            }
            case "matrix": {
                IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP5 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP5.setJumpMovementFactor(0.12425f);
                IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP6 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP6.setMotionY(-0.0125);
                if (MinecraftInstance.mc.getGameSettings().getKeyBindSneak().isKeyDown()) {
                    IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP7 == null) {
                        Intrinsics.throwNpe();
                    }
                    iEntityPlayerSP7.setMotionY(-0.1625);
                }
                IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP8 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP8.getTicksExisted() % 40 != 0) break;
                MinecraftInstance.mc.getTimer().setTimerSpeed(3.0f);
                this.usedTimer = true;
                break;
            }
            case "aac5": {
                IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP9 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP9.setJumpMovementFactor(0.42f);
                IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP10 == null) {
                    Intrinsics.throwNpe();
                }
                if (!iEntityPlayerSP10.getOnGround()) break;
                IEntityPlayerSP iEntityPlayerSP11 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP11 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP11.jump();
                break;
            }
        }
    }

    @Override
    @Nullable
    public String getTag() {
        return (String)this.modeValue.get();
    }
}

