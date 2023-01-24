/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.KeyStroke;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.minecraft.client.settings.KeyBinding;
import org.jetbrains.annotations.Nullable;

@ElementInfo(name="KeyStrokes")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/KeyStrokes;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "()V", "animSpeedValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "backGroundAlphaValue", "backGroundBlueValue", "backGroundGreenValue", "backGroundRedValue", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "highLightPercent", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "keys", "Ljava/util/ArrayList;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/KeyStroke;", "Lkotlin/collections/ArrayList;", "outline", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "outlineBoldValue", "outlineRainbow", "textAlphaValue", "textBlueValue", "textGreenValue", "textRedValue", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class KeyStrokes
extends Element {
    private final ArrayList<KeyStroke> keys = new ArrayList();
    private final IntegerValue backGroundRedValue = new IntegerValue("BackGroundRed", 0, 0, 255);
    private final IntegerValue backGroundGreenValue = new IntegerValue("BackGroundGreen", 0, 0, 255);
    private final IntegerValue backGroundBlueValue = new IntegerValue("BackGroundBlue", 0, 0, 255);
    private final IntegerValue backGroundAlphaValue = new IntegerValue("BackGroundAlpha", 170, 0, 255);
    private final IntegerValue textRedValue = new IntegerValue("TextRed", 255, 0, 255);
    private final IntegerValue textGreenValue = new IntegerValue("TextGreen", 255, 0, 255);
    private final IntegerValue textBlueValue = new IntegerValue("TextBlue", 255, 0, 255);
    private final IntegerValue textAlphaValue = new IntegerValue("TextAlpha", 255, 0, 255);
    private final FloatValue highLightPercent = new FloatValue("HighLightPercent", 0.5f, 0.0f, 1.0f);
    private final IntegerValue animSpeedValue = new IntegerValue("AnimationSpeed", 300, 0, 700);
    private final BoolValue outline = new BoolValue("Outline", false);
    private final IntegerValue outlineBoldValue = new IntegerValue("OutlineBold", 1, 0, 5);
    private final BoolValue outlineRainbow = new BoolValue("OutLineRainbow", false);
    private final FontValue fontValue;

    @Override
    @Nullable
    public Border drawElement() {
        Color backGroundColor = new Color(((Number)this.backGroundRedValue.get()).intValue(), ((Number)this.backGroundGreenValue.get()).intValue(), ((Number)this.backGroundBlueValue.get()).intValue(), ((Number)this.backGroundAlphaValue.get()).intValue());
        Color textColor = (Boolean)this.outlineRainbow.get() != false ? ColorUtils.INSTANCE.rainbowWithAlpha(((Number)this.textAlphaValue.get()).intValue()) : new Color(((Number)this.textRedValue.get()).intValue(), ((Number)this.textGreenValue.get()).intValue(), ((Number)this.textBlueValue.get()).intValue(), ((Number)this.textAlphaValue.get()).intValue());
        for (KeyStroke keyStroke : this.keys) {
            keyStroke.render(((Number)this.animSpeedValue.get()).intValue(), backGroundColor, textColor, ((Number)this.highLightPercent.get()).floatValue(), (Boolean)this.outline.get(), ((Number)this.outlineBoldValue.get()).intValue(), (IFontRenderer)this.fontValue.get(), (float)this.getRenderX(), (float)this.getRenderY(), this.getScale());
        }
        return new Border(0.0f, 0.0f, 47.0f, 47.0f);
    }

    public KeyStrokes() {
        super(5.0, 25.0, 1.25f, Side.Companion.default());
        IFontRenderer iFontRenderer = Fonts.font35;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font35");
        this.fontValue = new FontValue("Font", iFontRenderer);
        KeyBinding keyBinding = MinecraftInstance.mc2.field_71474_y.field_74351_w;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "mc2.gameSettings.keyBindForward");
        this.keys.add(new KeyStroke(keyBinding, 16, 0, 15, 15).initKeyName());
        KeyBinding keyBinding2 = MinecraftInstance.mc2.field_71474_y.field_74370_x;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding2, "mc2.gameSettings.keyBindLeft");
        this.keys.add(new KeyStroke(keyBinding2, 0, 16, 15, 15).initKeyName());
        KeyBinding keyBinding3 = MinecraftInstance.mc2.field_71474_y.field_74368_y;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding3, "mc2.gameSettings.keyBindBack");
        this.keys.add(new KeyStroke(keyBinding3, 16, 16, 15, 15).initKeyName());
        KeyBinding keyBinding4 = MinecraftInstance.mc2.field_71474_y.field_74366_z;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding4, "mc2.gameSettings.keyBindRight");
        this.keys.add(new KeyStroke(keyBinding4, 32, 16, 15, 15).initKeyName());
        KeyBinding keyBinding5 = MinecraftInstance.mc2.field_71474_y.field_74312_F;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding5, "mc2.gameSettings.keyBindAttack");
        this.keys.add(new KeyStroke(keyBinding5, 0, 32, 23, 15).initKeyName("L"));
        KeyBinding keyBinding6 = MinecraftInstance.mc2.field_71474_y.field_74313_G;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding6, "mc2.gameSettings.keyBindUseItem");
        this.keys.add(new KeyStroke(keyBinding6, 24, 32, 23, 15).initKeyName("R"));
    }
}

