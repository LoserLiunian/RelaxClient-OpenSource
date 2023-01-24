/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelector;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorByName;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorConstructor;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorRemappable;
import org.spongepowered.asm.mixin.injection.selectors.TargetSelector;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.AnnotatedMixin;
import org.spongepowered.tools.obfuscation.AnnotatedMixinElementHandler;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.ReferenceManager;
import org.spongepowered.tools.obfuscation.SuppressedBy;
import org.spongepowered.tools.obfuscation.ext.SpecialPackages;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IReferenceManager;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;

class AnnotatedMixinElementHandlerInjector
extends AnnotatedMixinElementHandler {
    AnnotatedMixinElementHandlerInjector(IMixinAnnotationProcessor ap, AnnotatedMixin mixin) {
        super(ap, mixin);
    }

    public void registerInjector(AnnotatedElementInjector elem) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", (Element)elem.getElement());
        }
        for (String reference : elem.getAnnotation().getList("method")) {
            ITargetSelectorRemappable targetMember;
            ITargetSelector targetSelector = TargetSelector.parse(reference);
            try {
                targetSelector.validate();
            }
            catch (InvalidMemberDescriptorException ex) {
                elem.printMessage(this.ap, Diagnostic.Kind.ERROR, ex.getMessage());
            }
            if (!(targetSelector instanceof ITargetSelectorRemappable) || (targetMember = (ITargetSelectorRemappable)targetSelector).getName() == null) continue;
            if (targetMember.getDesc() != null) {
                this.validateReferencedTarget((ExecutableElement)elem.getElement(), elem.getAnnotation(), targetMember, elem.toString());
            }
            if (!elem.shouldRemap()) continue;
            for (TypeHandle target : this.mixin.getTargets()) {
                if (!this.registerInjector(elem, reference, targetMember, target)) break;
            }
        }
    }

    private boolean registerInjector(AnnotatedElementInjector elem, String reference, ITargetSelectorRemappable targetMember, TypeHandle target) {
        String desc = target.findDescriptor(targetMember);
        if (desc == null) {
            Diagnostic.Kind error;
            Diagnostic.Kind kind = error = this.mixin.isMultiTarget() ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            if (target.isSimulated()) {
                elem.printMessage(this.ap, Diagnostic.Kind.NOTE, elem + " target '" + reference + "' in @Pseudo mixin will not be obfuscated");
            } else if (target.isImaginary()) {
                elem.printMessage(this.ap, error, elem + " target requires method signature because enclosing type information for " + target + " is unavailable");
            } else if (!targetMember.isInitialiser()) {
                elem.printMessage(this.ap, error, "Unable to determine signature for " + elem + " target method");
            }
            return true;
        }
        String targetName = elem + " target " + targetMember.getName();
        MappingMethod targetMethod = target.getMappingMethod(targetMember.getName(), desc);
        ObfuscationData<MappingMethod> obfData = this.obf.getDataProvider().getObfMethod(targetMethod);
        if (obfData.isEmpty()) {
            if (target.isSimulated()) {
                obfData = this.obf.getDataProvider().getRemappedMethod(targetMethod);
            } else {
                if (targetMember.isClassInitialiser()) {
                    return true;
                }
                Diagnostic.Kind error = targetMember.isConstructor() ? Diagnostic.Kind.WARNING : Diagnostic.Kind.ERROR;
                elem.addMessage(error, "No obfuscation mapping for " + targetName, (Element)elem.getElement(), elem.getAnnotation());
                return false;
            }
        }
        IReferenceManager refMap = this.obf.getReferenceManager();
        try {
            if (targetMember.getOwner() == null && this.mixin.isMultiTarget() || target.isSimulated()) {
                obfData = AnnotatedMixinElementHandler.stripOwnerData(obfData);
            }
            refMap.addMethodMapping(this.classRef, reference, obfData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            String conflictType;
            String string = conflictType = this.mixin.isMultiTarget() ? "Multi-target" : "Target";
            if (elem.hasCoerceArgument() && targetMember.getOwner() == null && targetMember.getDesc() == null) {
                String newName;
                ITargetSelector oldMember = TargetSelector.parse(ex.getOld());
                ITargetSelector newMember = TargetSelector.parse(ex.getNew());
                String oldName = oldMember instanceof ITargetSelectorByName ? ((ITargetSelectorByName)oldMember).getName() : oldMember.toString();
                String string2 = newName = newMember instanceof ITargetSelectorByName ? ((ITargetSelectorByName)newMember).getName() : newMember.toString();
                if (oldName != null && oldName.equals(newName)) {
                    obfData = AnnotatedMixinElementHandler.stripDescriptors(obfData);
                    refMap.setAllowConflicts(true);
                    refMap.addMethodMapping(this.classRef, reference, obfData);
                    refMap.setAllowConflicts(false);
                    elem.printMessage(this.ap, Diagnostic.Kind.WARNING, "Coerced " + conflictType + " reference has conflicting descriptors for " + targetName + ": Storing bare references " + obfData.values() + " in refMap");
                    return true;
                }
            }
            elem.printMessage(this.ap, Diagnostic.Kind.ERROR, conflictType + " reference conflict for " + targetName + ": " + reference + " -> " + ex.getNew() + " previously defined as " + ex.getOld());
        }
        return true;
    }

    public void registerInjectionPoint(AnnotatedElementInjectionPoint elem, String format) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", (Element)elem.getElement());
        }
        if (!elem.shouldRemap()) {
            return;
        }
        String type = InjectionPointData.parseType((String)elem.getAt().getValue("value"));
        String target = (String)elem.getAt().getValue("target");
        if ("NEW".equals(type)) {
            this.remapNewTarget(String.format(format, type + ".<target>"), target, elem);
            this.remapNewTarget(String.format(format, type + ".args[class]"), elem.getAtArg("class"), elem);
        } else {
            this.remapReference(String.format(format, type + ".<target>"), target, elem);
        }
    }

    protected final void remapNewTarget(String subject, String reference, AnnotatedElementInjectionPoint elem) {
        ITargetSelectorConstructor member;
        String target;
        if (reference == null) {
            return;
        }
        ITargetSelector selector = TargetSelector.parse(reference);
        if (selector instanceof ITargetSelectorConstructor && (target = (member = (ITargetSelectorConstructor)selector).toCtorType()) != null) {
            String desc = member.toCtorDesc();
            MappingMethod m = new MappingMethod(target, ".", desc != null ? desc : "()V");
            ObfuscationData<MappingMethod> remapped = this.obf.getDataProvider().getRemappedMethod(m);
            if (remapped.isEmpty() && !SpecialPackages.isExcludedPackage(member.toCtorType())) {
                this.ap.printMessage(Diagnostic.Kind.WARNING, (CharSequence)("Cannot find class mapping for " + subject + " '" + target + "'"), (Element)elem.getElement(), elem.getAnnotation().asMirror(), SuppressedBy.MAPPING);
                return;
            }
            ObfuscationData<String> mappings = new ObfuscationData<String>();
            for (ObfuscationType type : remapped) {
                MappingMethod mapping = remapped.get(type);
                if (desc == null) {
                    mappings.put(type, mapping.getOwner());
                    continue;
                }
                mappings.put(type, mapping.getDesc().replace(")V", ")L" + mapping.getOwner() + ";"));
            }
            this.obf.getReferenceManager().addClassMapping(this.classRef, reference, mappings);
        }
        elem.notifyRemapped();
    }

    protected final void remapReference(String subject, String reference, AnnotatedElementInjectionPoint elem) {
        if (reference == null) {
            return;
        }
        ITargetSelector targetSelector = TargetSelector.parse(reference);
        if (!(targetSelector instanceof ITargetSelectorRemappable)) {
            return;
        }
        ITargetSelectorRemappable targetMember = (ITargetSelectorRemappable)targetSelector;
        AnnotationMirror errorsOn = (this.ap.getCompilerEnvironment() == IMixinAnnotationProcessor.CompilerEnvironment.JDT ? elem.getAt() : elem.getAnnotation()).asMirror();
        if (!targetMember.isFullyQualified()) {
            String missing = targetMember.getOwner() == null ? (targetMember.getDesc() == null ? "owner and signature" : "owner") : "signature";
            this.ap.printMessage(Diagnostic.Kind.ERROR, (CharSequence)(subject + " is not fully qualified, missing " + missing), (Element)elem.getElement(), errorsOn);
            return;
        }
        try {
            targetMember.validate();
        }
        catch (InvalidMemberDescriptorException ex) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, (CharSequence)ex.getMessage(), (Element)elem.getElement(), errorsOn);
        }
        try {
            if (targetMember.isField()) {
                ObfuscationData<MappingField> obfFieldData = this.obf.getDataProvider().getObfFieldRecursive(targetMember);
                if (obfFieldData.isEmpty()) {
                    if (targetMember.getOwner() == null || !SpecialPackages.isExcludedPackage(targetMember.getOwner())) {
                        this.ap.printMessage(Diagnostic.Kind.WARNING, (CharSequence)("Cannot find field mapping for " + subject + " '" + reference + "'"), (Element)elem.getElement(), errorsOn, SuppressedBy.MAPPING);
                    }
                    return;
                }
                this.obf.getReferenceManager().addFieldMapping(this.classRef, reference, targetMember, obfFieldData);
            } else {
                ObfuscationData<MappingMethod> obfMethodData = this.obf.getDataProvider().getObfMethodRecursive(targetMember);
                if (obfMethodData.isEmpty()) {
                    if (targetMember.getOwner() == null || !SpecialPackages.isExcludedPackage(targetMember.getOwner())) {
                        this.ap.printMessage(Diagnostic.Kind.WARNING, (CharSequence)("Cannot find method mapping for " + subject + " '" + reference + "'"), (Element)elem.getElement(), errorsOn, SuppressedBy.MAPPING);
                    }
                    return;
                }
                this.obf.getReferenceManager().addMethodMapping(this.classRef, reference, targetMember, obfMethodData);
            }
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, (CharSequence)("Unexpected reference conflict for " + subject + ": " + reference + " -> " + ex.getNew() + " previously defined as " + ex.getOld()), (Element)elem.getElement(), errorsOn);
            return;
        }
        elem.notifyRemapped();
    }

    static class AnnotatedElementInjectionPoint
    extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement> {
        private final AnnotationHandle at;
        private Map<String, String> args;
        private final InjectorRemap state;

        public AnnotatedElementInjectionPoint(ExecutableElement element, AnnotationHandle inject, AnnotationHandle at, InjectorRemap state) {
            super(element, inject);
            this.at = at;
            this.state = state;
        }

        public boolean shouldRemap() {
            return this.at.getBoolean("remap", this.state.shouldRemap());
        }

        public AnnotationHandle getAt() {
            return this.at;
        }

        public String getAtArg(String key) {
            if (this.args == null) {
                this.args = new HashMap<String, String>();
                for (String arg : this.at.getList("args")) {
                    if (arg == null) continue;
                    int eqPos = arg.indexOf(61);
                    if (eqPos > -1) {
                        this.args.put(arg.substring(0, eqPos), arg.substring(eqPos + 1));
                        continue;
                    }
                    this.args.put(arg, "");
                }
            }
            return this.args.get(key);
        }

        public void notifyRemapped() {
            this.state.notifyRemapped();
        }
    }

    static class AnnotatedElementInjector
    extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement> {
        private final InjectorRemap state;

        public AnnotatedElementInjector(ExecutableElement element, AnnotationHandle annotation, InjectorRemap shouldRemap) {
            super(element, annotation);
            this.state = shouldRemap;
        }

        public boolean shouldRemap() {
            return this.state.shouldRemap();
        }

        public boolean hasCoerceArgument() {
            if (!this.annotation.toString().equals("@Inject")) {
                return false;
            }
            Iterator<? extends VariableElement> iterator2 = ((ExecutableElement)this.element).getParameters().iterator();
            if (iterator2.hasNext()) {
                VariableElement param = iterator2.next();
                return AnnotationHandle.of(param, Coerce.class).exists();
            }
            return false;
        }

        public void addMessage(Diagnostic.Kind kind, CharSequence msg, Element element, AnnotationHandle annotation) {
            this.state.addMessage(kind, msg, element, annotation);
        }

        public String toString() {
            return this.getAnnotation().toString();
        }
    }
}

