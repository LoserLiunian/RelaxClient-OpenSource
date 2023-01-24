/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.PacketEvent;
import net.ccbluex.liquidbounce.event.WorldEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="HytGetName", description="fix by cool", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/misc/HytGetName;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "mode", "Lnet/ccbluex/liquidbounce/value/ListValue;", "clearAll", "", "onDisable", "onPacket", "event", "Lnet/ccbluex/liquidbounce/event/PacketEvent;", "onWorld", "Lnet/ccbluex/liquidbounce/event/WorldEvent;", "Relaxed"})
public final class HytGetName
extends Module {
    private final ListValue mode = new ListValue("GetNameMode", new String[]{"4V4/1V1", "32/64", "16V16"}, "4V4/1V1");

    @Override
    public void onDisable() {
        this.clearAll();
        super.onDisable();
    }

    /*
     * Unable to fully structure code
     */
    @EventTarget
    public final void onPacket(@NotNull PacketEvent event) {
        block14: {
            Intrinsics.checkParameterIsNotNull(event, "event");
            packet = event.getPacket();
            if (!MinecraftInstance.classProvider.isSPacketChat(packet)) break block14;
            var3_3 = (String)this.mode.get();
            var4_4 = false;
            v0 = var3_3;
            if (v0 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            v1 = v0.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(v1, "(this as java.lang.String).toLowerCase()");
            var3_3 = v1;
            switch (var3_3.hashCode()) {
                case 48636014: {
                    if (!var3_3.equals("32/64")) ** break;
                    ** GOTO lbl21
                }
                case 46976214: {
                    if (!var3_3.equals("16v16")) ** break;
                    break;
                }
                case -1961702257: {
                    if (!var3_3.equals("4v4/1v1")) ** break;
lbl21:
                    // 2 sources

                    matcher = Pattern.compile("\u6740\u6b7b\u4e86 (.*?)\\(").matcher(packet.asSPacketChat().getChatComponent().func_150260_c());
                    matcher2 = Pattern.compile("\u8d77\u5e8a\u6218\u4e89>> (.*?) (\\((((.*?)\u6b7b\u4e86!)))").matcher(packet.asSPacketChat().getChatComponent().func_150260_c());
                    if (matcher.find()) {
                        v2 = matcher.group(1);
                        Intrinsics.checkExpressionValueIsNotNull(v2, "matcher.group(1)");
                        var7_9 = v2;
                        var8_11 = false;
                        v3 = var7_9;
                        if (v3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        name = StringsKt.trim((CharSequence)v3).toString();
                        if (Intrinsics.areEqual(name, "") ^ true) {
                            LiquidBounce.INSTANCE.getFileManager().friendsConfig.addFriend(name);
                            new Thread(new Runnable(name){
                                final /* synthetic */ String $name;

                                public final void run() {
                                    try {
                                        Thread.sleep(5000L);
                                        LiquidBounce.INSTANCE.getFileManager().friendsConfig.removeFriend(this.$name);
                                    }
                                    catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                {
                                    this.$name = string;
                                }
                            }).start();
                        }
                    }
                    if (!matcher2.find()) ** break;
                    v4 = matcher2.group(1);
                    Intrinsics.checkExpressionValueIsNotNull(v4, "matcher2.group(1)");
                    var7_9 = v4;
                    var8_11 = false;
                    v5 = var7_9;
                    if (v5 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    name = StringsKt.trim((CharSequence)v5).toString();
                    if (!(Intrinsics.areEqual(name, "") ^ true)) ** break;
                    LiquidBounce.INSTANCE.getFileManager().friendsConfig.addFriend(name);
                    new Thread(new Runnable(name){
                        final /* synthetic */ String $name;

                        public final void run() {
                            try {
                                Thread.sleep(5000L);
                                LiquidBounce.INSTANCE.getFileManager().friendsConfig.removeFriend(this.$name);
                            }
                            catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        {
                            this.$name = string;
                        }
                    }).start();
                    ** break;
                }
            }
            matcher = Pattern.compile("\u51fb\u8d25\u4e86 (.*?)!").matcher(packet.asSPacketChat().getChatComponent().func_150260_c());
            matcher2 = Pattern.compile("\u73a9\u5bb6 (.*?)\u6b7b\u4e86\uff01").matcher(packet.asSPacketChat().getChatComponent().func_150260_c());
            if (matcher.find()) {
                v6 = matcher.group(1);
                Intrinsics.checkExpressionValueIsNotNull(v6, "matcher.group(1)");
                var7_10 = v6;
                var8_12 = false;
                v7 = var7_10;
                if (v7 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                name = StringsKt.trim((CharSequence)v7).toString();
                if (Intrinsics.areEqual(name, "") ^ true) {
                    LiquidBounce.INSTANCE.getFileManager().friendsConfig.addFriend(name);
                    new Thread(new Runnable(name){
                        final /* synthetic */ String $name;

                        public final void run() {
                            try {
                                Thread.sleep(10000L);
                                LiquidBounce.INSTANCE.getFileManager().friendsConfig.removeFriend(this.$name);
                            }
                            catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        {
                            this.$name = string;
                        }
                    }).start();
                }
            }
            if (!matcher2.find()) ** break;
            v8 = matcher2.group(1);
            Intrinsics.checkExpressionValueIsNotNull(v8, "matcher2.group(1)");
            var7_10 = v8;
            var8_12 = false;
            v9 = var7_10;
            if (v9 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            name = StringsKt.trim((CharSequence)v9).toString();
            if (!(Intrinsics.areEqual(name, "") ^ true)) ** break;
            LiquidBounce.INSTANCE.getFileManager().friendsConfig.addFriend(name);
            new Thread(new Runnable(name){
                final /* synthetic */ String $name;

                public final void run() {
                    try {
                        Thread.sleep(10000L);
                        LiquidBounce.INSTANCE.getFileManager().friendsConfig.removeFriend(this.$name);
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                {
                    this.$name = string;
                }
            }).start();
            ** break;
        }
    }

    @EventTarget
    public final void onWorld(@Nullable WorldEvent event) {
        this.clearAll();
    }

    private final void clearAll() {
        LiquidBounce.INSTANCE.getFileManager().friendsConfig.clearFriends();
    }
}

