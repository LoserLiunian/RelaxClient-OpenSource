/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityTNTPrimed;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.item.IItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MotionEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="TNTBlock", description="Automatically blocks with your sword when TNT around you explodes.", category=ModuleCategory.COMBAT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/TNTBlock;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "autoSwordValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "blocked", "", "fuseValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "rangeValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "onMotionUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/MotionEvent;", "Relaxed"})
public final class TNTBlock
extends Module {
    private final IntegerValue fuseValue = new IntegerValue("Fuse", 10, 0, 80);
    private final FloatValue rangeValue = new FloatValue("Range", 9.0f, 1.0f, 20.0f);
    private final BoolValue autoSwordValue = new BoolValue("AutoSword", true);
    private boolean blocked;

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onMotionUpdate(@Nullable MotionEvent event) {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            return;
        }
        IWorldClient theWorld = iWorldClient;
        for (IEntity entity : theWorld.getLoadedEntityList()) {
            IEntityTNTPrimed tntPrimed;
            if (!MinecraftInstance.classProvider.isEntityTNTPrimed(entity) || !(thePlayer.getDistanceToEntity(entity) <= ((Number)this.rangeValue.get()).floatValue()) || (tntPrimed = entity.asEntityTNTPrimed()).getFuse() > ((Number)this.fuseValue.get()).intValue()) continue;
            if (((Boolean)this.autoSwordValue.get()).booleanValue()) {
                int slot = -1;
                float bestDamage = 1.0f;
                int n = 0;
                int n2 = 8;
                while (n <= n2) {
                    void i;
                    IItemStack itemStack = thePlayer.getInventory().getStackInSlot((int)i);
                    if (itemStack != null && MinecraftInstance.classProvider.isItemSword(itemStack.getItem())) {
                        float itemDamage;
                        IItem iItem = itemStack.getItem();
                        if (iItem == null) {
                            Intrinsics.throwNpe();
                        }
                        if ((itemDamage = iItem.asItemSword().getDamageVsEntity() + 4.0f) > bestDamage) {
                            bestDamage = itemDamage;
                            slot = i;
                        }
                    }
                    ++i;
                }
                if (slot != -1 && slot != thePlayer.getInventory().getCurrentItem()) {
                    thePlayer.getInventory().setCurrentItem(slot);
                    MinecraftInstance.mc.getPlayerController().updateController();
                }
            }
            if (thePlayer.getHeldItem() != null) {
                IItemStack iItemStack = thePlayer.getHeldItem();
                if (iItemStack == null) {
                    Intrinsics.throwNpe();
                }
                if (MinecraftInstance.classProvider.isItemSword(iItemStack.getItem())) {
                    MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().setPressed(true);
                    this.blocked = true;
                }
            }
            return;
        }
        if (this.blocked && !MinecraftInstance.mc.getGameSettings().isKeyDown(MinecraftInstance.mc.getGameSettings().getKeyBindUseItem())) {
            MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().setPressed(false);
            this.blocked = false;
        }
    }
}

