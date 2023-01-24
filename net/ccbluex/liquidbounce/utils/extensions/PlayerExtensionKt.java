/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.utils.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.api.minecraft.util.IAxisAlignedBB;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007\u001a\n\u0010\t\u001a\u00020\n*\u00020\u000b\u001a\n\u0010\f\u001a\u00020\r*\u00020\u0007\u001a\n\u0010\u000e\u001a\u00020\r*\u00020\u000b\u001a\n\u0010\u000f\u001a\u00020\r*\u00020\u0007\u00a8\u0006\u0010"}, d2={"getNearestPointBB", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WVec3;", "eye", "box", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IAxisAlignedBB;", "getDistanceToEntityBox", "", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "entity", "getPing", "", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/player/IEntityPlayer;", "isAnimal", "", "isClientFriend", "isMob", "Relaxed"})
public final class PlayerExtensionKt {
    public static final double getDistanceToEntityBox(@NotNull IEntity $this$getDistanceToEntityBox, @NotNull IEntity entity) {
        Intrinsics.checkParameterIsNotNull($this$getDistanceToEntityBox, "$this$getDistanceToEntityBox");
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        WVec3 eyes = $this$getDistanceToEntityBox.getPositionEyes(1.0f);
        WVec3 pos = PlayerExtensionKt.getNearestPointBB(eyes, entity.getEntityBoundingBox());
        double d = pos.getXCoord() - eyes.getXCoord();
        boolean bl = false;
        double xDist = Math.abs(d);
        double d2 = pos.getYCoord() - eyes.getYCoord();
        boolean bl2 = false;
        double yDist = Math.abs(d2);
        double d3 = pos.getZCoord() - eyes.getZCoord();
        int n = 0;
        double zDist = Math.abs(d3);
        d3 = xDist;
        n = 2;
        boolean bl3 = false;
        double d4 = Math.pow(d3, n);
        d3 = yDist;
        n = 2;
        double d5 = d4;
        bl3 = false;
        double d6 = Math.pow(d3, n);
        d3 = zDist;
        n = 2;
        d5 += d6;
        bl3 = false;
        d6 = Math.pow(d3, n);
        d3 = d5 + d6;
        n = 0;
        return Math.sqrt(d3);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final WVec3 getNearestPointBB(@NotNull WVec3 eye, @NotNull IAxisAlignedBB box) {
        Intrinsics.checkParameterIsNotNull(eye, "eye");
        Intrinsics.checkParameterIsNotNull(box, "box");
        double[] origin = new double[]{eye.getXCoord(), eye.getYCoord(), eye.getZCoord()};
        double[] destMins = new double[]{box.getMinX(), box.getMinY(), box.getMinZ()};
        double[] destMaxs = new double[]{box.getMaxX(), box.getMaxY(), box.getMaxZ()};
        int n = 0;
        int n2 = 2;
        while (n <= n2) {
            void i;
            if (origin[i] > destMaxs[i]) {
                origin[i] = destMaxs[i];
            } else if (origin[i] < destMins[i]) {
                origin[i] = destMins[i];
            }
            ++i;
        }
        return new WVec3(origin[0], origin[1], origin[2]);
    }

    public static final int getPing(@NotNull IEntityPlayer $this$getPing) {
        INetworkPlayerInfo playerInfo;
        Intrinsics.checkParameterIsNotNull($this$getPing, "$this$getPing");
        INetworkPlayerInfo iNetworkPlayerInfo = playerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo($this$getPing.getUniqueID());
        return iNetworkPlayerInfo != null ? iNetworkPlayerInfo.getResponseTime() : 0;
    }

    public static final boolean isAnimal(@NotNull IEntity $this$isAnimal) {
        Intrinsics.checkParameterIsNotNull($this$isAnimal, "$this$isAnimal");
        return MinecraftInstance.classProvider.isEntityAnimal($this$isAnimal) || MinecraftInstance.classProvider.isEntitySquid($this$isAnimal) || MinecraftInstance.classProvider.isEntityGolem($this$isAnimal) || MinecraftInstance.classProvider.isEntityBat($this$isAnimal);
    }

    public static final boolean isMob(@NotNull IEntity $this$isMob) {
        Intrinsics.checkParameterIsNotNull($this$isMob, "$this$isMob");
        return MinecraftInstance.classProvider.isEntityMob($this$isMob) || MinecraftInstance.classProvider.isEntityVillager($this$isMob) || MinecraftInstance.classProvider.isEntitySlime($this$isMob) || MinecraftInstance.classProvider.isEntityGhast($this$isMob) || MinecraftInstance.classProvider.isEntityDragon($this$isMob) || MinecraftInstance.classProvider.isEntityShulker($this$isMob);
    }

    public static final boolean isClientFriend(@NotNull IEntityPlayer $this$isClientFriend) {
        Intrinsics.checkParameterIsNotNull($this$isClientFriend, "$this$isClientFriend");
        String string = $this$isClientFriend.getName();
        if (string == null) {
            return false;
        }
        String entityName = string;
        return LiquidBounce.INSTANCE.getFileManager().friendsConfig.isFriend(ColorUtils.stripColor(entityName));
    }
}

