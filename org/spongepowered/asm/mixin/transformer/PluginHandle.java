/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Strings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.throwables.CompanionPluginError;
import org.spongepowered.asm.mixin.transformer.MixinConfig;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.service.IMixinService;

class PluginHandle {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    private final MixinConfig parent;
    private final IMixinConfigPlugin plugin;
    private CompatibilityMode mode = CompatibilityMode.NORMAL;
    private Method mdPreApply;
    private Method mdPostApply;

    PluginHandle(MixinConfig parent, IMixinService service, String pluginClassName) {
        IMixinConfigPlugin plugin = null;
        if (!Strings.isNullOrEmpty((String)pluginClassName)) {
            try {
                Class<?> pluginClass = service.getClassProvider().findClass(pluginClassName, true);
                plugin = (IMixinConfigPlugin)pluginClass.newInstance();
            }
            catch (Throwable th) {
                logger.error("Error loading companion plugin class [{}] for mixin config [{}]. The plugin may be out of date: {}:{}", new Object[]{pluginClassName, parent, th.getClass().getSimpleName(), th.getMessage(), th});
                plugin = null;
            }
        }
        this.parent = parent;
        this.plugin = plugin;
    }

    IMixinConfigPlugin get() {
        return this.plugin;
    }

    boolean isAvailable() {
        return this.plugin != null;
    }

    void onLoad(String mixinPackage) {
        if (this.plugin != null) {
            this.plugin.onLoad(mixinPackage);
        }
    }

    String getRefMapperConfig() {
        return this.plugin != null ? this.plugin.getRefMapperConfig() : null;
    }

    List<String> getMixins() {
        return this.plugin != null ? this.plugin.getMixins() : null;
    }

    boolean shouldApplyMixin(String targetName, String className) {
        return this.plugin == null || this.plugin.shouldApplyMixin(targetName, className);
    }

    public void preApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, MixinInfo mixinInfo) {
        if (this.plugin == null) {
            return;
        }
        if (this.mode == CompatibilityMode.FAILED) {
            throw new IllegalStateException("Companion plugin failure for [" + this.parent + "] plugin [" + this.plugin.getClass() + "]");
        }
        if (this.mode == CompatibilityMode.COMPATIBLE) {
            try {
                this.applyLegacy(this.mdPreApply, targetClassName, targetClass, mixinClassName, mixinInfo);
            }
            catch (Exception ex) {
                this.mode = CompatibilityMode.FAILED;
                throw ex;
            }
            return;
        }
        try {
            this.plugin.preApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        }
        catch (AbstractMethodError ex) {
            this.mode = CompatibilityMode.COMPATIBLE;
            this.initReflection();
            this.preApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        }
    }

    public void postApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, MixinInfo mixinInfo) {
        if (this.plugin == null) {
            return;
        }
        if (this.mode == CompatibilityMode.FAILED) {
            throw new IllegalStateException("Companion plugin failure for [" + this.parent + "] plugin [" + this.plugin.getClass() + "]");
        }
        if (this.mode == CompatibilityMode.COMPATIBLE) {
            try {
                this.applyLegacy(this.mdPostApply, targetClassName, targetClass, mixinClassName, mixinInfo);
            }
            catch (Exception ex) {
                this.mode = CompatibilityMode.FAILED;
                throw ex;
            }
            return;
        }
        try {
            this.plugin.postApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        }
        catch (AbstractMethodError ex) {
            this.mode = CompatibilityMode.COMPATIBLE;
            this.initReflection();
            this.postApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        }
    }

    private void initReflection() {
        if (this.mdPreApply != null) {
            return;
        }
        try {
            Class<?> pluginClass = this.plugin.getClass();
            this.mdPreApply = pluginClass.getMethod("preApply", String.class, ClassNode.class, String.class, IMixinInfo.class);
            this.mdPostApply = pluginClass.getMethod("postApply", String.class, ClassNode.class, String.class, IMixinInfo.class);
        }
        catch (Throwable th) {
            logger.catching(th);
        }
    }

    private void applyLegacy(Method method, String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        try {
            method.invoke(this.plugin, targetClassName, new ClassNode(targetClass), mixinClassName, mixinInfo);
        }
        catch (LinkageError err) {
            throw new CompanionPluginError(this.apiError("Accessing [" + err.getMessage() + "]"), err);
        }
        catch (IllegalAccessException ex) {
            throw new CompanionPluginError(this.apiError("Fallback failed [" + ex.getMessage() + "]"), ex);
        }
        catch (IllegalArgumentException ex) {
            throw new CompanionPluginError(this.apiError("Fallback failed [" + ex.getMessage() + "]"), ex);
        }
        catch (InvocationTargetException ex) {
            Throwable th = ex.getCause() != null ? ex.getCause() : ex;
            throw new CompanionPluginError(this.apiError("Fallback failed [" + th.getMessage() + "]"), th);
        }
    }

    private String apiError(String message) {
        return String.format("Companion plugin attempted to use a deprected API in [%s] plugin [%s]: %s", this.parent, this.plugin.getClass().getName(), message);
    }

    static enum CompatibilityMode {
        NORMAL,
        COMPATIBLE,
        FAILED;

    }
}

