/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module.modules.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.enums.BlockType;
import net.ccbluex.liquidbounce.api.enums.EnumFacingType;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IPlayerControllerMP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.util.IEnumFacing;
import net.ccbluex.liquidbounce.api.minecraft.util.IMovingObjectPosition;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MotionEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.player.Blink;
import net.ccbluex.liquidbounce.features.module.modules.world.ChestAura$WhenMappings;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.RotationUtils;
import net.ccbluex.liquidbounce.utils.VecRotation;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import net.ccbluex.liquidbounce.utils.extensions.BlockExtensionKt;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BlockValue;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import org.jetbrains.annotations.NotNull;

@ModuleInfo(name="ChestAura", description="Automatically opens chests around you.", category=ModuleCategory.WORLD)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/world/ChestAura;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "chestValue", "Lnet/ccbluex/liquidbounce/value/BlockValue;", "clickedBlocks", "", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "getClickedBlocks", "()Ljava/util/List;", "currentBlock", "delayValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "rangeValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "rotationsValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "throughWallsValue", "timer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "visualSwing", "onDisable", "", "onMotion", "event", "Lnet/ccbluex/liquidbounce/event/MotionEvent;", "Relaxed"})
public final class ChestAura
extends Module {
    private static final FloatValue rangeValue;
    private static final IntegerValue delayValue;
    private static final BoolValue throughWallsValue;
    private static final BoolValue visualSwing;
    private static final BlockValue chestValue;
    private static final BoolValue rotationsValue;
    private static WBlockPos currentBlock;
    private static final MSTimer timer;
    @NotNull
    private static final List<WBlockPos> clickedBlocks;
    public static final ChestAura INSTANCE;

    @NotNull
    public final List<WBlockPos> getClickedBlocks() {
        return clickedBlocks;
    }

    @EventTarget
    public final void onMotion(@NotNull MotionEvent event) {
        block27: {
            block26: {
                Intrinsics.checkParameterIsNotNull(event, "event");
                if (LiquidBounce.INSTANCE.getModuleManager().get(Blink.class).getState()) break block26;
                Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
                if (module == null) {
                    throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
                }
                if (!((KillAura)module).isBlockingChestAura()) break block27;
            }
            return;
        }
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        IWorldClient theWorld = iWorldClient;
        switch (ChestAura$WhenMappings.$EnumSwitchMapping$0[event.getEventState().ordinal()]) {
            case 1: {
                Object v4;
                Map.Entry it;
                Map.Entry element$iv$iv;
                Map $this$filterTo$iv$iv;
                if (MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen())) {
                    timer.reset();
                }
                float radius = ((Number)rangeValue.get()).floatValue() + 1.0f;
                WVec3 eyesPos = new WVec3(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() + (double)thePlayer.getEyeHeight(), thePlayer.getPosZ());
                Map $this$filter$iv = BlockUtils.searchBlocks((int)radius);
                boolean $i$f$filter = false;
                Map map = $this$filter$iv;
                Map destination$iv$iv = new LinkedHashMap();
                boolean $i$f$filterTo = false;
                Map map2 = $this$filterTo$iv$iv;
                boolean bl = false;
                Iterator iterator2 = map2.entrySet().iterator();
                while (iterator2.hasNext()) {
                    it = element$iv$iv = iterator2.next();
                    boolean bl2 = false;
                    if (!(MinecraftInstance.functions.getIdFromBlock((IBlock)it.getValue()) == ((Number)chestValue.get()).intValue() && !clickedBlocks.contains(it.getKey()) && BlockUtils.getCenterDistance((WBlockPos)it.getKey()) < ((Number)rangeValue.get()).doubleValue())) continue;
                    destination$iv$iv.put(element$iv$iv.getKey(), element$iv$iv.getValue());
                }
                $this$filter$iv = destination$iv$iv;
                $i$f$filter = false;
                $this$filterTo$iv$iv = $this$filter$iv;
                destination$iv$iv = new LinkedHashMap();
                $i$f$filterTo = false;
                map2 = $this$filterTo$iv$iv;
                bl = false;
                iterator2 = map2.entrySet().iterator();
                while (iterator2.hasNext()) {
                    WBlockPos blockPos;
                    IMovingObjectPosition movingObjectPosition;
                    it = element$iv$iv = iterator2.next();
                    boolean bl3 = false;
                    boolean bl4 = ((Boolean)throughWallsValue.get()).booleanValue() ? true : (movingObjectPosition = theWorld.rayTraceBlocks(eyesPos, BlockExtensionKt.getVec(blockPos = (WBlockPos)it.getKey()), false, true, false)) != null && Intrinsics.areEqual(movingObjectPosition.getBlockPos(), blockPos);
                    if (!bl4) continue;
                    destination$iv$iv.put(element$iv$iv.getKey(), element$iv$iv.getValue());
                }
                Map $this$minBy$iv = destination$iv$iv;
                boolean $i$f$minBy = false;
                Iterable $this$minBy$iv$iv = $this$minBy$iv.entrySet();
                boolean $i$f$minBy2 = false;
                Iterator iterator$iv$iv = $this$minBy$iv$iv.iterator();
                if (!iterator$iv$iv.hasNext()) {
                    v4 = null;
                } else {
                    Object minElem$iv$iv = iterator$iv$iv.next();
                    if (!iterator$iv$iv.hasNext()) {
                        v4 = minElem$iv$iv;
                    } else {
                        Map.Entry it2 = (Map.Entry)minElem$iv$iv;
                        boolean bl5 = false;
                        double minValue$iv$iv = BlockUtils.getCenterDistance((WBlockPos)it2.getKey());
                        do {
                            Object e$iv$iv = iterator$iv$iv.next();
                            Map.Entry it3 = (Map.Entry)e$iv$iv;
                            $i$a$-minBy-ChestAura$onMotion$3 = false;
                            double v$iv$iv = BlockUtils.getCenterDistance((WBlockPos)it3.getKey());
                            if (Double.compare(minValue$iv$iv, v$iv$iv) <= 0) continue;
                            minElem$iv$iv = e$iv$iv;
                            minValue$iv$iv = v$iv$iv;
                        } while (iterator$iv$iv.hasNext());
                        v4 = minElem$iv$iv;
                    }
                }
                Map.Entry entry = v4;
                WBlockPos wBlockPos = currentBlock = entry != null ? (WBlockPos)entry.getKey() : null;
                if (!((Boolean)rotationsValue.get()).booleanValue()) break;
                WBlockPos wBlockPos2 = currentBlock;
                if (wBlockPos2 == null) {
                    return;
                }
                VecRotation vecRotation = RotationUtils.faceBlock(wBlockPos2);
                if (vecRotation == null) {
                    return;
                }
                RotationUtils.setTargetRotation(vecRotation.getRotation());
                break;
            }
            case 2: {
                if (currentBlock == null || !timer.hasTimePassed(((Number)delayValue.get()).intValue())) break;
                IPlayerControllerMP iPlayerControllerMP = MinecraftInstance.mc.getPlayerController();
                IWorldClient iWorldClient2 = MinecraftInstance.mc.getTheWorld();
                if (iWorldClient2 == null) {
                    Intrinsics.throwNpe();
                }
                IItemStack iItemStack = thePlayer.getHeldItem();
                WBlockPos wBlockPos = currentBlock;
                if (wBlockPos == null) {
                    Intrinsics.throwNpe();
                }
                IEnumFacing iEnumFacing = MinecraftInstance.classProvider.getEnumFacing(EnumFacingType.DOWN);
                WBlockPos wBlockPos3 = currentBlock;
                if (wBlockPos3 == null) {
                    Intrinsics.throwNpe();
                }
                if (!iPlayerControllerMP.onPlayerRightClick(thePlayer, iWorldClient2, iItemStack, wBlockPos, iEnumFacing, BlockExtensionKt.getVec(wBlockPos3))) break;
                if (((Boolean)visualSwing.get()).booleanValue()) {
                    thePlayer.swingItem();
                } else {
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketAnimation());
                }
                WBlockPos wBlockPos4 = currentBlock;
                if (wBlockPos4 == null) {
                    Intrinsics.throwNpe();
                }
                clickedBlocks.add(wBlockPos4);
                currentBlock = null;
                timer.reset();
                break;
            }
        }
    }

    @Override
    public void onDisable() {
        clickedBlocks.clear();
    }

    private ChestAura() {
    }

    static {
        ChestAura chestAura;
        INSTANCE = chestAura = new ChestAura();
        rangeValue = new FloatValue("Range", 5.0f, 1.0f, 6.0f);
        delayValue = new IntegerValue("Delay", 100, 50, 200);
        throughWallsValue = new BoolValue("ThroughWalls", true);
        visualSwing = new BoolValue("VisualSwing", true);
        chestValue = new BlockValue("Chest", MinecraftInstance.functions.getIdFromBlock(MinecraftInstance.classProvider.getBlockEnum(BlockType.CHEST)));
        rotationsValue = new BoolValue("Rotations", true);
        timer = new MSTimer();
        boolean bl = false;
        clickedBlocks = new ArrayList();
    }
}

