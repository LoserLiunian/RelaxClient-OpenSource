/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.command.commands;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.ServerUtils;
import net.ccbluex.liquidbounce.utils.login.MinecraftAccount;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2={"Lnet/ccbluex/liquidbounce/features/command/commands/LoginCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "Relaxed"})
public final class LoginCommand
extends Command {
    @Override
    public void execute(@NotNull String[] args2) {
        String string;
        Intrinsics.checkParameterIsNotNull(args2, "args");
        if (args2.length <= 1) {
            this.chatSyntax("login <username/email> [password]");
            return;
        }
        if (args2.length > 2) {
            String string2 = GuiAltManager.login(new MinecraftAccount(args2[1], args2[2]));
            string = string2;
            Intrinsics.checkExpressionValueIsNotNull(string2, "GuiAltManager.login(Mine\u2026ccount(args[1], args[2]))");
        } else {
            String string3 = GuiAltManager.login(new MinecraftAccount(args2[1]));
            string = string3;
            Intrinsics.checkExpressionValueIsNotNull(string3, "GuiAltManager.login(MinecraftAccount(args[1]))");
        }
        String result = string;
        this.chat(result);
        if (StringsKt.startsWith$default(result, "\u00a7cYour name is now", false, 2, null)) {
            if (MinecraftInstance.mc.isIntegratedServerRunning()) {
                return;
            }
            IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
            if (iWorldClient == null) {
                Intrinsics.throwNpe();
            }
            iWorldClient.sendQuittingDisconnectingPacket();
            ServerUtils.connectToLastServer();
        }
    }

    public LoginCommand() {
        super("login", new String[0]);
    }
}

