/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.launch.platform;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.connect.IMixinConnector;
import org.spongepowered.asm.service.IClassProvider;
import org.spongepowered.asm.service.MixinService;

public class MixinConnectorManager {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final Set<String> connectorClasses = new LinkedHashSet<String>();
    private final List<IMixinConnector> connectors = new ArrayList<IMixinConnector>();

    MixinConnectorManager() {
    }

    void addConnector(String connectorClass) {
        this.connectorClasses.add(connectorClass);
    }

    void inject() {
        this.loadConnectors();
        this.initConnectors();
    }

    void loadConnectors() {
        IClassProvider classProvider = MixinService.getService().getClassProvider();
        for (String connectorClassName : this.connectorClasses) {
            Class<?> connectorClass = null;
            try {
                Class<?> clazz = classProvider.findClass(connectorClassName);
                if (!IMixinConnector.class.isAssignableFrom(clazz)) {
                    logger.error("Mixin Connector [" + connectorClassName + "] does not implement IMixinConnector");
                    continue;
                }
                connectorClass = clazz;
            }
            catch (ClassNotFoundException ex) {
                logger.catching((Throwable)ex);
                continue;
            }
            try {
                IMixinConnector connector = (IMixinConnector)connectorClass.newInstance();
                this.connectors.add(connector);
                logger.info("Successfully loaded Mixin Connector [" + connectorClassName + "]");
            }
            catch (ReflectiveOperationException ex) {
                logger.warn("Error loading Mixin Connector [" + connectorClassName + "]", (Throwable)ex);
            }
        }
        this.connectorClasses.clear();
    }

    void initConnectors() {
        for (IMixinConnector connector : this.connectors) {
            try {
                connector.connect();
            }
            catch (Exception ex) {
                logger.warn("Error initialising Mixin Connector [" + connector.getClass().getName() + "]", (Throwable)ex);
            }
        }
    }
}

