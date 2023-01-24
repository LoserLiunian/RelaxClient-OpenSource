/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.render.HUD;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.FadeState;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification$WhenMappings;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notifications;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.render.EaseUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ.\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u0002042\u0006\u00107\u001a\u0002042\u0006\u00108\u001a\u000204J\u0016\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020=R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\bX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u001a\u0010#\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010*R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\f\u00a8\u0006>"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Notification;", "", "title", "", "content", "type", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/NotifyType;", "time", "", "animeTime", "(Ljava/lang/String;Ljava/lang/String;Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/NotifyType;II)V", "getAnimeTime", "()I", "animeXTime", "", "getAnimeXTime", "()J", "setAnimeXTime", "(J)V", "animeYTime", "getAnimeYTime", "setAnimeYTime", "getContent", "()Ljava/lang/String;", "displayTime", "getDisplayTime", "setDisplayTime", "fadeState", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/FadeState;", "getFadeState", "()Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/FadeState;", "setFadeState", "(Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/FadeState;)V", "height", "getHeight", "nowY", "getNowY", "setNowY", "(I)V", "string", "getString", "setString", "(Ljava/lang/String;)V", "getTime", "getTitle", "getType", "()Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/NotifyType;", "width", "getWidth", "drawCircle", "", "x", "", "y", "radius", "start", "end", "drawNotification", "", "index", "noti", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Notifications;", "Relaxed"})
public final class Notification {
    private final int height = 30;
    @NotNull
    private FadeState fadeState;
    private int nowY;
    @NotNull
    private String string;
    private long displayTime;
    private long animeXTime;
    private long animeYTime;
    private final int width;
    @NotNull
    private final String title;
    @NotNull
    private final String content;
    @NotNull
    private final NotifyType type;
    private final int time;
    private final int animeTime;

    public final int getHeight() {
        return this.height;
    }

    @NotNull
    public final FadeState getFadeState() {
        return this.fadeState;
    }

    public final void setFadeState(@NotNull FadeState fadeState) {
        Intrinsics.checkParameterIsNotNull((Object)fadeState, "<set-?>");
        this.fadeState = fadeState;
    }

    public final int getNowY() {
        return this.nowY;
    }

    public final void setNowY(int n) {
        this.nowY = n;
    }

    @NotNull
    public final String getString() {
        return this.string;
    }

