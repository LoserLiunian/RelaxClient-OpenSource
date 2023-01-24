/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.service;

import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.service.IGlobalPropertyService;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.IMixinServiceBootstrap;
import org.spongepowered.asm.service.ServiceInitialisationException;
import org.spongepowered.asm.service.ServiceNotAvailableError;

public final class MixinService {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private static MixinService instance;
    private ServiceLoader<IMixinServiceBootstrap> bootstrapServiceLoader;
    private final Set<String> bootedServices = new HashSet<String>();
    private ServiceLoader<IMixinService> serviceLoader;
    private IMixinService service = null;
    private IGlobalPropertyService propertyService;

    private MixinService() {
        this.runBootServices();
    }

    private void runBootServices() {
        this.bootstrapServiceLoader = ServiceLoader.load(IMixinServiceBootstrap.class, this.getClass().getClassLoader());
        Iterator<IMixinServiceBootstrap> iter = this.bootstrapServiceLoader.iterator();
        while (iter.hasNext()) {
            try {
                IMixinServiceBootstrap bootService = iter.next();
                bootService.bootstrap();
                this.bootedServices.add(bootService.getServiceClassName());
            }
            catch (ServiceInitialisationException ex) {
                logger.debug("Mixin bootstrap service {} is not available: {}", new Object[]{ex.getStackTrace()[0].getClassName(), ex.getMessage()});
            }
            catch (Throwable th) {
                logger.debug("Catching {}:{} initialising service", new Object[]{th.getClass().getName(), th.getMessage(), th});
            }
        }
    }

    private static MixinService getInstance() {
        if (instance == null) {
            instance = new MixinService();
        }
        return instance;
    }

    public static void boot() {
        MixinService.getInstance();
    }

    public static IMixinService getService() {
        return MixinService.getInstance().getServiceInstance();
    }

    private synchronized IMixinService getServiceInstance() {
        if (this.service == null) {
            this.service = this.initService();
        }
        return this.service;
    }

    private IMixinService initService() {
        this.serviceLoader = ServiceLoader.load(IMixinService.class, this.getClass().getClassLoader());
        Iterator<IMixinService> iter = this.serviceLoader.iterator();
        ArrayList<String> badServices = new ArrayList<String>();
        int brokenServiceCount = 0;
        while (iter.hasNext()) {
            try {
                IMixinService service = iter.next();
                if (this.bootedServices.contains(service.getClass().getName())) {
                    logger.debug("MixinService [{}] was successfully booted in {}", new Object[]{service.getName(), this.getClass().getClassLoader()});
                }
                if (service.isValid()) {
                    return service;
                }
                logger.debug("MixinService [{}] is not valid", new Object[]{service.getName()});
                badServices.add(String.format("INVALID[%s]", service.getName()));
            }
            catch (ServiceConfigurationError sce) {
                ++brokenServiceCount;
            }
            catch (Throwable th) {
                String faultingClassName = th.getStackTrace()[0].getClassName();
                logger.debug("MixinService [{}] failed initialisation: {}", new Object[]{faultingClassName, th.getMessage()});
                int pos = faultingClassName.lastIndexOf(46);
                badServices.add(String.format("ERROR[%s]", pos < 0 ? faultingClassName : faultingClassName.substring(pos + 1)));
            }
        }
        String brokenServiceNote = brokenServiceCount == 0 ? "" : " and " + brokenServiceCount + " other invalid services.";
        throw new ServiceNotAvailableError("No mixin host service is available. Services: " + Joiner.on((String)", ").join(badServices) + brokenServiceNote);
    }

    public static IGlobalPropertyService getGlobalPropertyService() {
        return MixinService.getInstance().getGlobalPropertyServiceInstance();
    }

    private IGlobalPropertyService getGlobalPropertyServiceInstance() {
        if (this.propertyService == null) {
            this.propertyService = this.initPropertyService();
        }
        return this.propertyService;
    }

    private IGlobalPropertyService initPropertyService() {
        ServiceLoader<IGlobalPropertyService> serviceLoader = ServiceLoader.load(IGlobalPropertyService.class, this.getClass().getClassLoader());
        Iterator<IGlobalPropertyService> iter = serviceLoader.iterator();
        while (iter.hasNext()) {
            try {
                IGlobalPropertyService service = iter.next();
                return service;
            }
            catch (ServiceConfigurationError serviceConfigurationError) {}
            finally {
            }
        }
        throw new ServiceNotAvailableError("No mixin global property service is available");
    }
}

