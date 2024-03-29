/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 */
package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelector;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorByName;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

@InjectionPoint.AtCode(value="INVOKE")
public class BeforeInvoke
extends InjectionPoint {
    protected final ITargetSelector target;
    protected final boolean allowPermissive;
    protected final int ordinal;
    protected final String className;
    protected final IMixinContext context;
    protected final Logger logger = LogManager.getLogger((String)"mixin");
    private boolean log = false;

    public BeforeInvoke(InjectionPointData data) {
        super(data);
        this.target = data.getTarget();
        this.ordinal = data.getOrdinal();
        this.log = data.get("log", false);
        this.className = this.getClassName();
        this.context = data.getContext();
        this.allowPermissive = this.context.getOption(MixinEnvironment.Option.REFMAP_REMAP) && this.context.getOption(MixinEnvironment.Option.REFMAP_REMAP_ALLOW_PERMISSIVE) && !this.context.getReferenceMapper().isDefault();
    }

    private String getClassName() {
        InjectionPoint.AtCode atCode = this.getClass().getAnnotation(InjectionPoint.AtCode.class);
        return String.format("@At(%s)", atCode != null ? atCode.value() : this.getClass().getSimpleName().toUpperCase(Locale.ROOT));
    }

    public BeforeInvoke setLogging(boolean logging) {
        this.log = logging;
        return this;
    }

    @Override
    public boolean find(String desc, InsnList insns, Collection<AbstractInsnNode> nodes) {
        this.log("{} is searching for an injection point in method with descriptor {}", this.className, desc);
        boolean hasDescriptor = this.target instanceof ITargetSelectorByName && ((ITargetSelectorByName)this.target).getDesc() == null;
        boolean found = this.find(desc, insns, nodes, this.target, SearchType.STRICT);
        if (!found && hasDescriptor && this.allowPermissive) {
            this.logger.warn("STRICT match for {} using \"{}\" in {} returned 0 results, attempting permissive search. To inhibit permissive search set mixin.env.allowPermissiveMatch=false", new Object[]{this.className, this.target, this.context});
            found = this.find(desc, insns, nodes, this.target, SearchType.PERMISSIVE);
        }
        return found;
    }

    protected boolean find(String desc, InsnList insns, Collection<AbstractInsnNode> nodes, ITargetSelector selector, SearchType searchType) {
        if (selector == null) {
            return false;
        }
        ITargetSelector target = searchType == SearchType.PERMISSIVE ? selector.configure("permissive") : selector;
        int ordinal = 0;
        int found = 0;
        for (AbstractInsnNode insn : insns) {
            if (this.matchesInsn(insn)) {
                MemberInfo nodeInfo = new MemberInfo(insn);
                this.log("{} is considering insn {}", this.className, nodeInfo);
                if (target.match(insn).isExactMatch()) {
                    this.log("{} > found a matching insn, checking preconditions...", this.className);
                    if (this.matchesOrdinal(ordinal)) {
                        this.log("{} > > > found a matching insn at ordinal {}", this.className, ordinal);
                        if (this.addInsn(insns, nodes, insn)) {
                            ++found;
                        }
                        if (this.ordinal == ordinal) break;
                    }
                    ++ordinal;
                }
            }
            this.inspectInsn(desc, insns, insn);
        }
        if (searchType == SearchType.PERMISSIVE && found > 1) {
            this.logger.warn("A permissive match for {} using \"{}\" in {} matched {} instructions, this may cause unexpected behaviour. To inhibit permissive search set mixin.env.allowPermissiveMatch=false", new Object[]{this.className, selector, this.context, found});
        }
        return found > 0;
    }

    protected boolean addInsn(InsnList insns, Collection<AbstractInsnNode> nodes, AbstractInsnNode insn) {
        nodes.add(insn);
        return true;
    }

    protected boolean matchesInsn(AbstractInsnNode insn) {
        return insn instanceof MethodInsnNode;
    }

    protected void inspectInsn(String desc, InsnList insns, AbstractInsnNode insn) {
    }

    protected boolean matchesOrdinal(int ordinal) {
        this.log("{} > > comparing target ordinal {} with current ordinal {}", this.className, this.ordinal, ordinal);
        return this.ordinal == -1 || this.ordinal == ordinal;
    }

    protected void log(String message, Object ... params) {
        if (this.log) {
            this.logger.info(message, params);
        }
    }

    public static enum SearchType {
        STRICT,
        PERMISSIVE;

    }
}

