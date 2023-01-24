/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.modlauncher.Environment
 *  cpw.mods.modlauncher.Launcher
 *  cpw.mods.modlauncher.api.IEnvironment$Keys
 *  cpw.mods.modlauncher.api.TypesafeMap$Key
 */
package org.spongepowered.asm.launch.platform;

import cpw.mods.modlauncher.Environment;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.TypesafeMap;
import java.util.Collection;
import java.util.Locale;
import org.spongepowered.asm.launch.platform.IMixinPlatformAgent;
import org.spongepowered.asm.launch.platform.IMixinPlatformServiceAgent;
import org.spongepowered.asm.launch.platform.MixinPlatformAgentAbstract;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;

public class MixinPlatformAgentMinecraftForge
extends MixinPlatformAgentAbstract
implements IMixinPlatformServiceAgent {
    @Override
    public void init() {
    }

    @Override
    public IMixinPlatformAgent.AcceptResult accept(MixinPlatformManager manager, IContainerHandle handle) {
        return IMixinPlatformAgent.AcceptResult.REJECTED;
    }

    @Override
    public String getSideName() {
        Environment environment = Launcher.INSTANCE.environment();
        String launchTarget = environment.getProperty((TypesafeMap.Key)IEnvironment.Keys.LAUNCHTARGET.get()).orElse("missing").toLowerCase(Locale.ROOT);
        if (launchTarget.contains("server")) {
            return "SERVER";
        }
        if (launchTarget.contains("client")) {
            return "CLIENT";
        }
        return null;
    }

    @Override
    public Collection<IContainerHandle> getMixinContainers() {
        return null;
    }
}

