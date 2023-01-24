/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Cape", description="LiquidBounce+ capes.", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/Cape;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "styleValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "getStyleValue", "()Lnet/ccbluex/liquidbounce/value/ListValue;", "tag", "", "getTag", "()Ljava/lang/String;", "getCapeLocation", "Lnet/minecraft/util/ResourceLocation;", "value", "CapeStyle", "Relaxed"})
public final class Cape
extends Module {
    @NotNull
    private final ListValue styleValue = new ListValue("Style", new String[]{"Dark", "Astolfo", "Sunny", "Target", "Wyy", "PowerX", "Azrael", "Flux", "LiquidBounce", "Light", "Novoline", "Special1", "Special2"}, "Dark");

    @NotNull
    public final ListValue getStyleValue() {
        return this.styleValue;
    }

    @NotNull
    public final ResourceLocation getCapeLocation(@NotNull String value) {
        String string;
        Intrinsics.checkParameterIsNotNull(value, "value");
        try {
            string = value;
            boolean bl = false;
            String string2 = string.toUpperCase();
            Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.String).toUpperCase()");
            string = CapeStyle.valueOf(string2).getLocation();
        }
        catch (IllegalArgumentException e) {
            string = CapeStyle.DARK.getLocation();
        }
        return string;
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.styleValue.get();
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013\u00a8\u0006\u0014"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/Cape$CapeStyle;", "", "location", "Lnet/minecraft/util/ResourceLocation;", "(Ljava/lang/String;ILnet/minecraft/util/ResourceLocation;)V", "getLocation", "()Lnet/minecraft/util/ResourceLocation;", "DARK", "ASTOLFO", "LIGHT", "SUNNY", "WYY", "POWERX", "AZRAEL", "TARGET", "FLUX", "LIQUIDBOUNCE", "NOVOLINE", "SPECIAL1", "SPECIAL2", "Relaxed"})
    public static final class CapeStyle
    extends Enum<CapeStyle> {
        public static final /* enum */ CapeStyle DARK;
        public static final /* enum */ CapeStyle ASTOLFO;
        public static final /* enum */ CapeStyle LIGHT;
        public static final /* enum */ CapeStyle SUNNY;
        public static final /* enum */ CapeStyle WYY;
        public static final /* enum */ CapeStyle POWERX;
        public static final /* enum */ CapeStyle AZRAEL;
        public static final /* enum */ CapeStyle TARGET;
        public static final /* enum */ CapeStyle FLUX;
        public static final /* enum */ CapeStyle LIQUIDBOUNCE;
        public static final /* enum */ CapeStyle NOVOLINE;
        public static final /* enum */ CapeStyle SPECIAL1;
        public static final /* enum */ CapeStyle SPECIAL2;
        private static final /* synthetic */ CapeStyle[] $VALUES;
        @NotNull
        private final ResourceLocation location;

        static {
            CapeStyle[] capeStyleArray = new CapeStyle[13];
            CapeStyle[] capeStyleArray2 = capeStyleArray;
            capeStyleArray[0] = DARK = new CapeStyle(new ResourceLocation("relaxed/capes/dark.png"));
            capeStyleArray[1] = ASTOLFO = new CapeStyle(new ResourceLocation("relaxed/capes/astolfo.png"));
            capeStyleArray[2] = LIGHT = new CapeStyle(new ResourceLocation("relaxed/capes/light.png"));
            capeStyleArray[3] = SUNNY = new CapeStyle(new ResourceLocation("relaxed/capes/Sunny.png"));
            capeStyleArray[4] = WYY = new CapeStyle(new ResourceLocation("relaxed/capes/Wyy.png"));
            capeStyleArray[5] = POWERX = new CapeStyle(new ResourceLocation("relaxed/capes/PowerX.png"));
            capeStyleArray[6] = AZRAEL = new CapeStyle(new ResourceLocation("relaxed/capes/azrael.png"));
            capeStyleArray[7] = TARGET = new CapeStyle(new ResourceLocation("relaxed/capes/Target.png"));
            capeStyleArray[8] = FLUX = new CapeStyle(new ResourceLocation("relaxed/capes/Flux.png"));
            capeStyleArray[9] = LIQUIDBOUNCE = new CapeStyle(new ResourceLocation("relaxed/capes/LiquidBounce.png"));
            capeStyleArray[10] = NOVOLINE = new CapeStyle(new ResourceLocation("relaxed/capes/Novoline.png"));
            capeStyleArray[11] = SPECIAL1 = new CapeStyle(new ResourceLocation("relaxed/capes/special1.png"));
            capeStyleArray[12] = SPECIAL2 = new CapeStyle(new ResourceLocation("relaxed/capes/special2.png"));
            $VALUES = capeStyleArray;
        }

        @NotNull
        public final ResourceLocation getLocation() {
            return this.location;
        }

        private CapeStyle(ResourceLocation location) {
            this.location = location;
        }

        public static CapeStyle[] values() {
            return (CapeStyle[])$VALUES.clone();
        }

        public static CapeStyle valueOf(String string) {
            return Enum.valueOf(CapeStyle.class, string);
        }
    }
}

