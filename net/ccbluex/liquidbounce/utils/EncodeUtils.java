/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.utils;

import native0.Loader;
import oh.yalan.NativeClass;

@NativeClass
public class EncodeUtils {
    public static native String md5();

    public static native String sha256(String var0);

    private static native String toHex(byte[] var0);

    static {
        Loader.registerNativesForClass(5, EncodeUtils.class);
    }
}

