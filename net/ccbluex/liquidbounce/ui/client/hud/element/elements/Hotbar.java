/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.hotbarutil;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@ElementInfo(name="Hotbar")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Hotbar;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "(DD)V", "lastSlot", "", "slotlist", "", "Lnet/ccbluex/liquidbounce/utils/hotbarutil;", "getSlotlist", "()Ljava/util/List;", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class Hotbar
extends Element {
    @NotNull
    private final List<hotbarutil> slotlist;
    private int lastSlot;

    @NotNull
    public final List<hotbarutil> getSlotlist() {
        return this.slotlist;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    @NotNull
    public Border drawElement() {
        GlStateManager.func_179094_E();
        GlStateManager.func_179091_B();
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        $this$forEachIndexed$iv = this.slotlist;
        $i$f$forEachIndexed = false;
        index$iv = 0;
        for (T item$iv : $this$forEachIndexed$iv) {
            var6_6 = index$iv++;
            var7_7 = false;
            if (var6_6 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            var8_8 = var6_6;
            var9_9 = (hotbarutil)item$iv;
            index = var8_8;
            $i$a$-forEachIndexed-Hotbar$drawElement$1 = false;
            v0 = MinecraftInstance.mc.getThePlayer();
            if (v0 == null) {
                Intrinsics.throwNpe();
            }
            if (index != v0.getInventory().getCurrentItem()) ** GOTO lbl-1000
            v1 = MinecraftInstance.mc.getThePlayer();
            if (v1 == null) {
                Intrinsics.throwNpe();
            }
            if (v1.getInventory().getMainInventory().get(index) != null) {
                v2 = true;
            } else lbl-1000:
            // 2 sources

            {
                v2 = false;
            }
            hover = v2;
            scale = hotbarutil.getTranslate().getX();
            positionX = (float)(index * 25) / scale - (float)5;
            v3 = MinecraftInstance.mc.getThePlayer();
            if (v3 == null) {
                Intrinsics.throwNpe();
            }
            v4 = v3.getInventory().getMainInventory();
            v5 = MinecraftInstance.mc.getThePlayer();
            if (v5 == null) {
                Intrinsics.throwNpe();
            }
            currentitem = v4.get(v5.getInventory().getCurrentItem());
            v6 = MinecraftInstance.mc2.field_71439_g;
            if (v6 == null) {
                Intrinsics.throwNpe();
            }
            v7 = v6.field_71071_by.field_70462_a;
            v8 = MinecraftInstance.mc2.field_71439_g;
            if (v8 == null) {
                Intrinsics.throwNpe();
            }
            v9 = v7.get(v8.field_71071_by.field_70461_c);
            Intrinsics.checkExpressionValueIsNotNull(v9, "mc2.player!!.inventory.m\u2026!!.inventory.currentItem]");
            currentitem2 = (ItemStack)v9;
            hotbarutil.setSize(hover != false ? 1.5f : 1.0f);
            hotbarutil.getTranslate().translate(hotbarutil.getSize(), 0.0f, 2.0);
            if (hover) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179152_a((float)(scale - 0.5f), (float)(scale - 0.5f), (float)(scale - 0.5f));
                try {
                    list = currentitem2.func_82840_a((EntityPlayer)MinecraftInstance.mc2.field_71439_g, MinecraftInstance.mc.getGameSettings().getAdvancedItemTooltips());
                    infolist = new ArrayList<E>();
                    var19_21 = 0;
                    var20_23 = list.size();
                    while (var19_21 < var20_23) {
                        if (!infolist.contains(list.get((int)i)) && ((String)list.get((int)i)).length() > 2) {
                            infolist.add(list.get((int)i));
                        }
                        ++i;
                    }
                    posy = -13.0f;
                    $this$forEachIndexed$iv = infolist;
                    $i$f$forEachIndexed = false;
                    index$iv = 0;
                    for (T item$iv : $this$forEachIndexed$iv) {
                        var25_28 = index$iv++;
                        var26_29 = false;
                        if (var25_28 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        var27_30 = var25_28;
                        var28_31 = (String)item$iv;
                        index = var27_30;
                        $i$a$-forEachIndexed-Hotbar$drawElement$1$1 = false;
                        v10 = ColorUtils.stripColor((String)infolist.get(index));
                        v11 = currentitem;
                        if (v11 == null) {
                            Intrinsics.throwNpe();
                        }
                        font = Intrinsics.areEqual(v10, v11.getDisplayName()) != false ? Fonts.font35 : Fonts.font30;
                        v12 = infolist.get(index);
                        Intrinsics.checkExpressionValueIsNotNull(v12, "infolist[index]");
                        font.drawString((String)v12, positionX * 1.5f, -(8.5f * (float)infolist.size()) + posy, Intrinsics.areEqual(ColorUtils.stripColor((String)infolist.get(index)), currentitem.getDisplayName()) != false ? -1 : new Color(175, 175, 175).getRGB(), true);
                        posy += (float)font.getFontHeight() + 2.0f;
                    }
                    infolist.clear();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                GlStateManager.func_179121_F();
            }
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)scale, (float)scale, (float)scale);
            RenderHelper.func_74520_c();
            hotbarutil.renderHotbarItem(index, positionX, -10.0f, MinecraftInstance.mc.getTimer().getRenderPartialTicks());
            RenderHelper.func_74518_a();
            GlStateManager.func_179121_F();
        }
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
        return new Border(0.0f, 0.0f, 180.0f, 17.0f);
    }

    /*
     * WARNING - void declaration
     */
    public Hotbar(double x, double y) {
        super(x, y, 0.0f, null, 12, null);
        List list;
        Hotbar hotbar = this;
        int n = 0;
        hotbar.slotlist = list = (List)new ArrayList();
        this.lastSlot = -1;
        n = 0;
        int n2 = 8;
        while (n <= n2) {
            void i;
            hotbarutil slot = new hotbarutil();
            this.slotlist.add(slot);
            ++i;
        }
    }

    public /* synthetic */ Hotbar(double d, double d2, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 40.0;
        }
        if ((n & 2) != 0) {
            d2 = 100.0;
        }
        this(d, d2);
    }

    public Hotbar() {
        this(0.0, 0.0, 3, (DefaultConstructorMarker)null);
    }
}

