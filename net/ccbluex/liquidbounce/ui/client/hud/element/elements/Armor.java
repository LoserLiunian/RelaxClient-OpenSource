/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.enums.MaterialType;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.render.entity.IRenderItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="Armor")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Armor;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class Armor
extends Element {
    private final ListValue modeValue;

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public Border drawElement() {
        if (MinecraftInstance.mc.getPlayerController().isNotCreative()) {
            GL11.glPushMatrix();
            IRenderItem renderItem = MinecraftInstance.mc.getRenderItem();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            boolean isInsideWater = iEntityPlayerSP.isInsideOfMaterial(MinecraftInstance.classProvider.getMaterialEnum(MaterialType.WATER));
            int x = 1;
            int y = isInsideWater ? -10 : 0;
            String mode = (String)this.modeValue.get();
            int n = 3;
            boolean bl = false;
            while (n >= 0) {
                void index;
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP2.getInventory().getArmorInventory().get((int)index) == null) {
                } else {
                    IItemStack stack;
                    renderItem.renderItemIntoGUI(stack, x, y);
                    renderItem.renderItemOverlays(MinecraftInstance.mc.getFontRendererObj(), stack, x, y);
                    if (StringsKt.equals(mode, "Horizontal", true)) {
                        x += 18;
                    } else if (StringsKt.equals(mode, "Vertical", true)) {
                        y += 18;
                    }
                }
                --index;
            }
            MinecraftInstance.classProvider.getGlStateManager().enableAlpha();
            MinecraftInstance.classProvider.getGlStateManager().disableBlend();
            MinecraftInstance.classProvider.getGlStateManager().disableLighting();
            MinecraftInstance.classProvider.getGlStateManager().disableCull();
            GL11.glPopMatrix();
        }
        return StringsKt.equals((String)this.modeValue.get(), "Horizontal", true) ? new Border(0.0f, 0.0f, 72.0f, 17.0f) : new Border(0.0f, 0.0f, 18.0f, 72.0f);
    }

    public Armor(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.modeValue = new ListValue("Alignment", new String[]{"Horizontal", "Vertical"}, "Horizontal");
    }

    public /* synthetic */ Armor(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = -8.0;
        }
        if ((n & 2) != 0) {
            d2 = 57.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        if ((n & 8) != 0) {
            side = new Side(Side.Horizontal.MIDDLE, Side.Vertical.DOWN);
        }
        this(d, d2, f, side);
    }

    public Armor() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }
}

