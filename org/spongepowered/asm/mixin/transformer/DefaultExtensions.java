/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgsClassGenerator;
import org.spongepowered.asm.mixin.transformer.InnerClassGenerator;
import org.spongepowered.asm.mixin.transformer.SyntheticClassRegistry;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionCheckClass;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionCheckInterfaces;
import org.spongepowered.asm.mixin.transformer.ext.extensions.ExtensionClassExporter;
import org.spongepowered.asm.service.ISyntheticClassInfo;
import org.spongepowered.asm.util.IConsumer;

final class DefaultExtensions {
    private DefaultExtensions() {
    }

    static void create(MixinEnvironment environment, Extensions extensions, final SyntheticClassRegistry registry) {
        IConsumer<ISyntheticClassInfo> registryDelegate = new IConsumer<ISyntheticClassInfo>(){

            @Override
            public void accept(ISyntheticClassInfo item) {
                registry.registerSyntheticClass(item);
            }
        };
        extensions.add(new ArgsClassGenerator(registryDelegate));
        extensions.add(new InnerClassGenerator(registryDelegate));
        extensions.add(new ExtensionClassExporter(environment));
        extensions.add(new ExtensionCheckClass());
        extensions.add(new ExtensionCheckInterfaces());
    }
}

