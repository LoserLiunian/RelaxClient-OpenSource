/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.color.Gident;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="TargetHud")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J&\u0010(\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\b\u0010.\u001a\u00020/H\u0016J \u00100\u001a\u00020$2\u0006\u00101\u001a\u0002022\u0006\u0010&\u001a\u00020-2\u0006\u0010'\u001a\u00020-H\u0002J \u00103\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J \u00104\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J \u00105\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J \u00106\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\n \u0014*\u0004\u0018\u00010\u00130\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/TargetHud;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "()V", "animProgress", "", "getAnimProgress", "()F", "setAnimProgress", "(F)V", "blur", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "cfont", "decimalFormat", "Ljava/text/DecimalFormat;", "easingHealth", "fadeSpeed", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "fadeSpeed2", "fontRenderer", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;", "kotlin.jvm.PlatformType", "getFontRenderer", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;", "setFontRenderer", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IFontRenderer;)V", "mainTarget", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "getMainTarget", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "setMainTarget", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;)V", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "outline", "shadow", "astro", "", "target", "width", "height", "calculateCompensation", "current", "delta", "", "speed", "", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "drawHead", "skin", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IResourceLocation;", "liquidbounce", "nameless", "novoline", "wtf", "Relaxed"})
public final class TargetHud
extends Element {
    private final ListValue modeValue = new ListValue("Style", new String[]{"Novoline", "LiquidBounce", "Astro", "WTF", "Nameless"}, "LiquidBounce");
    private final BoolValue shadow = new BoolValue("Shadow", true);
    private final BoolValue blur = new BoolValue("Blur ", true);
    private final BoolValue cfont = new BoolValue("CFont", false);
    private final BoolValue outline = new BoolValue("Outline", false);
    private final DecimalFormat decimalFormat = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));
    private final FloatValue fadeSpeed = new FloatValue("HP-FadeSpeed", 2.0f, 1.0f, 9.0f);
    private final FloatValue fadeSpeed2 = new FloatValue("FadeSpeed", 2.0f, 1.0f, 9.0f);
    private float easingHealth;
    private IFontRenderer fontRenderer = Fonts.font40;
    @Nullable
    private IEntityLivingBase mainTarget;
    private float animProgress;

    public final IFontRenderer getFontRenderer() {
        return this.fontRenderer;
    }

    public final void setFontRenderer(IFontRenderer iFontRenderer) {
        this.fontRenderer = iFontRenderer;
    }

    @Nullable
    public final IEntityLivingBase getMainTarget() {
        return this.mainTarget;
    }

    public final void setMainTarget(@Nullable IEntityLivingBase iEntityLivingBase) {
        this.mainTarget = iEntityLivingBase;
    }

    public final float getAnimProgress() {
        return this.animProgress;
    }

    public final void setAnimProgress(float f) {
        this.animProgress = f;
    }

    public final float calculateCompensation(float target, float current, long delta, int speed) {
        float current2 = current;
        long delta2 = delta;
        float diff = current2 - target;
        if (delta2 < 1L) {
            delta2 = 1L;
        }
        double xD = 0.0;
        if (diff > (float)speed) {
            xD = (double)((long)speed * delta2 / 16L) < 0.25 ? 0.5 : (double)((long)speed * delta2 / 16L);
            if ((current2 = (float)((double)current2 - xD)) < target) {
                current2 = target;
            }
        } else if (diff < (float)(-speed)) {
            xD = (double)((long)speed * delta2 / 16L) < 0.25 ? 0.5 : (double)((long)speed * delta2 / 16L);
            if ((current2 = (float)((double)current2 + xD)) > target) {
                current2 = target;
            }
        } else {
            current2 = target;
        }
        return current2;
    }

    @Override
    @NotNull
    public Border drawElement() {
        String p1;
        boolean bl;
        this.fontRenderer = (Boolean)this.cfont.get() != false ? Fonts.font35 : MinecraftInstance.mc.getFontRendererObj();
        Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
        }
        IEntityLivingBase kaTarget = ((KillAura)module).getTarget();
        IEntityLivingBase actualTarget = kaTarget != null ? kaTarget : (IEntityLivingBase)(MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen()) ? MinecraftInstance.mc.getThePlayer() : null);
        float width = 80.0f;
        String string = (String)this.modeValue.get();
        boolean bl2 = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        if (string3.equals("novoline")) {
            width = 80.0f;
        } else {
            string = (String)this.modeValue.get();
            bl2 = false;
            String string4 = string;
            if (string4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string5 = string4.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(string5, "(this as java.lang.String).toLowerCase()");
            if (string5.equals("astro")) {
                width = 90.0f;
            } else {
                string = (String)this.modeValue.get();
                bl2 = false;
                String string6 = string;
                if (string6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string7 = string6.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(string7, "(this as java.lang.String).toLowerCase()");
                if (string7.equals("wtf")) {
                    width = 100.0f;
                } else {
                    string = (String)this.modeValue.get();
                    bl2 = false;
                    String string8 = string;
                    if (string8 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string9 = string8.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string9, "(this as java.lang.String).toLowerCase()");
                    width = string9.equals("nameless") ? 100.0f : 128.0f;
                }
            }
        }
        float height = 34.0f;
        String string10 = (String)this.modeValue.get();
        boolean bl3 = false;
        String string11 = string10;
        if (string11 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string12 = string11.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string12, "(this as java.lang.String).toLowerCase()");
        if (string12.equals("novoline")) {
            height = 34.0f;
        } else {
            string10 = (String)this.modeValue.get();
            bl3 = false;
            String string13 = string10;
            if (string13 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string14 = string13.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(string14, "(this as java.lang.String).toLowerCase()");
            if (string14.equals("astro")) {
                height = 40.0f;
            } else {
                string10 = (String)this.modeValue.get();
                bl3 = false;
                String string15 = string10;
                if (string15 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string16 = string15.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(string16, "(this as java.lang.String).toLowerCase()");
                if (string16.equals("wtf")) {
                    height = 44.0f;
                } else {
                    string10 = (String)this.modeValue.get();
                    bl3 = false;
                    String string17 = string10;
                    if (string17 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string18 = string17.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string18, "(this as java.lang.String).toLowerCase()");
                    height = string18.equals("nameless") ? 36.0f : 36.0f;
                }
            }
        }
        float num = actualTarget != null ? 0.0f : 1.0f;
        TargetHud targetHud = this;
        float f = 2.0f;
        float f2 = 10.0f - ((Number)this.fadeSpeed2.get()).floatValue();
        float f3 = num - this.animProgress;
        float f4 = targetHud.animProgress;
        TargetHud targetHud2 = targetHud;
        boolean bl4 = false;
        float f5 = (float)Math.pow(f, f2);
        targetHud2.animProgress = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
        this.animProgress = RangesKt.coerceIn(this.animProgress, 0.0f, 1.0f);
        if (actualTarget != null) {
            this.mainTarget = actualTarget;
        } else if (this.animProgress >= 1.0f) {
            this.mainTarget = null;
        }
        if (this.mainTarget == null) {
            this.easingHealth = 0.0f;
            return new Border(0.0f, 0.0f, width, height);
        }
        String string19 = (String)this.modeValue.get();
        boolean bl5 = false;
        String string20 = string19;
        if (string20 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string21 = string20.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string21, "(this as java.lang.String).toLowerCase()");
        if (string21.equals("novoline")) {
            int n;
            int n2 = 38;
            IEntityLivingBase iEntityLivingBase = this.mainTarget;
            if (iEntityLivingBase == null) {
                Intrinsics.throwNpe();
            }
            String string22 = iEntityLivingBase.getName();
            if (string22 != null) {
                string19 = string22;
                IFontRenderer iFontRenderer = MinecraftInstance.mc.getFontRendererObj();
                int n3 = n2;
                bl4 = false;
                bl = false;
                p1 = string19;
                boolean bl6 = false;
                int n4 = iFontRenderer.getStringWidth(p1);
                n2 = n3;
                n = n4;
            } else {
                n = 0;
            }
            width = RangesKt.coerceAtLeast(n2 + n, 80);
        } else {
            string19 = (String)this.modeValue.get();
            bl5 = false;
            String string23 = string19;
            if (string23 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string24 = string23.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(string24, "(this as java.lang.String).toLowerCase()");
            if (string24.equals("astro")) {
                int n;
                int n5 = 38;
                IEntityLivingBase iEntityLivingBase = this.mainTarget;
                if (iEntityLivingBase == null) {
                    Intrinsics.throwNpe();
                }
                String string25 = iEntityLivingBase.getName();
                if (string25 != null) {
                    string19 = string25;
                    IFontRenderer iFontRenderer = MinecraftInstance.mc.getFontRendererObj();
                    int n6 = n5;
                    bl4 = false;
                    bl = false;
                    p1 = string19;
                    boolean bl7 = false;
                    int n7 = iFontRenderer.getStringWidth(p1);
                    n5 = n6;
                    n = n7;
                } else {
                    n = 0;
                }
                width = RangesKt.coerceAtLeast(n5 + n, 90);
            } else {
                string19 = (String)this.modeValue.get();
                bl5 = false;
                String string26 = string19;
                if (string26 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string27 = string26.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(string27, "(this as java.lang.String).toLowerCase()");
                if (string27.equals("wtf")) {
                    int n;
                    int n8 = 38;
                    IEntityLivingBase iEntityLivingBase = this.mainTarget;
                    if (iEntityLivingBase == null) {
                        Intrinsics.throwNpe();
                    }
                    String string28 = iEntityLivingBase.getName();
                    if (string28 != null) {
                        string19 = string28;
                        IFontRenderer iFontRenderer = MinecraftInstance.mc.getFontRendererObj();
                        int n9 = n8;
                        bl4 = false;
                        bl = false;
                        p1 = string19;
                        boolean bl8 = false;
                        int n10 = iFontRenderer.getStringWidth(p1);
                        n8 = n9;
                        n = n10;
                    } else {
                        n = 0;
                    }
                    width = RangesKt.coerceAtLeast(n8 + n, 100);
                } else {
                    string19 = (String)this.modeValue.get();
                    bl5 = false;
                    String string29 = string19;
                    if (string29 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string30 = string29.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string30, "(this as java.lang.String).toLowerCase()");
                    if (string30.equals("nameless")) {
                        int n;
                        int n11 = 38;
                        IEntityLivingBase iEntityLivingBase = this.mainTarget;
                        if (iEntityLivingBase == null) {
                            Intrinsics.throwNpe();
                        }
                        String string31 = iEntityLivingBase.getName();
                        if (string31 != null) {
                            string19 = string31;
                            IFontRenderer iFontRenderer = MinecraftInstance.mc.getFontRendererObj();
                            int n12 = n11;
                            bl4 = false;
                            bl = false;
                            p1 = string19;
                            boolean bl9 = false;
                            int n13 = iFontRenderer.getStringWidth(p1);
                            n11 = n12;
                            n = n13;
                        } else {
                            n = 0;
                        }
                        width = RangesKt.coerceAtLeast(n11 + n, 100);
                    } else {
                        int n;
                        int n14 = 38;
                        IEntityLivingBase iEntityLivingBase = this.mainTarget;
                        if (iEntityLivingBase == null) {
                            Intrinsics.throwNpe();
                        }
                        String string32 = iEntityLivingBase.getName();
                        if (string32 != null) {
                            string19 = string32;
                            IFontRenderer iFontRenderer = MinecraftInstance.mc.getFontRendererObj();
                            int n15 = n14;
                            bl4 = false;
                            bl = false;
                            p1 = string19;
                            boolean bl10 = false;
                            int n16 = iFontRenderer.getStringWidth(p1);
                            n14 = n15;
                            n = n16;
                        } else {
                            n = 0;
                        }
                        width = RangesKt.coerceAtLeast(n14 + n, 100);
                    }
                }
            }
        }
        float calcScaleX = this.animProgress;
        float calcScaleY = this.animProgress;
        float calcTranslateX = width / 2.0f * calcScaleX;
        float calcTranslateY = height / 2.0f * calcScaleY;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)calcTranslateX, (float)calcTranslateY, (float)0.0f);
        GL11.glScalef((float)(1.0f - calcScaleX), (float)(1.0f - calcScaleY), (float)(1.0f - calcScaleX));
        String string33 = (String)this.modeValue.get();
        boolean bl11 = false;
        String string34 = string33;
        if (string34 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string35 = string34.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string35, "(this as java.lang.String).toLowerCase()");
        if (string35.equals("novoline")) {
            IEntityLivingBase iEntityLivingBase = this.mainTarget;
            if (iEntityLivingBase == null) {
                Intrinsics.throwNpe();
            }
            this.novoline(iEntityLivingBase, width, height);
        } else {
            string33 = (String)this.modeValue.get();
            bl11 = false;
            String string36 = string33;
            if (string36 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string37 = string36.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(string37, "(this as java.lang.String).toLowerCase()");
            if (string37.equals("astro")) {
                IEntityLivingBase iEntityLivingBase = this.mainTarget;
                if (iEntityLivingBase == null) {
                    Intrinsics.throwNpe();
                }
                this.astro(iEntityLivingBase, width, height);
            } else {
                string33 = (String)this.modeValue.get();
                bl11 = false;
                String string38 = string33;
                if (string38 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String string39 = string38.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(string39, "(this as java.lang.String).toLowerCase()");
                if (string39.equals("wtf")) {
                    IEntityLivingBase iEntityLivingBase = this.mainTarget;
                    if (iEntityLivingBase == null) {
                        Intrinsics.throwNpe();
                    }
                    this.wtf(iEntityLivingBase, width, height);
                } else {
                    string33 = (String)this.modeValue.get();
                    bl11 = false;
                    String string40 = string33;
                    if (string40 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string41 = string40.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string41, "(this as java.lang.String).toLowerCase()");
                    if (string41.equals("nameless")) {
                        IEntityLivingBase iEntityLivingBase = this.mainTarget;
                        if (iEntityLivingBase == null) {
                            Intrinsics.throwNpe();
                        }
                        this.nameless(iEntityLivingBase, width, height);
                    } else {
                        IEntityLivingBase iEntityLivingBase = this.mainTarget;
                        if (iEntityLivingBase == null) {
                            Intrinsics.throwNpe();
                        }
                        this.liquidbounce(iEntityLivingBase, width, height);
                    }
                }
            }
        }
        GL11.glPopMatrix();
        GlStateManager.func_179117_G();
        return new Border(0.0f, 0.0f, width, height);
    }

    private final void liquidbounce(IEntityLivingBase target, float width, float height) {
        block10: {
            block12: {
                block11: {
                    if (target == null) break block10;
                    if (this.easingHealth < 0.0f || this.easingHealth > target.getMaxHealth()) break block11;
                    float f = this.easingHealth - target.getHealth();
                    boolean bl = false;
                    if (!((double)Math.abs(f) < 0.01)) break block12;
                }
                this.easingHealth = target.getHealth();
            }
            RenderUtils.rectangle(0.0, 0.0, width, height, new Color(0, 0, 0, 70).getRGB());
            if (((Boolean)this.shadow.get()).booleanValue()) {
                RenderUtils.drawShadowWithCustomAlpha(0.0f, 0.0f, (float)((double)width), (float)((double)height), 255.0f);
            }
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea((float)(this.getRenderX() + (double)0.0f), (float)(this.getRenderY() + (double)0.0f), (float)((double)width), (float)((double)height));
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            Color c = new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue());
            Color c2 = new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue());
            double startPos = 0.0;
            double healthBar = width;
            double cfr_ignored_0 = -startPos * 2.0;
            RenderUtils.drawGradientSideways(startPos, (double)height - 2.0, startPos + (double)(this.easingHealth / target.getMaxHealth()) * healthBar, height, c2.getRGB(), c.getRGB());
            TargetHud targetHud = this;
            float f = 2.0f;
            float f2 = 10.0f - ((Number)this.fadeSpeed.get()).floatValue();
            float f3 = target.getHealth() - this.easingHealth;
            float f4 = targetHud.easingHealth;
            TargetHud targetHud2 = targetHud;
            boolean bl = false;
            float f5 = (float)Math.pow(f, f2);
            targetHud2.easingHealth = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
            String string = target.getName();
            if (string != null) {
                String string2 = string;
                boolean bl2 = false;
                bl = false;
                String it = string2;
                boolean bl3 = false;
                this.fontRenderer.drawStringWithShadow(it, 36, 7, 0xFFFFFF);
            }
            StringBuilder stringBuilder = new StringBuilder().append("Distance: ");
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            this.fontRenderer.drawStringWithShadow(stringBuilder.append(this.decimalFormat.format(PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, target))).toString(), 36, 19, 0xFFFFFF);
            IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            INetworkPlayerInfo playerInfo = iINetHandlerPlayClient.getPlayerInfo(iEntityPlayerSP2.getUniqueID());
            if (MinecraftInstance.classProvider.isEntityPlayer(target)) {
                playerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo(target.getUniqueID());
            }
            if (playerInfo != null) {
                IResourceLocation locationSkin = playerInfo.getLocationSkin();
                float renderHurtTime = (float)target.getHurtTime() - (target.getHurtTime() != 0 ? Minecraft.func_71410_x().field_71428_T.field_194147_b : 0.0f);
                float hurtPercent = renderHurtTime / 10.0f;
                GL11.glColor4f((float)1.0f, (float)(1.0f - hurtPercent), (float)(1.0f - hurtPercent), (float)1.0f);
                float scale = hurtPercent == 0.0f ? 1.0f : (hurtPercent < 0.5f ? 1.0f - 0.2f * hurtPercent * (float)2 : 0.8f + 0.2f * (hurtPercent - 0.5f) * (float)2);
                int size = 30;
                GL11.glPushMatrix();
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glTranslatef((float)((float)size * 0.5f * (1.0f - scale) / scale), (float)((float)size * 0.5f * (1.0f - scale) / scale), (float)0.0f);
                MinecraftInstance.mc.getTextureManager().bindTexture(locationSkin);
                RenderUtils.drawScaledCustomSizeModalRect(2, 2, 8.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
                GL11.glPopMatrix();
            }
        }
    }

    private final void novoline(IEntityLivingBase target, float width, float height) {
        block9: {
            block11: {
                block10: {
                    if (target == null) break block9;
                    if (this.easingHealth < 0.0f || this.easingHealth > target.getMaxHealth()) break block10;
                    float f = this.easingHealth - target.getHealth();
                    boolean bl = false;
                    if (!((double)Math.abs(f) < 0.01)) break block11;
                }
                this.easingHealth = target.getHealth();
            }
            Color c = new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue());
            Color c2 = new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue());
            RenderUtils.rectangleBordered(0.0, 0.0, width, height, 0.5, new Color(0, 0, 0, 30).getRGB(), (Boolean)this.outline.get() != false ? RenderUtils.reAlpha(c.getRGB(), 0.4f) : new Color(0, 0, 0, 80).getRGB());
            if (((Boolean)this.shadow.get()).booleanValue()) {
                RenderUtils.drawShadowWithCustomAlpha(0.0f, 0.0f, (float)((double)width), (float)((double)height), 255.0f);
            }
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea((float)(this.getRenderX() + (double)0.0f), (float)(this.getRenderY() + (double)0.0f), (float)((double)width), (float)((double)height));
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            RenderUtils.rectangle(36.0, 17.0, (double)width - (double)4, 27.0, new Color(35, 35, 35, 20).getRGB());
            RenderUtils.drawGradientSideways(36.0, 17.0, 36.0 + (double)(this.easingHealth / target.getMaxHealth() * (width - (float)40)), 27.0, c2.getRGB(), c.getRGB());
            this.fontRenderer.drawCenteredString(String.valueOf((float)((int)((double)(target.getHealth() / target.getMaxHealth()) * 1000.0)) / 10.0f) + "%", 36.0f + (width - (float)40) / 2.0f, 19.0f, -1, true);
            TargetHud targetHud = this;
            float f = 2.0f;
            float f2 = 10.0f - ((Number)this.fadeSpeed.get()).floatValue();
            float f3 = target.getHealth() - this.easingHealth;
            float f4 = targetHud.easingHealth;
            TargetHud targetHud2 = targetHud;
            boolean bl = false;
            float f5 = (float)Math.pow(f, f2);
            targetHud2.easingHealth = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
            String string = target.getName();
            if (string != null) {
                String string2 = string;
                boolean bl2 = false;
                bl = false;
                String it = string2;
                boolean bl3 = false;
                this.fontRenderer.drawStringWithShadow(it, 36, 5, 0xFFFFFF);
            }
            IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            INetworkPlayerInfo playerInfo = iINetHandlerPlayClient.getPlayerInfo(iEntityPlayerSP.getUniqueID());
            if (MinecraftInstance.classProvider.isEntityPlayer(target)) {
                playerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo(target.getUniqueID());
            }
            if (playerInfo != null) {
                IResourceLocation locationSkin = playerInfo.getLocationSkin();
                float renderHurtTime = (float)target.getHurtTime() - (target.getHurtTime() != 0 ? Minecraft.func_71410_x().field_71428_T.field_194147_b : 0.0f);
                float hurtPercent = renderHurtTime / 10.0f;
                GL11.glColor4f((float)1.0f, (float)(1.0f - hurtPercent), (float)(1.0f - hurtPercent), (float)1.0f);
                float scale = hurtPercent == 0.0f ? 1.0f : (hurtPercent < 0.5f ? 1.0f - 0.2f * hurtPercent * (float)2 : 0.8f + 0.2f * (hurtPercent - 0.5f) * (float)2);
                int size = 30;
                GL11.glPushMatrix();
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glTranslatef((float)((float)size * 0.5f * (1.0f - scale) / scale), (float)((float)size * 0.5f * (1.0f - scale) / scale), (float)0.0f);
                MinecraftInstance.mc.getTextureManager().bindTexture(locationSkin);
                RenderUtils.drawScaledCustomSizeModalRect(2, 2, 8.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
                GL11.glPopMatrix();
            }
        }
    }

    private final void astro(IEntityLivingBase target, float width, float height) {
        block12: {
            block14: {
                block13: {
                    if (target == null) break block12;
                    if (this.easingHealth < 0.0f || this.easingHealth > target.getMaxHealth()) break block13;
                    float f = this.easingHealth - target.getHealth();
                    boolean bl = false;
                    if (!((double)Math.abs(f) < 0.01)) break block14;
                }
                this.easingHealth = target.getHealth();
            }
            Color c = new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue());
            Color c2 = new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue());
            RenderUtils.rectangle(0.0, 0.0, width, height, new Color(0, 0, 0, 120).getRGB());
            if (((Boolean)this.shadow.get()).booleanValue()) {
                RenderUtils.drawShadowWithCustomAlpha(0.0f, 0.0f, (float)((double)width), (float)((double)height), 255.0f);
            }
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea((float)(this.getRenderX() + (double)0.0f), (float)(this.getRenderY() + (double)0.0f), (float)((double)width), (float)((double)height));
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            RenderUtils.rectangle(2.0, (double)height - 6.0, 2.0 + (double)(width - (float)4), (double)height - 2.0, new Color(25, 25, 25, 70).getRGB());
            RenderUtils.drawGradientSideways(2.0, (double)height - 6.0, 2.0 + (double)(this.easingHealth / target.getMaxHealth() * (width - (float)4)), (double)height - 2.0, c2.getRGB(), c.getRGB());
            TargetHud targetHud = this;
            float f = 2.0f;
            float f2 = 10.0f - ((Number)this.fadeSpeed.get()).floatValue();
            float f3 = target.getHealth() - this.easingHealth;
            float f4 = targetHud.easingHealth;
            TargetHud targetHud2 = targetHud;
            boolean bl = false;
            float f5 = (float)Math.pow(f, f2);
            targetHud2.easingHealth = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
            String string = target.getName();
            if (string != null) {
                String string2 = string;
                boolean bl2 = false;
                bl = false;
                String it = string2;
                boolean bl3 = false;
                this.fontRenderer.drawStringWithShadow(it, 34, 5, 0xFFFFFF);
            }
            if (MinecraftInstance.classProvider.isEntityPlayer(target)) {
                INetworkPlayerInfo playerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo(target.getUniqueID());
                if (playerInfo != null) {
                    IResourceLocation locationSkin = playerInfo.getLocationSkin();
                    this.drawHead(locationSkin, 30, 30);
                }
            } else {
                INetworkPlayerInfo playerInfo;
                IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                if ((playerInfo = iINetHandlerPlayClient.getPlayerInfo(iEntityPlayerSP.getUniqueID())) != null) {
                    IResourceLocation locationSkin = playerInfo.getLocationSkin();
                    this.drawHead(locationSkin, 30, 30);
                }
            }
        }
    }

    private final void wtf(IEntityLivingBase target, float width, float height) {
        block6: {
            block8: {
                block7: {
                    if (target == null) break block6;
                    if (this.easingHealth < 0.0f || this.easingHealth > target.getMaxHealth()) break block7;
                    float f = this.easingHealth - target.getHealth();
                    boolean bl = false;
                    if (!((double)Math.abs(f) < 0.01)) break block8;
                }
                this.easingHealth = target.getHealth();
            }
            Color c = new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue());
            Color c2 = new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue());
            if (((Boolean)this.shadow.get()).booleanValue()) {
                RenderUtils.drawShadowWithCustomAlpha(0.0f, 0.0f, (float)((double)width), (float)((double)height), 255.0f);
            }
            RenderUtils.rectangleBordered(0.0, 0.0, width, height, 1.0, new Color(95, 95, 95, 255).getRGB(), new Color(0, 0, 0, 255).getRGB());
            RenderUtils.rectangleBordered(1.5, 1.5, (double)width - 1.5, (double)height - 1.5, 1.0, new Color(45, 45, 45, 255).getRGB(), new Color(65, 65, 65, 255).getRGB());
            double startPos = 6.0;
            double barWidth = (double)width - startPos * 2.0;
            RenderUtils.rectangle(startPos - 0.5, 15.5, startPos + barWidth + 0.5, 26.5, new Color(25, 25, 25, 255).getRGB());
            RenderUtils.drawGradientSideways(startPos, 16.0, startPos + (double)(this.easingHealth / target.getMaxHealth()) * barWidth, 26.0, c2.getRGB(), c.getRGB());
            Fonts.font35.drawCenteredString(String.valueOf((float)((int)(target.getHealth() * (float)10)) / 10.0f) + " HP", width / 2.0f, 19.0f, -1, true);
            TargetHud targetHud = this;
            float f = 2.0f;
            float f2 = 10.0f - ((Number)this.fadeSpeed.get()).floatValue();
            float f3 = target.getHealth() - this.easingHealth;
            float f4 = targetHud.easingHealth;
            TargetHud targetHud2 = targetHud;
            boolean bl = false;
            float f5 = (float)Math.pow(f, f2);
            targetHud2.easingHealth = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
            String string = target.getName();
            if (string != null) {
                String string2 = string;
                boolean bl2 = false;
                bl = false;
                String it = string2;
                boolean bl3 = false;
                this.fontRenderer.drawStringWithShadow(it, (int)startPos + 2, 6, 0xFFFFFF);
            }
            StringBuilder stringBuilder = new StringBuilder().append("Distance: ");
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            this.fontRenderer.drawStringWithShadow(stringBuilder.append((int)iEntityPlayerSP.getDistanceToEntity(target)).append("m").toString(), (int)startPos + 2, 30, 0xFFFFFF);
        }
    }

    private final void nameless(IEntityLivingBase target, float width, float height) {
        block10: {
            block12: {
                block11: {
                    if (target == null) break block10;
                    if (this.easingHealth < 0.0f || this.easingHealth > target.getMaxHealth()) break block11;
                    float f = this.easingHealth - target.getHealth();
                    boolean bl = false;
                    if (!((double)Math.abs(f) < 0.01)) break block12;
                }
                this.easingHealth = target.getHealth();
            }
            RenderUtils.drawRoundedRect(0.0f, 0.0f, width, height, (int)2.4f, new Color(0, 0, 0, 100).getRGB());
            if (((Boolean)this.shadow.get()).booleanValue()) {
                RenderUtils.drawShadowWithCustomAlpha(0.0f, 0.0f, (float)((double)width), (float)((double)height), 255.0f);
            }
            if (((Boolean)this.blur.get()).booleanValue()) {
                GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
                BlurBuffer.blurArea((float)(this.getRenderX() + (double)0.0f), (float)(this.getRenderY() + (double)0.0f), (float)((double)width), (float)((double)height));
                GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
            }
            Color c = new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue());
            Color c2 = new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue());
            double startPos = 2.0;
            double barWidth = (double)width - startPos * 2.0;
            RenderUtils.drawGradientSideways(startPos, (double)height - 3.0, startPos + (double)(this.easingHealth / target.getMaxHealth()) * barWidth, (double)height - 2.0, c2.getRGB(), c.getRGB());
            TargetHud targetHud = this;
            float f = 2.0f;
            float f2 = 10.0f - ((Number)this.fadeSpeed.get()).floatValue();
            float f3 = target.getHealth() - this.easingHealth;
            float f4 = targetHud.easingHealth;
            TargetHud targetHud2 = targetHud;
            boolean bl = false;
            float f5 = (float)Math.pow(f, f2);
            targetHud2.easingHealth = f4 + f3 / f5 * (float)RenderUtils.deltaTime;
            String string = target.getName();
            if (string != null) {
                String string2 = string;
                boolean bl2 = false;
                bl = false;
                String it = string2;
                boolean bl3 = false;
                this.fontRenderer.drawStringWithShadow(it, 34, 7, 0xFFFFFF);
            }
            StringBuilder stringBuilder = new StringBuilder().append("Distance: ");
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            this.fontRenderer.drawStringWithShadow(stringBuilder.append(this.decimalFormat.format(PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, target))).toString(), 34, 19, 0xFFFFFF);
            IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            INetworkPlayerInfo playerInfo = iINetHandlerPlayClient.getPlayerInfo(iEntityPlayerSP2.getUniqueID());
            if (MinecraftInstance.classProvider.isEntityPlayer(target)) {
                playerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo(target.getUniqueID());
            }
            if (playerInfo != null) {
                IResourceLocation locationSkin = playerInfo.getLocationSkin();
                float renderHurtTime = (float)target.getHurtTime() - (target.getHurtTime() != 0 ? Minecraft.func_71410_x().field_71428_T.field_194147_b : 0.0f);
                float hurtPercent = renderHurtTime / 10.0f;
                GL11.glColor4f((float)1.0f, (float)(1.0f - hurtPercent), (float)(1.0f - hurtPercent), (float)1.0f);
                float scale = hurtPercent == 0.0f ? 1.0f : (hurtPercent < 0.5f ? 1.0f - 0.2f * hurtPercent * (float)2 : 0.8f + 0.2f * (hurtPercent - 0.5f) * (float)2);
                int size = 30;
                GL11.glPushMatrix();
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glTranslatef((float)((float)size * 0.5f * (1.0f - scale) / scale), (float)((float)size * 0.5f * (1.0f - scale) / scale), (float)0.0f);
                MinecraftInstance.mc.getTextureManager().bindTexture(locationSkin);
                RenderUtils.drawScaledCustomSizeModalRect(2, 2, 8.0f, 8.0f, 8, 8, size, size, 64.0f, 64.0f);
                GL11.glPopMatrix();
            }
        }
    }

    private final void drawHead(IResourceLocation skin, int width, int height) {
        MinecraftInstance.mc.getTextureManager().bindTexture(skin);
        RenderUtils.drawScaledCustomSizeModalRect(2, 2, 8.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
    }

    public TargetHud() {
        super(0.0, 0.0, 0.0f, null, 15, null);
    }
}

