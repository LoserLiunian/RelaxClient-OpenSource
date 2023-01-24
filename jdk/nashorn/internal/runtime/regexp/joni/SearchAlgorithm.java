/*
 * Decompiled with CFR 0.152.
 */
package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

final class SearchAlgorithm {
    static final Forward SLOW_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "EXACT_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            int end = textEnd;
            if ((end -= targetEnd - targetP - 1) > textRange) {
                end = textRange;
            }
            for (int s = textP; s < end; ++s) {
                int t;
                if (text[s] != target[targetP]) continue;
                int p = s + 1;
                for (t = targetP + 1; t < targetEnd && target[t] == text[p++]; ++t) {
                }
                if (t != targetEnd) continue;
                return s;
            }
            return -1;
        }
    };
    static final Backward SLOW_BACKWARD = new Backward(){

        @Override
        final int search(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            int s = textEnd;
            if ((s -= targetEnd - targetP) > textStart) {
                s = textStart;
            }
            while (s >= textP) {
                if (text[s] == target[targetP]) {
                    int t;
                    int p = s + 1;
                    for (t = targetP + 1; t < targetEnd && target[t] == text[p++]; ++t) {
                    }
                    if (t == targetEnd) {
                        return s;
                    }
                }
                --s;
            }
            return -1;
        }
    };
    static final Forward SLOW_IC_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "EXACT_IC_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            int end = textEnd;
            if ((end -= targetEnd - targetP - 1) > textRange) {
                end = textRange;
            }
            for (int s = textP; s < end; ++s) {
                if (!SearchAlgorithm.lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) continue;
                return s;
            }
            return -1;
        }
    };
    static final Backward SLOW_IC_BACKWARD = new Backward(){

        @Override
        final int search(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            int s = textEnd;
            if ((s -= targetEnd - targetP) > textStart) {
                s = textStart;
            }
            while (s >= textP) {
                if (SearchAlgorithm.lowerCaseMatch(target, targetP, targetEnd, text, s, textEnd)) {
                    return s;
                }
                s = EncodingHelper.prevCharHead(adjustText, s);
            }
            return -1;
        }
    };
    static final Forward BM_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "EXACT_BM_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            int tail = targetEnd - 1;
            int end = textRange + (targetEnd - targetP) - 1;
            int s = textP + (targetEnd - targetP) - 1;
            if (end > textEnd) {
                end = textEnd;
            }
            if (regex.intMap == null) {
                while (s < end) {
                    int p = s;
                    int t = tail;
                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return p;
                        }
                        --p;
                        --t;
                    }
                    s += regex.map[text[s] & 0xFF];
                }
            } else {
                while (s < end) {
                    int p = s;
                    int t = tail;
                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return p;
                        }
                        --p;
                        --t;
                    }
                    s += regex.intMap[text[s] & 0xFF];
                }
            }
            return -1;
        }
    };
    static final Backward BM_BACKWARD = new Backward(){
        private static final int BM_BACKWARD_SEARCH_LENGTH_THRESHOLD = 100;

        @Override
        final int search(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            int s;
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            if (regex.intMapBackward == null) {
                if (s_ - range_ < 100) {
                    return SLOW_BACKWARD.search(regex, text, textP, adjustText, textEnd, textStart, s_, range_);
                }
                this.setBmBackwardSkip(regex, target, targetP, targetEnd);
            }
            if (textStart < (s = textEnd - (targetEnd - targetP))) {
                s = textStart;
            }
            while (s >= textP) {
                int t;
                int p = s;
                for (t = targetP; t < targetEnd && text[p] == target[t]; ++t) {
                    ++p;
                }
                if (t == targetEnd) {
                    return s;
                }
                s -= regex.intMapBackward[text[s] & 0xFF];
            }
            return -1;
        }

        private void setBmBackwardSkip(Regex regex, char[] chars, int p, int end) {
            int i;
            int[] skip;
            if (regex.intMapBackward == null) {
                regex.intMapBackward = skip = new int[256];
            } else {
                skip = regex.intMapBackward;
            }
            int len = end - p;
            for (i = 0; i < 256; ++i) {
                skip[i] = len;
            }
            for (i = len - 1; i > 0; --i) {
                skip[chars[i] & 0xFF] = i;
            }
        }
    };
    static final Forward BM_IC_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "EXACT_BM_IC_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int targetP = regex.exactP;
            int targetEnd = regex.exactEnd;
            int tail = targetEnd - 1;
            int end = textRange + (targetEnd - targetP) - 1;
            int s = textP + (targetEnd - targetP) - 1;
            if (end > textEnd) {
                end = textEnd;
            }
            if (regex.intMap == null) {
                while (s < end) {
                    int p = s - (targetEnd - targetP) + 1;
                    if (SearchAlgorithm.lowerCaseMatch(target, targetP, targetEnd, text, p, s + 1)) {
                        return p;
                    }
                    s += regex.map[text[s] & 0xFF];
                }
            } else {
                while (s < end) {
                    int p = s - (targetEnd - targetP) + 1;
                    if (SearchAlgorithm.lowerCaseMatch(target, targetP, targetEnd, text, p, s + 1)) {
                        return p;
                    }
                    s += regex.intMap[text[s] & 0xFF];
                }
            }
            return -1;
        }
    };
    static final Forward BM_NOT_REV_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "EXACT_BM_NOT_REV_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int end = textRange;
            int targetEnd = regex.exactEnd;
            int tail = targetEnd - 1;
            int targetP = regex.exactP;
            int tlen1 = tail - targetP;
            if (end + tlen1 > textEnd) {
                end = textEnd - tlen1;
            }
            int s = textP;
            if (regex.intMap == null) {
                while (s < end) {
                    int se;
                    int p = se = s + tlen1;
                    int t = tail;
                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return s;
                        }
                        --p;
                        --t;
                    }
                    byte skip = regex.map[text[se] & 0xFF];
                    t = s;
                    while (++s - t < skip && s < end) {
                    }
                }
            } else {
                while (s < end) {
                    int se;
                    int p = se = s + tlen1;
                    int t = tail;
                    while (text[p] == target[t]) {
                        if (t == targetP) {
                            return s;
                        }
                        --p;
                        --t;
                    }
                    int skip = regex.intMap[text[se] & 0xFF];
                    t = s;
                    while (++s - t < skip && s < end) {
                    }
                }
            }
            return -1;
        }
    };
    static final Forward BM_NOT_REV_IC_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "EXACT_BM_NOT_REV_IC_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            char[] target = regex.exact;
            int end = textRange;
            int targetEnd = regex.exactEnd;
            int tail = targetEnd - 1;
            int targetP = regex.exactP;
            int tlen1 = tail - targetP;
            if (end + tlen1 > textEnd) {
                end = textEnd - tlen1;
            }
            int s = textP;
            if (regex.intMap == null) {
                while (s < end) {
                    int se = s + tlen1;
                    if (SearchAlgorithm.lowerCaseMatch(target, targetP, targetEnd, text, s, se + 1)) {
                        return s;
                    }
                    byte skip = regex.map[text[se] & 0xFF];
                    int t = s;
                    while (++s - t < skip && s < end) {
                    }
                }
            } else {
                while (s < end) {
                    int se = s + tlen1;
                    if (SearchAlgorithm.lowerCaseMatch(target, targetP, targetEnd, text, s, se + 1)) {
                        return s;
                    }
                    int skip = regex.intMap[text[se] & 0xFF];
                    int t = s;
                    while (++s - t < skip && s < end) {
                    }
                }
            }
            return -1;
        }
    };
    static final Forward MAP_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "MAP_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            byte[] map = regex.map;
            for (int s = textP; s < textRange; ++s) {
                if (map[text[s] & 0xFF] == 0) continue;
                return s;
            }
            return -1;
        }
    };
    static final Backward MAP_BACKWARD = new Backward(){

        @Override
        final int search(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            byte[] map = regex.map;
            int s = textStart;
            if (s >= textEnd) {
                s = textEnd - 1;
            }
            while (s >= textP) {
                if (map[text[s] & 0xFF] != 0) {
                    return s;
                }
                s = EncodingHelper.prevCharHead(adjustText, s);
            }
            return -1;
        }
    };
    static final Forward MAP_SB_FORWARD = new Forward(){

        @Override
        final String getName() {
            return "MAP_SB_FORWARD";
        }

        @Override
        final int search(Regex regex, char[] text, int textP, int textEnd, int textRange) {
            byte[] map = regex.map;
            for (int s = textP; s < textRange; ++s) {
                if (map[text[s] & 0xFF] == 0) continue;
                return s;
            }
            return -1;
        }
    };
    static final Backward MAP_SB_BACKWARD = new Backward(){

        @Override
        final int search(Regex regex, char[] text, int textP, int adjustText, int textEnd, int textStart, int s_, int range_) {
            byte[] map = regex.map;
            int s = textStart;
            if (s >= textEnd) {
                s = textEnd - 1;
            }
            while (s >= textP) {
                if (map[text[s] & 0xFF] != 0) {
                    return s;
                }
                --s;
            }
            return -1;
        }
    };

    SearchAlgorithm() {
    }

    private static boolean lowerCaseMatch(char[] t, int tPp, int tEnd, char[] chars, int pp, int end) {
        int tP = tPp;
        int p = pp;
        while (tP < tEnd) {
            if (t[tP++] == EncodingHelper.toLowerCase(chars[p++])) continue;
            return false;
        }
        return true;
    }

    static abstract class Backward {
        Backward() {
        }

        abstract int search(Regex var1, char[] var2, int var3, int var4, int var5, int var6, int var7, int var8);
    }

    static abstract class Forward {
        Forward() {
        }

        abstract String getName();

        abstract int search(Regex var1, char[] var2, int var3, int var4, int var5);
    }
}

