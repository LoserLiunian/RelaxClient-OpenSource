/*
 * Decompiled with CFR 0.152.
 */
package me.yarukon.font;

public class Yarukon {
    public static Yarukon INSTANCE;
    public char[] chars = new char[65535];
    public char[] ascii_chars = new char[256];

    public Yarukon() {
        int i;
        INSTANCE = this;
        for (i = 0; i < this.chars.length; ++i) {
            this.chars[i] = (char)i;
        }
        for (i = 0; i < this.ascii_chars.length; ++i) {
            this.ascii_chars[i] = (char)i;
        }
    }
}

