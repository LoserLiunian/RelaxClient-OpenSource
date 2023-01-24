/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render2DEvent;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.render.shader.FramebufferShader;
import net.ccbluex.liquidbounce.utils.render.shader.shaders.GlowShader;
import net.ccbluex.liquidbounce.utils.render.shader.shaders.OutlineShader;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="ProphuntESP", description="Allows you to see disguised players in PropHunt.", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u001aH\u0007R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/ProphuntESP;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "blocks", "", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "", "getBlocks", "()Ljava/util/Map;", "colorBlueValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "colorGreenValue", "colorRainbow", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "colorRedValue", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "shaderGlowRadius", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "shaderOutlineRadius", "onDisable", "", "onRender2D", "event", "Lnet/ccbluex/liquidbounce/event/Render2DEvent;", "onRender3D", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "Relaxed"})
public final class ProphuntESP
extends Module {
    @NotNull
    private final Map<WBlockPos, Long> blocks = new HashMap();
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Box", "OtherBox", "ShaderOutline", "ShaderGlow"}, "OtherBox");
    private final FloatValue shaderOutlineRadius = new FloatValue("ShaderOutline-Radius", 1.35f, 1.0f, 2.0f);
    private final FloatValue shaderGlowRadius = new FloatValue("ShaderGlow-Radius", 2.3f, 2.0f, 3.0f);
    private final IntegerValue colorRedValue = new IntegerValue("R", 0, 0, 255);
    private final IntegerValue colorGreenValue = new IntegerValue("G", 90, 0, 255);
    private final IntegerValue colorBlueValue = new IntegerValue("B", 255, 0, 255);
    private final BoolValue colorRainbow = new BoolValue("Rainbow", false);

    @NotNull
    public final Map<WBlockPos, Long> getBlocks() {
        return this.blocks;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onDisable() {
        Map<WBlockPos, Long> map = this.blocks;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (map) {
            boolean bl3 = false;
            this.blocks.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventTarget
    public final void onRender3D(@Nullable Render3DEvent event) {
        String mode = (String)this.modeValue.get();
        Color color = (Boolean)this.colorRainbow.get() != false ? ColorUtils.rainbow() : new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue());
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        for (IEntity entity : iWorldClient.getLoadedEntityList()) {
            if (!StringsKt.equals(mode, "Box", true) || !StringsKt.equals(mode, "OtherBox", true)) break;
            if (!MinecraftInstance.classProvider.isEntityFallingBlock(entity)) continue;
            RenderUtils.drawEntityBox(entity, color, StringsKt.equals(mode, "Box", true));
        }
        Map<WBlockPos, Long> map = this.blocks;
        boolean bl = false;
        boolean bl2 = false;
        synchronized (map) {
            boolean bl3 = false;
            Iterator<Map.Entry<WBlockPos, Long>> iterator2 = this.blocks.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry<WBlockPos, Long> entry = iterator2.next();
                if (System.currentTimeMillis() - ((Number)entry.getValue()).longValue() > 2000L) {
                    iterator2.remove();
                    continue;
                }
                RenderUtils.drawBlockBox(entry.getKey(), color, StringsKt.equals(mode, "Box", true));
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @EventTarget
    public final void onRender2D(@NotNull Render2DEvent event) {
        Color color;
        FramebufferShader framebufferShader;
        String mode;
        Intrinsics.checkParameterIsNotNull(event, "event");
        switch (mode = (String)this.modeValue.get()) {
            case "ShaderOutline": {
                framebufferShader = OutlineShader.OUTLINE_SHADER;
                break;
            }
            case "ShaderGlow": {
                framebufferShader = GlowShader.GLOW_SHADER;
                break;
            }
            default: {
                framebufferShader = null;
            }
        }
        if (framebufferShader == null) {
            return;
        }
        FramebufferShader shader = framebufferShader;
        shader.startDraw(event.getPartialTicks());
        try {
            IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
            if (iWorldClient == null) {
                Intrinsics.throwNpe();
            }
            for (IEntity entity : iWorldClient.getLoadedEntityList()) {
                if (!MinecraftInstance.classProvider.isEntityFallingBlock(entity)) continue;
                MinecraftInstance.mc.getRenderManager().renderEntityStatic(entity, MinecraftInstance.mc.getTimer().getRenderPartialTicks(), true);
            }
        }
        catch (Exception ex) {
            ClientUtils.getLogger().error("An error occurred while rendering all entities for shader esp", (Throwable)ex);
        }
        Color color2 = color = (Boolean)this.colorRainbow.get() != false ? ColorUtils.rainbow() : new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue());
        float radius = StringsKt.equals(mode, "ShaderOutline", true) ? ((Number)this.shaderOutlineRadius.get()).floatValue() : (StringsKt.equals(mode, "ShaderGlow", true) ? ((Number)this.shaderGlowRadius.get()).floatValue() : 1.0f);
        shader.stopDraw(color, radius, 1.0f);
    }
}

