/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.api.minecraft.client.render.entity;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J \u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J \u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J(\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J(\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&R\u0018\u0010\u0002\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0016"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/client/render/entity/IRenderItem;", "", "zLevel", "", "getZLevel", "()F", "setZLevel", "(F)V", "renderItemAndEffectIntoGUI", "", "stack", "Lnet/ccbluex/liquidbounce/api/minecraft/item/IItemStack;", "x", "", "y", "renderItemAndEffectIntoGUI2", "Lnet/minecraft/item/ItemStack;", "renderItemIntoGUI", "renderItemOverlays", "fontRenderer", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;", "renderItemOverlays2", "Relaxed"})
public interface IRenderItem {
    public float getZLevel();

    public void setZLevel(float var1);

    public void renderItemAndEffectIntoGUI(@NotNull IItemStack var1, int var2, int var3);

    public void renderItemIntoGUI(@NotNull IItemStack var1, int var2, int var3);

    public void renderItemOverlays(@NotNull IFontRenderer var1, @NotNull IItemStack var2, int var3, int var4);

    public void renderItemAndEffectIntoGUI2(@NotNull ItemStack var1, int var2, int var3);

    public void renderItemOverlays2(@NotNull IFontRenderer var1, @NotNull ItemStack var2, int var3, int var4);
}

