/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.Session
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.awt.Color;
import java.io.Closeable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import me.utils.render.ColorUtils2;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.features.module.modules.color.Gident;
import net.ccbluex.liquidbounce.features.module.modules.color.Rainbow;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.CPSCounter;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.ServerUtils;
import net.ccbluex.liquidbounce.utils.render.Palette;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.render.shader.shaders.RainbowFontShader;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.ccbluex.liquidbounce.value.TextValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@ElementInfo(name="Text")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\f\n\u0002\b\b\b\u0007\u0018\u0000 \\2\u00020\u0001:\u0001\\B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\n\u0010C\u001a\u0004\u0018\u00010DH\u0016J.\u0010E\u001a\u00020F2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010G\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\u00032\u0006\u0010I\u001a\u00020)J.\u0010E\u001a\u00020F2\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020)J\u0012\u0010J\u001a\u0004\u0018\u00010\u001a2\u0006\u0010K\u001a\u00020\u001aH\u0002J\u000e\u0010L\u001a\u00020F2\u0006\u0010I\u001a\u00020MJ\u000e\u0010L\u001a\u00020F2\u0006\u0010N\u001a\u00020)J&\u0010L\u001a\u00020F2\u0006\u0010O\u001a\u00020)2\u0006\u0010P\u001a\u00020)2\u0006\u0010Q\u001a\u00020)2\u0006\u0010R\u001a\u00020)J\u0018\u0010S\u001a\u00020F2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020)H\u0016J \u0010W\u001a\u00020F2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010X\u001a\u00020)H\u0016J\u0010\u0010Y\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020\u001aH\u0002J\u000e\u0010Z\u001a\u00020\u00002\u0006\u0010T\u001a\u00020MJ\b\u0010[\u001a\u00020FH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00107\u001a\u00020)X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006]"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Text;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "Mode", "Lnet/ccbluex/liquidbounce/value/ListValue;", "amountValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "astolfoRainbowIndex", "astolfoRainbowOffset", "astolfoclient", "balpha", "blueValue", "bord", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "brightnessValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "char", "colorModeValue", "display", "", "getDisplay", "()Ljava/lang/String;", "displayString", "Lnet/ccbluex/liquidbounce/value/TextValue;", "displayText", "distanceValue", "doslide", "", "getDoslide", "()Z", "setDoslide", "(Z)V", "editMode", "editTicks", "", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "greenValue", "newRainbowIndex", "prevClick", "", "rainbowX", "rainbowY", "redValue", "saturationValue", "shadow", "slide", "slidedelay", "slidetext", "getSlidetext", "()I", "setSlidetext", "(I)V", "slidetimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "getSlidetimer", "()Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "setSlidetimer", "(Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;)V", "speedStr", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "drawRect", "", "x2", "y2", "color", "getReplacement", "str", "glColor", "Ljava/awt/Color;", "hex", "red", "green", "blue", "alpha", "handleKey", "c", "", "keyCode", "handleMouseClick", "mouseButton", "multiReplace", "setColor", "updateElement", "Companion", "Relaxed"})
public final class Text
extends Element {
    private final FloatValue brightnessValue;
    private final ListValue Mode;
    private final TextValue displayString;
    private final IntegerValue redValue;
    private final IntegerValue greenValue;
    private final IntegerValue blueValue;
    private final IntegerValue newRainbowIndex;
    private final IntegerValue astolfoRainbowOffset;
    private final IntegerValue astolfoclient;
    private final IntegerValue astolfoRainbowIndex;
    private final FloatValue saturationValue;
    private final ListValue colorModeValue;
    private final FloatValue rainbowX;
    private final FloatValue rainbowY;
    private final BoolValue shadow;
    private final BoolValue bord;
    private final BoolValue slide;
    private final BoolValue char;
    private final IntegerValue slidedelay;
    private final IntegerValue balpha;
    private final IntegerValue distanceValue;
    private final IntegerValue amountValue;
    private FontValue fontValue;
    private boolean editMode;
    private int editTicks;
    private long prevClick;
    private String speedStr;
    private String displayText;
    private int slidetext;
    @NotNull
    private MSTimer slidetimer;
    private boolean doslide;
    @NotNull
    private static final SimpleDateFormat DATE_FORMAT;
    @NotNull
    private static final SimpleDateFormat HOUR_FORMAT;
    @NotNull
    private static final DecimalFormat DECIMAL_FORMAT;
    @NotNull
    private static final DecimalFormat DECIMAL_FORMAT_INT;
    public static final Companion Companion;

    private final String getDisplay() {
        CharSequence charSequence = (CharSequence)this.displayString.get();
        boolean bl = false;
        String textContent = charSequence.length() == 0 && !this.editMode ? "Relaxed | Fps:%fps% | %serverip% | User:%username%" : (String)this.displayString.get();
        return this.multiReplace(textContent);
    }

    /*
     * WARNING - bad return control flow
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final String getReplacement(String str) {
        String string;
        block79: {
            block81: {
                block74: {
                    block75: {
                        block77: {
                            block76: {
                                block78: {
                                    block80: {
                                        block73: {
                                            if (MinecraftInstance.mc.getThePlayer() != null) {
                                                switch (str) {
                                                    case "x": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(iEntityPlayerSP.getPosX());
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(iEntityPlayerSP.getPosX());
                                                    }
                                                    case "y": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(iEntityPlayerSP.getPosY());
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(iEntityPlayerSP.getPosY());
                                                    }
                                                    case "z": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(iEntityPlayerSP.getPosZ());
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(iEntityPlayerSP.getPosZ());
                                                    }
                                                    case "xInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(iEntityPlayerSP.getPosX());
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(iEntityPlayerSP.getPosX());
                                                    }
                                                    case "yInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(iEntityPlayerSP.getPosY());
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(iEntityPlayerSP.getPosY());
                                                    }
                                                    case "zInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(iEntityPlayerSP.getPosZ());
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(iEntityPlayerSP.getPosZ());
                                                    }
                                                    case "xdp": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return String.valueOf(iEntityPlayerSP.getPosX());
                                                        Intrinsics.throwNpe();
                                                        return String.valueOf(iEntityPlayerSP.getPosX());
                                                    }
                                                    case "ydp": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return String.valueOf(iEntityPlayerSP.getPosY());
                                                        Intrinsics.throwNpe();
                                                        return String.valueOf(iEntityPlayerSP.getPosY());
                                                    }
                                                    case "zdp": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return String.valueOf(iEntityPlayerSP.getPosZ());
                                                        Intrinsics.throwNpe();
                                                        return String.valueOf(iEntityPlayerSP.getPosZ());
                                                    }
                                                    case "velocity": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        double d = iEntityPlayerSP.getMotionX();
                                                        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP2 == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        double d2 = d * iEntityPlayerSP2.getMotionX();
                                                        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP3 == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        double d3 = iEntityPlayerSP3.getMotionZ();
                                                        IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP4 == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        double d4 = d2 + d3 * iEntityPlayerSP4.getMotionZ();
                                                        DecimalFormat decimalFormat = DECIMAL_FORMAT;
                                                        boolean bl = false;
                                                        double d5 = Math.sqrt(d4);
                                                        return decimalFormat.format(d5);
                                                    }
                                                    case "ping": {
                                                        EntityPlayerSP entityPlayerSP = MinecraftInstance.mc2.field_71439_g;
                                                        if (entityPlayerSP != null) return String.valueOf(EntityUtils.INSTANCE.getPing((EntityPlayer)entityPlayerSP));
                                                        Intrinsics.throwNpe();
                                                        return String.valueOf(EntityUtils.INSTANCE.getPing((EntityPlayer)entityPlayerSP));
                                                    }
                                                    case "health": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getHealth()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getHealth()));
                                                    }
                                                    case "maxHealth": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getMaxHealth()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getMaxHealth()));
                                                    }
                                                    case "healthInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getHealth()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getHealth()));
                                                    }
                                                    case "maxHealthInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getMaxHealth()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getMaxHealth()));
                                                    }
                                                    case "yaw": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getRotationYaw()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getRotationYaw()));
                                                    }
                                                    case "pitch": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getRotationPitch()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT.format(Float.valueOf(iEntityPlayerSP.getRotationPitch()));
                                                    }
                                                    case "yawInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getRotationYaw()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getRotationYaw()));
                                                    }
                                                    case "pitchInt": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getRotationPitch()));
                                                        Intrinsics.throwNpe();
                                                        return DECIMAL_FORMAT_INT.format(Float.valueOf(iEntityPlayerSP.getRotationPitch()));
                                                    }
                                                    case "bps": {
                                                        return this.speedStr;
                                                    }
                                                    case "hurtTime": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return String.valueOf(iEntityPlayerSP.getHurtTime());
                                                        Intrinsics.throwNpe();
                                                        return String.valueOf(iEntityPlayerSP.getHurtTime());
                                                    }
                                                    case "onGround": {
                                                        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                                                        if (iEntityPlayerSP != null) return String.valueOf(iEntityPlayerSP.getOnGround());
                                                        Intrinsics.throwNpe();
                                                        return String.valueOf(iEntityPlayerSP.getOnGround());
                                                    }
                                                }
                                            }
                                            String string2 = str;
                                            switch (string2.hashCode()) {
                                                case 3076014: {
                                                    if (string2.equals("date")) break block73;
                                                    if (!string2.equals("date")) return null;
                                                    break block74;
                                                }
                                                case 3494900: {
                                                    if (string2.equals("rcps")) return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.RIGHT));
                                                    if (!string2.equals("rcps")) return null;
                                                    return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.RIGHT));
                                                }
                                                case -215825919: {
                                                    if (!string2.equals("clientcreator")) return null;
                                                    return "CCBlueX, P1ayerLk_";
                                                }
                                                case -892772691: {
                                                    if (!string2.equals("clientversion")) return null;
                                                    return "b1";
                                                }
                                                case 1102251254: {
                                                    if (!string2.equals("clientName")) return null;
                                                    return "Relaxed";
                                                }
                                                case 98726: {
                                                    if (string2.equals("cps")) return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.LEFT));
                                                    if (!string2.equals("cps")) return null;
                                                    return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.LEFT));
                                                }
                                                case 101609: {
                                                    if (string2.equals("fps")) break;
                                                    if (!string2.equals("fps")) return null;
                                                    break block75;
                                                }
                                                case 3316154: {
                                                    if (string2.equals("lcps")) return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.LEFT));
                                                    if (!string2.equals("lcps")) return null;
                                                    return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.LEFT));
                                                }
                                                case 3345945: {
                                                    if (string2.equals("mcps")) return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.MIDDLE));
                                                    if (!string2.equals("mcps")) return null;
                                                    return String.valueOf(CPSCounter.getCPS(CPSCounter.MouseButton.MIDDLE));
                                                }
                                                case -266666762: {
                                                    if (!string2.equals("userName")) return null;
                                                    break block76;
                                                }
                                                case 771880589: {
                                                    if (!string2.equals("clientVersion")) return null;
                                                    break block77;
                                                }
                                                case 1103204566: {
                                                    if (!string2.equals("clientname")) return null;
                                                    return "Relaxed";
                                                }
                                                case 1379104682: {
                                                    if (!string2.equals("serverip")) return null;
                                                    break block78;
                                                }
                                                case 1379103690: {
                                                    if (!string2.equals("serverIp")) return null;
                                                    break block79;
                                                }
                                                case 1448827361: {
                                                    if (!string2.equals("clientCreator")) return null;
                                                    return "CCBlueX, P1ayerLk_";
                                                }
                                                case 3560141: {
                                                    if (string2.equals("time")) break block80;
                                                    if (!string2.equals("time")) return null;
                                                    break block81;
                                                }
                                                case -265713450: {
                                                    if (!string2.equals("username")) return null;
                                                    Session session = MinecraftInstance.mc2.func_110432_I();
                                                    Intrinsics.checkExpressionValueIsNotNull(session, "mc2.getSession()");
                                                    string = session.func_111285_a();
                                                    return string;
                                                }
                                            }
                                            string = String.valueOf(Minecraft.func_175610_ah());
                                            return string;
                                        }
                                        string = DATE_FORMAT.format(System.currentTimeMillis());
                                        return string;
                                    }
                                    string = HOUR_FORMAT.format(System.currentTimeMillis());
                                    return string;
                                }
                                string = ServerUtils.getRemoteIp();
                                return string;
                            }
                            string = MinecraftInstance.mc.getSession().getUsername();
                            return string;
                        }
                        string = String.valueOf(1);
                        return string;
                    }
                    string = String.valueOf(Minecraft.func_175610_ah());
                    return string;
                }
                string = DATE_FORMAT.format(System.currentTimeMillis());
                return string;
            }
            string = HOUR_FORMAT.format(System.currentTimeMillis());
            return string;
        }
        string = ServerUtils.getRemoteIp();
        return string;
        return null;
    }

    /*
     * Unable to fully structure code
     */
    private final String multiReplace(String str) {
        lastPercent = -1;
        result = new StringBuilder();
        var4_4 = 0;
        var5_5 = ((CharSequence)str).length();
        while (var4_4 < var5_5) {
            block7: {
                block6: {
                    if (str.charAt((int)i) != '%') break block6;
                    if (lastPercent == -1) ** GOTO lbl25
                    if (lastPercent + 1 == i) ** GOTO lbl-1000
                    var7_7 = str;
                    var8_8 = lastPercent + 1;
                    var10_10 = this;
                    var9_9 = false;
                    v0 = var7_7;
                    if (v0 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(v0.substring(var8_8, (int)i), "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    replacement = var10_10.getReplacement(var11_11);
                    if (replacement != null) {
                        result.append(replacement);
                        lastPercent = -1;
                    } else lbl-1000:
                    // 2 sources

                    {
                        result.append(str, lastPercent, (int)i);
lbl25:
                        // 2 sources

                        lastPercent = i;
                    }
                    break block7;
                }
                if (lastPercent == -1) {
                    result.append(str.charAt((int)i));
                }
            }
            ++i;
        }
        if (lastPercent != -1) {
            result.append(str, lastPercent, str.length());
        }
        v1 = result.toString();
        Intrinsics.checkExpressionValueIsNotNull(v1, "result.toString()");
        return v1;
    }

    public final int getSlidetext() {
        return this.slidetext;
    }

    public final void setSlidetext(int n) {
        this.slidetext = n;
    }

    @NotNull
    public final MSTimer getSlidetimer() {
        return this.slidetimer;
    }

    public final void setSlidetimer(@NotNull MSTimer mSTimer) {
        Intrinsics.checkParameterIsNotNull(mSTimer, "<set-?>");
        this.slidetimer = mSTimer;
    }

    public final boolean getDoslide() {
        return this.doslide;
    }

    public final void setDoslide(boolean bl) {
        this.doslide = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    @Override
    @Nullable
    public Border drawElement() {
        IFontRenderer fontRenderer = (IFontRenderer)this.fontValue.get();
        float length2 = 4.5f;
        String string = this.displayText;
        int n = 0;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        char[] cArray = string2.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(cArray, "(this as java.lang.String).toCharArray()");
        char[] charArray = cArray;
        if (((Boolean)this.char.get()).booleanValue()) {
            length2 = fontRenderer.getStringWidth(this.displayText);
        } else {
            for (char charIndex : charArray) {
                length2 += (float)fontRenderer.getStringWidth(String.valueOf(charIndex));
            }
        }
        if (((Boolean)this.slide.get()).booleanValue() && !MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen())) {
            String string3;
            if (this.slidetimer.hasTimePassed(((Number)this.slidedelay.get()).intValue())) {
                if (this.slidetext <= this.getDisplay().length() && this.doslide) {
                    ++this.slidetext;
                    this.slidetimer.reset();
                } else if (!this.doslide && this.slidetext >= 0) {
                    --this.slidetext;
                    this.slidetimer.reset();
                }
            }
            if (this.slidetext == this.getDisplay().length() && this.doslide) {
                this.doslide = false;
            } else if (this.slidetext == 0 && !this.doslide) {
                this.doslide = true;
            }
            String charIndex = this.getDisplay();
            n = 0;
            int n2 = this.slidetext;
            Text text = this;
            int n3 = 0;
            String string4 = charIndex;
            if (string4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String string5 = string4.substring(n, n2);
            Intrinsics.checkExpressionValueIsNotNull(string5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            text.displayText = string3 = string5;
        } else {
            this.displayText = this.getDisplay();
        }
        String colorMode = (String)this.colorModeValue.get();
        int color = new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()).getRGB();
        boolean rainbow = StringsKt.equals(colorMode, "1", true);
        if (((Boolean)this.bord.get()).booleanValue()) {
            double barEnd;
            double barStart;
            int i;
            int n4;
            if (Intrinsics.areEqual((String)this.Mode.get(), "Skeet")) {
                RenderUtils.autoExhibition(-4.0, -5.2, length2, (double)fontRenderer.getFontHeight() + 1.5, 1.0);
                int[] counter = new int[]{0};
                double barLength = length2;
                int n5 = 0;
                n4 = ((Number)this.amountValue.get()).intValue() - 1;
                if (n5 <= n4) {
                    while (true) {
                        int n6;
                        int n7;
                        barStart = (double)i / (double)((Number)this.amountValue.get()).intValue() * barLength;
                        barEnd = (double)(i + 1) / (double)((Number)this.amountValue.get()).intValue() * barLength;
                        if (rainbow) {
                            n7 = 0;
                        } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                            n7 = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                        } else if (StringsKt.equals(colorMode, "Fade", true)) {
                            Color color2 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), i * ((Number)this.distanceValue.get()).intValue(), this.displayText.length() * 200);
                            Intrinsics.checkExpressionValueIsNotNull(color2, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                            n7 = color2.getRGB();
                        } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                            n7 = RenderUtils.Astolfo(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                        } else if (StringsKt.equals(colorMode, "Gident", true)) {
                            Color color3 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color3, "RenderUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n7 = color3.getRGB();
                        } else {
                            n7 = StringsKt.equals(colorMode, "NewRainbow", true) ? RenderUtils.getRainbow(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                        }
                        if (rainbow) {
                            n6 = 0;
                        } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                            n6 = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                        } else if (StringsKt.equals(colorMode, "Fade", true)) {
                            Color color4 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), i * ((Number)this.distanceValue.get()).intValue(), this.displayText.length() * 200);
                            Intrinsics.checkExpressionValueIsNotNull(color4, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                            n6 = color4.getRGB();
                        } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                            n6 = RenderUtils.Astolfo(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                        } else if (StringsKt.equals(colorMode, "Gident", true)) {
                            Color color5 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color5, "RenderUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n6 = color5.getRGB();
                        } else {
                            n6 = StringsKt.equals(colorMode, "NewRainbow", true) ? RenderUtils.getRainbow(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                        }
                        RenderUtils.drawGradientSideways(-1.4 + barStart, -2.7, -1.4 + barEnd, -2.0, n7, n6);
                        if (i == n4) break;
                        ++i;
                    }
                }
            }
            if (Intrinsics.areEqual((String)this.Mode.get(), "Slide")) {
                RenderUtils.drawRect(-4.0f, -4.5f, length2, (float)fontRenderer.getFontHeight(), new Color(0, 0, 0, ((Number)this.balpha.get()).intValue()).getRGB());
                double barLength = length2 + 1.0f;
                int[] counter2 = new int[]{0};
                i = 0;
                n4 = ((Number)this.amountValue.get()).intValue() - 1;
                if (i <= n4) {
                    while (true) {
                        int n8;
                        int n9;
                        barStart = (double)i / (double)((Number)this.amountValue.get()).intValue() * barLength;
                        barEnd = (double)(i + 1) / (double)((Number)this.amountValue.get()).intValue() * barLength;
                        if (rainbow) {
                            n9 = 0;
                        } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                            n9 = ColorUtils2.hslRainbow$default(counter2[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                        } else if (StringsKt.equals(colorMode, "Fade", true)) {
                            Color color6 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), i * ((Number)this.distanceValue.get()).intValue(), this.displayText.length() * 200);
                            Intrinsics.checkExpressionValueIsNotNull(color6, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                            n9 = color6.getRGB();
                        } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                            n9 = RenderUtils.Astolfo(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                        } else if (StringsKt.equals(colorMode, "Gident", true)) {
                            Color color7 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color7, "RenderUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n9 = color7.getRGB();
                        } else {
                            n9 = StringsKt.equals(colorMode, "NewRainbow", true) ? RenderUtils.getRainbow(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                        }
                        if (rainbow) {
                            n8 = 0;
                        } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                            n8 = ColorUtils2.hslRainbow$default(counter2[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                        } else if (StringsKt.equals(colorMode, "Fade", true)) {
                            Color color8 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), i * ((Number)this.distanceValue.get()).intValue(), this.displayText.length() * 200);
                            Intrinsics.checkExpressionValueIsNotNull(color8, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                            n8 = color8.getRGB();
                        } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                            n8 = RenderUtils.Astolfo(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                        } else if (StringsKt.equals(colorMode, "Gident", true)) {
                            Color color9 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)(i * ((Number)this.distanceValue.get()).intValue())) / (double)10);
                            Intrinsics.checkExpressionValueIsNotNull(color9, "RenderUtils.getGradientO\u2026stanceValue.get()) / 10))");
                            n8 = color9.getRGB();
                        } else {
                            n8 = StringsKt.equals(colorMode, "NewRainbow", true) ? RenderUtils.getRainbow(i * ((Number)this.distanceValue.get()).intValue(), ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                        }
                        RenderUtils.drawGradientSideways(-4.0 + barStart, -4.2, -1.0 + barEnd, -3.0, n9, n8);
                        if (i == n4) break;
                        ++i;
                    }
                }
            }
        }
        int[] counter = new int[]{0};
        if (((Boolean)this.char.get()).booleanValue()) {
            boolean rainbow2 = StringsKt.equals(colorMode, "1", true);
            float counter2 = ((Number)this.rainbowX.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowX.get()).floatValue();
            float i = ((Number)this.rainbowY.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowY.get()).floatValue();
            float offset$iv22 = (float)(System.currentTimeMillis() % (long)10000) / 10000.0f;
            boolean $i$f$begin = false;
            if (rainbow2) {
                void y$iv;
                void x$iv;
                RainbowFontShader.INSTANCE.setStrengthX((float)x$iv);
                RainbowFontShader.INSTANCE.setStrengthY((float)y$iv);
                RainbowFontShader.INSTANCE.setOffset(offset$iv22);
                RainbowFontShader.INSTANCE.startShader();
            }
            Closeable x$iv = RainbowFontShader.INSTANCE;
            boolean y$iv = false;
            Throwable offset$iv22 = null;
            try {
                int n10;
                Object it = (RainbowFontShader)x$iv;
                boolean bl = false;
                if (rainbow2) {
                    n10 = 0;
                } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                    n10 = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                } else if (StringsKt.equals(colorMode, "Fade", true)) {
                    Color color10 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), counter[0] * 100, this.displayText.length() * 200);
                    Intrinsics.checkExpressionValueIsNotNull(color10, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                    n10 = color10.getRGB();
                } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                    n10 = RenderUtils.Astolfo(counter[0] * 100, ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                } else if (StringsKt.equals(colorMode, "NewRainbow", true)) {
                    n10 = RenderUtils.getRainbow(counter[0] * 100, ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                } else if (StringsKt.equals(colorMode, "Gident", true)) {
                    Color color11 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)counter[0]) / (double)10);
                    Intrinsics.checkExpressionValueIsNotNull(color11, "RenderUtils.getGradientO\u2026le() + counter[0]) / 10))");
                    n10 = color11.getRGB();
                } else {
                    n10 = color;
                }
                fontRenderer.drawString(this.displayText, 0.0f, 0.0f, n10, (Boolean)this.shadow.get());
                if (this.editMode && MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen()) && this.editTicks <= 40) {
                    int n11;
                    float f = fontRenderer.getStringWidth(this.displayText);
                    if (rainbow2) {
                        n11 = 0;
                    } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                        n11 = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                    } else if (StringsKt.equals(colorMode, "Fade", true)) {
                        Color color12 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), counter[0] * 100, this.displayText.length() * 200);
                        Intrinsics.checkExpressionValueIsNotNull(color12, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                        n11 = color12.getRGB();
                    } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                        n11 = RenderUtils.Astolfo(counter[0] * 100, ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                    } else if (StringsKt.equals(colorMode, "Gident", true)) {
                        Color color13 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)counter[0]) / (double)10);
                        Intrinsics.checkExpressionValueIsNotNull(color13, "RenderUtils.getGradientO\u2026le() + counter[0]) / 10))");
                        n11 = color13.getRGB();
                    } else {
                        n11 = StringsKt.equals(colorMode, "NewRainbow", true) ? RenderUtils.getRainbow(counter[0] * 100, ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                    }
                    fontRenderer.drawString("_", f, 0.0f, n11, (Boolean)this.shadow.get());
                }
                counter[0] = counter[0] + 1;
                it = Unit.INSTANCE;
            }
            catch (Throwable it) {
                offset$iv22 = it;
                throw it;
            }
            finally {
                CloseableKt.closeFinally(x$iv, offset$iv22);
            }
        }
        int length = 0;
        float x$iv = ((Number)this.rainbowX.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowX.get()).floatValue();
        float y$iv = ((Number)this.rainbowY.get()).floatValue() == 0.0f ? 0.0f : 1.0f / ((Number)this.rainbowY.get()).floatValue();
        float offset$iv = (float)(System.currentTimeMillis() % (long)10000) / 10000.0f;
        boolean $i$f$begin = false;
        if (rainbow) {
            RainbowFontShader.INSTANCE.setStrengthX(x$iv);
            RainbowFontShader.INSTANCE.setStrengthY(y$iv);
            RainbowFontShader.INSTANCE.setOffset(offset$iv);
            RainbowFontShader.INSTANCE.startShader();
        }
        Closeable closeable = RainbowFontShader.INSTANCE;
        boolean bl = false;
        Throwable throwable = null;
        try {
            RainbowFontShader it = (RainbowFontShader)closeable;
            boolean bl2 = false;
            for (char charIndex : charArray) {
                int n12;
                boolean rainbow3 = StringsKt.equals(colorMode, "1", true);
                String string6 = String.valueOf(charIndex);
                float f = length;
                if (rainbow3) {
                    n12 = 0;
                } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                    n12 = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                } else if (StringsKt.equals(colorMode, "Fade", true)) {
                    Color color14 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), counter[0] * 100, this.displayText.length() * 200);
                    Intrinsics.checkExpressionValueIsNotNull(color14, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                    n12 = color14.getRGB();
                } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                    n12 = RenderUtils.Astolfo(counter[0] * 100, ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                } else if (StringsKt.equals(colorMode, "NewRainbow", true)) {
                    n12 = RenderUtils.getRainbow(counter[0] * 100, ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue());
                } else if (StringsKt.equals(colorMode, "Gident", true)) {
                    Color color15 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)counter[0]) / (double)10);
                    Intrinsics.checkExpressionValueIsNotNull(color15, "RenderUtils.getGradientO\u2026le() + counter[0]) / 10))");
                    n12 = color15.getRGB();
                } else {
                    n12 = color;
                }
                fontRenderer.drawString(string6, f, 0.0f, n12, (Boolean)this.shadow.get());
                counter[0] = counter[0] + 1;
                counter[0] = RangesKt.coerceIn(counter[0], 0, this.displayText.length());
                length += fontRenderer.getStringWidth(String.valueOf(charIndex));
            }
            if (this.editMode && MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen()) && this.editTicks <= 40) {
                int n13;
                if (rainbow) {
                    n13 = 0;
                } else if (StringsKt.equals(colorMode, "Rainbow", true)) {
                    n13 = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null).getRGB();
                } else if (StringsKt.equals(colorMode, "Fade", true)) {
                    Color color16 = Palette.fade2(new Color(((Number)this.redValue.get()).intValue(), ((Number)this.greenValue.get()).intValue(), ((Number)this.blueValue.get()).intValue()), counter[0] * 100, this.displayText.length() * 200);
                    Intrinsics.checkExpressionValueIsNotNull(color16, "Palette.fade2(Color(redV\u2026displayText.length * 200)");
                    n13 = color16.getRGB();
                } else if (StringsKt.equals(colorMode, "Astolfo", true)) {
                    n13 = RenderUtils.Astolfo(counter[0] * 100, ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue(), ((Number)this.astolfoRainbowOffset.get()).intValue(), ((Number)this.astolfoRainbowIndex.get()).intValue(), ((Number)this.astolfoclient.get()).intValue());
                } else if (StringsKt.equals(colorMode, "Gident", true)) {
                    Color color17 = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue(), 1), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue() + (double)counter[0]) / (double)10);
                    Intrinsics.checkExpressionValueIsNotNull(color17, "RenderUtils.getGradientO\u2026le() + counter[0]) / 10))");
                    n13 = color17.getRGB();
                } else {
                    n13 = StringsKt.equals(colorMode, "NewRainbow", true) ? RenderUtils.getRainbow(counter[0] * 100, ((Number)this.newRainbowIndex.get()).intValue(), ((Number)this.saturationValue.get()).floatValue(), ((Number)this.brightnessValue.get()).floatValue()) : color;
                }
                fontRenderer.drawString("_", length2, 0.0f, n13, (Boolean)this.shadow.get());
            }
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
        if (this.editMode && !MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen())) {
            this.editMode = false;
            this.updateElement();
        }
        return new Border(-2.0f, -2.0f, length2, fontRenderer.getFontHeight());
    }

    @Override
    public void updateElement() {
        this.editTicks += 5;
        if (this.editTicks > 80) {
            this.editTicks = 0;
        }
        this.displayText = this.editMode ? (String)this.displayString.get() : this.getDisplay();
    }

    @Override
    public void handleMouseClick(double x, double y, int mouseButton) {
        if (this.isInBorder(x, y) && mouseButton == 0) {
            if (System.currentTimeMillis() - this.prevClick <= 250L) {
                this.editMode = true;
            }
            this.prevClick = System.currentTimeMillis();
        } else {
            this.editMode = false;
        }
    }

    @Override
    public void handleKey(char c, int keyCode) {
        if (this.editMode && MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen())) {
            if (keyCode == 14) {
                CharSequence charSequence = (CharSequence)this.displayString.get();
                int n = 0;
                if (charSequence.length() > 0) {
                    charSequence = (String)this.displayString.get();
                    n = 0;
                    int n2 = ((String)this.displayString.get()).length() - 1;
                    TextValue textValue = this.displayString;
                    boolean bl = false;
                    CharSequence charSequence2 = charSequence;
                    if (charSequence2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string = ((String)charSequence2).substring(n, n2);
                    Intrinsics.checkExpressionValueIsNotNull(string, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    String string2 = string;
                    textValue.set(string2);
                }
                this.updateElement();
                return;
            }
            if (ChatAllowedCharacters.func_71566_a((char)c) || c == '\u00a7') {
                this.displayString.set((String)this.displayString.get() + c);
            }
            this.updateElement();
        }
    }

    @NotNull
    public final Text setColor(@NotNull Color c) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        this.redValue.set(c.getRed());
        this.greenValue.set(c.getGreen());
        this.blueValue.set(c.getBlue());
        return this;
    }

    public final void drawRect(float x, float y, float x2, float y2, int color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        this.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public final void drawRect(double x, double y, double x2, double y2, int color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        this.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public final void glColor(int red, int green, int blue, int alpha) {
        GlStateManager.func_179131_c((float)((float)red / 255.0f), (float)((float)green / 255.0f), (float)((float)blue / 255.0f), (float)((float)alpha / 255.0f));
    }

    public final void glColor(@NotNull Color color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
    }

    public final void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
    }

    public Text(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.brightnessValue = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
        this.Mode = new ListValue("Border-Mode", new String[]{"Slide", "Skeet"}, "Slide");
        this.displayString = new TextValue("DisplayText", "");
        this.redValue = new IntegerValue("Text-R", 255, 0, 255);
        this.greenValue = new IntegerValue("Text-G", 255, 0, 255);
        this.blueValue = new IntegerValue("Text-B", 255, 0, 255);
        this.newRainbowIndex = new IntegerValue("NewRainbowOffset", 1, 1, 50);
        this.astolfoRainbowOffset = new IntegerValue("AstolfoOffset", 5, 1, 20);
        this.astolfoclient = new IntegerValue("AstolfoRange", 109, 1, 765);
        this.astolfoRainbowIndex = new IntegerValue("AstolfoIndex", 109, 1, 300);
        this.saturationValue = new FloatValue("Saturation", 0.9f, 0.0f, 1.0f);
        this.colorModeValue = new ListValue("Text-Color", new String[]{"Custom", "Rainbow", "Fade", "Astolfo", "NewRainbow", "Gident"}, "Gident");
        this.rainbowX = new FloatValue("Rainbow-X", -1000.0f, -2000.0f, 2000.0f);
        this.rainbowY = new FloatValue("Rainbow-Y", -1000.0f, -2000.0f, 2000.0f);
        this.shadow = new BoolValue("Shadow", true);
        this.bord = new BoolValue("Border", false);
        this.slide = new BoolValue("Slide", false);
        this.char = new BoolValue("NotChar", false);
        this.slidedelay = new IntegerValue("SlideDelay", 100, 0, 1000);
        this.balpha = new IntegerValue("BordAlpha", 255, 0, 255);
        this.distanceValue = new IntegerValue("Distance", 0, 0, 400);
        this.amountValue = new IntegerValue("Amount", 25, 1, 50);
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.speedStr = "";
        this.displayText = "";
        this.slidetimer = new MSTimer();
        this.doslide = true;
    }

    public /* synthetic */ Text(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 10.0;
        }
        if ((n & 2) != 0) {
            d2 = 10.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        if ((n & 8) != 0) {
            side = Side.Companion.default();
        }
        this(d, d2, f, side);
    }

    public Text() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }

    static {
        Companion = new Companion(null);
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        HOUR_FORMAT = new SimpleDateFormat("HH:mm");
        DECIMAL_FORMAT = new DecimalFormat("0.00");
        DECIMAL_FORMAT_INT = new DecimalFormat("0");
    }

    public static final /* synthetic */ void access$setFontValue$p(Text $this, FontValue fontValue) {
        $this.fontValue = fontValue;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006\u00a8\u0006\u0011"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Text$Companion;", "", "()V", "DATE_FORMAT", "Ljava/text/SimpleDateFormat;", "getDATE_FORMAT", "()Ljava/text/SimpleDateFormat;", "DECIMAL_FORMAT", "Ljava/text/DecimalFormat;", "getDECIMAL_FORMAT", "()Ljava/text/DecimalFormat;", "DECIMAL_FORMAT_INT", "getDECIMAL_FORMAT_INT", "HOUR_FORMAT", "getHOUR_FORMAT", "defaultClient", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Text;", "Relaxed"})
    public static final class Companion {
        @NotNull
        public final SimpleDateFormat getDATE_FORMAT() {
            return DATE_FORMAT;
        }

        @NotNull
        public final SimpleDateFormat getHOUR_FORMAT() {
            return HOUR_FORMAT;
        }

        @NotNull
        public final DecimalFormat getDECIMAL_FORMAT() {
            return DECIMAL_FORMAT;
        }

        @NotNull
        public final DecimalFormat getDECIMAL_FORMAT_INT() {
            return DECIMAL_FORMAT_INT;
        }

        @NotNull
        public final Text defaultClient() {
            Text text = new Text(2.0, 2.0, 2.0f, null, 8, null);
            text.displayString.set("%clientName%");
            text.shadow.set(true);
            FontValue fontValue = text.fontValue;
            IFontRenderer iFontRenderer = Fonts.font40;
            Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
            fontValue.set(iFontRenderer);
            text.setColor(new Color(0, 111, 255));
            return text;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

