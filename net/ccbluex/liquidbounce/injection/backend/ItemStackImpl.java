/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.injection.backend;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.block.state.IIBlockState;
import net.ccbluex.liquidbounce.api.minecraft.enchantments.IEnchantment;
import net.ccbluex.liquidbounce.api.minecraft.entity.ai.attributes.IAttributeModifier;
import net.ccbluex.liquidbounce.api.minecraft.item.IItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.nbt.INBTBase;
import net.ccbluex.liquidbounce.api.minecraft.nbt.INBTTagCompound;
import net.ccbluex.liquidbounce.api.minecraft.nbt.INBTTagList;
import net.ccbluex.liquidbounce.api.util.WrappedCollection;
import net.ccbluex.liquidbounce.injection.backend.EnchantmentImpl;
import net.ccbluex.liquidbounce.injection.backend.IBlockStateImpl;
import net.ccbluex.liquidbounce.injection.backend.ItemImpl;
import net.ccbluex.liquidbounce.injection.backend.ItemStackImpl;
import net.ccbluex.liquidbounce.injection.backend.NBTBaseImpl;
import net.ccbluex.liquidbounce.injection.backend.NBTTagCompoundImpl;
import net.ccbluex.liquidbounce.injection.backend.NBTTagListImpl;
import net.ccbluex.liquidbounce.injection.implementations.IMixinItemStack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0012H\u0016J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u000201002\u0006\u00102\u001a\u00020\u0006H\u0016J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u000208H\u0016J\u0010\u00109\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010:\u001a\u00020+2\u0006\u00102\u001a\u00020\u00062\u0006\u0010;\u001a\u00020<H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00128VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015R\u0014\u0010\u001e\u001a\u00020\u00128VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R(\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u0011\u001a\u0004\u0018\u00010 8V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b'\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)\u00a8\u0006="}, d2={"Lnet/ccbluex/liquidbounce/injection/backend/ItemStackImpl;", "Lnet/ccbluex/liquidbounce/api/minecraft/item/IItemStack;", "wrapped", "Lnet/minecraft/item/ItemStack;", "(Lnet/minecraft/item/ItemStack;)V", "displayName", "", "getDisplayName", "()Ljava/lang/String;", "enchantmentTagList", "Lnet/ccbluex/liquidbounce/api/minecraft/nbt/INBTTagList;", "getEnchantmentTagList", "()Lnet/ccbluex/liquidbounce/api/minecraft/nbt/INBTTagList;", "item", "Lnet/ccbluex/liquidbounce/api/minecraft/item/IItem;", "getItem", "()Lnet/ccbluex/liquidbounce/api/minecraft/item/IItem;", "value", "", "itemDamage", "getItemDamage", "()I", "setItemDamage", "(I)V", "itemDelay", "", "getItemDelay", "()J", "maxItemUseDuration", "getMaxItemUseDuration", "stackSize", "getStackSize", "Lnet/ccbluex/liquidbounce/api/minecraft/nbt/INBTTagCompound;", "tagCompound", "getTagCompound", "()Lnet/ccbluex/liquidbounce/api/minecraft/nbt/INBTTagCompound;", "setTagCompound", "(Lnet/ccbluex/liquidbounce/api/minecraft/nbt/INBTTagCompound;)V", "unlocalizedName", "getUnlocalizedName", "getWrapped", "()Lnet/minecraft/item/ItemStack;", "addEnchantment", "", "enchantment", "Lnet/ccbluex/liquidbounce/api/minecraft/enchantments/IEnchantment;", "level", "getAttributeModifier", "", "Lnet/ccbluex/liquidbounce/api/minecraft/entity/ai/attributes/IAttributeModifier;", "key", "getStrVsBlock", "", "block", "Lnet/ccbluex/liquidbounce/api/minecraft/block/state/IIBlockState;", "isSplash", "", "setStackDisplayName", "setTagInfo", "nbt", "Lnet/ccbluex/liquidbounce/api/minecraft/nbt/INBTBase;", "Relaxed"})
public final class ItemStackImpl
implements IItemStack {
    @NotNull
    private final ItemStack wrapped;

    /*
     * WARNING - void declaration
     */
    @Override
    public float getStrVsBlock(@NotNull IIBlockState block) {
        void $this$unwrap$iv;
        Intrinsics.checkParameterIsNotNull(block, "block");
        IIBlockState iIBlockState = block;
        ItemStack itemStack = this.wrapped;
        boolean $i$f$unwrap = false;
        IBlockState iBlockState = ((IBlockStateImpl)$this$unwrap$iv).getWrapped();
        return itemStack.func_150997_a(iBlockState);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void setTagInfo(@NotNull String key, @NotNull INBTBase nbt) {
        void $this$unwrap$iv;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(nbt, "nbt");
        INBTBase iNBTBase = nbt;
        String string = key;
        ItemStack itemStack = this.wrapped;
        boolean $i$f$unwrap = false;
        Object t = ((NBTBaseImpl)$this$unwrap$iv).getWrapped();
        itemStack.func_77983_a(string, t);
    }

    @Override
    @NotNull
    public IItemStack setStackDisplayName(@NotNull String displayName) {
        Intrinsics.checkParameterIsNotNull(displayName, "displayName");
        ItemStack itemStack = this.wrapped.func_151001_c(displayName);
        Intrinsics.checkExpressionValueIsNotNull(itemStack, "wrapped.setStackDisplayName(displayName)");
        ItemStack $this$wrap$iv = itemStack;
        boolean $i$f$wrap = false;
        return new ItemStackImpl($this$wrap$iv);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void addEnchantment(@NotNull IEnchantment enchantment, int level) {
        void $this$unwrap$iv;
        Intrinsics.checkParameterIsNotNull(enchantment, "enchantment");
        IEnchantment iEnchantment = enchantment;
        ItemStack itemStack = this.wrapped;
        boolean $i$f$unwrap = false;
        Enchantment enchantment2 = ((EnchantmentImpl)$this$unwrap$iv).getWrapped();
        itemStack.func_77966_a(enchantment2, level);
    }

    @Override
    @NotNull
    public Collection<IAttributeModifier> getAttributeModifier(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new WrappedCollection(this.wrapped.func_111283_C(EntityEquipmentSlot.MAINHAND).get((Object)key), getAttributeModifier.1.INSTANCE, getAttributeModifier.2.INSTANCE);
    }

    @Override
    public boolean isSplash() {
        return Intrinsics.areEqual(this.wrapped.func_77973_b(), Items.field_185155_bH);
    }

    @Override
    @NotNull
    public String getDisplayName() {
        String string = this.wrapped.func_82833_r();
        Intrinsics.checkExpressionValueIsNotNull(string, "wrapped.displayName");
        return string;
    }

    @Override
    @NotNull
    public String getUnlocalizedName() {
        String string = this.wrapped.func_77977_a();
        Intrinsics.checkExpressionValueIsNotNull(string, "wrapped.unlocalizedName");
        return string;
    }

    @Override
    public int getMaxItemUseDuration() {
        return this.wrapped.func_77988_m();
    }

    @Override
    @Nullable
    public INBTTagList getEnchantmentTagList() {
        INBTTagList iNBTTagList;
        NBTTagList nBTTagList = this.wrapped.func_77986_q();
        if (nBTTagList != null) {
            NBTTagList $this$wrap$iv = nBTTagList;
            boolean $i$f$wrap = false;
            iNBTTagList = new NBTTagListImpl($this$wrap$iv);
        } else {
            iNBTTagList = null;
        }
        return iNBTTagList;
    }

    @Override
    @Nullable
    public INBTTagCompound getTagCompound() {
        INBTTagCompound iNBTTagCompound;
        NBTTagCompound nBTTagCompound = this.wrapped.func_77978_p();
        if (nBTTagCompound != null) {
            NBTTagCompound $this$wrap$iv = nBTTagCompound;
            boolean $i$f$wrap = false;
            iNBTTagCompound = new NBTTagCompoundImpl($this$wrap$iv);
        } else {
            iNBTTagCompound = null;
        }
        return iNBTTagCompound;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void setTagCompound(@Nullable INBTTagCompound value) {
        NBTTagCompound nBTTagCompound;
        ItemStack itemStack = this.wrapped;
        INBTTagCompound iNBTTagCompound = value;
        if (iNBTTagCompound != null) {
            void $this$unwrap$iv;
            INBTTagCompound iNBTTagCompound2 = iNBTTagCompound;
            ItemStack itemStack2 = itemStack;
            boolean $i$f$unwrap = false;
            void v2 = $this$unwrap$iv;
            if (v2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.injection.backend.NBTTagCompoundImpl");
            }
            NBTTagCompound nBTTagCompound2 = (NBTTagCompound)((NBTTagCompoundImpl)v2).getWrapped();
            itemStack = itemStack2;
            nBTTagCompound = nBTTagCompound2;
        } else {
            nBTTagCompound = null;
        }
        itemStack.func_77982_d(nBTTagCompound);
    }

    @Override
    public int getStackSize() {
        return this.wrapped.field_77994_a;
    }

    @Override
    public int getItemDamage() {
        return this.wrapped.func_77952_i();
    }

    @Override
    public void setItemDamage(int value) {
        this.wrapped.func_77964_b(value);
    }

    @Override
    @Nullable
    public IItem getItem() {
        IItem iItem;
        Item item = this.wrapped.func_77973_b();
        if (item != null) {
            Item $this$wrap$iv = item;
            boolean $i$f$wrap = false;
            iItem = new ItemImpl<Item>($this$wrap$iv);
        } else {
            iItem = null;
        }
        return iItem;
    }

    @Override
    public long getItemDelay() {
        ItemStack itemStack = this.wrapped;
        if (itemStack == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.injection.implementations.IMixinItemStack");
        }
        return ((IMixinItemStack)itemStack).getItemDelay();
    }

    @NotNull
    public final ItemStack getWrapped() {
        return this.wrapped;
    }

    public ItemStackImpl(@NotNull ItemStack wrapped) {
        Intrinsics.checkParameterIsNotNull(wrapped, "wrapped");
        this.wrapped = wrapped;
    }
}

