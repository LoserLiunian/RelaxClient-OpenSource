/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.ClassNode
 */
package org.spongepowered.tools.agent;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;

class MixinAgentClassLoader
extends ClassLoader {
    private static final Logger logger = LogManager.getLogger((String)"mixin.agent");
    private Map<Class<?>, byte[]> mixins = new HashMap();
    private Map<String, byte[]> targets = new HashMap<String, byte[]>();

    MixinAgentClassLoader() {
    }

    void addMixinClass(String name) {
        logger.debug("Mixin class {} added to class loader", new Object[]{name});
        try {
            byte[] bytes = this.materialise(name);
            Class<?> clazz = this.defineClass(name, bytes, 0, bytes.length);
            clazz.newInstance();
            this.mixins.put(clazz, bytes);
        }
        catch (Throwable e) {
            logger.catching(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void addTargetClass(String name, ClassNode classNode) {
        Map<String, byte[]> map = this.targets;
        synchronized (map) {
            if (this.targets.containsKey(name)) {
                return;
            }
            try {
                ClassWriter cw = new ClassWriter(0);
                classNode.accept((ClassVisitor)cw);
                this.targets.put(name, cw.toByteArray());
            }
            catch (Exception ex) {
                logger.error("Error storing original class bytecode for {} in mixin hotswap agent. {}: {}", new Object[]{name, ex.getClass().getName(), ex.getMessage()});
                logger.debug((Object)ex);
            }
        }
    }

    byte[] getFakeMixinBytecode(Class<?> clazz) {
        return this.mixins.get(clazz);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    byte[] getOriginalTargetBytecode(String name) {
        Map<String, byte[]> map = this.targets;
        synchronized (map) {
            return this.targets.get(name);
        }
    }

    private byte[] materialise(String name) {
        ClassWriter cw = new ClassWriter(3);
        cw.visit(MixinEnvironment.getCompatibilityLevel().classVersion(), 1, name.replace('.', '/'), null, Type.getInternalName(Object.class), null);
        MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 0);
        mv.visitMethodInsn(183, Type.getInternalName(Object.class), "<init>", "()V", false);
        mv.visitInsn(177);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }
}

