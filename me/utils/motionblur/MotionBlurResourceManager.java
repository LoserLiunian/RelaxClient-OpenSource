/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package me.utils.motionblur;

import java.util.List;
import java.util.Set;
import me.utils.motionblur.MotionBlurResource;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class MotionBlurResourceManager
implements IResourceManager {
    public Set<String> func_135055_a() {
        return null;
    }

    public IResource func_110536_a(ResourceLocation location) {
        return new MotionBlurResource();
    }

    public List<IResource> func_135056_b(ResourceLocation location) {
        return null;
    }
}

