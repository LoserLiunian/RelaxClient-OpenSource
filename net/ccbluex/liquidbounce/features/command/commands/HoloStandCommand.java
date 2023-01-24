/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.command.commands;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.enums.ItemType;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.nbt.INBTTagCompound;
import net.ccbluex.liquidbounce.api.minecraft.nbt.INBTTagList;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.StringUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2={"Lnet/ccbluex/liquidbounce/features/command/commands/HoloStandCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "Relaxed"})
public final class HoloStandCommand
extends Command {
    @Override
    public void execute(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        if (args2.length > 4) {
            if (MinecraftInstance.mc.getPlayerController().isNotCreative()) {
                this.chat("\u00a7c\u00a7lError: \u00a73You need to be in creative mode.");
                return;
            }
            try {
                String string = args2[1];
                boolean bl = false;
                double x = Double.parseDouble(string);
                String string2 = args2[2];
                boolean bl2 = false;
                double y = Double.parseDouble(string2);
                String string3 = args2[3];
                boolean bl3 = false;
                double z = Double.parseDouble(string3);
                String message = StringUtils.toCompleteString(args2, 4);
                IItemStack itemStack = MinecraftInstance.classProvider.createItemStack(MinecraftInstance.classProvider.getItemEnum(ItemType.ARMOR_STAND));
                INBTTagCompound base = MinecraftInstance.classProvider.createNBTTagCompound();
                INBTTagCompound entityTag = MinecraftInstance.classProvider.createNBTTagCompound();
                entityTag.setInteger("Invisible", 1);
                String string4 = message;
                Intrinsics.checkExpressionValueIsNotNull(string4, "message");
                entityTag.setString("CustomName", string4);
                entityTag.setInteger("CustomNameVisible", 1);
                entityTag.setInteger("NoGravity", 1);
                INBTTagList position = MinecraftInstance.classProvider.createNBTTagList();
                position.appendTag(MinecraftInstance.classProvider.createNBTTagDouble(x));
                position.appendTag(MinecraftInstance.classProvider.createNBTTagDouble(y));
                position.appendTag(MinecraftInstance.classProvider.createNBTTagDouble(z));
                entityTag.setTag("Pos", position);
                base.setTag("EntityTag", entityTag);
                itemStack.setTagCompound(base);
                itemStack.setStackDisplayName("\u00a7c\u00a7lHolo\u00a7eStand");
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketCreativeInventoryAction(36, itemStack));
                this.chat("The HoloStand was successfully added to your inventory.");
            }
            catch (NumberFormatException exception) {
                this.chatSyntaxError();
            }
            return;
        }
        this.chatSyntax("holostand <x> <y> <z> <message...>");
    }

    public HoloStandCommand() {
        super("holostand", new String[0]);
    }
}

