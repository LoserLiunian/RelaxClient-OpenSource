/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.timer.TimeUtils;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="PlayerHealthSend", description="Debug Health", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/PlayerHealthSend;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "healthData", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "timer", "Lnet/ccbluex/liquidbounce/utils/timer/TimeUtils;", "handleEvents", "", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class PlayerHealthSend
extends Module {
    private final TimeUtils timer = new TimeUtils();
    private final HashMap<Integer, Float> healthData = new HashMap();

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        for (IEntity entity : iWorldClient.getLoadedEntityList()) {
            if (!MinecraftInstance.classProvider.isEntityLivingBase(event) || !EntityUtils.isSelected(entity, true)) continue;
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            Integer n = iEntityPlayerSP.getEntityId();
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            Float f = this.healthData.getOrDefault(n, Float.valueOf(iEntityPlayerSP2.getMaxHealth()));
            Intrinsics.checkExpressionValueIsNotNull(f, "healthData.getOrDefault(\u2026mc.thePlayer!!.maxHealth)");
            float lastHealth = ((Number)f).floatValue();
            Map map = this.healthData;
            IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP3 == null) {
                Intrinsics.throwNpe();
            }
            Integer n2 = iEntityPlayerSP3.getEntityId();
            IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP4 == null) {
                Intrinsics.throwNpe();
            }
            map.put(n2, Float.valueOf(iEntityPlayerSP4.getHealth()));
            IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP5 == null) {
                Intrinsics.throwNpe();
            }
            if (lastHealth == iEntityPlayerSP5.getHealth()) continue;
            IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP6 == null) {
                Intrinsics.throwNpe();
            }
            if (lastHealth > iEntityPlayerSP6.getHealth()) {
                StringBuilder stringBuilder = new StringBuilder().append("\u00a7c\u6263\u9664\u8840\u91cf\u00a7a");
                IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP7 == null) {
                    Intrinsics.throwNpe();
                }
                StringBuilder stringBuilder2 = stringBuilder.append(lastHealth - iEntityPlayerSP7.getHealth()).append("HP").append(" \u00a7f| ").append("\u00a7c\u5f53\u524d\u8840\u91cf\u00a7a");
                IEntityPlayerSP iEntityPlayerSP8 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP8 == null) {
                    Intrinsics.throwNpe();
                }
                ClientUtils.displayChatMessage(stringBuilder2.append(iEntityPlayerSP8.getHealth()).append("HP").toString());
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder().append("\u00a7c\u589e\u52a0\u8840\u91cf\u00a7a");
            IEntityPlayerSP iEntityPlayerSP9 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP9 == null) {
                Intrinsics.throwNpe();
            }
            float f2 = lastHealth - iEntityPlayerSP9.getHealth();
            StringBuilder stringBuilder3 = stringBuilder;
            boolean bl = false;
            float f3 = Math.abs(f2);
            StringBuilder stringBuilder4 = stringBuilder3.append(f3).append("HP").append(" \u00a7f| ").append("\u00a7c\u5f53\u524d\u8840\u91cf\u00a7a");
            IEntityPlayerSP iEntityPlayerSP10 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP10 == null) {
                Intrinsics.throwNpe();
            }
            ClientUtils.displayChatMessage(stringBuilder4.append(iEntityPlayerSP10.getHealth()).append("HP").toString());
        }
        if (this.timer.delay(220.0f)) {
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP.getHealth() < 10.0f) {
                LiquidBounce.INSTANCE.getHud().addNotification(new Notification("Waring", "Low hp ", NotifyType.WARNING, 0, 0, 24, null));
            }
        }
        this.timer.reset();
    }

    @Override
    public boolean handleEvents() {
        return true;
    }
}

