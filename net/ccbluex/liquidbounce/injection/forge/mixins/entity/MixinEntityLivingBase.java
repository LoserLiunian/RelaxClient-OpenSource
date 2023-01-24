/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.common.ForgeHooks
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.entity;

import java.util.Objects;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.JumpEvent;
import net.ccbluex.liquidbounce.event.StrafeEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.AirJump;
import net.ccbluex.liquidbounce.features.module.modules.movement.LiquidWalk;
import net.ccbluex.liquidbounce.features.module.modules.movement.NoJumpDelay;
import net.ccbluex.liquidbounce.features.module.modules.render.AntiBlind;
import net.ccbluex.liquidbounce.features.module.modules.render.OldHitting;
import net.ccbluex.liquidbounce.injection.forge.mixins.entity.MixinEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityLivingBase.class})
public abstract class MixinEntityLivingBase
extends MixinEntity {
    @Shadow
    public int field_184628_bn;
    @Shadow
    protected boolean field_70703_bu;
    @Shadow
    private int field_70773_bE;

    @Shadow
    public abstract boolean func_184587_cr();

    @Shadow
    public abstract ItemStack func_184607_cu();

    @Shadow
    protected abstract float func_175134_bD();

    @Shadow
    public abstract PotionEffect func_70660_b(Potion var1);

    @Shadow
    public abstract boolean func_70644_a(Potion var1);

    @Shadow
    public void func_70636_d() {
    }

    @Override
    @Shadow
    protected abstract void func_184231_a(double var1, boolean var3, IBlockState var4, BlockPos var5);

    @Shadow
    public abstract float func_110143_aJ();

    @Shadow
    public abstract ItemStack func_184586_b(EnumHand var1);

    @Shadow
    protected abstract void func_70626_be();

    @Shadow
    protected abstract void func_70629_bd();

    @Shadow
    public abstract boolean func_184613_cA();

    @Shadow
    public abstract int func_184605_cv();

    @Overwrite
    protected void func_70664_aZ() {
        JumpEvent jumpEvent = new JumpEvent(this.func_175134_bD());
        LiquidBounce.eventManager.callEvent(jumpEvent);
        if (jumpEvent.isCancelled()) {
            return;
        }
        this.field_70181_x = jumpEvent.getMotion();
        if (this.func_70644_a(MobEffects.field_76430_j)) {
            this.field_70181_x += (double)((float)(this.func_70660_b(MobEffects.field_76430_j).func_76458_c() + 1) * 0.1f);
        }
        if (this.func_70051_ag()) {
            float f = this.field_70177_z * ((float)Math.PI / 180);
            this.field_70159_w -= (double)(MathHelper.func_76126_a((float)f) * 0.2f);
            this.field_70179_y += (double)(MathHelper.func_76134_b((float)f) * 0.2f);
        }
        this.field_70160_al = true;
        ForgeHooks.onLivingJump((EntityLivingBase)((EntityLivingBase)this));
    }

    @Inject(method={"onLivingUpdate"}, at={@At(value="HEAD")})
    private void headLiving(CallbackInfo callbackInfo) {
        if (Objects.requireNonNull(LiquidBounce.moduleManager.getModule(NoJumpDelay.class)).getState()) {
            this.field_70773_bE = 0;
        }
    }

    @Inject(method={"onLivingUpdate"}, at={@At(value="FIELD", target="Lnet/minecraft/entity/EntityLivingBase;isJumping:Z", ordinal=1)})
    private void onJumpSection(CallbackInfo callbackInfo) {
        LiquidWalk liquidWalk;
        if (Objects.requireNonNull(LiquidBounce.moduleManager.getModule(AirJump.class)).getState() && this.field_70703_bu && this.field_70773_bE == 0) {
            this.func_70664_aZ();
            this.field_70773_bE = 10;
        }
        if (Objects.requireNonNull(liquidWalk = (LiquidWalk)LiquidBounce.moduleManager.getModule(LiquidWalk.class)).getState() && !this.field_70703_bu && !this.func_70093_af() && this.func_70090_H() && ((String)liquidWalk.getModeValue().get()).equalsIgnoreCase("Swim")) {
            this.func_70629_bd();
        }
    }

    @Inject(method={"getLook"}, at={@At(value="HEAD")}, cancellable=true)
    private void getLook(CallbackInfoReturnable<Vec3d> callbackInfoReturnable) {
        if ((EntityLivingBase)this instanceof EntityPlayerSP) {
            callbackInfoReturnable.setReturnValue(this.func_174806_f(this.field_70125_A, this.field_70177_z));
        }
    }

    @Inject(method={"isPotionActive(Lnet/minecraft/potion/Potion;)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void isPotionActive(Potion p_isPotionActive_1_, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        AntiBlind antiBlind = (AntiBlind)LiquidBounce.moduleManager.getModule(AntiBlind.class);
        if ((p_isPotionActive_1_ == MobEffects.field_76431_k || p_isPotionActive_1_ == MobEffects.field_76440_q) && Objects.requireNonNull(antiBlind).getState() && ((Boolean)antiBlind.getConfusionEffect().get()).booleanValue()) {
            callbackInfoReturnable.setReturnValue(false);
        }
    }

    @Overwrite
    private int func_82166_i() {
        OldHitting animations;
        int speed;
        int n = this.func_70644_a(MobEffects.field_76422_e) ? 6 - (1 + this.func_70660_b(MobEffects.field_76422_e).func_76458_c()) : (speed = this.func_70644_a(MobEffects.field_76419_f) ? 6 + (1 + this.func_70660_b(MobEffects.field_76419_f).func_76458_c()) * 2 : 6);
        if (this.equals(Minecraft.func_71410_x().field_71439_g) && (animations = (OldHitting)LiquidBounce.moduleManager.getModule(OldHitting.class)).getState()) {
            speed *= ((Integer)OldHitting.SpeedSwing.get()).intValue();
        }
        return speed;
    }

    @Inject(method={"moveRelative"}, at={@At(value="HEAD")}, cancellable=true)
    private void handleRotations(float strafe, float up, float forward, float friction, CallbackInfo callbackInfo) {
        if (this != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        StrafeEvent strafeEvent = new StrafeEvent(strafe, forward, friction);
        LiquidBounce.eventManager.callEvent(strafeEvent);
        if (strafeEvent.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

