/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.MethodNode
 */
package org.spongepowered.asm.util.asm;

import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class ElementNode<TNode> {
    private final ClassNode owner;

    protected ElementNode(ClassNode owner) {
        this.owner = owner;
    }

    public boolean isMethod() {
        return false;
    }

    public boolean isField() {
        return false;
    }

    public MethodNode getMethod() {
        return null;
    }

    public FieldNode getField() {
        return null;
    }

    public ClassNode getOwner() {
        return this.owner;
    }

    public String getOwnerName() {
        return this.owner != null ? this.owner.name : null;
    }

    public abstract String getName();

    public abstract String getDesc();

    public abstract String getSignature();

    public abstract TNode get();

    public static ElementNode<MethodNode> of(ClassNode owner, MethodNode method) {
        return new ElementNodeMethod(owner, method);
    }

    public static ElementNode<FieldNode> of(ClassNode owner, FieldNode field) {
        return new ElementNodeField(owner, field);
    }

    public static <TNode> ElementNode<TNode> of(ClassNode owner, TNode node) {
        if (node instanceof MethodNode) {
            return new ElementNodeMethod(owner, (MethodNode)node);
        }
        if (node instanceof FieldNode) {
            return new ElementNodeField(owner, (FieldNode)node);
        }
        throw new IllegalArgumentException("Could not create ElementNode for unknown node type: " + node.getClass().getName());
    }

    public static <TNode> List<ElementNode<TNode>> listOf(ClassNode owner, List<TNode> list) {
        ArrayList<ElementNode<TNode>> nodes = new ArrayList<ElementNode<TNode>>();
        for (TNode node : list) {
            nodes.add(ElementNode.of(owner, node));
        }
        return nodes;
    }

    public static List<ElementNode<FieldNode>> fieldList(ClassNode owner) {
        ArrayList<ElementNode<FieldNode>> fields = new ArrayList<ElementNode<FieldNode>>();
        for (FieldNode field : owner.fields) {
            fields.add(new ElementNodeField(owner, field));
        }
        return fields;
    }

    public static List<ElementNode<MethodNode>> methodList(ClassNode owner) {
        ArrayList<ElementNode<MethodNode>> methods = new ArrayList<ElementNode<MethodNode>>();
        for (MethodNode method : owner.methods) {
            methods.add(new ElementNodeMethod(owner, method));
        }
        return methods;
    }

    static class ElementNodeField
    extends ElementNode<FieldNode> {
        private FieldNode field;

        ElementNodeField(ClassNode owner, FieldNode field) {
            super(owner);
            this.field = field;
        }

        @Override
        public boolean isField() {
            return true;
        }

        @Override
        public FieldNode getField() {
            return this.field;
        }

        @Override
        public String getName() {
            return this.field.name;
        }

        @Override
        public String getDesc() {
            return this.field.desc;
        }

        @Override
        public String getSignature() {
            return this.field.signature;
        }

        @Override
        public FieldNode get() {
            return this.field;
        }

        public String toString() {
            return String.format("FieldElement[%s:%s]", this.field.name, this.field.desc);
        }
    }

    static class ElementNodeMethod
    extends ElementNode<MethodNode> {
        private MethodNode method;

        ElementNodeMethod(ClassNode owner, MethodNode method) {
            super(owner);
            this.method = method;
        }

        @Override
        public boolean isMethod() {
            return true;
        }

        @Override
        public MethodNode getMethod() {
            return this.method;
        }

        @Override
        public String getName() {
            return this.method.name;
        }

        @Override
        public String getDesc() {
            return this.method.desc;
        }

        @Override
        public String getSignature() {
            return this.method.signature;
        }

        @Override
        public MethodNode get() {
            return this.method;
        }

        public String toString() {
            return String.format("MethodElement[%s%s]", this.method.name, this.method.desc);
        }
    }
}

