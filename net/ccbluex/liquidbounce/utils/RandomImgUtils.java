/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package net.ccbluex.liquidbounce.utils;

import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class RandomImgUtils {
    private static long startTime = 0L;
    static Random random = new Random();
    static int count = random.nextInt(1);
    public static int count2 = random.nextInt(1);

    public static ResourceLocation getBackGround() {
        return new ResourceLocation("relaxed/bg.png");
    }
}

