/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.data.IMetadataSection
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package me.utils.motionblur;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import net.ccbluex.liquidbounce.features.module.modules.render.MotionBlur;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class MotionBlurResource
implements IResource {
    public InputStream func_110527_b() {
        double amount = 0.7 + (double)((Integer)MotionBlur.MOTION_BLUR_AMOUNT.get()).intValue() / 100.0 * 3.0 - 0.01;
        return IOUtils.toInputStream((String)String.format(Locale.ENGLISH, "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}", amount, amount, amount));
    }

    public boolean func_110528_c() {
        return false;
    }

    public IMetadataSection func_110526_a(String metadata) {
        return null;
    }

    public ResourceLocation func_177241_a() {
        return null;
    }

    public String func_177240_d() {
        return null;
    }

    public void close() throws IOException {
    }
}

