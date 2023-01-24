/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.gui;

import java.awt.Color;
import me.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.Render2DEvent;
import net.ccbluex.liquidbounce.features.module.modules.render.AntiBlind;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.features.module.modules.render.NoScoreboard;
import net.ccbluex.liquidbounce.injection.forge.mixins.gui.MixinGui;
import net.ccbluex.liquidbounce.ui.font.AWTFontRenderer;
import net.ccbluex.liquidbounce.utils.ClassUtils;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={GuiIngame.class})
public abstract class MixinGuiInGame
extends MixinGui {
    @Shadow
    @Final
    protected static ResourceLocation field_110330_c;
    @Shadow
    @Final
    protected Minecraft field_73839_d;

    @Shadow
    protected abstract void func_184044_a(int var1, int var2, float var3, EntityPlayer var4, ItemStack var5);

    @Inject(method={"renderScoreboard"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderScoreboard(CallbackInfo callbackInfo) {
        if (LiquidBounce.moduleManager.getModule(HUD.class).getState() || NoScoreboard.INSTANCE.getState()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderHotbar"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderTooltip(ScaledResolution sr, float partialTicks, CallbackInfo callbackInfo) {
        HUD hud = (HUD)LiquidBounce.moduleManager.getModule(HUD.class);
        if (Minecraft.func_71410_x().func_175606_aa() instanceof EntityPlayer && hud.getState() && (((Boolean)hud.getBlackHotbarValue().get()).booleanValue() || ((Boolean)hud.getAnimHotbarValue().get()).booleanValue())) {
            Minecraft mc = Minecraft.func_71410_x();
            EntityPlayer entityPlayer = (EntityPlayer)mc.func_175606_aa();
            boolean blackHB = (Boolean)hud.getBlackHotbarValue().get();
            int middleScreen = sr.func_78326_a() / 2;
            float posInv = hud.getAnimPos((float)entityPlayer.field_71071_by.field_70461_c * 20.0f);
            GlStateManager.func_179117_G();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            mc.func_110434_K().func_110577_a(field_110330_c);
            float f = this.field_73735_i;
            this.field_73735_i = -90.0f;
            Color var10000 = RenderUtils.interpolateColorsBackAndForth(8, 1, new Color(255, 255, 255, 255), new Color(0, 0, 0, 0), false);
            GuiIngame.func_73734_a((int)(middleScreen - 91 + entityPlayer.field_71071_by.field_70461_c * 20 + 1 + 2), (int)(sr.func_78328_b() - 2), (int)(middleScreen - 91 - 1 + entityPlayer.field_71071_by.field_70461_c * 20 + 22), (int)(sr.func_78328_b() - 22 - 1 + 1), (int)var10000.getRGB());
            BlurBuffer.blurArea2(middleScreen - 91, sr.func_78328_b() - 24, middleScreen + 91, sr.func_78328_b());
            GuiIngame.func_73734_a((int)(middleScreen - 91 + entityPlayer.field_71071_by.field_70461_c * 20 + 1 + 2), (int)(sr.func_78328_b() - 24), (int)(middleScreen - 91 - 1 + entityPlayer.field_71071_by.field_70461_c * 20 + 22), (int)(sr.func_78328_b() - 22 - 1 + 1), (int)var10000.getRGB());
            GlStateManager.func_179117_G();
            net.ccbluex.liquidbounce.utils.render.RenderUtils.originalRoundedRect(middleScreen - 91, sr.func_78328_b() - 2, middleScreen + 91, sr.func_78328_b() - 22, 3.0f, Integer.MIN_VALUE);
            net.ccbluex.liquidbounce.utils.render.RenderUtils.originalRoundedRect((float)(middleScreen - 91) + posInv, sr.func_78328_b() - 2, (float)(middleScreen - 91) + posInv + 22.0f, sr.func_78328_b() - 22, 3.0f, Integer.MAX_VALUE);
            this.field_73735_i = f;
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179091_B();
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
            RenderHelper.func_74520_c();
            for (int l = 0; l < 9; ++l) {
                int i1 = middleScreen - 90 + l * 20 + 2;
                int j1 = sr.func_78328_b() - 16 - 3;
                this.func_184044_a(i1, j1, partialTicks, entityPlayer, (ItemStack)entityPlayer.field_71071_by.field_70462_a.get(l));
            }
            RenderHelper.func_74518_a();
            GlStateManager.func_179101_C();
            GlStateManager.func_179084_k();
            GlStateManager.func_179117_G();
            LiquidBounce.eventManager.callEvent(new Render2DEvent(partialTicks));
            AWTFontRenderer.Companion.garbageCollectionTick();
            callbackInfo.cancel();
        }
    }

    @Overwrite
    protected void func_184048_a(ScaledResolution p_renderPotionEffects_1_) {
    }

    @Inject(method={"renderHotbar"}, at={@At(value="RETURN")})
    private void renderTooltipPost(ScaledResolution sr, float partialTicks, CallbackInfo callbackInfo) {
        this.callRender2DEvent(partialTicks);
    }

    private void callRender2DEvent(float partialTicks) {
        if (!ClassUtils.hasClass("net.labymod.api.LabyModAPI")) {
            LiquidBounce.eventManager.callEvent(new Render2DEvent(partialTicks));
            AWTFontRenderer.Companion.garbageCollectionTick();
        }
    }

    @Inject(method={"renderPumpkinOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderPumpkinOverlay(CallbackInfo callbackInfo) {
        AntiBlind antiBlind = (AntiBlind)LiquidBounce.moduleManager.getModule(AntiBlind.class);
        if (antiBlind.getState() && ((Boolean)antiBlind.getPumpkinEffect().get()).booleanValue()) {
            callbackInfo.cancel();
        }
    }
}

