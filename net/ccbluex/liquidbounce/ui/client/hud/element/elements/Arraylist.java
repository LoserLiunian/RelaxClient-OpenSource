/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import me.utils.render.BlurUtils;
import me.utils.render.ShadowUtils;
import me.utils.render.VisualUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Arraylist$WhenMappings;
import net.ccbluex.liquidbounce.ui.font.AWTFontRenderer;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.AnimationUtils;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="Arraylist", single=true)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\n\u0010W\u001a\u0004\u0018\u00010XH\u0016J\u000e\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u000208J\u0010\u0010\\\u001a\u00020Z2\u0006\u0010]\u001a\u000208H\u0002J\b\u0010^\u001a\u00020_H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u000e\u0010\u001f\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u000e\u0010\"\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u000e\u0010&\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010*\u001a\n ,*\u0004\u0018\u00010+0+X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020807X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010F\u001a\b\u0012\u0004\u0012\u00020807X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020UX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006`"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Arraylist;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "abcOrder", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "animationSpeed", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "backgroundColorAlphaValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "backgroundColorBlueValue", "backgroundColorGreenValue", "backgroundColorRedValue", "blurStrength", "blurValue", "brightnessValue", "cRainbowDistValue", "cRainbowSecValue", "caseValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "colorAlphaValue", "getColorAlphaValue", "()Lnet/ccbluex/liquidbounce/value/IntegerValue;", "colorBlueValue", "getColorBlueValue", "colorBlueValue2", "colorGreenValue", "getColorGreenValue", "colorGreenValue2", "colorModeValue", "colorRedValue", "getColorRedValue", "colorRedValue2", "fadeDistanceValue", "fadeOffset", "fadeSpeed", "fontRenderer", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;", "kotlin.jvm.PlatformType", "getFontRenderer", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;", "setFontRenderer", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;)V", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "gidentspeed", "hAnimation", "liquidSlowlyDistanceValue", "modules", "", "Lnet/ccbluex/liquidbounce/features/module/Module;", "nameBreak", "rectLeftValue", "rectRightValue", "saturationValue", "shadow", "shadowColorBlueValue", "shadowColorGreenValue", "shadowColorMode", "shadowColorRedValue", "shadowNoCutValue", "shadowShaderValue", "shadowStrength", "skyDistanceValue", "sortedModules", "spaceValue", "tags", "tagsArrayColor", "tagsStyleValue", "textBlue", "textBlue2", "textGreen", "textGreen2", "textHeightValue", "textRed", "textRed2", "textYValue", "vAnimation", "x2", "", "y2", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "getModName", "", "mod", "getModTag", "m", "updateElement", "", "Relaxed"})
public final class Arraylist
extends Element {
    private final ListValue colorModeValue;
    private final BoolValue blurValue;
    private final FloatValue blurStrength;
    private final BoolValue shadow;
    private final BoolValue shadowShaderValue;
    private final BoolValue shadowNoCutValue;
    private final IntegerValue shadowStrength;
    private final ListValue shadowColorMode;
    private final IntegerValue shadowColorRedValue;
    private final IntegerValue shadowColorGreenValue;
    private final IntegerValue shadowColorBlueValue;
    @NotNull
    private final IntegerValue colorRedValue;
    @NotNull
    private final IntegerValue colorGreenValue;
    @NotNull
    private final IntegerValue colorBlueValue;
    private final IntegerValue colorRedValue2;
    private final IntegerValue colorGreenValue2;
    private final IntegerValue colorBlueValue2;
    private final IntegerValue gidentspeed;
    @NotNull
    private final IntegerValue colorAlphaValue;
    private final FloatValue fadeOffset;
    private final FloatValue fadeSpeed;
    private final IntegerValue textRed;
    private final IntegerValue textGreen;
    private final IntegerValue textBlue;
    private final IntegerValue textRed2;
    private final IntegerValue textGreen2;
    private final IntegerValue textBlue2;
    private final FloatValue saturationValue;
    private final FloatValue brightnessValue;
    private final IntegerValue skyDistanceValue;
    private final IntegerValue cRainbowSecValue;
    private final IntegerValue cRainbowDistValue;
    private final IntegerValue liquidSlowlyDistanceValue;
    private final IntegerValue fadeDistanceValue;
    private final ListValue hAnimation;
    private final ListValue vAnimation;
    private final FloatValue animationSpeed;
    private final BoolValue nameBreak;
    private final BoolValue abcOrder;
    private final BoolValue tags;
    private final ListValue tagsStyleValue;
    private final IntegerValue backgroundColorRedValue;
    private final IntegerValue backgroundColorGreenValue;
    private final IntegerValue backgroundColorBlueValue;
    private final IntegerValue backgroundColorAlphaValue;
    private final ListValue rectRightValue;
    private final ListValue rectLeftValue;
    private final ListValue caseValue;
    private final FloatValue spaceValue;
    private final FloatValue textHeightValue;
    private final FloatValue textYValue;
    private final BoolValue tagsArrayColor;
    private final FontValue fontValue;
    private int x2;
    private float y2;
    private List<? extends Module> modules;
    private List<? extends Module> sortedModules;
    private IFontRenderer fontRenderer;

    @NotNull
    public final IntegerValue getColorRedValue() {
        return this.colorRedValue;
    }

    @NotNull
    public final IntegerValue getColorGreenValue() {
        return this.colorGreenValue;
    }

    @NotNull
    public final IntegerValue getColorBlueValue() {
        return this.colorBlueValue;
    }

    @NotNull
    public final IntegerValue getColorAlphaValue() {
        return this.colorAlphaValue;
    }

    public final IFontRenderer getFontRenderer() {
        return this.fontRenderer;
    }

    public final void setFontRenderer(IFontRenderer iFontRenderer) {
        this.fontRenderer = iFontRenderer;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @Nullable
    public Border drawElement() {
        IFontRenderer fontRenderer = (IFontRenderer)this.fontValue.get();
        int[] counter = new int[]{0};
        AWTFontRenderer.Companion.setAssumeNonVolatile(true);
        int delta = RenderUtils.deltaTime;
        String colorMode = (String)this.colorModeValue.get();
        String rectColorMode = (String)this.colorModeValue.get();
        int customColor = new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), ((Number)this.colorAlphaValue.get()).intValue()).getRGB();
        int rectCustomColor = new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), ((Number)this.colorAlphaValue.get()).intValue()).getRGB();
        float space = ((Number)this.spaceValue.get()).floatValue();
        float textHeight = ((Number)this.textHeightValue.get()).floatValue();
        float textY = ((Number)this.textYValue.get()).floatValue();
        int backgroundCustomColor = new Color(((Number)this.backgroundColorRedValue.get()).intValue(), ((Number)this.backgroundColorGreenValue.get()).intValue(), ((Number)this.backgroundColorBlueValue.get()).intValue(), ((Number)this.backgroundColorAlphaValue.get()).intValue()).getRGB();
        boolean textShadow = (Boolean)this.shadow.get();
        float textSpacer = textHeight + space;
        float saturation = ((Number)this.saturationValue.get()).floatValue();
        float brightness = ((Number)this.brightnessValue.get()).floatValue();
        int inx = 0;
        for (Module module : this.sortedModules) {
            if (module.getArray() && (module.getState() || module.getSlide() != 0.0f)) {
                String displayString = this.getModName(module);
                int width = fontRenderer.getStringWidth(displayString);
                switch ((String)this.hAnimation.get()) {
                    case "Astolfo": {
                        if (module.getState()) {
                            if (module.getSlide() < (float)width) {
                                Module module2 = module;
                                module2.setSlide(module2.getSlide() + ((Number)this.animationSpeed.get()).floatValue() * (float)delta);
                                module.setSlideStep((float)delta / 1.0f);
                            }
                        } else if (module.getSlide() > 0.0f) {
                            Module module3 = module;
                            module3.setSlide(module3.getSlide() - ((Number)this.animationSpeed.get()).floatValue() * (float)delta);
                            module.setSlideStep(0.0f);
                        }
                        if (!(module.getSlide() > (float)width)) break;
                        module.setSlide(width);
                        break;
                    }
                    case "Slide": {
                        if (module.getState()) {
                            if (!(module.getSlide() < (float)width)) break;
                            module.setSlide((float)AnimationUtils.animate(width, module.getSlide(), (double)((Number)this.animationSpeed.get()).floatValue() * 0.025 * (double)delta));
                            module.setSlideStep((float)delta / 1.0f);
                            break;
                        }
                        if (!(module.getSlide() > 0.0f)) break;
                        module.setSlide((float)AnimationUtils.animate(-((double)width), module.getSlide(), (double)((Number)this.animationSpeed.get()).floatValue() * 0.025 * (double)delta));
                        module.setSlideStep(0.0f);
                        break;
                    }
                    case "Default": {
                        if (module.getState()) {
                            if (!(module.getSlide() < (float)width)) break;
                            module.setSlide(AnimationUtils.easeOut(module.getSlideStep(), width) * (float)width);
                            Module module4 = module;
                            module4.setSlideStep(module4.getSlideStep() + (float)delta / 4.0f);
                            break;
                        }
                        if (!(module.getSlide() > 0.0f)) break;
                        module.setSlide(AnimationUtils.easeOut(module.getSlideStep(), width) * (float)width);
                        Module module5 = module;
                        module5.setSlideStep(module5.getSlideStep() - (float)delta / 4.0f);
                        break;
                    }
                    default: {
                        module.setSlide(module.getState() ? (float)width : 0.0f);
                        Module module6 = module;
                        module6.setSlideStep(module6.getSlideStep() + (float)(module.getState() ? delta : -delta));
                    }
                }
                module.setSlide(RangesKt.coerceIn(module.getSlide(), 0.0f, (float)width));
                module.setSlideStep(RangesKt.coerceIn(module.getSlideStep(), 0.0f, (float)width));
            }
            float yPos = (this.getSide().getVertical() == Side.Vertical.DOWN ? -textSpacer : textSpacer) * (float)(this.getSide().getVertical() == Side.Vertical.DOWN ? inx + 1 : inx);
            if (module.getArray() && module.getSlide() > 0.0f) {
                if (StringsKt.equals((String)this.vAnimation.get(), "Rise", true) && !module.getState()) {
                    yPos = (float)(-fontRenderer.getFontHeight()) - textY;
                }
                float size = (float)this.modules.size() * 0.02f;
                switch ((String)this.vAnimation.get()) {
                    case "LiquidSense": {
                        if (!module.getState()) break;
                        if (module.getHigt() < yPos) {
                            Module module7 = module;
                            module7.setHigt(module7.getHigt() + (size - Math.min(module.getHigt() * 0.002f, size - module.getHigt() * 1.0E-4f)) * (float)delta);
                            module.setHigt(Math.min(yPos, module.getHigt()));
                            break;
                        }
                        Module module8 = module;
                        module8.setHigt(module8.getHigt() - (size - Math.min(module.getHigt() * 0.002f, size - module.getHigt() * 1.0E-4f)) * (float)delta);
                        module.setHigt(Math.max(module.getHigt(), yPos));
                        break;
                    }
                    case "Slide": 
                    case "Rise": {
                        module.setHigt((float)AnimationUtils.animate(yPos, module.getHigt(), (double)((Number)this.animationSpeed.get()).floatValue() * 0.025 * (double)delta));
                        break;
                    }
                    case "Astolfo": {
                        if (module.getHigt() < yPos) {
                            Module module9 = module;
                            module9.setHigt(module9.getHigt() + ((Number)this.animationSpeed.get()).floatValue() / 2.0f * (float)delta);
                            module.setHigt(Math.min(yPos, module.getHigt()));
                            break;
                        }
                        Module module10 = module;
                        module10.setHigt(module10.getHigt() - ((Number)this.animationSpeed.get()).floatValue() / 2.0f * (float)delta);
                        module.setHigt(Math.max(module.getHigt(), yPos));
                        break;
                    }
                    default: {
                        module.setHigt(yPos);
                    }
                }
                ++inx;
                continue;
            }
            if (StringsKt.equals((String)this.vAnimation.get(), "rise", true)) continue;
            module.setHigt(yPos);
        }
        switch (Arraylist$WhenMappings.$EnumSwitchMapping$0[this.getSide().getHorizontal().ordinal()]) {
            case 1: 
            case 2: {
                int rectColor;
                int LiquidSlowly;
                Integer test;
                int moduleColor;
                void module;
                boolean bl;
                int index;
                int index$iv;
                int $i$f$forEachIndexed;
                Iterable $this$forEachIndexed$iv;
                float yP;
                if (((Boolean)this.shadowShaderValue.get()).booleanValue()) {
                    GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                    GL11.glPushMatrix();
                    ShadowUtils.INSTANCE.shadow(((Number)this.shadowStrength.get()).intValue(), new Function0<Unit>(this, textHeight, saturation, brightness, counter, colorMode, customColor){
                        final /* synthetic */ Arraylist this$0;
                        final /* synthetic */ float $textHeight;
                        final /* synthetic */ float $saturation;
                        final /* synthetic */ float $brightness;
                        final /* synthetic */ int[] $counter;
                        final /* synthetic */ String $colorMode;
                        final /* synthetic */ int $customColor;

                        /*
                         * WARNING - void declaration
                         */
                        public final void invoke() {
                            GL11.glPushMatrix();
                            GL11.glTranslated((double)this.this$0.getRenderX(), (double)this.this$0.getRenderY(), (double)0.0);
                            Iterable $this$forEachIndexed$iv = Arraylist.access$getModules$p(this.this$0);
                            boolean $i$f$forEachIndexed = false;
                            int index$iv = 0;
                            for (T item$iv : $this$forEachIndexed$iv) {
                                int n;
                                String string;
                                void module;
                                int n2 = index$iv++;
                                boolean bl = false;
                                if (n2 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                int n3 = n2;
                                Module module2 = (Module)item$iv;
                                int index = n3;
                                boolean bl2 = false;
                                float xPos = -module.getSlide() - (float)2;
                                String string2 = (String)Arraylist.access$getShadowColorMode$p(this.this$0).get();
                                float f = module.getHigt() + this.$textHeight;
                                float f2 = StringsKt.equals((String)Arraylist.access$getRectRightValue$p(this.this$0).get(), "right", true) ? -1.0f : 0.0f;
                                float f3 = module.getHigt();
                                float f4 = xPos - (float)(StringsKt.equals((String)Arraylist.access$getRectRightValue$p(this.this$0).get(), "right", true) ? 3 : 2);
                                boolean bl3 = false;
                                String string3 = string2;
                                if (string3 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                                Intrinsics.checkExpressionValueIsNotNull(string3.toLowerCase(), "(this as java.lang.String).toLowerCase()");
                                switch (string) {
                                    case "background": {
                                        n = new Color(((Number)Arraylist.access$getBackgroundColorRedValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getBackgroundColorGreenValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getBackgroundColorBlueValue$p(this.this$0).get()).intValue()).getRGB();
                                        break;
                                    }
                                    case "text": {
                                        Integer test;
                                        Color color = Color.getHSBColor(module.getHue(), this.$saturation, this.$brightness);
                                        Intrinsics.checkExpressionValueIsNotNull(color, "Color.getHSBColor(module\u2026, saturation, brightness)");
                                        int moduleColor = color.getRGB();
                                        int Sky = RenderUtils.SkyRainbow(this.$counter[0] * (((Number)Arraylist.access$getSkyDistanceValue$p(this.this$0).get()).intValue() * 50), ((Number)Arraylist.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)Arraylist.access$getBrightnessValue$p(this.this$0).get()).floatValue());
                                        int CRainbow = RenderUtils.getRainbowOpaque(((Number)Arraylist.access$getCRainbowSecValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)Arraylist.access$getBrightnessValue$p(this.this$0).get()).floatValue(), this.$counter[0] * (50 * ((Number)Arraylist.access$getCRainbowDistValue$p(this.this$0).get()).intValue()));
                                        int FadeColor = ColorUtils.fade(new Color(((Number)this.this$0.getColorRedValue().get()).intValue(), ((Number)this.this$0.getColorGreenValue().get()).intValue(), ((Number)this.this$0.getColorBlueValue().get()).intValue(), ((Number)this.this$0.getColorAlphaValue().get()).intValue()), index * ((Number)Arraylist.access$getFadeDistanceValue$p(this.this$0).get()).intValue(), 100).getRGB();
                                        this.$counter[0] = this.$counter[0] - 1;
                                        Color color2 = ColorUtils.LiquidSlowly(System.nanoTime(), index * ((Number)Arraylist.access$getLiquidSlowlyDistanceValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)Arraylist.access$getBrightnessValue$p(this.this$0).get()).floatValue());
                                        Integer n4 = test = color2 != null ? Integer.valueOf(color2.getRGB()) : null;
                                        if (n4 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        int LiquidSlowly = n4;
                                        if (StringsKt.equals(this.$colorMode, "Random", true)) {
                                            n = moduleColor;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "Sky", true)) {
                                            n = Sky;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "CRainbow", true)) {
                                            n = CRainbow;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "LiquidSlowly", true)) {
                                            n = LiquidSlowly;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "Fade", true)) {
                                            n = FadeColor;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "Gradinet", true)) {
                                            Color color3 = VisualUtils.getGradientOffset(new Color(((Number)Arraylist.access$getTextRed$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getTextGreen$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getTextBlue$p(this.this$0).get()).intValue()), new Color(((Number)Arraylist.access$getTextRed2$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getTextGreen2$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getTextBlue2$p(this.this$0).get()).intValue()), (double)index * (double)((Number)Arraylist.access$getFadeOffset$p(this.this$0).get()).floatValue());
                                            Intrinsics.checkExpressionValueIsNotNull(color3, "VisualUtils.getGradientO\u2026eOffset.get().toDouble())");
                                            n = color3.getRGB();
                                            break;
                                        }
                                        n = this.$customColor;
                                        break;
                                    }
                                    default: {
                                        n = new Color(((Number)Arraylist.access$getShadowColorRedValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getShadowColorGreenValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getShadowColorBlueValue$p(this.this$0).get()).intValue()).getRGB();
                                    }
                                }
                                RenderUtils.newDrawRect(f4, f3, f2, f, n);
                            }
                            GL11.glPopMatrix();
                            this.$counter[0] = 0;
                        }
                        {
                            this.this$0 = arraylist;
                            this.$textHeight = f;
                            this.$saturation = f2;
                            this.$brightness = f3;
                            this.$counter = nArray;
                            this.$colorMode = string;
                            this.$customColor = n;
                            super(0);
                        }
                    }, new Function0<Unit>(this, textHeight){
                        final /* synthetic */ Arraylist this$0;
                        final /* synthetic */ float $textHeight;

                        /*
                         * WARNING - void declaration
                         */
                        public final void invoke() {
                            if (!((Boolean)Arraylist.access$getShadowNoCutValue$p(this.this$0).get()).booleanValue()) {
                                GL11.glPushMatrix();
                                GL11.glTranslated((double)this.this$0.getRenderX(), (double)this.this$0.getRenderY(), (double)0.0);
                                Iterable $this$forEachIndexed$iv = Arraylist.access$getModules$p(this.this$0);
                                boolean $i$f$forEachIndexed = false;
                                int index$iv = 0;
                                for (T item$iv : $this$forEachIndexed$iv) {
                                    void module;
                                    int n = index$iv++;
                                    boolean bl = false;
                                    if (n < 0) {
                                        CollectionsKt.throwIndexOverflow();
                                    }
                                    int n2 = n;
                                    Module module2 = (Module)item$iv;
                                    int index = n2;
                                    boolean bl2 = false;
                                    float xPos = -module.getSlide() - (float)2;
                                    RenderUtils.quickDrawRect(xPos - (float)(StringsKt.equals((String)Arraylist.access$getRectRightValue$p(this.this$0).get(), "right", true) ? 3 : 2), module.getHigt(), StringsKt.equals((String)Arraylist.access$getRectRightValue$p(this.this$0).get(), "right", true) ? -1.0f : 0.0f, module.getHigt() + this.$textHeight);
                                }
                                GL11.glPopMatrix();
                            }
                        }
                        {
                            this.this$0 = arraylist;
                            this.$textHeight = f;
                            super(0);
                        }
                    });
                    GL11.glPopMatrix();
                    GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
                }
                if (((Boolean)this.blurValue.get()).booleanValue()) {
                    GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                    GL11.glPushMatrix();
                    float f = (float)this.getRenderX();
                    float floatY = (float)this.getRenderY();
                    yP = 0.0f;
                    float xP = 0.0f;
                    $this$forEachIndexed$iv = this.modules;
                    $i$f$forEachIndexed = 0;
                    index$iv = 0;
                    for (Module item$iv22 : $this$forEachIndexed$iv) {
                        void module11;
                        int n = index$iv++;
                        boolean bl2 = false;
                        if (n < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        int n2 = n;
                        Module module12 = item$iv22;
                        int index2 = n2;
                        boolean bl3 = false;
                        String dString = this.getModName((Module)module11);
                        float wid = (float)fontRenderer.getStringWidth(dString) + 2.0f;
                        float yPos = this.getSide().getVertical() == Side.Vertical.DOWN ? -textSpacer : textSpacer * (float)(this.getSide().getVertical() == Side.Vertical.DOWN ? index2 + 1 : index2);
                        yP += yPos;
                        xP = Math.min(xP, -wid);
                    }
                    BlurUtils.blur(f, floatY, f + xP, floatY + yP, ((Number)this.blurStrength.get()).floatValue(), false, new Function0<Unit>(this, f, floatY, textHeight){
                        final /* synthetic */ Arraylist this$0;
                        final /* synthetic */ float $floatX;
                        final /* synthetic */ float $floatY;
                        final /* synthetic */ float $textHeight;

                        /*
                         * WARNING - void declaration
                         */
                        public final void invoke() {
                            Iterable $this$forEachIndexed$iv = Arraylist.access$getModules$p(this.this$0);
                            boolean $i$f$forEachIndexed = false;
                            int index$iv = 0;
                            for (T item$iv : $this$forEachIndexed$iv) {
                                void module;
                                int n = index$iv++;
                                boolean bl = false;
                                if (n < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                int n2 = n;
                                Module module2 = (Module)item$iv;
                                int index = n2;
                                boolean bl2 = false;
                                float xPos = -module.getSlide() - (float)2;
                                RenderUtils.quickDrawRect(this.$floatX + xPos - (float)(StringsKt.equals((String)Arraylist.access$getRectRightValue$p(this.this$0).get(), "right", true) ? 3 : 2), this.$floatY + module.getHigt(), this.$floatX + (StringsKt.equals((String)Arraylist.access$getRectRightValue$p(this.this$0).get(), "right", true) ? -1.0f : 0.0f), this.$floatY + module.getHigt() + this.$textHeight);
                            }
                        }
                        {
                            this.this$0 = arraylist;
                            this.$floatX = f;
                            this.$floatY = f2;
                            this.$textHeight = f3;
                            super(0);
                        }
                    });
                    GL11.glPopMatrix();
                    GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
                }
                Iterable iterable = this.modules;
                boolean $i$f$forEachIndexed2 = false;
                int index$iv2 = 0;
                for (Object item$iv3 : iterable) {
                    int n;
                    int n3;
                    Module item$iv22;
                    $i$f$forEachIndexed = index$iv2++;
                    index$iv = 0;
                    if ($i$f$forEachIndexed < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    int n4 = $i$f$forEachIndexed;
                    item$iv22 = (Module)item$iv3;
                    index = n4;
                    bl = false;
                    String displayString = this.getModName((Module)module);
                    int width = fontRenderer.getStringWidth(displayString);
                    float xPos = -module.getSlide() - (float)2;
                    Color color = Color.getHSBColor(module.getHue(), saturation, brightness);
                    Intrinsics.checkExpressionValueIsNotNull(color, "Color.getHSBColor(module\u2026, saturation, brightness)");
                    moduleColor = color.getRGB();
                    int Sky = 0;
                    Sky = RenderUtils.SkyRainbow(counter[0] * (((Number)this.skyDistanceValue.get()).intValue() * 50), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                    int CRainbow = 0;
                    CRainbow = RenderUtils.getRainbowOpaque(((Number)this.cRainbowSecValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), counter[0] * (50 * ((Number)this.cRainbowDistValue.get()).intValue()));
                    int FadeColor = ColorUtils.fade(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), ((Number)this.colorAlphaValue.get()).intValue()), index * ((Number)this.fadeDistanceValue.get()).intValue(), 100).getRGB();
                    counter[0] = counter[0] - 1;
                    Color color2 = ColorUtils.LiquidSlowly(System.nanoTime(), index * ((Number)this.liquidSlowlyDistanceValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                    Integer n5 = test = color2 != null ? Integer.valueOf(color2.getRGB()) : null;
                    if (n5 == null) {
                        Intrinsics.throwNpe();
                    }
                    LiquidSlowly = n5;
                    RenderUtils.drawRect(xPos - (float)(StringsKt.equals((String)this.rectRightValue.get(), "right", true) ? 3 : 2), module.getHigt(), StringsKt.equals((String)this.rectRightValue.get(), "right", true) ? -1.0f : 0.0f, module.getHigt() + textHeight, backgroundCustomColor);
                    float f = xPos - (float)(StringsKt.equals((String)this.rectRightValue.get(), "right", true) ? 1 : 0);
                    float f2 = module.getHigt() + textY;
                    if (StringsKt.equals(colorMode, "Random", true)) {
                        n3 = moduleColor;
                    } else if (StringsKt.equals(colorMode, "Sky", true)) {
                        n3 = Sky;
                    } else if (StringsKt.equals(colorMode, "CRainbow", true)) {
                        n3 = CRainbow;
                    } else if (StringsKt.equals(colorMode, "LiquidSlowly", true)) {
                        n3 = LiquidSlowly;
                    } else if (StringsKt.equals(colorMode, "Fade", true)) {
                        n3 = FadeColor;
                    } else if (StringsKt.equals(colorMode, "Gradinet", true)) {
                        Color color3 = VisualUtils.getGradientOffset(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), 1), new Color(((Number)this.colorRedValue2.get()).intValue(), ((Number)this.colorGreenValue2.get()).intValue(), ((Number)this.colorBlueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)this.gidentspeed.get()).intValue() + (double)(module.getHigt() / (float)fontRenderer.getFontHeight())) / (double)10);
                        Intrinsics.checkExpressionValueIsNotNull(color3, "VisualUtils.getGradientO\u2026                 ) / 10))");
                        n3 = color3.getRGB();
                    } else {
                        n3 = customColor;
                    }
                    fontRenderer.drawString(displayString, f, f2, n3, textShadow);
                    if (StringsKt.equals((String)this.rectRightValue.get(), "none", true)) continue;
                    if (StringsKt.equals(rectColorMode, "Random", true)) {
                        n = moduleColor;
                    } else if (StringsKt.equals(rectColorMode, "Sky", true)) {
                        n = Sky;
                    } else if (StringsKt.equals(rectColorMode, "CRainbow", true)) {
                        n = CRainbow;
                    } else if (StringsKt.equals(rectColorMode, "LiquidSlowly", true)) {
                        n = LiquidSlowly;
                    } else if (StringsKt.equals(rectColorMode, "Fade", true)) {
                        n = FadeColor;
                    } else if (StringsKt.equals(rectColorMode, "Gradinet", true)) {
                        Color color4 = VisualUtils.getGradientOffset(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), 1), new Color(((Number)this.colorRedValue2.get()).intValue(), ((Number)this.colorGreenValue2.get()).intValue(), ((Number)this.colorBlueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)this.gidentspeed.get()).intValue() + (double)(module.getHigt() / (float)fontRenderer.getFontHeight())) / (double)10);
                        Intrinsics.checkExpressionValueIsNotNull(color4, "VisualUtils.getGradientO\u2026                 ) / 10))");
                        n = color4.getRGB();
                    } else {
                        n = rectCustomColor;
                    }
                    rectColor = n;
                    if (StringsKt.equals((String)this.rectRightValue.get(), "left", true)) {
                        RenderUtils.drawRect(xPos - (float)3, module.getHigt(), xPos - (float)2, module.getHigt() + textHeight, rectColor);
                        continue;
                    }
                    if (StringsKt.equals((String)this.rectRightValue.get(), "right", true)) {
                        RenderUtils.drawRect(-1.0f, module.getHigt(), 0.0f, module.getHigt() + textHeight, rectColor);
                        continue;
                    }
                    if (StringsKt.equals((String)this.rectRightValue.get(), "outline", true)) {
                        RenderUtils.drawRect(-1.0f, module.getHigt() - 1.0f, 0.0f, module.getHigt() + textHeight, rectColor);
                        RenderUtils.drawRect(xPos - (float)3, module.getHigt(), xPos - (float)2, module.getHigt() + textHeight, rectColor);
                        if (Intrinsics.areEqual(module, this.modules.get(0)) ^ true) {
                            String displayStrings = this.getModName(this.modules.get(index - 1));
                            RenderUtils.drawRect(xPos - (float)3 - (float)(fontRenderer.getStringWidth(displayStrings) - fontRenderer.getStringWidth(displayString)), module.getHigt(), xPos - (float)2, module.getHigt() + 1.0f, rectColor);
                            if (!Intrinsics.areEqual(module, this.modules.get(this.modules.size() - 1))) continue;
                            RenderUtils.drawRect(xPos - (float)3, module.getHigt() + textHeight, 0.0f, module.getHigt() + textHeight + 1.0f, rectColor);
                            continue;
                        }
                        RenderUtils.drawRect(xPos - (float)3, module.getHigt(), 0.0f, module.getHigt() - 1.0f, rectColor);
                        continue;
                    }
                    if (StringsKt.equals((String)this.rectRightValue.get(), "special", true)) {
                        if (Intrinsics.areEqual(module, this.modules.get(0))) {
                            RenderUtils.drawRect(xPos - (float)2, module.getHigt(), 0.0f, module.getHigt() - 1.0f, rectColor);
                        }
                        if (!Intrinsics.areEqual(module, this.modules.get(this.modules.size() - 1))) continue;
                        RenderUtils.drawRect(xPos - (float)2, module.getHigt() + textHeight, 0.0f, module.getHigt() + textHeight + 1.0f, rectColor);
                        continue;
                    }
                    if (!StringsKt.equals((String)this.rectRightValue.get(), "top", true) || !Intrinsics.areEqual(module, this.modules.get(0))) continue;
                    RenderUtils.drawRect(xPos - (float)2, module.getHigt(), 0.0f, module.getHigt() - 1.0f, rectColor);
                }
                break;
            }
            case 3: {
                int rectColor;
                int LiquidSlowly;
                Integer test;
                int moduleColor;
                void module;
                boolean bl;
                int index;
                int index$iv;
                int $i$f$forEachIndexed;
                Iterable $this$forEachIndexed$iv;
                float yP;
                if (((Boolean)this.shadowShaderValue.get()).booleanValue()) {
                    GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                    GL11.glPushMatrix();
                    ShadowUtils.INSTANCE.shadow(((Number)this.shadowStrength.get()).intValue(), new Function0<Unit>(this, fontRenderer, textHeight, saturation, brightness, counter, colorMode, customColor){
                        final /* synthetic */ Arraylist this$0;
                        final /* synthetic */ IFontRenderer $fontRenderer;
                        final /* synthetic */ float $textHeight;
                        final /* synthetic */ float $saturation;
                        final /* synthetic */ float $brightness;
                        final /* synthetic */ int[] $counter;
                        final /* synthetic */ String $colorMode;
                        final /* synthetic */ int $customColor;

                        /*
                         * WARNING - void declaration
                         */
                        public final void invoke() {
                            GL11.glPushMatrix();
                            GL11.glTranslated((double)this.this$0.getRenderX(), (double)this.this$0.getRenderY(), (double)0.0);
                            Iterable $this$forEachIndexed$iv = Arraylist.access$getModules$p(this.this$0);
                            boolean $i$f$forEachIndexed = false;
                            int index$iv = 0;
                            for (T item$iv : $this$forEachIndexed$iv) {
                                int n;
                                String string;
                                void module;
                                int n2 = index$iv++;
                                boolean bl = false;
                                if (n2 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                int n3 = n2;
                                Module module2 = (Module)item$iv;
                                int index = n3;
                                boolean bl2 = false;
                                String displayString = this.this$0.getModName((Module)module);
                                int width = this.$fontRenderer.getStringWidth(displayString);
                                float xPos = -((float)width - module.getSlide()) + (float)(StringsKt.equals((String)Arraylist.access$getRectLeftValue$p(this.this$0).get(), "left", true) ? 3 : 2);
                                String string2 = (String)Arraylist.access$getShadowColorMode$p(this.this$0).get();
                                float f = module.getHigt() + this.$textHeight;
                                float f2 = xPos + (float)width + (StringsKt.equals((String)Arraylist.access$getRectLeftValue$p(this.this$0).get(), "right", true) ? 3.0f : 2.0f);
                                float f3 = module.getHigt();
                                float f4 = 0.0f;
                                boolean bl3 = false;
                                String string3 = string2;
                                if (string3 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                                Intrinsics.checkExpressionValueIsNotNull(string3.toLowerCase(), "(this as java.lang.String).toLowerCase()");
                                switch (string) {
                                    case "background": {
                                        n = new Color(((Number)Arraylist.access$getBackgroundColorRedValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getBackgroundColorGreenValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getBackgroundColorBlueValue$p(this.this$0).get()).intValue()).getRGB();
                                        break;
                                    }
                                    case "text": {
                                        Integer test;
                                        Color color = Color.getHSBColor(module.getHue(), this.$saturation, this.$brightness);
                                        Intrinsics.checkExpressionValueIsNotNull(color, "Color.getHSBColor(module\u2026, saturation, brightness)");
                                        int moduleColor = color.getRGB();
                                        int Sky = RenderUtils.SkyRainbow(this.$counter[0] * (((Number)Arraylist.access$getSkyDistanceValue$p(this.this$0).get()).intValue() * 50), ((Number)Arraylist.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)Arraylist.access$getBrightnessValue$p(this.this$0).get()).floatValue());
                                        int CRainbow = RenderUtils.getRainbowOpaque(((Number)Arraylist.access$getCRainbowSecValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)Arraylist.access$getBrightnessValue$p(this.this$0).get()).floatValue(), this.$counter[0] * (50 * ((Number)Arraylist.access$getCRainbowDistValue$p(this.this$0).get()).intValue()));
                                        int FadeColor = ColorUtils.fade(new Color(((Number)this.this$0.getColorRedValue().get()).intValue(), ((Number)this.this$0.getColorGreenValue().get()).intValue(), ((Number)this.this$0.getColorBlueValue().get()).intValue(), ((Number)this.this$0.getColorAlphaValue().get()).intValue()), index * ((Number)Arraylist.access$getFadeDistanceValue$p(this.this$0).get()).intValue(), 100).getRGB();
                                        this.$counter[0] = this.$counter[0] - 1;
                                        Color color2 = ColorUtils.LiquidSlowly(System.nanoTime(), index * ((Number)Arraylist.access$getLiquidSlowlyDistanceValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)Arraylist.access$getBrightnessValue$p(this.this$0).get()).floatValue());
                                        Integer n4 = test = color2 != null ? Integer.valueOf(color2.getRGB()) : null;
                                        if (n4 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        int LiquidSlowly = n4;
                                        if (StringsKt.equals(this.$colorMode, "Random", true)) {
                                            n = moduleColor;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "Sky", true)) {
                                            n = Sky;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "CRainbow", true)) {
                                            n = CRainbow;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "LiquidSlowly", true)) {
                                            n = LiquidSlowly;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "Fade", true)) {
                                            n = FadeColor;
                                            break;
                                        }
                                        if (StringsKt.equals(this.$colorMode, "Gradinet", true)) {
                                            Color color3 = VisualUtils.getGradientOffset(new Color(((Number)this.this$0.getColorRedValue().get()).intValue(), ((Number)this.this$0.getColorGreenValue().get()).intValue(), ((Number)this.this$0.getColorBlueValue().get()).intValue(), 1), new Color(((Number)Arraylist.access$getColorRedValue2$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getColorGreenValue2$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getColorBlueValue2$p(this.this$0).get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Arraylist.access$getGidentspeed$p(this.this$0).get()).intValue() + (double)(module.getHigt() / (float)this.$fontRenderer.getFontHeight())) / (double)10);
                                            Intrinsics.checkExpressionValueIsNotNull(color3, "VisualUtils.getGradientO\u2026                 ) / 10))");
                                            n = color3.getRGB();
                                            break;
                                        }
                                        n = this.$customColor;
                                        break;
                                    }
                                    default: {
                                        n = new Color(((Number)Arraylist.access$getShadowColorRedValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getShadowColorGreenValue$p(this.this$0).get()).intValue(), ((Number)Arraylist.access$getShadowColorBlueValue$p(this.this$0).get()).intValue()).getRGB();
                                    }
                                }
                                RenderUtils.drawRect(f4, f3, f2, f, n);
                            }
                            GL11.glPopMatrix();
                        }
                        {
                            this.this$0 = arraylist;
                            this.$fontRenderer = iFontRenderer;
                            this.$textHeight = f;
                            this.$saturation = f2;
                            this.$brightness = f3;
                            this.$counter = nArray;
                            this.$colorMode = string;
                            this.$customColor = n;
                            super(0);
                        }
                    }, new Function0<Unit>(this, fontRenderer, textHeight){
                        final /* synthetic */ Arraylist this$0;
                        final /* synthetic */ IFontRenderer $fontRenderer;
                        final /* synthetic */ float $textHeight;

                        /*
                         * WARNING - void declaration
                         */
                        public final void invoke() {
                            if (!((Boolean)Arraylist.access$getShadowNoCutValue$p(this.this$0).get()).booleanValue()) {
                                GL11.glPushMatrix();
                                GL11.glTranslated((double)this.this$0.getRenderX(), (double)this.this$0.getRenderY(), (double)0.0);
                                Iterable $this$forEachIndexed$iv = Arraylist.access$getModules$p(this.this$0);
                                boolean $i$f$forEachIndexed = false;
                                int index$iv = 0;
                                for (T item$iv : $this$forEachIndexed$iv) {
                                    void module;
                                    int n = index$iv++;
                                    boolean bl = false;
                                    if (n < 0) {
                                        CollectionsKt.throwIndexOverflow();
                                    }
                                    int n2 = n;
                                    Module module2 = (Module)item$iv;
                                    int index = n2;
                                    boolean bl2 = false;
                                    String displayString = this.this$0.getModName((Module)module);
                                    int width = this.$fontRenderer.getStringWidth(displayString);
                                    float xPos = -((float)width - module.getSlide()) + (float)(StringsKt.equals((String)Arraylist.access$getRectLeftValue$p(this.this$0).get(), "left", true) ? 3 : 2);
                                    RenderUtils.quickDrawRect(0.0f, module.getHigt(), xPos + (float)width + (float)(StringsKt.equals((String)Arraylist.access$getRectLeftValue$p(this.this$0).get(), "right", true) ? 3 : 2), module.getHigt() + this.$textHeight);
                                }
                                GL11.glPopMatrix();
                            }
                        }
                        {
                            this.this$0 = arraylist;
                            this.$fontRenderer = iFontRenderer;
                            this.$textHeight = f;
                            super(0);
                        }
                    });
                    GL11.glPopMatrix();
                    GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
                }
                if (((Boolean)this.blurValue.get()).booleanValue()) {
                    GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                    GL11.glPushMatrix();
                    float f = (float)this.getRenderX();
                    float floatY = (float)this.getRenderY();
                    yP = 0.0f;
                    float xP = 0.0f;
                    $this$forEachIndexed$iv = this.modules;
                    $i$f$forEachIndexed = 0;
                    index$iv = 0;
                    for (Module item$iv2 : $this$forEachIndexed$iv) {
                        void module13;
                        index = index$iv++;
                        bl = false;
                        if (index < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        int displayString = index;
                        Module width = item$iv2;
                        int index3 = displayString;
                        boolean bl4 = false;
                        String dString = this.getModName((Module)module13);
                        float wid = (float)fontRenderer.getStringWidth(dString) + 2.0f;
                        float yPos = this.getSide().getVertical() == Side.Vertical.DOWN ? -textSpacer : textSpacer * (float)(this.getSide().getVertical() == Side.Vertical.DOWN ? index3 + 1 : index3);
                        yP += yPos;
                        xP = Math.max(xP, wid);
                    }
                    BlurUtils.blur(f, floatY, f + xP, floatY + yP, ((Number)this.blurStrength.get()).floatValue(), false, new Function0<Unit>(this, fontRenderer, f, floatY, textHeight){
                        final /* synthetic */ Arraylist this$0;
                        final /* synthetic */ IFontRenderer $fontRenderer;
                        final /* synthetic */ float $floatX;
                        final /* synthetic */ float $floatY;
                        final /* synthetic */ float $textHeight;

                        /*
                         * WARNING - void declaration
                         */
                        public final void invoke() {
                            Iterable $this$forEachIndexed$iv = Arraylist.access$getModules$p(this.this$0);
                            boolean $i$f$forEachIndexed = false;
                            int index$iv = 0;
                            for (T item$iv : $this$forEachIndexed$iv) {
                                void module;
                                int n = index$iv++;
                                boolean bl = false;
                                if (n < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                int n2 = n;
                                Module module2 = (Module)item$iv;
                                int index = n2;
                                boolean bl2 = false;
                                String displayString = this.this$0.getModName((Module)module);
                                int width = this.$fontRenderer.getStringWidth(displayString);
                                float xPos = -((float)width - module.getSlide()) + (float)(StringsKt.equals((String)Arraylist.access$getRectLeftValue$p(this.this$0).get(), "left", true) ? 3 : 2);
                                RenderUtils.quickDrawRect(this.$floatX, this.$floatY + module.getHigt(), this.$floatX + xPos + (float)width + (float)(StringsKt.equals((String)Arraylist.access$getRectLeftValue$p(this.this$0).get(), "right", true) ? 3 : 2), this.$floatY + module.getHigt() + this.$textHeight);
                            }
                        }
                        {
                            this.this$0 = arraylist;
                            this.$fontRenderer = iFontRenderer;
                            this.$floatX = f;
                            this.$floatY = f2;
                            this.$textHeight = f3;
                            super(0);
                        }
                    });
                    GL11.glPopMatrix();
                    GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
                }
                Iterable iterable = this.modules;
                boolean $i$f$forEachIndexed3 = false;
                int index$iv3 = 0;
                for (Object item$iv : iterable) {
                    int n;
                    int n6;
                    Module item$iv2;
                    int n7 = index$iv3++;
                    boolean bl3 = false;
                    if (n7 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    int n8 = n7;
                    item$iv2 = (Module)item$iv;
                    index = n8;
                    boolean bl6 = false;
                    String displayString = this.getModName((Module)module);
                    int width = fontRenderer.getStringWidth(displayString);
                    float xPos = -((float)width - module.getSlide()) + (float)(StringsKt.equals((String)this.rectLeftValue.get(), "left", true) ? 3 : 2);
                    Color color = Color.getHSBColor(module.getHue(), saturation, brightness);
                    Intrinsics.checkExpressionValueIsNotNull(color, "Color.getHSBColor(module\u2026, saturation, brightness)");
                    moduleColor = color.getRGB();
                    int Sky = 0;
                    Sky = RenderUtils.SkyRainbow(counter[0] * (((Number)this.skyDistanceValue.get()).intValue() * 50), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                    int CRainbow = 0;
                    CRainbow = RenderUtils.getRainbowOpaque(((Number)this.cRainbowSecValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), counter[0] * (50 * ((Number)this.cRainbowDistValue.get()).intValue()));
                    int FadeColor = ColorUtils.fade(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), ((Number)this.colorAlphaValue.get()).intValue()), index * ((Number)this.fadeDistanceValue.get()).intValue(), 100).getRGB();
                    counter[0] = counter[0] - 1;
                    Color color5 = ColorUtils.LiquidSlowly(System.nanoTime(), index * ((Number)this.liquidSlowlyDistanceValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                    Integer n9 = test = color5 != null ? Integer.valueOf(color5.getRGB()) : null;
                    if (n9 == null) {
                        Intrinsics.throwNpe();
                    }
                    LiquidSlowly = n9;
                    RenderUtils.drawRect(0.0f, module.getHigt(), xPos + (float)width + (float)(StringsKt.equals((String)this.rectLeftValue.get(), "right", true) ? 3 : 2), module.getHigt() + textHeight, backgroundCustomColor);
                    float f = module.getHigt() + textY;
                    if (StringsKt.equals(colorMode, "Random", true)) {
                        n6 = moduleColor;
                    } else if (StringsKt.equals(colorMode, "Sky", true)) {
                        n6 = Sky;
                    } else if (StringsKt.equals(colorMode, "CRainbow", true)) {
                        n6 = CRainbow;
                    } else if (StringsKt.equals(colorMode, "LiquidSlowly", true)) {
                        n6 = LiquidSlowly;
                    } else if (StringsKt.equals(colorMode, "Fade", true)) {
                        n6 = FadeColor;
                    } else if (StringsKt.equals(colorMode, "Gradinet", true)) {
                        Color color6 = VisualUtils.getGradientOffset(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), 1), new Color(((Number)this.colorRedValue2.get()).intValue(), ((Number)this.colorGreenValue2.get()).intValue(), ((Number)this.colorBlueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)this.gidentspeed.get()).intValue() + (double)(module.getHigt() / (float)fontRenderer.getFontHeight())) / (double)10);
                        Intrinsics.checkExpressionValueIsNotNull(color6, "VisualUtils.getGradientO\u2026                 ) / 10))");
                        n6 = color6.getRGB();
                    } else {
                        n6 = customColor;
                    }
                    fontRenderer.drawString(displayString, xPos, f, n6, textShadow);
                    if (StringsKt.equals((String)this.rectLeftValue.get(), "none", true)) continue;
                    if (StringsKt.equals(rectColorMode, "Random", true)) {
                        n = moduleColor;
                    } else if (StringsKt.equals(rectColorMode, "Sky", true)) {
                        n = Sky;
                    } else if (StringsKt.equals(rectColorMode, "CRainbow", true)) {
                        n = CRainbow;
                    } else if (StringsKt.equals(rectColorMode, "LiquidSlowly", true)) {
                        n = LiquidSlowly;
                    } else if (StringsKt.equals(rectColorMode, "Fade", true)) {
                        n = FadeColor;
                    } else if (StringsKt.equals(rectColorMode, "Gradinet", true)) {
                        Color color7 = VisualUtils.getGradientOffset(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), 1), new Color(((Number)this.colorRedValue2.get()).intValue(), ((Number)this.colorGreenValue2.get()).intValue(), ((Number)this.colorBlueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)this.gidentspeed.get()).intValue() + (double)(module.getHigt() / (float)fontRenderer.getFontHeight())) / (double)10);
                        Intrinsics.checkExpressionValueIsNotNull(color7, "VisualUtils.getGradientO\u2026                 ) / 10))");
                        n = color7.getRGB();
                    } else {
                        n = rectCustomColor;
                    }
                    rectColor = n;
                    if (StringsKt.equals((String)this.rectLeftValue.get(), "left", true)) {
                        RenderUtils.drawRect(0.0f, module.getHigt() - 1.0f, 1.0f, module.getHigt() + textHeight, rectColor);
                        continue;
                    }
                    if (!StringsKt.equals((String)this.rectLeftValue.get(), "right", true)) continue;
                    RenderUtils.drawRect(xPos + (float)width + (float)2, module.getHigt(), xPos + (float)width + (float)2 + 1.0f, module.getHigt() + textHeight, rectColor);
                }
                break;
            }
        }
        if (MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen())) {
            this.x2 = Integer.MIN_VALUE;
            if (this.modules.isEmpty()) {
                return this.getSide().getHorizontal() == Side.Horizontal.LEFT ? new Border(0.0f, -1.0f, 20.0f, 20.0f) : new Border(0.0f, -1.0f, -20.0f, 20.0f);
            }
            for (Module module : this.modules) {
                switch (Arraylist$WhenMappings.$EnumSwitchMapping$1[this.getSide().getHorizontal().ordinal()]) {
                    case 1: 
                    case 2: {
                        int xPos = -((int)module.getSlide()) - 2;
                        if (this.x2 != Integer.MIN_VALUE && xPos >= this.x2) break;
                        this.x2 = xPos;
                        break;
                    }
                    case 3: {
                        int xPos = (int)module.getSlide() + 14;
                        if (this.x2 != Integer.MIN_VALUE && xPos <= this.x2) break;
                        this.x2 = xPos;
                        break;
                    }
                }
            }
            this.y2 = (this.getSide().getVertical() == Side.Vertical.DOWN ? -textSpacer : textSpacer) * (float)this.modules.size();
            return new Border(0.0f, 0.0f, (float)this.x2 - 7.0f, this.y2 - (this.getSide().getVertical() == Side.Vertical.DOWN ? 1.0f : 0.0f));
        }
        AWTFontRenderer.Companion.setAssumeNonVolatile(false);
        GlStateManager.func_179117_G();
        return null;
    }

    @Override
    public void updateElement() {
        List list;
        Iterable $this$sortedBy$iv;
        boolean $i$f$sortedBy;
        List list2;
        Arraylist arraylist;
        List list3;
        Iterable $this$filterTo$iv$iv;
        Iterable iterable;
        Iterable $this$filter$iv;
        boolean $i$f$filter;
        Arraylist arraylist2;
        Arraylist arraylist3 = this;
        if (((Boolean)this.abcOrder.get()).booleanValue()) {
            Iterable iterable2 = LiquidBounce.INSTANCE.getModuleManager().getModules();
            arraylist2 = arraylist3;
            $i$f$filter = false;
            iterable = $this$filter$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterTo = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                Module it = (Module)element$iv$iv;
                boolean bl = false;
                if (!(it.getArray() && (StringsKt.equals((String)this.hAnimation.get(), "none", true) ? it.getState() : it.getSlide() > 0.0f))) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            list3 = (List)destination$iv$iv;
            arraylist = arraylist2;
            list2 = list3;
        } else {
            $this$filter$iv = LiquidBounce.INSTANCE.getModuleManager().getModules();
            arraylist2 = arraylist3;
            $i$f$filter = false;
            $this$filterTo$iv$iv = $this$filter$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterTo = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                Module it = (Module)element$iv$iv;
                boolean bl = false;
                if (!(it.getArray() && (StringsKt.equals((String)this.hAnimation.get(), "none", true) ? it.getState() : it.getSlide() > 0.0f))) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            list3 = (List)destination$iv$iv;
            $this$filter$iv = list3;
            $i$f$sortedBy = false;
            iterable = $this$sortedBy$iv;
            boolean bl = false;
            Comparator comparator = new Comparator<T>(this){
                final /* synthetic */ Arraylist this$0;
                {
                    this.this$0 = arraylist;
                }

                public final int compare(T a, T b) {
                    boolean bl = false;
                    Module it = (Module)a;
                    boolean bl2 = false;
                    Comparable comparable = Integer.valueOf(-((IFontRenderer)Arraylist.access$getFontValue$p(this.this$0).get()).getStringWidth(this.this$0.getModName(it)));
                    it = (Module)b;
                    Comparable comparable2 = comparable;
                    bl2 = false;
                    Integer n = -((IFontRenderer)Arraylist.access$getFontValue$p(this.this$0).get()).getStringWidth(this.this$0.getModName(it));
                    return ComparisonsKt.compareValues(comparable2, (Comparable)n);
                }
            };
            list3 = CollectionsKt.sortedWith(iterable, comparator);
            arraylist = arraylist2;
            list2 = list3;
        }
        arraylist.modules = list2;
        Arraylist arraylist4 = this;
        if (((Boolean)this.abcOrder.get()).booleanValue()) {
            list = CollectionsKt.toList((Iterable)LiquidBounce.INSTANCE.getModuleManager().getModules());
        } else {
            $this$sortedBy$iv = LiquidBounce.INSTANCE.getModuleManager().getModules();
            arraylist2 = arraylist4;
            $i$f$sortedBy = false;
            iterable = $this$sortedBy$iv;
            boolean bl = false;
            Comparator comparator = new Comparator<T>(this){
                final /* synthetic */ Arraylist this$0;
                {
                    this.this$0 = arraylist;
                }

                public final int compare(T a, T b) {
                    boolean bl = false;
                    Module it = (Module)a;
                    boolean bl2 = false;
                    Comparable comparable = Integer.valueOf(-((IFontRenderer)Arraylist.access$getFontValue$p(this.this$0).get()).getStringWidth(this.this$0.getModName(it)));
                    it = (Module)b;
                    Comparable comparable2 = comparable;
                    bl2 = false;
                    Integer n = -((IFontRenderer)Arraylist.access$getFontValue$p(this.this$0).get()).getStringWidth(this.this$0.getModName(it));
                    return ComparisonsKt.compareValues(comparable2, (Comparable)n);
                }
            };
            list3 = CollectionsKt.sortedWith(iterable, comparator);
            arraylist4 = arraylist2;
            list = CollectionsKt.toList(list3);
        }
        arraylist4.sortedModules = list;
    }

    private final String getModTag(Module m) {
        if (!((Boolean)this.tags.get()).booleanValue() || m.getTag() == null) {
            return "";
        }
        String returnTag = ' ' + ((Boolean)this.tagsArrayColor.get() != false ? "" : "\u00a77");
        if (!StringsKt.equals((String)this.tagsStyleValue.get(), "default", true)) {
            returnTag = returnTag + String.valueOf(((String)this.tagsStyleValue.get()).charAt(0)) + (StringsKt.equals((String)this.tagsStyleValue.get(), "-", true) || StringsKt.equals((String)this.tagsStyleValue.get(), "|", true) ? " " : "");
        }
        returnTag = returnTag + m.getTag();
        if (!(StringsKt.equals((String)this.tagsStyleValue.get(), "default", true) || StringsKt.equals((String)this.tagsStyleValue.get(), "-", true) || StringsKt.equals((String)this.tagsStyleValue.get(), "|", true))) {
            returnTag = returnTag + String.valueOf(((String)this.tagsStyleValue.get()).charAt(1));
        }
        return returnTag;
    }

    @NotNull
    public final String getModName(@NotNull Module mod) {
        Intrinsics.checkParameterIsNotNull(mod, "mod");
        String displayName = ((Boolean)this.nameBreak.get() != false ? mod.getName() : mod.getName()) + this.getModTag(mod);
        String string = (String)this.caseValue.get();
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        switch (string3) {
            case "lower": {
                String string4 = displayName;
                boolean bl2 = false;
                String string5 = string4;
                if (string5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string6 = string5.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(string6, "(this as java.lang.String).toLowerCase()");
                displayName = string6;
                break;
            }
            case "upper": {
                String string7 = displayName;
                boolean bl3 = false;
                String string8 = string7;
                if (string8 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string9 = string8.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(string9, "(this as java.lang.String).toUpperCase()");
                displayName = string9;
                break;
            }
        }
        return displayName;
    }

    public Arraylist(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.colorModeValue = new ListValue("Color", new String[]{"Custom", "Random", "Sky", "CRainbow", "LiquidSlowly", "Fade", "Gradinet"}, "Gradinet");
        this.blurValue = new BoolValue("Blur", false);
        this.blurStrength = new FloatValue("Blur-Strength", 0.0f, 0.0f, 30.0f);
        this.shadow = new BoolValue("ShadowText", true);
        this.shadowShaderValue = new BoolValue("Shadow", false);
        this.shadowNoCutValue = new BoolValue("Shadow-NoCut", false);
        this.shadowStrength = new IntegerValue("Shadow-Strength", 1, 1, 30);
        this.shadowColorMode = new ListValue("Shadow-Color", new String[]{"Background", "Text", "Custom"}, "Background");
        this.shadowColorRedValue = new IntegerValue("Shadow-Red", 0, 0, 255);
        this.shadowColorGreenValue = new IntegerValue("Shadow-Green", 111, 0, 255);
        this.shadowColorBlueValue = new IntegerValue("Shadow-Blue", 255, 0, 255);
        this.colorRedValue = new IntegerValue("Red", 0, 0, 255);
        this.colorGreenValue = new IntegerValue("Green", 111, 0, 255);
        this.colorBlueValue = new IntegerValue("Blue", 255, 0, 255);
        this.colorRedValue2 = new IntegerValue("R2", 0, 0, 255);
        this.colorGreenValue2 = new IntegerValue("G2", 111, 0, 255);
        this.colorBlueValue2 = new IntegerValue("B2", 255, 0, 255);
        this.gidentspeed = new IntegerValue("GidentSpeed", 100, 1, 1000);
        this.colorAlphaValue = new IntegerValue("Alpha", 255, 0, 255);
        this.fadeOffset = new FloatValue("Gradinet-Offset", 0.2f, 0.1f, 1.0f);
        this.fadeSpeed = new FloatValue("Gradinet-Speed", 2.0f, 1.0f, 10.0f);
        this.textRed = new IntegerValue("Gradinet-Red", 0, 0, 255);
        this.textGreen = new IntegerValue("Gradinet-Green", 0, 0, 255);
        this.textBlue = new IntegerValue("Gradinet-Blue", 255, 0, 255);
        this.textRed2 = new IntegerValue("Gradinet-Red-2", 25, 0, 255);
        this.textGreen2 = new IntegerValue("Gradinet-Green-2", 45, 0, 255);
        this.textBlue2 = new IntegerValue("Gradinet-Blue-2", 150, 0, 255);
        this.saturationValue = new FloatValue("Saturation", 0.9f, 0.0f, 1.0f);
        this.brightnessValue = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
        this.skyDistanceValue = new IntegerValue("Sky-Distance", 2, 0, 4);
        this.cRainbowSecValue = new IntegerValue("CRainbow-Seconds", 2, 1, 10);
        this.cRainbowDistValue = new IntegerValue("CRainbow-Distance", 2, 1, 6);
        this.liquidSlowlyDistanceValue = new IntegerValue("LiquidSlowly-Distance", 90, 1, 90);
        this.fadeDistanceValue = new IntegerValue("Fade-Distance", 50, 1, 100);
        this.hAnimation = new ListValue("HorizontalAnimation", new String[]{"Default", "None", "Slide", "Astolfo"}, "None");
        this.vAnimation = new ListValue("VerticalAnimation", new String[]{"None", "LiquidSense", "Slide", "Rise", "Astolfo"}, "None");
        this.animationSpeed = new FloatValue("Animation-Speed", 0.25f, 0.01f, 1.0f);
        this.nameBreak = new BoolValue("NameBreak", true);
        this.abcOrder = new BoolValue("Alphabetical-Order", false);
        this.tags = new BoolValue("Tags", true);
        this.tagsStyleValue = new ListValue("TagsStyle", new String[]{"-", "|", "()", "[]", "<>", "Default"}, "-");
        this.backgroundColorRedValue = new IntegerValue("Background-R", 0, 0, 255);
        this.backgroundColorGreenValue = new IntegerValue("Background-G", 0, 0, 255);
        this.backgroundColorBlueValue = new IntegerValue("Background-B", 0, 0, 255);
        this.backgroundColorAlphaValue = new IntegerValue("Background-Alpha", 0, 0, 255);
        this.rectRightValue = new ListValue("Rect-Right", new String[]{"None", "Left", "Right", "Outline", "Special", "Top"}, "None");
        this.rectLeftValue = new ListValue("Rect-Left", new String[]{"None", "Left", "Right"}, "None");
        this.caseValue = new ListValue("Case", new String[]{"None", "Lower", "Upper"}, "None");
        this.spaceValue = new FloatValue("Space", 0.0f, 0.0f, 5.0f);
        this.textHeightValue = new FloatValue("TextHeight", 11.0f, 1.0f, 20.0f);
        this.textYValue = new FloatValue("TextY", 1.0f, 0.0f, 20.0f);
        this.tagsArrayColor = new BoolValue("TagsArrayColor", false);
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.modules = CollectionsKt.emptyList();
        this.sortedModules = CollectionsKt.emptyList();
        this.fontRenderer = Fonts.bold40;
    }

    public /* synthetic */ Arraylist(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 1.0;
        }
        if ((n & 2) != 0) {
            d2 = 2.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        if ((n & 8) != 0) {
            side = new Side(Side.Horizontal.RIGHT, Side.Vertical.UP);
        }
        this(d, d2, f, side);
    }

    public Arraylist() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }

    public static final /* synthetic */ List access$getModules$p(Arraylist $this) {
        return $this.modules;
    }

    public static final /* synthetic */ void access$setModules$p(Arraylist $this, List list) {
        $this.modules = list;
    }

    public static final /* synthetic */ ListValue access$getRectRightValue$p(Arraylist $this) {
        return $this.rectRightValue;
    }

    public static final /* synthetic */ ListValue access$getShadowColorMode$p(Arraylist $this) {
        return $this.shadowColorMode;
    }

    public static final /* synthetic */ IntegerValue access$getBackgroundColorRedValue$p(Arraylist $this) {
        return $this.backgroundColorRedValue;
    }

    public static final /* synthetic */ IntegerValue access$getBackgroundColorGreenValue$p(Arraylist $this) {
        return $this.backgroundColorGreenValue;
    }

    public static final /* synthetic */ IntegerValue access$getBackgroundColorBlueValue$p(Arraylist $this) {
        return $this.backgroundColorBlueValue;
    }

    public static final /* synthetic */ IntegerValue access$getSkyDistanceValue$p(Arraylist $this) {
        return $this.skyDistanceValue;
    }

    public static final /* synthetic */ FloatValue access$getSaturationValue$p(Arraylist $this) {
        return $this.saturationValue;
    }

    public static final /* synthetic */ FloatValue access$getBrightnessValue$p(Arraylist $this) {
        return $this.brightnessValue;
    }

    public static final /* synthetic */ IntegerValue access$getCRainbowSecValue$p(Arraylist $this) {
        return $this.cRainbowSecValue;
    }

    public static final /* synthetic */ IntegerValue access$getCRainbowDistValue$p(Arraylist $this) {
        return $this.cRainbowDistValue;
    }

    public static final /* synthetic */ IntegerValue access$getFadeDistanceValue$p(Arraylist $this) {
        return $this.fadeDistanceValue;
    }

    public static final /* synthetic */ IntegerValue access$getLiquidSlowlyDistanceValue$p(Arraylist $this) {
        return $this.liquidSlowlyDistanceValue;
    }

    public static final /* synthetic */ IntegerValue access$getTextRed$p(Arraylist $this) {
        return $this.textRed;
    }

    public static final /* synthetic */ IntegerValue access$getTextGreen$p(Arraylist $this) {
        return $this.textGreen;
    }

    public static final /* synthetic */ IntegerValue access$getTextBlue$p(Arraylist $this) {
        return $this.textBlue;
    }

    public static final /* synthetic */ IntegerValue access$getTextRed2$p(Arraylist $this) {
        return $this.textRed2;
    }

    public static final /* synthetic */ IntegerValue access$getTextGreen2$p(Arraylist $this) {
        return $this.textGreen2;
    }

    public static final /* synthetic */ IntegerValue access$getTextBlue2$p(Arraylist $this) {
        return $this.textBlue2;
    }

    public static final /* synthetic */ FloatValue access$getFadeOffset$p(Arraylist $this) {
        return $this.fadeOffset;
    }

    public static final /* synthetic */ IntegerValue access$getShadowColorRedValue$p(Arraylist $this) {
        return $this.shadowColorRedValue;
    }

    public static final /* synthetic */ IntegerValue access$getShadowColorGreenValue$p(Arraylist $this) {
        return $this.shadowColorGreenValue;
    }

    public static final /* synthetic */ IntegerValue access$getShadowColorBlueValue$p(Arraylist $this) {
        return $this.shadowColorBlueValue;
    }

    public static final /* synthetic */ BoolValue access$getShadowNoCutValue$p(Arraylist $this) {
        return $this.shadowNoCutValue;
    }

    public static final /* synthetic */ ListValue access$getRectLeftValue$p(Arraylist $this) {
        return $this.rectLeftValue;
    }

    public static final /* synthetic */ IntegerValue access$getColorRedValue2$p(Arraylist $this) {
        return $this.colorRedValue2;
    }

    public static final /* synthetic */ IntegerValue access$getColorGreenValue2$p(Arraylist $this) {
        return $this.colorGreenValue2;
    }

    public static final /* synthetic */ IntegerValue access$getColorBlueValue2$p(Arraylist $this) {
        return $this.colorBlueValue2;
    }

    public static final /* synthetic */ IntegerValue access$getGidentspeed$p(Arraylist $this) {
        return $this.gidentspeed;
    }

    public static final /* synthetic */ FontValue access$getFontValue$p(Arraylist $this) {
        return $this.fontValue;
    }
}

