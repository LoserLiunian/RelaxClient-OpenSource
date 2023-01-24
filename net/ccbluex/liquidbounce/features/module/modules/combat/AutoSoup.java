/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.IClassProvider;
import net.ccbluex.liquidbounce.api.enums.EnumFacingType;
import net.ccbluex.liquidbounce.api.enums.ItemType;
import net.ccbluex.liquidbounce.api.enums.WEnumHand;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketEntityAction;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerDigging;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.utils.InventoryUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="AutoSoup", description="Makes you automatically eat soup whenever your health is low.", category=ModuleCategory.COMBAT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/AutoSoup;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "bowlValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "delayValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "healthValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "openInventoryValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "simulateInventoryValue", "tag", "", "getTag", "()Ljava/lang/String;", "timer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class AutoSoup
extends Module {
    private final FloatValue healthValue = new FloatValue("Health", 15.0f, 0.0f, 20.0f);
    private final IntegerValue delayValue = new IntegerValue("Delay", 150, 0, 500);
    private final BoolValue openInventoryValue = new BoolValue("OpenInv", false);
    private final BoolValue simulateInventoryValue = new BoolValue("SimulateInventory", true);
    private final ListValue bowlValue = new ListValue("Bowl", new String[]{"Drop", "Move", "Stay"}, "Drop");
    private final MSTimer timer = new MSTimer();

    @Override
    @NotNull
    public String getTag() {
        return String.valueOf(((Number)this.healthValue.get()).floatValue());
    }

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onUpdate(@Nullable UpdateEvent event) {
        int soupInInventory;
        IPacket iPacket;
        boolean $i$f$createOpenInventoryPacket;
        IINetHandlerPlayClient iINetHandlerPlayClient;
        boolean openInventory;
        if (!this.timer.hasTimePassed(((Number)this.delayValue.get()).intValue())) {
            return;
        }
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        int soupInHotbar = InventoryUtils.findItem(36, 45, MinecraftInstance.classProvider.getItemEnum(ItemType.MUSHROOM_STEW));
        if (thePlayer.getHealth() <= ((Number)this.healthValue.get()).floatValue() && soupInHotbar != -1) {
            void hand$iv;
            MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketHeldItemChange(soupInHotbar - 36));
            IItemStack iItemStack = thePlayer.getInventory().getStackInSlot(soupInHotbar);
            WEnumHand wEnumHand = WEnumHand.MAIN_HAND;
            IINetHandlerPlayClient iINetHandlerPlayClient2 = MinecraftInstance.mc.getNetHandler();
            boolean $i$f$createUseItemPacket = false;
            IPacket iPacket2 = WrapperImpl.INSTANCE.getClassProvider().createCPacketTryUseItem((WEnumHand)hand$iv);
            iINetHandlerPlayClient2.addToSendQueue(iPacket2);
            if (StringsKt.equals((String)this.bowlValue.get(), "Drop", true)) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.DROP_ITEM, WBlockPos.Companion.getORIGIN(), MinecraftInstance.classProvider.getEnumFacing(EnumFacingType.DOWN)));
            }
            MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketHeldItemChange(thePlayer.getInventory().getCurrentItem()));
            this.timer.reset();
            return;
        }
        int bowlInHotbar = InventoryUtils.findItem(36, 45, MinecraftInstance.classProvider.getItemEnum(ItemType.BOWL));
        if (StringsKt.equals((String)this.bowlValue.get(), "Move", true) && bowlInHotbar != -1) {
            if (((Boolean)this.openInventoryValue.get()).booleanValue() && !MinecraftInstance.classProvider.isGuiInventory(MinecraftInstance.mc.getCurrentScreen())) {
                return;
            }
            boolean bowlMovable = false;
            int $i$f$createUseItemPacket = 9;
            int n = 36;
            while ($i$f$createUseItemPacket <= n) {
                void i;
                IItemStack itemStack = thePlayer.getInventory().getStackInSlot((int)i);
                if (itemStack == null) {
                    bowlMovable = true;
                    break;
                }
                if (Intrinsics.areEqual(itemStack.getItem(), MinecraftInstance.classProvider.getItemEnum(ItemType.BOWL)) && itemStack.getStackSize() < 64) {
                    bowlMovable = true;
                    break;
                }
                ++i;
            }
            if (bowlMovable) {
                boolean bl = openInventory = !MinecraftInstance.classProvider.isGuiInventory(MinecraftInstance.mc.getCurrentScreen()) && (Boolean)this.simulateInventoryValue.get() != false;
                if (openInventory) {
                    iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                    $i$f$createOpenInventoryPacket = false;
                    IClassProvider iClassProvider = WrapperImpl.INSTANCE.getClassProvider();
                    IEntityPlayerSP iEntityPlayerSP2 = LiquidBounce.INSTANCE.getWrapper().getMinecraft().getThePlayer();
                    if (iEntityPlayerSP2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iPacket = iClassProvider.createCPacketEntityAction(iEntityPlayerSP2, ICPacketEntityAction.WAction.OPEN_INVENTORY);
                    iINetHandlerPlayClient.addToSendQueue(iPacket);
                }
                MinecraftInstance.mc.getPlayerController().windowClick(0, bowlInHotbar, 0, 1, thePlayer);
            }
        }
        if ((soupInInventory = InventoryUtils.findItem(9, 36, MinecraftInstance.classProvider.getItemEnum(ItemType.MUSHROOM_STEW))) != -1 && InventoryUtils.hasSpaceHotbar()) {
            if (((Boolean)this.openInventoryValue.get()).booleanValue() && !MinecraftInstance.classProvider.isGuiInventory(MinecraftInstance.mc.getCurrentScreen())) {
                return;
            }
            boolean bl = openInventory = !MinecraftInstance.classProvider.isGuiInventory(MinecraftInstance.mc.getCurrentScreen()) && (Boolean)this.simulateInventoryValue.get() != false;
            if (openInventory) {
                iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                $i$f$createOpenInventoryPacket = false;
                IClassProvider iClassProvider = WrapperImpl.INSTANCE.getClassProvider();
                IEntityPlayerSP iEntityPlayerSP3 = LiquidBounce.INSTANCE.getWrapper().getMinecraft().getThePlayer();
                if (iEntityPlayerSP3 == null) {
                    Intrinsics.throwNpe();
                }
                iPacket = iClassProvider.createCPacketEntityAction(iEntityPlayerSP3, ICPacketEntityAction.WAction.OPEN_INVENTORY);
                iINetHandlerPlayClient.addToSendQueue(iPacket);
            }
            MinecraftInstance.mc.getPlayerController().windowClick(0, soupInInventory, 0, 1, thePlayer);
            if (openInventory) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketCloseWindow());
            }
            this.timer.reset();
        }
    }
}

