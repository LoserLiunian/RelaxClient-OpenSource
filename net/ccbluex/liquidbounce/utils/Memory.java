/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.event.UpdateEvent;

public class Memory
implements Listenable {
    public static float maxMemorySize = 1024.0f;
    public static float usedMemorySize = 256.0f;

    @Override
    public boolean handleEvents() {
        return true;
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        maxMemorySize = (float)memoryUsage.getMax() / 1048576.0f;
        usedMemorySize = (float)memoryUsage.getUsed() / 1048576.0f;
    }

    public static float getMemory() {
        return maxMemorySize / usedMemorySize;
    }
}

