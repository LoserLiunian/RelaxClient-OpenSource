/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;

@ModuleInfo(name="Animations", description="Render items Animations", category=ModuleCategory.RENDER)
public class Animations
extends Module {
    public static final ListValue Sword = new ListValue("PresetAnimationSword", new String[]{"Normal", "SlideDown1", "SlideDown2", "Slide", "Minecraft", "Avatar", "Tap1", "Tap2", "Poke", "Push1", "Push2", "Up", "Shield", "Akrien", "VisionFX", "Swong", "SigmaOld", "ETB", "Rotate360", "SmoothFloat", "Strange", "Reverse", "Zoom", "Move", "Stab", "Jello"}, "Slide");
    public static final ListValue transformFirstPersonRotate = new ListValue("TransformFirstPersonRotate", new String[]{"Rotate1", "Rotate2", "Custom", "None"}, "Rotate1");
    public static final ListValue doBlockTransformationsRotate = new ListValue("DoBlockTransformationsRotate", new String[]{"Rotate1", "Rotate2", "Custom", "None"}, "None");
    public static final ListValue swingMethod = new ListValue("SwingMethod", new String[]{"Swing", "Cancel", "Default"}, "Default");
    public static final ListValue swingCancelMode = new ListValue("NoSwingMode", new String[]{"ServerSide", "Default"}, "Default");
    public static final FloatValue itemPosX = new FloatValue("ItemPosX", 0.0f, -1.0f, 1.0f);
    public static final FloatValue itemPosY = new FloatValue("ItemPosY", 0.0f, -1.0f, 1.0f);
    public static final FloatValue itemPosZ = new FloatValue("ItemPosZ", 0.0f, -1.0f, 1.0f);
    public static final FloatValue Scale = new FloatValue("Scale", 0.4f, 0.0f, 4.0f);
    public static final FloatValue blockPosX = new FloatValue("BlockPosX", 0.0f, -1.0f, 1.0f);
    public static final FloatValue blockPosY = new FloatValue("BlockPosY", 0.0f, -1.0f, 1.0f);
    public static final FloatValue blockPosZ = new FloatValue("BlockPosZ", 0.0f, -1.0f, 1.0f);
    public static final IntegerValue customRotate1 = new IntegerValue("CustomRotate1", 0, -360, 360);
    public static final IntegerValue customRotate2 = new IntegerValue("CustomRotate2", 0, -360, 360);
    public static final IntegerValue customRotate3 = new IntegerValue("CustomRotate3", 0, -360, 360);
    public static final BoolValue RotateItems = new BoolValue("RotateItems", false);
    public static final FloatValue SpeedRotate = new FloatValue("SpeedRotate", 1.0f, 0.0f, 10.0f);
    public static final IntegerValue SpeedSwing = new IntegerValue("SpeedSwing", 4, 0, 20);
    public static final FloatValue mcSwordPos = new FloatValue("MCSwordPos", 0.45f, 0.0f, 0.5f);
    public static final BoolValue fakeBlock = new BoolValue("FakeBlock", false);
    public static final FloatValue bobbing = new FloatValue("Bobbing", 0.3f, 0.3f, 10.0f);
    public static final BoolValue blockEverything = new BoolValue("BlockEverything", false);
    public static final ListValue guiAnimations = new ListValue("Container-Animation", new String[]{"None", "Zoom", "VSlide", "HSlide", "HVSlide"}, "None");
}

