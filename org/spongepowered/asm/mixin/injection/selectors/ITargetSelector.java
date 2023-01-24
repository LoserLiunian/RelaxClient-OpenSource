/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 */
package org.spongepowered.asm.mixin.injection.selectors;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.spongepowered.asm.mixin.injection.selectors.InvalidSelectorException;
import org.spongepowered.asm.mixin.injection.selectors.MatchResult;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.util.asm.ElementNode;

public interface ITargetSelector {
    public ITargetSelector next();

    public ITargetSelector configure(String ... var1);

    public ITargetSelector validate() throws InvalidSelectorException;

    public ITargetSelector attach(IMixinContext var1) throws InvalidSelectorException;

    public int getMatchCount();

    public <TNode> MatchResult match(ElementNode<TNode> var1);

    public MatchResult match(AbstractInsnNode var1);
}

