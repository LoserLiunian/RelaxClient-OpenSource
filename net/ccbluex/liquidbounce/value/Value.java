/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.value;

import com.google.gson.JsonElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H&J\u000b\u0010\u0018\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001d\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00028\u0000H\u0014\u00a2\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00028\u0000H\u0014\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0011J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0017H&R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0005\u001a\u00028\u0000X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006 "}, d2={"Lnet/ccbluex/liquidbounce/value/Value;", "T", "", "name", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "isSupported", "", "()Z", "setSupported", "(Z)V", "getName", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "changeValue", "", "fromJson", "element", "Lcom/google/gson/JsonElement;", "get", "onChange", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;)V", "onChanged", "set", "toJson", "Relaxed"})
public abstract class Value<T> {
    private boolean isSupported;
    @NotNull
    private final String name;
    private T value;

    public final boolean isSupported() {
        return this.isSupported;
    }

    public final void setSupported(boolean bl) {
        this.isSupported = bl;
    }

    public final void set(T newValue) {
        if (Intrinsics.areEqual(newValue, this.value)) {
            return;
        }
        T oldValue = this.get();
        try {
            this.onChange(oldValue, newValue);
            this.changeValue(newValue);
            this.onChanged(oldValue, newValue);
            LiquidBounce.INSTANCE.getFileManager().saveConfig(LiquidBounce.INSTANCE.getFileManager().valuesConfig);
        }
        catch (Exception e) {
            ClientUtils.getLogger().error("[ValueSystem (" + this.name + ")]: " + e.getClass().getName() + " (" + e.getMessage() + ") [" + oldValue + " >> " + newValue + ']');
        }
    }

    public final T get() {
        return this.value;
    }

    public void changeValue(T value) {
        this.value = value;
    }

    @Nullable
    public abstract JsonElement toJson();

    public abstract void fromJson(@NotNull JsonElement var1);

    protected void onChange(T oldValue, T newValue) {
    }

    protected void onChanged(T oldValue, T newValue) {
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final T getValue() {
        return this.value;
    }

    public final void setValue(T t) {
        this.value = t;
    }

    public Value(@NotNull String name, T value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.value = value;
        this.isSupported = true;
    }
}

