/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package org.spongepowered.asm.mixin.gen;

import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.gen.AccessorGeneratorField;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Bytecode;

public class AccessorGeneratorFieldSetter
extends AccessorGeneratorField {
    private boolean mutable;

    public AccessorGeneratorFieldSetter(AccessorInfo info) {
        super(info);
    }

    @Override
    public void validate() {
        super.validate();
        ClassInfo.Method method = this.info.getClassInfo().findMethod(this.info.getMethod());
        this.mutable = method.isDecoratedMutable();
        if (this.mutable || !Bytecode.hasFlag(this.targetField, 16)) {
            return;
        }
        if (this.info.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            LogManager.getLogger((String)"mixin").warn("{} for final field {}::{} is not @Mutable", new Object[]{this.info, ((MixinTargetContext)this.info.getContext()).getTarget(), this.targetField.name});
        }
    }

    @Override
    public MethodNode generate() {
        if (this.mutable) {
            this.targetField.access &= 0xFFFFFFEF;
        }
        int stackSpace = this.targetIsStatic ? 0 : 1;
        int maxLocals = stackSpace + this.targetType.getSize();
        int maxStack = stackSpace + this.targetType.getSize();
        MethodNode method = this.createMethod(maxLocals, maxStack);
        if (!this.targetIsStatic) {
            method.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
        }
        method.instructions.add((AbstractInsnNode)new VarInsnNode(this.targetType.getOpcode(21), stackSpace));
        int opcode = this.targetIsStatic ? 179 : 181;
        method.instructions.add((AbstractInsnNode)new FieldInsnNode(opcode, this.info.getClassNode().name, this.targetField.name, this.targetField.desc));
        method.instructions.add((AbstractInsnNode)new InsnNode(177));
        return method;
    }
}

