/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import me.utils.render.VisualUtils;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.features.module.modules.color.Gident;
import net.ccbluex.liquidbounce.features.module.modules.misc.AntiBot;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.PlayerList;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;

@ElementInfo(name="PlayerList")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/PlayerList;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "()V", "alphaValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "bgalphaValue", "bgblueValue", "bggreenValue", "bgredValue", "blueValue", "brightnessValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "cRainbowSecValue", "decimalFormat3", "Ljava/text/DecimalFormat;", "distanceValue", "fontOffsetValue", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "gradientAmountValue", "greenValue", "lineValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "rainbowList", "Lnet/ccbluex/liquidbounce/value/ListValue;", "redValue", "reverseValue", "saturationValue", "shadowValue", "sortValue", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class PlayerList
extends Element {
    private final DecimalFormat decimalFormat3 = new DecimalFormat("0.#", new DecimalFormatSymbols(Locale.ENGLISH));
    private final ListValue sortValue = new ListValue("Sort", new String[]{"Alphabet", "Distance", "Health"}, "Alphabet");
    private final FloatValue fontOffsetValue = new FloatValue("Font-Offset", 0.0f, 3.0f, -3.0f);
    private final BoolValue reverseValue = new BoolValue("Reverse", false);
    private final FontValue fontValue;
    private final BoolValue shadowValue;
    private final BoolValue lineValue;
    private final IntegerValue redValue;
    private final IntegerValue greenValue;
    private final IntegerValue blueValue;
    private final IntegerValue alphaValue;
    private final IntegerValue bgredValue;
    private final IntegerValue bggreenValue;
    private final IntegerValue bgblueValue;
    private final IntegerValue bgalphaValue;
    private final ListValue rainbowList;
    private final FloatValue saturationValue;
    private final FloatValue brightnessValue;
    private final IntegerValue cRainbowSecValue;
    private final IntegerValue distanceValue;
    private final IntegerValue gradientAmountValue;

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Border drawElement() {
        Iterator $this$filterTo$iv$iv2;
        boolean reverse = (Boolean)this.reverseValue.get();
        IFontRenderer font = (IFontRenderer)this.fontValue.get();
        float fontOffset = ((Number)this.fontOffsetValue.get()).floatValue();
        String rainbowType = (String)this.rainbowList.get();
        float nameLength = font.getStringWidth("Name (0)");
        float hpLength = font.getStringWidth("Health");
        float distLength = font.getStringWidth("Distance");
        float height = 4.0f + (float)Fonts.font35.getFontHeight();
        int color = new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue(), ((Number)this.alphaValue.get()).intValue()).getRGB();
        Color bgColor = new Color(((Number)this.bgredValue.get()).intValue(), ((Number)this.bggreenValue.get()).intValue(), ((Number)this.bgblueValue.get()).intValue(), ((Number)this.bgalphaValue.get()).intValue());
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        Object $this$filter$iv = iWorldClient.getPlayerEntities();
        boolean $i$f$filter = false;
        Iterable iterable = $this$filter$iv;
        Collection destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        Iterator iterator2 = $this$filterTo$iv$iv2.iterator();
        while (iterator2.hasNext()) {
            Object element$iv$iv = iterator2.next();
            IEntityPlayer it = (IEntityPlayer)element$iv$iv;
            boolean bl2 = false;
            if (!(!AntiBot.isBot(it) && Intrinsics.areEqual(it, MinecraftInstance.mc.getThePlayer()) ^ true)) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        List playerList = CollectionsKt.toMutableList((List)destination$iv$iv);
        nameLength = font.getStringWidth("Name (" + playerList.size() + ')');
        switch ((String)this.sortValue.get()) {
            case "Alphabet": {
                List list = playerList;
                $i$f$filter = false;
                Comparator comparator = new Comparator<T>(){

                    public final int compare(T a, T b) {
                        boolean bl = false;
                        IEntityPlayer it = (IEntityPlayer)a;
                        boolean bl2 = false;
                        String string = it.getName();
                        if (string == null) {
                            Intrinsics.throwNpe();
                        }
                        String string2 = string;
                        boolean bl3 = false;
                        String string3 = string2;
                        if (string3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string4 = string3.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(string4, "(this as java.lang.String).toLowerCase()");
                        it = (IEntityPlayer)b;
                        Comparable comparable = (Comparable)((Object)string4);
                        bl2 = false;
                        String string5 = it.getName();
                        if (string5 == null) {
                            Intrinsics.throwNpe();
                        }
                        string2 = string5;
                        bl3 = false;
                        String string6 = string2;
                        if (string6 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string7 = string6.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(string7, "(this as java.lang.String).toLowerCase()");
                        String string8 = string7;
                        return ComparisonsKt.compareValues(comparable, (Comparable)((Object)string8));
                    }
                };
                CollectionsKt.sortWith(list, comparator);
                break;
            }
            case "Distance": {
                CollectionsKt.sortWith(playerList, drawElement.2.INSTANCE);
                break;
            }
            default: {
                CollectionsKt.sortWith(playerList, drawElement.3.INSTANCE);
            }
        }
        if (reverse) {
            playerList = CollectionsKt.toMutableList(CollectionsKt.reversed(playerList));
        }
        Iterable $this$forEach$iv = playerList;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            IEntityPlayer it = (IEntityPlayer)element$iv;
            boolean bl = false;
            String string = it.getName();
            if (string == null) {
                Intrinsics.throwNpe();
            }
            if ((float)font.getStringWidth(string) > nameLength) {
                String string2 = it.getName();
                if (string2 == null) {
                    Intrinsics.throwNpe();
                }
                nameLength = font.getStringWidth(string2);
            }
            StringBuilder stringBuilder = new StringBuilder();
            if ((float)font.getStringWidth(stringBuilder.append(this.decimalFormat3.format(Float.valueOf(it.getHealth()))).append(" HP").toString()) > hpLength) {
                hpLength = font.getStringWidth(this.decimalFormat3.format(Float.valueOf(it.getHealth())) + " HP");
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (!((float)font.getStringWidth(stringBuilder2.append(this.decimalFormat3.format(PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, it))).append('m').toString()) > distLength)) continue;
            StringBuilder stringBuilder3 = new StringBuilder();
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            distLength = font.getStringWidth(stringBuilder3.append(this.decimalFormat3.format(PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP2, it))).append('m').toString());
        }
        if (((Boolean)this.lineValue.get()).booleanValue()) {
            double barLength = nameLength + hpLength + distLength + 50.0f;
            int $this$filterTo$iv$iv2 = 0;
            int n = ((Number)this.gradientAmountValue.get()).intValue() - 1;
            if ($this$filterTo$iv$iv2 <= n) {
                while (true) {
                    int n2;
                    int n3;
                    void i;
                    double barStart = (double)i / (double)((Number)this.gradientAmountValue.get()).intValue() * barLength;
                    double barEnd = (double)(i + true) / (double)((Number)this.gradientAmountValue.get()).intValue() * barLength;
                    switch (rainbowType) {
                        case "CRainbow": {
                            n3 = RenderUtils.getRainbowOpaque(((Number)this.cRainbowSecValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), (int)(i * ((Number)this.distanceValue.get()).intValue()));
                            break;
                        }
                        case "Sky": {
                            n3 = RenderUtils.SkyRainbow((int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            break;
                        }
                        case "LiquidSlowly": {
                            Color color2 = ColorUtils.LiquidSlowly(System.nanoTime(), (int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            if (color2 == null) {
                                Intrinsics.throwNpe();
                            }
                            n3 = color2.getRGB();
                            break;
                        }
                        case "Gident": {
                            Color color3 = VisualUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color3, "VisualUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n3 = color3.getRGB();
                            break;
                        }
                        case "Fade": {
                            n3 = ColorUtils.fade(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), (int)(i * ((Number)this.distanceValue.get()).intValue()), 100).getRGB();
                            break;
                        }
                        default: {
                            n3 = color;
                        }
                    }
                    switch (rainbowType) {
                        case "CRainbow": {
                            n2 = RenderUtils.getRainbowOpaque(((Number)this.cRainbowSecValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), (int)((i + true) * ((Number)this.distanceValue.get()).intValue()));
                            break;
                        }
                        case "Sky": {
                            n2 = RenderUtils.SkyRainbow((int)((i + true) * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            break;
                        }
                        case "LiquidSlowly": {
                            Color color4 = ColorUtils.LiquidSlowly(System.nanoTime(), (int)((i + true) * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            if (color4 == null) {
                                Intrinsics.throwNpe();
                            }
                            n2 = color4.getRGB();
                            break;
                        }
                        case "Gident": {
                            Color color5 = VisualUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color5, "VisualUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n2 = color5.getRGB();
                            break;
                        }
                        case "Fade": {
                            n2 = ColorUtils.fade(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), (int)((i + true) * ((Number)this.distanceValue.get()).intValue()), 100).getRGB();
                            break;
                        }
                        default: {
                            n2 = color;
                        }
                    }
                    RenderUtils.drawGradientSideways(barStart, -1.0, barEnd, 0.0, n3, n2);
                    if (i == n) break;
                    ++i;
                }
            }
        }
        RenderUtils.drawRect(0.0f, 0.0f, nameLength + hpLength + distLength + 50.0f, 4.0f + (float)Fonts.font35.getFontHeight(), bgColor.getRGB());
        font.drawString("Name (" + playerList.size() + ')', 5.0f, 3.0f, -1, (Boolean)this.shadowValue.get());
        font.drawString("Distance", 5.0f + nameLength + 10.0f, 3.0f, -1, (Boolean)this.shadowValue.get());
        font.drawString("Health", 5.0f + nameLength + distLength + 20.0f, 3.0f, -1, (Boolean)this.shadowValue.get());
        Iterable $this$forEachIndexed$iv = playerList;
        boolean $i$f$forEachIndexed = false;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            String string;
            void player;
            int n = index$iv++;
            boolean bl = false;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int n4 = n;
            IEntityPlayer bl2 = (IEntityPlayer)item$iv;
            int index = n4;
            boolean bl3 = false;
            RenderUtils.drawRect(0.0f, height, nameLength + hpLength + distLength + 50.0f, height + 2.0f + (float)Fonts.font35.getFontHeight(), bgColor.getRGB());
            String string3 = player.getName();
            if (string3 == null) {
                Intrinsics.throwNpe();
            }
            font.drawString(string3, 5.0f, height + 1.0f + fontOffset, -1, (Boolean)this.shadowValue.get());
            IFontRenderer iFontRenderer = font;
            StringBuilder stringBuilder = new StringBuilder();
            if (MinecraftInstance.mc.getThePlayer() != null) {
                IEntityPlayerSP iEntityPlayerSP;
                StringBuilder stringBuilder4 = stringBuilder;
                IFontRenderer iFontRenderer2 = iFontRenderer;
                boolean bl4 = false;
                boolean bl5 = false;
                IEntityPlayerSP it = iEntityPlayerSP;
                boolean bl6 = false;
                String string4 = this.decimalFormat3.format(PlayerExtensionKt.getDistanceToEntityBox(it, (IEntity)player));
                iFontRenderer = iFontRenderer2;
                stringBuilder = stringBuilder4;
                string = string4;
            } else {
                string = null;
            }
            iFontRenderer.drawString(stringBuilder.append(string).append('m').toString(), 5.0f + nameLength + 10.0f, height + 1.0f + fontOffset, -1, (Boolean)this.shadowValue.get());
            font.drawString(this.decimalFormat3.format(Float.valueOf(player.getHealth())) + " HP", 5.0f + nameLength + distLength + 20.0f, height + 1.0f + fontOffset, -1, (Boolean)this.shadowValue.get());
            height += 2.0f + (float)Fonts.font35.getFontHeight();
        }
        return new Border(0.0f, 0.0f, nameLength + hpLength + distLength + 50.0f, 4.0f + height + (float)Fonts.font35.getFontHeight());
    }

    public PlayerList() {
        super(0.0, 0.0, 0.0f, null, 15, null);
        IFontRenderer iFontRenderer = Fonts.font35;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font35");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.shadowValue = new BoolValue("Shadow", false);
        this.lineValue = new BoolValue("Line", true);
        this.redValue = new IntegerValue("Red", 255, 0, 255);
        this.greenValue = new IntegerValue("Green", 255, 0, 255);
        this.blueValue = new IntegerValue("Blue", 255, 0, 255);
        this.alphaValue = new IntegerValue("Alpha", 255, 0, 255);
        this.bgredValue = new IntegerValue("Background-Red", 0, 0, 255);
        this.bggreenValue = new IntegerValue("Background-Green", 0, 0, 255);
        this.bgblueValue = new IntegerValue("Background-Blue", 0, 0, 255);
        this.bgalphaValue = new IntegerValue("Background-Alpha", 120, 0, 255);
        this.rainbowList = new ListValue("Rainbow", new String[]{"Off", "CRainbow", "Sky", "LiquidSlowly", "Fade", "Gident"}, "Off");
        this.saturationValue = new FloatValue("Saturation", 0.9f, 0.0f, 1.0f);
        this.brightnessValue = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
        this.cRainbowSecValue = new IntegerValue("Seconds", 2, 1, 10);
        this.distanceValue = new IntegerValue("Line-Distance", 0, 0, 400);
        this.gradientAmountValue = new IntegerValue("Gradient-Amount", 25, 1, 50);
    }
}

