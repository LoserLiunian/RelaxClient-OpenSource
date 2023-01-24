/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Mouse
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.util.IMovingObjectPosition;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render2DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.file.configs.FriendsConfig;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;

@ModuleInfo(name="MidClick", description="Allows you to add a player as a friend by right clicking him.", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/misc/MidClick;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "wasDown", "", "onRender", "", "event", "Lnet/ccbluex/liquidbounce/event/Render2DEvent;", "Relaxed"})
public final class MidClick
extends Module {
    private boolean wasDown;

    @EventTarget
    public final void onRender(@Nullable Render2DEvent event) {
        if (MinecraftInstance.mc.getCurrentScreen() != null) {
            return;
        }
        if (!this.wasDown && Mouse.isButtonDown((int)2)) {
            IEntity entity;
            IMovingObjectPosition iMovingObjectPosition = MinecraftInstance.mc.getObjectMouseOver();
            if (iMovingObjectPosition == null) {
                Intrinsics.throwNpe();
            }
            if (MinecraftInstance.classProvider.isEntityPlayer(entity = iMovingObjectPosition.getEntityHit())) {
                String playerName;
                FriendsConfig friendsConfig;
                IEntity iEntity = entity;
                if (iEntity == null) {
                    Intrinsics.throwNpe();
                }
                if (!(friendsConfig = LiquidBounce.INSTANCE.getFileManager().friendsConfig).isFriend(playerName = ColorUtils.stripColor(iEntity.getName()))) {
                    friendsConfig.addFriend(playerName);
                    LiquidBounce.INSTANCE.getFileManager().saveConfig(friendsConfig);
                    ClientUtils.displayChatMessage("\u00a7a\u00a7l" + playerName + "\u00a7c was added to your friends.");
                } else {
                    friendsConfig.removeFriend(playerName);
                    LiquidBounce.INSTANCE.getFileManager().saveConfig(friendsConfig);
                    ClientUtils.displayChatMessage("\u00a7a\u00a7l" + playerName + "\u00a7c was removed from your friends.");
                }
            } else {
                ClientUtils.displayChatMessage("\u00a7c\u00a7lError: \u00a7aYou need to select a player.");
            }
        }
        this.wasDown = Mouse.isButtonDown((int)2);
    }
}

