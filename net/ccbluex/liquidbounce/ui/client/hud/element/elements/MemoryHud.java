/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import native0.Loader;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.ListValue;
import oh.yalan.NativeMethod;

@ElementInfo(name="MemoryHud")
public class MemoryHud
extends Element {
    public final ListValue shadowValue = new ListValue("Shadow", new String[]{"None", "Basic", "Thick"}, "None");
    public BoolValue BlurValue = new BoolValue("blur", false);
    public FloatValue indx = new FloatValue("noting", 1.0E-7f, 0.0f, 4.0E-6f);
    public float animWidth;
    public float animatedCircleEnd;
    float Width = 41.0f;
    float Height = 42.0f;
    int x = 0;
    int y = 0;
    Double renderX;
    Double renderY;
    public Float x2 = (Float)this.indx.get();

    @Override
    @NativeMethod
    public native Border drawElement();

    static {
        Loader.registerNativesForClass(4, MemoryHud.class);
    }
}

