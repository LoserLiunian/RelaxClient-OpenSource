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
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.launch.platform.IMixinPlatformAgent;
import org.spongepowered.asm.launch.platform.MixinPlatformManager;
import org.spongepowered.asm.launch.platform.container.IContainerHandle;
import org.spongepowered.asm.service.MixinService;

public class MixinContainer {
    private static final List<String> agentClasses = new ArrayList<String>();
    private static final Logger logger;
    private final IContainerHandle handle;
    private final List<IMixinPlatformAgent> agents = new ArrayList<IMixinPlatformAgent>();

    public MixinContainer(MixinPlatformManager manager, IContainerHandle handle) {
        this.handle = handle;
        Iterator<String> iter = agentClasses.iterator();
        while (iter.hasNext()) {
            String agentClass = iter.next();
            try {
                Class<?> clazz = Class.forName(agentClass);
                String simpleName = clazz.getSimpleName();
                logger.debug("Instancing new {} for {}", new Object[]{simpleName, this.handle});
                IMixinPlatformAgent agent = (IMixinPlatformAgent)clazz.newInstance();
                IMixinPlatformAgent.AcceptResult acceptAction = agent.accept(manager, this.handle);
                if (acceptAction == IMixinPlatformAgent.AcceptResult.ACCEPTED) {
                    this.agents.add(agent);
                } else if (acceptAction == IMixinPlatformAgent.AcceptResult.INVALID) {
                    iter.remove();
                    continue;
                }
                logger.debug("{} {} container {}", new Object[]{simpleName, acceptAction.name().toLowerCase(), this.handle});
            }
            catch (InstantiationException ex) {
                Throwable cause = ex.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException)cause;
                }
                throw new RuntimeException(cause);
            }
            catch (ReflectiveOperationException ex) {
                logger.catching((Throwable)ex);
            }
        }
    }

    public IContainerHandle getDescriptor() {
        return this.handle;
    }

    public Collection<String> getPhaseProviders() {
        ArrayList<String> phaseProviders = new ArrayList<String>();
        for (IMixinPlatformAgent agent : this.agents) {
            String phaseProvider = agent.getPhaseProvider();
            if (phaseProvider == null) continue;
            phaseProviders.add(phaseProvider);
        }
        return phaseProviders;
    }

    public void prepare() {
        for (IMixinPlatformAgent agent : this.agents) {
            logger.debug("Processing prepare() for {}", new Object[]{agent});
            agent.prepare();
        }
    }

    public void initPrimaryContainer() {
        for (IMixinPlatformAgent agent : this.agents) {
            logger.debug("Processing launch tasks for {}", new Object[]{agent});
            agent.initPrimaryContainer();
        }
    }

    public void inject() {
        for (IMixinPlatformAgent agent : this.agents) {
            logger.debug("Processing inject() for {}", new Object[]{agent});
            agent.inject();
        }
    }

    static {
        GlobalProperties.put(GlobalProperties.Keys.AGENTS, agentClasses);
        for (String agent : MixinService.getService().getPlatformAgents()) {
            agentClasses.add(agent);
        }
        agentClasses.add("org.spongepowered.asm.launch.platform.MixinPlatformAgentDefault");
        logger = LogManager.getLogger((String)"mixin");
    }
}

