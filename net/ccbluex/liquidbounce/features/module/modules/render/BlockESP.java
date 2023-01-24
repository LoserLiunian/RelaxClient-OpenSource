/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.enums.BlockType;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BlockValue;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="BlockESP", description="Allows you to see a selected block through walls.", category=ModuleCategory.RENDER)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J\u0012\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/BlockESP;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "blockLimitValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "blockValue", "Lnet/ccbluex/liquidbounce/value/BlockValue;", "colorBlueValue", "colorGreenValue", "colorRainbow", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "colorRedValue", "modeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "posList", "", "Lnet/ccbluex/liquidbounce/api/minecraft/util/WBlockPos;", "radiusValue", "searchTimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "tag", "", "getTag", "()Ljava/lang/String;", "thread", "Ljava/lang/Thread;", "onRender3D", "", "event", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class BlockESP
extends Module {
    private final ListValue modeValue = new ListValue("Mode", new String[]{"Box", "2D"}, "Box");
    private final BlockValue blockValue = new BlockValue("Block", 168);
    private final IntegerValue radiusValue = new IntegerValue("Radius", 40, 5, 120);
    private final IntegerValue blockLimitValue = new IntegerValue("BlockLimit", 256, 0, 2056);
    private final IntegerValue colorRedValue = new IntegerValue("R", 255, 0, 255);
    private final IntegerValue colorGreenValue = new IntegerValue("G", 179, 0, 255);
    private final IntegerValue colorBlueValue = new IntegerValue("B", 72, 0, 255);
    private final BoolValue colorRainbow = new BoolValue("Rainbow", false);
    private final MSTimer searchTimer = new MSTimer();
    private final List<WBlockPos> posList = new ArrayList();
    private Thread thread;

    @EventTarget
    public final void onUpdate(@Nullable UpdateEvent event) {
        block6: {
            block7: {
                if (!this.searchTimer.hasTimePassed(1000L)) break block6;
                if (this.thread == null) break block7;
                Thread thread2 = this.thread;
                if (thread2 == null) {
                    Intrinsics.throwNpe();
                }
                if (thread2.isAlive()) break block6;
            }
            int radius = ((Number)this.radiusValue.get()).intValue();
            IBlock selectedBlock = MinecraftInstance.functions.getBlockById(((Number)this.blockValue.get()).intValue());
            if (selectedBlock == null || Intrinsics.areEqual(selectedBlock, MinecraftInstance.classProvider.getBlockEnum(BlockType.AIR))) {
                return;
            }
            Thread thread3 = this.thread = new Thread(new Runnable(this, radius, selectedBlock){
                final /* synthetic */ BlockESP this$0;
                final /* synthetic */ int $radius;
                final /* synthetic */ IBlock $selectedBlock;

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 * WARNING - void declaration
                 */
                public final void run() {
                    int n;
                    List blockList = new ArrayList<E>();
                    int n2 = -this.$radius;
                    int n3 = this.$radius;
                    while (n2 < n3) {
                        void x;
                        n = this.$radius;
                        int n4 = -this.$radius + 1;
                        if (n >= n4) {
                            while (true) {
                                void y;
                                int n5 = -this.$radius;
                                int n6 = this.$radius;
                                while (n5 < n6) {
                                    IBlock block;
                                    void z;
                                    IEntityPlayerSP thePlayer;
                                    if (MinecraftInstance.mc.getThePlayer() == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    int xPos = (int)thePlayer.getPosX() + x;
                                    int yPos = (int)thePlayer.getPosY() + y;
                                    int zPos = (int)thePlayer.getPosZ() + z;
                                    WBlockPos blockPos = new WBlockPos(xPos, yPos, zPos);
                                    boolean $i$f$getBlock = false;
                                    Object object = MinecraftInstance.mc.getTheWorld();
                                    IBlock iBlock = object != null && (object = object.getBlockState(blockPos)) != null ? object.getBlock() : (block = null);
                                    if (Intrinsics.areEqual(block, this.$selectedBlock) && blockList.size() < ((Number)BlockESP.access$getBlockLimitValue$p(this.this$0).get()).intValue()) {
                                        blockList.add(blockPos);
                                    }
                                    ++z;
                                }
                                if (y == n4) break;
                                --y;
                            }
                        }
                        ++x;
                    }
                    BlockESP.access$getSearchTimer$p(this.this$0).reset();
                    List list = BlockESP.access$getPosList$p(this.this$0);
                    n3 = 0;
                    n = 0;
                    synchronized (list) {
                        boolean bl = false;
                        BlockESP.access$getPosList$p(this.this$0).clear();
                        n = (int)(BlockESP.access$getPosList$p(this.this$0).addAll(blockList) ? 1 : 0);
                    }
                }
                {
                    this.this$0 = blockESP;
                    this.$radius = n;
                    this.$selectedBlock = iBlock;
                }
            }, "BlockESP-BlockFinder");
            if (thread3 == null) {
                Intrinsics.throwNpe();
            }
            thread3.start();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    @EventTarget
    public final void onRender3D(@Nullable Render3DEvent event) {
        var2_2 = this.posList;
        var3_3 = false;
        var4_4 = false;
        synchronized (var2_2) {
            $i$a$-synchronized-BlockESP$onRender3D$1 = false;
            color = (Boolean)this.colorRainbow.get() != false ? ColorUtils.rainbow() : new Color(((Number)this.colorRedValue.get()).intValue(), ((Number)this.colorGreenValue.get()).intValue(), ((Number)this.colorBlueValue.get()).intValue());
            for (WBlockPos blockPos : this.posList) {
                var9_11 = (String)this.modeValue.get();
                var10_12 = false;
                v0 = var9_11;
                if (v0 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                v1 = v0.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(v1, "(this as java.lang.String).toLowerCase()");
                var9_11 = v1;
                switch (var9_11.hashCode()) {
                    case 1650: {
                        if (!var9_11.equals("2d")) ** break;
                        break;
                    }
                    case 97739: {
                        if (!var9_11.equals("box")) ** break;
                        RenderUtils.drawBlockBox(blockPos, color, true);
                        ** break;
                    }
                }
                v2 = color.getRGB();
                v3 = Color.BLACK;
                Intrinsics.checkExpressionValueIsNotNull(v3, "Color.BLACK");
                RenderUtils.draw2D(blockPos, v2, v3.getRGB());
lbl30:
                // 5 sources

            }
            var4_5 = Unit.INSTANCE;
        }
    }

    @Override
    @NotNull
    public String getTag() {
        return BlockUtils.getBlockName(((Number)this.blockValue.get()).intValue());
    }

    public static final /* synthetic */ IntegerValue access$getBlockLimitValue$p(BlockESP $this) {
        return $this.blockLimitValue;
    }

    public static final /* synthetic */ MSTimer access$getSearchTimer$p(BlockESP $this) {
        return $this.searchTimer;
    }

    public static final /* synthetic */ List access$getPosList$p(BlockESP $this) {
        return $this.posList;
    }
}

