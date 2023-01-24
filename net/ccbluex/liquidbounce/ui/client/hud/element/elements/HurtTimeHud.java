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

@ElementInfo(name="HurtTimeHud")
public class HurtTimeHud
extends Element {
    public final ListValue shadowValue = new ListValue("Shadow", new String[]{"None", "Basic", "Thick"}, "None");
    public FloatValue indx = new FloatValue("noting", 1.0E-7f, 0.0f, 4.0E-6f);
    public BoolValue BlurValue = new BoolValue("blur", false);
    public float animWidth;
    public float animatedCircleEnd;
    float Width = 41.0f;
    float Height = 42.0f;
    int x = 0;
    int y = 0;
    public Float x2 = (Float)this.indx.get();
    Double renderX;
    Double renderY;

    @Override
    @NativeMethod
    public native Border drawElement();

    static {
        Loader.registerNativesForClass(2, HurtTimeHud.class);
    }
}

