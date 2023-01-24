/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package org.spongepowered.asm.mixin.injection.invoke;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.invoke.InvokeInjector;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgsClassGenerator;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;

public class ModifyArgsInjector
extends InvokeInjector {
    private final ArgsClassGenerator argsClassGenerator;

    public ModifyArgsInjector(InjectionInfo info) {
        super(info, "@ModifyArgs");
        this.argsClassGenerator = (ArgsClassGenerator)info.getContext().getExtensions().getGenerator(ArgsClassGenerator.class);
    }

    @Override
    protected void checkTarget(Target target) {
        this.checkTargetModifiers(target, false);
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode node) {
        this.checkTargetForNode(target, node, InjectionPoint.RestrictTargetLevel.ALLOW_ALL);
        super.inject(target, node);
    }

    @Override
    protected void injectAtInvoke(Target target, InjectionNodes.InjectionNode node) {
        MethodInsnNode targetMethod = (MethodInsnNode)node.getCurrentTarget();
        Type[] args2 = Type.getArgumentTypes((String)targetMethod.desc);
        if (args2.length == 0) {
            throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " targets a method invocation " + targetMethod.name + targetMethod.desc + " with no arguments!");
        }
        String clArgs = this.argsClassGenerator.getArgsClass(targetMethod.desc, this.info.getContext().getMixin()).getName();
        boolean withArgs = this.verifyTarget(target);
        InsnList insns = new InsnList();
        Target.Extension extraStack = target.extendStack().add(1);
        this.packArgs(insns, clArgs, targetMethod);
        if (withArgs) {
            extraStack.add(target.arguments);
            Bytecode.loadArgs(target.arguments, insns, target.isStatic ? 0 : 1);
        }
        this.invokeHandler(insns);
        this.unpackArgs(insns, clArgs, args2);
        extraStack.apply();
        target.insns.insertBefore((AbstractInsnNode)targetMethod, insns);
    }

    private boolean verifyTarget(Target target) {
        String shortDesc = String.format("(L%s;)V", ArgsClassGenerator.ARGS_REF);
        if (!this.methodNode.desc.equals(shortDesc)) {
            String targetDesc = Bytecode.changeDescriptorReturnType(target.method.desc, "V");
            String longDesc = String.format("(L%s;%s", ArgsClassGenerator.ARGS_REF, targetDesc.substring(1));
            if (this.methodNode.desc.equals(longDesc)) {
                return true;
            }
            throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " has an invalid signature " + this.methodNode.desc + ", expected " + shortDesc + " or " + longDesc);
        }
        return false;
    }

    private void packArgs(InsnList insns, String clArgs, MethodInsnNode targetMethod) {
        String factoryDesc = Bytecode.changeDescriptorReturnType(targetMethod.desc, "L" + clArgs + ";");
        insns.add((AbstractInsnNode)new MethodInsnNode(184, clArgs, "of", factoryDesc, false));
        insns.add((AbstractInsnNode)new InsnNode(89));
        if (!this.isStatic) {
            insns.add((AbstractInsnNode)new VarInsnNode(25, 0));
            insns.add((AbstractInsnNode)new InsnNode(95));
        }
    }

    private void unpackArgs(InsnList insns, String clArgs, Type[] args2) {
        for (int i = 0; i < args2.length; ++i) {
            if (i < args2.length - 1) {
                insns.add((AbstractInsnNode)new InsnNode(89));
            }
            insns.add((AbstractInsnNode)new MethodInsnNode(182, clArgs, "$" + i, "()" + args2[i].getDescriptor(), false));
            if (i >= args2.length - 1) continue;
            if (args2[i].getSize() == 1) {
                insns.add((AbstractInsnNode)new InsnNode(95));
                continue;
            }
            insns.add((AbstractInsnNode)new InsnNode(93));
            insns.add((AbstractInsnNode)new InsnNode(88));
        }
    }
}

