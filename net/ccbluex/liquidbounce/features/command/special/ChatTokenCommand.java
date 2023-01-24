/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.command.special;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.chat.packet.packets.ServerRequestJWTPacket;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.misc.LiquidChat;
import net.ccbluex.liquidbounce.utils.misc.StringUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016\u00a2\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lnet/ccbluex/liquidbounce/features/command/special/ChatTokenCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "()V", "lChat", "Lnet/ccbluex/liquidbounce/features/module/modules/misc/LiquidChat;", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "tabComplete", "", "([Ljava/lang/String;)Ljava/util/List;", "Relaxed"})
public final class ChatTokenCommand
extends Command {
    private final LiquidChat lChat;

    @Override
    public void execute(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        if (args2.length > 1) {
            if (StringsKt.equals(args2[1], "set", true)) {
                if (args2.length > 2) {
                    String string = StringUtils.toCompleteString(args2, 2);
                    Intrinsics.checkExpressionValueIsNotNull(string, "StringUtils.toCompleteString(args, 2)");
                    LiquidChat.Companion.setJwtToken(string);
                    this.lChat.getJwtValue().set(true);
                    if (this.lChat.getState()) {
                        this.lChat.setState(false);
                        this.lChat.setState(true);
                    }
                } else {
                    this.chatSyntax("chattoken set <token>");
                }
            } else if (StringsKt.equals(args2[1], "generate", true)) {
                if (!this.lChat.getState()) {
                    this.chat("\u00a7cError: \u00a77LiquidChat is disabled!");
                    return;
                }
                this.lChat.getClient().sendPacket(new ServerRequestJWTPacket());
            } else if (StringsKt.equals(args2[1], "copy", true)) {
                CharSequence charSequence = LiquidChat.Companion.getJwtToken();
                boolean bl = false;
                if (charSequence.length() == 0) {
                    this.chat("\u00a7cError: \u00a77No token set! Generate one first using '" + LiquidBounce.INSTANCE.getCommandManager().getPrefix() + "chattoken generate'.");
                    return;
                }
                StringSelection stringSelection = new StringSelection(LiquidChat.Companion.getJwtToken());
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Intrinsics.checkExpressionValueIsNotNull(toolkit, "Toolkit.getDefaultToolkit()");
                toolkit.getSystemClipboard().setContents(stringSelection, stringSelection);
                this.chat("\u00a7aCopied to clipboard!");
            }
        } else {
            this.chatSyntax("chattoken <set/copy/generate>");
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public List<String> tabComplete(@NotNull String[] args2) {
        List list;
        Intrinsics.checkParameterIsNotNull(args2, "args");
        String[] stringArray = args2;
        boolean bl = false;
        if (stringArray.length == 0) {
            return CollectionsKt.emptyList();
        }
        switch (args2.length) {
            case 1: {
                void $this$filterTo$iv$iv;
                Iterable $this$mapTo$iv$iv;
                String[] $this$map$iv = new String[]{"set", "generate", "copy"};
                boolean $i$f$map = false;
                String[] stringArray2 = $this$map$iv;
                Collection destination$iv$iv = new ArrayList($this$map$iv.length);
                boolean $i$f$mapTo = false;
                Iterator iterator2 = $this$mapTo$iv$iv;
                int n = ((void)iterator2).length;
                for (int i = 0; i < n; ++i) {
                    String string;
                    void it;
                    void item$iv$iv;
                    void var11_14 = item$iv$iv = iterator2[i];
                    Collection collection = destination$iv$iv;
                    boolean bl2 = false;
                    void var13_16 = it;
                    boolean bl3 = false;
                    void v0 = var13_16;
                    if (v0 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(v0.toLowerCase(), "(this as java.lang.String).toLowerCase()");
                    collection.add(string);
                }
                Iterable $this$filter$iv = (List)destination$iv$iv;
                boolean $i$f$filter = false;
                $this$mapTo$iv$iv = $this$filter$iv;
                destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    String it = (String)element$iv$iv;
                    boolean bl4 = false;
                    if (!StringsKt.startsWith(it, args2[0], true)) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                list = (List)destination$iv$iv;
                break;
            }
            default: {
                list = CollectionsKt.emptyList();
            }
        }
        return list;
    }

    public ChatTokenCommand() {
        super("chattoken", new String[0]);
        Module module = LiquidBounce.INSTANCE.getModuleManager().getModule(LiquidChat.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.misc.LiquidChat");
        }
        this.lChat = (LiquidChat)module;
    }
}

