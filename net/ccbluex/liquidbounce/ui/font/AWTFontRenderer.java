/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.ui.font.CachedFont;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0007\u0018\u0000 42\u00020\u0001:\u000234B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010 \u001a\u00020!H\u0002J\u0006\u0010\"\u001a\u00020!J \u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J&\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020\f2\u0006\u0010%\u001a\u00020.2\u0006\u0010'\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0005J\u0006\u00100\u001a\u00020!J\u000e\u00101\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\fJ\u0018\u00102\u001a\u00020!2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2={"Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "font", "Ljava/awt/Font;", "startChar", "", "stopChar", "loadingScreen", "", "(Ljava/awt/Font;IIZ)V", "cachedStrings", "Ljava/util/HashMap;", "", "Lnet/ccbluex/liquidbounce/ui/font/CachedFont;", "Lkotlin/collections/HashMap;", "charLocations", "", "Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer$CharLocation;", "[Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer$CharLocation;", "getFont", "()Ljava/awt/Font;", "fontHeight", "height", "getHeight", "()I", "getLoadingScreen", "()Z", "setLoadingScreen", "(Z)V", "textureHeight", "textureID", "textureWidth", "collectGarbage", "", "delete", "drawChar", "char", "x", "", "y", "drawCharToImage", "Ljava/awt/image/BufferedImage;", "ch", "", "drawString", "text", "", "color", "finalize", "getStringWidth", "renderBitmap", "CharLocation", "Companion", "Relaxed"})
public final class AWTFontRenderer
extends MinecraftInstance {
    private int fontHeight;
    private final CharLocation[] charLocations;
    private final HashMap<String, CachedFont> cachedStrings;
    private int textureID;
    private int textureWidth;
    private int textureHeight;
    @NotNull
    private final Font font;
    private boolean loadingScreen;
    private static boolean assumeNonVolatile;
    @NotNull
    private static final ArrayList<AWTFontRenderer> activeFontRenderers;
    private static int gcTicks;
    private static final int GC_TICKS = 600;
    private static final int CACHED_FONT_REMOVAL_TIME = 30000;
    public static final Companion Companion;

    /*
     * WARNING - void declaration
     */
    private final void collectGarbage() {
        void $this$filterTo$iv$iv;
        long currentTime = System.currentTimeMillis();
        Map $this$filter$iv = this.cachedStrings;
        boolean $i$f$filter = false;
        Map map = $this$filter$iv;
        Map destination$iv$iv = new LinkedHashMap();
        boolean $i$f$filterTo = false;
        void var8_9 = $this$filterTo$iv$iv;
        boolean bl = false;
        Iterator iterator2 = var8_9.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry element$iv$iv;
            Map.Entry it = element$iv$iv = iterator2.next();
            boolean bl2 = false;
            if (!(currentTime - ((CachedFont)it.getValue()).getLastUsage() > (long)30000)) continue;
            destination$iv$iv.put(element$iv$iv.getKey(), element$iv$iv.getValue());
        }
        Map $this$forEach$iv = destination$iv$iv;
        boolean $i$f$forEach = false;
        map = $this$forEach$iv;
        boolean bl3 = false;
        Iterator iterator3 = map.entrySet().iterator();
        while (iterator3.hasNext()) {
            Map.Entry element$iv;
            Map.Entry it = element$iv = iterator3.next();
            boolean bl4 = false;
            GL11.glDeleteLists((int)((CachedFont)it.getValue()).getDisplayList(), (int)1);
            ((CachedFont)it.getValue()).setDeleted(true);
            this.cachedStrings.remove(it.getKey());
        }
    }

    public final int getHeight() {
        return (this.fontHeight - 8) / 2;
    }

    public final void drawString(@NotNull String text, double x, double y, int color) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        double scale = 0.25;
        double reverse = 1.0 / scale;
        GL11.glPushMatrix();
        GL11.glScaled((double)scale, (double)scale, (double)scale);
        GL11.glTranslated((double)(x * (double)2.0f), (double)(y * 2.0 - 2.0), (double)0.0);
        if (this.loadingScreen) {
            GL11.glBindTexture((int)3553, (int)this.textureID);
        } else {
            MinecraftInstance.classProvider.getGlStateManager().bindTexture(this.textureID);
        }
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        double currX = 0.0;
        CachedFont cached = this.cachedStrings.get(text);
        if (cached != null) {
            GL11.glCallList((int)cached.getDisplayList());
            cached.setLastUsage(System.currentTimeMillis());
            GL11.glPopMatrix();
            return;
        }
        int list = -1;
        if (assumeNonVolatile) {
            list = GL11.glGenLists((int)1);
            GL11.glNewList((int)list, (int)4865);
        }
        GL11.glBegin((int)7);
        String string = text;
        boolean bl = false;
        char[] cArray = string.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(cArray, "(this as java.lang.String).toCharArray()");
        for (char c : cArray) {
            CharLocation fontChar;
            if (c >= this.charLocations.length) {
                GL11.glEnd();
                GL11.glScaled((double)reverse, (double)reverse, (double)reverse);
                MinecraftInstance.mc.getFontRendererObj().drawString(String.valueOf(c), (float)currX * (float)scale + 1.0f, 2.0f, color, false);
                currX += (double)MinecraftInstance.mc.getFontRendererObj().getStringWidth(String.valueOf(c)) * reverse;
                GL11.glScaled((double)scale, (double)scale, (double)scale);
                if (this.loadingScreen) {
                    GL11.glBindTexture((int)3553, (int)this.textureID);
                } else {
                    MinecraftInstance.classProvider.getGlStateManager().bindTexture(this.textureID);
                }
                GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
                GL11.glBegin((int)7);
                continue;
            }
            if (this.charLocations[c] == null) {
                continue;
            }
            this.drawChar(fontChar, (float)currX, 0.0f);
            currX += (double)fontChar.getWidth() - 8.0;
        }
        GL11.glEnd();
        if (assumeNonVolatile) {
            ((Map)this.cachedStrings).put(text, new CachedFont(list, System.currentTimeMillis(), false, 4, null));
            GL11.glEndList();
        }
        GL11.glPopMatrix();
    }

    private final void drawChar(CharLocation charLocation, float x, float y) {
        float width = charLocation.getWidth();
        float height = charLocation.getHeight();
        float srcX = charLocation.getX();
        float srcY = charLocation.getY();
        float renderX = srcX / (float)this.textureWidth;
        float renderY = srcY / (float)this.textureHeight;
        float renderWidth = width / (float)this.textureWidth;
        float renderHeight = height / (float)this.textureHeight;
        GL11.glTexCoord2f((float)renderX, (float)renderY);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glTexCoord2f((float)renderX, (float)(renderY + renderHeight));
        GL11.glVertex2f((float)x, (float)(y + height));
        GL11.glTexCoord2f((float)(renderX + renderWidth), (float)(renderY + renderHeight));
        GL11.glVertex2f((float)(x + width), (float)(y + height));
        GL11.glTexCoord2f((float)(renderX + renderWidth), (float)renderY);
        GL11.glVertex2f((float)(x + width), (float)y);
    }

    /*
     * WARNING - void declaration
     */
    private final void renderBitmap(int startChar, int stopChar) {
        BufferedImage[] fontImages = new BufferedImage[stopChar];
        int rowHeight = 0;
        int charX = 0;
        int charY = 0;
        int n = startChar;
        int n2 = stopChar;
        while (n < n2) {
            void targetChar;
            BufferedImage fontImage = this.drawCharToImage((char)targetChar);
            CharLocation fontChar = new CharLocation(charX, charY, fontImage.getWidth(), fontImage.getHeight());
            if (fontChar.getHeight() > this.fontHeight) {
                this.fontHeight = fontChar.getHeight();
            }
            if (fontChar.getHeight() > rowHeight) {
                rowHeight = fontChar.getHeight();
            }
            this.charLocations[targetChar] = fontChar;
            fontImages[targetChar] = fontImage;
            if ((charX += fontChar.getWidth()) > 2048) {
                if (charX > this.textureWidth) {
                    this.textureWidth = charX;
                }
                charX = 0;
                charY += rowHeight;
                rowHeight = 0;
            }
            ++targetChar;
        }
        this.textureHeight = charY + rowHeight;
        BufferedImage bufferedImage = new BufferedImage(this.textureWidth, this.textureHeight, 2);
        Graphics graphics = bufferedImage.getGraphics();
        if (graphics == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.awt.Graphics2D");
        }
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setFont(this.font);
        graphics2D.setColor(new Color(255, 255, 255, 0));
        graphics2D.fillRect(0, 0, this.textureWidth, this.textureHeight);
        graphics2D.setColor(Color.white);
        int fontImage = startChar;
        int n3 = stopChar;
        while (fontImage < n3) {
            void targetChar;
            if (fontImages[targetChar] != null && this.charLocations[targetChar] != null) {
                Image image2 = fontImages[targetChar];
                CharLocation charLocation = this.charLocations[targetChar];
                if (charLocation == null) {
                    Intrinsics.throwNpe();
                }
                int n4 = charLocation.getX();
                CharLocation charLocation2 = this.charLocations[targetChar];
                if (charLocation2 == null) {
                    Intrinsics.throwNpe();
                }
                graphics2D.drawImage(image2, n4, charLocation2.getY(), null);
            }
            ++targetChar;
        }
        this.textureID = TextureUtil.func_110989_a((int)TextureUtil.func_110996_a(), (BufferedImage)bufferedImage, (boolean)true, (boolean)true);
    }

    private final BufferedImage drawCharToImage(char ch) {
        Graphics graphics = new BufferedImage(1, 1, 2).getGraphics();
        if (graphics == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.awt.Graphics2D");
        }
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setFont(this.font);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int charWidth = fontMetrics.charWidth(ch) + 8;
        if (charWidth <= 0) {
            charWidth = 7;
        }
        FontMetrics fontMetrics2 = fontMetrics;
        Intrinsics.checkExpressionValueIsNotNull(fontMetrics2, "fontMetrics");
        int charHeight = fontMetrics2.getHeight() + 3;
        if (charHeight <= 0) {
            charHeight = this.font.getSize();
        }
        BufferedImage fontImage = new BufferedImage(charWidth, charHeight, 2);
        Graphics graphics2 = fontImage.getGraphics();
        if (graphics2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.awt.Graphics2D");
        }
        Graphics2D graphics3 = (Graphics2D)graphics2;
        graphics3.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics3.setFont(this.font);
        graphics3.setColor(Color.WHITE);
        graphics3.drawString(String.valueOf(ch), 3, 1 + fontMetrics.getAscent());
        return fontImage;
    }

    public final int getStringWidth(@NotNull String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        int width = 0;
        String string = text;
        boolean bl = false;
        char[] cArray = string.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(cArray, "(this as java.lang.String).toCharArray()");
        for (int n : cArray) {
            CharLocation fontChar;
            if (this.charLocations[n < this.charLocations.length ? n : 3] == null) {
                continue;
            }
            width += fontChar.getWidth() - 8;
        }
        return width / 2;
    }

    public final void delete() {
        if (this.textureID != -1) {
            GL11.glDeleteTextures((int)this.textureID);
            this.textureID = -1;
        }
        activeFontRenderers.remove(this);
    }

    public final void finalize() {
        this.delete();
    }

    @NotNull
    public final Font getFont() {
        return this.font;
    }

    public final boolean getLoadingScreen() {
        return this.loadingScreen;
    }

    public final void setLoadingScreen(boolean bl) {
        this.loadingScreen = bl;
    }

    public AWTFontRenderer(@NotNull Font font, int startChar, int stopChar, boolean loadingScreen) {
        Intrinsics.checkParameterIsNotNull(font, "font");
        this.font = font;
        this.loadingScreen = loadingScreen;
        this.fontHeight = -1;
        this.charLocations = new CharLocation[stopChar];
        this.cachedStrings = new HashMap();
        this.textureID = -1;
        this.renderBitmap(startChar, stopChar);
        activeFontRenderers.add(this);
    }

    public /* synthetic */ AWTFontRenderer(Font font, int n, int n2, boolean bl, int n3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n3 & 2) != 0) {
            n = 0;
        }
        if ((n3 & 4) != 0) {
            n2 = 255;
        }
        if ((n3 & 8) != 0) {
            bl = false;
        }
        this(font, n, n2, bl);
    }

    static {
        Companion = new Companion(null);
        activeFontRenderers = new ArrayList();
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b\u00a8\u0006\u001d"}, d2={"Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer$CharLocation;", "", "x", "", "y", "width", "height", "(IIII)V", "getHeight", "()I", "setHeight", "(I)V", "getWidth", "setWidth", "getX", "setX", "getY", "setY", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "Relaxed"})
    private static final class CharLocation {
        private int x;
        private int y;
        private int width;
        private int height;

        public final int getX() {
            return this.x;
        }

        public final void setX(int n) {
            this.x = n;
        }

        public final int getY() {
            return this.y;
        }

        public final void setY(int n) {
            this.y = n;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setWidth(int n) {
            this.width = n;
        }

        public final int getHeight() {
            return this.height;
        }

        public final void setHeight(int n) {
            this.height = n;
        }

        public CharLocation(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.width;
        }

        public final int component4() {
            return this.height;
        }

        @NotNull
        public final CharLocation copy(int x, int y, int width, int height) {
            return new CharLocation(x, y, width, height);
        }

        public static /* synthetic */ CharLocation copy$default(CharLocation charLocation, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                n = charLocation.x;
            }
            if ((n5 & 2) != 0) {
                n2 = charLocation.y;
            }
            if ((n5 & 4) != 0) {
                n3 = charLocation.width;
            }
            if ((n5 & 8) != 0) {
                n4 = charLocation.height;
            }
            return charLocation.copy(n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "CharLocation(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ")";
        }

        public int hashCode() {
            return ((Integer.hashCode(this.x) * 31 + Integer.hashCode(this.y)) * 31 + Integer.hashCode(this.width)) * 31 + Integer.hashCode(this.height);
        }

        public boolean equals(@Nullable Object object) {
            block3: {
                block2: {
                    if (this == object) break block2;
                    if (!(object instanceof CharLocation)) break block3;
                    CharLocation charLocation = (CharLocation)object;
                    if (this.x != charLocation.x || this.y != charLocation.y || this.width != charLocation.width || this.height != charLocation.height) break block3;
                }
                return true;
            }
            return false;
        }
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer$Companion;", "", "()V", "CACHED_FONT_REMOVAL_TIME", "", "GC_TICKS", "activeFontRenderers", "Ljava/util/ArrayList;", "Lnet/ccbluex/liquidbounce/ui/font/AWTFontRenderer;", "Lkotlin/collections/ArrayList;", "getActiveFontRenderers", "()Ljava/util/ArrayList;", "assumeNonVolatile", "", "getAssumeNonVolatile", "()Z", "setAssumeNonVolatile", "(Z)V", "gcTicks", "garbageCollectionTick", "", "Relaxed"})
    public static final class Companion {
        public final boolean getAssumeNonVolatile() {
            return assumeNonVolatile;
        }

        public final void setAssumeNonVolatile(boolean bl) {
            assumeNonVolatile = bl;
        }

        @NotNull
        public final ArrayList<AWTFontRenderer> getActiveFontRenderers() {
            return activeFontRenderers;
        }

        public final void garbageCollectionTick() {
            int n = gcTicks;
            gcTicks = n + 1;
            if (n > 600) {
                Iterable $this$forEach$iv = this.getActiveFontRenderers();
                boolean $i$f$forEach = false;
                for (Object element$iv : $this$forEach$iv) {
                    AWTFontRenderer it = (AWTFontRenderer)element$iv;
                    boolean bl = false;
                    it.collectGarbage();
                }
                gcTicks = 0;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

