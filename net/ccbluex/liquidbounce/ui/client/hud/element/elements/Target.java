/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.Palette;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.render.Stencil;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.minecraft.client.gui.Gui;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="Target")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J8\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Target;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "()V", "decimalFormat", "Ljava/text/DecimalFormat;", "easingHealth", "", "fadeSpeed", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "lastTarget", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "sb", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "drawHead", "", "skin", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IResourceLocation;", "x", "", "y", "width", "height", "alpha", "Relaxed"})
public final class Target
extends Element {
    private final DecimalFormat decimalFormat = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));
    private final FloatValue fadeSpeed = new FloatValue("FadeSpeed", 2.0f, 1.0f, 9.0f);
    private final BoolValue sb = new BoolValue("Blur", false);
    private FontValue fontValue;
    private float easingHealth;
    private IEntity lastTarget;

    @Override
    @NotNull
    public Border drawElement() {
        IEntityLivingBase target;
        block12: {
            int n;
            boolean bl;
            float floatY;
            float floatX;
            block14: {
                block13: {
                    IFontRenderer font = Fonts.font40;
                    Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
                    if (module == null) {
                        throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
                    }
                    target = ((KillAura)module).getTarget();
                    float length = RangesKt.coerceAtLeast((float)RangesKt.coerceAtLeast(font.getStringWidth(this.getName()), font.getStringWidth(this.getInfo().toString())) + 40.0f, 125.0f);
                    floatX = (float)this.getRenderX();
                    floatY = (float)this.getRenderY();
                    if (!MinecraftInstance.classProvider.isEntityPlayer(target) || target == null) break block12;
                    if (Intrinsics.areEqual(target, this.lastTarget) ^ true || this.easingHealth < 0.0f || this.easingHealth > target.getMaxHealth()) break block13;
                    float f = this.easingHealth - target.getHealth();
                    boolean bl2 = false;
                    if (!((double)Math.abs(f) < 0.01)) break block14;
                }
                this.easingHealth = target.getHealth();
            }
            int n2 = 38;
            String string = target.getName();
            if (string != null) {
                String string2 = string;
                Object t = this.fontValue.get();
                int n3 = n2;
                bl = false;
                boolean bl3 = false;
                String p1 = string2;
                boolean bl4 = false;
                int n4 = ((IFontRenderer)t).getStringWidth(p1);
                n2 = n3;
                n = n4;
            } else {
                n = 0;
            }
            float width = RangesKt.coerceAtLeast(n2 + n, 118);
            if (this.easingHealth > target.getHealth()) {
                RenderUtils.drawRect(3.0f, 41.0f, this.easingHealth / target.getMaxHealth() * width - 9.0f, 44.0f, new Color(252, 185, 65).getRGB());
            }
            if (((Boolean)this.sb.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                GL11.glPushMatrix();
                BlurBuffer.blurArea(floatX, floatY, 113.0f, 45.0f);
                GL11.glPopMatrix();
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            RenderUtils.drawRect(1.0f, 46.0f, 113.0f, 39.0f, new Color(106, 106, 106, 80));
            RenderUtils.drawRoundRect(0.0f, 1.0f, 38.0f, 38.0f, 7.0f, new Color(106, 106, 106, 80).getRGB());
            RenderUtils.drawRect(3.0f, 41.0f, target.getHealth() / target.getMaxHealth() * width - 9.0f, 44.0f, target.getHealth() >= (float)6 ? Palette.fade2(new Color(10, 243, 10, 150), 123, 123) : Palette.fade2(new Color(255, 10, 15, 200), 123, 123));
            if (this.easingHealth < target.getHealth()) {
                RenderUtils.drawRect(this.easingHealth / target.getMaxHealth() * width, 34.0f, target.getHealth() / target.getMaxHealth() * width, 36.0f, new Color(44, 201, 144).getRGB());
            }
            RenderUtils.drawRect(113.0f, 37.0f, 42.0f, 2.0f, new Color(106, 106, 106, 80));
            Target target2 = this;
            float f = 2.0f;
            float f2 = 10.0f - ((Number)this.fadeSpeed.get()).floatValue();
            float f3 = target.getHealth() - this.easingHealth;
            float f4 = target2.easingHealth;
            Target target3 = target2;
            bl = false;
            float f5 = (float)Math.pow(f, f2);
            target3.easingHealth = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
            String string3 = target.getName();
            if (string3 != null) {
                String string4 = string3;
                boolean bl5 = false;
                bl = false;
                String it = string4;
                boolean bl6 = false;
                ((IFontRenderer)this.fontValue.get()).drawStringWithShadow(it, 45, 5, 0xFFFFFF);
            }
            IFontRenderer iFontRenderer = (IFontRenderer)this.fontValue.get();
            StringBuilder stringBuilder = new StringBuilder().append("Distance: ");
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            iFontRenderer.drawStringWithShadow(stringBuilder.append(this.decimalFormat.format(PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, target))).toString(), 45, 18, 0xFFFFFF);
            INetworkPlayerInfo playerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo(target.getUniqueID());
            if (playerInfo != null) {
                ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Ping: " + RangesKt.coerceAtLeast(playerInfo.getResponseTime(), 0), 45, 26, 0xFFFFFF);
                IResourceLocation locationSkin = playerInfo.getLocationSkin();
                Stencil.write(false);
                GL11.glDisable((int)3553);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                RenderUtils.fastRoundedRect(4.0f, 4.0f, 34.0f, 34.0f, 8.0f);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3553);
                Stencil.erase(true);
                this.drawHead(playerInfo.getLocationSkin(), 4, 4, 30, 30, 1.0f);
                Stencil.dispose();
            }
        }
        this.lastTarget = target;
        return new Border(0.0f, 0.0f, 120.0f, 36.0f);
    }

    private final void drawHead(IResourceLocation skin, int x, int y, int width, int height, float alpha) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        MinecraftInstance.mc.getTextureManager().bindTexture(skin);
        Gui.func_152125_a((int)x, (int)y, (float)8.0f, (float)8.0f, (int)8, (int)8, (int)width, (int)height, (float)64.0f, (float)64.0f);
    }

    public Target() {
        super(0.0, 0.0, 0.0f, null, 15, null);
        IFontRenderer iFontRenderer = Fonts.CasanovaScotia35;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.CasanovaScotia35");
        this.fontValue = new FontValue("Font", iFontRenderer);
    }
}

