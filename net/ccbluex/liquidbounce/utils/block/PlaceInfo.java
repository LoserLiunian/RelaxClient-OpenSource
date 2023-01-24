/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.utils.block;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.enums.EnumFacingType;
import net.ccbluex.liquidbounce.api.minecraft.util.IEnumFacing;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0012"}, d2={"Lnet/ccbluex/liquidbounce/utils/block/PlaceInfo;", "", "blockPos", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "enumFacing", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IEnumFacing;", "vec3", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WVec3;", "(Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;Lnet/ccbluex/liquidbounce/api/minecraft/util/IEnumFacing;Lnet/ccbluex/liquidbounce/api/minecraft/util/WVec3;)V", "getBlockPos", "()Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "getEnumFacing", "()Lnet/ccbluex/liquidbounce/api/minecraft/util/IEnumFacing;", "getVec3", "()Lnet/ccbluex/liquidbounce/api/minecraft/util/WVec3;", "setVec3", "(Lnet/ccbluex/liquidbounce/api/minecraft/util/WVec3;)V", "Companion", "Relaxed"})
public final class PlaceInfo {
    @NotNull
    private final WBlockPos blockPos;
    @NotNull
    private final IEnumFacing enumFacing;
    @NotNull
    private WVec3 vec3;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final WBlockPos getBlockPos() {
        return this.blockPos;
    }

    @NotNull
    public final IEnumFacing getEnumFacing() {
        return this.enumFacing;
    }

    @NotNull
    public final WVec3 getVec3() {
        return this.vec3;
    }

    public final void setVec3(@NotNull WVec3 wVec3) {
        Intrinsics.checkParameterIsNotNull(wVec3, "<set-?>");
        this.vec3 = wVec3;
    }

    public PlaceInfo(@NotNull WBlockPos blockPos, @NotNull IEnumFacing enumFacing, @NotNull WVec3 vec3) {
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        Intrinsics.checkParameterIsNotNull(enumFacing, "enumFacing");
        Intrinsics.checkParameterIsNotNull(vec3, "vec3");
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
        this.vec3 = vec3;
    }

    public /* synthetic */ PlaceInfo(WBlockPos wBlockPos, IEnumFacing iEnumFacing, WVec3 wVec3, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 4) != 0) {
            wVec3 = new WVec3((double)wBlockPos.getX() + 0.5, (double)wBlockPos.getY() + 0.5, (double)wBlockPos.getZ() + 0.5);
        }
        this(wBlockPos, iEnumFacing, wVec3);
    }

    @JvmStatic
    @Nullable
    public static final PlaceInfo get(@NotNull WBlockPos blockPos) {
        return Companion.get(blockPos);
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2={"Lnet/ccbluex/liquidbounce/utils/block/PlaceInfo$Companion;", "", "()V", "get", "Lnet/ccbluex/liquidbounce/utils/block/PlaceInfo;", "blockPos", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "Relaxed"})
    public static final class Companion {
        @JvmStatic
        @Nullable
        public final PlaceInfo get(@NotNull WBlockPos blockPos) {
            Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
            if (BlockUtils.canBeClicked(blockPos.add(0, -1, 0))) {
                return new PlaceInfo(blockPos.add(0, -1, 0), WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.UP), null, 4, null);
            }
            if (BlockUtils.canBeClicked(blockPos.add(0, 0, 1))) {
                return new PlaceInfo(blockPos.add(0, 0, 1), WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.NORTH), null, 4, null);
            }
            if (BlockUtils.canBeClicked(blockPos.add(-1, 0, 0))) {
                return new PlaceInfo(blockPos.add(-1, 0, 0), WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.EAST), null, 4, null);
            }
            if (BlockUtils.canBeClicked(blockPos.add(0, 0, -1))) {
                return new PlaceInfo(blockPos.add(0, 0, -1), WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.SOUTH), null, 4, null);
            }
            return BlockUtils.canBeClicked(blockPos.add(1, 0, 0)) ? new PlaceInfo(blockPos.add(1, 0, 0), WrapperImpl.INSTANCE.getClassProvider().getEnumFacing(EnumFacingType.WEST), null, 4, null) : null;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

