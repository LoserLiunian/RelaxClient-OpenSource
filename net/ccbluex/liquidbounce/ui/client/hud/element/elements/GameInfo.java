/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.utils.render.ColorUtils2;
import me.utils.render.VisualUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.color.Gident;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.InfosUtils.Recorder;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Text;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.utils.ServerUtils;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.render.Palette;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="GameInfo")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0006\u00101\u001a\u00020\u0003J\b\u00102\u001a\u000203H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010&\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u000e\u0010(\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/GameInfo;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "(DDF)V", "DATE_FORMAT", "Ljava/text/SimpleDateFormat;", "GameInfo", "Lnet/ccbluex/liquidbounce/value/ListValue;", "GameInfoRows", "", "RectA", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "alphaValue", "astolfoRainbowIndex", "astolfoRainbowOffset", "astolfoclient", "bgalphaValue", "bgblueValue", "bggreenValue", "bgredValue", "blueValue", "blur", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "blurValue", "getBlurValue", "()Lnet/ccbluex/liquidbounce/value/BoolValue;", "brightnessValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "colorModeValue", "distanceValue", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "gradientAmountValue", "greenValue", "lineValue", "getLineValue", "newRainbowIndex", "radiusValue", "raduiValue", "redValue", "saturationValue", "textblueValue", "textblueae", "textgreenValue", "textredValue", "calculateBPS", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class GameInfo
extends Element {
    private final ListValue GameInfo;
    private final IntegerValue alphaValue;
    private final IntegerValue raduiValue;
    private final BoolValue blur;
    private final IntegerValue RectA;
    private final IntegerValue textredValue;
    private final IntegerValue textgreenValue;
    private final IntegerValue textblueValue;
    private final IntegerValue textblueae;
    private final FloatValue brightnessValue;
    private final IntegerValue redValue;
    private final IntegerValue greenValue;
    private final IntegerValue blueValue;
    private final FloatValue radiusValue;
    private final IntegerValue bgredValue;
    private final IntegerValue bggreenValue;
    private final IntegerValue bgblueValue;
    private final IntegerValue bgalphaValue;
    @NotNull
    private final BoolValue blurValue;
    @NotNull
    private final BoolValue lineValue;
    private final IntegerValue newRainbowIndex;
    private final IntegerValue astolfoRainbowOffset;
    private final IntegerValue astolfoclient;
    private final IntegerValue astolfoRainbowIndex;
    private final FloatValue saturationValue;
    private final ListValue colorModeValue;
    private final IntegerValue distanceValue;
    private final IntegerValue gradientAmountValue;
    private FontValue fontValue;
    private int GameInfoRows;
    private final SimpleDateFormat DATE_FORMAT;

    @NotNull
    public final BoolValue getBlurValue() {
        return this.blurValue;
    }

    @NotNull
    public final BoolValue getLineValue() {
        return this.lineValue;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Border drawElement() {
        IFontRenderer icon = Fonts.flux;
        int fontHeight = Fonts.font40.getFontHeight();
        float floatX = (float)this.getRenderX();
        float floatY = (float)this.getRenderY();
        double barLength1 = 163.0f;
        String colorMode = (String)this.colorModeValue.get();
        int color = new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue(), 192).getRGB();
        int Borderx1 = 0;
        int Bordery1 = 0;
        int Borderx2 = 0;
        int Bordery2 = 0;
        if (StringsKt.equals((String)this.GameInfo.get(), "Default", true)) {
            Borderx1 += 0;
            Bordery1 += this.GameInfoRows * 18 + 12;
            Borderx2 += 176;
            Bordery2 += 78;
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea(floatX, floatY + (float)8, 176.0f, 70.0f);
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            RenderUtils.drawRect(0.0f, (float)this.GameInfoRows * 18.0f + 25.0f, 176.0f, 80.0f, new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue(), 0).getRGB());
            RenderUtils.drawShadowWithCustomAlpha(0.0f, 12.5f, 176.0f, 64.0f, 255.0f);
            RenderUtils.drawRect(0.0f, 11.0f, 176.0f, 77.0f, new Color(20, 19, 18, 170).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Session Info", 3, this.GameInfoRows * 18 + 14, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("FPS:" + Minecraft.func_175610_ah(), 7, this.GameInfoRows * 18 + 26, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Ping:" + String.valueOf(EntityUtils.INSTANCE.getPing((EntityPlayer)MinecraftInstance.mc2.field_71439_g)), 7, this.GameInfoRows * 18 + 36, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            IFontRenderer iFontRenderer = (IFontRenderer)this.fontValue.get();
            StringBuilder stringBuilder = new StringBuilder().append("X:");
            DecimalFormat decimalFormat = Text.Companion.getDECIMAL_FORMAT();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder stringBuilder2 = stringBuilder.append(decimalFormat.format(iEntityPlayerSP.getPosX())).append(" ").append("Y:");
            DecimalFormat decimalFormat2 = Text.Companion.getDECIMAL_FORMAT();
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder stringBuilder3 = stringBuilder2.append(decimalFormat2.format(iEntityPlayerSP2.getPosY())).append(" ").append("Z:");
            DecimalFormat decimalFormat3 = Text.Companion.getDECIMAL_FORMAT();
            IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            iFontRenderer.drawStringWithShadow(stringBuilder3.append(decimalFormat3.format(iEntityPlayerSP3.getPosZ())).toString(), 7, this.GameInfoRows * 18 + 47, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("ServerIP:" + ServerUtils.getRemoteIp(), 7, this.GameInfoRows * 18 + 56, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Kills:" + Recorder.INSTANCE.getKillCounts(), 7, this.GameInfoRows * 18 + 68, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            int n = 0;
            int n2 = ((Number)this.gradientAmountValue.get()).intValue();
            while (n < n2) {
                int n3;
                int n4;
                void i;
                double barStart = (double)i / (double)((Number)this.gradientAmountValue.get()).intValue() * barLength1;
                double barEnd = (double)(i + true) / (double)((Number)this.gradientAmountValue.get()).intValue() * barLength1;
                double d = (double)8 + barStart - (double)8;
                double d2 = (double)8 + barEnd + (double)5;
                if (StringsKt.equals(colorMode, "Fade", true)) {
                    Color color2 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), (int)(i * ((Number)this.distanceValue.get()).intValue()), 1000);
                    Intrinsics.checkExpressionValueIsNotNull(color2, "Palette.fade2(\n         \u2026                        )");
                    n4 = color2.getRGB();
                } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                    n4 = VisualUtils.Astolfo((int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                } else if (StringsKt.equals(colorMode, "Gident", true)) {
                    Color color3 = VisualUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                    Intrinsics.checkExpressionValueIsNotNull(color3, "VisualUtils.getGradientO\u2026                        )");
                    n4 = color3.getRGB();
                } else {
                    n4 = StringsKt.equals(colorMode, "NewRainbow", true) ? VisualUtils.getRainbow((int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                }
                if (StringsKt.equals(colorMode, "Fade", true)) {
                    Color color4 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), (int)(i * ((Number)this.distanceValue.get()).intValue()), 1000);
                    Intrinsics.checkExpressionValueIsNotNull(color4, "Palette.fade2(\n         \u2026                        )");
                    n3 = color4.getRGB();
                } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                    n3 = VisualUtils.Astolfo((int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                } else if (StringsKt.equals(colorMode, "Gident", true)) {
                    Color color5 = VisualUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                    Intrinsics.checkExpressionValueIsNotNull(color5, "VisualUtils.getGradientO\u2026                        )");
                    n3 = color5.getRGB();
                } else {
                    n3 = StringsKt.equals(colorMode, "NewRainbow", true) ? VisualUtils.getRainbow((int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                }
                RenderUtils.drawGradientSideways(d, 10.0, d2, 11.0, n4, n3);
                ++i;
            }
        }
        if (StringsKt.equals((String)this.GameInfo.get(), "Flux", true)) {
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurRoundArea(floatX, floatY + (float)10, 120.0f, 65.0f, 7);
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            Borderx1 += 0;
            Bordery1 += this.GameInfoRows * 18 + 12;
            Borderx2 += 150;
            Bordery2 += 80;
            RenderUtils.drawCircleRect(0.0f, 10.0f, 120.0f, 75.0f, 7.0f, new Color(0, 0, 0, 120).getRGB());
            Fonts.flux.drawString("F", 5.0f, 16.0f, -1);
            Fonts.font35.drawString("Play Time: " + this.DATE_FORMAT.format(new Date(System.currentTimeMillis() - Recorder.INSTANCE.getStartTime() - 28800000L)), 20.0f, 15.0f, -1);
            Fonts.flux.drawString("G", 5.0f, 31.0f, -1);
            Fonts.font35.drawString("Kills: " + Recorder.INSTANCE.getKillCounts(), 20.0f, 30.0f, -1);
            Fonts.flux.drawString("H", 5.0f, 46.0f, -1);
            Fonts.font35.drawString("Win / Total: " + Recorder.INSTANCE.getWin() + " / " + Recorder.INSTANCE.getTotalPlayed(), 20.0f, 45.0f, -1);
            Fonts.flux.drawString("I", 5.0f, 61.0f, -1);
            StringBuilder stringBuilder = new StringBuilder().append("Speed:");
            EntityPlayerSP entityPlayerSP = MinecraftInstance.mc2.field_71439_g;
            Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc2.player");
            Fonts.font35.drawString(stringBuilder.append(MovementUtils.INSTANCE.getBlockSpeed((EntityLivingBase)entityPlayerSP)).append("/bps").toString(), 20.0f, 60.0f, -1);
        }
        if (StringsKt.equals((String)this.GameInfo.get(), "Shadow", true)) {
            Borderx1 += 0;
            Bordery1 += this.GameInfoRows * 18 + 12;
            Borderx2 += 176;
            Bordery2 += 80;
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea(floatX, floatY + (float)11, 176.0f, 70.0f);
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Session Info", 3, this.GameInfoRows * 18 + 14, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("FPS:" + Minecraft.func_175610_ah(), 7, this.GameInfoRows * 18 + 26, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Ping:" + String.valueOf(EntityUtils.INSTANCE.getPing((EntityPlayer)MinecraftInstance.mc2.field_71439_g)), 7, this.GameInfoRows * 18 + 36, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            IFontRenderer iFontRenderer = (IFontRenderer)this.fontValue.get();
            StringBuilder stringBuilder = new StringBuilder().append("X:");
            DecimalFormat decimalFormat = Text.Companion.getDECIMAL_FORMAT();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder stringBuilder4 = stringBuilder.append(decimalFormat.format(iEntityPlayerSP.getPosX())).append(" ").append("Y:");
            DecimalFormat decimalFormat4 = Text.Companion.getDECIMAL_FORMAT();
            IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP4 == null) {
                Intrinsics.throwNpe();
            }
            StringBuilder stringBuilder5 = stringBuilder4.append(decimalFormat4.format(iEntityPlayerSP4.getPosY())).append(" ").append("Z:");
            DecimalFormat decimalFormat5 = Text.Companion.getDECIMAL_FORMAT();
            IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP5 == null) {
                Intrinsics.throwNpe();
            }
            iFontRenderer.drawStringWithShadow(stringBuilder5.append(decimalFormat5.format(iEntityPlayerSP5.getPosZ())).toString(), 7, this.GameInfoRows * 18 + 47, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("ServerIP:" + ServerUtils.getRemoteIp(), 7, this.GameInfoRows * 18 + 56, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), ((Number)this.textblueae.get()).intValue()).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Kills:" + Recorder.INSTANCE.getKillCounts(), 7, this.GameInfoRows * 18 + 68, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            RenderUtils.drawRect(0.0f, (float)this.GameInfoRows * 18.0f + 25.0f, 176.0f, 80.0f, new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue(), 0).getRGB());
            RenderUtils.drawShadowWithCustomAlpha(0.0f, 11.0f, 176.0f, 70.0f, 255.0f);
            new Border(0.0f, (float)this.GameInfoRows * 18.0f + 12.0f, 176.0f, 80.0f);
        }
        if (StringsKt.equals((String)this.GameInfo.get(), "Old", true)) {
            Borderx1 += 0;
            Bordery1 += this.GameInfoRows * 18 + 12;
            Borderx2 += 150;
            Bordery2 += 80;
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea((float)this.getRenderX(), (float)this.getRenderY() + (float)8, 156.0f, 78.0f);
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            RenderUtils.drawShadowWithCustomAlpha(0.0f, 8.0f, 155.0f, 76.0f, 200.0f);
            RenderUtils.drawRect(0.0f, 8.0f, 156.0f, 85.0f, new Color(41, 41, 41, ((Number)this.RectA.get()).intValue()).getRGB());
            icon.drawString("c", 47.0f, 2.5f + (float)fontHeight + 6.0f, color);
            Fonts.bold35.drawStringWithShadow("Session Info", (int)(50.0f + (float)icon.getStringWidth("u")), (int)((float)this.GameInfoRows * 18.0f + (float)16), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            StringBuilder stringBuilder = new StringBuilder().append("Speed: ");
            EntityPlayerSP entityPlayerSP = MinecraftInstance.mc2.field_71439_g;
            Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "mc2.player");
            Fonts.bold30.drawStringWithShadow(stringBuilder.append(MovementUtils.INSTANCE.getBlockSpeed((EntityLivingBase)entityPlayerSP)).toString(), (int)(5.0f + (float)icon.getStringWidth("b")), (int)((float)this.GameInfoRows * 20.0f + (float)30), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            Fonts.bold30.drawStringWithShadow("FPS: " + Minecraft.func_175610_ah(), (int)(5.0f + (float)icon.getStringWidth("e")), (int)((float)this.GameInfoRows * 20.0f + (float)43), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            Fonts.bold30.drawStringWithShadow("Kills: " + Recorder.INSTANCE.getKillCounts(), (int)(5.0f + (float)icon.getStringWidth("G")), (int)((float)this.GameInfoRows * 20.0f + (float)54), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            Fonts.bold30.drawStringWithShadow("Played Time: " + this.DATE_FORMAT.format(new Date(System.currentTimeMillis() - Recorder.INSTANCE.getStartTime() - 28800000L)), (int)(5.0f + (float)icon.getStringWidth("G")), (int)((float)this.GameInfoRows * 20.0f + (float)66), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
        }
        if (StringsKt.equals((String)this.GameInfo.get(), "LoseLine", true)) {
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea(floatX, floatY + 10.5f, 150.0f, 70.0f);
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            Borderx1 += 0;
            Bordery1 += this.GameInfoRows * 18 + 12;
            Borderx2 += 150;
            Bordery2 += 80;
            RenderUtils.drawShadowWithCustomAlpha(0.0f, 10.5f, 150.0f, 70.0f, 200.0f);
            RenderUtils.drawRect(0.0f, 11.0f, 150.0f, 12.0f, ColorUtils2.hslRainbow$default(10, 0.0f, 0.0f, 30, 0, 0.0f, 0.0f, 118, null).getRGB());
            RenderUtils.drawRect(0.0f, (float)this.GameInfoRows * 18.0f + (float)12, 150.0f, (float)this.GameInfoRows * 18.0f + 25.0f, new Color(0, 0, 0, 100).getRGB());
            RenderUtils.drawRect(0.0f, (float)this.GameInfoRows * 18.0f + 25.0f, 150.0f, 80.0f, new Color(0, 0, 0, 100).getRGB());
            icon.drawString("c", 3.0f, 2.5f + (float)fontHeight + 6.0f, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            icon.drawString("m", 3.0f, 15.9f + (float)fontHeight + 6.0f, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            icon.drawString("f", 3.0f, 28.5f + (float)fontHeight + 6.0f, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            icon.drawString("a", 3.0f, 39.5f + (float)fontHeight + 6.0f, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            icon.drawString("x", 3.0f, 52.0f + (float)fontHeight + 6.0f, new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Session Info", (int)(5.0f + (float)icon.getStringWidth("u")), (int)((float)this.GameInfoRows * 18.0f + (float)16), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("BPS: " + this.calculateBPS(), (int)(5.0f + (float)icon.getStringWidth("b")), (int)((float)this.GameInfoRows * 18.0f + (float)30), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("FPS: " + Minecraft.func_175610_ah(), (int)(5.0f + (float)icon.getStringWidth("e")), (int)((float)this.GameInfoRows * 18.0f + (float)43), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Kills: " + Recorder.INSTANCE.getKillCounts(), (int)(5.0f + (float)icon.getStringWidth("G")), (int)((float)this.GameInfoRows * 18.0f + (float)54), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
            ((IFontRenderer)this.fontValue.get()).drawStringWithShadow("Played Time: " + this.DATE_FORMAT.format(new Date(System.currentTimeMillis() - Recorder.INSTANCE.getStartTime() - 28800000L)), (int)(5.0f + (float)icon.getStringWidth("G")), (int)((float)this.GameInfoRows * 18.0f + (float)66), new Color(((Number)this.textredValue.get()).intValue(), ((Number)this.textgreenValue.get()).intValue(), ((Number)this.textblueValue.get()).intValue(), 255).getRGB());
        }
        if (StringsKt.equals((String)this.GameInfo.get(), "Normal", true)) {
            IFontRenderer fontRenderer = (IFontRenderer)this.fontValue.get();
            int y2 = fontRenderer.getFontHeight() * 5 + (int)11.0;
            int x2 = (int)140.0;
            Borderx1 += -2;
            Bordery1 += -2;
            Borderx2 += x2;
            Bordery2 += y2;
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
            if (((Boolean)this.blurValue.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                GL11.glPushMatrix();
                BlurBuffer.blurRoundArea((float)this.getRenderX(), (float)this.getRenderY(), x2, y2, (int)((Number)this.radiusValue.get()).floatValue());
                GL11.glPopMatrix();
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            RenderUtils.drawShadowWithCustomAlpha(-2.0f, -2.0f, x2 + 2, y2 + 2, 255.0f);
            RenderUtils.drawRoundedRect(-2.0f, -2.0f, x2, y2, (int)((Number)this.radiusValue.get()).floatValue(), new Color(((Number)this.bgredValue.get()).intValue(), ((Number)this.bggreenValue.get()).intValue(), ((Number)this.bgblueValue.get()).intValue(), ((Number)this.bgalphaValue.get()).intValue()).getRGB());
            if (((Boolean)this.lineValue.get()).booleanValue()) {
                Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(HUD.class);
                if (module == null) {
                    throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.render.HUD");
                }
                HUD hud = (HUD)module;
                RenderUtils.drawGradientSideways(2.44, (double)fontRenderer.getFontHeight() + 2.5 + 0.0, 135.55999994277954, (double)fontRenderer.getFontHeight() + 2.5 + (double)1.16f, new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()).getRGB(), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue()).getRGB());
            }
            float f = (float)x2 / 2.0f;
            Color color6 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color6, "Color.WHITE");
            fontRenderer.drawCenteredString("Session Info", f, 3.0f, color6.getRGB(), true);
            String string = "Play Time: " + DATE_FORMAT.format(new Date(System.currentTimeMillis() - Recorder.INSTANCE.getStartTime() - 28800000L));
            int n = (int)((float)fontRenderer.getFontHeight() + 8.0f);
            Color color7 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color7, "Color.WHITE");
            fontRenderer.drawStringWithShadow(string, 2, n, color7.getRGB());
            String string2 = "Players Killed: " + Recorder.INSTANCE.getKillCounts();
            int n5 = (int)((float)(fontRenderer.getFontHeight() * 2) + 8.0f);
            Color color8 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color8, "Color.WHITE");
            fontRenderer.drawStringWithShadow(string2, 2, n5, color8.getRGB());
            String string3 = "Win: " + Recorder.INSTANCE.getTotalPlayed();
            int n6 = (int)((float)(fontRenderer.getFontHeight() * 3) + 8.0f);
            Color color9 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color9, "Color.WHITE");
            fontRenderer.drawStringWithShadow(string3, 2, n6, color9.getRGB());
            String string4 = "Total: " + Recorder.INSTANCE.getTotalPlayed();
            int n7 = (int)((float)(fontRenderer.getFontHeight() * 4) + 8.0f);
            Color color10 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color10, "Color.WHITE");
            fontRenderer.drawStringWithShadow(string4, 2, n7, color10.getRGB());
            return new Border(-2.0f, -2.0f, x2, y2);
        }
        if (StringsKt.equals((String)this.GameInfo.get(), "Round", true)) {
            IFontRenderer font = Fonts.bold30;
            Borderx1 += 0;
            Bordery1 += 0;
            Borderx2 += 150;
            Bordery2 += 3 + fontHeight + font.getFontHeight() * 3 + 30;
            int HM = 0;
            int S = 0;
            int M = 0;
            int H = 0;
            if (++HM == 120) {
                ++S;
                HM = 0;
            }
            if (S == 60) {
                ++M;
                S = 0;
            }
            if (M == 60) {
                ++H;
                M = 0;
            }
            Color color11 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color11, "Color.WHITE");
            int color12 = color11.getRGB();
            int fontHeight2 = Fonts.bold30.getFontHeight();
            DecimalFormat format = new DecimalFormat("#.##");
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                GL11.glPushMatrix();
                BlurBuffer.blurRoundArea((float)this.getRenderX(), (float)this.getRenderY(), 150.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 3) + 30.0f, (int)((Number)this.radiusValue.get()).floatValue());
                GL11.glPopMatrix();
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            VisualUtils.drawCircleRect(0.0f, 0.0f, 150.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 3) + 30.0f, ((Number)this.raduiValue.get()).intValue(), new Color(0, 0, 0, ((Number)this.alphaValue.get()).intValue()).getRGB());
            Fonts.bold40.drawString("Session State", 5.0f, 3.0f, color12);
            font.drawString("Played Time", 7.0f, 3.0f + (float)fontHeight2 + 5.0f, color12);
            font.drawString("Speed", 7.0f, 3.0f + (float)fontHeight2 + (float)font.getFontHeight() + 10.0f, color12);
            font.drawString("Ping", 7.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 2) + 15.0f, color12);
            font.drawString("Kills", 7.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 3) + 20.0f, color12);
            font.drawString(H + "h " + M + "m " + S + 's', (float)(150 - font.getStringWidth(H + "h " + M + "m " + S + 's')) - 5.0f, 3.0f + (float)fontHeight2 + 5.0f, color12);
            String string = format.format(MovementUtils.INSTANCE.getBps());
            Intrinsics.checkExpressionValueIsNotNull(string, "format.format(MovementUtils.bps)");
            String string5 = format.format(MovementUtils.INSTANCE.getBps());
            Intrinsics.checkExpressionValueIsNotNull(string5, "format.format(MovementUtils.bps)");
            font.drawString(string, (float)(150 - font.getStringWidth(string5)) - 5.0f, 3.0f + (float)fontHeight2 + (float)font.getFontHeight() + 10.0f, color12);
            Minecraft minecraft = Minecraft.func_71410_x();
            Intrinsics.checkExpressionValueIsNotNull(minecraft, "Minecraft.getMinecraft()");
            if (minecraft.func_71356_B()) {
                font.drawString("0ms (Singleplayer)", (float)(150 - font.getStringWidth("0ms (Singleplayer)")) - 5.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 2) + 15.0f, color12);
            } else {
                IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                EntityPlayerSP entityPlayerSP = Minecraft.func_71410_x().field_71439_g;
                Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP, "Minecraft.getMinecraft().player");
                UUID uUID = entityPlayerSP.func_110124_au();
                Intrinsics.checkExpressionValueIsNotNull(uUID, "Minecraft.getMinecraft().player.uniqueID");
                INetworkPlayerInfo iNetworkPlayerInfo = iINetHandlerPlayClient.getPlayerInfo(uUID);
                if (iNetworkPlayerInfo == null) {
                    Intrinsics.throwNpe();
                }
                String string6 = String.valueOf(iNetworkPlayerInfo.getResponseTime());
                IINetHandlerPlayClient iINetHandlerPlayClient2 = MinecraftInstance.mc.getNetHandler();
                EntityPlayerSP entityPlayerSP2 = Minecraft.func_71410_x().field_71439_g;
                Intrinsics.checkExpressionValueIsNotNull(entityPlayerSP2, "Minecraft.getMinecraft().player");
                UUID uUID2 = entityPlayerSP2.func_110124_au();
                Intrinsics.checkExpressionValueIsNotNull(uUID2, "Minecraft.getMinecraft().player.uniqueID");
                INetworkPlayerInfo iNetworkPlayerInfo2 = iINetHandlerPlayClient2.getPlayerInfo(uUID2);
                if (iNetworkPlayerInfo2 == null) {
                    Intrinsics.throwNpe();
                }
                font.drawString(string6, (float)(150 - font.getStringWidth(String.valueOf(iNetworkPlayerInfo2.getResponseTime()))) - 5.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 2) + 15.0f, color12);
            }
            font.drawString(String.valueOf(KillAura.Companion.getKillCounts()), (float)(150 - font.getStringWidth(String.valueOf(KillAura.Companion.getKillCounts()))) - 5.0f, 3.0f + (float)fontHeight2 + (float)(font.getFontHeight() * 3) + 20.0f, color12);
        }
        return new Border(Borderx1, Bordery1, Borderx2, Bordery2);
    }

    public final double calculateBPS() {
        if (MinecraftInstance.mc.getThePlayer() != null) {
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            double d = iEntityPlayerSP.getPosX();
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            double d2 = d - iEntityPlayerSP2.getPrevPosX();
            IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            double d3 = iEntityPlayerSP3.getPosZ();
            IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP4 == null) {
                Intrinsics.throwNpe();
            }
            double bps = Math.hypot(d2, d3 - iEntityPlayerSP4.getPrevPosZ()) * (double)MinecraftInstance.mc.getTimer().getTimerSpeed() * (double)20;
            return (double)Math.round(bps * 100.0) / 100.0;
        }
        return 0.0;
    }

    public GameInfo(double x, double y, float scale) {
        super(x, y, scale, null, 8, null);
        this.GameInfo = new ListValue("Mode", new String[]{"Default", "Shadow", "Old", "LoseLine", "Flux", "Normal", "Round"}, "Default");
        this.alphaValue = new IntegerValue("Round-Background-Alpha", 180, 0, 255);
        this.raduiValue = new IntegerValue("Round-RoundRadiu", 2, 0, 10);
        this.blur = new BoolValue("Old-Blur", true);
        this.RectA = new IntegerValue("Old-RectA", 120, 0, 255);
        this.textredValue = new IntegerValue("Old-TextRed", 255, 0, 255);
        this.textgreenValue = new IntegerValue("Old-TextRed", 244, 0, 255);
        this.textblueValue = new IntegerValue("Old-TextBlue", 255, 0, 255);
        this.textblueae = new IntegerValue("Old-Textalpha", 255, 0, 255);
        this.brightnessValue = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
        this.redValue = new IntegerValue("Text-R", 255, 0, 255);
        this.greenValue = new IntegerValue("Text-G", 255, 0, 255);
        this.blueValue = new IntegerValue("Text-B", 255, 0, 255);
        this.radiusValue = new FloatValue("Normal-Radius", 4.25f, 0.0f, 10.0f);
        this.bgredValue = new IntegerValue("Normal-Bg-R", 255, 0, 255);
        this.bggreenValue = new IntegerValue("Normal-Bg-G", 255, 0, 255);
        this.bgblueValue = new IntegerValue("Normal-Bg-B", 255, 0, 255);
        this.bgalphaValue = new IntegerValue("Normal-Bg-Alpha", 150, 0, 255);
        this.blurValue = new BoolValue("Normal-Blur", true);
        this.lineValue = new BoolValue("Normal-Line", true);
        this.newRainbowIndex = new IntegerValue("NewRainbowOffset", 1, 1, 50);
        this.astolfoRainbowOffset = new IntegerValue("AstolfoOffset", 5, 1, 20);
        this.astolfoclient = new IntegerValue("AstolfoRange", 109, 1, 765);
        this.astolfoRainbowIndex = new IntegerValue("AstolfoIndex", 109, 1, 300);
        this.saturationValue = new FloatValue("Saturation", 0.9f, 0.0f, 1.0f);
        this.colorModeValue = new ListValue("Color", new String[]{"Custom", "Rainbow", "Fade", "Astolfo", "NewRainbow", "Gident"}, "Custom");
        this.distanceValue = new IntegerValue("Distance", 0, 0, 400);
        this.gradientAmountValue = new IntegerValue("Gradient-Amount", 25, 1, 50);
        IFontRenderer iFontRenderer = Fonts.fontSFUI35;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.fontSFUI35");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    }

    public /* synthetic */ GameInfo(double d, double d2, float f, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 10.0;
        }
        if ((n & 2) != 0) {
            d2 = 10.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        this(d, d2, f);
    }

    public GameInfo() {
        this(0.0, 0.0, 0.0f, 7, null);
    }
}

