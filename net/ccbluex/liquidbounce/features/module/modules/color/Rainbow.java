/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.color;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Rainbow", category=ModuleCategory.COLOR, canEnable=false, description="Custom")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/color/Rainbow;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "Companion", "Relaxed"})
public final class Rainbow
extends Module {
    @JvmField
    @NotNull
    public static final FloatValue rainbowStart;
    @JvmField
    @NotNull
    public static final FloatValue rainbowStop;
    @JvmField
    @NotNull
    public static final FloatValue rainbowSaturation;
    @JvmField
    @NotNull
    public static final FloatValue rainbowBrightness;
    @JvmField
    @NotNull
    public static final IntegerValue rainbowSpeed;
    public static final Companion Companion;

    static {
        Companion = new Companion(null);
        rainbowStart = new FloatValue("Start", 0.1f, 0.0f, 1.0f);
        rainbowStop = new FloatValue("Stop", 0.2f, 0.0f, 1.0f);
        rainbowSaturation = new FloatValue("Saturation", 0.7f, 0.0f, 1.0f);
        rainbowBrightness = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
        rainbowSpeed = new IntegerValue("Speed", 1500, 500, 7000);
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/color/Rainbow$Companion;", "", "()V", "rainbowBrightness", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "rainbowSaturation", "rainbowSpeed", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "rainbowStart", "rainbowStop", "Relaxed"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

