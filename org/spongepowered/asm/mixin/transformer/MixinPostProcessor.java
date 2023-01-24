/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package org.spongepowered.asm.mixin.transformer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinConfig;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.meta.MixinProxy;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

class MixinPostProcessor
implements MixinConfig.IListener {
    private final String sessionId;
    private final Set<String> syntheticInnerClasses = new HashSet<String>();
    private final Map<String, MixinInfo> accessorMixins = new HashMap<String, MixinInfo>();
    private final Set<String> loadable = new HashSet<String>();

    MixinPostProcessor(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void onInit(MixinInfo mixin) {
        for (String innerClass : mixin.getSyntheticInnerClasses()) {
            this.registerSyntheticInner(innerClass.replace('/', '.'));
        }
    }

    @Override
    public void onPrepare(MixinInfo mixin) {
        String className = mixin.getClassName();
        if (mixin.isLoadable()) {
            this.registerLoadable(className);
        }
        if (mixin.isAccessor()) {
            this.registerAccessor(mixin);
        }
    }

    void registerSyntheticInner(String className) {
        this.syntheticInnerClasses.add(className);
    }

    void registerLoadable(String className) {
        this.loadable.add(className);
    }

    void registerAccessor(MixinInfo mixin) {
        this.registerLoadable(mixin.getClassName());
        this.accessorMixins.put(mixin.getClassName(), mixin);
    }

    boolean canProcess(String className) {
        return this.syntheticInnerClasses.contains(className) || this.loadable.contains(className);
    }

    public boolean processClass(String name, ClassNode classNode) {
        if (this.syntheticInnerClasses.contains(name)) {
            this.processSyntheticInner(classNode);
            return true;
        }
        if (this.accessorMixins.containsKey(name)) {
            MixinInfo mixin = this.accessorMixins.get(name);
            return this.processAccessor(classNode, mixin);
        }
        return false;
    }

    private void processSyntheticInner(ClassNode classNode) {
        classNode.access |= 1;
        for (FieldNode field : classNode.fields) {
            if ((field.access & 6) != 0) continue;
            field.access |= 1;
        }
        for (MethodNode method : classNode.methods) {
            if ((method.access & 6) != 0) continue;
            method.access |= 1;
        }
    }

    private boolean processAccessor(ClassNode classNode, MixinInfo mixin) {
        if (!MixinEnvironment.getCompatibilityLevel().supports(1)) {
            return false;
        }
        boolean transformed = false;
        MixinInfo.MixinClassNode mixinClassNode = mixin.getClassNode(0);
        ClassInfo targetClass = mixin.getTargets().get(0);
        for (MixinInfo.MixinMethodNode methodNode : mixinClassNode.mixinMethods) {
            if (!Bytecode.hasFlag(methodNode, 8)) continue;
            AnnotationNode accessor = methodNode.getVisibleAnnotation(Accessor.class);
            AnnotationNode invoker = methodNode.getVisibleAnnotation(Invoker.class);
            if (accessor == null && invoker == null) continue;
            ClassInfo.Method method = this.getAccessorMethod(mixin, methodNode, targetClass);
            MixinPostProcessor.createProxy(methodNode, targetClass, method);
            Annotations.setVisible(methodNode, MixinProxy.class, "sessionId", this.sessionId);
            classNode.methods.add(methodNode);
            transformed = true;
        }
        if (!transformed) {
            return false;
        }
        Bytecode.replace(mixinClassNode, classNode);
        return true;
    }

    private ClassInfo.Method getAccessorMethod(MixinInfo mixin, MethodNode methodNode, ClassInfo targetClass) throws MixinTransformerError {
        ClassInfo.Method method = mixin.getClassInfo().findMethod(methodNode, 10);
        if (!method.isConformed()) {
            String uniqueName = targetClass.getMethodMapper().getUniqueName(methodNode, this.sessionId, true);
            method.conform(uniqueName);
        }
        return method;
    }

    private static void createProxy(MethodNode methodNode, ClassInfo targetClass, ClassInfo.Method method) {
        methodNode.access |= 0x1000;
        methodNode.instructions.clear();
        Type[] args2 = Type.getArgumentTypes((String)methodNode.desc);
        Type returnType = Type.getReturnType((String)methodNode.desc);
        Bytecode.loadArgs(args2, methodNode.instructions, 0);
        methodNode.instructions.add((AbstractInsnNode)new MethodInsnNode(184, targetClass.getName(), method.getName(), methodNode.desc, false));
        methodNode.instructions.add((AbstractInsnNode)new InsnNode(returnType.getOpcode(172)));
        methodNode.maxStack = Bytecode.getFirstNonArgLocalIndex(args2, false);
        methodNode.maxLocals = 0;
    }
}

