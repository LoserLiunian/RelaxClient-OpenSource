/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.tools.obfuscation.interfaces;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.SuppressedBy;

public interface IMessagerSuppressible
extends Messager {
    public void printMessage(Diagnostic.Kind var1, CharSequence var2, Element var3, SuppressedBy var4);

    public void printMessage(Diagnostic.Kind var1, CharSequence var2, Element var3, AnnotationMirror var4, SuppressedBy var5);

    public void printMessage(Diagnostic.Kind var1, CharSequence var2, Element var3, AnnotationMirror var4, AnnotationValue var5, SuppressedBy var6);
}

