/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.CPSCounter;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.ServerUtils;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.ccbluex.liquidbounce.value.TextValue;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ElementInfo(name="Text2")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 D2\u00020\u0001:\u0001DB-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\n\u00102\u001a\u0004\u0018\u000103H\u0016J\u0016\u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u001dJ\u0012\u00107\u001a\u0004\u0018\u00010\u00122\u0006\u00108\u001a\u00020\u0012H\u0002J\u0018\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u001dH\u0016J \u0010>\u001a\u00020:2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u001dH\u0016J\u0010\u0010@\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u0012H\u0002J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010;\u001a\u00020BJ\b\u0010C\u001a\u00020:H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0010R\u000e\u0010)\u001a\u00020*X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010-\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0010R\u000e\u0010/\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Text2;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "alphaValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "blueValue", "colorModeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "getColorModeValue", "()Lnet/ccbluex/liquidbounce/value/ListValue;", "display", "", "getDisplay", "()Ljava/lang/String;", "displayString", "Lnet/ccbluex/liquidbounce/value/TextValue;", "getDisplayString", "()Lnet/ccbluex/liquidbounce/value/TextValue;", "displayText", "editMode", "", "editTicks", "", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "greenValue", "prevClick", "", "rainbowIndex", "rainbowSpeed", "rectAlphaValue", "rectBlueValue", "rectColorModeValue", "getRectColorModeValue", "rectExpandValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "rectGreenValue", "rectRedValue", "rectValue", "getRectValue", "redValue", "shadow", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "getClientName", "i", "i2", "getReplacement", "str", "handleKey", "", "c", "", "keyCode", "handleMouseClick", "mouseButton", "multiReplace", "setColor", "Ljava/awt/Color;", "updateElement", "Companion", "Relaxed"})
public final class Text2
extends Element {
    @NotNull
    private final TextValue displayString;
    private final IntegerValue redValue;
    private final IntegerValue greenValue;
    private final IntegerValue blueValue;
    private final IntegerValue alphaValue;
    @NotNull
    private final ListValue colorModeValue;
    private final BoolValue shadow;
    private final IntegerValue rectRedValue;
    private final IntegerValue rectGreenValue;
    private final IntegerValue rectBlueValue;
    private final IntegerValue rectAlphaValue;
    @NotNull
    private final ListValue rectColorModeValue;
    @NotNull
    private final ListValue rectValue;
    private final FloatValue rectExpandValue;
    private final IntegerValue rainbowSpeed;
    private final IntegerValue rainbowIndex;
    private final FontValue fontValue;
    private boolean editMode;
    private int editTicks;
    private long prevClick;
    private String displayText;
    @NotNull
    private static final SimpleDateFormat DATE_FORMAT;
    @NotNull
    private static final SimpleDateFormat HOUR_FORMAT;
    @NotNull
    private static final DecimalFormat DECIMAL_FORMAT;
    public static final Companion Companion;

    @NotNull
    public final TextValue getDisplayString() {
        return this.displayString;
    }

    @NotNull
    public final ListValue getColorModeValue() {
        return this.colorModeValue;
    }

    @NotNull
    public final ListValue getRectColorModeValue() {
        return this.rectColorModeValue;
    }

    @NotNull
    public final ListValue getRectValue() {
        return this.rectValue;
    }

    private final String getDisplay() {
        CharSequence charSequence = (CharSequence)this.displayString.get();
        boolean bl = false;
        String textContent = charSequence.length() == 0 && !this.editMode ? "Text Element" : (String)this.displayString.get();
        return this.multiReplace(textContent);
    }

    private final String getReplacement(String str) {
        String string;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP != null) {
            switch (str) {
                case "x": {
                    IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP2 == null) {
                        Intrinsics.throwNpe();
                    }
                    return DECIMAL_FORMAT.format(iEntityPlayerSP2.getPosX());
                }
                case "y": {
                    IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP3 == null) {
                        Intrinsics.throwNpe();
                    }
                    return DECIMAL_FORMAT.format(iEntityPlayerSP3.getPosY());
                }
                case "z": {
                    IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP4 == null) {
                        Intrinsics.throwNpe();
                    }
                    return DECIMAL_FORMAT.format(iEntityPlayerSP4.getPosZ());
                }
                case "xdp": {
                    IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP5 == null) {
                        Intrinsics.throwNpe();
                    }
                    return String.valueOf(iEntityPlayerSP5.getPosX());
                }
                case "ydp": {
                    IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP6 == null) {
                        Intrinsics.throwNpe();
                    }
                    return String.valueOf(iEntityPlayerSP6.getPosY());
                }
                case "zdp": {
                    IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP7 == null) {
                        Intrinsics.throwNpe();
                    }
                    return String.valueOf(iEntityPlayerSP7.getPosZ());
                }
                case "velocity": {
                    IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP8 == null) {
                        Intrinsics.throwNpe();
                    }
                    double d = iEntityPlayerSP8.getMotionX();
                    IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP9 == null) {
                        Intrinsics.throwNpe();
                    }
                    double d2 = d * iEntityPlayerSP9.getMotionX();
                    IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP10 == null) {
                        Intrinsics.throwNpe();
                    }
                    double d3 = iEntityPlayerSP10.getMotionZ();
                    IEntityPlayerSP iEntityPlayerSP11 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP11 == null) {
                        Intrinsics.throwNpe();
                    }
                    double d4 = d2 + d3 * iEntityPlayerSP11.getMotionZ();
                    DecimalFormat decimalFormat = DECIMAL_FORMAT;
                    boolean bl = false;
                    double d5 = Math.sqrt(d4);
                    return decimalFormat.format(d5);
                }
                case "ping": {
                    IEntityPlayerSP iEntityPlayerSP12 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP12 == null) {
                        Intrinsics.throwNpe();
                    }
                    return String.valueOf(PlayerExtensionKt.getPing(iEntityPlayerSP12));
                }
            }
        }
        switch (str) {
            case "clientName": {
                string = "Relaxed";
                break;
            }
            case "clientVersion": {
                string = String.valueOf(1);
                break;
            }
            case "clientCreator": {
                string = "CCBlueX, P1ayerLk_";
                break;
            }
            case "fps": {
                string = String.valueOf(Minecraft.func_175610_ah());
                break;
            }
            case "date": {
                string = DATE_FORMAT.format(System.currentTimeMillis());
                break;
            }
            case "time": {
                string = HOUR_FORMAT.format(System.currentTimeMillis());
                break;
            }
            case "serverIp": {
                string = ServerUtils.getRemoteIp();
                break;
            }
            case "cps": 
            case "lcps": {
                return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.LEFT));
            }
            case "mcps": {
                return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.MIDDLE));
            }
            case "rcps": {
                return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.RIGHT));
            }
            default: {
                string = null;
            }
        }
        return string;
    }

    /*
     * Unable to fully structure code
     */
    private final String multiReplace(String str) {
        lastPercent = -1;
        result = new StringBuilder();
        var4_4 = 0;
        var5_5 = ((CharSequence)str).length();
        while (var4_4 < var5_5) {
            block7: {
                block6: {
                    if (str.charAt((int)i) != '%') break block6;
                    if (lastPercent == -1) ** GOTO lbl25
                    if (lastPercent + 1 == i) ** GOTO lbl-1000
                    var7_7 = str;
                    var8_8 = lastPercent + 1;
                    var10_10 = this;
                    var9_9 = false;
                    v0 = var7_7;
                    if (v0 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(v0.substring(var8_8, (int)i), "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    replacement = var10_10.getReplacement(var11_11);
                    if (replacement != null) {
                        result.append(replacement);
                        lastPercent = -1;
                    } else lbl-1000:
                    // 2 sources

                    {
                        result.append(str, lastPercent, (int)i);
lbl25:
                        // 2 sources

                        lastPercent = i;
                    }
                    break block7;
                }
                if (lastPercent == -1) {
                    result.append(str.charAt((int)i));
                }
            }
            ++i;
        }
        if (lastPercent != -1) {
            result.append(str, lastPercent, str.length());
        }
        v1 = result.toString();
        Intrinsics.checkExpressionValueIsNotNull(v1, "result.toString()");
        return v1;
    }

    @NotNull
    public final String getClientName(int i, int i2) {
        String string = "SadNess";
        boolean bl = false;
        String string2 = string.substring(i, i2);
        Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        return string2;
    }

    /*
     * Exception decompiling
     */
    @Override
    @Nullable
    public Border drawElement() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Can't sort instructions [@NONE, blocks:[17] lbl122 : CaseStatement: default:\u000a, @NONE, blocks:[17] lbl122 : CaseStatement: default:\u000a]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.CompareByIndex.compare(CompareByIndex.java:25)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.CompareByIndex.compare(CompareByIndex.java:8)
         *     at java.util.TimSort.countRunAndMakeAscending(Unknown Source)
         *     at java.util.TimSort.sort(Unknown Source)
         *     at java.util.Arrays.sort(Unknown Source)
         *     at java.util.ArrayList.sort(Unknown Source)
         *     at java.util.Collections.sort(Unknown Source)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.buildSwitchCases(SwitchReplacer.java:271)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitch(SwitchReplacer.java:258)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:66)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:517)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public void updateElement() {
        this.editTicks += 5;
        if (this.editTicks > 80) {
            this.editTicks = 0;
        }
        this.displayText = this.editMode ? (String)this.displayString.get() : this.getDisplay();
    }

    @Override
    public void handleMouseClick(double x, double y, int mouseButton) {
        if (this.isInBorder(x, y) && mouseButton == 0) {
            if (System.currentTimeMillis() - this.prevClick <= 250L) {
                this.editMode = true;
            }
            this.prevClick = System.currentTimeMillis();
        } else {
            this.editMode = false;
        }
    }

    @Override
    public void handleKey(char c, int keyCode) {
        if (this.editMode && MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen())) {
            if (keyCode == 14) {
                CharSequence charSequence = (CharSequence)this.displayString.get();
                int n = 0;
                if (charSequence.length() > 0) {
                    charSequence = (String)this.displayString.get();
                    n = 0;
                    int n2 = ((String)this.displayString.get()).length() - 1;
                    TextValue textValue = this.displayString;
                    boolean bl = false;
                    CharSequence charSequence2 = charSequence;
                    if (charSequence2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string = ((String)charSequence2).substring(n, n2);
                    Intrinsics.checkExpressionValueIsNotNull(string, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    String string2 = string;
                    textValue.set(string2);
                }
                this.updateElement();
                return;
            }
            if (ColorUtils.INSTANCE.isAllowedCharacter(c) || c == '\u00a7') {
                this.displayString.set((String)this.displayString.get() + c);
            }
            this.updateElement();
        }
    }

    @NotNull
    public final Text2 setColor(@NotNull Color c) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        this.redValue.set(c.getRed());
        this.greenValue.set(c.getGreen());
        this.blueValue.set(c.getBlue());
        return this;
    }

    public Text2(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.displayString = new TextValue("DisplayText", "");
        this.redValue = new IntegerValue("Red", 255, 0, 255);
        this.greenValue = new IntegerValue("Green", 255, 0, 255);
        this.blueValue = new IntegerValue("Blue", 255, 0, 255);
        this.alphaValue = new IntegerValue("Alpha", 255, 0, 255);
        this.colorModeValue = new ListValue("Color", new String[]{"Custom", "Rainbow", "AnotherRainbow", "SkyRainbow"}, "Custom");
        this.shadow = new BoolValue("Shadow", false);
        this.rectRedValue = new IntegerValue("RectRed", 0, 0, 255);
        this.rectGreenValue = new IntegerValue("RectGreen", 0, 0, 255);
        this.rectBlueValue = new IntegerValue("RectBlue", 0, 0, 255);
        this.rectAlphaValue = new IntegerValue("RectAlpha", 255, 0, 255);
        this.rectColorModeValue = new ListValue("RectColor", new String[]{"Custom", "Rainbow", "AnotherRainbow", "SkyRainbow"}, "Custom");
        this.rectValue = new ListValue("Rect", new String[]{"Normal", "RNormal", "OneTap", "Skeet", "None"}, "None");
        this.rectExpandValue = new FloatValue("RectExpand", 0.3f, 0.0f, 1.0f);
        this.rainbowSpeed = new IntegerValue("RainbowSpeed", 10, 1, 10);
        this.rainbowIndex = new IntegerValue("RainbowIndex", 1, 1, 20);
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.displayText = this.getDisplay();
    }

    public /* synthetic */ Text2(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 10.0;
        }
        if ((n & 2) != 0) {
            d2 = 10.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        if ((n & 8) != 0) {
            side = Side.Companion.default();
        }
        this(d, d2, f, side);
    }

    public Text2() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }

    static {
        Companion = new Companion(null);
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        HOUR_FORMAT = new SimpleDateFormat("HH:mm");
        DECIMAL_FORMAT = new DecimalFormat("#.##");
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006\u00a8\u0006\r"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Text2$Companion;", "", "()V", "DATE_FORMAT", "Ljava/text/SimpleDateFormat;", "getDATE_FORMAT", "()Ljava/text/SimpleDateFormat;", "DECIMAL_FORMAT", "Ljava/text/DecimalFormat;", "getDECIMAL_FORMAT", "()Ljava/text/DecimalFormat;", "HOUR_FORMAT", "getHOUR_FORMAT", "Relaxed"})
    public static final class Companion {
        @NotNull
        public final SimpleDateFormat getDATE_FORMAT() {
            return DATE_FORMAT;
        }

        @NotNull
        public final SimpleDateFormat getHOUR_FORMAT() {
            return HOUR_FORMAT;
        }

        @NotNull
        public final DecimalFormat getDECIMAL_FORMAT() {
            return DECIMAL_FORMAT;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

