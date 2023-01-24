/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 */
package net.ccbluex.liquidbounce.utils.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import net.ccbluex.liquidbounce.api.enums.WDefaultVertexFormats;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.render.ITessellator;
import net.ccbluex.liquidbounce.api.minecraft.client.render.IWorldRenderer;
import net.ccbluex.liquidbounce.api.minecraft.renderer.entity.IRenderManager;
import net.ccbluex.liquidbounce.api.minecraft.util.IAxisAlignedBB;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.api.minecraft.util.IScaledResolution;
import net.ccbluex.liquidbounce.api.minecraft.util.ITimer;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ImageUtils;
import net.ccbluex.liquidbounce.utils.MathUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import net.ccbluex.liquidbounce.utils.render.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public final class RenderUtils
extends MinecraftInstance {
    private static final Map<Integer, Boolean> glCapMap = new HashMap<Integer, Boolean>();
    private static final int[] DISPLAY_LISTS_2D = new int[4];
    public static int deltaTime;

    public static int width() {
        return new ScaledResolution(Minecraft.func_71410_x()).func_78326_a();
    }

    public static void drawSmoothRect(double left, double top, double right, double bottom, int color) {
        GlStateManager.func_179117_G();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        RenderUtils.drawRect((float)left, (float)top, (float)right, (float)bottom, color);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderUtils.drawRect((float)(left * 2.0 - 1.0), (float)(top * 2.0), (float)(left * 2.0), (float)(bottom * 2.0 - 1.0), color);
        RenderUtils.drawRect((float)(left * 2.0), (float)(top * 2.0 - 1.0), (float)(right * 2.0), (float)(top * 2.0), color);
        RenderUtils.drawRect((float)(right * 2.0), (float)(top * 2.0), (float)(right * 2.0 + 1.0), (float)(bottom * 2.0 - 1.0), color);
        GL11.glDisable((int)3042);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }

    public static void drawCircle(double x, double y, double radius, float startAngle, float endAngle, int color, float lineWidth) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)lineWidth);
        GL11.glBegin((int)3);
        for (int i = (int)((double)startAngle / 360.0 * 100.0); i <= (int)((double)endAngle / 360.0 * 100.0); ++i) {
            double angle = Math.PI * 2 * (double)i / 100.0 + Math.toRadians(180.0);
            RenderUtils.color(color);
            GL11.glVertex2d((double)(x + Math.sin(angle) * radius), (double)(y + Math.cos(angle) * radius));
        }
        GL11.glEnd();
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GL11.glDisable((int)2848);
        GlStateManager.func_179121_F();
        GlStateManager.func_179117_G();
    }

    public static void drawFastRoundedRect(float x0, float y0, float x1, float y1, float radius, int color) {
        float f11;
        int i;
        boolean Semicircle = true;
        float f = 5.0f;
        float f2 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(color & 0xFF) / 255.0f;
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GlStateManager.func_179147_l();
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179090_x();
        GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
        GL11.glBegin((int)5);
        GL11.glVertex2f((float)(x0 + radius), (float)y0);
        GL11.glVertex2f((float)(x0 + radius), (float)y1);
        GL11.glVertex2f((float)(x1 - radius), (float)y0);
        GL11.glVertex2f((float)(x1 - radius), (float)y1);
        GL11.glEnd();
        GL11.glBegin((int)5);
        GL11.glVertex2f((float)x0, (float)(y0 + radius));
        GL11.glVertex2f((float)(x0 + radius), (float)(y0 + radius));
        GL11.glVertex2f((float)x0, (float)(y1 - radius));
        GL11.glVertex2f((float)(x0 + radius), (float)(y1 - radius));
        GL11.glEnd();
        GL11.glBegin((int)5);
        GL11.glVertex2f((float)x1, (float)(y0 + radius));
        GL11.glVertex2f((float)(x1 - radius), (float)(y0 + radius));
        GL11.glVertex2f((float)x1, (float)(y1 - radius));
        GL11.glVertex2f((float)(x1 - radius), (float)(y1 - radius));
        GL11.glEnd();
        GL11.glBegin((int)6);
        float f6 = x1 - radius;
        float f7 = y0 + radius;
        GL11.glVertex2f((float)f6, (float)f7);
        boolean j = false;
        for (i = 0; i <= 18; ++i) {
            f11 = (float)i * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 + (double)radius * Math.cos(Math.toRadians(f11)))), (float)((float)((double)f7 - (double)radius * Math.sin(Math.toRadians(f11)))));
        }
        GL11.glEnd();
        GL11.glBegin((int)6);
        f6 = x0 + radius;
        f7 = y0 + radius;
        GL11.glVertex2f((float)f6, (float)f7);
        for (i = 0; i <= 18; ++i) {
            f11 = (float)i * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 - (double)radius * Math.cos(Math.toRadians(f11)))), (float)((float)((double)f7 - (double)radius * Math.sin(Math.toRadians(f11)))));
        }
        GL11.glEnd();
        GL11.glBegin((int)6);
        f6 = x0 + radius;
        f7 = y1 - radius;
        GL11.glVertex2f((float)f6, (float)f7);
        for (i = 0; i <= 18; ++i) {
            f11 = (float)i * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 - (double)radius * Math.cos(Math.toRadians(f11)))), (float)((float)((double)f7 + (double)radius * Math.sin(Math.toRadians(f11)))));
        }
        GL11.glEnd();
        GL11.glBegin((int)6);
        f6 = x1 - radius;
        f7 = y1 - radius;
        GL11.glVertex2f((float)f6, (float)f7);
        for (i = 0; i <= 18; ++i) {
            f11 = (float)i * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 + (double)radius * Math.cos(Math.toRadians(f11)))), (float)((float)((double)f7 + (double)radius * Math.sin(Math.toRadians(f11)))));
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle >= 180 ? 360 - angle : angle) * 2;
        return trueColor ? RenderUtils.interpolateColorHue(start, end, (float)angle / 360.0f) : RenderUtils.interpolateColorC(start, end, (float)angle / 360.0f);
    }

    public static void drawImage3(ResourceLocation image2, float x, float y, int width, int height, float r, float g, float b, float al) {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)al);
        GL11.glTranslatef((float)x, (float)y, (float)x);
        mc2.func_110434_K().func_110577_a(image2);
        Gui.func_146110_a((int)0, (int)0, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        GL11.glTranslatef((float)(-x), (float)(-y), (float)(-x));
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static void drawLimitedCircle(float lx, float ly, float x2, float y2, int xx, int yy, float radius, Color color) {
        int sections = 50;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushAttrib((int)8192);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        RenderUtils.glColor(color);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glVertex2f((float)Math.min(x2, Math.max((float)xx + x, lx)), (float)Math.min(y2, Math.max((float)yy + y, ly)));
        }
        GlStateManager.func_179124_c((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }

    public static Color[] getAnalogousColor(Color color) {
        Color[] colors = new Color[2];
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float degree = 0.083333336f;
        float newHueAdded = hsb[0] + degree;
        colors[0] = new Color(Color.HSBtoRGB(newHueAdded, hsb[1], hsb[2]));
        float newHueSubtracted = hsb[0] - degree;
        colors[1] = new Color(Color.HSBtoRGB(newHueSubtracted, hsb[1], hsb[2]));
        return colors;
    }

    public static Color interpolateColorC(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return new Color(RenderUtils.interpolateInt(color1.getRed(), color2.getRed(), amount), RenderUtils.interpolateInt(color1.getGreen(), color2.getGreen(), amount), RenderUtils.interpolateInt(color1.getBlue(), color2.getBlue(), amount), RenderUtils.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static int loadGlTexture(BufferedImage bufferedImage) {
        int textureId = GL11.glGenTextures();
        GL11.glBindTexture((int)3553, (int)textureId);
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        GL11.glTexImage2D((int)3553, (int)0, (int)6408, (int)bufferedImage.getWidth(), (int)bufferedImage.getHeight(), (int)0, (int)6408, (int)5121, (ByteBuffer)ImageUtils.readImageToBuffer(bufferedImage));
        GL11.glBindTexture((int)3553, (int)0);
        return textureId;
    }

    public static Color interpolateColorHue(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        Color resultColor = Color.getHSBColor(MathUtils.interpolateFloat(color1HSB[0], color2HSB[0], amount), MathUtils.interpolateFloat(color1HSB[1], color2HSB[1], amount), MathUtils.interpolateFloat(color1HSB[2], color2HSB[2], amount));
        return new Color(resultColor.getRed(), resultColor.getGreen(), resultColor.getBlue(), RenderUtils.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
        return RenderUtils.interpolate(oldValue, newValue, (float)interpolationValue).intValue();
    }

    public static Double interpolate(double oldValue, double newValue, double interpolationValue) {
        return oldValue + (newValue - oldValue) * interpolationValue;
    }

    public static Color skyRainbow(int var2, float st, float bright) {
        double d;
        double v1 = Math.ceil(System.currentTimeMillis() + (long)(var2 * 109)) / 5.0;
        return Color.getHSBColor((double)((float)(d / 360.0)) < 0.5 ? -((float)(v1 / 360.0)) : (float)((v1 %= 360.0) / 360.0), st, bright);
    }

    public static void drawRoundedCornerRect(float x, float y, float x1, float y1, float radius, int color) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        boolean hasCull = GL11.glIsEnabled((int)2884);
        GL11.glDisable((int)2884);
        RenderUtils.glColor(color);
        RenderUtils.drawRoundedCornerRect(x, y, x1, y1, radius);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        RenderUtils.setGlState(2884, hasCull);
    }

    public static void drawRoundedCornerRect(float x, float y, float x1, float y1, float radius) {
        GL11.glBegin((int)9);
        float xRadius = (float)Math.min((double)(x1 - x) * 0.5, (double)radius);
        float yRadius = (float)Math.min((double)(y1 - y) * 0.5, (double)radius);
        RenderUtils.quickPolygonCircle(x + xRadius, y + yRadius, xRadius, yRadius, 180, 270, 4);
        RenderUtils.quickPolygonCircle(x1 - xRadius, y + yRadius, xRadius, yRadius, 90, 180, 4);
        RenderUtils.quickPolygonCircle(x1 - xRadius, y1 - yRadius, xRadius, yRadius, 0, 90, 4);
        RenderUtils.quickPolygonCircle(x + xRadius, y1 - yRadius, xRadius, yRadius, 270, 360, 4);
        GL11.glEnd();
    }

    public static void drawOutlinedRect(int x, int y, int width, int height, int lineSize, Color lineColor, Color backgroundColor) {
        RenderUtils.drawRect(x, y, width, height, backgroundColor.getRGB());
        RenderUtils.drawRect(x, y, width, y + lineSize, lineColor.getRGB());
        RenderUtils.drawRect(x, height - lineSize, width, height, lineColor.getRGB());
        RenderUtils.drawRect(x, y + lineSize, x + lineSize, height - lineSize, lineColor.getRGB());
        RenderUtils.drawRect(width - lineSize, y + lineSize, width, height - lineSize, lineColor.getRGB());
    }

    public static void newDrawRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder worldrenderer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179131_c((float)f, (float)f1, (float)f2, (float)f3);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b((double)left, (double)bottom, 0.0).func_181675_d();
        worldrenderer.func_181662_b((double)right, (double)bottom, 0.0).func_181675_d();
        worldrenderer.func_181662_b((double)right, (double)top, 0.0).func_181675_d();
        worldrenderer.func_181662_b((double)left, (double)top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void drawExhiRect(float x, float y, float x2, float y2) {
        RenderUtils.drawRect(x - 3.5f, y - 3.5f, x2 + 3.5f, y2 + 3.5f, Color.black.getRGB());
        RenderUtils.drawRect(x - 3.0f, y - 3.0f, x2 + 3.0f, y2 + 3.0f, new Color(50, 50, 50).getRGB());
        RenderUtils.drawRect(x - 2.5f, y - 2.5f, x2 + 2.5f, y2 + 2.5f, new Color(26, 26, 26).getRGB());
        RenderUtils.drawRect(x - 0.5f, y - 0.5f, x2 + 0.5f, y2 + 0.5f, new Color(50, 50, 50).getRGB());
        RenderUtils.drawRect(x, y, x2, y2, new Color(18, 18, 18).getRGB());
    }

    public static void drawScaledCustomSizeModalCircle(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        float f = 1.0f / tileWidth;
        float f1 = 1.0f / tileHeight;
        ITessellator tessellator = classProvider.getTessellatorInstance();
        IWorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(9, classProvider.getVertexFormatEnum(WDefaultVertexFormats.POSITION_TEX));
        float xRadius = (float)width / 2.0f;
        float yRadius = (float)height / 2.0f;
        float uRadius = ((u + (float)uWidth) * f - u * f) / 2.0f;
        float vRadius = ((v + (float)vHeight) * f1 - v * f1) / 2.0f;
        for (int i = 0; i <= 360; i += 10) {
            double xPosOffset = Math.sin((double)i * Math.PI / 180.0);
            double yPosOffset = Math.cos((double)i * Math.PI / 180.0);
            worldrenderer.pos((double)((float)x + xRadius) + xPosOffset * (double)xRadius, (double)((float)y + yRadius) + yPosOffset * (double)yRadius, 0.0).tex((double)(u * f + uRadius) + xPosOffset * (double)uRadius, (double)(v * f1 + vRadius) + yPosOffset * (double)vRadius).endVertex();
        }
        tessellator.draw();
    }

    private static void quickPolygonCircle(float x, float y, float xRadius, float yRadius, int start, int end, int split) {
        for (int i = end; i >= start; i -= split) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * Math.PI / 180.0) * (double)xRadius), (double)((double)y + Math.cos((double)i * Math.PI / 180.0) * (double)yRadius));
        }
    }

    public static void drawRoundedRect2(float left, float top, float right, float bottom, float radius, int points, int color) {
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        if (left < right) {
            float f4 = left + right;
            right = left;
            left = f4 - right;
        }
        if (top < bottom) {
            float f5 = top + bottom;
            bottom = top;
            top = f5 - bottom;
        }
        float[][] corners = new float[][]{{right + radius, top - radius, 270.0f}, {left - radius, top - radius, 360.0f}, {left - radius, bottom + radius, 90.0f}, {right + radius, bottom + radius, 180.0f}};
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179092_a((int)516, (float)0.003921569f);
        GlStateManager.func_179131_c((float)f, (float)f1, (float)f2, (float)f3);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder renderer = tessellator.func_178180_c();
        renderer.func_181668_a(9, DefaultVertexFormats.field_181705_e);
        for (float[] c : corners) {
            for (int i = 0; i <= points; ++i) {
                double anglerad = Math.PI * (double)(c[2] + (float)i * 90.0f / (float)points) / 180.0;
                renderer.func_181662_b((double)c[0] + Math.sin(anglerad) * (double)radius, (double)c[1] + Math.cos(anglerad) * (double)radius, 0.0).func_181675_d();
            }
        }
        tessellator.func_78381_a();
        GlStateManager.func_179084_k();
        GlStateManager.func_179098_w();
    }

    public static void drawShadow(int x, int y, float width, float height) {
        RenderUtils.drawImage(classProvider.createResourceLocation("Relaxed".toLowerCase() + "/shadow/shadow_250_125.png"), x - 25, y - 25, (int)(width + 50.0f), (int)(height + 50.0f));
    }

    public static void drawShadow(float x, float y, float width, float height) {
        RenderUtils.drawTexturedRect(x - 9.0f, y - 9.0f, 9.0f, 9.0f, "paneltopleft");
        RenderUtils.drawTexturedRect(x - 9.0f, y + height, 9.0f, 9.0f, "panelbottomleft");
        RenderUtils.drawTexturedRect(x + width, y + height, 9.0f, 9.0f, "panelbottomright");
        RenderUtils.drawTexturedRect(x + width, y - 9.0f, 9.0f, 9.0f, "paneltopright");
        RenderUtils.drawTexturedRect(x - 9.0f, y, 9.0f, height, "panelleft");
        RenderUtils.drawTexturedRect(x + width, y, 9.0f, height, "panelright");
        RenderUtils.drawTexturedRect(x, y - 9.0f, width, 9.0f, "paneltop");
        RenderUtils.drawTexturedRect(x, y + height, width, 9.0f, "panelbottom");
    }

    public static void drawShadowWithCustomAlpha(float x, float y, float width, float height, float alpha) {
        RenderUtils.drawTexturedRectWithCustomAlpha(x - 9.0f, y - 9.0f, 9.0f, 9.0f, "paneltopleft", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x - 9.0f, y + height, 9.0f, 9.0f, "panelbottomleft", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x + width, y + height, 9.0f, 9.0f, "panelbottomright", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x + width, y - 9.0f, 9.0f, 9.0f, "paneltopright", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x - 9.0f, y, 9.0f, height, "panelleft", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x + width, y, 9.0f, height, "panelright", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x, y - 9.0f, width, 9.0f, "paneltop", alpha);
        RenderUtils.drawTexturedRectWithCustomAlpha(x, y + height, width, 9.0f, "panelbottom", alpha);
    }

    public static double getAnimationState2(double animation, double finalState, double speed) {
        float add = (float)(0.01 * speed);
        animation = animation < finalState ? (animation + (double)add < finalState ? (animation += (double)add) : finalState) : (animation - (double)add > finalState ? (animation -= (double)add) : finalState);
        return animation;
    }

    public static void rectangle(double left, double top, double right, double bottom, int color) {
        double var5;
        if (left < right) {
            var5 = left;
            left = right;
            right = var5;
        }
        if (top < bottom) {
            var5 = top;
            top = bottom;
            bottom = var5;
        }
        float var11 = (float)(color >> 24 & 0xFF) / 255.0f;
        float var6 = (float)(color >> 16 & 0xFF) / 255.0f;
        float var7 = (float)(color >> 8 & 0xFF) / 255.0f;
        float var8 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder worldRenderer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179131_c((float)var6, (float)var7, (float)var8, (float)var11);
        worldRenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldRenderer.func_181662_b(left, bottom, 0.0).func_181675_d();
        worldRenderer.func_181662_b(right, bottom, 0.0).func_181675_d();
        worldRenderer.func_181662_b(right, top, 0.0).func_181675_d();
        worldRenderer.func_181662_b(left, top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void resetColor() {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void bindTexture(int texture) {
        GL11.glBindTexture((int)3553, (int)texture);
    }

    public static Framebuffer createFrameBuffer(Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.field_147621_c != mc.getDisplayWidth() || framebuffer.field_147618_d != mc.getDisplayHeight()) {
            if (framebuffer != null) {
                framebuffer.func_147608_a();
            }
            return new Framebuffer(mc.getDisplayWidth(), mc.getDisplayHeight(), true);
        }
        return framebuffer;
    }

    public static void quickDrawHead(IResourceLocation skin, int x, int y, int width, int height) {
        mc.getTextureManager().bindTexture(skin);
        RenderUtils.drawScaledCustomSizeModalRect(x, y, 8.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
        RenderUtils.drawScaledCustomSizeModalRect(x, y, 40.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
    }

    public static void drawHead(IResourceLocation skin, int x, int y, int width, int height) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture(skin);
        RenderUtils.drawScaledCustomSizeModalRect(x, y, 8.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
        RenderUtils.drawScaledCustomSizeModalRect(x, y, 40.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, IEntityLivingBase entity) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179142_g();
        GlStateManager.func_179137_b((double)posX, (double)posY, (double)50.0);
        GlStateManager.func_179152_a((float)(-scale), (float)scale, (float)scale);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179137_b((double)0.0, (double)0.0, (double)0.0);
        float renderYawOffset = entity.getRenderYawOffset();
        float rotationYaw = entity.getRotationYaw();
        float rotationPitch = entity.getRotationPitch();
        float prevRotationYawHead = entity.getPrevRotationYawHead();
        float rotationYawHead = entity.getRotationYawHead();
        entity.setRenderYawOffset(0.0f);
        entity.setRotationYaw(0.0f);
        entity.setRotationPitch(90.0f);
        entity.setRotationYawHead(entity.getRotationYaw());
        entity.setPrevRotationYawHead(entity.getRotationYaw());
        IRenderManager rendermanager = mc.getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        rendermanager.setRenderShadow(true);
        entity.setRenderYawOffset(renderYawOffset);
        entity.setRotationYaw(rotationYaw);
        entity.setRotationPitch(rotationPitch);
        entity.setPrevRotationYawHead(prevRotationYawHead);
        entity.setRotationYawHead(rotationYawHead);
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
    }

    public static void quickRenderCircle(double x, double y, double start, double end, double w, double h) {
        if (start > end) {
            double temp = end;
            end = start;
            start = temp;
        }
        GL11.glBegin((int)6);
        GL11.glVertex2d((double)x, (double)y);
        for (double i = end; i >= start; i -= 4.0) {
            double ldx = Math.cos(i * Math.PI / 180.0) * w;
            double ldy = Math.sin(i * Math.PI / 180.0) * h;
            GL11.glVertex2d((double)(x + ldx), (double)(y + ldy));
        }
        GL11.glVertex2d((double)x, (double)y);
        GL11.glEnd();
    }

    public static void drawCircleRect(float x, float y, float x1, float y1, float radius, int color) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        RenderUtils.glColor(color);
        RenderUtils.quickRenderCircle(x1 - radius, y1 - radius, 0.0, 90.0, radius, radius);
        RenderUtils.quickRenderCircle(x + radius, y1 - radius, 90.0, 180.0, radius, radius);
        RenderUtils.quickRenderCircle(x + radius, y + radius, 180.0, 270.0, radius, radius);
        RenderUtils.quickRenderCircle(x1 - radius, y + radius, 270.0, 360.0, radius, radius);
        RenderUtils.quickDrawRect(x + radius, y + radius, x1 - radius, y1 - radius);
        RenderUtils.quickDrawRect(x, y + radius, x + radius, y1 - radius);
        RenderUtils.quickDrawRect(x1 - radius, y + radius, x1, y1 - radius);
        RenderUtils.quickDrawRect(x + radius, y, x1 - radius, y + radius);
        RenderUtils.quickDrawRect(x + radius, y1 - radius, x1 - radius, y1);
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor, int borderColor) {
        RenderUtils.rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtils.rectangle(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtils.rectangle(x, y, x + width, y1, borderColor);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtils.rectangle(x1 - width, y, x1, y1, borderColor);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtils.rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void autoExhibition(double x, double y, double x1, double y1, double size) {
        RenderUtils.rectangleBordered(x, y, x1 + size, y1 + size, 0.5, Colors.getColor(90), Colors.getColor(0));
        RenderUtils.rectangleBordered(x + 1.0, y + 1.0, x1 + size - 1.0, y1 + size - 1.0, 1.0, Colors.getColor(90), Colors.getColor(61));
        RenderUtils.rectangleBordered(x + 2.5, y + 2.5, x1 + size - 2.5, y1 + size - 2.5, 0.5, Colors.getColor(61), Colors.getColor(0));
    }

    public static void originalRoundedRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, float radius, int color) {
        double i;
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        float z = 0.0f;
        if (paramXStart > paramXEnd) {
            z = paramXStart;
            paramXStart = paramXEnd;
            paramXEnd = z;
        }
        if (paramYStart > paramYEnd) {
            z = paramYStart;
            paramYStart = paramYEnd;
            paramYEnd = z;
        }
        double x1 = paramXStart + radius;
        double y1 = paramYStart + radius;
        double x2 = paramXEnd - radius;
        double y2 = paramYEnd - radius;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder worldrenderer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
        worldrenderer.func_181668_a(9, DefaultVertexFormats.field_181705_e);
        double degree = Math.PI / 180;
        for (i = 0.0; i <= 90.0; i += 1.0) {
            worldrenderer.func_181662_b(x2 + Math.sin(i * degree) * (double)radius, y2 + Math.cos(i * degree) * (double)radius, 0.0).func_181675_d();
        }
        for (i = 90.0; i <= 180.0; i += 1.0) {
            worldrenderer.func_181662_b(x2 + Math.sin(i * degree) * (double)radius, y1 + Math.cos(i * degree) * (double)radius, 0.0).func_181675_d();
        }
        for (i = 180.0; i <= 270.0; i += 1.0) {
            worldrenderer.func_181662_b(x1 + Math.sin(i * degree) * (double)radius, y1 + Math.cos(i * degree) * (double)radius, 0.0).func_181675_d();
        }
        for (i = 270.0; i <= 360.0; i += 1.0) {
            worldrenderer.func_181662_b(x1 + Math.sin(i * degree) * (double)radius, y2 + Math.cos(i * degree) * (double)radius, 0.0).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void drawRectPotion(float x, float y, float x2, float y2, int color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        RenderUtils.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void fastRoundedRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, float radius) {
        double i;
        float z = 0.0f;
        if (paramXStart > paramXEnd) {
            z = paramXStart;
            paramXStart = paramXEnd;
            paramXEnd = z;
        }
        if (paramYStart > paramYEnd) {
            z = paramYStart;
            paramYStart = paramYEnd;
            paramYEnd = z;
        }
        double x1 = paramXStart + radius;
        double y1 = paramYStart + radius;
        double x2 = paramXEnd - radius;
        double y2 = paramYEnd - radius;
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)1.0f);
        GL11.glBegin((int)9);
        double degree = Math.PI / 180;
        for (i = 0.0; i <= 90.0; i += 1.0) {
            GL11.glVertex2d((double)(x2 + Math.sin(i * degree) * (double)radius), (double)(y2 + Math.cos(i * degree) * (double)radius));
        }
        for (i = 90.0; i <= 180.0; i += 1.0) {
            GL11.glVertex2d((double)(x2 + Math.sin(i * degree) * (double)radius), (double)(y1 + Math.cos(i * degree) * (double)radius));
        }
        for (i = 180.0; i <= 270.0; i += 1.0) {
            GL11.glVertex2d((double)(x1 + Math.sin(i * degree) * (double)radius), (double)(y1 + Math.cos(i * degree) * (double)radius));
        }
        for (i = 270.0; i <= 360.0; i += 1.0) {
            GL11.glVertex2d((double)(x1 + Math.sin(i * degree) * (double)radius), (double)(y2 + Math.cos(i * degree) * (double)radius));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
    }

    public static void drawRoundRect(float x0, float y0, float x1, float y1, float radius, int color) {
        if (x0 == x1 || y0 == y1) {
            return;
        }
        int Semicircle = 18;
        float f = 5.0f;
        float f2 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(color & 0xFF) / 255.0f;
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
        GL11.glBegin((int)5);
        GL11.glVertex2f((float)(x0 + radius), (float)y0);
        GL11.glVertex2f((float)(x0 + radius), (float)y1);
        GL11.glVertex2f((float)(x1 - radius), (float)y0);
        GL11.glVertex2f((float)(x1 - radius), (float)y1);
        GL11.glEnd();
        GL11.glBegin((int)5);
        GL11.glVertex2f((float)x0, (float)(y0 + radius));
        GL11.glVertex2f((float)(x0 + radius), (float)(y0 + radius));
        GL11.glVertex2f((float)x0, (float)(y1 - radius));
        GL11.glVertex2f((float)(x0 + radius), (float)(y1 - radius));
        GL11.glEnd();
        GL11.glBegin((int)5);
        GL11.glVertex2f((float)x1, (float)(y0 + radius));
        GL11.glVertex2f((float)(x1 - radius), (float)(y0 + radius));
        GL11.glVertex2f((float)x1, (float)(y1 - radius));
        GL11.glVertex2f((float)(x1 - radius), (float)(y1 - radius));
        GL11.glEnd();
        GL11.glBegin((int)6);
        float f6 = x1 - radius;
        float f7 = y0 + radius;
        GL11.glVertex2f((float)f6, (float)f7);
        int j = 0;
        for (j = 0; j <= 18; ++j) {
            float f8 = (float)j * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 + (double)radius * Math.cos(Math.toRadians(f8)))), (float)((float)((double)f7 - (double)radius * Math.sin(Math.toRadians(f8)))));
        }
        GL11.glEnd();
        GL11.glBegin((int)6);
        f6 = x0 + radius;
        f7 = y0 + radius;
        GL11.glVertex2f((float)f6, (float)f7);
        for (j = 0; j <= 18; ++j) {
            float f9 = (float)j * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 - (double)radius * Math.cos(Math.toRadians(f9)))), (float)((float)((double)f7 - (double)radius * Math.sin(Math.toRadians(f9)))));
        }
        GL11.glEnd();
        GL11.glBegin((int)6);
        f6 = x0 + radius;
        f7 = y1 - radius;
        GL11.glVertex2f((float)f6, (float)f7);
        for (j = 0; j <= 18; ++j) {
            float f10 = (float)j * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 - (double)radius * Math.cos(Math.toRadians(f10)))), (float)((float)((double)f7 + (double)radius * Math.sin(Math.toRadians(f10)))));
        }
        GL11.glEnd();
        GL11.glBegin((int)6);
        f6 = x1 - radius;
        f7 = y1 - radius;
        GL11.glVertex2f((float)f6, (float)f7);
        for (j = 0; j <= 18; ++j) {
            float f11 = (float)j * 5.0f;
            GL11.glVertex2f((float)((float)((double)f6 + (double)radius * Math.cos(Math.toRadians(f11)))), (float)((float)((double)f7 + (double)radius * Math.sin(Math.toRadians(f11)))));
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void drawRoundRect(float x, float y, float x1, float y1, int color) {
        RenderUtils.drawRoundedRect(x, y, x1, y1, color, color);
        GlStateManager.func_179124_c((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void drawRoundedRect(float x, float y, float x1, float y1, int borderC, int insideC) {
        RenderUtils.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderUtils.drawVLine(x *= 2.0f, (y *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
        RenderUtils.drawVLine((x1 *= 2.0f) - 1.0f, y + 1.0f, y1 - 2.0f, borderC);
        RenderUtils.drawHLine(x + 2.0f, x1 - 3.0f, y, borderC);
        RenderUtils.drawHLine(x + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
        RenderUtils.drawHLine(x + 1.0f, x + 1.0f, y + 1.0f, borderC);
        RenderUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y + 1.0f, borderC);
        RenderUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
        RenderUtils.drawHLine(x + 1.0f, x + 1.0f, y1 - 2.0f, borderC);
        RenderUtils.drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderUtils.disableGL2D();
        Gui.func_73734_a((int)0, (int)0, (int)0, (int)0, (int)0);
    }

    public static void color(int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
    }

    public static double getAnimationState(double n, double n2, double n3) {
        float n4 = (float)((double)deltaTime * n3);
        n = n < n2 ? (n + (double)n4 < n2 ? (n += (double)n4) : n2) : (n - (double)n4 > n2 ? (n -= (double)n4) : n2);
        return n;
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawHLine(float par1, float par2, float par3, int par4) {
        if (par2 < par1) {
            float var5 = par1;
            par1 = par2;
            par2 = var5;
        }
        RenderUtils.drawRect(par1, par3, par2 + 1.0f, par3 + 1.0f, par4);
    }

    public static void drawVLine(float x, float y, float x1, int y1) {
        if (x1 < y) {
            float var5 = y;
            y = x1;
            x1 = var5;
        }
        RenderUtils.drawRect(x, y + 1.0f, x + 1.0f, x1, y1);
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static int height() {
        return new ScaledResolution(Minecraft.func_71410_x()).func_78328_b();
    }

    public static void drawFilledCircle1(int xx, int yy, float radius, int col) {
        float f = (float)(col >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col & 0xFF) / 255.0f;
        int sections = 50;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)6);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
            GL11.glVertex2f((float)((float)xx + x), (float)((float)yy + y));
        }
        GlStateManager.func_179124_c((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static void drawFilledCircle1(float xx, float yy, float radius, int col) {
        float f = (float)(col >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col & 0xFF) / 255.0f;
        int sections = 50;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
            GL11.glVertex2f((float)(xx + x), (float)(yy + y));
        }
        GlStateManager.func_179124_c((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static void drawFilledCircle1(int xx, int yy, float radius, Color color) {
        int sections = 50;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL11.glVertex2f((float)((float)xx + x), (float)((float)yy + y));
        }
        GlStateManager.func_179124_c((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static void drawCustomImage(int x, int y, int width, int height, ResourceLocation image2) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.func_71410_x());
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(image2);
        Gui.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static void drawTexturedRectWithCustomAlpha(float x, float y, float width, float height, String image2, float alpha) {
        boolean disableAlpha;
        GL11.glPushMatrix();
        boolean enableBlend = GL11.glIsEnabled((int)3042);
        boolean bl = disableAlpha = !GL11.glIsEnabled((int)3008);
        if (!enableBlend) {
            GL11.glEnable((int)3042);
        }
        if (!disableAlpha) {
            GL11.glDisable((int)3008);
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        mc.getTextureManager().bindTexture2(new ResourceLocation("relaxed/shadow/" + image2 + ".png"));
        RenderUtils.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        if (!enableBlend) {
            GL11.glDisable((int)3042);
        }
        if (!disableAlpha) {
            GL11.glEnable((int)3008);
        }
        GlStateManager.func_179117_G();
        GL11.glPopMatrix();
    }

    public static void stopDrawing() {
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static void drawTexturedRect(float x, float y, float width, float height, String image2) {
        boolean disableAlpha;
        GL11.glPushMatrix();
        boolean enableBlend = GL11.glIsEnabled((int)3042);
        boolean bl = disableAlpha = !GL11.glIsEnabled((int)3008);
        if (!enableBlend) {
            GL11.glEnable((int)3042);
        }
        if (!disableAlpha) {
            GL11.glDisable((int)3008);
        }
        mc.getTextureManager().bindTexture2(new ResourceLocation("relaxed/shadow/" + image2 + ".png"));
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtils.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        if (!enableBlend) {
            GL11.glDisable((int)3042);
        }
        if (!disableAlpha) {
            GL11.glEnable((int)3008);
        }
        GL11.glPopMatrix();
    }

    public static void drawEntityKillAuraESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        GL11.glLineWidth((float)lineWdith);
        GL11.glColor4f((float)lineRed, (float)lineGreen, (float)lineBlue, (float)lineAlpha);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static int getRainbowOpaque(int seconds, float saturation, float brightness, int index) {
        float hue = (float)((System.currentTimeMillis() + (long)index) % (long)(seconds * 1000)) / (float)(seconds * 1000);
        int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }

    public static int SkyRainbow(int var2, float st, float bright) {
        double d;
        double v1 = Math.ceil(System.currentTimeMillis() + (long)(var2 * 109)) / 5.0;
        return Color.getHSBColor((double)((float)(d / 360.0)) < 0.5 ? -((float)(v1 / 360.0)) : (float)((v1 %= 360.0) / 360.0), st, bright).getRGB();
    }

    public static void drawTexturedRect(int x, int y, int width, int height, String image2, ScaledResolution sr) {
        GL11.glPushMatrix();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        mc.getTextureManager().bindTexture2(new ResourceLocation("relaxed/shadow/" + image2 + ".png"));
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Gui.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GL11.glPopMatrix();
    }

    public static Color getGradientOffset(Color color1, Color color2, double offset) {
        int redPart;
        double inverse_percent;
        if (offset > 1.0) {
            inverse_percent = offset % 1.0;
            redPart = (int)offset;
            offset = redPart % 2 == 0 ? inverse_percent : 1.0 - inverse_percent;
        }
        inverse_percent = 1.0 - offset;
        redPart = (int)((double)color1.getRed() * inverse_percent + (double)color2.getRed() * offset);
        int greenPart = (int)((double)color1.getGreen() * inverse_percent + (double)color2.getGreen() * offset);
        int bluePart = (int)((double)color1.getBlue() * inverse_percent + (double)color2.getBlue() * offset);
        return new Color(redPart, greenPart, bluePart);
    }

    public static int Astolfo(int var2, float bright, float st, int index, int offset, float client2) {
        double d;
        double rainbowDelay = Math.ceil(System.currentTimeMillis() + (long)(var2 * index)) / (double)offset;
        return Color.getHSBColor((double)((float)(d / (double)client2)) < 0.5 ? -((float)(rainbowDelay / (double)client2)) : (float)((rainbowDelay %= (double)client2) / (double)client2), st, bright).getRGB();
    }

    public static int getRainbow(int index, int offset, float bright, float st) {
        float hue = (System.currentTimeMillis() + (long)offset * (long)index) % 2000L;
        return Color.getHSBColor(hue /= 2000.0f, st, bright).getRGB();
    }

    public static void drawGradientSidewaysH(double left, double top, double right, double bottom, int col1, int col2) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        RenderUtils.quickDrawGradientSidewaysH(left, top, right, bottom, col1, col2);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void quickDrawGradientSidewaysH(double left, double top, double right, double bottom, int col1, int col2) {
        GL11.glBegin((int)7);
        RenderUtils.glColor(col1);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glVertex2d((double)left, (double)bottom);
        RenderUtils.glColor(col2);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
    }

    public static Color reAlpha(Color cIn, float alpha) {
        return new Color((float)cIn.getRed() / 255.0f, (float)cIn.getGreen() / 255.0f, (float)cIn.getBlue() / 255.0f, (float)cIn.getAlpha() / 255.0f * alpha);
    }

    public static int reAlpha(int n, float n2) {
        Color color = new Color(n);
        return new Color(0.003921569f * (float)color.getRed(), 0.003921569f * (float)color.getGreen(), 0.003921569f * (float)color.getBlue(), n2).getRGB();
    }

    public static void drawGradientSidewaysV(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
        Gui.func_73734_a((int)0, (int)0, (int)0, (int)0, (int)0);
    }

    public static void quickDrawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawGradientSideways2(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void drawBlockBox(WBlockPos blockPos, Color color, boolean outline) {
        IRenderManager renderManager = mc.getRenderManager();
        ITimer timer = mc.getTimer();
        double x = (double)blockPos.getX() - renderManager.getRenderPosX();
        double y = (double)blockPos.getY() - renderManager.getRenderPosY();
        double z = (double)blockPos.getZ() - renderManager.getRenderPosZ();
        IAxisAlignedBB axisAlignedBB = classProvider.createAxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        IBlock block = BlockUtils.getBlock(blockPos);
        if (block != null) {
            IEntityPlayerSP player = mc.getThePlayer();
            double posX = player.getLastTickPosX() + (player.getPosX() - player.getLastTickPosX()) * (double)timer.getRenderPartialTicks();
            double posY = player.getLastTickPosY() + (player.getPosY() - player.getLastTickPosY()) * (double)timer.getRenderPartialTicks();
            double posZ = player.getLastTickPosZ() + (player.getPosZ() - player.getLastTickPosZ()) * (double)timer.getRenderPartialTicks();
            axisAlignedBB = block.getSelectedBoundingBox(mc.getTheWorld(), mc.getTheWorld().getBlockState(blockPos), blockPos).expand(0.002f, 0.002f, 0.002f).offset(-posX, -posY, -posZ);
        }
        GL11.glBlendFunc((int)770, (int)771);
        RenderUtils.enableGlCap(3042);
        RenderUtils.disableGlCap(3553, 2929);
        GL11.glDepthMask((boolean)false);
        RenderUtils.glColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() != 255 ? color.getAlpha() : (outline ? 26 : 35));
        RenderUtils.drawFilledBox(axisAlignedBB);
        if (outline) {
            GL11.glLineWidth((float)1.0f);
            RenderUtils.enableGlCap(2848);
            RenderUtils.glColor(color);
            RenderUtils.drawSelectionBoundingBox(axisAlignedBB);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDepthMask((boolean)true);
        RenderUtils.resetCaps();
    }

    public static void drawImage2(ResourceLocation image2, int x, int y, int width, int height) {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture2(image2);
        Gui.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static void glColor1(int color) {
    }

    public static void enableSmoothLine(float width) {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GL11.glLineWidth((float)width);
    }

    public static void disableSmoothLine() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glDepthMask((boolean)true);
        GL11.glCullFace((int)1029);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawSelectionBoundingBox(IAxisAlignedBB boundingBox) {
        ITessellator tessellator = classProvider.getTessellatorInstance();
        IWorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(3, classProvider.getVertexFormatEnum(WDefaultVertexFormats.POSITION));
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMinY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMinY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMaxY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMaxY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMaxY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMaxY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMinY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMaxZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMinZ()).endVertex();
        worldrenderer.pos(boundingBox.getMaxX(), boundingBox.getMinY(), boundingBox.getMinZ()).endVertex();
        tessellator.draw();
    }

    public static void drawEntityBox(IEntity entity, Color color, boolean outline) {
        IRenderManager renderManager = mc.getRenderManager();
        ITimer timer = mc.getTimer();
        GL11.glBlendFunc((int)770, (int)771);
        RenderUtils.enableGlCap(3042);
        RenderUtils.disableGlCap(3553, 2929);
        GL11.glDepthMask((boolean)false);
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosZ();
        IAxisAlignedBB entityBox = entity.getEntityBoundingBox();
        IAxisAlignedBB axisAlignedBB = classProvider.createAxisAlignedBB(entityBox.getMinX() - entity.getPosX() + x - 0.05, entityBox.getMinY() - entity.getPosY() + y, entityBox.getMinZ() - entity.getPosZ() + z - 0.05, entityBox.getMaxX() - entity.getPosX() + x + 0.05, entityBox.getMaxY() - entity.getPosY() + y + 0.15, entityBox.getMaxZ() - entity.getPosZ() + z + 0.05);
        if (outline) {
            GL11.glLineWidth((float)1.0f);
            RenderUtils.enableGlCap(2848);
            RenderUtils.glColor(color.getRed(), color.getGreen(), color.getBlue(), 95);
            RenderUtils.drawSelectionBoundingBox(axisAlignedBB);
        }
        RenderUtils.glColor(color.getRed(), color.getGreen(), color.getBlue(), outline ? 26 : 35);
        RenderUtils.drawFilledBox(axisAlignedBB);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDepthMask((boolean)true);
        RenderUtils.resetCaps();
    }

    public static void drawAxisAlignedBB(IAxisAlignedBB axisAlignedBB, Color color) {
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        RenderUtils.glColor(color);
        RenderUtils.drawFilledBox(axisAlignedBB);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
    }

    public static void drawPlatform(double y, Color color, double size) {
        IRenderManager renderManager = mc.getRenderManager();
        double renderY = y - renderManager.getRenderPosY();
        RenderUtils.drawAxisAlignedBB(classProvider.createAxisAlignedBB(size, renderY + 0.02, size, -size, renderY, -size), color);
    }

    public static void drawPlatform(IEntity entity, Color color) {
        IRenderManager renderManager = mc.getRenderManager();
        ITimer timer = mc.getTimer();
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosZ();
        IAxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox().offset(-entity.getPosX(), -entity.getPosY(), -entity.getPosZ()).offset(x, y, z);
        RenderUtils.drawAxisAlignedBB(classProvider.createAxisAlignedBB(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY() + 0.2, axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY() + 0.26, axisAlignedBB.getMaxZ()), color);
    }

    public static void drawFilledCircle2(IEntity entity, Color color) {
        int sections = 50;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushAttrib((int)8192);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < sections; ++i) {
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }

    public static void drawFilledBox(IAxisAlignedBB axisAlignedBB) {
        ITessellator tessellator = classProvider.getTessellatorInstance();
        IWorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, classProvider.getVertexFormatEnum(WDefaultVertexFormats.POSITION));
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMinX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMinZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY(), axisAlignedBB.getMaxZ()).endVertex();
        worldRenderer.pos(axisAlignedBB.getMaxX(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxZ()).endVertex();
        tessellator.draw();
    }

    public static void quickDrawRect(float x, float y, float x2, float y2) {
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
    }

    public static void drawRect(float x, float y, float x2, float y2, int color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        RenderUtils.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2f((float)x2, (float)y);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glVertex2f((float)x, (float)y2);
        GL11.glVertex2f((float)x2, (float)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void drawRect(int x, int y, int x2, int y2, int color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        RenderUtils.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2i((int)x2, (int)y);
        GL11.glVertex2i((int)x, (int)y);
        GL11.glVertex2i((int)x, (int)y2);
        GL11.glVertex2i((int)x2, (int)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void quickDrawRect(float x, float y, float x2, float y2, int color) {
        RenderUtils.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
    }

    public static void drawRect(float x, float y, float x2, float y2, Color color) {
        RenderUtils.drawRect(x, y, x2, y2, color.getRGB());
    }

    public static void drawBorderedRect(float x, float y, float x2, float y2, float width, int color1, int color2) {
        RenderUtils.drawRect(x, y, x2, y2, color2);
        RenderUtils.drawBorder(x, y, x2, y2, width, color1);
    }

    public static void drawBorder(float x, float y, float x2, float y2, float width, int color1) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        RenderUtils.glColor(color1);
        GL11.glLineWidth((float)width);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void quickDrawBorderedRect(float x, float y, float x2, float y2, float width, int color1, int color2) {
        RenderUtils.quickDrawRect(x, y, x2, y2, color2);
        RenderUtils.glColor(color1);
        GL11.glLineWidth((float)width);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
    }

    public static void drawLoadingCircle(float x, float y) {
        for (int i = 0; i < 4; ++i) {
            int rot = (int)(System.nanoTime() / 5000000L * (long)i % 360L);
            RenderUtils.drawCircle(x, y, i * 10, rot - 180, rot);
        }
    }

    public static void drawCircle(float x, float y, float radius, int start, int end) {
        classProvider.getGlStateManager().enableBlend();
        classProvider.getGlStateManager().disableTexture2D();
        classProvider.getGlStateManager().tryBlendFuncSeparate(770, 771, 1, 0);
        RenderUtils.glColor(Color.WHITE);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glBegin((int)3);
        for (float i = (float)end; i >= (float)start; i -= 4.0f) {
            GL11.glVertex2f((float)((float)((double)x + Math.cos((double)i * Math.PI / 180.0) * (double)(radius * 1.001f))), (float)((float)((double)y + Math.sin((double)i * Math.PI / 180.0) * (double)(radius * 1.001f))));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        classProvider.getGlStateManager().enableTexture2D();
        classProvider.getGlStateManager().disableBlend();
    }

    public static void drawFilledCircle(int xx, int yy, float radius, Color color) {
        int sections = 50;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushAttrib((int)8192);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL11.glVertex2f((float)((float)xx + x), (float)((float)yy + y));
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }

    public static void drawImage(IResourceLocation image2, int x, int y, int width, int height) {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        GL14.glBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture(image2);
        RenderUtils.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static void drawImage4(ResourceLocation image2, int x, int y, int width, int height) {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        GL14.glBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.getTextureManager().bindTexture2(image2);
        RenderUtils.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        ITessellator tessellator = classProvider.getTessellatorInstance();
        IWorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, classProvider.getVertexFormatEnum(WDefaultVertexFormats.POSITION_TEX));
        worldrenderer.pos(x, y + height, 0.0).tex(u * f, (v + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + width) * f, (v + height) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + width) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    public static void glColor(int red, int green, int blue, int alpha) {
        GL11.glColor4f((float)((float)red / 255.0f), (float)((float)green / 255.0f), (float)((float)blue / 255.0f), (float)((float)alpha / 255.0f));
    }

    public static void glColor(Color color) {
        RenderUtils.glColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    private static void glColor(int hex) {
        RenderUtils.glColor(hex >> 16 & 0xFF, hex >> 8 & 0xFF, hex & 0xFF, hex >> 24 & 0xFF);
    }

    public static void glColor(Color color, int alpha) {
        RenderUtils.glColor(color, (int)((float)alpha / 255.0f));
    }

    public static void glColo2(Color color, float alpha) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void glColor2(int hex, int alpha) {
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)((float)alpha / 255.0f));
    }

    public static void glColor2(int hex, float alpha) {
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void draw2D(IEntityLivingBase entity, double posX, double posY, double posZ, int color, int backgroundColor) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, (double)posZ);
        GL11.glRotated((double)(-mc.getRenderManager().getPlayerViewY()), (double)0.0, (double)1.0, (double)0.0);
        GL11.glScaled((double)-0.1, (double)-0.1, (double)0.1);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        RenderUtils.glColor(color);
        GL11.glCallList((int)DISPLAY_LISTS_2D[0]);
        RenderUtils.glColor(backgroundColor);
        GL11.glCallList((int)DISPLAY_LISTS_2D[1]);
        GL11.glTranslated((double)0.0, (double)(21.0 + -(entity.getEntityBoundingBox().getMaxY() - entity.getEntityBoundingBox().getMinY()) * 12.0), (double)0.0);
        RenderUtils.glColor(color);
        GL11.glCallList((int)DISPLAY_LISTS_2D[2]);
        RenderUtils.glColor(backgroundColor);
        GL11.glCallList((int)DISPLAY_LISTS_2D[3]);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void glColor3(int red, int green, int blue, int alpha) {
        GlStateManager.func_179131_c((float)((float)red / 255.0f), (float)((float)green / 255.0f), (float)((float)blue / 255.0f), (float)((float)alpha / 255.0f));
    }

    public static void glColo3(Color color) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void glColor3(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void startDrawing() {
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        Minecraft.func_71410_x().field_71460_t.func_78479_a(Minecraft.func_71410_x().field_71428_T.field_194147_b, 0);
    }

    public static void draw2D(WBlockPos blockPos, int color, int backgroundColor) {
        IRenderManager renderManager = mc.getRenderManager();
        double posX = (double)blockPos.getX() + 0.5 - renderManager.getRenderPosX();
        double posY = (double)blockPos.getY() - renderManager.getRenderPosY();
        double posZ = (double)blockPos.getZ() + 0.5 - renderManager.getRenderPosZ();
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, (double)posZ);
        GL11.glRotated((double)(-mc.getRenderManager().getPlayerViewY()), (double)0.0, (double)1.0, (double)0.0);
        GL11.glScaled((double)-0.1, (double)-0.1, (double)0.1);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        RenderUtils.glColor(color);
        GL11.glCallList((int)DISPLAY_LISTS_2D[0]);
        RenderUtils.glColor(backgroundColor);
        GL11.glCallList((int)DISPLAY_LISTS_2D[1]);
        GL11.glTranslated((double)0.0, (double)9.0, (double)0.0);
        RenderUtils.glColor(color);
        GL11.glCallList((int)DISPLAY_LISTS_2D[2]);
        RenderUtils.glColor(backgroundColor);
        GL11.glCallList((int)DISPLAY_LISTS_2D[3]);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void renderNameTag(String string, double x, double y, double z) {
        IRenderManager renderManager = mc.getRenderManager();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x - renderManager.getRenderPosX()), (double)(y - renderManager.getRenderPosY()), (double)(z - renderManager.getRenderPosZ()));
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-mc.getRenderManager().getPlayerViewY()), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)mc.getRenderManager().getPlayerViewX(), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)-0.05f, (float)-0.05f, (float)0.05f);
        RenderUtils.setGlCap(2896, false);
        RenderUtils.setGlCap(2929, false);
        RenderUtils.setGlCap(3042, true);
        GL11.glBlendFunc((int)770, (int)771);
        int width = Fonts.font35.getStringWidth(string) / 2;
        RenderUtils.drawRect(-width - 1, -1, width + 1, Fonts.font35.getFontHeight(), Integer.MIN_VALUE);
        Fonts.font35.drawString(string, -width, 1.5f, Color.WHITE.getRGB(), true);
        RenderUtils.resetCaps();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public static void drawLine(double x, double y, double x1, double y1, float width) {
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)width);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x1, (double)y1);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    public static void makeScissorBox(float x, float y, float x2, float y2) {
        IScaledResolution scaledResolution = classProvider.createScaledResolution(mc);
        int factor = scaledResolution.getScaleFactor();
        GL11.glScissor((int)((int)(x * (float)factor)), (int)((int)(((float)scaledResolution.getScaledHeight() - y2) * (float)factor)), (int)((int)((x2 - x) * (float)factor)), (int)((int)((y2 - y) * (float)factor)));
    }

    public static void resetCaps() {
        glCapMap.forEach(RenderUtils::setGlState);
    }

    public static void enableGlCap(int cap) {
        RenderUtils.setGlCap(cap, true);
    }

    public static void enableGlCap(int ... caps) {
        for (int cap : caps) {
            RenderUtils.setGlCap(cap, true);
        }
    }

    public static void disableGlCap(int cap) {
        RenderUtils.setGlCap(cap, true);
    }

    public static void disableGlCap(int ... caps) {
        for (int cap : caps) {
            RenderUtils.setGlCap(cap, false);
        }
    }

    public static void setGlCap(int cap, boolean state) {
        glCapMap.put(cap, GL11.glGetBoolean((int)cap));
        RenderUtils.setGlState(cap, state);
    }

    public static void setGlState(int cap, boolean state) {
        if (state) {
            GL11.glEnable((int)cap);
        } else {
            GL11.glDisable((int)cap);
        }
    }

    public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        float f = 1.0f / tileWidth;
        float f1 = 1.0f / tileHeight;
        ITessellator tessellator = classProvider.getTessellatorInstance();
        IWorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, classProvider.getVertexFormatEnum(WDefaultVertexFormats.POSITION_TEX));
        worldrenderer.pos(x, y + height, 0.0).tex(u * f, (v + (float)vHeight) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + (float)uWidth) * f, (v + (float)vHeight) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + (float)uWidth) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    static {
        for (int i = 0; i < DISPLAY_LISTS_2D.length; ++i) {
            RenderUtils.DISPLAY_LISTS_2D[i] = GL11.glGenLists((int)1);
        }
        GL11.glNewList((int)DISPLAY_LISTS_2D[0], (int)4864);
        RenderUtils.quickDrawRect(-7.0f, 2.0f, -4.0f, 3.0f);
        RenderUtils.quickDrawRect(4.0f, 2.0f, 7.0f, 3.0f);
        RenderUtils.quickDrawRect(-7.0f, 0.5f, -6.0f, 3.0f);
        RenderUtils.quickDrawRect(6.0f, 0.5f, 7.0f, 3.0f);
        GL11.glEndList();
        GL11.glNewList((int)DISPLAY_LISTS_2D[1], (int)4864);
        RenderUtils.quickDrawRect(-7.0f, 3.0f, -4.0f, 3.3f);
        RenderUtils.quickDrawRect(4.0f, 3.0f, 7.0f, 3.3f);
        RenderUtils.quickDrawRect(-7.3f, 0.5f, -7.0f, 3.3f);
        RenderUtils.quickDrawRect(7.0f, 0.5f, 7.3f, 3.3f);
        GL11.glEndList();
        GL11.glNewList((int)DISPLAY_LISTS_2D[2], (int)4864);
        RenderUtils.quickDrawRect(4.0f, -20.0f, 7.0f, -19.0f);
        RenderUtils.quickDrawRect(-7.0f, -20.0f, -4.0f, -19.0f);
        RenderUtils.quickDrawRect(6.0f, -20.0f, 7.0f, -17.5f);
        RenderUtils.quickDrawRect(-7.0f, -20.0f, -6.0f, -17.5f);
        GL11.glEndList();
        GL11.glNewList((int)DISPLAY_LISTS_2D[3], (int)4864);
        RenderUtils.quickDrawRect(7.0f, -20.0f, 7.3f, -17.5f);
        RenderUtils.quickDrawRect(-7.3f, -20.0f, -7.0f, -17.5f);
        RenderUtils.quickDrawRect(4.0f, -20.3f, 7.3f, -20.0f);
        RenderUtils.quickDrawRect(-7.3f, -20.3f, -4.0f, -20.0f);
        GL11.glEndList();
    }
}

