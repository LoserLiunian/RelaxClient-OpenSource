/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.command.special;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.chat.Client;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.misc.LiquidChat;
import net.ccbluex.liquidbounce.utils.misc.StringUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Lnet/ccbluex/liquidbounce/features/command/special/PrivateChatCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "()V", "lChat", "Lnet/ccbluex/liquidbounce/features/module/modules/misc/LiquidChat;", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "Relaxed"})
public final class PrivateChatCommand
extends Command {
    private final LiquidChat lChat;

    @Override
    public void execute(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        if (args2.length > 2) {
            if (!this.lChat.getState()) {
                this.chat("\u00a7cError: \u00a77LiquidChat is disabled!");
                return;
            }
            if (!this.lChat.getClient().isConnected()) {
                this.chat("\u00a7cError: \u00a7LiquidChat is currently not connected to the server!");
                return;
            }
            String target = args2[1];
            String message = StringUtils.toCompleteString(args2, 2);
            Client client2 = this.lChat.getClient();
            String string = message;
            Intrinsics.checkExpressionValueIsNotNull(string, "message");
            client2.sendPrivateMessage(target, string);
            this.chat("Message was successfully sent.");
        } else {
            this.chatSyntax("pchat <username> <message>");
        }
    }

    public PrivateChatCommand() {
        super("pchat", "privatechat", "lcpm");
        Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(LiquidChat.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.misc.LiquidChat");
        }
        this.lChat = (LiquidChat)module;
    }
}

