/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Strings
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AnnotationNode
 *  org.objectweb.asm.tree.MethodNode
 */
package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;
import org.spongepowered.asm.mixin.injection.selectors.ITargetSelector;
import org.spongepowered.asm.mixin.injection.selectors.TargetSelector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InvalidMemberDescriptorException;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionPointException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InjectionPointData {
    private static final Pattern AT_PATTERN = InjectionPointData.createPattern();
    private final Map<String, String> args = new HashMap<String, String>();
    private final IMixinContext context;
    private final MethodNode method;
    private final AnnotationNode parent;
    private final String at;
    private final String type;
    private final InjectionPoint.Selector selector;
    private final String target;
    private final String slice;
    private final int ordinal;
    private final int opcode;
    private final String id;

    public InjectionPointData(IMixinContext context, MethodNode method, AnnotationNode parent, String at, List<String> args2, String target, String slice, int ordinal, int opcode, String id) {
        this.context = context;
        this.method = method;
        this.parent = parent;
        this.at = at;
        this.target = target;
        this.slice = Strings.nullToEmpty((String)slice);
        this.ordinal = Math.max(-1, ordinal);
        this.opcode = opcode;
        this.id = id;
        this.parseArgs(args2);
        this.args.put("target", target);
        this.args.put("ordinal", String.valueOf(ordinal));
        this.args.put("opcode", String.valueOf(opcode));
        Matcher matcher = AT_PATTERN.matcher(at);
        this.type = InjectionPointData.parseType(matcher, at);
        this.selector = InjectionPointData.parseSelector(matcher);
    }

    private void parseArgs(List<String> args2) {
        if (args2 == null) {
            return;
        }
        for (String arg : args2) {
            if (arg == null) continue;
            int eqPos = arg.indexOf(61);
            if (eqPos > -1) {
                this.args.put(arg.substring(0, eqPos), arg.substring(eqPos + 1));
                continue;
            }
            this.args.put(arg, "");
        }
    }

    public String getAt() {
        return this.at;
    }

    public String getType() {
        return this.type;
    }

    public InjectionPoint.Selector getSelector() {
        return this.selector;
    }

    public IMixinContext getContext() {
        return this.context;
    }

    public MethodNode getMethod() {
        return this.method;
    }

    public Type getMethodReturnType() {
        return Type.getReturnType((String)this.method.desc);
    }

    public AnnotationNode getParent() {
        return this.parent;
    }

    public String getSlice() {
        return this.slice;
    }

    public LocalVariableDiscriminator getLocalVariableDiscriminator() {
        return LocalVariableDiscriminator.parse(this.parent);
    }

    public String get(String key, String defaultValue) {
        String value = this.args.get(key);
        return value != null ? value : defaultValue;
    }

    public int get(String key, int defaultValue) {
        return InjectionPointData.parseInt(this.get(key, String.valueOf(defaultValue)), defaultValue);
    }

    public boolean get(String key, boolean defaultValue) {
        return InjectionPointData.parseBoolean(this.get(key, String.valueOf(defaultValue)), defaultValue);
    }

    public ITargetSelector get(String key) {
        try {
            return TargetSelector.parseAndValidate(this.get(key, ""), this.context);
        }
        catch (InvalidMemberDescriptorException ex) {
            throw new InvalidInjectionPointException(this.context, "Failed parsing @At(\"%s\").%s descriptor \"%s\" on %s", this.at, key, this.target, this.getDescription());
        }
    }

    public ITargetSelector getTarget() {
        try {
            return TargetSelector.parseAndValidate(this.target, this.context);
        }
        catch (InvalidMemberDescriptorException ex) {
            throw new InvalidInjectionPointException(this.context, "Failed parsing @At(\"%s\").target descriptor \"%s\" on %s", this.at, this.target, this.getDescription());
        }
    }

    public String getDescription() {
        return InjectionInfo.describeInjector(this.context, this.parent, this.method);
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getOpcode(int defaultOpcode) {
        return this.opcode > 0 ? this.opcode : defaultOpcode;
    }

    public int getOpcode(int defaultOpcode, int ... validOpcodes) {
        for (int validOpcode : validOpcodes) {
            if (this.opcode != validOpcode) continue;
            return this.opcode;
        }
        return defaultOpcode;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return this.type;
    }

    private static Pattern createPattern() {
        return Pattern.compile(String.format("^([^:]+):?(%s)?$", Joiner.on((char)'|').join((Object[])InjectionPoint.Selector.values())));
    }

    public static String parseType(String at) {
        Matcher matcher = AT_PATTERN.matcher(at);
        return InjectionPointData.parseType(matcher, at);
    }

    private static String parseType(Matcher matcher, String at) {
        return matcher.matches() ? matcher.group(1) : at;
    }

    private static InjectionPoint.Selector parseSelector(Matcher matcher) {
        return matcher.matches() && matcher.group(2) != null ? InjectionPoint.Selector.valueOf(matcher.group(2)) : InjectionPoint.Selector.DEFAULT;
    }

    private static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        }
        catch (Exception ex) {
            return defaultValue;
        }
    }

    private static boolean parseBoolean(String string, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(string);
        }
        catch (Exception ex) {
            return defaultValue;
        }
    }
}

