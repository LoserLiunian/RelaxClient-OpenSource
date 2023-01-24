/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.misc.AntiBot;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@ModuleInfo(name="Tracers", description="Draws a line to targets around you.", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/Tracers;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "botValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "colorBlueValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "colorGreenValue", "colorMode", "Lnet/ccbluex/liquidbounce/value/ListValue;", "colorRedValue", "thicknessValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "drawTraces", "", "entity", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "color", "Ljava/awt/Color;", "onRender3D", "event", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "Relaxed"})
public final class Tracers
extends Module {
    private final ListValue colorMode = new ListValue("Color", new String[]{"Custom", "DistanceColor", "Rainbow"}, "Custom");
    private final FloatValue thicknessValue = new FloatValue("Thickness", 2.0f, 1.0f, 5.0f);
    private final IntegerValue colorRedValue = new IntegerValue("R", 0, 0, 255);
    private final IntegerValue colorGreenValue = new IntegerValue("G", 160, 0, 255);
    private final IntegerValue colorBlueValue = new IntegerValue("B", 255, 0, 255);
    private final BoolValue botValue = new BoolValue("Bots", true);

    @EventTarget
    public final void onRender3D(@NotNull Render3DEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)((Number)this.thicknessValue.get()).floatValue());
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glBegin((int)1);
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        for (IEntity entity : iWorldClient.getLoadedEntityList()) {
            String colorMode;
            if (!MinecraftInstance.classProvider.isEntityLivingBase(entity) || !((Boolean)this.botValue.get()).booleanValue() && AntiBot.isBot(entity.asEntityLivingBase()) || !(Intrinsics.areEqual(entity, thePlayer) ^ true) || !EntityUtils.isSelected(entity, false)) continue;
            int dist = (int)(thePlayer.getDistanceToEntity(entity) * (float)2);
            if (dist > 255) {
                dist = 255;
            }
            String string = (String)this.colorMode.get();
            boolean bl = false;
            String string2 = string;
            if (string2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            Intrinsics.checkExpressionValueIsNotNull(string2.toLowerCase(), "(this as java.lang.String).toLowerCase()");
            Color color = MinecraftInstance.classProvider.isEntityPlayer(entity) && PlayerExtensionKt.isClientFriend(entity.asEntityPlayer()) ? new Color(0, 0, 255, 150) : (Intrinsics.areEqual(colorMode, "custom") ? new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue(), 150) : (Intrinsics.areEqual(colorMode, "distancecolor") ? new Color(255 - dist, dist, 0, 150) : (Intrinsics.areEqual(colorMode, "rainbow") ? ColorUtils.rainbow() : new Color(255, 255, 255, 150))));
            this.drawTraces(entity, color);
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private final void drawTraces(IEntity entity, Color color) {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosZ();
        WVec3 eyeVector = new WVec3(0.0, 0.0, 1.0).rotatePitch((float)(-Math.toRadians(thePlayer.getRotationPitch()))).rotateYaw((float)(-Math.toRadians(thePlayer.getRotationYaw())));
        RenderUtils.glColor(color);
        GL11.glVertex3d((double)eyeVector.getXCoord(), (double)((double)thePlayer.getEyeHeight() + eyeVector.getYCoord()), (double)eyeVector.getZCoord());
        GL11.glVertex3d((double)x, (double)y, (double)z);
        GL11.glVertex3d((double)x, (double)y, (double)z);
        GL11.glVertex3d((double)x, (double)(y + (double)entity.getHeight()), (double)z);
    }
}

