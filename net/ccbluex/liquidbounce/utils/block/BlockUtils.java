/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.utils.block;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.block.material.IMaterial;
import net.ccbluex.liquidbounce.api.minecraft.block.state.IIBlockState;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.util.IAxisAlignedBB;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.api.minecraft.world.IWorld;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J*\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\n\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00040\u000bj\u0002`\rH\u0007J*\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\n\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00040\u000bj\u0002`\rH\u0007J\u0013\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\bJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\bJ\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\bJ\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0011\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\bJ\u001c\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u001d2\u0006\u0010\u001e\u001a\u00020\u0013H\u0007\u00a8\u0006\u001f"}, d2={"Lnet/ccbluex/liquidbounce/utils/block/BlockUtils;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "()V", "canBeClicked", "", "blockPos", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "collideBlock", "axisAlignedBB", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IAxisAlignedBB;", "collide", "Lkotlin/Function1;", "Lnet/ccbluex/liquidbounce/api/minecraft/client/block/IBlock;", "Lnet/ccbluex/liquidbounce/utils/block/Collidable;", "collideBlockIntersects", "getBlock", "getBlockName", "", "id", "", "getCenterDistance", "", "getMaterial", "Lnet/ccbluex/liquidbounce/api/minecraft/block/material/IMaterial;", "getState", "Lnet/ccbluex/liquidbounce/api/minecraft/block/state/IIBlockState;", "isFullBlock", "isReplaceable", "searchBlocks", "", "radius", "Relaxed"})
public final class BlockUtils
extends MinecraftInstance {
    public static final BlockUtils INSTANCE;

    @JvmStatic
    @Nullable
    public static final IBlock getBlock(@NotNull WBlockPos blockPos) {
        int $i$f$getBlock = 0;
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        Object object = MinecraftInstance.mc.getTheWorld();
        return object != null && (object = object.getBlockState(blockPos)) != null ? object.getBlock() : null;
    }

    @JvmStatic
    @Nullable
    public static final IMaterial getMaterial(@NotNull WBlockPos blockPos) {
        int $i$f$getMaterial = 0;
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        boolean $i$f$getState = false;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        IIBlockState state = iWorldClient != null ? iWorldClient.getBlockState(blockPos) : null;
        Object object = state;
        return object != null && (object = object.getBlock()) != null ? object.getMaterial(state) : null;
    }

    @JvmStatic
    public static final boolean isReplaceable(@NotNull WBlockPos blockPos) {
        int $i$f$isReplaceable = 0;
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        boolean $i$f$getMaterial = false;
        boolean $i$f$getState = false;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        IIBlockState state$iv = iWorldClient != null ? iWorldClient.getBlockState(blockPos) : null;
        Object object = state$iv;
        IMaterial iMaterial = object != null && (object = object.getBlock()) != null ? object.getMaterial(state$iv) : null;
        return iMaterial != null ? iMaterial.isReplaceable() : false;
    }

    @JvmStatic
    @Nullable
    public static final IIBlockState getState(@NotNull WBlockPos blockPos) {
        int $i$f$getState = 0;
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        return iWorldClient != null ? iWorldClient.getBlockState(blockPos) : null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean canBeClicked(@NotNull WBlockPos blockPos) {
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        boolean $i$f$getBlock = false;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        Object object = iWorldClient;
        if (iWorldClient == null) return false;
        IIBlockState iIBlockState = object.getBlockState(blockPos);
        object = iIBlockState;
        if (iIBlockState == null) return false;
        IBlock iBlock = object.getBlock();
        if (iBlock == null) return false;
        IBlock iBlock2 = iBlock;
        boolean $i$f$getState = false;
        IWorldClient iWorldClient2 = MinecraftInstance.mc.getTheWorld();
        IIBlockState iIBlockState2 = iWorldClient2 != null ? iWorldClient2.getBlockState(blockPos) : null;
        boolean bl = iBlock2.canCollideCheck(iIBlockState2, false);
        if (!bl) return false;
        IWorldClient iWorldClient3 = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient3 == null) {
            Intrinsics.throwNpe();
        }
        if (!iWorldClient3.getWorldBorder().contains(blockPos)) return false;
        return true;
    }

    @JvmStatic
    @NotNull
    public static final String getBlockName(int id) {
        IBlock iBlock = MinecraftInstance.functions.getBlockById(id);
        if (iBlock == null) {
            Intrinsics.throwNpe();
        }
        return iBlock.getLocalizedName();
    }

    @JvmStatic
    public static final boolean isFullBlock(@NotNull WBlockPos blockPos) {
        Object object;
        block6: {
            block5: {
                IIBlockState iIBlockState;
                Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
                boolean $i$f$getBlock = false;
                Object object2 = MinecraftInstance.mc.getTheWorld();
                if ((object2 != null && (object2 = object2.getBlockState(blockPos)) != null ? object2.getBlock() : (object = null)) == null) break block5;
                IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
                if (iWorldClient == null) {
                    Intrinsics.throwNpe();
                }
                WBlockPos wBlockPos = blockPos;
                IWorld iWorld = iWorldClient;
                IBlock iBlock = object;
                boolean $i$f$getState = false;
                IWorldClient iWorldClient2 = MinecraftInstance.mc.getTheWorld();
                IIBlockState iIBlockState2 = iIBlockState = iWorldClient2 != null ? iWorldClient2.getBlockState(blockPos) : null;
                if (iIBlockState2 == null) {
                    return false;
                }
                object = iBlock.getCollisionBoundingBox(iWorld, wBlockPos, iIBlockState2);
                if (object != null) break block6;
            }
            return false;
        }
        Object axisAlignedBB = object;
        return axisAlignedBB.getMaxX() - axisAlignedBB.getMinX() == 1.0 && axisAlignedBB.getMaxY() - axisAlignedBB.getMinY() == 1.0 && axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ() == 1.0;
    }

    @JvmStatic
    public static final double getCenterDistance(@NotNull WBlockPos blockPos) {
        Intrinsics.checkParameterIsNotNull(blockPos, "blockPos");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        return iEntityPlayerSP.getDistance((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
    }

    /*
     * WARNING - void declaration
     */
    @JvmStatic
    @NotNull
    public static final Map<WBlockPos, IBlock> searchBlocks(int radius) {
        boolean bl = false;
        Map blocks = new LinkedHashMap();
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            return blocks;
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        int n = radius;
        int n2 = -radius + 1;
        if (n >= n2) {
            while (true) {
                void x;
                int n3;
                int n4;
                if ((n4 = radius) >= (n3 = -radius + 1)) {
                    while (true) {
                        void y;
                        int n5;
                        int n6;
                        if ((n6 = radius) >= (n5 = -radius + 1)) {
                            while (true) {
                                void z;
                                WBlockPos blockPos = new WBlockPos((int)thePlayer.getPosX() + x, (int)thePlayer.getPosY() + y, (int)thePlayer.getPosZ() + z);
                                boolean $i$f$getBlock = false;
                                Object object = MinecraftInstance.mc.getTheWorld();
                                if ((object != null && (object = object.getBlockState(blockPos)) != null ? object.getBlock() : null) == null) {
                                } else {
                                    IBlock block;
                                    block = block;
                                    blocks.put(blockPos, block);
                                }
                                if (z == n5) break;
                                --z;
                            }
                        }
                        if (y == n3) break;
                        --y;
                    }
                }
                if (x == n2) break;
                --x;
            }
        }
        return blocks;
    }

    /*
     * WARNING - void declaration
     */
    @JvmStatic
    public static final boolean collideBlock(@NotNull IAxisAlignedBB axisAlignedBB, @NotNull Function1<? super IBlock, Boolean> collide) {
        Intrinsics.checkParameterIsNotNull(axisAlignedBB, "axisAlignedBB");
        Intrinsics.checkParameterIsNotNull(collide, "collide");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        double d = thePlayer.getEntityBoundingBox().getMinX();
        boolean bl = false;
        long l = (int)Math.floor(d);
        d = thePlayer.getEntityBoundingBox().getMaxX();
        bl = false;
        long l2 = (long)((int)Math.floor(d)) + 1L;
        while (l < l2) {
            void x;
            double d2 = thePlayer.getEntityBoundingBox().getMinZ();
            boolean bl2 = false;
            int n = (int)Math.floor(d2);
            d2 = thePlayer.getEntityBoundingBox().getMaxZ();
            bl2 = false;
            int n2 = (int)Math.floor(d2) + 1;
            while (n < n2) {
                IBlock block;
                void z;
                WBlockPos blockPos$iv = new WBlockPos((double)x, axisAlignedBB.getMinY(), (double)z);
                boolean $i$f$getBlock = false;
                Object object = MinecraftInstance.mc.getTheWorld();
                IBlock iBlock = object != null && (object = object.getBlockState(blockPos$iv)) != null ? object.getBlock() : (block = null);
                if (!collide.invoke(block).booleanValue()) {
                    return false;
                }
                ++z;
            }
            l = x + 1L;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    @JvmStatic
    public static final boolean collideBlockIntersects(@NotNull IAxisAlignedBB axisAlignedBB, @NotNull Function1<? super IBlock, Boolean> collide) {
        Intrinsics.checkParameterIsNotNull(axisAlignedBB, "axisAlignedBB");
        Intrinsics.checkParameterIsNotNull(collide, "collide");
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        IWorldClient world = iWorldClient;
        double d = thePlayer.getEntityBoundingBox().getMinX();
        boolean bl = false;
        int n = (int)Math.floor(d);
        d = thePlayer.getEntityBoundingBox().getMaxX();
        bl = false;
        int n2 = (int)Math.floor(d) + 1;
        while (n < n2) {
            void x;
            double d2 = thePlayer.getEntityBoundingBox().getMinZ();
            boolean bl2 = false;
            int n3 = (int)Math.floor(d2);
            d2 = thePlayer.getEntityBoundingBox().getMaxZ();
            bl2 = false;
            int n4 = (int)Math.floor(d2) + 1;
            while (n3 < n4) {
                IBlock block;
                void z;
                WBlockPos blockPos = new WBlockPos((double)x, axisAlignedBB.getMinY(), (double)z);
                boolean $i$f$getBlock = false;
                Object object = MinecraftInstance.mc.getTheWorld();
                IBlock iBlock = object != null && (object = object.getBlockState(blockPos)) != null ? object.getBlock() : (block = null);
                if (collide.invoke(block).booleanValue()) {
                    boolean $i$f$getState = false;
                    IWorldClient iWorldClient2 = MinecraftInstance.mc.getTheWorld();
                    Object object2 = iWorldClient2 != null ? iWorldClient2.getBlockState(blockPos) : null;
                    if (object2 != null) {
                        IIBlockState iIBlockState = object2;
                        boolean bl3 = false;
                        boolean bl4 = false;
                        IIBlockState it = iIBlockState;
                        boolean bl5 = false;
                        IBlock iBlock2 = block;
                        object2 = iBlock2 != null ? iBlock2.getCollisionBoundingBox(world, blockPos, it) : null;
                        if (object2 == null) {
                        } else {
                            IIBlockState boundingBox = object2;
                            if (thePlayer.getEntityBoundingBox().intersectsWith((IAxisAlignedBB)((Object)boundingBox))) {
                                return true;
                            }
                        }
                    }
                }
                ++z;
            }
            ++x;
        }
        return false;
    }

    private BlockUtils() {
    }

    static {
        BlockUtils blockUtils;
        INSTANCE = blockUtils = new BlockUtils();
    }
}