    public final void setString(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "<set-?>");
        this.string = string;
    }

    public final long getDisplayTime() {
        return this.displayTime;
    }

    public final void setDisplayTime(long l) {
        this.displayTime = l;
    }

    public final long getAnimeXTime() {
        return this.animeXTime;
    }

    public final void setAnimeXTime(long l) {
        this.animeXTime = l;
    }

    public final long getAnimeYTime() {
        return this.animeYTime;
    }

    public final void setAnimeYTime(long l) {
        this.animeYTime = l;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void drawCircle(float x, float y, float radius, float start, float end) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glBegin((int)3);
        Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(HUD.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.render.HUD");
        }
        HUD hud = (HUD)module;
        for (float i = end; i >= start; i -= 4.0f) {
            Color color = RenderUtils.getGradientOffset(new Color(((Number)hud.getRedValue2().get()).intValue(), ((Number)hud.getGreenValue2().get()).intValue(), ((Number)hud.getBlueValue2().get()).intValue()), new Color(((Number)hud.getRedValue3().get()).intValue(), ((Number)hud.getGreenValue3().get()).intValue(), ((Number)hud.getBlueValue3().get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / 360.0 + (double)(i * (float)34 / (float)360 * (float)56 / (float)100)) / (double)10);
            Intrinsics.checkExpressionValueIsNotNull(color, "RenderUtils.getGradientO\u2026 100) / 10)\n            )");
            int c = color.getRGB();
            float f2 = (float)(c >> 24 & 0xFF) / 255.0f;
            float f22 = (float)(c >> 16 & 0xFF) / 255.0f;
            float f3 = (float)(c >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(c & 0xFF) / 255.0f;
            GlStateManager.func_179131_c((float)f22, (float)f3, (float)f4, (float)f2);
            GL11.glVertex2f((float)((float)((double)x + Math.cos((double)i * Math.PI / (double)180) * (double)(radius * 1.001f))), (float)((float)((double)y + Math.sin((double)i * Math.PI / (double)180) * (double)(radius * 1.001f))));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public final boolean drawNotification(int index, @NotNull Notifications noti) {
        double pct;
        Intrinsics.checkParameterIsNotNull(noti, "noti");
        int realY = -(index + 1) * (this.height + 10);
        long nowTime = System.currentTimeMillis();
        if (this.nowY != realY) {
            pct = (double)(nowTime - this.animeYTime) / (double)this.animeTime;
            if (pct > 1.0) {
                this.nowY = realY;
                pct = 1.0;
            } else {
                pct = EaseUtils.easeOutBack(pct);
            }
            GL11.glTranslated((double)0.0, (double)((double)(realY - this.nowY) * pct), (double)0.0);
        } else {
            this.animeYTime = nowTime;
        }
        GL11.glTranslated((double)0.0, (double)this.nowY, (double)0.0);
        pct = (double)(nowTime - this.animeXTime) / (double)this.animeTime;
        switch (Notification$WhenMappings.$EnumSwitchMapping$0[this.fadeState.ordinal()]) {
            case 1: {
                if (pct > 1.0) {
                    this.fadeState = FadeState.STAY;
                    this.animeXTime = nowTime;
                    pct = 1.0;
                }
                pct = EaseUtils.easeOutBack(pct);
                break;
            }
            case 2: {
                pct = 1.0;
                if (nowTime - this.animeXTime <= (long)this.time) break;
                this.fadeState = FadeState.OUT;
                this.animeXTime = nowTime;
                break;
            }
            case 3: {
                if (pct > 1.0) {
                    this.fadeState = FadeState.END;
                    this.animeXTime = nowTime;
                    pct = 1.0;
                }
                pct = 1.0 - EaseUtils.easeInBack(pct);
                break;
            }
            case 4: {
                return true;
            }
        }
        String string1 = "";
        if (Intrinsics.areEqual(this.type.toString(), "SUCCESS")) {
            this.string = "a";
            string1 = "o";
        }
        if (Intrinsics.areEqual(this.type.toString(), "ERROR")) {
            this.string = "B";
            string1 = "p";
        }
        if (Intrinsics.areEqual(this.type.toString(), "WARNING")) {
            this.string = "D";
            string1 = "r";
        }
        if (Intrinsics.areEqual(this.type.toString(), "INFO")) {
            this.string = "C";
            string1 = "m";
        }
        double renderX = noti.getRenderX();
        double renderY = noti.getRenderY();
        String string = (String)noti.getMode().get();
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        string = string3;
        switch (string.hashCode()) {
            case 1648341806: {
                if (!string.equals("novoline")) break;
                GL11.glScaled((double)pct, (double)pct, (double)pct);
                GL11.glTranslatef((float)(-((float)this.width) / (float)2), (float)(-((float)this.height) / (float)2), (float)0.0f);
                RenderUtils.drawRect(0.0f, 0.0f, (float)this.width + (float)10, (float)this.height, new Color(63, 63, 63, 40));
                RenderUtils.drawShadow(0.0f, 0.0f, (float)this.width + (float)10, (float)this.height);
                int n = (int)24.5f;
                Color color = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color, "Color.WHITE");
                Fonts.font40.drawStringWithShadow(this.title, n, 7, color.getRGB());
                String string4 = this.content + " (" + new BigDecimal(((float)this.time - (float)this.time * ((float)(nowTime - this.displayTime) / ((float)this.animeTime * 2.0f + (float)this.time))) / (float)1000).setScale(1, 4).toString() + "s)";
                int n2 = (int)24.5f;
                int n3 = (int)17.3f;
                Color color2 = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color2, "Color.WHITE");
                Fonts.font35.drawString(string4, n2, n3, color2.getRGB());
                RenderUtils.drawFilledCircle(13, 15, 8.5f, Color.BLACK);
                Color color3 = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color3, "Color.WHITE");
                Fonts.n80.drawString(this.string, 4, 8, color3.getRGB());
                float per = (float)270 - (float)270 * ((float)(nowTime - this.displayTime) / ((float)this.animeTime * 2.0f + (float)this.time));
                ClientUtils.getLogger().info((Object)Float.valueOf(per));
                GlStateManager.func_179117_G();
                break;
            }
        }
        return false;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    @NotNull
    public final NotifyType getType() {
        return this.type;
    }

    public final int getTime() {
        return this.time;
    }

    public final int getAnimeTime() {
        return this.animeTime;
    }

    public Notification(@NotNull String title, @NotNull String content, @NotNull NotifyType type, int time, int animeTime) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull((Object)type, "type");
        this.title = title;
        this.content = content;
        this.type = type;
        this.time = time;
        this.animeTime = animeTime;
        this.height = 30;
        this.fadeState = FadeState.IN;
        this.nowY = -this.height;
        this.string = "";
        this.displayTime = System.currentTimeMillis();
        this.animeXTime = System.currentTimeMillis();
        this.animeYTime = System.currentTimeMillis();
        this.width = Fonts.font30.getStringWidth(this.content) + 53;
    }

    public /* synthetic */ Notification(String string, String string2, NotifyType notifyType, int n, int n2, int n3, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n3 & 8) != 0) {
            n = 2000;
        }
        if ((n3 & 0x10) != 0) {
            n2 = 500;
        }
        this(string, string2, notifyType, n, n2);
    }
}

