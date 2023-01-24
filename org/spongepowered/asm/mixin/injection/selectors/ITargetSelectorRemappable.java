/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.injection.selectors;

import org.spongepowered.asm.mixin.injection.selectors.ITargetSelectorByName;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;

public interface ITargetSelectorRemappable
extends ITargetSelectorByName {
    public IMapping<?> asMapping();

    public MappingMethod asMethodMapping();

    public MappingField asFieldMapping();

    public ITargetSelectorRemappable move(String var1);

    public ITargetSelectorRemappable transform(String var1);

    public ITargetSelectorRemappable remapUsing(MappingMethod var1, boolean var2);
}

