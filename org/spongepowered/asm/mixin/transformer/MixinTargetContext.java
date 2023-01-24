/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.Handle
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InvokeDynamicInsnNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.LocalVariableNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package org.spongepowered.asm.mixin.transformer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.struct.MemberRef;
import org.spongepowered.asm.mixin.struct.SourceMap;
import org.spongepowered.asm.mixin.throwables.ClassMetadataNotFoundException;
import org.spongepowered.asm.mixin.transformer.ActivityStack;
import org.spongepowered.asm.mixin.transformer.ClassContext;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.InnerClassGenerator;
import org.spongepowered.asm.mixin.transformer.MixinInfo;
import org.spongepowered.asm.mixin.transformer.TargetClassContext;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.obfuscation.RemapperChain;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.ClassSignature;

public class MixinTargetContext
extends ClassContext
implements IMixinContext {
    private static final Logger logger = LogManager.getLogger((String)"mixin");
    protected final ActivityStack activities = new ActivityStack(null);
    private final MixinInfo mixin;
    private final ClassNode classNode;
    private final TargetClassContext targetClass;
    private final String sessionId;
    private final ClassInfo targetClassInfo;
    private final BiMap<String, String> innerClasses = HashBiMap.create();
    private final List<MethodNode> shadowMethods = new ArrayList<MethodNode>();
    private final Map<FieldNode, ClassInfo.Field> shadowFields = new LinkedHashMap<FieldNode, ClassInfo.Field>();
    private final List<MethodNode> mergedMethods = new ArrayList<MethodNode>();
    private final InjectorGroupInfo.Map injectorGroups = new InjectorGroupInfo.Map();
    private final List<InjectionInfo> injectors = new ArrayList<InjectionInfo>();
    private final List<AccessorInfo> accessors = new ArrayList<AccessorInfo>();
    private final boolean inheritsFromMixin;
    private final boolean detachedSuper;
    private final SourceMap.File stratum;
    private int minRequiredClassVersion = MixinEnvironment.CompatibilityLevel.JAVA_6.classVersion();

    MixinTargetContext(MixinInfo mixin, ClassNode classNode, TargetClassContext context) {
        this.mixin = mixin;
        this.classNode = classNode;
        this.targetClass = context;
        this.targetClassInfo = context.getClassInfo();
        this.stratum = context.getSourceMap().addFile(this.classNode);
        this.inheritsFromMixin = mixin.getClassInfo().hasMixinInHierarchy() || this.targetClassInfo.hasMixinTargetInHierarchy();
        this.detachedSuper = !this.classNode.superName.equals(this.getTarget().getClassNode().superName);
        this.sessionId = context.getSessionId();
        this.requireVersion(classNode.version);
        InnerClassGenerator icg = (InnerClassGenerator)context.getExtensions().getGenerator(InnerClassGenerator.class);
        for (String innerClass : this.mixin.getInnerClasses()) {
            this.innerClasses.put((Object)innerClass, (Object)icg.registerInnerClass(this.mixin, innerClass, this));
        }
    }

    void addShadowMethod(MethodNode method) {
        this.shadowMethods.add(method);
    }

    void addShadowField(FieldNode fieldNode, ClassInfo.Field fieldInfo) {
        this.shadowFields.put(fieldNode, fieldInfo);
    }

    void addAccessorMethod(MethodNode method, Class<? extends Annotation> type) {
        this.accessors.add(AccessorInfo.of(this, method, type));
    }

    void addMixinMethod(MethodNode method) {
        Annotations.setVisible(method, MixinMerged.class, "mixin", this.getClassName());
        this.getTarget().addMixinMethod(method);
    }

    void methodMerged(MethodNode method) {
        this.mergedMethods.add(method);
        this.targetClassInfo.addMethod(method);
        this.getTarget().methodMerged(method);
        Annotations.setVisible(method, MixinMerged.class, "mixin", this.getClassName(), "priority", this.getPriority(), "sessionId", this.sessionId);
    }

    public String toString() {
        return this.mixin.toString();
    }

    public MixinEnvironment getEnvironment() {
        return this.mixin.getParent().getEnvironment();
    }

    @Override
    public boolean getOption(MixinEnvironment.Option option) {
        return this.getEnvironment().getOption(option);
    }

    @Override
    public ClassNode getClassNode() {
        return this.classNode;
    }

    @Override
    public String getClassName() {
        return this.mixin.getClassName();
    }

    @Override
    public String getClassRef() {
        return this.mixin.getClassRef();
    }

    public TargetClassContext getTarget() {
        return this.targetClass;
    }

    @Override
    public String getTargetClassRef() {
        return this.getTarget().getClassRef();
    }

    public ClassNode getTargetClassNode() {
        return this.getTarget().getClassNode();
    }

    public ClassInfo getTargetClassInfo() {
        return this.targetClassInfo;
    }

    @Override
    public ClassInfo getClassInfo() {
        return this.mixin.getClassInfo();
    }

    public ClassSignature getSignature() {
        return this.getClassInfo().getSignature();
    }

    public SourceMap.File getStratum() {
        return this.stratum;
    }

    public int getMinRequiredClassVersion() {
        return this.minRequiredClassVersion;
    }

    public int getDefaultRequiredInjections() {
        return this.mixin.getParent().getDefaultRequiredInjections();
    }

    public String getDefaultInjectorGroup() {
        return this.mixin.getParent().getDefaultInjectorGroup();
    }

    public int getMaxShiftByValue() {
        return this.mixin.getParent().getMaxShiftByValue();
    }

    public InjectorGroupInfo.Map getInjectorGroups() {
        return this.injectorGroups;
    }

    public boolean requireOverwriteAnnotations() {
        return this.mixin.getParent().requireOverwriteAnnotations();
    }

    void transformMethod(MethodNode method) {
        this.activities.clear();
        try {
            ActivityStack.Activity activity = this.activities.begin("Validate");
            this.validateMethod(method);
            activity.next("Transform Descriptor");
            this.transformDescriptor(method);
            activity.next("Transform LVT");
            this.transformLVT(method);
            activity.next("Transform Line Numbers");
            this.stratum.applyOffset(method);
            activity.next("Transform Instructions");
            AbstractInsnNode lastInsn = null;
            ListIterator iter = method.instructions.iterator();
            while (iter.hasNext()) {
                AbstractInsnNode insn = (AbstractInsnNode)iter.next();
                ActivityStack.Activity insnActivity = this.activities.begin(Bytecode.getOpcodeName(insn) + " ");
                if (insn instanceof MethodInsnNode) {
                    MethodInsnNode methodNode = (MethodInsnNode)insn;
                    insnActivity.append("%s::%s%s", methodNode.owner, methodNode.name, methodNode.desc);
                    this.transformMethodRef(method, iter, new MemberRef.Method(methodNode));
                } else if (insn instanceof FieldInsnNode) {
                    FieldInsnNode fieldNode = (FieldInsnNode)insn;
                    insnActivity.append("%s::%s:%s", fieldNode.owner, fieldNode.name, fieldNode.desc);
                    this.transformFieldRef(method, iter, new MemberRef.Field(fieldNode));
                    this.checkFinal(method, iter, fieldNode);
                } else if (insn instanceof TypeInsnNode) {
                    TypeInsnNode typeNode = (TypeInsnNode)insn;
                    insnActivity.append(typeNode.desc);
                    this.transformTypeNode(method, iter, typeNode, lastInsn);
                } else if (insn instanceof LdcInsnNode) {
                    this.transformConstantNode(method, iter, (LdcInsnNode)insn);
                } else if (insn instanceof InvokeDynamicInsnNode) {
                    InvokeDynamicInsnNode invokeNode = (InvokeDynamicInsnNode)insn;
                    insnActivity.append("%s %s", invokeNode.name, invokeNode.desc);
                    this.transformInvokeDynamicNode(method, iter, invokeNode);
                }
                lastInsn = insn;
                insnActivity.end();
            }
            activity.end();
        }
        catch (InvalidMixinException ex) {
            ex.prepend(this.activities);
            throw ex;
        }
        catch (Exception ex) {
            throw new InvalidMixinException(this, "Unexpecteded " + ex.getClass().getSimpleName() + " whilst transforming the mixin class:", (Throwable)ex, this.activities);
        }
    }

    private void validateMethod(MethodNode method) {
        ClassInfo.Method superMethod;
        if (Annotations.getInvisible(method, SoftOverride.class) != null) {
            if (Bytecode.getVisibility(method) == Bytecode.Visibility.PRIVATE) {
                throw new InvalidMixinException((IMixinContext)this, "Mixin method " + method.name + method.desc + " is tagged with @SoftOverride but the method is PRIVATE");
            }
            superMethod = this.targetClassInfo.findMethodInHierarchy(method.name, method.desc, ClassInfo.SearchType.SUPER_CLASSES_ONLY, ClassInfo.Traversal.SUPER);
            if (superMethod == null || !superMethod.isInjected()) {
                throw new InvalidMixinException((IMixinContext)this, "Mixin method " + method.name + method.desc + " is tagged with @SoftOverride but no valid method was found in superclasses of " + this.getTarget().getClassName());
            }
        }
        if (Bytecode.isVirtual(method) && (superMethod = this.targetClassInfo.findMethodInHierarchy(method, ClassInfo.SearchType.SUPER_CLASSES_ONLY, ClassInfo.Traversal.ALL, 0)) != null && superMethod.isFinal()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s%s in %s overrides a final method from %s", method.name, method.desc, this.mixin, superMethod.getOwner().getClassName()));
        }
    }

    private void transformLVT(MethodNode method) {
        if (method.localVariables == null) {
            return;
        }
        ActivityStack.Activity localVarActivity = this.activities.begin("?");
        for (LocalVariableNode local : method.localVariables) {
            if (local == null || local.desc == null) continue;
            localVarActivity.next("var=%s", local.name);
            local.desc = this.transformSingleDescriptor(Type.getType((String)local.desc));
        }
        localVarActivity.end();
    }

    private void transformMethodRef(MethodNode method, Iterator<AbstractInsnNode> iter, MemberRef methodRef) {
        this.transformDescriptor(methodRef);
        if (methodRef.getOwner().equals(this.getClassRef())) {
            methodRef.setOwner(this.getTarget().getClassRef());
            ClassInfo.Method md = this.getClassInfo().findMethod(methodRef.getName(), methodRef.getDesc(), 10);
            if (md != null && md.isRenamed() && md.getOriginalName().equals(methodRef.getName()) && (md.isSynthetic() || md.isConformed())) {
                methodRef.setName(md.getName());
            }
            this.upgradeMethodRef(method, methodRef, md);
        } else if (this.innerClasses.containsKey((Object)methodRef.getOwner())) {
            methodRef.setOwner((String)this.innerClasses.get((Object)methodRef.getOwner()));
            methodRef.setDesc(this.transformMethodDescriptor(methodRef.getDesc()));
        } else if (this.detachedSuper || this.inheritsFromMixin) {
            if (methodRef.getOpcode() == 183) {
                this.updateStaticBinding(method, methodRef);
            } else if (methodRef.getOpcode() == 182 && ClassInfo.forName(methodRef.getOwner()).isMixin()) {
                this.updateDynamicBinding(method, methodRef);
            }
        }
    }

    private void transformFieldRef(MethodNode method, Iterator<AbstractInsnNode> iter, MemberRef fieldRef) {
        if ("super$".equals(fieldRef.getName())) {
            if (fieldRef instanceof MemberRef.Field) {
                this.processImaginarySuper(method, ((MemberRef.Field)fieldRef).insn);
                iter.remove();
            } else {
                throw new InvalidMixinException((IMixinInfo)this.mixin, "Cannot call imaginary super from method handle.");
            }
        }
        this.transformDescriptor(fieldRef);
        if (fieldRef.getOwner().equals(this.getClassRef())) {
            fieldRef.setOwner(this.getTarget().getClassRef());
            ClassInfo.Field field = this.getClassInfo().findField(fieldRef.getName(), fieldRef.getDesc(), 10);
            if (field != null && field.isRenamed() && field.getOriginalName().equals(fieldRef.getName()) && field.isStatic()) {
                fieldRef.setName(field.getName());
            }
        } else {
            ClassInfo fieldOwner = ClassInfo.forName(fieldRef.getOwner());
            if (fieldOwner.isMixin()) {
                ClassInfo actualOwner = this.targetClassInfo.findCorrespondingType(fieldOwner);
                fieldRef.setOwner(actualOwner != null ? actualOwner.getName() : this.getTarget().getClassRef());
            }
        }
    }

    private void checkFinal(MethodNode method, Iterator<AbstractInsnNode> iter, FieldInsnNode fieldNode) {
        if (!fieldNode.owner.equals(this.getTarget().getClassRef())) {
            return;
        }
        int opcode = fieldNode.getOpcode();
        if (opcode == 180 || opcode == 178) {
            return;
        }
        for (Map.Entry<FieldNode, ClassInfo.Field> shadow : this.shadowFields.entrySet()) {
            FieldNode shadowFieldNode = shadow.getKey();
            if (!shadowFieldNode.desc.equals(fieldNode.desc) || !shadowFieldNode.name.equals(fieldNode.name)) continue;
            ClassInfo.Field shadowField = shadow.getValue();
            if (shadowField.isDecoratedFinal()) {
                if (shadowField.isDecoratedMutable()) {
                    if (this.mixin.getParent().getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
                        logger.warn("Write access to @Mutable @Final field {} in {}::{}", new Object[]{shadowField, this.mixin, method.name});
                    }
                } else if ("<init>".equals(method.name) || "<clinit>".equals(method.name)) {
                    logger.warn("@Final field {} in {} should be final", new Object[]{shadowField, this.mixin});
                } else {
                    logger.error("Write access detected to @Final field {} in {}::{}", new Object[]{shadowField, this.mixin, method.name});
                    if (this.mixin.getParent().getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERIFY)) {
                        throw new InvalidMixinException((IMixinInfo)this.mixin, "Write access detected to @Final field " + shadowField + " in " + this.mixin + "::" + method.name);
                    }
                }
            }
            return;
        }
    }

    private void transformTypeNode(MethodNode method, Iterator<AbstractInsnNode> iter, TypeInsnNode typeInsn, AbstractInsnNode lastNode) {
        if (typeInsn.getOpcode() == 192 && typeInsn.desc.equals(this.getTarget().getClassRef()) && lastNode.getOpcode() == 25 && ((VarInsnNode)lastNode).var == 0) {
            iter.remove();
            return;
        }
        if (typeInsn.desc.equals(this.getClassRef())) {
            typeInsn.desc = this.getTarget().getClassRef();
        } else {
            String newName = (String)this.innerClasses.get((Object)typeInsn.desc);
            if (newName != null) {
                typeInsn.desc = newName;
            }
        }
        this.transformDescriptor(typeInsn);
    }

    private void transformConstantNode(MethodNode method, Iterator<AbstractInsnNode> iter, LdcInsnNode ldcInsn) {
        ldcInsn.cst = this.transformConstant(method, iter, ldcInsn.cst);
    }

    private void transformInvokeDynamicNode(MethodNode method, Iterator<AbstractInsnNode> iter, InvokeDynamicInsnNode dynInsn) {
        this.requireVersion(51);
        dynInsn.desc = this.transformMethodDescriptor(dynInsn.desc);
        dynInsn.bsm = this.transformHandle(method, iter, dynInsn.bsm);
        for (int i = 0; i < dynInsn.bsmArgs.length; ++i) {
            dynInsn.bsmArgs[i] = this.transformConstant(method, iter, dynInsn.bsmArgs[i]);
        }
    }

    private Object transformConstant(MethodNode method, Iterator<AbstractInsnNode> iter, Object constant) {
        if (constant instanceof Type) {
            Type type = (Type)constant;
            String desc = this.transformDescriptor(type);
            if (!type.toString().equals(desc)) {
                return Type.getType((String)desc);
            }
            return constant;
        }
        if (constant instanceof Handle) {
            return this.transformHandle(method, iter, (Handle)constant);
        }
        return constant;
    }

    private Handle transformHandle(MethodNode method, Iterator<AbstractInsnNode> iter, Handle handle) {
        MemberRef.Handle memberRef = new MemberRef.Handle(handle);
        if (memberRef.isField()) {
            this.transformFieldRef(method, iter, memberRef);
        } else {
            this.transformMethodRef(method, iter, memberRef);
        }
        return memberRef.getMethodHandle();
    }

    private void processImaginarySuper(MethodNode method, FieldInsnNode fieldInsn) {
        if (fieldInsn.getOpcode() != 180) {
            if ("<init>".equals(method.name)) {
                throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super declaration: field " + fieldInsn.name + " must not specify an initialiser");
            }
            throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: found " + Bytecode.getOpcodeName(fieldInsn.getOpcode()) + " opcode in " + method.name + method.desc);
        }
        if ((method.access & 2) != 0 || (method.access & 8) != 0) {
            throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: method " + method.name + method.desc + " is private or static");
        }
        if (Annotations.getInvisible(method, SoftOverride.class) == null) {
            throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: method " + method.name + method.desc + " is not decorated with @SoftOverride");
        }
        ListIterator methodIter = method.instructions.iterator(method.instructions.indexOf((AbstractInsnNode)fieldInsn));
        while (methodIter.hasNext()) {
            AbstractInsnNode insn = (AbstractInsnNode)methodIter.next();
            if (!(insn instanceof MethodInsnNode)) continue;
            MethodInsnNode methodNode = (MethodInsnNode)insn;
            if (!methodNode.owner.equals(this.getClassRef()) || !methodNode.name.equals(method.name) || !methodNode.desc.equals(method.desc)) continue;
            methodNode.setOpcode(183);
            this.updateStaticBinding(method, new MemberRef.Method(methodNode));
            return;
        }
        throw new InvalidMixinException((IMixinContext)this, "Illegal imaginary super access: could not find INVOKE for " + method.name + method.desc);
    }

    private void updateStaticBinding(MethodNode method, MemberRef methodRef) {
        this.updateBinding(method, methodRef, ClassInfo.Traversal.SUPER);
    }

    private void updateDynamicBinding(MethodNode method, MemberRef methodRef) {
        this.updateBinding(method, methodRef, ClassInfo.Traversal.ALL);
    }

    private void updateBinding(MethodNode method, MemberRef methodRef, ClassInfo.Traversal traversal) {
        if ("<init>".equals(method.name) || methodRef.getOwner().equals(this.getTarget().getClassRef()) || this.getTarget().getClassRef().startsWith("<")) {
            return;
        }
        ClassInfo.Method superMethod = this.targetClassInfo.findMethodInHierarchy(methodRef.getName(), methodRef.getDesc(), traversal.getSearchType(), traversal);
        if (superMethod != null) {
            if (superMethod.getOwner().isMixin()) {
                throw new InvalidMixinException((IMixinContext)this, "Invalid " + methodRef + " in " + this + " resolved " + superMethod.getOwner() + " but is mixin.");
            }
            methodRef.setOwner(superMethod.getImplementor().getName());
        } else if (ClassInfo.forName(methodRef.getOwner()).isMixin()) {
            throw new MixinTransformerError("Error resolving " + methodRef + " in " + this);
        }
    }

    void transformDescriptor(FieldNode field) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        field.desc = this.transformSingleDescriptor(field.desc, false);
    }

    void transformDescriptor(MethodNode method) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        method.desc = this.transformMethodDescriptor(method.desc);
    }

    void transformDescriptor(MemberRef member) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        if (member.isField()) {
            member.setDesc(this.transformSingleDescriptor(member.getDesc(), false));
        } else {
            member.setDesc(this.transformMethodDescriptor(member.getDesc()));
        }
    }

    void transformDescriptor(TypeInsnNode typeInsn) {
        if (!this.inheritsFromMixin && this.innerClasses.size() == 0) {
            return;
        }
        typeInsn.desc = this.transformSingleDescriptor(typeInsn.desc, true);
    }

    private String transformDescriptor(Type type) {
        if (type.getSort() == 11) {
            return this.transformMethodDescriptor(type.getDescriptor());
        }
        return this.transformSingleDescriptor(type);
    }

    private String transformSingleDescriptor(Type type) {
        if (type.getSort() < 9) {
            return type.toString();
        }
        return this.transformSingleDescriptor(type.toString(), false);
    }

    private String transformSingleDescriptor(String desc, boolean isObject) {
        ActivityStack.Activity descriptorActivity = this.activities.begin("desc=%s", desc);
        String type = desc;
        while (type.startsWith("[") || type.startsWith("L")) {
            if (type.startsWith("[")) {
                type = type.substring(1);
                continue;
            }
            type = type.substring(1, type.indexOf(";"));
            isObject = true;
        }
        if (!isObject) {
            descriptorActivity.end();
            return desc;
        }
        String innerClassName = (String)this.innerClasses.get((Object)type);
        if (innerClassName != null) {
            descriptorActivity.end();
            return desc.replace(type, innerClassName);
        }
        if (this.innerClasses.inverse().containsKey((Object)type)) {
            descriptorActivity.end();
            return desc;
        }
        ClassInfo typeInfo = ClassInfo.forName(type);
        if (typeInfo == null) {
            throw new ClassMetadataNotFoundException(type.replace('/', '.'));
        }
        if (!typeInfo.isMixin() || typeInfo.isLoadable()) {
            descriptorActivity.end();
            return desc;
        }
        String realDesc = desc.replace(type, this.findRealType(typeInfo).toString());
        descriptorActivity.end();
        return realDesc;
    }

    private String transformMethodDescriptor(String desc) {
        StringBuilder newDesc = new StringBuilder();
        newDesc.append('(');
        for (Type arg : Type.getArgumentTypes((String)desc)) {
            newDesc.append(this.transformSingleDescriptor(arg));
        }
        return newDesc.append(')').append(this.transformSingleDescriptor(Type.getReturnType((String)desc))).toString();
    }

    @Override
    public Target getTargetMethod(MethodNode method) {
        return this.getTarget().getTargetMethod(method);
    }

    MethodNode findMethod(MethodNode method, AnnotationNode annotation) {
        List aka;
        LinkedList<String> aliases = new LinkedList<String>();
        aliases.add(method.name);
        if (annotation != null && (aka = (List)Annotations.getValue(annotation, "aliases")) != null) {
            aliases.addAll(aka);
        }
        return this.getTarget().findMethod(aliases, method.desc);
    }

    MethodNode findRemappedMethod(MethodNode method) {
        RemapperChain remapperChain = this.getEnvironment().getRemappers();
        String remappedName = remapperChain.mapMethodName(this.getTarget().getClassRef(), method.name, method.desc);
        if (remappedName.equals(method.name)) {
            return null;
        }
        LinkedList<String> aliases = new LinkedList<String>();
        aliases.add(remappedName);
        return this.getTarget().findAliasedMethod(aliases, method.desc);
    }

    FieldNode findField(FieldNode field, AnnotationNode shadow) {
        List aka;
        LinkedList<String> aliases = new LinkedList<String>();
        aliases.add(field.name);
        if (shadow != null && (aka = (List)Annotations.getValue(shadow, "aliases")) != null) {
            aliases.addAll(aka);
        }
        return this.getTarget().findAliasedField(aliases, field.desc);
    }

    FieldNode findRemappedField(FieldNode field) {
        RemapperChain remapperChain = this.getEnvironment().getRemappers();
        String remappedName = remapperChain.mapFieldName(this.getTarget().getClassRef(), field.name, field.desc);
        if (remappedName.equals(field.name)) {
            return null;
        }
        LinkedList<String> aliases = new LinkedList<String>();
        aliases.add(remappedName);
        return this.getTarget().findAliasedField(aliases, field.desc);
    }

    protected void requireVersion(int version) {
        this.minRequiredClassVersion = Math.max(this.minRequiredClassVersion, version);
        if (version > MixinEnvironment.getCompatibilityLevel().classVersion()) {
            throw new InvalidMixinException((IMixinContext)this, "Unsupported mixin class version " + version);
        }
    }

    @Override
    public Extensions getExtensions() {
        return this.targetClass.getExtensions();
    }

    @Override
    public IMixinInfo getMixin() {
        return this.mixin;
    }

    MixinInfo getInfo() {
        return this.mixin;
    }

    boolean isRequired() {
        return this.mixin.isRequired();
    }

    @Override
    public int getPriority() {
        return this.mixin.getPriority();
    }

    Set<String> getInterfaces() {
        return this.mixin.getInterfaces();
    }

    Collection<MethodNode> getShadowMethods() {
        return this.shadowMethods;
    }

    List<MethodNode> getMethods() {
        return this.classNode.methods;
    }

    Set<Map.Entry<FieldNode, ClassInfo.Field>> getShadowFields() {
        return this.shadowFields.entrySet();
    }

    List<FieldNode> getFields() {
        return this.classNode.fields;
    }

    Level getLoggingLevel() {
        return this.mixin.getLoggingLevel();
    }

    boolean shouldSetSourceFile() {
        return this.mixin.getParent().shouldSetSourceFile();
    }

    String getSourceFile() {
        return this.classNode.sourceFile;
    }

    @Override
    public IReferenceMapper getReferenceMapper() {
        return this.mixin.getParent().getReferenceMapper();
    }

    void preApply(String transformedName, ClassNode targetClass) {
        this.mixin.preApply(transformedName, targetClass);
    }

    void postApply(String transformedName, ClassNode targetClass) {
        this.activities.clear();
        try {
            ActivityStack.Activity activity = this.activities.begin("Validating Injector Groups");
            this.injectorGroups.validateAll();
            activity.next("Plugin Post-Application");
            this.mixin.postApply(transformedName, targetClass);
            activity.end();
        }
        catch (InjectionValidationException ex) {
            InjectorGroupInfo group = ex.getGroup();
            throw new InjectionError(String.format("Critical injection failure: Callback group %s in %s failed injection check: %s", group, this.mixin, ex.getMessage()));
        }
        catch (InvalidMixinException ex) {
            ex.prepend(this.activities);
            throw ex;
        }
        catch (Exception ex) {
            throw new InvalidMixinException(this, "Unexpecteded " + ex.getClass().getSimpleName() + " whilst transforming the mixin class:", (Throwable)ex, this.activities);
        }
    }

    String getUniqueName(MethodNode method, boolean preservePrefix) {
        return this.targetClassInfo.getMethodMapper().getUniqueName(method, this.sessionId, preservePrefix);
    }

    String getUniqueName(FieldNode field) {
        return this.targetClassInfo.getMethodMapper().getUniqueName(field, this.sessionId);
    }

    void prepareInjections() {
        this.activities.clear();
        try {
            this.injectors.clear();
            ActivityStack.Activity prepareActivity = this.activities.begin("?");
            for (MethodNode method : this.mergedMethods) {
                prepareActivity.next("%s%s", method.name, method.desc);
                ActivityStack.Activity methodActivity = this.activities.begin("Parse");
                InjectionInfo injectInfo = InjectionInfo.parse(this, method);
                if (injectInfo == null) continue;
                methodActivity.next("Validate");
                if (injectInfo.isValid()) {
                    methodActivity.next("Prepare");
                    injectInfo.prepare();
                    this.injectors.add(injectInfo);
                }
                methodActivity.next("Undecorate");
                method.visibleAnnotations.remove(injectInfo.getAnnotation());
                methodActivity.end();
            }
            prepareActivity.end();
        }
        catch (InvalidMixinException ex) {
            ex.prepend(this.activities);
            throw ex;
        }
        catch (Exception ex) {
            throw new InvalidMixinException(this, "Unexpecteded " + ex.getClass().getSimpleName() + " whilst transforming the mixin class:", (Throwable)ex, this.activities);
        }
    }

    void applyInjections() {
        this.activities.clear();
        try {
            ActivityStack.Activity applyActivity = this.activities.begin("Inject");
            ActivityStack.Activity injectActivity = this.activities.begin("?");
            for (InjectionInfo injectInfo : this.injectors) {
                injectActivity.next(injectInfo.toString());
                injectInfo.inject();
            }
            applyActivity.next("PostInject");
            ActivityStack.Activity postInjectActivity = this.activities.begin("?");
            for (InjectionInfo injectInfo : this.injectors) {
                postInjectActivity.next(injectInfo.toString());
                injectInfo.postInject();
            }
            applyActivity.end();
            this.injectors.clear();
        }
        catch (InvalidMixinException ex) {
            ex.prepend(this.activities);
            throw ex;
        }
        catch (Exception ex) {
            throw new InvalidMixinException(this, "Unexpecteded " + ex.getClass().getSimpleName() + " whilst transforming the mixin class:", (Throwable)ex, this.activities);
        }
    }

    List<MethodNode> generateAccessors() {
        this.activities.clear();
        ArrayList<MethodNode> methods = new ArrayList<MethodNode>();
        try {
            ActivityStack.Activity accessorActivity = this.activities.begin("Locate");
            ActivityStack.Activity locateActivity = this.activities.begin("?");
            for (AccessorInfo accessorInfo : this.accessors) {
                locateActivity.next(accessorInfo.toString());
                accessorInfo.locate();
            }
            accessorActivity.next("Validate");
            ActivityStack.Activity validateActivity = this.activities.begin("?");
            for (AccessorInfo accessor : this.accessors) {
                validateActivity.next(accessor.toString());
                accessor.validate();
            }
            accessorActivity.next("Generate");
            ActivityStack.Activity activity = this.activities.begin("?");
            for (AccessorInfo accessor : this.accessors) {
                activity.next(accessor.toString());
                MethodNode generated = accessor.generate();
                this.getTarget().addMixinMethod(generated);
                methods.add(generated);
            }
            accessorActivity.end();
        }
        catch (InvalidMixinException ex) {
            ex.prepend(this.activities);
            throw ex;
        }
        catch (Exception ex) {
            throw new InvalidMixinException(this, "Unexpecteded " + ex.getClass().getSimpleName() + " whilst transforming the mixin class:", (Throwable)ex, this.activities);
        }
        return methods;
    }

    private ClassInfo findRealType(ClassInfo mixin) {
        if (mixin == this.getClassInfo()) {
            return this.targetClassInfo;
        }
        ClassInfo type = this.targetClassInfo.findCorrespondingType(mixin);
        if (type == null) {
            throw new InvalidMixinException((IMixinContext)this, "Resolution error: unable to find corresponding type for " + mixin + " in hierarchy of " + this.targetClassInfo);
        }
        return type;
    }
}

