/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemShield
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.item;

import me.utils.Debug;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.render.AntiBlind;
import net.ccbluex.liquidbounce.features.module.modules.render.OldHitting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={ItemRenderer.class})
public abstract class MixinItemRenderer {
    @Shadow
    @Final
    private Minecraft field_78455_a;
    @Shadow
    private ItemStack field_187468_e;
    private float equippedProgress;
    private float prevEquippedProgress;
    @Shadow
    private ItemStack field_187467_d;

    @Shadow
    protected abstract void func_187463_a(float var1, float var2, float var3);

    @Shadow
    protected abstract void func_187453_a(EnumHandSide var1, float var2);

    @Shadow
    protected abstract void func_187454_a(float var1, EnumHandSide var2, ItemStack var3);

    @Shadow
    protected abstract void func_187456_a(float var1, float var2, EnumHandSide var3);

    @Shadow
    protected abstract void func_187465_a(float var1, EnumHandSide var2, float var3, ItemStack var4);

    @Shadow
    protected abstract void func_187459_b(EnumHandSide var1, float var2);

    @Shadow
    public abstract void func_187462_a(EntityLivingBase var1, ItemStack var2, ItemCameraTransforms.TransformType var3, boolean var4);

    private void func_178096_b(float p_178096_1_, float p_178096_2_) {
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179109_b((float)0.0f, (float)(p_178096_1_ * -0.6f), (float)0.0f);
        GlStateManager.func_179114_b((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        float var3 = MathHelper.func_76126_a((float)(p_178096_2_ * p_178096_2_ * (float)Math.PI));
        float var4 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)p_178096_2_) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var3 * -20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var4 * -20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var4 * -80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    private void strange(float lul, float lol) {
        GlStateManager.func_179109_b((float)0.56f, (float)-0.52f, (float)-0.71999997f);
        GlStateManager.func_179114_b((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        float var26 = MathHelper.func_76126_a((float)(lol * lul * (float)Math.PI));
        float var27 = MathHelper.func_76134_b((float)(MathHelper.func_76129_c((float)lul) * (float)Math.PI));
        float var28 = MathHelper.func_76135_e((float)(MathHelper.func_76129_c((float)lul) * (float)Math.PI));
        GlStateManager.func_179114_b((float)(var26 * var27), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(var28 * 15.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)(var27 * 10.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
    }

    private void func_178103_d() {
        GlStateManager.func_179109_b((float)-0.5f, (float)0.2f, (float)0.0f);
        GlStateManager.func_179114_b((float)30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)-80.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
    }

    @Overwrite
    public void func_187457_a(AbstractClientPlayer player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_) {
        boolean flag = hand == EnumHand.MAIN_HAND;
        EnumHandSide enumhandside = flag ? player.func_184591_cq() : player.func_184591_cq().func_188468_a();
        GlStateManager.func_179094_E();
        EntityPlayerSP abstractclientplayer = this.field_78455_a.field_71439_g;
        if (LiquidBounce.moduleManager.getModule(OldHitting.class).getState()) {
            GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
            GL11.glTranslated((double)((Float)OldHitting.itemPosX.get()).doubleValue(), (double)((Float)OldHitting.itemPosY.get()).doubleValue(), (double)((Float)OldHitting.itemPosZ.get()).doubleValue());
        }
        float f8 = abstractclientplayer.func_70678_g(p_187457_5_);
        OldHitting ot = (OldHitting)LiquidBounce.moduleManager.getModule(OldHitting.class);
        KillAura aura = (KillAura)LiquidBounce.moduleManager.getModule(KillAura.class);
        if (LiquidBounce.moduleManager.getModule(OldHitting.class).getState()) {
            GL11.glTranslated((double)((Float)OldHitting.itemPosX.get()).doubleValue(), (double)((Float)OldHitting.itemPosY.get()).doubleValue(), (double)((Float)OldHitting.itemPosZ.get()).doubleValue());
        }
        if (stack.func_190926_b()) {
            if (flag && !player.func_82150_aj()) {
                this.func_187456_a(p_187457_7_, p_187457_5_, enumhandside);
            }
        } else if (stack.func_77973_b() == Items.field_151098_aY) {
            if (flag && this.field_187468_e.func_190926_b()) {
                this.func_187463_a(p_187457_3_, p_187457_7_, p_187457_5_);
            } else {
                this.func_187465_a(p_187457_7_, enumhandside, p_187457_5_, stack);
            }
        } else {
            boolean flag1;
            boolean bl = flag1 = enumhandside == EnumHandSide.RIGHT;
            if (player.func_184587_cr() && player.func_184605_cv() > 0 && player.func_184600_cs() == hand) {
                int j = flag1 ? 1 : -1;
                switch (stack.func_77975_n()) {
                    case NONE: {
                        this.func_187459_b(enumhandside, p_187457_7_);
                        break;
                    }
                    case EAT: 
                    case DRINK: {
                        this.func_187454_a(p_187457_2_, enumhandside, stack);
                        this.func_187459_b(enumhandside, p_187457_7_);
                        break;
                    }
                    case BLOCK: {
                        this.func_187459_b(enumhandside, p_187457_7_);
                        break;
                    }
                    case BOW: {
                        this.func_187459_b(enumhandside, p_187457_7_);
                        GlStateManager.func_179109_b((float)((float)j * -0.2785682f), (float)0.18344387f, (float)0.15731531f);
                        GlStateManager.func_179114_b((float)-13.935f, (float)1.0f, (float)0.0f, (float)0.0f);
                        GlStateManager.func_179114_b((float)((float)j * 35.3f), (float)0.0f, (float)1.0f, (float)0.0f);
                        GlStateManager.func_179114_b((float)((float)j * -9.785f), (float)0.0f, (float)0.0f, (float)1.0f);
                        float f5 = (float)stack.func_77988_m() - ((float)this.field_78455_a.field_71439_g.func_184605_cv() - p_187457_2_ + 1.0f);
                        float f6 = f5 / 20.0f;
                        f6 = (f6 * f6 + f6 * 2.0f) / 3.0f;
                        if (f6 > 1.0f) {
                            f6 = 1.0f;
                        }
                        if (f6 > 0.1f) {
                            float f7 = MathHelper.func_76126_a((float)((f5 - 0.1f) * 1.3f));
                            float f3 = f6 - 0.1f;
                            float f4 = f7 * f3;
                            GlStateManager.func_179109_b((float)(f4 * 0.0f), (float)(f4 * 0.004f), (float)(f4 * 0.0f));
                        }
                        GlStateManager.func_179109_b((float)(f6 * 0.0f), (float)(f6 * 0.0f), (float)(f6 * 0.04f));
                        GlStateManager.func_179152_a((float)1.0f, (float)1.0f, (float)(1.0f + f6 * 0.2f));
                        GlStateManager.func_179114_b((float)((float)j * 45.0f), (float)0.0f, (float)-1.0f, (float)0.0f);
                    }
                }
            } else if (ot.getState()) {
                if (aura.getBlockingStatus() || this.field_78455_a.field_71474_y.field_74313_G.field_74513_e && (!((Boolean)ot.getOnlySword().get()).booleanValue() || this.field_187467_d.func_77973_b() instanceof ItemSword)) {
                    String z = (String)ot.getModeValue().get();
                    float f1 = abstractclientplayer.func_70678_g(p_187457_7_);
                    float var2 = 1.0f - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * p_187457_7_);
                    float var4 = this.field_78455_a.field_71439_g.func_70678_g(p_187457_7_);
                    float var15 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)var4) * (float)Math.PI));
                    switch (z) {
                        case "Strange": {
                            this.strange(p_187457_7_, p_187457_5_);
                            this.func_178103_d();
                            break;
                        }
                        case "Reverse": {
                            this.func_178096_b(p_187457_7_, p_187457_5_);
                            this.func_178103_d();
                            break;
                        }
                        case "ETB": {
                            Debug.ETB(enumhandside, p_187457_7_, p_187457_5_);
                            break;
                        }
                        case "MineCraft": {
                            Debug.func_178096_b(enumhandside, p_187457_7_, p_187457_5_);
                            break;
                        }
                        case "Test": {
                            Debug.Test(enumhandside, p_187457_7_, p_187457_5_);
                            break;
                        }
                        case "SigmaOld": {
                            Debug.sigmaold(enumhandside, p_187457_7_, p_187457_5_);
                            break;
                        }
                        case "Jello": {
                            Debug.jello(enumhandside, p_187457_7_, p_187457_5_);
                        }
                        case "Zoom": {
                            Debug.Zoom(enumhandside, p_187457_7_, p_187457_5_);
                            break;
                        }
                        case "SideDown": {
                            Debug.transformSideFirstPersonBlock(enumhandside, p_187457_7_, p_187457_5_);
                            break;
                        }
                        case "Push": {
                            Debug.Push(enumhandside, p_187457_7_, p_187457_5_);
                        }
                    }
                } else {
                    float f = -0.4f * MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)p_187457_5_) * (float)Math.PI));
                    float f1 = 0.2f * MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)p_187457_5_) * ((float)Math.PI * 2)));
                    float f2 = -0.2f * MathHelper.func_76126_a((float)(p_187457_5_ * (float)Math.PI));
                    int i = flag1 ? 1 : -1;
                    GlStateManager.func_179109_b((float)((float)i * f), (float)f1, (float)f2);
                    GlStateManager.func_179152_a((float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue(), (float)((Float)OldHitting.Scale.get()).floatValue());
                    this.func_187459_b(enumhandside, p_187457_7_);
                    this.func_187453_a(enumhandside, p_187457_5_);
                }
            } else {
                this.func_187459_b(enumhandside, p_187457_7_);
                this.func_187453_a(enumhandside, p_187457_5_);
            }
            if (ot.getState()) {
                if (stack.func_77973_b() instanceof ItemShield && this.field_187467_d.func_77973_b() instanceof ItemSword) {
                    GlStateManager.func_179121_F();
                    return;
                }
                this.func_187462_a((EntityLivingBase)player, stack, flag1 ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !flag1);
            } else {
                this.func_187462_a((EntityLivingBase)player, stack, flag1 ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, !flag1);
            }
        }
        GlStateManager.func_179121_F();
        if (LiquidBounce.moduleManager.getModule(OldHitting.class).getState()) {
            GL11.glTranslated((double)(-((Float)OldHitting.itemPosX.get()).doubleValue()), (double)(-((Float)OldHitting.itemPosY.get()).doubleValue()), (double)(-((Float)OldHitting.itemPosZ.get()).doubleValue()));
        }
    }

    @Inject(method={"renderFireInFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderFireInFirstPerson(CallbackInfo callbackInfo) {
        AntiBlind antiBlind = (AntiBlind)LiquidBounce.moduleManager.getModule(AntiBlind.class);
        if (antiBlind.getState() && ((Boolean)antiBlind.getFireEffect().get()).booleanValue()) {
            callbackInfo.cancel();
        }
    }
}

