/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.world;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import me.betterfps.LibGDXMath;
import me.betterfps.NewMCMath;
import me.betterfps.RivensFullMath;
import me.betterfps.RivensHalfMath;
import me.betterfps.RivensMath;
import me.betterfps.TaylorMath;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="BetterFPS", category=ModuleCategory.WORLD, description=":)")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\"\u00a2\u0006\u0002\u0010$J\u0015\u0010%\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\"\u00a2\u0006\u0002\u0010$R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006&"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/world/BetterFPS;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "cosMode", "Lnet/ccbluex/liquidbounce/value/ListValue;", "getCosMode", "()Lnet/ccbluex/liquidbounce/value/ListValue;", "libGDX", "Lme/betterfps/LibGDXMath;", "getLibGDX", "()Lme/betterfps/LibGDXMath;", "newMC", "Lme/betterfps/NewMCMath;", "getNewMC", "()Lme/betterfps/NewMCMath;", "rivens", "Lme/betterfps/RivensMath;", "getRivens", "()Lme/betterfps/RivensMath;", "rivensFull", "Lme/betterfps/RivensFullMath;", "getRivensFull", "()Lme/betterfps/RivensFullMath;", "rivensHalf", "Lme/betterfps/RivensHalfMath;", "getRivensHalf", "()Lme/betterfps/RivensHalfMath;", "sinMode", "getSinMode", "taylor", "Lme/betterfps/TaylorMath;", "getTaylor", "()Lme/betterfps/TaylorMath;", "cos", "", "value", "(F)Ljava/lang/Float;", "sin", "Relaxed"})
public final class BetterFPS
extends Module {
    @NotNull
    private final LibGDXMath libGDX = new LibGDXMath();
    @NotNull
    private final RivensFullMath rivensFull = new RivensFullMath();
    @NotNull
    private final RivensHalfMath rivensHalf = new RivensHalfMath();
    @NotNull
    private final RivensMath rivens = new RivensMath();
    @NotNull
    private final TaylorMath taylor = new TaylorMath();
    @NotNull
    private final NewMCMath newMC = new NewMCMath();
    @NotNull
    private final ListValue sinMode = new ListValue("SinMode", new String[]{"Vanilla", "Taylor", "LibGDX", "RivensFull", "RivensHalf", "Rivens", "Java", "1.16"}, "Vanilla");
    @NotNull
    private final ListValue cosMode = new ListValue("CosMode", new String[]{"Vanilla", "Taylor", "LibGDX", "RivensFull", "RivensHalf", "Rivens", "Java", "1.16"}, "Vanilla");

    @NotNull
    public final LibGDXMath getLibGDX() {
        return this.libGDX;
    }

    @NotNull
    public final RivensFullMath getRivensFull() {
        return this.rivensFull;
    }

    @NotNull
    public final RivensHalfMath getRivensHalf() {
        return this.rivensHalf;
    }

    @NotNull
    public final RivensMath getRivens() {
        return this.rivens;
    }

    @NotNull
    public final TaylorMath getTaylor() {
        return this.taylor;
    }

    @NotNull
    public final NewMCMath getNewMC() {
        return this.newMC;
    }

    @NotNull
    public final ListValue getSinMode() {
        return this.sinMode;
    }

    @NotNull
    public final ListValue getCosMode() {
        return this.cosMode;
    }

    @Nullable
    public final Float sin(float value) {
        Float f;
        String string = (String)this.sinMode.get();
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        switch (string3) {
            case "taylor": {
                f = Float.valueOf(this.taylor.sin(value));
                break;
            }
            case "libgdx": {
                f = Float.valueOf(this.libGDX.sin(value));
                break;
            }
            case "rivensfull": {
                f = Float.valueOf(this.rivensFull.sin(value));
                break;
            }
            case "rivenshalf": {
                f = Float.valueOf(this.rivensHalf.sin(value));
                break;
            }
            case "rivens": {
                f = Float.valueOf(this.rivens.sin(value));
                break;
            }
            case "java": {
                double d = value;
                boolean bl2 = false;
                f = Float.valueOf((float)Math.sin(d));
                break;
            }
            case "1.16": {
                f = Float.valueOf(this.newMC.sin(value));
                break;
            }
            default: {
                f = null;
            }
        }
        return f;
    }

    @Nullable
    public final Float cos(float value) {
        Float f;
        String string = (String)this.cosMode.get();
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        switch (string3) {
            case "taylor": {
                f = Float.valueOf(this.taylor.cos(value));
                break;
            }
            case "libgdx": {
                f = Float.valueOf(this.libGDX.cos(value));
                break;
            }
            case "rivensfull": {
                f = Float.valueOf(this.rivensFull.cos(value));
                break;
            }
            case "rivenshalf": {
                f = Float.valueOf(this.rivensHalf.cos(value));
                break;
            }
            case "rivens": {
                f = Float.valueOf(this.rivens.cos(value));
                break;
            }
            case "java": {
                double d = value;
                boolean bl2 = false;
                f = Float.valueOf((float)Math.cos(d));
                break;
            }
            case "1.16": {
                f = Float.valueOf(this.newMC.cos(value));
                break;
            }
            default: {
                f = null;
            }
        }
        return f;
    }
}

