/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.api.minecraft.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\"\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u001f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bB'\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\fJ\b\u0010\u0015\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+\u00a8\u0006,"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/util/WEnumChatFormatting;", "", "formattingName", "", "formattingCodeIn", "", "colorIndex", "", "(Ljava/lang/String;ILjava/lang/String;CI)V", "fancyStylingIn", "", "(Ljava/lang/String;ILjava/lang/String;CZ)V", "(Ljava/lang/String;ILjava/lang/String;CZI)V", "getColorIndex", "()I", "getFancyStylingIn", "()Z", "getFormattingCodeIn", "()C", "getFormattingName", "()Ljava/lang/String;", "toString", "BLACK", "DARK_BLUE", "DARK_GREEN", "DARK_AQUA", "DARK_RED", "DARK_PURPLE", "GOLD", "GRAY", "DARK_GRAY", "BLUE", "GREEN", "AQUA", "RED", "LIGHT_PURPLE", "YELLOW", "WHITE", "OBFUSCATED", "BOLD", "STRIKETHROUGH", "UNDERLINE", "ITALIC", "RESET", "Relaxed"})
public final class WEnumChatFormatting
extends Enum<WEnumChatFormatting> {
    public static final /* enum */ WEnumChatFormatting BLACK;
    public static final /* enum */ WEnumChatFormatting DARK_BLUE;
    public static final /* enum */ WEnumChatFormatting DARK_GREEN;
    public static final /* enum */ WEnumChatFormatting DARK_AQUA;
    public static final /* enum */ WEnumChatFormatting DARK_RED;
    public static final /* enum */ WEnumChatFormatting DARK_PURPLE;
    public static final /* enum */ WEnumChatFormatting GOLD;
    public static final /* enum */ WEnumChatFormatting GRAY;
    public static final /* enum */ WEnumChatFormatting DARK_GRAY;
    public static final /* enum */ WEnumChatFormatting BLUE;
    public static final /* enum */ WEnumChatFormatting GREEN;
    public static final /* enum */ WEnumChatFormatting AQUA;
    public static final /* enum */ WEnumChatFormatting RED;
    public static final /* enum */ WEnumChatFormatting LIGHT_PURPLE;
    public static final /* enum */ WEnumChatFormatting YELLOW;
    public static final /* enum */ WEnumChatFormatting WHITE;
    public static final /* enum */ WEnumChatFormatting OBFUSCATED;
    public static final /* enum */ WEnumChatFormatting BOLD;
    public static final /* enum */ WEnumChatFormatting STRIKETHROUGH;
    public static final /* enum */ WEnumChatFormatting UNDERLINE;
    public static final /* enum */ WEnumChatFormatting ITALIC;
    public static final /* enum */ WEnumChatFormatting RESET;
    private static final /* synthetic */ WEnumChatFormatting[] $VALUES;
    @NotNull
    private final String formattingName;
    private final char formattingCodeIn;
    private final boolean fancyStylingIn;
    private final int colorIndex;

    static {
        WEnumChatFormatting[] wEnumChatFormattingArray = new WEnumChatFormatting[22];
        WEnumChatFormatting[] wEnumChatFormattingArray2 = wEnumChatFormattingArray;
        wEnumChatFormattingArray[0] = BLACK = new WEnumChatFormatting("BLACK", '0', 0);
        wEnumChatFormattingArray[1] = DARK_BLUE = new WEnumChatFormatting("DARK_BLUE", '1', 1);
        wEnumChatFormattingArray[2] = DARK_GREEN = new WEnumChatFormatting("DARK_GREEN", '2', 2);
        wEnumChatFormattingArray[3] = DARK_AQUA = new WEnumChatFormatting("DARK_AQUA", '3', 3);
        wEnumChatFormattingArray[4] = DARK_RED = new WEnumChatFormatting("DARK_RED", '4', 4);
        wEnumChatFormattingArray[5] = DARK_PURPLE = new WEnumChatFormatting("DARK_PURPLE", '5', 5);
        wEnumChatFormattingArray[6] = GOLD = new WEnumChatFormatting("GOLD", '6', 6);
        wEnumChatFormattingArray[7] = GRAY = new WEnumChatFormatting("GRAY", '7', 7);
        wEnumChatFormattingArray[8] = DARK_GRAY = new WEnumChatFormatting("DARK_GRAY", '8', 8);
        wEnumChatFormattingArray[9] = BLUE = new WEnumChatFormatting("BLUE", '9', 9);
        wEnumChatFormattingArray[10] = GREEN = new WEnumChatFormatting("GREEN", 'a', 10);
        wEnumChatFormattingArray[11] = AQUA = new WEnumChatFormatting("AQUA", 'b', 11);
        wEnumChatFormattingArray[12] = RED = new WEnumChatFormatting("RED", 'c', 12);
        wEnumChatFormattingArray[13] = LIGHT_PURPLE = new WEnumChatFormatting("LIGHT_PURPLE", 'd', 13);
        wEnumChatFormattingArray[14] = YELLOW = new WEnumChatFormatting("YELLOW", 'e', 14);
        wEnumChatFormattingArray[15] = WHITE = new WEnumChatFormatting("WHITE", 'f', 15);
        wEnumChatFormattingArray[16] = OBFUSCATED = new WEnumChatFormatting("OBFUSCATED", 'k', true);
        wEnumChatFormattingArray[17] = BOLD = new WEnumChatFormatting("BOLD", 'l', true);
        wEnumChatFormattingArray[18] = STRIKETHROUGH = new WEnumChatFormatting("STRIKETHROUGH", 'm', true);
        wEnumChatFormattingArray[19] = UNDERLINE = new WEnumChatFormatting("UNDERLINE", 'n', true);
        wEnumChatFormattingArray[20] = ITALIC = new WEnumChatFormatting("ITALIC", 'o', true);
        wEnumChatFormattingArray[21] = RESET = new WEnumChatFormatting("RESET", 'r', -1);
        $VALUES = wEnumChatFormattingArray;
    }

    @NotNull
    public String toString() {
        return "" + '\u00a7' + this.formattingCodeIn;
    }

    @NotNull
    public final String getFormattingName() {
        return this.formattingName;
    }

    public final char getFormattingCodeIn() {
        return this.formattingCodeIn;
    }

    public final boolean getFancyStylingIn() {
        return this.fancyStylingIn;
    }

    public final int getColorIndex() {
        return this.colorIndex;
    }

    private WEnumChatFormatting(String formattingName, char formattingCodeIn, boolean fancyStylingIn, int colorIndex) {
        this.formattingName = formattingName;
        this.formattingCodeIn = formattingCodeIn;
        this.fancyStylingIn = fancyStylingIn;
        this.colorIndex = colorIndex;
    }

    private WEnumChatFormatting(String formattingName, char formattingCodeIn, int colorIndex) {
        this(formattingName, formattingCodeIn, false, colorIndex);
    }

    private WEnumChatFormatting(String formattingName, char formattingCodeIn, boolean fancyStylingIn) {
        this(formattingName, formattingCodeIn, fancyStylingIn, -1);
    }

    public static WEnumChatFormatting[] values() {
        return (WEnumChatFormatting[])$VALUES.clone();
    }

    public static WEnumChatFormatting valueOf(String string) {
        return Enum.valueOf(WEnumChatFormatting.class, string);
    }
}

