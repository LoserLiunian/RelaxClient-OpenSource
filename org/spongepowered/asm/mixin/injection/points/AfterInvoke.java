/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.points.BeforeInvoke;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@InjectionPoint.AtCode(value="INVOKE_ASSIGN")
public class AfterInvoke
extends BeforeInvoke {
    public AfterInvoke(InjectionPointData data) {
        super(data);
    }

    @Override
    protected boolean addInsn(InsnList insns, Collection<AbstractInsnNode> nodes, AbstractInsnNode insn) {
        MethodInsnNode methodNode = (MethodInsnNode)insn;
        if (Type.getReturnType((String)methodNode.desc) == Type.VOID_TYPE) {
            return false;
        }
        if ((insn = InjectionPoint.nextNode(insns, insn)) instanceof VarInsnNode && insn.getOpcode() >= 54) {
            insn = InjectionPoint.nextNode(insns, insn);
        }
        nodes.add(insn);
        return true;
    }
}

