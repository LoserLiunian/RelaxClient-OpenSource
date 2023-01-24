/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.BiMap
 *  com.google.common.io.Files
 */
package org.spongepowered.tools.obfuscation.mapping.fg3;

import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;
import org.spongepowered.tools.obfuscation.mapping.common.MappingProvider;
import org.spongepowered.tools.obfuscation.mapping.fg3.MappingMethodLazy;

public class MappingProviderTSrg
extends MappingProvider {
    private List<String> inputMappings = new ArrayList<String>();

    public MappingProviderTSrg(Messager messager, Filer filer) {
        super(messager, filer);
    }

    @Override
    public void read(File input) throws IOException {
        BiMap packageMap = this.packageMap;
        BiMap classMap = this.classMap;
        BiMap fieldMap = this.fieldMap;
        BiMap methodMap = this.methodMap;
        String fromClass = null;
        String toClass = null;
        this.inputMappings.addAll(Files.readLines((File)input, (Charset)Charset.defaultCharset()));
        for (String line : this.inputMappings) {
            if (Strings.isNullOrEmpty((String)line) || line.startsWith("#")) continue;
            String[] parts = line.split(" ");
            if (line.startsWith("\t")) {
                if (fromClass == null) {
                    throw new IllegalStateException("Error parsing TSRG file, found member declaration with no class: " + line);
                }
                parts[0] = parts[0].substring(1);
                if (parts.length == 2) {
                    fieldMap.forcePut((Object)new MappingField(fromClass, parts[0]), (Object)new MappingField(toClass, parts[1]));
                    continue;
                }
                if (parts.length == 3) {
                    methodMap.forcePut((Object)new MappingMethod(fromClass, parts[0], parts[1]), (Object)new MappingMethodLazy(toClass, parts[2], parts[1], this));
                    continue;
                }
                throw new IllegalStateException("Error parsing TSRG file, too many arguments: " + line);
            }
            if (parts.length > 1) {
                String to;
                String from = parts[0];
                if (parts.length == 2) {
                    to = parts[1];
                    if (from.endsWith("/")) {
                        packageMap.forcePut((Object)from.substring(0, from.length() - 1), (Object)to.substring(0, to.length() - 1));
                        continue;
                    }
                    classMap.forcePut((Object)from, (Object)to);
                    fromClass = from;
                    toClass = to;
                    continue;
                }
                if (parts.length <= 2) continue;
                to = (String)classMap.get((Object)from);
                if (to == null) {
                    throw new IllegalStateException("Error parsing TSRG file, found inline member before class mapping: " + line);
                }
                if (parts.length == 3) {
                    fieldMap.forcePut((Object)new MappingField(from, parts[1]), (Object)new MappingField(to, parts[2]));
                    continue;
                }
                if (parts.length == 4) {
                    methodMap.forcePut((Object)new MappingMethod(from, parts[1], parts[2]), (Object)new MappingMethodLazy(to, parts[3], parts[2], this));
                    continue;
                }
                throw new IllegalStateException("Error parsing TSRG file, too many arguments: " + line);
            }
            throw new IllegalStateException("Error parsing TSRG, unrecognised directive: " + line);
        }
    }

    @Override
    public MappingField getFieldMapping(MappingField field) {
        if (field.getDesc() != null) {
            field = new MappingFieldSrg(field);
        }
        return (MappingField)this.fieldMap.get((Object)field);
    }

    List<String> getInputMappings() {
        return this.inputMappings;
    }
}

