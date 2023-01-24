/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Slot
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.gui;

import java.util.List;
import me.animation.EaseUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.player.InventoryCleaner;
import net.ccbluex.liquidbounce.features.module.modules.render.OldHitting;
import net.ccbluex.liquidbounce.features.module.modules.world.ChestStealer;
import net.ccbluex.liquidbounce.injection.forge.mixins.gui.MixinGuiScreen;
import net.ccbluex.liquidbounce.injection.implementations.IMixinGuiContainer;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiContainer.class})
public abstract class MixinGuiContainer
extends MixinGuiScreen
implements IMixinGuiContainer {
    private GuiButton invManagerButton;
    private GuiButton killAuraButton;
    private float progress = 0.0f;
    private long lastMS = 0L;
    @Shadow
    protected int field_146999_f;
    @Shadow
    protected int field_147000_g;

    @Shadow
    protected abstract void func_184098_a(Slot var1, int var2, int var3, ClickType var4);

    @Inject(method={"initGui"}, at={@At(value="HEAD")}, cancellable=true)
    public void injectInitGui(CallbackInfo callbackInfo) {
        GuiButton guiButton2;
        GuiButton guiButton;
        GuiScreen guiScreen = Minecraft.func_71410_x().field_71462_r;
        List list = this.field_146292_n;
        this.killAuraButton = guiButton = new GuiButton(1024576, 5, 5, 140, 20, "Disable KillAura");
        list.add(guiButton);
        int firstY = 20;
        List list2 = this.field_146292_n;
        this.invManagerButton = guiButton2 = new GuiButton(321123, 5, 10 + firstY, 140, 20, "Disable InvManager");
        list2.add(guiButton2);
        this.lastMS = System.currentTimeMillis();
        this.progress = 0.0f;
    }

    @Inject(method={"drawScreen"}, at={@At(value="HEAD")}, cancellable=true)
    protected void drawScreenHead(CallbackInfo callbackInfo) {
        this.progress = this.progress >= 1.0f ? 1.0f : (float)(System.currentTimeMillis() - this.lastMS) / 300.0f;
        double trueAnim = EaseUtils.easeOutQuart(this.progress);
        ChestStealer cs = (ChestStealer)LiquidBounce.moduleManager.getModule(ChestStealer.class);
        switch ((String)OldHitting.guiAnimations.get()) {
            case "Zoom": {
                GL11.glTranslated((double)((1.0 - trueAnim) * ((double)this.field_146294_l / 2.0)), (double)((1.0 - trueAnim) * ((double)this.field_146295_m / 2.0)), (double)0.0);
                GL11.glScaled((double)trueAnim, (double)trueAnim, (double)trueAnim);
                break;
            }
            case "HSlide": {
                GL11.glTranslated((double)((1.0 - trueAnim) * (double)(-this.field_146294_l)), (double)0.0, (double)0.0);
                break;
            }
            case "VSlide": {
                GL11.glTranslated((double)0.0, (double)((1.0 - trueAnim) * (double)(-this.field_146295_m)), (double)0.0);
                break;
            }
            case "HVSlide": {
                GL11.glTranslated((double)((1.0 - trueAnim) * (double)(-this.field_146294_l)), (double)((1.0 - trueAnim) * (double)(-this.field_146295_m)), (double)0.0);
            }
        }
        RenderUtils.drawGradientSideways(0.0, 0.0, this.field_146999_f, this.field_147000_g, 0, 0);
        GL11.glPushMatrix();
    }

    @Inject(method={"drawScreen"}, at={@At(value="RETURN")}, cancellable=true)
    protected void drawScreenReturn(CallbackInfo callbackInfo) {
        GL11.glPopMatrix();
    }

    @Inject(method={"mouseClicked"}, at={@At(value="RETURN")})
    private void mouseClicked(int mouseX, int mouseY, int mouseButton, CallbackInfo callbackInfo) {
        for (Object aButtonList : this.field_146292_n) {
            GuiButton var52 = (GuiButton)aButtonList;
            if (var52.func_146116_c(this.field_146297_k, mouseX, mouseY) && var52.field_146127_k == 1024576) {
                LiquidBounce.moduleManager.getModule(KillAura.class).setState(false);
            }
            if (!var52.func_146116_c(this.field_146297_k, mouseX, mouseY) || var52.field_146127_k != 321123) continue;
            LiquidBounce.moduleManager.getModule(InventoryCleaner.class).setState(false);
        }
    }

    @Override
    public void publicHandleMouseClick(Slot slot, int slotNumber, int clickedButton, ClickType clickType) {
        this.func_184098_a(slot, slotNumber, clickedButton, clickType);
    }
}

