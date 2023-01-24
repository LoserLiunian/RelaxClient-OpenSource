/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;
import org.spongepowered.asm.service.IMixinAuditTrail;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.perf.Profiler;

public class MixinClassGenerator {
    static final Logger logger = LogManager.getLogger((String)"mixin");
    private final Extensions extensions;
    private final Profiler profiler;
    private final IMixinAuditTrail auditTrail;

    MixinClassGenerator(MixinEnvironment environment, Extensions extensions) {
        this.extensions = extensions;
        this.profiler = MixinEnvironment.getProfiler();
        this.auditTrail = MixinService.getService().getAuditTrail();
    }

    synchronized boolean generateClass(MixinEnvironment environment, String name, ClassNode classNode) {
        if (name == null) {
            logger.warn("MixinClassGenerator tried to generate a class with no name!");
            return false;
        }
        for (IClassGenerator generator : this.extensions.getGenerators()) {
            Profiler.Section genTimer = this.profiler.begin("generator", generator.getClass().getSimpleName().toLowerCase(Locale.ROOT));
            boolean success = generator.generate(name, classNode);
            genTimer.end();
            if (!success) continue;
            if (this.auditTrail != null) {
                this.auditTrail.onGenerate(name, generator.getName());
            }
            this.extensions.export(environment, name.replace('.', '/'), false, classNode);
            return true;
        }
        return false;
    }
}

