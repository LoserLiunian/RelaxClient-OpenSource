/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.gui;

import java.awt.Color;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.injection.backend.FontRendererImpl;
import net.ccbluex.liquidbounce.ui.font.AWTFontRenderer;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@SideOnly(value=Side.CLIENT)
@Mixin(value={GuiButton.class})
public abstract class MixinGuiButton
extends Gui {
    @Shadow
    @Final
    protected static ResourceLocation field_146122_a;
    @Shadow
    public boolean field_146125_m;
    @Shadow
    public int field_146128_h;
    @Shadow
    public int field_146129_i;
    @Shadow
    public int field_146120_f;
    @Shadow
    public int field_146121_g;
    @Shadow
    public boolean field_146124_l;
    @Shadow
    public String field_146126_j;
    @Shadow
    protected boolean field_146123_n;
    private float cut;
    private float alpha;

    @Shadow
    protected abstract void func_146119_b(Minecraft var1, int var2, int var3);

    @Overwrite
    public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.field_146125_m) {
            FontRenderer fontRenderer = mc.func_135016_M().func_135042_a() ? mc.field_71466_p : ((FontRendererImpl)Fonts.font35).getWrapped();
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int delta = RenderUtils.deltaTime;
            if (this.field_146124_l && this.field_146123_n) {
                this.cut += 0.05f * (float)delta;
                if (this.cut >= 4.0f) {
                    this.cut = 4.0f;
                }
                this.alpha += 0.3f * (float)delta;
                if (this.alpha >= 210.0f) {
                    this.alpha = 210.0f;
                }
            } else {
                this.cut -= 0.05f * (float)delta;
                if (this.cut <= 0.0f) {
                    this.cut = 0.0f;
                }
                this.alpha -= 0.3f * (float)delta;
                if (this.alpha <= 120.0f) {
                    this.alpha = 120.0f;
                }
            }
            Gui.func_73734_a((int)(this.field_146128_h + (int)this.cut), (int)this.field_146129_i, (int)(this.field_146128_h + this.field_146120_f - (int)this.cut), (int)(this.field_146129_i + this.field_146121_g), (int)(this.field_146124_l ? new Color(0.0f, 0.0f, 0.0f, this.alpha / 255.0f).getRGB() : new Color(0.5f, 0.5f, 0.5f, 0.5f).getRGB()));
            mc.func_110434_K().func_110577_a(field_146122_a);
            this.func_146119_b(mc, mouseX, mouseY);
            AWTFontRenderer.Companion.setAssumeNonVolatile(true);
            fontRenderer.func_175063_a(this.field_146126_j, (float)(this.field_146128_h + this.field_146120_f / 2 - fontRenderer.func_78256_a(this.field_146126_j) / 2), (float)this.field_146129_i + (float)(this.field_146121_g - 5) / 2.0f, 0xE0E0E0);
            AWTFontRenderer.Companion.setAssumeNonVolatile(false);
            RenderUtils.drawBorderedRect(this.field_146128_h + (int)this.cut, this.field_146129_i, this.field_146128_h + this.field_146120_f - (int)this.cut, this.field_146129_i + this.field_146121_g, 1.5f, new Color(225, 225, 225, 255).getRGB(), new Color(200, 200, 200, 20).getRGB());
            if (((Boolean)HUD.Hotbarblur.get()).booleanValue()) {
                GL11.glPushMatrix();
                BlurBuffer.blurArea2(this.field_146128_h + (int)this.cut, this.field_146129_i, this.field_146128_h + this.field_146120_f - (int)this.cut, this.field_146129_i + this.field_146121_g);
                GL11.glPopMatrix();
            }
            mc.func_110434_K().func_110577_a(field_146122_a);
            this.func_146119_b(mc, mouseX, mouseY);
            AWTFontRenderer.Companion.setAssumeNonVolatile(true);
            fontRenderer.func_175063_a(this.field_146126_j, (float)(this.field_146128_h + this.field_146120_f / 2 - fontRenderer.func_78256_a(this.field_146126_j) / 2), (float)this.field_146129_i + (float)(this.field_146121_g - 5) / 2.0f, 0xE0E0E0);
            AWTFontRenderer.Companion.setAssumeNonVolatile(false);
            GlStateManager.func_179117_G();
        }
    }
}

