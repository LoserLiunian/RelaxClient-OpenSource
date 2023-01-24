/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Mouse
 */
package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.settings.IKeyBinding;
import net.ccbluex.liquidbounce.api.minecraft.entity.player.IInventoryPlayer;
import net.ccbluex.liquidbounce.api.minecraft.item.IItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.potion.PotionType;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MotionEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.timer.TimeUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;

@ModuleInfo(name="AutoHead", description="faq", category=ModuleCategory.COMBAT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0012\u0010\u001a\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/AutoHead;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "delay", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "doingStuff", "", "getDoingStuff", "()Z", "setDoingStuff", "(Z)V", "eatApples", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "eatHeads", "eatingApple", "health", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "switched", "", "timer", "Lnet/ccbluex/liquidbounce/utils/timer/TimeUtils;", "getItemFromHotbar", "id", "onDisable", "", "onEnable", "onUpdate", "event", "Lnet/ccbluex/liquidbounce/event/MotionEvent;", "repairItemPress", "repairItemSwitch", "Relaxed"})
public final class AutoHead
extends Module {
    private boolean eatingApple;
    private int switched = -1;
    private boolean doingStuff;
    private final TimeUtils timer = new TimeUtils();
    private final BoolValue eatHeads = new BoolValue("EatHead", true);
    private final BoolValue eatApples = new BoolValue("EatApples", true);
    private final FloatValue health = new FloatValue("Health", 10.0f, 1.0f, 20.0f);
    private final IntegerValue delay = new IntegerValue("Delay", 750, 100, 2000);

    public final boolean getDoingStuff() {
        return this.doingStuff;
    }

    public final void setDoingStuff(boolean bl) {
        this.doingStuff = bl;
    }

    @Override
    public void onEnable() {
        this.eatingApple = this.doingStuff = false;
        this.switched = -1;
        this.timer.reset();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.doingStuff = false;
        if (this.eatingApple) {
            this.repairItemPress();
            this.repairItemSwitch();
        }
        super.onDisable();
    }

    private final void repairItemPress() {
        IKeyBinding keyBindUseItem;
        if (MinecraftInstance.mc.getGameSettings() != null && (keyBindUseItem = MinecraftInstance.mc.getGameSettings().getKeyBindUseItem()) != null) {
            keyBindUseItem.setPressed(false);
        }
    }

    /*
     * Unable to fully structure code
     */
    @EventTarget
    public final void onUpdate(@Nullable MotionEvent event) {
        block20: {
            block22: {
                block21: {
                    if (MinecraftInstance.mc.getThePlayer() == null) {
                        return;
                    }
                    v0 = MinecraftInstance.mc.getThePlayer();
                    if (v0 == null) {
                        Intrinsics.throwNpe();
                    }
                    v1 = v0.getInventory();
                    if (v1 == null) {
                        return;
                    }
                    inventory = v1;
                    this.doingStuff = false;
                    if (Mouse.isButtonDown((int)0) || Mouse.isButtonDown((int)1)) break block20;
                    useItem = MinecraftInstance.mc.getGameSettings().getKeyBindUseItem();
                    if (!this.timer.hasReached(((Number)this.delay.get()).intValue())) {
                        this.eatingApple = false;
                        this.repairItemPress();
                        this.repairItemSwitch();
                        return;
                    }
                    v2 = MinecraftInstance.mc.getThePlayer();
                    if (v2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (v2.getCapabilities().isCreativeMode()) break block21;
                    v3 = MinecraftInstance.mc.getThePlayer();
                    if (v3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (v3.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.REGENERATION))) break block21;
                    v4 = MinecraftInstance.mc.getThePlayer();
                    if (v4 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!(v4.getHealth() >= ((Number)this.health.get()).floatValue())) break block22;
                }
                this.timer.reset();
                if (this.eatingApple) {
                    this.eatingApple = false;
                    this.repairItemPress();
                    this.repairItemSwitch();
                }
                return;
            }
            var4_4 = false;
            var5_5 = true;
            while (var4_4 <= var5_5) {
                block24: {
                    block23: {
                        v5 = doEatHeads = i != false;
                        if (!doEatHeads) break block23;
                        if (((Boolean)this.eatHeads.get()).booleanValue()) ** GOTO lbl-1000
                        break block24;
                    }
                    if (!((Boolean)this.eatApples.get()).booleanValue()) {
                        this.eatingApple = false;
                        this.repairItemPress();
                        this.repairItemSwitch();
                    } else lbl-1000:
                    // 2 sources

                    {
                        slot = 0;
                        v6 = slot = doEatHeads != false ? this.getItemFromHotbar(397) : this.getItemFromHotbar(322);
                        if (slot != -1) {
                            tempSlot = inventory.getCurrentItem();
                            this.doingStuff = true;
                            if (doEatHeads) {
                                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketHeldItemChange(slot));
                                v7 = MinecraftInstance.mc.getNetHandler();
                                v8 = MinecraftInstance.mc.getThePlayer();
                                if (v8 == null) {
                                    Intrinsics.throwNpe();
                                }
                                v7.addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerBlockPlacement(v8.getInventory().getCurrentItemInHand()));
                                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketHeldItemChange(tempSlot));
                                this.timer.reset();
                            } else {
                                inventory.setCurrentItem(slot);
                                useItem.setPressed(true);
                                if (!this.eatingApple) {
                                    this.eatingApple = true;
                                    this.switched = tempSlot;
                                }
                            }
                        }
                    }
                }
                ++i;
            }
        }
    }

    private final void repairItemSwitch() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP p = iEntityPlayerSP;
        IInventoryPlayer iInventoryPlayer = p.getInventory();
        if (iInventoryPlayer == null) {
            return;
        }
        IInventoryPlayer inventory = iInventoryPlayer;
        int switched = this.switched;
        if (switched == -1) {
            return;
        }
        inventory.setCurrentItem(switched);
        this.switched = switched = -1;
    }

    /*
     * WARNING - void declaration
     */
    private final int getItemFromHotbar(int id) {
        int n = 0;
        int n2 = 8;
        while (n <= n2) {
            void i;
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP.getInventory().getMainInventory().get((int)i) != null) {
                IItem item;
                IItemStack a;
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                IItemStack iItemStack = a = iEntityPlayerSP2.getInventory().getMainInventory().get((int)i);
                if (iItemStack == null) {
                    Intrinsics.throwNpe();
                }
                IItem iItem = item = iItemStack.getItem();
                if (iItem == null) {
                    Intrinsics.throwNpe();
                }
                if (Intrinsics.areEqual(MinecraftInstance.functions.getIdFromItem(iItem), (Object)id)) {
                    return (int)i;
                }
            }
            ++i;
        }
        return -1;
    }
}

