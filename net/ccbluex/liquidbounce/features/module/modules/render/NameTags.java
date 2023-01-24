/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.renderer.entity.IRenderManager;
import net.ccbluex.liquidbounce.api.minecraft.util.IIChatComponent;
import net.ccbluex.liquidbounce.api.minecraft.util.ITimer;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.misc.AntiBot;
import net.ccbluex.liquidbounce.ui.font.AWTFontRenderer;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@ModuleInfo(name="NameTags", description="Changes the scale of the nametags so you can always read them.", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/NameTags;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "armorValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "borderValue", "botValue", "clearNamesValue", "distanceValue", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "healthValue", "pingValue", "scaleValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "onRender3D", "", "event", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "renderNameTag", "entity", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "tag", "", "Relaxed"})
public final class NameTags
extends Module {
    private final BoolValue healthValue = new BoolValue("Health", true);
    private final BoolValue pingValue = new BoolValue("Ping", true);
    private final BoolValue distanceValue = new BoolValue("Distance", false);
    private final BoolValue armorValue = new BoolValue("Armor", true);
    private final BoolValue clearNamesValue = new BoolValue("ClearNames", false);
    private final FontValue fontValue;
    private final BoolValue borderValue;
    private final FloatValue scaleValue;
    private final BoolValue botValue;

    @EventTarget
    public final void onRender3D(@NotNull Render3DEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        GL11.glPushAttrib((int)8192);
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)2848);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        for (IEntity entity : iWorldClient.getLoadedEntityList()) {
            String string;
            if (!EntityUtils.isSelected(entity, false) || AntiBot.isBot(entity.asEntityLivingBase()) && !((Boolean)this.botValue.get()).booleanValue()) continue;
            IEntityLivingBase iEntityLivingBase = entity.asEntityLivingBase();
            if (((Boolean)this.clearNamesValue.get()).booleanValue()) {
                IIChatComponent iIChatComponent = entity.getDisplayName();
                string = ColorUtils.stripColor(iIChatComponent != null ? iIChatComponent.getUnformattedText() : null);
                if (string == null) {
                    continue;
                }
            } else {
                IIChatComponent iIChatComponent = entity.getDisplayName();
                if (iIChatComponent == null) {
                    continue;
                }
                string = iIChatComponent.getUnformattedText();
            }
            this.renderNameTag(iEntityLivingBase, string);
        }
        GL11.glPopMatrix();
        GL11.glPopAttrib();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private final void renderNameTag(IEntityLivingBase entity, String tag) {
        String distanceText;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        IFontRenderer fontRenderer = (IFontRenderer)this.fontValue.get();
        boolean bot = AntiBot.isBot(entity);
        String nameColor = bot ? "\u00a73" : (entity.isInvisible() ? "\u00a76" : (entity.isSneaking() ? "\u00a74" : "\u00a77"));
        int ping = MinecraftInstance.classProvider.isEntityPlayer(entity) ? PlayerExtensionKt.getPing(entity.asEntityPlayer()) : 0;
        String string = distanceText = (Boolean)this.distanceValue.get() != false ? "\u00a77" + MathKt.roundToInt(thePlayer.getDistanceToEntity(entity)) + "m " : "";
        String pingText = (Boolean)this.pingValue.get() != false && MinecraftInstance.classProvider.isEntityPlayer(entity) ? (ping > 200 ? "\u00a7c" : (ping > 100 ? "\u00a7e" : "\u00a7a")) + ping + "ms \u00a77" : "";
        String healthText = (Boolean)this.healthValue.get() != false ? "\u00a77\u00a7c " + (int)entity.getHealth() + " HP" : "";
        String botText = bot ? " \u00a7c\u00a7lBot" : "";
        String text = distanceText + pingText + nameColor + tag + healthText + botText;
        GL11.glPushMatrix();
        ITimer timer = MinecraftInstance.mc.getTimer();
        IRenderManager renderManager = MinecraftInstance.mc.getRenderManager();
        GL11.glTranslated((double)(entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosX()), (double)(entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosY() + (double)entity.getEyeHeight() + 0.55), (double)(entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)timer.getRenderPartialTicks() - renderManager.getRenderPosZ()));
        GL11.glRotatef((float)(-MinecraftInstance.mc.getRenderManager().getPlayerViewY()), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)MinecraftInstance.mc.getRenderManager().getPlayerViewX(), (float)1.0f, (float)0.0f, (float)0.0f);
        float distance = thePlayer.getDistanceToEntity(entity) * 0.25f;
        if (distance < 1.0f) {
            distance = 1.0f;
        }
        float scale = distance / 100.0f * ((Number)this.scaleValue.get()).floatValue();
        GL11.glScalef((float)(-scale), (float)(-scale), (float)scale);
        AWTFontRenderer.Companion.setAssumeNonVolatile(true);
        float width = (float)fontRenderer.getStringWidth(text) * 0.5f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        if (((Boolean)this.borderValue.get()).booleanValue()) {
            RenderUtils.quickDrawBorderedRect(-width - 2.0f, -2.0f, width + 4.0f, (float)fontRenderer.getFontHeight() + 2.0f, 2.0f, new Color(255, 255, 255, 90).getRGB(), Integer.MIN_VALUE);
        } else {
            RenderUtils.quickDrawRect(-width - 2.0f, -2.0f, width + 4.0f, (float)fontRenderer.getFontHeight() + 2.0f, Integer.MIN_VALUE);
        }
        GL11.glEnable((int)3553);
        fontRenderer.drawString(text, 1.0f + -width, Intrinsics.areEqual(fontRenderer, Fonts.minecraftFont) ? 1.0f : 1.5f, 0xFFFFFF, true);
        AWTFontRenderer.Companion.setAssumeNonVolatile(false);
        if (((Boolean)this.armorValue.get()).booleanValue() && MinecraftInstance.classProvider.isEntityPlayer(entity)) {
            int[] indices;
            MinecraftInstance.mc.getRenderItem().setZLevel(-147.0f);
            for (int index : indices = new int[]{0, 1, 2, 3, 5, 4}) {
                IItemStack equipmentInSlot;
                if (entity.getEquipmentInSlot(index) == null) {
                    continue;
                }
                MinecraftInstance.mc.getRenderItem().renderItemAndEffectIntoGUI(equipmentInSlot, -50 + index * 20, -22);
            }
            GlStateManager.func_179141_d();
            GlStateManager.func_179084_k();
            GlStateManager.func_179098_w();
        }
        GL11.glPopMatrix();
    }

    public NameTags() {
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.borderValue = new BoolValue("Border", true);
        this.scaleValue = new FloatValue("Scale", 1.0f, 1.0f, 4.0f);
        this.botValue = new BoolValue("Bots", true);
    }
}

