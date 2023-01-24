/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.command.commands;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2={"Lnet/ccbluex/liquidbounce/features/command/commands/RemoteViewCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "tabComplete", "", "([Ljava/lang/String;)Ljava/util/List;", "Relaxed"})
public final class RemoteViewCommand
extends Command {
    @Override
    public void execute(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        if (args2.length < 2) {
            if (Intrinsics.areEqual(MinecraftInstance.mc.getRenderViewEntity(), MinecraftInstance.mc.getThePlayer()) ^ true) {
                MinecraftInstance.mc.setRenderViewEntity(MinecraftInstance.mc.getThePlayer());
                return;
            }
            this.chatSyntax("remoteview <username>");
            return;
        }
        String targetName = args2[1];
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        for (IEntity entity : iWorldClient.getLoadedEntityList()) {
            if (!Intrinsics.areEqual(targetName, entity.getName())) continue;
            MinecraftInstance.mc.setRenderViewEntity(entity);
            this.chat("Now viewing perspective of \u00a78" + entity.getName() + "\u00a73.");
            this.chat("Execute \u00a78" + LiquidBounce.INSTANCE.getCommandManager().getPrefix() + "remoteview \u00a73again to go back to yours.");
            break;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    @NotNull
    public List<String> tabComplete(@NotNull String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        var2_2 = args;
        var3_3 = false;
        if (var2_2.length == 0) {
            return CollectionsKt.emptyList();
        }
        switch (args.length) {
            case 1: {
                v0 = MinecraftInstance.mc.getTheWorld();
                if (v0 == null) {
                    Intrinsics.throwNpe();
                }
                $this$filter$iv = v0.getPlayerEntities();
                $i$f$filter = false;
                var4_4 = $this$filter$iv;
                destination$iv$iv = new ArrayList<E>();
                $i$f$filterTo = false;
                for (T element$iv$iv : $this$filterTo$iv$iv) {
                    it = (IEntityPlayer)element$iv$iv;
                    $i$a$-filter-RemoteViewCommand$tabComplete$1 = false;
                    if (it.getName() == null) ** GOTO lbl-1000
                    v1 = it.getName();
                    if (v1 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (StringsKt.startsWith(v1, args[0], true)) {
                        v2 = true;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v2 = false;
                    }
                    if (!v2) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                $this$map$iv = (List)destination$iv$iv;
                $i$f$map = false;
                $this$filterTo$iv$iv = $this$map$iv;
                destination$iv$iv = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                $i$f$mapTo = false;
                for (T item$iv$iv : $this$mapTo$iv$iv) {
                    it = (IEntityPlayer)item$iv$iv;
                    var11_11 = destination$iv$iv;
                    $i$a$-map-RemoteViewCommand$tabComplete$2 = false;
                    if (it.getName() == null) {
                        Intrinsics.throwNpe();
                    }
                    var11_11.add(var12_12);
                }
                return (List)destination$iv$iv;
            }
        }
        return CollectionsKt.emptyList();
    }

    public RemoteViewCommand() {
        super("remoteview", "rv");
    }
}

