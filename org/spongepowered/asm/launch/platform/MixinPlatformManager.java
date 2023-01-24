/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.launch.platform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.platform.CommandLineOptions;
import org.spongepowered.asm.launch.platform.MixinConnectorManager;
import org.spongepowered.asm.launch.platform.MixinContainer;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.throwables.MixinError;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.service.ServiceVersionError;

public class MixinPlatformManager {
    private static final String DEFAULT_MAIN_CLASS = "net.minecraft.client.main.Main";
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final Map<IContainerHandle, MixinContainer> containers = new LinkedHashMap<IContainerHandle, MixinContainer>();
    private final MixinConnectorManager connectors = new MixinConnectorManager();
    private MixinContainer primaryContainer;
    private boolean prepared = false;
    private boolean injected;

    public void init() {
        logger.debug("Initialising Mixin Platform Manager");
        IContainerHandle primaryContainerHandle = MixinService.getService().getPrimaryContainer();
        this.primaryContainer = this.addContainer(primaryContainerHandle);
        this.scanForContainers();
    }

    public Collection<String> getPhaseProviderClasses() {
        Collection<String> phaseProviders = this.primaryContainer.getPhaseProviders();
        if (phaseProviders != null) {
            return Collections.unmodifiableCollection(phaseProviders);
        }
        return Collections.emptyList();
    }

    public final MixinContainer addContainer(IContainerHandle handle) {
        MixinContainer existingContainer = this.containers.get(handle);
        if (existingContainer != null) {
            return existingContainer;
        }
        MixinContainer container = this.createContainerFor(handle);
        this.containers.put(handle, container);
        this.addNestedContainers(handle);
        return container;
    }

    private MixinContainer createContainerFor(IContainerHandle handle) {
        logger.debug("Adding mixin platform agents for container {}", new Object[]{handle});
        MixinContainer container = new MixinContainer(this, handle);
        if (this.prepared) {
            container.prepare();
        }
        return container;
    }

    private void addNestedContainers(IContainerHandle handle) {
        for (IContainerHandle nested : handle.getNestedContainers()) {
            if (this.containers.containsKey(nested)) continue;
            this.addContainer(nested);
        }
    }

    public final void prepare(CommandLineOptions args2) {
        this.prepared = true;
        for (MixinContainer container : this.containers.values()) {
            container.prepare();
        }
        for (String config : args2.getConfigs()) {
            this.addConfig(config);
        }
    }

    public final void inject() {
        if (this.injected) {
            return;
        }
        this.injected = true;
        if (this.primaryContainer != null) {
            this.primaryContainer.initPrimaryContainer();
        }
        this.scanForContainers();
        logger.debug("inject() running with {} agents", new Object[]{this.containers.size()});
        for (MixinContainer container : this.containers.values()) {
            try {
                container.inject();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.connectors.inject();
    }

    private void scanForContainers() {
        Collection<IContainerHandle> mixinContainers = null;
        try {
            mixinContainers = MixinService.getService().getMixinContainers();
        }
        catch (AbstractMethodError ame) {
            throw new ServiceVersionError("Mixin service is out of date");
        }
        ArrayList<IContainerHandle> existingContainers = new ArrayList<IContainerHandle>(this.containers.keySet());
        for (IContainerHandle existingContainer : existingContainers) {
            this.addNestedContainers(existingContainer);
        }
        for (IContainerHandle handle : mixinContainers) {
            try {
                logger.debug("Adding agents for Mixin Container {}", new Object[]{handle});
                this.addContainer(handle);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getLaunchTarget() {
        return DEFAULT_MAIN_CLASS;
    }

    final void setCompatibilityLevel(String level) {
        try {
            MixinEnvironment.CompatibilityLevel value = MixinEnvironment.CompatibilityLevel.valueOf(level.toUpperCase(Locale.ROOT));
            logger.debug("Setting mixin compatibility level: {}", new Object[]{value});
            MixinEnvironment.setCompatibilityLevel(value);
        }
        catch (IllegalArgumentException ex) {
            logger.warn("Invalid compatibility level specified: {}", new Object[]{level});
        }
    }

    final void addConfig(String config) {
        if (config.endsWith(".json")) {
            logger.debug("Registering mixin config: {}", new Object[]{config});
            Mixins.addConfiguration(config);
        } else if (config.contains(".json@")) {
            throw new MixinError("Setting config phase via manifest is no longer supported: " + config + ". Specify target in config instead");
        }
    }

    final void addTokenProvider(String provider) {
        if (provider.contains("@")) {
            String[] parts = provider.split("@", 2);
            MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(parts[1]);
            if (phase != null) {
                logger.debug("Registering token provider class: {}", new Object[]{parts[0]});
                MixinEnvironment.getEnvironment(phase).registerTokenProviderClass(parts[0]);
            }
            return;
        }
        MixinEnvironment.getDefaultEnvironment().registerTokenProviderClass(provider);
    }

    final void addConnector(String connectorClass) {
        this.connectors.addConnector(connectorClass);
    }
}

