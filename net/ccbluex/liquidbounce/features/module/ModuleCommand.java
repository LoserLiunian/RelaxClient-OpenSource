/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.block.IBlock;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCommand;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.block.BlockUtils;
import net.ccbluex.liquidbounce.utils.misc.StringUtils;
import net.ccbluex.liquidbounce.value.BlockValue;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.ccbluex.liquidbounce.value.TextValue;
import net.ccbluex.liquidbounce.value.Value;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016\u00a2\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016\u00a2\u0006\u0002\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2={"Lnet/ccbluex/liquidbounce/features/module/ModuleCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "module", "Lnet/ccbluex/liquidbounce/features/module/Module;", "values", "", "Lnet/ccbluex/liquidbounce/value/Value;", "(Lnet/ccbluex/liquidbounce/features/module/Module;Ljava/util/List;)V", "getModule", "()Lnet/ccbluex/liquidbounce/features/module/Module;", "getValues", "()Ljava/util/List;", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "tabComplete", "([Ljava/lang/String;)Ljava/util/List;", "Relaxed"})
public final class ModuleCommand
extends Command {
    @NotNull
    private final Module module;
    @NotNull
    private final List<Value<?>> values;

    /*
     * WARNING - void declaration
     */
    @Override
    public void execute(@NotNull String[] args2) {
        void $this$filterTo$iv$iv2;
        Intrinsics.checkParameterIsNotNull(args2, "args");
        Iterable $this$filter$iv = this.values;
        boolean $i$f$filter22 = false;
        Iterable iterable = $this$filter$iv;
        Collection destination$iv$iv2 = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object element$iv$iv : $this$filterTo$iv$iv2) {
            Value it = (Value)element$iv$iv;
            boolean bl = false;
            if (!(!(it instanceof FontValue))) continue;
            destination$iv$iv2.add(element$iv$iv);
        }
        String valueNames2 = CollectionsKt.joinToString$default((List)destination$iv$iv2, "/", null, null, 0, null, execute.valueNames.2.INSTANCE, 30, null);
        String $i$f$filter22 = this.module.getName();
        boolean $this$filterTo$iv$iv2 = false;
        String string = $i$f$filter22;
        if (string == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string2 = string.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.String).toLowerCase()");
        String moduleName = string2;
        if (args2.length < 2) {
            this.chatSyntax(this.values.size() == 1 ? moduleName + ' ' + valueNames2 + " <value>" : moduleName + " <" + valueNames2 + '>');
            return;
        }
        Value<?> value = this.module.getValue(args2[1]);
        if (value == null) {
            this.chatSyntax(moduleName + " <" + valueNames2 + '>');
            return;
        }
        if (value instanceof BoolValue) {
            boolean newValue = (Boolean)((BoolValue)value).get() == false;
            ((BoolValue)value).set(newValue);
            this.chat("\u00a77" + this.module.getName() + " \u00a78" + args2[1] + "\u00a77 was toggled " + (newValue ? "\u00a78on\u00a77" : "\u00a78off\u00a77."));
            this.playEdit();
        } else {
            if (args2.length < 3) {
                if (value instanceof IntegerValue || value instanceof FloatValue || value instanceof TextValue) {
                    String newValue = args2[1];
                    StringBuilder stringBuilder = new StringBuilder().append(moduleName).append(' ');
                    ModuleCommand moduleCommand = this;
                    boolean destination$iv$iv2 = false;
                    String string3 = newValue;
                    if (string3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string4 = string3.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string4, "(this as java.lang.String).toLowerCase()");
                    String string5 = string4;
                    moduleCommand.chatSyntax(stringBuilder.append(string5).append(" <value>").toString());
                } else if (value instanceof ListValue) {
                    String newValue = args2[1];
                    StringBuilder stringBuilder = new StringBuilder().append(moduleName).append(' ');
                    ModuleCommand moduleCommand = this;
                    boolean destination$iv$iv2 = false;
                    String string6 = newValue;
                    if (string6 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string7 = string6.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string7, "(this as java.lang.String).toLowerCase()");
                    String string8 = string7;
                    newValue = ArraysKt.joinToString$default(((ListValue)value).getValues(), (CharSequence)"/", null, null, 0, null, null, 62, null);
                    stringBuilder = stringBuilder.append(string8).append(" <");
                    destination$iv$iv2 = false;
                    String string9 = newValue;
                    if (string9 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string10 = string9.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string10, "(this as java.lang.String).toLowerCase()");
                    string8 = string10;
                    moduleCommand.chatSyntax(stringBuilder.append(string8).append('>').toString());
                }
                return;
            }
            try {
                String string11;
                int n;
                Value<?> newValue = value;
                if (newValue instanceof BlockValue) {
                    int id = 0;
                    try {
                        String string12 = args2[2];
                        boolean bl = false;
                        n = Integer.parseInt(string12);
                    }
                    catch (NumberFormatException exception) {
                        Integer tmpId;
                        Integer n2;
                        IBlock iBlock = MinecraftInstance.functions.getBlockFromName(args2[2]);
                        if (iBlock != null) {
                            IBlock iBlock2 = iBlock;
                            boolean bl = false;
                            boolean bl2 = false;
                            IBlock it = iBlock2;
                            boolean bl3 = false;
                            n2 = MinecraftInstance.functions.getIdFromBlock(it);
                        } else {
                            n2 = tmpId = null;
                        }
                        if (tmpId == null || tmpId <= 0) {
                            this.chat("\u00a77Block \u00a78" + args2[2] + "\u00a77 does not exist!");
                            return;
                        }
                        n = tmpId;
                    }
                    id = n;
                    ((BlockValue)value).set(id);
                    String string13 = args2[1];
                    StringBuilder stringBuilder = new StringBuilder().append("\u00a77").append(this.module.getName()).append(" \u00a78");
                    ModuleCommand moduleCommand = this;
                    boolean bl = false;
                    String string14 = string13;
                    if (string14 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string15 = string14.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string15, "(this as java.lang.String).toLowerCase()");
                    String string16 = string15;
                    moduleCommand.chat(stringBuilder.append(string16).append("\u00a77 was set to \u00a78").append(BlockUtils.getBlockName(id)).append("\u00a77.").toString());
                    this.playEdit();
                    return;
                }
                if (newValue instanceof IntegerValue) {
                    string11 = args2[2];
                    IntegerValue integerValue = (IntegerValue)value;
                    n = 0;
                    int n3 = Integer.parseInt(string11);
                    integerValue.set(n3);
                } else if (newValue instanceof FloatValue) {
                    string11 = args2[2];
                    FloatValue floatValue = (FloatValue)value;
                    n = 0;
                    float f = Float.parseFloat(string11);
                    floatValue.set(Float.valueOf(f));
                } else if (newValue instanceof ListValue) {
                    if (!((ListValue)value).contains(args2[2])) {
                        string11 = args2[1];
                        StringBuilder stringBuilder = new StringBuilder().append(moduleName).append(' ');
                        ModuleCommand moduleCommand = this;
                        n = 0;
                        String string17 = string11;
                        if (string17 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string18 = string17.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(string18, "(this as java.lang.String).toLowerCase()");
                        String string19 = string18;
                        string11 = ArraysKt.joinToString$default(((ListValue)value).getValues(), (CharSequence)"/", null, null, 0, null, null, 62, null);
                        stringBuilder = stringBuilder.append(string19).append(" <");
                        n = 0;
                        String string20 = string11;
                        if (string20 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String string21 = string20.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(string21, "(this as java.lang.String).toLowerCase()");
                        string19 = string21;
                        moduleCommand.chatSyntax(stringBuilder.append(string19).append('>').toString());
                        return;
                    }
                    ((ListValue)value).set(args2[2]);
                } else if (newValue instanceof TextValue) {
                    TextValue textValue = (TextValue)value;
                    String string22 = StringUtils.toCompleteString(args2, 2);
                    Intrinsics.checkExpressionValueIsNotNull(string22, "StringUtils.toCompleteString(args, 2)");
                    textValue.set(string22);
                }
                this.chat("\u00a77" + this.module.getName() + " \u00a78" + args2[1] + "\u00a77 was set to \u00a78" + value.get() + "\u00a77.");
                this.playEdit();
            }
            catch (NumberFormatException e) {
                this.chat("\u00a78" + args2[2] + "\u00a77 cannot be converted to number!");
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @NotNull
    public List<String> tabComplete(@NotNull String[] args2) {
        List<String> list;
        Intrinsics.checkParameterIsNotNull(args2, "args");
        Object object = args2;
        boolean bl = false;
        if (((String[])object).length == 0) {
            return CollectionsKt.emptyList();
        }
        switch (args2.length) {
            case 1: {
                void $this$mapTo$iv$iv;
                Value it;
                Iterable $this$filterTo$iv$iv;
                Iterable $this$filter$iv = this.values;
                boolean $i$f$filter = false;
                Iterable iterable = $this$filter$iv;
                Collection destination$iv$iv = new ArrayList();
                boolean $i$f$filterTo = false;
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    it = (Value)element$iv$iv;
                    boolean bl2 = false;
                    if (!(!(it instanceof FontValue) && StringsKt.startsWith(it.getName(), args2[0], true))) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                Iterable $this$map$iv = (List)destination$iv$iv;
                boolean $i$f$map = false;
                $this$filterTo$iv$iv = $this$map$iv;
                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                boolean $i$f$mapTo = false;
                for (Object item$iv$iv : $this$mapTo$iv$iv) {
                    String string;
                    it = (Value)item$iv$iv;
                    Collection collection = destination$iv$iv;
                    boolean bl3 = false;
                    String string2 = it.getName();
                    boolean bl4 = false;
                    String string3 = string2;
                    if (string3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    Intrinsics.checkExpressionValueIsNotNull(string3.toLowerCase(), "(this as java.lang.String).toLowerCase()");
                    collection.add(string);
                }
                list = (List)destination$iv$iv;
                break;
            }
            case 2: {
                object = this.module.getValue(args2[0]);
                if (object instanceof BlockValue) {
                    void $this$filterTo$iv$iv;
                    String it;
                    Iterable $this$mapTo$iv$iv;
                    Iterable $this$map$iv = MinecraftInstance.functions.getItemRegistryKeys();
                    boolean $i$f$map = false;
                    Iterable destination$iv$iv = $this$map$iv;
                    Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    boolean $i$f$mapTo = false;
                    for (Object item$iv$iv : $this$mapTo$iv$iv) {
                        String string;
                        IResourceLocation bl3 = (IResourceLocation)item$iv$iv;
                        Collection collection = destination$iv$iv2;
                        boolean bl5 = false;
                        String string4 = it.getResourcePath();
                        boolean bl6 = false;
                        String string5 = string4;
                        if (string5 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        Intrinsics.checkExpressionValueIsNotNull(string5.toLowerCase(), "(this as java.lang.String).toLowerCase()");
                        collection.add(string);
                    }
                    Iterable $this$filter$iv = (List)destination$iv$iv2;
                    boolean $i$f$filter = false;
                    $this$mapTo$iv$iv = $this$filter$iv;
                    destination$iv$iv2 = new ArrayList();
                    boolean $i$f$filterTo = false;
                    for (Object element$iv$iv : $this$filterTo$iv$iv) {
                        it = (String)element$iv$iv;
                        boolean bl7 = false;
                        if (!StringsKt.startsWith(it, args2[1], true)) continue;
                        destination$iv$iv2.add(element$iv$iv);
                    }
                    return (List)destination$iv$iv2;
                }
                if (object instanceof ListValue) {
                    Iterable $this$forEach$iv = this.values;
                    boolean $i$f$forEach = false;
                    for (Object element$iv : $this$forEach$iv) {
                        void $this$filterTo$iv$iv;
                        Value value = (Value)element$iv;
                        boolean bl8 = false;
                        if (!StringsKt.equals(value.getName(), args2[0], true) || !(value instanceof ListValue)) continue;
                        String[] $this$filter$iv = ((ListValue)value).getValues();
                        boolean $i$f$filter = false;
                        String[] bl7 = $this$filter$iv;
                        Collection destination$iv$iv = new ArrayList();
                        boolean $i$f$filterTo = false;
                        void var14_39 = $this$filterTo$iv$iv;
                        int n = ((void)var14_39).length;
                        for (int i = 0; i < n; ++i) {
                            void element$iv$iv;
                            void it = element$iv$iv = var14_39[i];
                            boolean bl9 = false;
                            if (!StringsKt.startsWith((String)it, args2[1], true)) continue;
                            destination$iv$iv.add(element$iv$iv);
                        }
                        return (List)destination$iv$iv;
                    }
                    return CollectionsKt.emptyList();
                }
                list = CollectionsKt.emptyList();
                break;
            }
            default: {
                list = CollectionsKt.emptyList();
            }
        }
        return list;
    }

    @NotNull
    public final Module getModule() {
        return this.module;
    }

    @NotNull
    public final List<Value<?>> getValues() {
        return this.values;
    }

    public ModuleCommand(@NotNull Module module, @NotNull List<? extends Value<?>> values2) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(values2, "values");
        String string = module.getName();
        ModuleCommand moduleCommand = this;
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        String string4 = string3;
        super(string4, new String[0]);
        this.module = module;
        this.values = values2;
        if (this.values.isEmpty()) {
            throw (Throwable)new IllegalArgumentException("Values are empty!");
        }
    }

    public /* synthetic */ ModuleCommand(Module module, List list, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            list = module.getValues();
        }
        this(module, list);
    }
}

