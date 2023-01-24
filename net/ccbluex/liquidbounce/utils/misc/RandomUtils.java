/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.utils.misc;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\nJ\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\nH\u0007\u00a8\u0006\u0013"}, d2={"Lnet/ccbluex/liquidbounce/utils/misc/RandomUtils;", "", "()V", "nextDouble", "", "startInclusive", "endInclusive", "nextFloat", "", "nextInt", "", "endExclusive", "random", "", "length", "chars", "", "randomNumber", "randomString", "Relaxed"})
public final class RandomUtils {
    public static final RandomUtils INSTANCE;

    @JvmStatic
    public static final int nextInt(int startInclusive, int endExclusive) {
        return endExclusive - startInclusive <= 0 ? startInclusive : startInclusive + new Random().nextInt(endExclusive - startInclusive);
    }

    public final double nextDouble(double startInclusive, double endInclusive) {
        return startInclusive == endInclusive || endInclusive - startInclusive <= 0.0 ? startInclusive : startInclusive + (endInclusive - startInclusive) * Math.random();
    }

    public final float nextFloat(float startInclusive, float endInclusive) {
        return startInclusive == endInclusive || endInclusive - startInclusive <= 0.0f ? startInclusive : (float)((double)startInclusive + (double)(endInclusive - startInclusive) * Math.random());
    }

    @NotNull
    public final String randomNumber(int length) {
        return this.random(length, "123456789");
    }

    @JvmStatic
    @NotNull
    public static final String randomString(int length) {
        return INSTANCE.random(length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    @NotNull
    public final String random(int length, @NotNull String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        String string = chars;
        int n = length;
        RandomUtils randomUtils = this;
        boolean bl = false;
        char[] cArray = string.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(cArray, "(this as java.lang.String).toCharArray()");
        char[] cArray2 = cArray;
        return randomUtils.random(n, cArray2);
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final String random(int length, @NotNull char[] chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        int n2 = length;
        while (n < n2) {
            void i;
            stringBuilder.append(chars[new Random().nextInt(chars.length)]);
            ++i;
        }
        String string = stringBuilder.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "stringBuilder.toString()");
        return string;
    }

    private RandomUtils() {
    }

    static {
        RandomUtils randomUtils;
        INSTANCE = randomUtils = new RandomUtils();
    }
}

