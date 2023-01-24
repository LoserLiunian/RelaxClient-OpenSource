/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.tools.obfuscation;

public enum SuppressedBy {
    CONSTRAINTS("constraints"),
    VISIBILITY("visibility"),
    TARGET("target"),
    MAPPING("mapping"),
    OVERWRITE("overwrite"),
    DEFAULT_PACKAGE("default-package"),
    PUBLIC_TARGET("public-target"),
    RAW_TYPES("rawtypes");

    private final String token;

    private SuppressedBy(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}

