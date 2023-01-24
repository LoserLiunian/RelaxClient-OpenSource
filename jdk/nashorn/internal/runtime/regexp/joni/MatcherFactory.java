/*
 * Decompiled with CFR 0.152.
 */
package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.ByteCodeMachine;
import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import jdk.nashorn.internal.runtime.regexp.joni.Region;

public abstract class MatcherFactory {
    static final MatcherFactory DEFAULT = new MatcherFactory(){

        @Override
        public Matcher create(Regex regex, Region region, char[] chars, int p, int end) {
            return new ByteCodeMachine(regex, region, chars, p, end);
        }
    };

    public abstract Matcher create(Regex var1, Region var2, char[] var3, int var4, int var5);
}

