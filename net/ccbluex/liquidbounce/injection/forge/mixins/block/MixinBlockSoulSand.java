/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSoulSand
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.block;

import java.util.Objects;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.movement.NoSlow;
import net.minecraft.block.BlockSoulSand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={BlockSoulSand.class})
public class MixinBlockSoulSand {
    @Inject(method={"onEntityCollidedWithBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onEntityCollidedWithBlock(CallbackInfo callbackInfo) {
        NoSlow noSlow = (NoSlow)LiquidBounce.moduleManager.getModule(NoSlow.class);
        if (Objects.requireNonNull(noSlow).getState() && ((Boolean)noSlow.getSoulsandValue().get()).booleanValue()) {
            callbackInfo.cancel();
        }
    }
}

