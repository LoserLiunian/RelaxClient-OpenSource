/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.api.minecraft.potion;

import kotlin.Metadata;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0012"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/potion/PotionType;", "", "(Ljava/lang/String;I)V", "JUMP", "HEAL", "REGENERATION", "BLINDNESS", "MOVE_SPEED", "HUNGER", "DIG_SLOWDOWN", "CONFUSION", "WEAKNESS", "DIG_SPEED", "MOVE_SLOWDOWN", "HARM", "WITHER", "POISON", "NIGHT_VISION", "Relaxed"})
public final class PotionType
extends Enum<PotionType> {
    public static final /* enum */ PotionType JUMP;
    public static final /* enum */ PotionType HEAL;
    public static final /* enum */ PotionType REGENERATION;
    public static final /* enum */ PotionType BLINDNESS;
    public static final /* enum */ PotionType MOVE_SPEED;
    public static final /* enum */ PotionType HUNGER;
    public static final /* enum */ PotionType DIG_SLOWDOWN;
    public static final /* enum */ PotionType CONFUSION;
    public static final /* enum */ PotionType WEAKNESS;
    public static final /* enum */ PotionType DIG_SPEED;
    public static final /* enum */ PotionType MOVE_SLOWDOWN;
    public static final /* enum */ PotionType HARM;
    public static final /* enum */ PotionType WITHER;
    public static final /* enum */ PotionType POISON;
    public static final /* enum */ PotionType NIGHT_VISION;
    private static final /* synthetic */ PotionType[] $VALUES;

    static {
        PotionType[] potionTypeArray = new PotionType[15];
        PotionType[] potionTypeArray2 = potionTypeArray;
        potionTypeArray[0] = JUMP = new PotionType();
        potionTypeArray[1] = HEAL = new PotionType();
        potionTypeArray[2] = REGENERATION = new PotionType();
        potionTypeArray[3] = BLINDNESS = new PotionType();
        potionTypeArray[4] = MOVE_SPEED = new PotionType();
        potionTypeArray[5] = HUNGER = new PotionType();
        potionTypeArray[6] = DIG_SLOWDOWN = new PotionType();
        potionTypeArray[7] = CONFUSION = new PotionType();
        potionTypeArray[8] = WEAKNESS = new PotionType();
        potionTypeArray[9] = DIG_SPEED = new PotionType();
        potionTypeArray[10] = MOVE_SLOWDOWN = new PotionType();
        potionTypeArray[11] = HARM = new PotionType();
        potionTypeArray[12] = WITHER = new PotionType();
        potionTypeArray[13] = POISON = new PotionType();
        potionTypeArray[14] = NIGHT_VISION = new PotionType();
        $VALUES = potionTypeArray;
    }

    public static PotionType[] values() {
        return (PotionType[])$VALUES.clone();
    }

    public static PotionType valueOf(String string) {
        return Enum.valueOf(PotionType.class, string);
    }
}

