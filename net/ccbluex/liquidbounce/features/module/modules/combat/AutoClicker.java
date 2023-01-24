/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.combat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.RandomUtils;
import net.ccbluex.liquidbounce.utils.timer.TimeUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="AutoClicker", description="Constantly clicks when holding down a mouse button.", category=ModuleCategory.COMBAT)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/AutoClicker;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "jitterValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "leftDelay", "", "leftLastSwing", "leftValue", "maxCPSValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "minCPSValue", "rightDelay", "rightLastSwing", "rightValue", "onRender", "", "event", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class AutoClicker
extends Module {
    private final IntegerValue maxCPSValue = new IntegerValue(this, "MaxCPS", 8, 1, 20){
        final /* synthetic */ AutoClicker this$0;

        protected void onChanged(int oldValue, int newValue) {
            int minCPS2 = ((Number)AutoClicker.access$getMinCPSValue$p(this.this$0).get()).intValue();
            if (minCPS2 > newValue) {
                this.set(minCPS2);
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final IntegerValue minCPSValue = new IntegerValue(this, "MinCPS", 5, 1, 20){
        final /* synthetic */ AutoClicker this$0;

        protected void onChanged(int oldValue, int newValue) {
            int maxCPS2 = ((Number)AutoClicker.access$getMaxCPSValue$p(this.this$0).get()).intValue();
            if (maxCPS2 < newValue) {
                this.set(maxCPS2);
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final BoolValue rightValue = new BoolValue("Right", true);
    private final BoolValue leftValue = new BoolValue("Left", true);
    private final BoolValue jitterValue = new BoolValue("Jitter", false);
    private long rightDelay = TimeUtils.randomClickDelay(((Number)this.minCPSValue.get()).intValue(), ((Number)this.maxCPSValue.get()).intValue());
    private long rightLastSwing;
    private long leftDelay = TimeUtils.randomClickDelay(((Number)this.minCPSValue.get()).intValue(), ((Number)this.maxCPSValue.get()).intValue());
    private long leftLastSwing;

    @EventTarget
    public final void onRender(@NotNull Render3DEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (MinecraftInstance.mc.getGameSettings().getKeyBindAttack().isKeyDown() && ((Boolean)this.leftValue.get()).booleanValue() && System.currentTimeMillis() - this.leftLastSwing >= this.leftDelay && MinecraftInstance.mc.getPlayerController().getCurBlockDamageMP() == 0.0f) {
            MinecraftInstance.mc.getGameSettings().getKeyBindAttack().onTick(MinecraftInstance.mc.getGameSettings().getKeyBindAttack().getKeyCode());
            this.leftLastSwing = System.currentTimeMillis();
            this.leftDelay = TimeUtils.randomClickDelay(((Number)this.minCPSValue.get()).intValue(), ((Number)this.maxCPSValue.get()).intValue());
        }
        if (MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().isKeyDown()) {
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (!iEntityPlayerSP.isUsingItem() && ((Boolean)this.rightValue.get()).booleanValue() && System.currentTimeMillis() - this.rightLastSwing >= this.rightDelay) {
                MinecraftInstance.mc.getGameSettings().getKeyBindAttack().onTick(MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().getKeyCode());
                this.rightLastSwing = System.currentTimeMillis();
                this.rightDelay = TimeUtils.randomClickDelay(((Number)this.minCPSValue.get()).intValue(), ((Number)this.maxCPSValue.get()).intValue());
            }
        }
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (((Boolean)this.jitterValue.get()).booleanValue() && (((Boolean)this.leftValue.get()).booleanValue() && MinecraftInstance.mc.getGameSettings().getKeyBindAttack().isKeyDown() && MinecraftInstance.mc.getPlayerController().getCurBlockDamageMP() == 0.0f || ((Boolean)this.rightValue.get()).booleanValue() && MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().isKeyDown() && !thePlayer.isUsingItem())) {
            if (Random.Default.nextBoolean()) {
                IEntityPlayerSP iEntityPlayerSP2 = thePlayer;
                iEntityPlayerSP2.setRotationYaw(iEntityPlayerSP2.getRotationYaw() + (Random.Default.nextBoolean() ? -RandomUtils.INSTANCE.nextFloat(0.0f, 1.0f) : RandomUtils.INSTANCE.nextFloat(0.0f, 1.0f)));
            }
            if (Random.Default.nextBoolean()) {
                IEntityPlayerSP iEntityPlayerSP3 = thePlayer;
                iEntityPlayerSP3.setRotationPitch(iEntityPlayerSP3.getRotationPitch() + (Random.Default.nextBoolean() ? -RandomUtils.INSTANCE.nextFloat(0.0f, 1.0f) : RandomUtils.INSTANCE.nextFloat(0.0f, 1.0f)));
                if (thePlayer.getRotationPitch() > (float)90) {
                    thePlayer.setRotationPitch(90.0f);
                } else if (thePlayer.getRotationPitch() < (float)-90) {
                    thePlayer.setRotationPitch(-90.0f);
                }
            }
        }
    }

    public static final /* synthetic */ IntegerValue access$getMinCPSValue$p(AutoClicker $this) {
        return $this.minCPSValue;
    }

    public static final /* synthetic */ IntegerValue access$getMaxCPSValue$p(AutoClicker $this) {
        return $this.maxCPSValue;
    }
}

