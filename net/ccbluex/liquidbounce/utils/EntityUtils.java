/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.utils;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.combat.NoFriends;
import net.ccbluex.liquidbounce.features.module.modules.misc.AntiBot;
import net.ccbluex.liquidbounce.features.module.modules.misc.Teams;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0012\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0004H\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2={"Lnet/ccbluex/liquidbounce/utils/EntityUtils;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "()V", "targetAnimals", "", "targetDead", "targetInvisible", "targetMobs", "targetPlayer", "getPing", "", "entityPlayer", "Lnet/minecraft/entity/player/EntityPlayer;", "getPing2", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/player/IEntityPlayer;", "isFriend", "entity", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "isSelected", "canAttackCheck", "Relaxed"})
public final class EntityUtils
extends MinecraftInstance {
    @JvmField
    public static boolean targetInvisible;
    @JvmField
    public static boolean targetPlayer;
    @JvmField
    public static boolean targetMobs;
    @JvmField
    public static boolean targetAnimals;
    @JvmField
    public static boolean targetDead;
    public static final EntityUtils INSTANCE;

    @JvmStatic
    public static final boolean isSelected(@Nullable IEntity entity, boolean canAttackCheck) {
        block11: {
            block12: {
                if (!MinecraftInstance.classProvider.isEntityLivingBase(entity)) break block11;
                if (targetDead) break block12;
                IEntity iEntity = entity;
                if (iEntity == null) {
                    Intrinsics.throwNpe();
                }
                if (!iEntity.isEntityAlive()) break block11;
            }
            if (entity != null && Intrinsics.areEqual(entity, MinecraftInstance.mc.getThePlayer()) ^ true && (targetInvisible || !entity.isInvisible())) {
                if (targetPlayer && MinecraftInstance.classProvider.isEntityPlayer(entity)) {
                    IEntityPlayer entityPlayer = entity.asEntityPlayer();
                    if (canAttackCheck) {
                        if (AntiBot.isBot(entityPlayer)) {
                            return false;
                        }
                        if (PlayerExtensionKt.isClientFriend(entityPlayer) && !LiquidBounce.INSTANCE.getModuleManager().getModule(NoFriends.class).getState()) {
                            return false;
                        }
                        if (entityPlayer.isSpectator()) {
                            return false;
                        }
                        Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(Teams.class);
                        if (module == null) {
                            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.misc.Teams");
                        }
                        Teams teams = (Teams)module;
                        return !teams.getState() || !teams.isInYourTeam(entityPlayer);
                    }
                    return true;
                }
                return targetMobs && PlayerExtensionKt.isMob(entity) || targetAnimals && PlayerExtensionKt.isAnimal(entity);
            }
        }
        return false;
    }

    public final int getPing2(@Nullable IEntityPlayer entityPlayer) {
        INetworkPlayerInfo networkPlayerInfo;
        if (entityPlayer == null) {
            return 0;
        }
        INetworkPlayerInfo iNetworkPlayerInfo = networkPlayerInfo = MinecraftInstance.mc.getNetHandler().getPlayerInfo(entityPlayer.getUniqueID());
        return iNetworkPlayerInfo != null ? iNetworkPlayerInfo.getResponseTime() : 0;
    }

    public final boolean isFriend(@NotNull IEntity entity) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        return MinecraftInstance.classProvider.isEntityPlayer(entity) && entity.getName() != null && LiquidBounce.INSTANCE.getFileManager().friendsConfig.isFriend(ColorUtils.stripColor(entity.getName()));
    }

    public final int getPing(@Nullable EntityPlayer entityPlayer) {
        INetworkPlayerInfo networkPlayerInfo;
        if (entityPlayer == null) {
            return 0;
        }
        IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
        UUID uUID = entityPlayer.func_110124_au();
        Intrinsics.checkExpressionValueIsNotNull(uUID, "entityPlayer.uniqueID");
        INetworkPlayerInfo iNetworkPlayerInfo = networkPlayerInfo = iINetHandlerPlayClient.getPlayerInfo(uUID);
        return iNetworkPlayerInfo != null ? iNetworkPlayerInfo.getResponseTime() : 0;
    }

    private EntityUtils() {
    }

    static {
        EntityUtils entityUtils;
        INSTANCE = entityUtils = new EntityUtils();
        targetPlayer = true;
        targetMobs = true;
    }
}

