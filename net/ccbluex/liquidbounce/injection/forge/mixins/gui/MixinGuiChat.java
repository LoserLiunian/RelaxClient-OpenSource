/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Mouse
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.gui;

import java.awt.Color;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.injection.forge.mixins.gui.MixinGuiScreen;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={GuiChat.class})
public abstract class MixinGuiChat
extends MixinGuiScreen {
    @Shadow
    protected GuiTextField field_146415_a;
    private float yPosOfInputField;
    private float fade = 0.0f;

    @Shadow
    public abstract void func_184072_a(String ... var1);

    @Inject(method={"initGui"}, at={@At(value="RETURN")})
    private void init(CallbackInfo callbackInfo) {
        this.field_146415_a.field_146210_g = this.field_146295_m + 1;
        this.yPosOfInputField = this.field_146415_a.field_146210_g;
    }

    @Inject(method={"keyTyped"}, at={@At(value="RETURN")})
    private void updateLength(CallbackInfo callbackInfo) {
        if (!this.field_146415_a.func_146179_b().startsWith(String.valueOf(LiquidBounce.commandManager.getPrefix()))) {
            return;
        }
        LiquidBounce.commandManager.autoComplete(this.field_146415_a.func_146179_b());
        if (!this.field_146415_a.func_146179_b().startsWith(LiquidBounce.commandManager.getPrefix() + "lc")) {
            this.field_146415_a.func_146203_f(10000);
        } else {
            this.field_146415_a.func_146203_f(100);
        }
    }

    @Inject(method={"updateScreen"}, at={@At(value="HEAD")})
    private void updateScreen(CallbackInfo callbackInfo) {
        int delta = RenderUtils.deltaTime;
        if (this.fade < 14.0f) {
            this.fade += 0.4f * (float)delta;
        }
        if (this.fade > 14.0f) {
            this.fade = 14.0f;
        }
        if (this.yPosOfInputField > (float)(this.field_146295_m - 12)) {
            this.yPosOfInputField -= 0.4f * (float)delta;
        }
        if (this.yPosOfInputField < (float)(this.field_146295_m - 12)) {
            this.yPosOfInputField = this.field_146295_m - 12;
        }
        this.field_146415_a.field_146210_g = (int)this.yPosOfInputField;
    }

    @Overwrite
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        ITextComponent ichatcomponent;
        Gui.func_73734_a((int)2, (int)(this.field_146295_m - (int)this.fade), (int)(this.field_146294_l - 2), (int)this.field_146295_m, (int)Integer.MIN_VALUE);
        this.field_146415_a.func_146194_f();
        if (LiquidBounce.commandManager.getLatestAutoComplete().length > 0 && !this.field_146415_a.func_146179_b().isEmpty() && this.field_146415_a.func_146179_b().startsWith(String.valueOf(LiquidBounce.commandManager.getPrefix()))) {
            String[] latestAutoComplete = LiquidBounce.commandManager.getLatestAutoComplete();
            String[] textArray = this.field_146415_a.func_146179_b().split(" ");
            String trimmedString = latestAutoComplete[0].replaceFirst("(?i)" + textArray[textArray.length - 1], "");
            this.field_146297_k.field_71466_p.func_175063_a(trimmedString, (float)(this.field_146415_a.field_146209_f + this.field_146297_k.field_71466_p.func_78256_a(this.field_146415_a.func_146179_b())), (float)this.field_146415_a.field_146210_g, new Color(165, 165, 165).getRGB());
        }
        if ((ichatcomponent = this.field_146297_k.field_71456_v.func_146158_b().func_146236_a(Mouse.getX(), Mouse.getY())) != null) {
            this.func_175272_a(ichatcomponent, mouseX, mouseY);
        }
    }
}

