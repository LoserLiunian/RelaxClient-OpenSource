/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 */
package net.ccbluex.liquidbounce.ui.font;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.util.IWrappedFontRenderer;
import net.ccbluex.liquidbounce.event.TextEvent;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.ui.font.AWTFontRenderer;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.render.shader.shaders.RainbowFontShader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\f\n\u0002\b\u0005\u0018\u0000 ,2\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J0\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J*\u0010\u001f\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J2\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J*\u0010!\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J&\u0010\"\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000eJ<\u0010#\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u001e2\b\b\u0002\u0010%\u001a\u00020\u001eH\u0002J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020(H\u0016J\u0012\u0010+\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u0018H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010\u00a8\u0006-"}, d2={"Lnet/ccbluex/liquidbounce/ui/font/GameFontRenderer;", "Lnet/ccbluex/liquidbounce/api/util/IWrappedFontRenderer;", "font", "Ljava/awt/Font;", "(Ljava/awt/Font;)V", "boldFont", "Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer;", "boldItalicFont", "defaultFont", "getDefaultFont", "()Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer;", "setDefaultFont", "(Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer;)V", "fontHeight", "", "getFontHeight", "()I", "height", "getHeight", "italicFont", "size", "getSize", "drawCenteredString", "s", "", "x", "", "y", "color", "shadow", "", "drawString", "text", "drawStringWithShadow", "drawStringWithShadow2", "drawText", "ignoreColor", "rainbow", "getCharWidth", "character", "", "getColorCode", "charCode", "getStringWidth", "Companion", "Relaxed"})
public final class GameFontRenderer
implements IWrappedFontRenderer {
    private final int fontHeight;
    @NotNull
    private AWTFontRenderer defaultFont;
    private AWTFontRenderer boldFont;
    private AWTFontRenderer italicFont;
    private AWTFontRenderer boldItalicFont;
    public static final Companion Companion = new Companion(null);

    public final int getFontHeight() {
        return this.fontHeight;
    }

    @NotNull
    public final AWTFontRenderer getDefaultFont() {
        return this.defaultFont;
    }

    public final void setDefaultFont(@NotNull AWTFontRenderer aWTFontRenderer) {
        Intrinsics.checkParameterIsNotNull(aWTFontRenderer, "<set-?>");
        this.defaultFont = aWTFontRenderer;
    }

    public final int getHeight() {
        return this.defaultFont.getHeight() / 2;
    }

    public final int getSize() {
        return this.defaultFont.getFont().getSize();
    }

    public final int drawStringWithShadow2(@NotNull String text, float x, float y, int color) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.drawString(text, x + 0.5f, y + 0.5f, -16777216);
        return this.drawString(text, x, y, color);
    }

    @Override
    public int drawString(@Nullable String s, float x, float y, int color) {
        return this.drawString(s, x, y, color, false);
    }

    @Override
    public int drawStringWithShadow(@Nullable String text, float x, float y, int color) {
        return this.drawString(text, x, y, color, true);
    }

    @Override
    public int drawCenteredString(@NotNull String s, float x, float y, int color, boolean shadow) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        return this.drawString(s, x - (float)this.getStringWidth(s) / 2.0f, y, color, shadow);
    }

    @Override
    public int drawCenteredString(@NotNull String s, float x, float y, int color) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        return this.drawStringWithShadow(s, x - (float)this.getStringWidth(s) / 2.0f, y, color);
    }

    @Override
    public int drawString(@Nullable String text, float x, float y, int color, boolean shadow) {
        boolean rainbow;
        float currY;
        String currentText;
        block1: {
            block5: {
                block4: {
                    block3: {
                        block2: {
                            currentText = text;
                            TextEvent event = new TextEvent(currentText);
                            LiquidBounce.INSTANCE.getEventManager().callEvent(event);
                            String string = event.getText();
                            if (string == null) {
                                return 0;
                            }
                            currentText = string;
                            currY = y - 3.0f;
                            rainbow = RainbowFontShader.INSTANCE.isInUse();
                            if (!shadow) break block1;
                            GL20.glUseProgram((int)0);
                            if (!StringsKt.equals((String)HUD.Companion.getShadowValue().get(), "Test", true)) break block2;
                            GameFontRenderer.drawText$default(this, currentText, x + 0.5f, currY + 0.5f, new Color(200, 200, 200, 70).getRGB(), true, false, 32, null);
                            GameFontRenderer.drawText$default(this, currentText, x - 0.5f, currY - 0.5f, new Color(200, 200, 200, 70).getRGB(), true, false, 32, null);
                            GameFontRenderer.drawText$default(this, currentText, x + 0.5f, currY - 0.5f, new Color(200, 200, 200, 70).getRGB(), true, false, 32, null);
                            GameFontRenderer.drawText$default(this, currentText, x - 0.5f, currY + 0.5f, new Color(200, 200, 200, 70).getRGB(), true, false, 32, null);
                            break block1;
                        }
                        if (!StringsKt.equals((String)HUD.Companion.getShadowValue().get(), "LiquidBounce", true)) break block3;
                        GameFontRenderer.drawText$default(this, currentText, x + 1.0f, currY + 1.0f, new Color(0, 0, 0, 150).getRGB(), true, false, 32, null);
                        break block1;
                    }
                    if (!StringsKt.equals((String)HUD.Companion.getShadowValue().get(), "Default", true)) break block4;
                    GameFontRenderer.drawText$default(this, currentText, x + 0.5f, currY + 0.5f, new Color(0, 0, 0, 130).getRGB(), true, false, 32, null);
                    break block1;
                }
                if (!StringsKt.equals((String)HUD.Companion.getShadowValue().get(), "Autumn", true)) break block5;
                GameFontRenderer.drawText$default(this, currentText, x + 1.0f, currY + 1.0f, new Color(20, 20, 20, 200).getRGB(), true, false, 32, null);
                break block1;
            }
            if (!StringsKt.equals((String)HUD.Companion.getShadowValue().get(), "Outline", true)) break block1;
            GameFontRenderer.drawText$default(this, currentText, x + 0.5f, currY + 0.5f, new Color(0, 0, 0, 130).getRGB(), true, false, 32, null);
            GameFontRenderer.drawText$default(this, currentText, x - 0.5f, currY - 0.5f, new Color(0, 0, 0, 130).getRGB(), true, false, 32, null);
            GameFontRenderer.drawText$default(this, currentText, x + 0.5f, currY - 0.5f, new Color(0, 0, 0, 130).getRGB(), true, false, 32, null);
            GameFontRenderer.drawText$default(this, currentText, x - 0.5f, currY + 0.5f, new Color(0, 0, 0, 130).getRGB(), true, false, 32, null);
        }
        return this.drawText(currentText, x, currY, color, false, rainbow);
    }

    /*
     * WARNING - void declaration
     */
    private final int drawText(String text, float x, float y, int color, boolean ignoreColor, boolean rainbow) {
        if (text == null) {
            return 0;
        }
        CharSequence charSequence = text;
        boolean bl = false;
        boolean bl2 = false;
        if (charSequence.length() == 0) {
            return (int)x;
        }
        int rainbowShaderId = RainbowFontShader.INSTANCE.getProgramId();
        if (rainbow) {
            GL20.glUseProgram((int)rainbowShaderId);
        }
        GL11.glTranslated((double)((double)x - 1.5), (double)((double)y + 0.5), (double)0.0);
        WrapperImpl.INSTANCE.getClassProvider().getGlStateManager().enableAlpha();
        WrapperImpl.INSTANCE.getClassProvider().getGlStateManager().enableBlend();
        WrapperImpl.INSTANCE.getClassProvider().getGlStateManager().tryBlendFuncSeparate(770, 771, 1, 0);
        WrapperImpl.INSTANCE.getClassProvider().getGlStateManager().enableTexture2D();
        int currentColor = color;
        if ((currentColor & 0xFC000000) == 0) {
            currentColor |= 0xFF000000;
        }
        int defaultColor = currentColor;
        int alpha = currentColor >> 24 & 0xFF;
        if (StringsKt.contains$default((CharSequence)text, "\u00a7", false, 2, null)) {
            List parts = StringsKt.split$default((CharSequence)text, new String[]{"\u00a7"}, false, 0, 6, null);
            AWTFontRenderer currentFont = this.defaultFont;
            double width = 0.0;
            boolean randomCase = false;
            boolean bold = false;
            boolean italic = false;
            boolean strikeThrough = false;
            boolean underline = false;
            Iterable $this$forEachIndexed$iv = parts;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                String words;
                int colorIndex;
                void part;
                int n = index$iv++;
                boolean bl3 = false;
                if (n < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int n2 = n;
                String string = (String)item$iv;
                int index = n2;
                boolean bl4 = false;
                CharSequence charSequence2 = (CharSequence)part;
                boolean bl5 = false;
                if (charSequence2.length() == 0) continue;
                if (index == 0) {
                    currentFont.drawString((String)part, width, 0.0, currentColor);
                    width += (double)currentFont.getStringWidth((String)part);
                    continue;
                }
                void var30_33 = part;
                int n3 = 1;
                int n4 = 0;
                void v0 = var30_33;
                if (v0 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                Intrinsics.checkExpressionValueIsNotNull(v0.substring(n3), "(this as java.lang.String).substring(startIndex)");
                char type = part.charAt(0);
                n4 = colorIndex = Companion.getColorIndex(type);
                if (0 <= n4 && 15 >= n4) {
                    if (!ignoreColor) {
                        currentColor = ColorUtils.hexColors[colorIndex] | alpha << 24;
                        if (rainbow) {
                            GL20.glUseProgram((int)0);
                        }
                    }
                    bold = false;
                    italic = false;
                    randomCase = false;
                    underline = false;
                    strikeThrough = false;
                } else if (colorIndex == 16) {
                    randomCase = true;
                } else if (colorIndex == 17) {
                    bold = true;
                } else if (colorIndex == 18) {
                    strikeThrough = true;
                } else if (colorIndex == 19) {
                    underline = true;
                } else if (colorIndex == 20) {
                    italic = true;
                } else if (colorIndex == 21) {
                    currentColor = color;
                    if ((currentColor & 0xFC000000) == 0) {
                        currentColor |= 0xFF000000;
                    }
                    if (rainbow) {
                        GL20.glUseProgram((int)rainbowShaderId);
                    }
                    bold = false;
                    italic = false;
                    randomCase = false;
                    underline = false;
                    strikeThrough = false;
                }
                currentFont = bold && italic ? this.boldItalicFont : (bold ? this.boldFont : (italic ? this.italicFont : this.defaultFont));
                currentFont.drawString(randomCase ? ColorUtils.INSTANCE.randomMagicText(words) : words, width, 0.0, currentColor);
                if (strikeThrough) {
                    RenderUtils.drawLine(width / 2.0 + 1.0, (double)currentFont.getHeight() / 3.0, (width + (double)currentFont.getStringWidth(words)) / 2.0 + 1.0, (double)currentFont.getHeight() / 3.0, (float)this.fontHeight / 16.0f);
                }
                if (underline) {
                    RenderUtils.drawLine(width / 2.0 + 1.0, (double)currentFont.getHeight() / 2.0, (width + (double)currentFont.getStringWidth(words)) / 2.0 + 1.0, (double)currentFont.getHeight() / 2.0, (float)this.fontHeight / 16.0f);
                }
                width += (double)currentFont.getStringWidth(words);
            }
        } else {
            this.defaultFont.drawString(text, 0.0, 0.0, currentColor);
        }
        WrapperImpl.INSTANCE.getClassProvider().getGlStateManager().disableBlend();
        GL11.glTranslated((double)(-((double)x - 1.5)), (double)(-((double)y + 0.5)), (double)0.0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        return (int)(x + (float)this.getStringWidth(text));
    }

    static /* synthetic */ int drawText$default(GameFontRenderer gameFontRenderer, String string, float f, float f2, int n, boolean bl, boolean bl2, int n2, Object object) {
        if ((n2 & 0x20) != 0) {
            bl2 = false;
        }
        return gameFontRenderer.drawText(string, f, f2, n, bl, bl2);
    }

    @Override
    public int getColorCode(char charCode) {
        return ColorUtils.hexColors[Companion.getColorIndex(charCode)];
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public int getStringWidth(@Nullable String text) {
        int n;
        String currentText = text;
        TextEvent event = new TextEvent(currentText);
        LiquidBounce.INSTANCE.getEventManager().callEvent(event);
        String string = event.getText();
        if (string == null) {
            return 0;
        }
        currentText = string;
        if (StringsKt.contains$default((CharSequence)currentText, "\u00a7", false, 2, null)) {
            List parts = StringsKt.split$default((CharSequence)currentText, new String[]{"\u00a7"}, false, 0, 6, null);
            AWTFontRenderer currentFont = this.defaultFont;
            int width = 0;
            boolean bold = false;
            boolean italic = false;
            Iterable $this$forEachIndexed$iv = parts;
            boolean $i$f$forEachIndexed = false;
            int index$iv = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                String words;
                void part;
                int n2 = index$iv++;
                boolean bl = false;
                if (n2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int n3 = n2;
                String string2 = (String)item$iv;
                int index = n3;
                boolean bl2 = false;
                CharSequence charSequence = (CharSequence)part;
                boolean bl3 = false;
                if (charSequence.length() == 0) continue;
                if (index == 0) {
                    width += currentFont.getStringWidth((String)part);
                    continue;
                }
                void var21_22 = part;
                int n4 = 1;
                boolean bl4 = false;
                void v1 = var21_22;
                if (v1 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                Intrinsics.checkExpressionValueIsNotNull(v1.substring(n4), "(this as java.lang.String).substring(startIndex)");
                char type = part.charAt(0);
                int colorIndex = Companion.getColorIndex(type);
                if (colorIndex < 16) {
                    bold = false;
                    italic = false;
                } else if (colorIndex == 17) {
                    bold = true;
                } else if (colorIndex == 20) {
                    italic = true;
                } else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                }
                currentFont = bold && italic ? this.boldItalicFont : (bold ? this.boldFont : (italic ? this.italicFont : this.defaultFont));
                width += currentFont.getStringWidth(words);
            }
            n = width / 2;
        } else {
            n = this.defaultFont.getStringWidth(currentText) / 2;
        }
        return n;
    }

    @Override
    public int getCharWidth(char character) {
        return this.getStringWidth(String.valueOf(character));
    }

    public GameFontRenderer(@NotNull Font font) {
        Intrinsics.checkParameterIsNotNull(font, "font");
        this.defaultFont = new AWTFontRenderer(font, 0, 0, false, 14, null);
        Font font2 = font.deriveFont(1);
        Intrinsics.checkExpressionValueIsNotNull(font2, "font.deriveFont(Font.BOLD)");
        this.boldFont = new AWTFontRenderer(font2, 0, 0, false, 14, null);
        Font font3 = font.deriveFont(2);
        Intrinsics.checkExpressionValueIsNotNull(font3, "font.deriveFont(Font.ITALIC)");
        this.italicFont = new AWTFontRenderer(font3, 0, 0, false, 14, null);
        Font font4 = font.deriveFont(3);
        Intrinsics.checkExpressionValueIsNotNull(font4, "font.deriveFont(Font.BOLD or Font.ITALIC)");
        this.boldItalicFont = new AWTFontRenderer(font4, 0, 0, false, 14, null);
        this.fontHeight = this.getHeight();
    }

    @JvmStatic
    public static final int getColorIndex(char type) {
        return Companion.getColorIndex(type);
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2={"Lnet/ccbluex/liquidbounce/ui/font/GameFontRenderer$Companion;", "", "()V", "getColorIndex", "", "type", "", "Relaxed"})
    public static final class Companion {
        @JvmStatic
        public final int getColorIndex(char type) {
            char c = type;
            char c2 = c;
            return '0' <= c2 && '9' >= c2 ? type - 48 : ('a' <= (c2 = c) && 'f' >= c2 ? type - 97 + 10 : ('k' <= (c2 = c) && 'o' >= c2 ? type - 107 + 16 : (c == 'r' ? 21 : -1)));
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

