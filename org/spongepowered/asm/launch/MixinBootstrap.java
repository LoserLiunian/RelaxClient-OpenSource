/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.launch;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.launch.MixinInitialisationError;
import org.spongepowered.asm.launch.platform.CommandLineOptions;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.service.MixinService;

public abstract class MixinBootstrap {
    public static final String VERSION = "0.8";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static boolean initialised = false;
    private static boolean initState = true;
    private static MixinPlatformManager platform;

    private MixinBootstrap() {
    }

    @Deprecated
    public static void addProxy() {
        MixinService.getService().beginPhase();
    }

    public static MixinPlatformManager getPlatform() {
        if (platform == null) {
            Object globalPlatformManager = GlobalProperties.get(GlobalProperties.Keys.PLATFORM_MANAGER);
            if (globalPlatformManager instanceof MixinPlatformManager) {
                platform = (MixinPlatformManager)globalPlatformManager;
            } else {
                platform = new MixinPlatformManager();
                GlobalProperties.put(GlobalProperties.Keys.PLATFORM_MANAGER, platform);
                platform.init();
            }
        }
        return platform;
    }

    public static void init() {
        if (!MixinBootstrap.start()) {
            return;
        }
        MixinBootstrap.doInit(CommandLineOptions.defaultArgs());
    }

    static boolean start() {
        if (MixinBootstrap.isSubsystemRegistered()) {
            if (!MixinBootstrap.checkSubsystemVersion()) {
                throw new MixinInitialisationError("Mixin subsystem version " + MixinBootstrap.getActiveSubsystemVersion() + " was already initialised. Cannot bootstrap version " + VERSION);
            }
            return false;
        }
        MixinBootstrap.registerSubsystem(VERSION);
        if (!initialised) {
            initialised = true;
            MixinEnvironment.Phase initialPhase = MixinService.getService().getInitialPhase();
            if (initialPhase == MixinEnvironment.Phase.DEFAULT) {
                logger.error("Initialising mixin subsystem after game pre-init phase! Some mixins may be skipped.");
                MixinEnvironment.init(initialPhase);
                MixinBootstrap.getPlatform().prepare(CommandLineOptions.defaultArgs());
                initState = false;
            } else {
                MixinEnvironment.init(initialPhase);
            }
            MixinService.getService().beginPhase();
        }
        MixinBootstrap.getPlatform();
        return true;
    }

    @Deprecated
    static void doInit(List<String> args2) {
        MixinBootstrap.doInit(CommandLineOptions.ofArgs(args2));
    }

    static void doInit(CommandLineOptions args2) {
        if (!initialised) {
            if (MixinBootstrap.isSubsystemRegistered()) {
                logger.warn("Multiple Mixin containers present, init suppressed for 0.8");
                return;
            }
            throw new IllegalStateException("MixinBootstrap.doInit() called before MixinBootstrap.start()");
        }
        MixinBootstrap.getPlatform().getPhaseProviderClasses();
        if (initState) {
            MixinBootstrap.getPlatform().prepare(args2);
            MixinService.getService().init();
        }
    }

    static void inject() {
        MixinBootstrap.getPlatform().inject();
    }

    private static boolean isSubsystemRegistered() {
        return GlobalProperties.get(GlobalProperties.Keys.INIT) != null;
    }

    private static boolean checkSubsystemVersion() {
        return VERSION.equals(MixinBootstrap.getActiveSubsystemVersion());
    }

    private static Object getActiveSubsystemVersion() {
        Object version = GlobalProperties.get(GlobalProperties.Keys.INIT);
        return version != null ? version : "";
    }

    private static void registerSubsystem(String version) {
        GlobalProperties.put(GlobalProperties.Keys.INIT, version);
    }

    static {
        MixinService.boot();
        MixinService.getService().prepare();
    }
}

