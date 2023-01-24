/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.vector.Vector2f
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.utils.render.VisualUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.color.Gident;
import net.ccbluex.liquidbounce.features.module.modules.render.ESP;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

@ElementInfo(name="NewRadar")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\n\u0010(\u001a\u0004\u0018\u00010)H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Radar;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "(DD)V", "alphaValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "backgroundAlphaValue", "backgroundBlueValue", "backgroundGreenValue", "backgroundRedValue", "blueValue", "blur", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "borderAlphaValue", "borderBlueValue", "borderGreenValue", "borderRedValue", "borderStrengthValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "borderValue", "brightnessValue", "cRainbowSecValue", "distanceValue", "exhiValue", "fovSizeValue", "gidentspeed", "gradientAmountValue", "greenValue", "lineValue", "playerShapeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "playerSizeValue", "rainbowList", "redValue", "saturationValue", "shadowValue", "sizeValue", "viewDistanceValue", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Companion", "Relaxed"})
public final class Radar
extends Element {
    private final FloatValue sizeValue;
    private final FloatValue viewDistanceValue;
    private final ListValue playerShapeValue;
    private final FloatValue playerSizeValue;
    private final FloatValue fovSizeValue;
    private final BoolValue exhiValue;
    private final BoolValue blur;
    private final BoolValue lineValue;
    private final ListValue rainbowList;
    private final IntegerValue redValue;
    private final IntegerValue greenValue;
    private final IntegerValue blueValue;
    private final IntegerValue alphaValue;
    private final IntegerValue gidentspeed;
    private final FloatValue saturationValue;
    private final FloatValue brightnessValue;
    private final IntegerValue cRainbowSecValue;
    private final IntegerValue distanceValue;
    private final IntegerValue gradientAmountValue;
    private final IntegerValue backgroundRedValue;
    private final IntegerValue backgroundGreenValue;
    private final IntegerValue backgroundBlueValue;
    private final IntegerValue backgroundAlphaValue;
    private final BoolValue borderValue;
    private final FloatValue borderStrengthValue;
    private final IntegerValue borderRedValue;
    private final IntegerValue borderGreenValue;
    private final IntegerValue borderBlueValue;
    private final IntegerValue borderAlphaValue;
    private final BoolValue shadowValue;
    private static final float SQRT_OF_TWO;
    public static final Companion Companion;

    /*
     * WARNING - void declaration
     */
    @Override
    @Nullable
    public Border drawElement() {
        Tessellator tessellator;
        IEntity renderViewEntity = MinecraftInstance.mc.getRenderViewEntity();
        float size = ((Number)this.sizeValue.get()).floatValue();
        float viewDistance = ((Number)this.viewDistanceValue.get()).floatValue() * 16.0f;
        double maxDisplayableDistanceSquare = ((double)viewDistance + (double)((Number)this.fovSizeValue.get()).floatValue()) * ((double)viewDistance + (double)((Number)this.fovSizeValue.get()).floatValue());
        float halfSize = size / 2.0f;
        String rainbowType = (String)this.rainbowList.get();
        int cColor = new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue(), ((Number)this.alphaValue.get()).intValue()).getRGB();
        if (((Boolean)this.blur.get()).booleanValue()) {
            GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
            BlurBuffer.blurArea((float)this.getRenderX(), (float)this.getRenderY(), size, size);
            BlurBuffer.blurArea((float)this.getRenderX(), (float)this.getRenderY(), size, size);
            GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
        }
        if (((Boolean)this.exhiValue.get()).booleanValue()) {
            RenderUtils.drawExhiRect(0.0f, (Boolean)this.lineValue.get() != false ? -1.0f : 0.0f, size, size);
        } else {
            RenderUtils.drawRect(0.0f, 0.0f, size, size, new Color(((Number)this.backgroundRedValue.get()).intValue(), ((Number)this.backgroundGreenValue.get()).intValue(), ((Number)this.backgroundBlueValue.get()).intValue(), ((Number)this.backgroundAlphaValue.get()).intValue()).getRGB());
        }
        if (((Boolean)this.shadowValue.get()).booleanValue()) {
            RenderUtils.drawShadowWithCustomAlpha(0.0f, 0.0f, size, size, 255.0f);
        }
        if (((Boolean)this.lineValue.get()).booleanValue()) {
            double barLength = size;
            int n = 0;
            int n2 = ((Number)this.gradientAmountValue.get()).intValue() - 1;
            if (n <= n2) {
                while (true) {
                    int n3;
                    int n4;
                    void i;
                    double barStart = (double)i / (double)((Number)this.gradientAmountValue.get()).intValue() * barLength;
                    double barEnd = (double)(i + true) / (double)((Number)this.gradientAmountValue.get()).intValue() * barLength;
                    switch (rainbowType) {
                        case "CRainbow": {
                            n4 = RenderUtils.getRainbowOpaque(((Number)this.cRainbowSecValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), (int)(i * ((Number)this.distanceValue.get()).intValue()));
                            break;
                        }
                        case "Sky": {
                            n4 = RenderUtils.SkyRainbow((int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            break;
                        }
                        case "LiquidSlowly": {
                            Color color = ColorUtils.LiquidSlowly(System.nanoTime(), (int)(i * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            if (color == null) {
                                Intrinsics.throwNpe();
                            }
                            n4 = color.getRGB();
                            break;
                        }
                        case "Fade": {
                            n4 = ColorUtils.fade(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), (int)(i * ((Number)this.distanceValue.get()).intValue()), 100).getRGB();
                            break;
                        }
                        case "Gident": {
                            Color color = VisualUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color, "VisualUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n4 = color.getRGB();
                            break;
                        }
                        default: {
                            n4 = cColor;
                        }
                    }
                    switch (rainbowType) {
                        case "CRainbow": {
                            n3 = RenderUtils.getRainbowOpaque(((Number)this.cRainbowSecValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), (int)((i + true) * ((Number)this.distanceValue.get()).intValue()));
                            break;
                        }
                        case "Sky": {
                            n3 = RenderUtils.SkyRainbow((int)((i + true) * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            break;
                        }
                        case "LiquidSlowly": {
                            Color color = ColorUtils.LiquidSlowly(System.nanoTime(), (int)((i + true) * ((Number)this.distanceValue.get()).intValue()), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                            if (color == null) {
                                Intrinsics.throwNpe();
                            }
                            n3 = color.getRGB();
                            break;
                        }
                        case "Fade": {
                            n3 = ColorUtils.fade(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), (int)((i + true) * ((Number)this.distanceValue.get()).intValue()), 100).getRGB();
                            break;
                        }
                        case "Gident": {
                            Color color = VisualUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color, "VisualUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n3 = color.getRGB();
                            break;
                        }
                        default: {
                            n3 = cColor;
                        }
                    }
                    RenderUtils.drawGradientSideways(barStart, -1.0, barEnd, 0.0, n4, n3);
                    if (i == n2) break;
                    ++i;
                }
            }
        }
        if (((Boolean)this.borderValue.get()).booleanValue()) {
            float strength = ((Number)this.borderStrengthValue.get()).floatValue() / 2.0f;
            int borderColor = new Color(((Number)this.borderRedValue.get()).intValue(), ((Number)this.borderGreenValue.get()).intValue(), ((Number)this.borderBlueValue.get()).intValue(), ((Number)this.borderAlphaValue.get()).intValue()).getRGB();
            RenderUtils.drawRect(halfSize - strength, 0.0f, halfSize + strength, size, borderColor);
            RenderUtils.drawRect(0.0f, halfSize - strength, halfSize - strength, halfSize + strength, borderColor);
            RenderUtils.drawRect(halfSize + strength, halfSize - strength, size, halfSize + strength, borderColor);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        float f = (float)this.getX();
        float f2 = (float)this.getY();
        float f3 = (float)this.getX();
        boolean strength = false;
        float f4 = (float)Math.ceil(size);
        float f5 = f + f4;
        f4 = (float)this.getY();
        f = f5;
        strength = false;
        float f6 = (float)Math.ceil(size);
        RenderUtils.makeScissorBox(f3, f2, f, f4 + f6);
        GL11.glEnable((int)3089);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)halfSize, (float)halfSize, (float)0.0f);
        IEntity iEntity = renderViewEntity;
        if (iEntity == null) {
            Intrinsics.throwNpe();
        }
        GL11.glRotatef((float)iEntity.getRotationYaw(), (float)0.0f, (float)0.0f, (float)-1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        boolean circleMode = StringsKt.equals((String)this.playerShapeValue.get(), "circle", true);
        Tessellator tessellator2 = tessellator = Tessellator.func_178181_a();
        Intrinsics.checkExpressionValueIsNotNull(tessellator2, "tessellator");
        BufferBuilder worldRenderer = tessellator2.func_178180_c();
        if (circleMode) {
            GL11.glEnable((int)2832);
        }
        float playerSize = ((Number)this.playerSizeValue.get()).floatValue();
        GL11.glEnable((int)2881);
        worldRenderer.func_181668_a(0, DefaultVertexFormats.field_181706_f);
        GL11.glPointSize((float)playerSize);
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        for (IEntity entity : iWorldClient.getLoadedEntityList()) {
            boolean transform;
            Vector2f positionRelativeToPlayer;
            if (entity == null || entity == MinecraftInstance.mc.getThePlayer() || !EntityUtils.isSelected(entity, false) || maxDisplayableDistanceSquare < (double)(positionRelativeToPlayer = new Vector2f((float)(renderViewEntity.getPosX() - entity.getPosX()), (float)(renderViewEntity.getPosZ() - entity.getPosZ()))).lengthSquared()) continue;
            boolean bl = transform = ((Number)this.fovSizeValue.get()).floatValue() > 0.0f;
            if (transform) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(positionRelativeToPlayer.x / viewDistance * size), (float)(positionRelativeToPlayer.y / viewDistance * size), (float)0.0f);
                GL11.glRotatef((float)entity.getRotationYaw(), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            Module module = LiquidBounce.INSTANCE.getModuleManager().get(ESP.class);
            if (module == null) {
                throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.render.ESP");
            }
            Color color = ((ESP)module).getColor(entity);
            worldRenderer.func_181662_b((double)(positionRelativeToPlayer.x / viewDistance * size), (double)(positionRelativeToPlayer.y / viewDistance * size), 0.0).func_181666_a((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 1.0f).func_181675_d();
            if (!transform) continue;
            GL11.glPopMatrix();
        }
        tessellator.func_78381_a();
        if (circleMode) {
            GL11.glDisable((int)2832);
        }
        GL11.glDisable((int)2881);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glDisable((int)3089);
        GL11.glPopMatrix();
        return new Border(0.0f, 0.0f, size, size);
    }

    public Radar(double x, double y) {
        super(x, y, 0.0f, null, 12, null);
        this.sizeValue = new FloatValue("Size", 90.0f, 30.0f, 500.0f);
        this.viewDistanceValue = new FloatValue("View Distance", 4.0f, 0.5f, 32.0f);
        this.playerShapeValue = new ListValue("Player Shape", new String[]{"Rectangle", "Circle"}, "Triangle");
        this.playerSizeValue = new FloatValue("Player Size", 2.0f, 0.5f, 20.0f);
        this.fovSizeValue = new FloatValue("FOV Size", 10.0f, 0.0f, 50.0f);
        this.exhiValue = new BoolValue("Use Exhi Rect", false);
        this.blur = new BoolValue("Blur", true);
        this.lineValue = new BoolValue("Line", false);
        this.rainbowList = new ListValue("Line-Rainbow", new String[]{"Off", "CRainbow", "Sky", "LiquidSlowly", "Fade", "Gident"}, "Off");
        this.redValue = new IntegerValue("Line-Red", 255, 0, 255);
        this.greenValue = new IntegerValue("Line-Green", 255, 0, 255);
        this.blueValue = new IntegerValue("Line-Blue", 255, 0, 255);
        this.alphaValue = new IntegerValue("Line-Alpha", 255, 0, 255);
        this.gidentspeed = new IntegerValue("GidentSpeed", 100, 1, 1000);
        this.saturationValue = new FloatValue("Saturation", 0.9f, 0.0f, 1.0f);
        this.brightnessValue = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
        this.cRainbowSecValue = new IntegerValue("Seconds", 2, 1, 10);
        this.distanceValue = new IntegerValue("Line-Distance", 0, 0, 400);
        this.gradientAmountValue = new IntegerValue("Gradient-Amount", 25, 1, 50);
        this.backgroundRedValue = new IntegerValue("Background Red", 0, 0, 255);
        this.backgroundGreenValue = new IntegerValue("Background Green", 0, 0, 255);
        this.backgroundBlueValue = new IntegerValue("Background Blue", 0, 0, 255);
        this.backgroundAlphaValue = new IntegerValue("Background Alpha", 50, 0, 255);
        this.borderValue = new BoolValue("Border", false);
        this.borderStrengthValue = new FloatValue("Border Strength", 2.0f, 1.0f, 5.0f);
        this.borderRedValue = new IntegerValue("Border Red", 0, 0, 255);
        this.borderGreenValue = new IntegerValue("Border Green", 0, 0, 255);
        this.borderBlueValue = new IntegerValue("Border Blue", 0, 0, 255);
        this.borderAlphaValue = new IntegerValue("Border Alpha", 150, 0, 255);
        this.shadowValue = new BoolValue("Shadow", true);
    }

    public /* synthetic */ Radar(double d, double d2, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 5.0;
        }
        if ((n & 2) != 0) {
            d2 = 130.0;
        }
        this(d, d2);
    }

    public Radar() {
        this(0.0, 0.0, 3, (DefaultConstructorMarker)null);
    }

    static {
        Companion = new Companion(null);
        float f = 2.0f;
        boolean bl = false;
        SQRT_OF_TWO = (float)Math.sqrt(f);
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Radar$Companion;", "", "()V", "SQRT_OF_TWO", "", "Relaxed"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

