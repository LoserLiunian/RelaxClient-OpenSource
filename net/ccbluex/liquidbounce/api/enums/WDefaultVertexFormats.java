/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.api.enums;

import kotlin.Metadata;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lnet/ccbluex/liquidbounce/api/enums/WDefaultVertexFormats;", "", "(Ljava/lang/String;I)V", "POSITION", "POSITION_TEX", "POSITION_COLOR", "Relaxed"})
public final class WDefaultVertexFormats
extends Enum<WDefaultVertexFormats> {
    public static final /* enum */ WDefaultVertexFormats POSITION;
    public static final /* enum */ WDefaultVertexFormats POSITION_TEX;
    public static final /* enum */ WDefaultVertexFormats POSITION_COLOR;
    private static final /* synthetic */ WDefaultVertexFormats[] $VALUES;

    static {
        WDefaultVertexFormats[] wDefaultVertexFormatsArray = new WDefaultVertexFormats[3];
        WDefaultVertexFormats[] wDefaultVertexFormatsArray2 = wDefaultVertexFormatsArray;
        wDefaultVertexFormatsArray[0] = POSITION = new WDefaultVertexFormats();
        wDefaultVertexFormatsArray[1] = POSITION_TEX = new WDefaultVertexFormats();
        wDefaultVertexFormatsArray[2] = POSITION_COLOR = new WDefaultVertexFormats();
        $VALUES = wDefaultVertexFormatsArray;
    }

    public static WDefaultVertexFormats[] values() {
        return (WDefaultVertexFormats[])$VALUES.clone();
    }

    public static WDefaultVertexFormats valueOf(String string) {
        return Enum.valueOf(WDefaultVertexFormats.class, string);
    }
}

