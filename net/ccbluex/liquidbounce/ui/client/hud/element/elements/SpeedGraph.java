/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="SpeedGraph")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/SpeedGraph;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "colorBlueValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "colorGreenValue", "colorRedValue", "height", "lastTick", "", "speedList", "Ljava/util/ArrayList;", "thickness", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "width", "yMultiplier", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class SpeedGraph
extends Element {
    private final FloatValue yMultiplier;
    private final IntegerValue height;
    private final IntegerValue width;
    private final FloatValue thickness;
    private final IntegerValue colorRedValue;
    private final IntegerValue colorGreenValue;
    private final IntegerValue colorBlueValue;
    private final ArrayList<Double> speedList;
    private int lastTick;

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Border drawElement() {
        int start;
        int width = ((Number)this.width.get()).intValue();
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (this.lastTick != iEntityPlayerSP.getTicksExisted()) {
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            this.lastTick = iEntityPlayerSP2.getTicksExisted();
            IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            double z2 = iEntityPlayerSP3.getPosZ();
            IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP4 == null) {
                Intrinsics.throwNpe();
            }
            double z1 = iEntityPlayerSP4.getPrevPosZ();
            IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP5 == null) {
                Intrinsics.throwNpe();
            }
            double x2 = iEntityPlayerSP5.getPosX();
            IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP6 == null) {
                Intrinsics.throwNpe();
            }
            double x1 = iEntityPlayerSP6.getPrevPosX();
            double d = (z2 - z1) * (z2 - z1) + (x2 - x1) * (x2 - x1);
            boolean bl = false;
            double speed = Math.sqrt(d);
            if (speed < 0.0) {
                speed = -speed;
            }
            this.speedList.add(speed);
            while (this.speedList.size() > width) {
                this.speedList.remove(0);
            }
        }
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)((Number)this.thickness.get()).floatValue());
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glBegin((int)1);
        int size = this.speedList.size();
        int z1 = start = size > width ? size - width : 0;
        int n = size - 1;
        while (z1 < n) {
            void i;
            double y = ((Number)this.speedList.get((int)i)).doubleValue() * (double)10 * ((Number)this.yMultiplier.get()).doubleValue();
            double y1 = ((Number)this.speedList.get((int)(i + true))).doubleValue() * (double)10 * ((Number)this.yMultiplier.get()).doubleValue();
            RenderUtils.glColor(new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), 255));
            GL11.glVertex2d((double)((double)i - (double)start), (double)((double)(((Number)this.height.get()).intValue() + 1) - RangesKt.coerceAtMost(y, (double)((Number)this.height.get()).intValue())));
            GL11.glVertex2d((double)((double)i + 1.0 - (double)start), (double)((double)(((Number)this.height.get()).intValue() + 1) - RangesKt.coerceAtMost(y1, (double)((Number)this.height.get()).intValue())));
            ++i;
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GlStateManager.func_179117_G();
        return new Border(0.0f, 0.0f, width, (float)((Number)this.height.get()).intValue() + (float)2);
    }

    public SpeedGraph(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.yMultiplier = new FloatValue("yMultiplier", 7.0f, 1.0f, 20.0f);
        this.height = new IntegerValue("Height", 50, 30, 150);
        this.width = new IntegerValue("Width", 150, 100, 300);
        this.thickness = new FloatValue("Thickness", 2.0f, 1.0f, 3.0f);
        this.colorRedValue = new IntegerValue("R", 0, 0, 255);
        this.colorGreenValue = new IntegerValue("G", 111, 0, 255);
        this.colorBlueValue = new IntegerValue("B", 255, 0, 255);
        this.speedList = new ArrayList();
        this.lastTick = -1;
    }

    public /* synthetic */ SpeedGraph(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 75.0;
        }
        if ((n & 2) != 0) {
            d2 = 110.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        if ((n & 8) != 0) {
            side = new Side(Side.Horizontal.MIDDLE, Side.Vertical.DOWN);
        }
        this(d, d2, f, side);
    }

    public SpeedGraph() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }
}

