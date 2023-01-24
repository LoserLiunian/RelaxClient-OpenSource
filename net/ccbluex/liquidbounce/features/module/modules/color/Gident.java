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
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="Gident", description="Custom", category=ModuleCategory.COLOR)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/color/Gident;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "Companion", "Relaxed"})
public final class Gident
extends Module {
    @JvmField
    @NotNull
    public static final IntegerValue gidentspeed;
    @JvmField
    @NotNull
    public static final IntegerValue redValue;
    @JvmField
    @NotNull
    public static final IntegerValue greenValue;
    @JvmField
    @NotNull
    public static final IntegerValue blueValue;
    @JvmField
    @NotNull
    public static final IntegerValue redValue2;
    @JvmField
    @NotNull
    public static final IntegerValue greenValue2;
    @JvmField
    @NotNull
    public static final IntegerValue blueValue2;
    public static final Companion Companion;

    static {
        Companion = new Companion(null);
        gidentspeed = new IntegerValue("GidentSpeed", 100, 1, 1000);
        redValue = new IntegerValue("Red", 255, 0, 255);
        greenValue = new IntegerValue("Green", 255, 0, 255);
        blueValue = new IntegerValue("Blue", 255, 0, 255);
        redValue2 = new IntegerValue("Red2", 255, 0, 255);
        greenValue2 = new IntegerValue("Green2", 255, 0, 255);
        blueValue2 = new IntegerValue("Blue2", 255, 0, 255);
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/color/Gident$Companion;", "", "()V", "blueValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "blueValue2", "gidentspeed", "greenValue", "greenValue2", "redValue", "redValue2", "Relaxed"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

