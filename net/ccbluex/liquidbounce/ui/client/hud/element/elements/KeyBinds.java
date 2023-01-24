/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import native0.Loader;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.ListValue;
import oh.yalan.NativeMethod;

@ElementInfo(name="KeyBinds")
public class KeyBinds
extends Element {
    public final BoolValue onlyState = new BoolValue("OnlyModuleState", false);
    public final BoolValue BlurValue = new BoolValue("blur", false);
    public final ListValue shadowValue = new ListValue("Shadow", new String[]{"None", "Basic", "Thick"}, "None");
    Double renderX;
    Double renderY;

    @Override
    @NativeMethod
    public native Border drawElement();

    public int getmoduley() {
        int y = 0;
        for (Module module : LiquidBounce.moduleManager.getModules()) {
            if (module.getKeyBind() == 0 || ((Boolean)this.onlyState.get()).booleanValue() && !module.getState()) continue;
            y += 12;
        }
        return y;
    }

    static {
        Loader.registerNativesForClass(3, KeyBinds.class);
    }
}

