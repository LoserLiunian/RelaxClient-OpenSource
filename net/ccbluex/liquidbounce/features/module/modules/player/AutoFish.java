/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.player;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.MinecraftVersion;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="AutoFish", description="Automatically catches fish when using a rod.", category=ModuleCategory.PLAYER, supportedVersions={MinecraftVersion.MC_1_8})
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/player/AutoFish;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "rodOutTimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class AutoFish
extends Module {
    private final MSTimer rodOutTimer = new MSTimer();

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        IEntityPlayerSP thePlayer;
        block6: {
            block5: {
                Intrinsics.checkParameterIsNotNull(event, "event");
                IEntityPlayerSP iEntityPlayerSP = thePlayer = MinecraftInstance.mc.getThePlayer();
                if ((iEntityPlayerSP != null ? iEntityPlayerSP.getHeldItem() : null) == null) break block5;
                IItemStack iItemStack = thePlayer.getHeldItem();
                if (iItemStack == null) {
                    Intrinsics.throwNpe();
                }
                if (MinecraftInstance.classProvider.isItemFishingRod(iItemStack.getItem())) break block6;
            }
            return;
        }
        IEntity fishEntity = thePlayer.getFishEntity();
        if (this.rodOutTimer.hasTimePassed(500L) && fishEntity == null || fishEntity != null && fishEntity.getMotionX() == 0.0 && fishEntity.getMotionZ() == 0.0 && fishEntity.getMotionY() != 0.0) {
            MinecraftInstance.mc.rightClickMouse();
            this.rodOutTimer.reset();
        }
    }
}

