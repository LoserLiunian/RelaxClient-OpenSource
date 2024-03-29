/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.tools.obfuscation.service;

import java.util.Collection;
import java.util.Set;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.service.ObfuscationTypeDescriptor;

public interface IObfuscationService {
    public Set<String> getSupportedOptions();

    public Collection<ObfuscationTypeDescriptor> getObfuscationTypes(IMixinAnnotationProcessor var1);
}

