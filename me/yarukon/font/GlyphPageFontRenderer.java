/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  org.lwjgl.opengl.GL11
 */
package me.yarukon.font;

import java.awt.Font;
import java.util.Locale;
import me.yarukon.font.GlyphPage;
import me.yarukon.font.Yarukon;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GlyphPageFontRenderer {
    private float posX;
    private float posY;
    private final int[] colorCode = new int[32];
    private float red;
    private float blue;
    private float green;
    private float alpha;
    private int textColor;
    private boolean randomStyle;
    private boolean boldStyle;
    private boolean italicStyle;
    private boolean underlineStyle;
    private boolean strikethroughStyle;
    private final GlyphPage regularGlyphPage;
    private final GlyphPage boldGlyphPage;
    private final GlyphPage italicGlyphPage;
    private final GlyphPage boldItalicGlyphPage;

    public GlyphPageFontRenderer(GlyphPage regularGlyphPage, GlyphPage boldGlyphPage, GlyphPage italicGlyphPage, GlyphPage boldItalicGlyphPage) {
        this.regularGlyphPage = regularGlyphPage;
        this.boldGlyphPage = boldGlyphPage;
        this.italicGlyphPage = italicGlyphPage;
        this.boldItalicGlyphPage = boldItalicGlyphPage;
        for (int i = 0; i < 32; ++i) {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i & 1) * 170 + j;
            if (i == 6) {
                k += 85;
            }
            if (i >= 16) {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }
            this.colorCode[i] = (k & 0xFF) << 16 | (l & 0xFF) << 8 | i1 & 0xFF;
        }
    }

    public static GlyphPageFontRenderer create(Font font, boolean allChars) {
        GlyphPage regularPage = new GlyphPage(font, true, true);
        regularPage.generateGlyphPage(allChars ? Yarukon.INSTANCE.chars : Yarukon.INSTANCE.ascii_chars, allChars);
        return new GlyphPageFontRenderer(regularPage, regularPage, regularPage, regularPage);
    }

    public int drawCenteredString(String text, float x, float y, int color) {
        return this.drawString(text, x - (float)this.getStringWidth(text) / 2.0f, y, color, false);
    }

    public int drawCenteredStringWithShadow(String text, float x, float y, int color) {
        return this.drawString(text, x - (float)this.getStringWidth(text) / 2.0f, y, color, true);
    }

    public int drawStringWithShadow(String text, float x, float y, int color) {
        return this.drawString(text, x, y, color, true);
    }

    public int drawString(String text, float x, float y, int color) {
        return this.drawString(text, x, y, color, false);
    }

    public int drawString(String text, float x, float y, int color, boolean dropShadow) {
        int i;
        GlStateManager.func_179141_d();
        this.resetStyles();
        if (dropShadow) {
            i = this.renderString(text, x + 1.0f, y + 1.0f, color, true);
            i = Math.max(i, this.renderString(text, x, y, color, false));
        } else {
            i = this.renderString(text, x, y, color, false);
        }
        return i;
    }

    private int renderString(String text, float x, float y, int color, boolean dropShadow) {
        if (text == null) {
            return 0;
        }
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        if (dropShadow) {
            color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
        }
        this.red = (float)(color >> 16 & 0xFF) / 255.0f;
        this.blue = (float)(color >> 8 & 0xFF) / 255.0f;
        this.green = (float)(color & 0xFF) / 255.0f;
        this.alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        GlStateManager.func_179131_c((float)this.red, (float)this.blue, (float)this.green, (float)this.alpha);
        this.posX = x * 2.0f;
        this.posY = y * 2.0f;
        this.renderStringAtPos(text, dropShadow);
        return (int)(this.posX / 4.0f);
    }

    private void renderStringAtPos(String text, boolean shadow) {
        GlyphPage glyphPage = this.getCurrentGlyphPage();
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)771);
        GlStateManager.func_179098_w();
        glyphPage.bindTexture();
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        for (int i = 0; i < text.length(); ++i) {
            char c0 = text.charAt(i);
            if (c0 == '\u00a7' && i + 1 < text.length()) {
                int i1 = "0123456789abcdefklmnor".indexOf(text.toLowerCase(Locale.ENGLISH).charAt(i + 1));
                if (i1 < 16) {
                    int j1;
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (i1 < 0) {
                        i1 = 15;
                    }
                    if (shadow) {
                        i1 += 16;
                    }
                    this.textColor = j1 = this.colorCode[i1];
                    GlStateManager.func_179131_c((float)((float)(j1 >> 16) / 255.0f), (float)((float)(j1 >> 8 & 0xFF) / 255.0f), (float)((float)(j1 & 0xFF) / 255.0f), (float)this.alpha);
                } else if (i1 == 16) {
                    this.randomStyle = true;
                } else if (i1 == 17) {
                    this.boldStyle = true;
                } else if (i1 == 18) {
                    this.strikethroughStyle = true;
                } else if (i1 == 19) {
                    this.underlineStyle = true;
                } else if (i1 == 20) {
                    this.italicStyle = true;
                } else {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    GlStateManager.func_179131_c((float)this.red, (float)this.blue, (float)this.green, (float)this.alpha);
                }
                ++i;
                continue;
            }
            glyphPage = this.getCurrentGlyphPage();
            glyphPage.bindTexture();
            float f = glyphPage.drawChar(c0, this.posX, this.posY);
            this.doDraw(f, glyphPage);
        }
        glyphPage.unbindTexture();
        GL11.glPopMatrix();
    }

    private void doDraw(float f, GlyphPage glyphPage) {
        if (this.strikethroughStyle) {
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder worldrenderer = tessellator.func_178180_c();
            GlStateManager.func_179090_x();
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
            worldrenderer.func_181662_b((double)this.posX, (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2)), 0.0).func_181675_d();
            worldrenderer.func_181662_b((double)(this.posX + f), (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2)), 0.0).func_181675_d();
            worldrenderer.func_181662_b((double)(this.posX + f), (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2) - 1.0f), 0.0).func_181675_d();
            worldrenderer.func_181662_b((double)this.posX, (double)(this.posY + (float)(glyphPage.getMaxFontHeight() / 2) - 1.0f), 0.0).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179098_w();
        }
        if (this.underlineStyle) {
            Tessellator tessellator1 = Tessellator.func_178181_a();
            BufferBuilder worldrenderer1 = tessellator1.func_178180_c();
            GlStateManager.func_179090_x();
            worldrenderer1.func_181668_a(7, DefaultVertexFormats.field_181705_e);
            int l = this.underlineStyle ? -1 : 0;
            worldrenderer1.func_181662_b((double)(this.posX + (float)l), (double)(this.posY + (float)glyphPage.getMaxFontHeight()), 0.0).func_181675_d();
            worldrenderer1.func_181662_b((double)(this.posX + f), (double)(this.posY + (float)glyphPage.getMaxFontHeight()), 0.0).func_181675_d();
            worldrenderer1.func_181662_b((double)(this.posX + f), (double)(this.posY + (float)glyphPage.getMaxFontHeight() - 1.0f), 0.0).func_181675_d();
            worldrenderer1.func_181662_b((double)(this.posX + (float)l), (double)(this.posY + (float)glyphPage.getMaxFontHeight() - 1.0f), 0.0).func_181675_d();
            tessellator1.func_78381_a();
            GlStateManager.func_179098_w();
        }
        this.posX += f;
    }

    private GlyphPage getCurrentGlyphPage() {
        if (this.boldStyle && this.italicStyle) {
            return this.boldItalicGlyphPage;
        }
        if (this.boldStyle) {
            return this.boldGlyphPage;
        }
        if (this.italicStyle) {
            return this.italicGlyphPage;
        }
        return this.regularGlyphPage;
    }

    private void resetStyles() {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

    public int getFontHeight() {
        return this.regularGlyphPage.getMaxFontHeight() / 2;
    }

    public int getStringWidth(String text) {
        if (text == null) {
            return 0;
        }
        int width = 0;
        int size = text.length();
        boolean on = false;
        for (int i = 0; i < size; ++i) {
            char character = text.charAt(i);
            if (character == '\u00a7') {
                on = true;
                continue;
            }
            if (on && character >= '0' && character <= 'r') {
                int colorIndex = "0123456789abcdefklmnor".indexOf(character);
                if (colorIndex < 16) {
                    this.boldStyle = false;
                    this.italicStyle = false;
                } else if (colorIndex == 17) {
                    this.boldStyle = true;
                } else if (colorIndex == 20) {
                    this.italicStyle = true;
                } else if (colorIndex == 21) {
                    this.boldStyle = false;
                    this.italicStyle = false;
                }
                ++i;
                on = false;
                continue;
            }
            if (on) {
                --i;
            }
            character = text.charAt(i);
            GlyphPage currentPage = this.getCurrentGlyphPage();
            width = (int)((float)width + (currentPage.getWidth(character) - 8.0f));
        }
        return width / 2;
    }

    public String trimStringToWidth(String text, int width) {
        return this.trimStringToWidth(text, width, false);
    }

    public String trimStringToWidth(String text, int maxWidth, boolean reverse) {
        StringBuilder stringbuilder = new StringBuilder();
        boolean on = false;
        int j = reverse ? text.length() - 1 : 0;
        int k = reverse ? -1 : 1;
        int width = 0;
        for (int i = j; i >= 0 && i < text.length() && i < maxWidth; i += k) {
            char character = text.charAt(i);
            if (character == '\u00a7') {
                on = true;
            } else if (on && character >= '0' && character <= 'r') {
                int colorIndex = "0123456789abcdefklmnor".indexOf(character);
                if (colorIndex < 16) {
                    this.boldStyle = false;
                    this.italicStyle = false;
                } else if (colorIndex == 17) {
                    this.boldStyle = true;
                } else if (colorIndex == 20) {
                    this.italicStyle = true;
                } else if (colorIndex == 21) {
                    this.boldStyle = false;
                    this.italicStyle = false;
                }
                ++i;
                on = false;
            } else {
                if (on) {
                    --i;
                }
                character = text.charAt(i);
                GlyphPage currentPage = this.getCurrentGlyphPage();
                width = (int)((float)width + (currentPage.getWidth(character) - 8.0f) / 2.0f);
            }
            if (i > width) break;
            if (reverse) {
                stringbuilder.insert(0, character);
                continue;
            }
            stringbuilder.append(character);
        }
        return stringbuilder.toString();
    }
}

