/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.api.minecraft.world;

import kotlin.Metadata;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0002\u00a8\u0006\u0003"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/world/IWorldSettings;", "", "WGameType", "Relaxed"})
public interface IWorldSettings {

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/world/IWorldSettings$WGameType;", "", "(Ljava/lang/String;I)V", "NOT_SET", "SURVIVAL", "CREATIVE", "ADVENTUR", "SPECTATOR", "Relaxed"})
    public static final class WGameType
    extends Enum<WGameType> {
        public static final /* enum */ WGameType NOT_SET;
        public static final /* enum */ WGameType SURVIVAL;
        public static final /* enum */ WGameType CREATIVE;
        public static final /* enum */ WGameType ADVENTUR;
        public static final /* enum */ WGameType SPECTATOR;
        private static final /* synthetic */ WGameType[] $VALUES;

        static {
            WGameType[] wGameTypeArray = new WGameType[5];
            WGameType[] wGameTypeArray2 = wGameTypeArray;
            wGameTypeArray[0] = NOT_SET = new WGameType();
            wGameTypeArray[1] = SURVIVAL = new WGameType();
            wGameTypeArray[2] = CREATIVE = new WGameType();
            wGameTypeArray[3] = ADVENTUR = new WGameType();
            wGameTypeArray[4] = SPECTATOR = new WGameType();
            $VALUES = wGameTypeArray;
        }

        public static WGameType[] values() {
            return (WGameType[])$VALUES.clone();
        }

        public static WGameType valueOf(String string) {
            return Enum.valueOf(WGameType.class, string);
        }
    }
}

