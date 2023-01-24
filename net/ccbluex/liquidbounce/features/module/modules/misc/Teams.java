/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer;
import net.ccbluex.liquidbounce.api.minecraft.item.IItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemArmor;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.scoreboard.ITeam;
import net.ccbluex.liquidbounce.api.minecraft.util.IIChatComponent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.BoolValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Teams", description="\u9632\u6b62\u6740\u622e\u5149\u73af\u653b\u51fb\u961f\u53cb", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/misc/Teams;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "armorColorValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "colorValue", "gommeSWValue", "scoreboardValue", "isInYourTeam", "", "target", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "Relaxed"})
public final class Teams
extends Module {
    private final BoolValue scoreboardValue = new BoolValue("ScoreboardTeam", true);
    private final BoolValue colorValue = new BoolValue("Color", true);
    private final BoolValue gommeSWValue = new BoolValue("GommeSW", false);
    private final BoolValue armorColorValue = new BoolValue("ArmorColor", false);

    public final boolean isInYourTeam(@NotNull IEntityLivingBase target) {
        String clientName;
        String targetName;
        Intrinsics.checkParameterIsNotNull(target, "target");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (((Boolean)this.scoreboardValue.get()).booleanValue() && thePlayer.getTeam() != null && target.getTeam() != null) {
            ITeam iTeam = thePlayer.getTeam();
            if (iTeam == null) {
                Intrinsics.throwNpe();
            }
            ITeam iTeam2 = target.getTeam();
            if (iTeam2 == null) {
                Intrinsics.throwNpe();
            }
            if (iTeam.isSameTeam(iTeam2)) {
                return true;
            }
        }
        IIChatComponent displayName = thePlayer.getDisplayName();
        if (((Boolean)this.armorColorValue.get()).booleanValue()) {
            IEntityPlayer entityPlayer = target.asEntityPlayer();
            if (thePlayer.getInventory().getArmorInventory().get(3) != null && entityPlayer.getInventory().getArmorInventory().get(3) != null) {
                IItemStack myHead;
                IItemStack iItemStack = myHead = thePlayer.getInventory().getArmorInventory().get(3);
                if (iItemStack == null) {
                    Intrinsics.throwNpe();
                }
                IItem iItem = iItemStack.getItem();
                if (iItem == null) {
                    Intrinsics.throwNpe();
                }
                IItemArmor myItemArmor = iItem.asItemArmor();
                IItemStack entityHead = entityPlayer.getInventory().getArmorInventory().get(3);
                IItem iItem2 = myHead.getItem();
                if (iItem2 == null) {
                    Intrinsics.throwNpe();
                }
                IItemArmor entityItemArmor = iItem2.asItemArmor();
                int n = myItemArmor.getColor(myHead);
                IItemStack iItemStack2 = entityHead;
                if (iItemStack2 == null) {
                    Intrinsics.throwNpe();
                }
                if (n == entityItemArmor.getColor(iItemStack2)) {
                    return true;
                }
            }
        }
        if (((Boolean)this.gommeSWValue.get()).booleanValue() && displayName != null && target.getDisplayName() != null) {
            IIChatComponent iIChatComponent = target.getDisplayName();
            if (iIChatComponent == null) {
                Intrinsics.throwNpe();
            }
            targetName = StringsKt.replace$default(iIChatComponent.getFormattedText(), "\u00a7r", "", false, 4, null);
            clientName = StringsKt.replace$default(displayName.getFormattedText(), "\u00a7r", "", false, 4, null);
            if (StringsKt.startsWith$default(targetName, "T", false, 2, null) && StringsKt.startsWith$default(clientName, "T", false, 2, null)) {
                char c = targetName.charAt(1);
                boolean bl = false;
                if (Character.isDigit(c)) {
                    c = clientName.charAt(1);
                    bl = false;
                    if (Character.isDigit(c)) {
                        return targetName.charAt(1) == clientName.charAt(1);
                    }
                }
            }
        }
        if (((Boolean)this.colorValue.get()).booleanValue() && displayName != null && target.getDisplayName() != null) {
            IIChatComponent iIChatComponent = target.getDisplayName();
            if (iIChatComponent == null) {
                Intrinsics.throwNpe();
            }
            targetName = StringsKt.replace$default(iIChatComponent.getFormattedText(), "\u00a7r", "", false, 4, null);
            clientName = StringsKt.replace$default(displayName.getFormattedText(), "\u00a7r", "", false, 4, null);
            return StringsKt.startsWith$default(targetName, "" + '\u00a7' + clientName.charAt(1), false, 2, null);
        }
        return false;
    }
}

