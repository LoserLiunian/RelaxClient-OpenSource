/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.settings.GameSettings
 */
package me.sound;

import java.awt.Color;
import me.sound.SoundPlayer;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;

public class Sound {
    public static Sound INSTANCE;
    public static final Minecraft mc;
    public static boolean canSendMotionPacket;
    private static boolean notificationsAllowed;
    public static boolean hasOptifine;

    public static EntityRenderer getEntityRenderer() {
        return Sound.getMinecraft().field_71460_t;
    }

    public static Minecraft getMinecraft() {
        return Minecraft.func_71410_x();
    }

    public static PlayerControllerMP getPlayerController() {
        return Sound.getMinecraft().field_71442_b;
    }

    public static GameSettings getGameSettings() {
        return Sound.getMinecraft().field_71474_y;
    }

    public static boolean notificationsAllowed() {
        return notificationsAllowed;
    }

    public static Color getClientColor() {
        return new Color(236, 133, 209);
    }

    public static Color getAlternateClientColor() {
        return new Color(28, 167, 222);
    }

    public static void notificationsAllowed(boolean value) {
        notificationsAllowed = value;
    }

    public void Volll() {
        new SoundPlayer().playSound(SoundPlayer.SoundType.VICTORY, LiquidBounce.moduleManager.getToggleVolume());
    }

    public boolean fastRenderDisabled(GameSettings gameSettingsIn) {
        try {
            return !this.fastRenderDisabled(gameSettingsIn);
        }
        catch (Exception exception) {
            return true;
        }
    }

    static {
        mc = Minecraft.func_71410_x();
        canSendMotionPacket = true;
        notificationsAllowed = false;
        hasOptifine = false;
    }
}

