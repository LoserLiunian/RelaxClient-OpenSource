/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.utils.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.network.INetworkPlayerInfo;
import net.ccbluex.liquidbounce.api.minecraft.scoreboard.ITeam;
import net.ccbluex.liquidbounce.api.minecraft.util.IIChatComponent;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u00a8\u0006\u0003"}, d2={"getFullName", "", "Lnet/ccbluex/liquidbounce/api/minecraft/client/network/INetworkPlayerInfo;", "Relaxed"})
public final class NetworkExtensionKt {
    @NotNull
    public static final String getFullName(@NotNull INetworkPlayerInfo $this$getFullName) {
        Object object;
        block6: {
            String name;
            block5: {
                Intrinsics.checkParameterIsNotNull($this$getFullName, "$this$getFullName");
                if ($this$getFullName.getDisplayName() != null) {
                    IIChatComponent iIChatComponent = $this$getFullName.getDisplayName();
                    if (iIChatComponent == null) {
                        Intrinsics.throwNpe();
                    }
                    return iIChatComponent.getFormattedText();
                }
                ITeam team = $this$getFullName.getPlayerTeam();
                name = $this$getFullName.getGameProfile().getName();
                object = team;
                if (object == null) break block5;
                String string = name;
                Intrinsics.checkExpressionValueIsNotNull(string, "name");
                if ((object = object.formatString(string)) != null) break block6;
            }
            String string = name;
            object = string;
            Intrinsics.checkExpressionValueIsNotNull(string, "name");
        }
        return object;
    }
}

