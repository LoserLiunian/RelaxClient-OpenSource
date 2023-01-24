/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.world;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="Ambience", description="Change your world time and weather client-side.", category=ModuleCategory.WORLD)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0018H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/world/Ambience;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "cycleSpeedValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "rainStrengthValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "tag", "", "getTag", "()Ljava/lang/String;", "tagValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "timeCycle", "", "timeModeValue", "weatherModeValue", "worldTimeValue", "onEnable", "", "onPacket", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class Ambience
extends Module {
    private final ListValue timeModeValue = new ListValue("Time", new String[]{"Static", "Cycle"}, "Static");
    private final IntegerValue cycleSpeedValue = new IntegerValue("CycleSpeed", 1, -24, 24);
    private final IntegerValue worldTimeValue = new IntegerValue("Time", 12000, 0, 24000);
    private final ListValue weatherModeValue = new ListValue("Weather", new String[]{"Clear", "Rain", "NoModification"}, "Clear");
    private final FloatValue rainStrengthValue = new FloatValue("RainStrength", 0.1f, 0.01f, 1.0f);
    private final ListValue tagValue = new ListValue("Tag", new String[]{"TimeOnly", "Simplified", "Detailed", "None"}, "TimeOnly");
    private long timeCycle;

    @Override
    public void onEnable() {
        this.timeCycle = 0L;
    }

    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (MinecraftInstance.classProvider.isSPacketTimeUpdate(event.getPacket())) {
            event.cancelEvent();
        }
    }

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (StringsKt.equals((String)this.timeModeValue.get(), "static", true)) {
            WorldClient worldClient = Minecraft.func_71410_x().field_71441_e;
            Intrinsics.checkExpressionValueIsNotNull(worldClient, "Minecraft.getMinecraft().world");
            worldClient.func_72877_b((long)((Number)this.worldTimeValue.get()).intValue());
        } else {
            WorldClient worldClient = MinecraftInstance.mc2.field_71441_e;
            Intrinsics.checkExpressionValueIsNotNull(worldClient, "mc2.world");
            worldClient.func_72877_b(this.timeCycle);
            this.timeCycle += (long)(((Number)this.cycleSpeedValue.get()).intValue() * 10);
            if (this.timeCycle > 24000L) {
                this.timeCycle = 0L;
            }
            if (this.timeCycle < 0L) {
                this.timeCycle = 24000L;
            }
        }
        if (!StringsKt.equals((String)this.weatherModeValue.get(), "nomodification", true)) {
            MinecraftInstance.mc2.field_71441_e.func_72894_k(StringsKt.equals((String)this.weatherModeValue.get(), "clear", true) ? 0.0f : ((Number)this.rainStrengthValue.get()).floatValue());
        }
    }

    @Override
    @Nullable
    public String getTag() {
        String string;
        String string2 = (String)this.tagValue.get();
        boolean bl = false;
        String string3 = string2;
        if (string3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string4 = string3.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string4, "(this as java.lang.String).toLowerCase()");
        switch (string4) {
            case "timeonly": {
                if (StringsKt.equals((String)this.timeModeValue.get(), "static", true)) {
                    string = String.valueOf(((Number)this.worldTimeValue.get()).intValue());
                    break;
                }
                string = String.valueOf(this.timeCycle);
                break;
            }
            case "simplified": {
                string = (StringsKt.equals((String)this.timeModeValue.get(), "static", true) ? String.valueOf(((Number)this.worldTimeValue.get()).intValue()) : String.valueOf(this.timeCycle)) + ", " + (String)this.weatherModeValue.get();
                break;
            }
            case "detailed": {
                string = "Time: " + (StringsKt.equals((String)this.timeModeValue.get(), "static", true) ? String.valueOf(((Number)this.worldTimeValue.get()).intValue()) : "Cycle, " + String.valueOf(this.timeCycle)) + ", Weather: " + (String)this.weatherModeValue.get();
                break;
            }
            default: {
                string = null;
            }
        }
        return string;
    }
}

