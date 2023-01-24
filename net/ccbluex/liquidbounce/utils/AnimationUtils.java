/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.utils;

public final class AnimationUtils {
    public static double animate(double target, double current, double speed) {
        boolean larger;
        if (current == target) {
            return current;
        }
        boolean bl = larger = target > current;
        if (speed < 0.0) {
            speed = 0.0;
        } else if (speed > 1.0) {
            speed = 1.0;
        }
        double dif = Math.max(target, current) - Math.min(target, current);
        double factor = dif * speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        if (larger) {
            if ((current += factor) >= target) {
                current = target;
            }
        } else if (target < current && (current -= factor) <= target) {
            current = target;
        }
        return current;
    }

    public static float animate(float target, float current, float speed) {
        boolean larger;
        if (current == target) {
            return current;
        }
        boolean bl = larger = target > current;
        if (speed < 0.0f) {
            speed = 0.0f;
        } else if (speed > 1.0f) {
            speed = 1.0f;
        }
        double dif = Math.max((double)target, (double)current) - Math.min((double)target, (double)current);
        double factor = dif * (double)speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        if (larger) {
            if ((current += (float)factor) >= target) {
                current = target;
            }
        } else if (target < current && (current -= (float)factor) <= target) {
            current = target;
        }
        return current;
    }

    public static double changer(double current, double add, double min, double max) {
        if ((current += add) > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        return current;
    }

    public static float changer(float current, float add, float min, float max) {
        if ((current += add) > max) {
            current = max;
        }
        if (current < min) {
            current = min;
        }
        return current;
    }
}

