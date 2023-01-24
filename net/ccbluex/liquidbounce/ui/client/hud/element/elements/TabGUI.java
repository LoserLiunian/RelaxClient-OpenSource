/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.TabGUI$WhenMappings;
import net.ccbluex.liquidbounce.ui.font.AWTFontRenderer;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.render.shader.shaders.RainbowShader;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="TabGUI")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0002:;B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\n\u0010/\u001a\u0004\u0018\u000100H\u0016J\u0018\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020#H\u0016J\u0010\u00106\u001a\u0002022\u0006\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u000202H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010'\u001a\f\u0012\b\u0012\u00060)R\u00020\u00000(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TabGUI;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "(DD)V", "alphaValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "arrowsValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "backgroundAlphaValue", "backgroundBlueValue", "backgroundGreenValue", "backgroundRedValue", "blueValue", "borderAlphaValue", "borderBlueValue", "borderGreenValue", "borderRainbow", "borderRedValue", "borderStrength", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "borderValue", "categoryMenu", "", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "greenValue", "itemY", "", "rainbowX", "rainbowY", "rectangleRainbow", "redValue", "selectedCategory", "", "selectedModule", "tabHeight", "tabY", "tabs", "", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TabGUI$Tab;", "textFade", "textPositionY", "textShadow", "upperCaseValue", "width", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "handleKey", "", "c", "", "keyCode", "parseAction", "action", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TabGUI$Action;", "updateAnimation", "Action", "Tab", "Relaxed"})
public final class TabGUI
extends Element {
    private final FloatValue rainbowX;
    private final FloatValue rainbowY;
    private final IntegerValue redValue;
    private final IntegerValue greenValue;
    private final IntegerValue blueValue;
    private final IntegerValue alphaValue;
    private final BoolValue rectangleRainbow;
    private final IntegerValue backgroundRedValue;
    private final IntegerValue backgroundGreenValue;
    private final IntegerValue backgroundBlueValue;
    private final IntegerValue backgroundAlphaValue;
    private final BoolValue borderValue;
    private final FloatValue borderStrength;
    private final IntegerValue borderRedValue;
    private final IntegerValue borderGreenValue;
    private final IntegerValue borderBlueValue;
    private final IntegerValue borderAlphaValue;
    private final BoolValue borderRainbow;
    private final BoolValue arrowsValue;
    private final FontValue fontValue;
    private final BoolValue textShadow;
    private final BoolValue textFade;
    private final FloatValue textPositionY;
    private final FloatValue width;
    private final FloatValue tabHeight;
    private final BoolValue upperCaseValue;
    private final List<Tab> tabs;
    private boolean categoryMenu;
    private int selectedCategory;
    private int selectedModule;
    private float tabY;
    private float itemY;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    @Override
    @Nullable
    public Border drawElement() {
        RainbowShader instance$iv;
        boolean $i$f$begin;
        this.updateAnimation();
        AWTFontRenderer.Companion.setAssumeNonVolatile(true);
        IFontRenderer fontRenderer = (IFontRenderer)this.fontValue.get();
        boolean rectangleRainbowEnabled = (Boolean)this.rectangleRainbow.get();
        Color backgroundColor = new Color(((Number)this.backgroundRedValue.get()).intValue(), ((Number)this.backgroundGreenValue.get()).intValue(), ((Number)this.backgroundBlueValue.get()).intValue(), ((Number)this.backgroundAlphaValue.get()).intValue());
        Color borderColor = (Boolean)this.borderRainbow.get() == false ? new Color(((Number)this.borderRedValue.get()).intValue(), ((Number)this.borderGreenValue.get()).intValue(), ((Number)this.borderBlueValue.get()).intValue(), ((Number)this.borderAlphaValue.get()).intValue()) : Color.black;
        float guiHeight = (float)this.tabs.size() * ((Number)this.tabHeight.get()).floatValue();
        RenderUtils.drawRect(1.0f, 0.0f, ((Number)this.width.get()).floatValue(), guiHeight, backgroundColor.getRGB());
        if (((Boolean)this.borderValue.get()).booleanValue()) {
            boolean enable$iv;
            RainbowShader.Companion companion = RainbowShader.Companion;
            boolean bl = (Boolean)this.borderRainbow.get();
            float f = ((Number)this.rainbowX.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowX.get()).floatValue();
            float f2 = ((Number)this.rainbowY.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowY.get()).floatValue();
            float offset$iv = (float)(System.currentTimeMillis() % (long)10000) / 10000.0f;
            $i$f$begin = false;
            instance$iv = RainbowShader.INSTANCE;
            if (enable$iv) {
                void y$iv;
                void x$iv;
                instance$iv.setStrengthX((float)x$iv);
                instance$iv.setStrengthY((float)y$iv);
                instance$iv.setOffset(offset$iv);
                instance$iv.startShader();
            }
            Closeable this_$iv = instance$iv;
            enable$iv = false;
            Throwable x$iv = null;
            try {
                Object it = (RainbowShader)this_$iv;
                boolean bl2 = false;
                float f3 = ((Number)this.width.get()).floatValue();
                float f4 = ((Number)this.borderStrength.get()).floatValue();
                Color color = borderColor;
                Intrinsics.checkExpressionValueIsNotNull(color, "borderColor");
                RenderUtils.drawBorder(1.0f, 0.0f, f3, guiHeight, f4, color.getRGB());
                it = Unit.INSTANCE;
            }
            catch (Throwable it) {
                x$iv = it;
                throw it;
            }
            finally {
                CloseableKt.closeFinally(this_$iv, x$iv);
            }
        }
        Color rectColor = !rectangleRainbowEnabled ? new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue(), ((Number)this.alphaValue.get()).intValue()) : Color.black;
        RainbowShader.Companion enable$iv = RainbowShader.Companion;
        float x$iv = ((Number)this.rainbowX.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowX.get()).floatValue();
        float it = ((Number)this.rainbowY.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowY.get()).floatValue();
        float offset$iv = (float)(System.currentTimeMillis() % (long)10000) / 10000.0f;
        $i$f$begin = false;
        instance$iv = RainbowShader.INSTANCE;
        if (rectangleRainbowEnabled) {
            void y$iv;
            instance$iv.setStrengthX(x$iv);
            instance$iv.setStrengthY((float)y$iv);
            instance$iv.setOffset(offset$iv);
            instance$iv.startShader();
        }
        Closeable this_$iv = instance$iv;
        boolean x$iv2 = false;
        Throwable y$iv = null;
        try {
            Object it2 = (RainbowShader)this_$iv;
            boolean bl = false;
            RenderUtils.drawRect(1.0f, 1.0f + this.tabY - 1.0f, ((Number)this.width.get()).floatValue(), this.tabY + ((Number)this.tabHeight.get()).floatValue(), rectColor);
            it2 = Unit.INSTANCE;
        }
        catch (Throwable it2) {
            y$iv = it2;
            throw it2;
        }
        finally {
            CloseableKt.closeFinally(this_$iv, y$iv);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float y = 1.0f;
        Iterable $this$forEachIndexed$iv = this.tabs;
        boolean $i$f$forEachIndexed = false;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            String string;
            void tab;
            int n = index$iv++;
            boolean bl = false;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int n2 = n;
            Tab tab2 = (Tab)item$iv;
            int index = n2;
            boolean bl3 = false;
            if (((Boolean)this.upperCaseValue.get()).booleanValue()) {
                String string2 = tab.getTabName();
                boolean bl4 = false;
                String string3 = string2;
                if (string3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string4 = string3.toUpperCase();
                string = string4;
                Intrinsics.checkExpressionValueIsNotNull(string4, "(this as java.lang.String).toUpperCase()");
            } else {
                string = tab.getTabName();
            }
            String tabName = string;
            float textX = this.getSide().getHorizontal() == Side.Horizontal.RIGHT ? ((Number)this.width.get()).floatValue() - (float)fontRenderer.getStringWidth(tabName) - tab.getTextFade() - (float)3 : tab.getTextFade() + (float)5;
            float textY = y + ((Number)this.textPositionY.get()).floatValue();
            int textColor = this.selectedCategory == index ? 0xFFFFFF : new Color(210, 210, 210).getRGB();
            fontRenderer.drawString(tabName, textX, textY, textColor, (Boolean)this.textShadow.get());
            if (((Boolean)this.arrowsValue.get()).booleanValue()) {
                if (this.getSide().getHorizontal() == Side.Horizontal.RIGHT) {
                    fontRenderer.drawString(!this.categoryMenu && this.selectedCategory == index ? ">" : "<", 3.0f, y + 2.0f, 0xFFFFFF, (Boolean)this.textShadow.get());
                } else {
                    fontRenderer.drawString(!this.categoryMenu && this.selectedCategory == index ? "<" : ">", ((Number)this.width.get()).floatValue() - 8.0f, y + 2.0f, 0xFFFFFF, (Boolean)this.textShadow.get());
                }
            }
            if (index == this.selectedCategory && !this.categoryMenu) {
                float tabX = this.getSide().getHorizontal() == Side.Horizontal.RIGHT ? 1.0f - (float)tab.getMenuWidth() : ((Number)this.width.get()).floatValue() + (float)5;
                Color color = rectColor;
                Intrinsics.checkExpressionValueIsNotNull(color, "rectColor");
                int n3 = color.getRGB();
                int n4 = backgroundColor.getRGB();
                Color color2 = borderColor;
                Intrinsics.checkExpressionValueIsNotNull(color2, "borderColor");
                tab.drawTab(tabX, y, n3, n4, color2.getRGB(), ((Number)this.borderStrength.get()).floatValue(), (Boolean)this.upperCaseValue.get(), fontRenderer, (Boolean)this.borderRainbow.get(), rectangleRainbowEnabled);
            }
            y += ((Number)this.tabHeight.get()).floatValue();
        }
        AWTFontRenderer.Companion.setAssumeNonVolatile(false);
        return new Border(1.0f, 0.0f, ((Number)this.width.get()).floatValue(), guiHeight);
    }

    @Override
    public void handleKey(char c, int keyCode) {
        switch (keyCode) {
            case 200: {
                this.parseAction(Action.UP);
                break;
            }
            case 208: {
                this.parseAction(Action.DOWN);
                break;
            }
            case 205: {
                this.parseAction(this.getSide().getHorizontal() == Side.Horizontal.RIGHT ? Action.LEFT : Action.RIGHT);
                break;
            }
            case 203: {
                this.parseAction(this.getSide().getHorizontal() == Side.Horizontal.RIGHT ? Action.RIGHT : Action.LEFT);
                break;
            }
            case 28: {
                this.parseAction(Action.TOGGLE);
                break;
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    private final void updateAnimation() {
        int delta = RenderUtils.deltaTime;
        float xPos = ((Number)this.tabHeight.get()).floatValue() * (float)this.selectedCategory;
        this.tabY = (int)this.tabY != (int)xPos ? (xPos > this.tabY ? (this.tabY += 0.1f * (float)delta) : (this.tabY -= 0.1f * (float)delta)) : xPos;
        float xPos2 = ((Number)this.tabHeight.get()).floatValue() * (float)this.selectedModule;
        this.itemY = (int)this.itemY != (int)xPos2 ? (xPos2 > this.itemY ? (this.itemY += 0.1f * (float)delta) : (this.itemY -= 0.1f * (float)delta)) : xPos2;
        if (this.categoryMenu) {
            this.itemY = 0.0f;
        }
        if (((Boolean)this.textFade.get()).booleanValue()) {
            Iterable $this$forEachIndexed$iv = this.tabs;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                void tab;
                int n = index$iv++;
                boolean bl = false;
                if (n < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int n2 = n;
                Tab tab2 = (Tab)item$iv;
                int index = n2;
                boolean bl2 = false;
                if (index == this.selectedCategory) {
                    if (tab.getTextFade() < (float)4) {
                        void v0 = tab;
                        v0.setTextFade(v0.getTextFade() + 0.05f * (float)delta);
                    }
                    if (!(tab.getTextFade() > (float)4)) continue;
                    tab.setTextFade(4.0f);
                    continue;
                }
                if (tab.getTextFade() > 0.0f) {
                    void v1 = tab;
                    v1.setTextFade(v1.getTextFade() - 0.05f * (float)delta);
                }
                if (!(tab.getTextFade() < 0.0f)) continue;
                tab.setTextFade(0.0f);
            }
        } else {
            for (Tab tab : this.tabs) {
                if (tab.getTextFade() > 0.0f) {
                    Tab tab3 = tab;
                    tab3.setTextFade(tab3.getTextFade() - 0.05f * (float)delta);
                }
                if (!(tab.getTextFade() < 0.0f)) continue;
                tab.setTextFade(0.0f);
            }
        }
    }

    private final void parseAction(Action action) {
        boolean toggle = false;
        switch (TabGUI$WhenMappings.$EnumSwitchMapping$0[action.ordinal()]) {
            case 1: {
                if (this.categoryMenu) {
                    TabGUI tabGUI = this;
                    tabGUI.selectedCategory += -1;
                    int cfr_ignored_0 = tabGUI.selectedCategory;
                    if (this.selectedCategory >= 0) break;
                    this.selectedCategory = this.tabs.size() - 1;
                    this.tabY = ((Number)this.tabHeight.get()).floatValue() * (float)this.selectedCategory;
                    break;
                }
                TabGUI tabGUI = this;
                tabGUI.selectedModule += -1;
                int cfr_ignored_1 = tabGUI.selectedModule;
                if (this.selectedModule >= 0) break;
                this.selectedModule = this.tabs.get(this.selectedCategory).getModules().size() - 1;
                this.itemY = ((Number)this.tabHeight.get()).floatValue() * (float)this.selectedModule;
                break;
            }
            case 2: {
                if (this.categoryMenu) {
                    TabGUI tabGUI = this;
                    ++tabGUI.selectedCategory;
                    int cfr_ignored_2 = tabGUI.selectedCategory;
                    if (this.selectedCategory <= this.tabs.size() - 1) break;
                    this.selectedCategory = 0;
                    this.tabY = ((Number)this.tabHeight.get()).floatValue() * (float)this.selectedCategory;
                    break;
                }
                TabGUI tabGUI = this;
                ++tabGUI.selectedModule;
                int cfr_ignored_3 = tabGUI.selectedModule;
                if (this.selectedModule <= this.tabs.get(this.selectedCategory).getModules().size() - 1) break;
                this.selectedModule = 0;
                this.itemY = ((Number)this.tabHeight.get()).floatValue() * (float)this.selectedModule;
                break;
            }
            case 3: {
                if (this.categoryMenu) break;
                this.categoryMenu = true;
                break;
            }
            case 4: {
                if (!this.categoryMenu) {
                    toggle = true;
                    break;
                }
                this.categoryMenu = false;
                this.selectedModule = 0;
                break;
            }
            case 5: {
                if (this.categoryMenu) break;
                toggle = true;
                break;
            }
        }
        if (toggle) {
            int sel = this.selectedModule;
            this.tabs.get(this.selectedCategory).getModules().get(sel).toggle();
        }
    }

    /*
     * WARNING - void declaration
     */
    public TabGUI(double x, double y) {
        super(x, y, 0.0f, null, 12, null);
        List list;
        this.rainbowX = new FloatValue("Rainbow-X", -1000.0f, -2000.0f, 2000.0f);
        this.rainbowY = new FloatValue("Rainbow-Y", -1000.0f, -2000.0f, 2000.0f);
        this.redValue = new IntegerValue("Rectangle Red", 0, 0, 255);
        this.greenValue = new IntegerValue("Rectangle Green", 148, 0, 255);
        this.blueValue = new IntegerValue("Rectangle Blue", 255, 0, 255);
        this.alphaValue = new IntegerValue("Rectangle Alpha", 140, 0, 255);
        this.rectangleRainbow = new BoolValue("Rectangle Rainbow", false);
        this.backgroundRedValue = new IntegerValue("Background Red", 0, 0, 255);
        this.backgroundGreenValue = new IntegerValue("Background Green", 0, 0, 255);
        this.backgroundBlueValue = new IntegerValue("Background Blue", 0, 0, 255);
        this.backgroundAlphaValue = new IntegerValue("Background Alpha", 150, 0, 255);
        this.borderValue = new BoolValue("Border", true);
        this.borderStrength = new FloatValue("Border Strength", 2.0f, 1.0f, 5.0f);
        this.borderRedValue = new IntegerValue("Border Red", 0, 0, 255);
        this.borderGreenValue = new IntegerValue("Border Green", 0, 0, 255);
        this.borderBlueValue = new IntegerValue("Border Blue", 0, 0, 255);
        this.borderAlphaValue = new IntegerValue("Border Alpha", 150, 0, 255);
        this.borderRainbow = new BoolValue("Border Rainbow", false);
        this.arrowsValue = new BoolValue("Arrows", true);
        IFontRenderer iFontRenderer = Fonts.font35;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font35");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.textShadow = new BoolValue("TextShadow", false);
        this.textFade = new BoolValue("TextFade", true);
        this.textPositionY = new FloatValue("TextPosition-Y", 2.0f, 0.0f, 5.0f);
        this.width = new FloatValue("Width", 60.0f, 55.0f, 100.0f);
        this.tabHeight = new FloatValue("TabHeight", 12.0f, 10.0f, 15.0f);
        this.upperCaseValue = new BoolValue("UpperCase", false);
        TabGUI tabGUI = this;
        boolean bl = false;
        tabGUI.tabs = list = (List)new ArrayList();
        this.categoryMenu = true;
        for (ModuleCategory category : ModuleCategory.values()) {
            void $this$filterTo$iv$iv;
            Tab tab = new Tab(category.getDisplayName());
            Iterable $this$filter$iv = LiquidBounce.INSTANCE.getModuleManager().getModules();
            boolean $i$f$filter = false;
            Iterable iterable = $this$filter$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterTo = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                Module module = (Module)element$iv$iv;
                boolean bl2 = false;
                if (!(category == module.getCategory())) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            Iterable $this$forEach$iv = (List)destination$iv$iv;
            boolean $i$f$forEach = false;
            for (Object element$iv : $this$forEach$iv) {
                Module e = (Module)element$iv;
                boolean bl3 = false;
                tab.getModules().add(e);
            }
            this.tabs.add(tab);
        }
    }

    public /* synthetic */ TabGUI(double d, double d2, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 5.0;
        }
        if ((n & 2) != 0) {
            d2 = 25.0;
        }
        this(d, d2);
    }

    public TabGUI() {
        this(0.0, 0.0, 3, (DefaultConstructorMarker)null);
    }

    public static final /* synthetic */ void access$setItemY$p(TabGUI $this, float f) {
        $this.itemY = f;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JV\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006&"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TabGUI$Tab;", "", "tabName", "", "(Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TabGUI;Ljava/lang/String;)V", "menuWidth", "", "getMenuWidth", "()I", "setMenuWidth", "(I)V", "modules", "", "Lnet/ccbluex/liquidbounce/features/module/Module;", "getModules", "()Ljava/util/List;", "getTabName", "()Ljava/lang/String;", "textFade", "", "getTextFade", "()F", "setTextFade", "(F)V", "drawTab", "", "x", "y", "color", "backgroundColor", "borderColor", "borderStrength", "upperCase", "", "fontRenderer", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;", "borderRainbow", "rectRainbow", "Relaxed"})
    private final class Tab {
        @NotNull
        private final List<Module> modules;
        private int menuWidth;
        private float textFade;
        @NotNull
        private final String tabName;

        @NotNull
        public final List<Module> getModules() {
            return this.modules;
        }

        public final int getMenuWidth() {
            return this.menuWidth;
        }

        public final void setMenuWidth(int n) {
            this.menuWidth = n;
        }

        public final float getTextFade() {
            return this.textFade;
        }

        public final void setTextFade(float f) {
            this.textFade = f;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * WARNING - void declaration
         */
        public final void drawTab(float x, float y, int color, int backgroundColor, int borderColor, float borderStrength, boolean upperCase, @NotNull IFontRenderer fontRenderer, boolean borderRainbow, boolean rectRainbow) {
            Object this_$iv;
            RainbowShader instance$iv;
            boolean $i$f$begin;
            Intrinsics.checkParameterIsNotNull(fontRenderer, "fontRenderer");
            int maxWidth = 0;
            for (Module module : this.modules) {
                String string;
                String string2;
                String string3;
                boolean bl;
                IFontRenderer iFontRenderer;
                String string4;
                IFontRenderer iFontRenderer2 = fontRenderer;
                if (upperCase) {
                    string4 = module.getName();
                    iFontRenderer = iFontRenderer2;
                    bl = false;
                    String string5 = string4;
                    if (string5 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(string5.toUpperCase(), "(this as java.lang.String).toUpperCase()");
                    iFontRenderer2 = iFontRenderer;
                    string2 = string3;
                } else {
                    string2 = module.getName();
                }
                if (iFontRenderer2.getStringWidth(string2) + 4 <= maxWidth) continue;
                IFontRenderer iFontRenderer3 = fontRenderer;
                if (upperCase) {
                    string4 = module.getName();
                    iFontRenderer = iFontRenderer3;
                    bl = false;
                    String string6 = string4;
                    if (string6 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(string6.toUpperCase(), "(this as java.lang.String).toUpperCase()");
                    iFontRenderer3 = iFontRenderer;
                    string = string3;
                } else {
                    string = module.getName();
                }
                maxWidth = (int)((float)iFontRenderer3.getStringWidth(string) + 7.0f);
            }
            this.menuWidth = maxWidth;
            float menuHeight = (float)this.modules.size() * ((Number)TabGUI.this.tabHeight.get()).floatValue();
            if (((Boolean)TabGUI.this.borderValue.get()).booleanValue()) {
                RainbowShader.Companion companion = RainbowShader.Companion;
                float f = ((Number)TabGUI.this.rainbowX.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)TabGUI.this.rainbowX.get()).floatValue();
                float f2 = ((Number)TabGUI.this.rainbowY.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)TabGUI.this.rainbowY.get()).floatValue();
                float offset$iv = (float)(System.currentTimeMillis() % (long)10000) / 10000.0f;
                $i$f$begin = false;
                instance$iv = RainbowShader.INSTANCE;
                if (borderRainbow) {
                    void y$iv;
                    void x$iv;
                    instance$iv.setStrengthX((float)x$iv);
                    instance$iv.setStrengthY((float)y$iv);
                    instance$iv.setOffset(offset$iv);
                    instance$iv.startShader();
                }
                this_$iv = instance$iv;
                boolean x$iv = false;
                Throwable y$iv = null;
                try {
                    Object it = (RainbowShader)this_$iv;
                    boolean bl = false;
                    RenderUtils.drawBorder(x - 1.0f, y - 1.0f, x + (float)this.menuWidth - 2.0f, y + menuHeight - 1.0f, borderStrength, borderColor);
                    it = Unit.INSTANCE;
                }
                catch (Throwable it) {
                    y$iv = it;
                    throw it;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)this_$iv, y$iv);
                }
            }
            RenderUtils.drawRect(x - 1.0f, y - 1.0f, x + (float)this.menuWidth - 2.0f, y + menuHeight - 1.0f, backgroundColor);
            this_$iv = RainbowShader.Companion;
            float x$iv = ((Number)TabGUI.this.rainbowX.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)TabGUI.this.rainbowX.get()).floatValue();
            float y$iv = ((Number)TabGUI.this.rainbowY.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)TabGUI.this.rainbowY.get()).floatValue();
            float offset$iv = (float)(System.currentTimeMillis() % (long)10000) / 10000.0f;
            $i$f$begin = false;
            instance$iv = RainbowShader.INSTANCE;
            if (rectRainbow) {
                instance$iv.setStrengthX(x$iv);
                instance$iv.setStrengthY(y$iv);
                instance$iv.setOffset(offset$iv);
                instance$iv.startShader();
            }
            this_$iv = instance$iv;
            boolean x$iv2 = false;
            Throwable y$iv2 = null;
            try {
                RainbowShader it = (RainbowShader)this_$iv;
                boolean bl = false;
                RenderUtils.drawRect(x - 1.0f, y + TabGUI.this.itemY - 1.0f, x + (float)this.menuWidth - 2.0f, y + TabGUI.this.itemY + ((Number)TabGUI.this.tabHeight.get()).floatValue() - 1.0f, color);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable) {
                y$iv2 = throwable;
                throw throwable;
            }
            finally {
                CloseableKt.closeFinally((Closeable)this_$iv, y$iv2);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Iterable $this$forEachIndexed$iv = this.modules;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                String string;
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
                int moduleColor = module.getState() ? 0xFFFFFF : new Color(205, 205, 205).getRGB();
                IFontRenderer iFontRenderer = fontRenderer;
                if (upperCase) {
                    String string7;
                    String string8 = module.getName();
                    IFontRenderer iFontRenderer4 = iFontRenderer;
                    boolean bl3 = false;
                    String string9 = string8;
                    if (string9 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(string9.toUpperCase(), "(this as java.lang.String).toUpperCase()");
                    iFontRenderer = iFontRenderer4;
                    string = string7;
                } else {
                    string = module.getName();
                }
                iFontRenderer.drawString(string, x + 2.0f, y + ((Number)TabGUI.this.tabHeight.get()).floatValue() * (float)index + ((Number)TabGUI.this.textPositionY.get()).floatValue(), moduleColor, (Boolean)TabGUI.this.textShadow.get());
            }
        }

        @NotNull
        public final String getTabName() {
            return this.tabName;
        }

        public Tab(String tabName) {
            List list;
            Intrinsics.checkParameterIsNotNull(tabName, "tabName");
            this.tabName = tabName;
            Tab tab = this;
            boolean bl = false;
            tab.modules = list = (List)new ArrayList();
        }
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TabGUI$Action;", "", "(Ljava/lang/String;I)V", "UP", "DOWN", "LEFT", "RIGHT", "TOGGLE", "Relaxed"})
    public static final class Action
    extends Enum<Action> {
        public static final /* enum */ Action UP;
        public static final /* enum */ Action DOWN;
        public static final /* enum */ Action LEFT;
        public static final /* enum */ Action RIGHT;
        public static final /* enum */ Action TOGGLE;
        private static final /* synthetic */ Action[] $VALUES;

        static {
            Action[] actionArray = new Action[5];
            Action[] actionArray2 = actionArray;
            actionArray[0] = UP = new Action();
            actionArray[1] = DOWN = new Action();
            actionArray[2] = LEFT = new Action();
            actionArray[3] = RIGHT = new Action();
            actionArray[4] = TOGGLE = new Action();
            $VALUES = actionArray;
        }

        public static Action[] values() {
            return (Action[])$VALUES.clone();
        }

        public static Action valueOf(String string) {
            return Enum.valueOf(Action.class, string);
        }
    }
}

