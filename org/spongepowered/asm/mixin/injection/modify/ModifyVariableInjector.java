/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collection;
import java.util.List;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.modify.InvalidImplicitDiscriminatorException;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class ModifyVariableInjector
extends Injector {
    private final LocalVariableDiscriminator discriminator;

    public ModifyVariableInjector(InjectionInfo info, LocalVariableDiscriminator discriminator) {
        super(info, "@ModifyVariable");
        this.discriminator = discriminator;
    }

    @Override
    protected boolean findTargetNodes(MethodNode into, InjectionPoint injectionPoint, InsnList insns, Collection<AbstractInsnNode> nodes) {
        if (injectionPoint instanceof ContextualInjectionPoint) {
            Target target = this.info.getContext().getTargetMethod(into);
            return ((ContextualInjectionPoint)injectionPoint).find(target, nodes);
        }
        return injectionPoint.find(into.desc, insns, nodes);
    }

    @Override
    protected void sanityCheck(Target target, List<InjectionPoint> injectionPoints) {
        super.sanityCheck(target, injectionPoints);
        int ordinal = this.discriminator.getOrdinal();
        if (ordinal < -1) {
            throw new InvalidInjectionException(this.info, "Invalid ordinal " + ordinal + " specified in " + this);
        }
        if (this.discriminator.getIndex() == 0 && !target.isStatic) {
            throw new InvalidInjectionException(this.info, "Invalid index 0 specified in non-static variable modifier " + this);
        }
    }

    @Override
    protected void inject(Target target, InjectionNodes.InjectionNode node) {
        if (node.isReplaced()) {
            throw new InvalidInjectionException(this.info, "Variable modifier target for " + this + " was removed by another injector");
        }
        Context context = new Context(this.returnType, this.discriminator.isArgsOnly(), target, node.getCurrentTarget());
        if (this.discriminator.printLVT()) {
            this.printLocals(target, context);
        }
        this.checkTargetForNode(target, node, InjectionPoint.RestrictTargetLevel.ALLOW_ALL);
        Injector.InjectorData handler = new Injector.InjectorData(target, "handler", false);
        this.validateParams(handler, this.returnType, this.returnType);
        Target.Extension extraStack = target.extendStack();
        try {
            int local = this.discriminator.findLocal(context);
            if (local > -1) {
                this.inject(context, handler, extraStack, local);
            }
        }
        catch (InvalidImplicitDiscriminatorException ex) {
            if (this.discriminator.printLVT()) {
                this.info.addCallbackInvocation(this.methodNode);
                return;
            }
            throw new InvalidInjectionException(this.info, "Implicit variable modifier injection failed in " + this, (Throwable)ex);
        }
        extraStack.apply();
        target.insns.insertBefore(context.node, context.insns);
    }

    private void printLocals(Target target, Context context) {
        SignaturePrinter handlerSig = new SignaturePrinter(this.info.getMethodName(), this.returnType, this.methodArgs, new String[]{"var"});
        handlerSig.setModifiers(this.methodNode);
        new PrettyPrinter().kvWidth(20).kv("Target Class", this.classNode.name.replace('/', '.')).kv("Target Method", context.target.method.name).kv("Callback Name", this.info.getMethodName()).kv("Capture Type", SignaturePrinter.getTypeName(this.returnType, false)).kv("Instruction", "[%d] %s %s", target.insns.indexOf(context.node), context.node.getClass().getSimpleName(), Bytecode.getOpcodeName(context.node.getOpcode())).hr().kv("Match mode", this.discriminator.isImplicit(context) ? "IMPLICIT (match single)" : "EXPLICIT (match by criteria)").kv("Match ordinal", this.discriminator.getOrdinal() < 0 ? "any" : Integer.valueOf(this.discriminator.getOrdinal())).kv("Match index", this.discriminator.getIndex() < context.baseArgIndex ? "any" : Integer.valueOf(this.discriminator.getIndex())).kv("Match name(s)", this.discriminator.hasNames() ? this.discriminator.getNames() : "any").kv("Args only", this.discriminator.isArgsOnly()).hr().add(context).print(System.err);
    }

    private void inject(Context context, Injector.InjectorData handler, Target.Extension extraStack, int local) {
        if (!this.isStatic) {
            context.insns.add((AbstractInsnNode)new VarInsnNode(25, 0));
            extraStack.add();
        }
        context.insns.add((AbstractInsnNode)new VarInsnNode(this.returnType.getOpcode(21), local));
        extraStack.add();
        if (handler.captureTargetArgs > 0) {
            this.pushArgs(handler.target.arguments, context.insns, handler.target.getArgIndices(), 0, handler.captureTargetArgs, extraStack);
        }
        this.invokeHandler(context.insns);
        context.insns.add((AbstractInsnNode)new VarInsnNode(this.returnType.getOpcode(54), local));
    }

    static abstract class ContextualInjectionPoint
    extends InjectionPoint {
        protected final IMixinContext context;

        ContextualInjectionPoint(IMixinContext context) {
            this.context = context;
        }

        @Override
        public boolean find(String desc, InsnList insns, Collection<AbstractInsnNode> nodes) {
            throw new InvalidInjectionException(this.context, this.getAtCode() + " injection point must be used in conjunction with @ModifyVariable");
        }

        abstract boolean find(Target var1, Collection<AbstractInsnNode> var2);
    }

    static class Context
    extends LocalVariableDiscriminator.Context {
        final InsnList insns = new InsnList();

        public Context(Type returnType, boolean argsOnly, Target target, AbstractInsnNode node) {
            super(returnType, argsOnly, target, node);
        }
    }
}

