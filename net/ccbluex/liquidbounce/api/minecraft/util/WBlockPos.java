/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.api.minecraft.util;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.enums.EnumFacingType;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.util.IEnumFacing;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3i;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\n\u0012\u0006\u0010\u0005\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\nJ\u0006\u0010\u000f\u001a\u00020\u0000J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u0006\u0010\u0011\u001a\u00020\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u0014\u001a\u00020\u0000J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u001a\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0010\u001a\u00020\nH\u0007J\u0006\u0010\u0018\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u001a\u001a\u00020\u0000J\u0006\u0010\u001b\u001a\u00020\u0000J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u0006\u0010\u001c\u001a\u00020\u0000J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nR\u000e\u0010\f\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WVec3i;", "x", "", "y", "z", "(DDD)V", "source", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;)V", "", "(III)V", "Z_MASK", "", "add", "down", "n", "east", "getBlock", "Lnet/ccbluex/liquidbounce/api/minecraft/client/block/IBlock;", "north", "offset", "side", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IEnumFacing;", "south", "subtract", "p_subtract_1_", "up", "west", "Companion", "Relaxed"})
public class WBlockPos
extends WVec3i {
    private final long Z_MASK = 0L;
    @NotNull
    private static final WBlockPos ORIGIN;
    private static final int NUM_X_BITS;
    private static final int NUM_Z_BITS = 0;
    private static final int NUM_Y_BITS = 0;
    private static final int Y_SHIFT = 0;
    private static final int X_SHIFT = 0;
    public static final Companion Companion;

    @NotNull
    public final WBlockPos add(int x, int y, int z) {
        return x == 0 && y == 0 && z == 0 ? this : new WBlockPos(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    @Nullable
    public final WBlockPos subtract(@NotNull WBlockPos p_subtract_1_) {
        Intrinsics.checkParameterIsNotNull(p_subtract_1_, "p_subtract_1_");
        return this.add(-p_subtract_1_.getX(), -p_subtract_1_.getY(), -p_subtract_1_.getZ());
    }

    @JvmOverloads
    @NotNull
    public final WBlockPos offset(@NotNull IEnumFacing side, int n) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        return n == 0 ? this : new WBlockPos(this.getX() + side.getDirectionVec().getX() * n, this.getY() + side.getDirectionVec().getY() * n, this.getZ() + side.getDirectionVec().getZ() * n);
    }

    public static /* synthetic */ WBlockPos offset$default(WBlockPos wBlockPos, IEnumFacing iEnumFacing, int n, int n2, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: offset");
        }
        if ((n2 & 2) != 0) {
            n = 1;
        }
        return wBlockPos.offset(iEnumFacing, n);
    }

    @JvmOverloads
    @NotNull
    public final WBlockPos offset(@NotNull IEnumFacing side) {
        return WBlockPos.offset$default(this, side, 0, 2, null);
    }

    @NotNull
    public final WBlockPos up() {
        return this.up(1);
    }

    @NotNull
    public final WBlockPos up(int n) {
        return this.offset(WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.UP), n);
    }

    @NotNull
    public final WBlockPos down() {
        return this.down(1);
    }

    @NotNull
    public final WBlockPos down(int n) {
        return this.offset(WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.DOWN), n);
    }

    @NotNull
    public final WBlockPos west() {
        return this.west(1);
    }

    @NotNull
    public final WBlockPos west(int n) {
        return this.offset(WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.WEST), n);
    }

    @NotNull
    public final WBlockPos east() {
        return this.east(1);
    }

    @NotNull
    public final WBlockPos east(int n) {
        return this.offset(WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.EAST), n);
    }

    @NotNull
    public final WBlockPos north() {
        return this.north(1);
    }

    @NotNull
    public final WBlockPos north(int n) {
        return this.offset(WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.NORTH), n);
    }

    @NotNull
    public final WBlockPos south() {
        return this.south(1);
    }

    @NotNull
    public final WBlockPos south(int n) {
        return this.offset(WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.SOUTH), n);
    }

    @Nullable
    public final IBlock getBlock() {
        WBlockPos blockPos$iv = this;
        boolean $i$f$getBlock = false;
        Object object = MinecraftInstance.mc.getTheWorld();
        return object != null && (object = object.getBlockState(blockPos$iv)) != null ? object.getBlock() : null;
    }

    public WBlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public WBlockPos(double x, double y, double z) {
        WBlockPos wBlockPos = this;
        boolean bl = false;
        double d = Math.floor(x);
        int n = (int)d;
        bl = false;
        double d2 = Math.floor(y);
        int n2 = (int)d2;
        bl = false;
        double d3 = Math.floor(z);
        wBlockPos(n, n2, (int)d3);
    }

    public WBlockPos(@NotNull IEntity source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this(source.getPosX(), source.getPosY(), source.getPosZ());
    }

    static {
        Companion = new Companion(null);
        ORIGIN = new WBlockPos(0, 0, 0);
        NUM_X_BITS = 1 + MathHelper.func_151239_c((int)MathHelper.func_151236_b((int)30000000));
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006\u00a8\u0006\u0013"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos$Companion;", "", "()V", "NUM_X_BITS", "", "getNUM_X_BITS", "()I", "NUM_Y_BITS", "getNUM_Y_BITS", "NUM_Z_BITS", "getNUM_Z_BITS", "ORIGIN", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "getORIGIN", "()Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "X_SHIFT", "getX_SHIFT", "Y_SHIFT", "getY_SHIFT", "Relaxed"})
    public static final class Companion {
        @NotNull
        public final WBlockPos getORIGIN() {
            return ORIGIN;
        }

        public final int getNUM_X_BITS() {
            return NUM_X_BITS;
        }

        public final int getNUM_Z_BITS() {
            return NUM_Z_BITS;
        }

        public final int getNUM_Y_BITS() {
            return NUM_Y_BITS;
        }

        public final int getY_SHIFT() {
            return Y_SHIFT;
        }

        public final int getX_SHIFT() {
            return X_SHIFT;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

