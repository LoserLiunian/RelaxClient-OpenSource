/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Strings
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 */
package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelector;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorByName;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorConstructor;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorRemappable;
import org.spongepowered.asm.mixin.injection.selectors.InvalidSelectorException;
import org.spongepowered.asm.mixin.injection.selectors.MatchResult;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.mixin.injection.struct.TargetNotSupportedException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.SignaturePrinter;
import org.spongepowered.asm.util.asm.ElementNode;

public final class MemberInfo
implements ITargetSelectorRemappable,
ITargetSelectorConstructor {
    private final String owner;
    private final String name;
    private final String desc;
    private final boolean matchAll;
    private final boolean forceField;
    private final String unparsed;

    public MemberInfo(String name, boolean matchAll) {
        this(name, null, null, matchAll);
    }

    public MemberInfo(String name, String owner, boolean matchAll) {
        this(name, owner, null, matchAll);
    }

    public MemberInfo(String name, String owner, String desc) {
        this(name, owner, desc, false);
    }

    public MemberInfo(String name, String owner, String desc, boolean matchAll) {
        this(name, owner, desc, matchAll, null);
    }

    public MemberInfo(String name, String owner, String desc, boolean matchAll, String unparsed) {
        if (owner != null && owner.contains(".")) {
            throw new IllegalArgumentException("Attempt to instance a MemberInfo with an invalid owner format");
        }
        this.owner = owner;
        this.name = name;
        this.desc = desc;
        this.matchAll = matchAll;
        this.forceField = false;
        this.unparsed = unparsed;
    }

    public MemberInfo(AbstractInsnNode insn) {
        this.matchAll = false;
        this.forceField = false;
        this.unparsed = null;
        if (insn instanceof MethodInsnNode) {
            MethodInsnNode methodNode = (MethodInsnNode)insn;
            this.owner = methodNode.owner;
            this.name = methodNode.name;
            this.desc = methodNode.desc;
        } else if (insn instanceof FieldInsnNode) {
            FieldInsnNode fieldNode = (FieldInsnNode)insn;
            this.owner = fieldNode.owner;
            this.name = fieldNode.name;
            this.desc = fieldNode.desc;
        } else {
            throw new IllegalArgumentException("insn must be an instance of MethodInsnNode or FieldInsnNode");
        }
    }

    public MemberInfo(IMapping<?> mapping) {
        this.owner = mapping.getOwner();
        this.name = mapping.getSimpleName();
        this.desc = mapping.getDesc();
        this.matchAll = false;
        this.forceField = mapping.getType() == IMapping.Type.FIELD;
        this.unparsed = null;
    }

    private MemberInfo(MemberInfo remapped, MappingMethod method, boolean setOwner) {
        this.owner = setOwner ? method.getOwner() : remapped.owner;
        this.name = method.getSimpleName();
        this.desc = method.getDesc();
        this.matchAll = remapped.matchAll;
        this.forceField = false;
        this.unparsed = null;
    }

    private MemberInfo(MemberInfo original, String owner) {
        this.owner = owner;
        this.name = original.name;
        this.desc = original.desc;
        this.matchAll = original.matchAll;
        this.forceField = original.forceField;
        this.unparsed = null;
    }

    @Override
    public ITargetSelector next() {
        return null;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public int getMatchCount() {
        return this.matchAll ? Integer.MAX_VALUE : 1;
    }

    public String toString() {
        String desc;
        String owner = this.owner != null ? "L" + this.owner + ";" : "";
        String name = this.name != null ? this.name : "";
        String qualifier = this.matchAll ? "*" : "";
        String string = desc = this.desc != null ? this.desc : "";
        String separator = desc.startsWith("(") ? "" : (this.desc != null ? ":" : "");
        return owner + name + qualifier + separator + desc;
    }

    @Deprecated
    public String toSrg() {
        if (!this.isFullyQualified()) {
            throw new MixinException("Cannot convert unqualified reference to SRG mapping");
        }
        if (this.desc.startsWith("(")) {
            return this.owner + "/" + this.name + " " + this.desc;
        }
        return this.owner + "/" + this.name;
    }

    @Override
    public String toDescriptor() {
        if (this.desc == null) {
            return "";
        }
        return new SignaturePrinter(this).setFullyQualified(true).toDescriptor();
    }

    @Override
    public String toCtorType() {
        if (this.unparsed == null) {
            return null;
        }
        String returnType = this.getReturnType();
        if (returnType != null) {
            return returnType;
        }
        if (this.owner != null) {
            return this.owner;
        }
        if (this.name != null && this.desc == null) {
            return this.name;
        }
        return this.desc != null ? this.desc : this.unparsed;
    }

    @Override
    public String toCtorDesc() {
        return Bytecode.changeDescriptorReturnType(this.desc, "V");
    }

    private String getReturnType() {
        if (this.desc == null || this.desc.indexOf(41) == -1 || this.desc.indexOf(40) != 0) {
            return null;
        }
        String returnType = this.desc.substring(this.desc.indexOf(41) + 1);
        if (returnType.startsWith("L") && returnType.endsWith(";")) {
            return returnType.substring(1, returnType.length() - 1);
        }
        return returnType;
    }

    @Override
    public IMapping<?> asMapping() {
        return this.isField() ? this.asFieldMapping() : this.asMethodMapping();
    }

    @Override
    public MappingMethod asMethodMapping() {
        if (!this.isFullyQualified()) {
            throw new MixinException("Cannot convert unqualified reference " + this + " to MethodMapping");
        }
        if (this.isField()) {
            throw new MixinException("Cannot convert a non-method reference " + this + " to MethodMapping");
        }
        return new MappingMethod(this.owner, this.name, this.desc);
    }

    @Override
    public MappingField asFieldMapping() {
        if (!this.isField()) {
            throw new MixinException("Cannot convert non-field reference " + this + " to FieldMapping");
        }
        return new MappingField(this.owner, this.name, this.desc);
    }

    @Override
    public boolean isFullyQualified() {
        return this.owner != null && this.name != null && this.desc != null;
    }

    @Override
    public boolean isField() {
        return this.forceField || this.desc != null && !this.desc.startsWith("(");
    }

    @Override
    public boolean isConstructor() {
        return "<init>".equals(this.name);
    }

    @Override
    public boolean isClassInitialiser() {
        return "<clinit>".equals(this.name);
    }

    @Override
    public boolean isInitialiser() {
        return this.isConstructor() || this.isClassInitialiser();
    }

    @Override
    public MemberInfo validate() throws InvalidMemberDescriptorException {
        if (this.owner != null) {
            if (!this.owner.matches("(?i)^[\\w\\p{Sc}/]+$")) {
                throw new InvalidMemberDescriptorException("Invalid owner: " + this.owner);
            }
            if (this.unparsed != null && this.unparsed.lastIndexOf(46) > 0 && this.owner.startsWith("L")) {
                throw new InvalidMemberDescriptorException("Malformed owner: " + this.owner + " If you are seeing this message unexpectedly and the owner appears to be correct, replace the owner descriptor with formal type L" + this.owner + "; to suppress this error");
            }
        }
        if (this.name != null && !this.name.matches("(?i)^<?[\\w\\p{Sc}]+>?$")) {
            throw new InvalidMemberDescriptorException("Invalid name: " + this.name);
        }
        if (this.desc != null) {
            if (!this.desc.matches("^(\\([\\w\\p{Sc}\\[/;]*\\))?\\[*[\\w\\p{Sc}/;]+$")) {
                throw new InvalidMemberDescriptorException("Invalid descriptor: " + this.desc);
            }
            if (this.isField()) {
                if (!this.desc.equals(Type.getType((String)this.desc).getDescriptor())) {
                    throw new InvalidMemberDescriptorException("Invalid field type in descriptor: " + this.desc);
                }
            } else {
                try {
                    Type.getArgumentTypes((String)this.desc);
                }
                catch (Exception ex) {
                    throw new InvalidMemberDescriptorException("Invalid descriptor: " + this.desc);
                }
                String retString = this.desc.substring(this.desc.indexOf(41) + 1);
                try {
                    Type retType = Type.getType((String)retString);
                    if (!retString.equals(retType.getDescriptor())) {
                        throw new InvalidMemberDescriptorException("Invalid return type \"" + retString + "\" in descriptor: " + this.desc);
                    }
                }
                catch (Exception ex) {
                    throw new InvalidMemberDescriptorException("Invalid return type \"" + retString + "\" in descriptor: " + this.desc);
                }
            }
        }
        return this;
    }

    @Override
    public <TNode> MatchResult match(ElementNode<TNode> node) {
        return node == null ? MatchResult.NONE : this.matches(node.getOwnerName(), node.getName(), node.getDesc());
    }

    @Override
    public MatchResult match(AbstractInsnNode insn) {
        if (insn instanceof MethodInsnNode) {
            MethodInsnNode method = (MethodInsnNode)insn;
            return this.matches(method.owner, method.name, method.desc);
        }
        if (insn instanceof FieldInsnNode) {
            FieldInsnNode field = (FieldInsnNode)insn;
            return this.matches(field.owner, field.name, field.desc);
        }
        return MatchResult.NONE;
    }

    @Override
    public MatchResult matches(String owner, String name, String desc) {
        if (this.desc != null && desc != null && !this.desc.equals(desc)) {
            return MatchResult.NONE;
        }
        if (this.owner != null && owner != null && !this.owner.equals(owner)) {
            return MatchResult.NONE;
        }
        if (this.name != null && name != null) {
            if (this.name.equals(name)) {
                return MatchResult.EXACT_MATCH;
            }
            if (this.name.equalsIgnoreCase(name)) {
                return MatchResult.MATCH;
            }
            return MatchResult.NONE;
        }
        return MatchResult.EXACT_MATCH;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ITargetSelectorByName)) {
            return false;
        }
        ITargetSelectorByName other = (ITargetSelectorByName)obj;
        boolean otherForceField = other instanceof MemberInfo ? ((MemberInfo)other).forceField : other.isField();
        boolean otherMatchAll = other.getMatchCount() == Integer.MAX_VALUE;
        return this.matchAll == otherMatchAll && this.forceField == otherForceField && Objects.equal((Object)this.owner, (Object)other.getOwner()) && Objects.equal((Object)this.name, (Object)other.getName()) && Objects.equal((Object)this.desc, (Object)other.getDesc());
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.matchAll, this.owner, this.name, this.desc});
    }

    @Override
    public ITargetSelector configure(String ... args2) {
        ITargetSelectorRemappable configured = this;
        for (String arg : args2) {
            if (arg == null) continue;
            if (arg.startsWith("move:")) {
                configured = configured.move(Strings.emptyToNull((String)arg.substring(5)));
                continue;
            }
            if (arg.startsWith("transform:")) {
                configured = configured.transform(Strings.emptyToNull((String)arg.substring(10)));
                continue;
            }
            if ("permissive".equals(arg)) {
                configured = configured.transform(null);
                continue;
            }
            if (!"orphan".equals(arg)) continue;
            configured = configured.move(null);
        }
        return configured;
    }

    @Override
    public ITargetSelector attach(IMixinContext context) throws InvalidSelectorException {
        if (this.owner != null && !this.owner.equals(context.getTargetClassRef())) {
            throw new TargetNotSupportedException(this.owner);
        }
        return this;
    }

    @Override
    public ITargetSelectorRemappable move(String newOwner) {
        if (newOwner == null && this.owner == null || newOwner != null && newOwner.equals(this.owner)) {
            return this;
        }
        return new MemberInfo(this, newOwner);
    }

    @Override
    public ITargetSelectorRemappable transform(String newDesc) {
        if (newDesc == null && this.desc == null || newDesc != null && newDesc.equals(this.desc)) {
            return this;
        }
        return new MemberInfo(this.name, this.owner, newDesc, this.matchAll);
    }

    @Override
    public ITargetSelectorRemappable remapUsing(MappingMethod srgMethod, boolean setOwner) {
        return new MemberInfo(this, srgMethod, setOwner);
    }

    public static MemberInfo parse(String input, IReferenceMapper refMapper, String className) {
        boolean matchAll;
        String desc = null;
        String owner = null;
        String name = Strings.nullToEmpty((String)input).replaceAll("\\s", "");
        if (refMapper != null) {
            name = refMapper.remap(className, name);
        }
        int lastDotPos = name.lastIndexOf(46);
        int semiColonPos = name.indexOf(59);
        if (lastDotPos > -1) {
            owner = name.substring(0, lastDotPos).replace('.', '/');
            name = name.substring(lastDotPos + 1);
        } else if (semiColonPos > -1 && name.startsWith("L")) {
            owner = name.substring(1, semiColonPos).replace('.', '/');
            name = name.substring(semiColonPos + 1);
        }
        int parenPos = name.indexOf(40);
        int colonPos = name.indexOf(58);
        if (parenPos > -1) {
            desc = name.substring(parenPos);
            name = name.substring(0, parenPos);
        } else if (colonPos > -1) {
            desc = name.substring(colonPos + 1);
            name = name.substring(0, colonPos);
        }
        if ((name.indexOf(47) > -1 || name.indexOf(46) > -1) && owner == null) {
            owner = name;
            name = "";
        }
        if (matchAll = name.endsWith("*")) {
            name = name.substring(0, name.length() - 1);
        }
        if (name.isEmpty()) {
            name = null;
        }
        return new MemberInfo(name, owner, desc, matchAll, input);
    }

    public static MemberInfo fromMapping(IMapping<?> mapping) {
        return new MemberInfo(mapping);
    }
}

