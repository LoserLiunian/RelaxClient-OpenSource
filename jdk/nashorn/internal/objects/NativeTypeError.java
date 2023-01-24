/*
 * Decompiled with CFR 0.152.
 */
package jdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.objects.NativeError;
import jdk.nashorn.internal.runtime.AccessorProperty;
import jdk.nashorn.internal.runtime.JSType;
import jdk.nashorn.internal.runtime.Property;
import jdk.nashorn.internal.runtime.PropertyMap;
import jdk.nashorn.internal.runtime.ScriptObject;
import jdk.nashorn.internal.runtime.ScriptRuntime;

public final class NativeTypeError
extends ScriptObject {
    public Object instMessage;
    public Object nashornException;
    private static PropertyMap $nasgenmap$;

    NativeTypeError(Object msg, Global global) {
        super(global.getTypeErrorPrototype(), $nasgenmap$);
        if (msg != ScriptRuntime.UNDEFINED) {
            this.instMessage = JSType.toString(msg);
        } else {
            this.delete("message", false);
        }
        NativeError.initException(this);
    }

    private NativeTypeError(Object msg) {
        this(msg, Global.instance());
    }

    @Override
    public String getClassName() {
        return "Error";
    }

    public static NativeTypeError constructor(boolean newObj, Object self, Object msg) {
        return new NativeTypeError(msg);
    }

    static {
        NativeTypeError.$clinit$();
    }

    public static void $clinit$() {
        ArrayList<Property> arrayList = new ArrayList<Property>(2);
        arrayList.add(AccessorProperty.create("message", 2, cfr_ldc_0(), cfr_ldc_1()));
        arrayList.add(AccessorProperty.create("nashornException", 2, cfr_ldc_2(), cfr_ldc_3()));
        $nasgenmap$ = PropertyMap.newMap(arrayList);
    }

    public Object G$instMessage() {
        return this.instMessage;
    }

    public void S$instMessage(Object object) {
        this.instMessage = object;
    }

    public Object G$nashornException() {
        return this.nashornException;
    }

    public void S$nashornException(Object object) {
        this.nashornException = object;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findVirtual(NativeTypeError.class, "G$instMessage", MethodType.fromMethodDescriptorString("()Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findVirtual(NativeTypeError.class, "S$instMessage", MethodType.fromMethodDescriptorString("(Ljava/lang/Object;)V", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findVirtual(NativeTypeError.class, "G$nashornException", MethodType.fromMethodDescriptorString("()Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_3() {
        try {
            return MethodHandles.lookup().findVirtual(NativeTypeError.class, "S$nashornException", MethodType.fromMethodDescriptorString("(Ljava/lang/Object;)V", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

