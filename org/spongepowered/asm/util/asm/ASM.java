/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Opcodes
 */
package org.spongepowered.asm.util.asm;

import java.lang.reflect.Field;
import org.objectweb.asm.Opcodes;

public final class ASM {
    private static int majorVersion = 5;
    private static int minorVersion = 0;
    private static String maxVersion = "FALLBACK";
    public static final int API_VERSION = ASM.detectVersion();

    private ASM() {
    }

    public static int getApiVersionMajor() {
        return majorVersion;
    }

    public static int getApiVersionMinor() {
        return minorVersion;
    }

    public static String getApiVersionString() {
        return String.format("ASM %d.%d (%s)", majorVersion, minorVersion, maxVersion);
    }

    private static int detectVersion() {
        int apiVersion = 262144;
        for (Field field : Opcodes.class.getDeclaredFields()) {
            if (field.getType() != Integer.TYPE || !field.getName().startsWith("ASM")) continue;
            try {
                boolean experimental;
                int version = field.getInt(null);
                int minor = version >> 8 & 0xFF;
                int major = version >> 16 & 0xFF;
                boolean bl = experimental = (version >> 24 & 0xFF) != 0;
                if (major < majorVersion) continue;
                maxVersion = field.getName();
                if (experimental) continue;
                apiVersion = version;
                majorVersion = major;
                minorVersion = minor;
            }
            catch (ReflectiveOperationException ex) {
                throw new Error(ex);
            }
        }
        return apiVersion;
    }
}

