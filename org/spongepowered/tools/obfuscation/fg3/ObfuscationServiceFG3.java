/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableSet
 */
package org.spongepowered.tools.obfuscation.fg3;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Set;
import org.spongepowered.tools.obfuscation.fg3.ObfuscationEnvironmentFG3;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.service.IObfuscationService;
import org.spongepowered.tools.obfuscation.service.ObfuscationTypeDescriptor;

public class ObfuscationServiceFG3
implements IObfuscationService {
    public static final String SEARGE = "searge";
    public static final String REOBF_TSRG_FILE = "reobfTsrgFile";
    public static final String REOBF_EXTRA_TSRG_FILES = "reobfTsrgFiles";
    public static final String OUT_TSRG_SRG_FILE = "outTsrgFile";
    public static final String TSRG_OUTPUT_BEHAVIOUR = "mergeBehaviour";

    @Override
    public Set<String> getSupportedOptions() {
        return ImmutableSet.of((Object)REOBF_TSRG_FILE, (Object)OUT_TSRG_SRG_FILE, (Object)TSRG_OUTPUT_BEHAVIOUR);
    }

    @Override
    public Collection<ObfuscationTypeDescriptor> getObfuscationTypes(IMixinAnnotationProcessor ap) {
        ImmutableList.Builder list = ImmutableList.builder();
        if (ap.getOptions("mappingTypes").contains("tsrg")) {
            list.add((Object)new ObfuscationTypeDescriptor(SEARGE, REOBF_TSRG_FILE, REOBF_EXTRA_TSRG_FILES, OUT_TSRG_SRG_FILE, ObfuscationEnvironmentFG3.class));
        }
        return list.build();
    }
}

