/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package me.utils;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.features.module.modules.render.OldHitting;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class Debug
extends MinecraftInstance {
    public static Boolean thePlayerisBlocking = false;

    public static void render(ScaledResolution sr, int itemX, float partialTicks) {
        GL11.glPushMatrix();
        HUD hud = (HUD)LiquidBounce.moduleManager.getModule(HUD.class);
        GL11.glPopMatrix();
        int middleScreen = sr.func_78326_a() / 2;
    }

    public static void func_178096_b(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(equippedProg * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        float var3 = MathHelper.func_76126_a((float)(swingProgress * swingProgress * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var4 * -20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var4 * -80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void transformSideFirstPersonBlock(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179137_b((double)((double)side * 0.56), (double)(-0.52 + (double)equippedProg * -0.6), (double)-0.72);
        GlStateManager.func_179137_b((double)((double)side * -0.1414214), (double)0.08, (double)0.1414214);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        double f = Math.sin((double)(swingProgress * swingProgress) * Math.PI);
        double f1 = Math.sin(Math.sqrt(swingProgress) * Math.PI);
        GlStateManager.func_179114_b((float)((float)(f * -20.0)), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)(f1 * -20.0)), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)((float)(f1 * -80.0)), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void Push(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179137_b((double)((double)side * 0.56), (double)(-0.52 + (double)equippedProg * -0.6), (double)-0.72);
        GlStateManager.func_179137_b((double)((double)side * -0.1414214), (double)0.08, (double)0.1414214);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        double f = Math.sin((double)(swingProgress * swingProgress) * Math.PI);
        double f1 = Math.sin(Math.sqrt(swingProgress) * Math.PI);
        GlStateManager.func_179114_b((float)((float)(f * -10.0)), (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)((float)(f1 * -10.0)), (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)((float)(f1 * -10.0)), (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void sigmaold(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(equippedProg * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        float var3 = MathHelper.func_76126_a((float)(swingProgress * swingProgress * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -15.0f), (float)0.0f, (float)1.0f, (float)0.2f);
        GlStateManager.func_179114_b((float)(var4 * -10.0f), (float)0.2f, (float)0.1f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var4 * -30.0f), (float)1.3f, (float)0.1f, (float)0.2f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void jello(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        float var13 = MathHelper.func_76126_a((float)(swingProgress * swingProgress * (float)Math.PI));
        float var14 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var13 * -35.0f), (float)0.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var14 * 0.0f), (float)0.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var14 * 20.0f), (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void func_178103_d() {
        GlStateManager.func_179109_b((float)-0.5f, (float)0.2f, (float)0.0f);
        GlStateManager.func_179114_b((float)30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)-80.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
    }

    public static void Test(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(equippedProg * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        float var3 = MathHelper.func_76126_a((float)(swingProgress * swingProgress * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * (float)Math.PI));
        float var5 = MathHelper.func_76123_f((float)((float)MathHelper.func_76141_d((float)swingProgress) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var4 * -20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var5 * -80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    private void transformFirstPersonItem1(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(equippedProg * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        float var3 = MathHelper.func_76126_a((float)(swingProgress * swingProgress * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * (float)Math.PI));
        float var5 = MathHelper.func_76123_f((float)((float)MathHelper.func_76141_d((float)swingProgress) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -34.0f), (float)0.0f, (float)1.0f, (float)0.2f);
        GlStateManager.func_179114_b((float)(var4 * -20.7f), (float)0.2f, (float)0.1f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var4 * -68.6f), (float)1.3f, (float)0.1f, (float)0.2f);
        GlStateManager.func_179152_a((float)0.4f, (float)0.4f, (float)0.4f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void ETB(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(equippedProg * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        float var3 = MathHelper.func_76126_a((float)(swingProgress * swingProgress * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -34.0f), (float)0.0f, (float)1.0f, (float)0.2f);
        GlStateManager.func_179114_b((float)(var4 * -20.7f), (float)0.2f, (float)0.1f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var4 * -68.6f), (float)1.3f, (float)0.1f, (float)0.2f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    public static void Zoom(EnumHandSide p_187459_1_, float equippedProg, float swingProgress) {
        int side = p_187459_1_ == EnumHandSide.RIGHT ? 1 : -1;
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(swingProgress * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)-102.25f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 13.365f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)side * 78.05f), (float)0.0f, (float)0.0f, (float)1.0f);
        float var3 = MathHelper.func_76126_a((float)(equippedProg * equippedProg * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)equippedProg) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -20.0f), (float)0.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var4 * -20.0f), (float)0.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var4 * -20.0f), (float)0.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }
}

