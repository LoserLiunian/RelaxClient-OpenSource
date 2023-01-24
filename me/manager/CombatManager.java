/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package me.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.event.AttackEvent;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.MovementUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010%2\u0006\u0010.\u001a\u00020/J\b\u00100\u001a\u00020\u000bH\u0016J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0007J\u0010\u00105\u001a\u0002022\u0006\u00103\u001a\u000204H\u0007J\u0010\u00106\u001a\u0002022\u0006\u00103\u001a\u000207H\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\tR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0007\"\u0004\b#\u0010\tR\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0007\"\u0004\b,\u0010\t\u00a8\u00068"}, d2={"Lme/manager/CombatManager;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "()V", "hours", "", "getHours", "()I", "setHours", "(I)V", "inCombat", "", "getInCombat", "()Z", "setInCombat", "(Z)V", "kill", "getKill", "setKill", "killedEntities", "getKilledEntities", "setKilledEntities", "lastAttackTimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "minutes", "getMinutes", "setMinutes", "playedTime", "", "getPlayedTime", "()Ljava/lang/String;", "setPlayedTime", "(Ljava/lang/String;)V", "seconds", "getSeconds", "setSeconds", "target", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "getTarget", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "setTarget", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;)V", "ticks", "getTicks", "setTicks", "getNearByEntity", "radius", "", "handleEvents", "onAttack", "", "event", "Lnet/ccbluex/liquidbounce/event/AttackEvent;", "onKilled", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class CombatManager
extends MinecraftInstance
implements Listenable {
    private int kill;
    private boolean inCombat;
    private final MSTimer lastAttackTimer = new MSTimer();
    @Nullable
    private IEntityLivingBase target;
    private int killedEntities;
    private int ticks;
    @NotNull
    private String playedTime = "0h 0m 0s";
    private int seconds;
    private int minutes;
    private int hours;

    public final int getKill() {
        return this.kill;
    }

    public final void setKill(int n) {
        this.kill = n;
    }

    public final boolean getInCombat() {
        return this.inCombat;
    }

    public final void setInCombat(boolean bl) {
        this.inCombat = bl;
    }

    @Nullable
    public final IEntityLivingBase getTarget() {
        return this.target;
    }

    public final void setTarget(@Nullable IEntityLivingBase iEntityLivingBase) {
        this.target = iEntityLivingBase;
    }

    public final int getKilledEntities() {
        return this.killedEntities;
    }

    public final void setKilledEntities(int n) {
        this.killedEntities = n;
    }

    public final int getTicks() {
        return this.ticks;
    }

    public final void setTicks(int n) {
        this.ticks = n;
    }

    @NotNull
    public final String getPlayedTime() {
        return this.playedTime;
    }

    public final void setPlayedTime(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "<set-?>");
        this.playedTime = string;
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final void setSeconds(int n) {
        this.seconds = n;
    }

    public final int getMinutes() {
        return this.minutes;
    }

    public final void setMinutes(int n) {
        this.minutes = n;
    }

    public final int getHours() {
        return this.hours;
    }

    public final void setHours(int n) {
        this.hours = n;
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        block14: {
            block15: {
                Intrinsics.checkParameterIsNotNull(event, "event");
                ++this.ticks;
                if (this.ticks == 20) {
                    ++this.seconds;
                    this.ticks = 0;
                }
                if (this.seconds == 60) {
                    ++this.minutes;
                    this.seconds = 0;
                }
                if (this.minutes == 60) {
                    ++this.hours;
                    this.minutes = 0;
                }
                this.playedTime = String.valueOf(this.hours) + "h " + String.valueOf(this.minutes) + "m " + String.valueOf(this.seconds) + "s";
                if (MinecraftInstance.mc.getThePlayer() == null) {
                    return;
                }
                MovementUtils.INSTANCE.updateBlocksPerSecond();
                this.inCombat = false;
                if (!this.lastAttackTimer.hasTimePassed(1000L)) {
                    this.inCombat = true;
                    return;
                }
                IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
                if (iWorldClient == null) {
                    Intrinsics.throwNpe();
                }
                for (IEntity entity : iWorldClient.getLoadedEntityList()) {
                    if (!(entity instanceof IEntityLivingBase)) continue;
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!(entity.getDistanceToEntity(iEntityPlayerSP) < (float)7) || !EntityUtils.isSelected(entity, true)) continue;
                    this.inCombat = true;
                    break;
                }
                if (this.target == null) break block14;
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                IEntityLivingBase iEntityLivingBase = this.target;
                if (iEntityLivingBase == null) {
                    Intrinsics.throwNpe();
                }
                if (iEntityPlayerSP.getDistanceToEntity(iEntityLivingBase) > (float)7 || !this.inCombat) break block15;
                IEntityLivingBase iEntityLivingBase2 = this.target;
                if (iEntityLivingBase2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!iEntityLivingBase2.isDead()) break block14;
            }
            this.target = null;
        }
    }

    @EventTarget
    public final void onAttack(@NotNull AttackEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getTargetEntity() instanceof IEntityLivingBase && EntityUtils.isSelected(event.getTargetEntity(), true)) {
            this.target = (IEntityLivingBase)event.getTargetEntity();
        }
        this.lastAttackTimer.reset();
    }

    @EventTarget
    public final void onKilled(@NotNull AttackEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntity iEntity = event.getTargetEntity();
        if (iEntity == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase");
        }
        IEntityLivingBase target = (IEntityLivingBase)iEntity;
        if (!(target instanceof EntityPlayer)) {
            return;
        }
        ++this.killedEntities;
    }

    /*
     * WARNING - void declaration
     */
    @Nullable
    public final IEntityLivingBase getNearByEntity(float radius) {
        IEntityLivingBase iEntityLivingBase;
        try {
            void $this$filterTo$iv$iv;
            IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
            if (iWorldClient == null) {
                Intrinsics.throwNpe();
            }
            Iterable $this$filter$iv = iWorldClient.getLoadedEntityList();
            boolean $i$f$filter = false;
            Iterable iterable = $this$filter$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterTo = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                IEntity it = (IEntity)element$iv$iv;
                boolean bl = false;
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                if (!(iEntityPlayerSP.getDistanceToEntity(it) < radius && EntityUtils.isSelected(it, true))) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            Iterable $this$sortedBy$iv = (List)destination$iv$iv;
            boolean $i$f$sortedBy = false;
            iterable = $this$sortedBy$iv;
            boolean bl = false;
            Comparator comparator = new Comparator<T>(){

                public final int compare(T a, T b) {
                    boolean bl = false;
                    IEntity it = (IEntity)a;
                    boolean bl2 = false;
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        Intrinsics.throwNpe();
                    }
                    Comparable comparable = Float.valueOf(it.getDistanceToEntity(iEntityPlayerSP));
                    it = (IEntity)b;
                    Comparable comparable2 = comparable;
                    bl2 = false;
                    IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Float f = Float.valueOf(it.getDistanceToEntity(iEntityPlayerSP2));
                    return ComparisonsKt.compareValues(comparable2, (Comparable)f);
                }
            };
            iEntityLivingBase = (IEntityLivingBase)CollectionsKt.sortedWith(iterable, comparator).get(0);
        }
        catch (Exception e) {
            iEntityLivingBase = null;
        }
        return iEntityLivingBase;
    }

    @Override
    public boolean handleEvents() {
        return true;
    }
}

