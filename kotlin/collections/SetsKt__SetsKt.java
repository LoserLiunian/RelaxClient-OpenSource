/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package kotlin.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000B\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aK\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u001f\b\u0001\u0010\u0005\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\b\tH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001aC\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001f\b\u0001\u0010\u0005\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\b\tH\u0087\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\u0012\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b\u001a\u001f\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\rj\b\u0012\u0004\u0012\u0002H\u000b`\u000e\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a5\u0010\f\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\rj\b\u0012\u0004\u0012\u0002H\u000b`\u000e\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0011\u001a\u001f\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\u0013j\b\u0012\u0004\u0012\u0002H\u000b`\u0014\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a5\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u0002H\u000b0\u0013j\b\u0012\u0004\u0012\u0002H\u000b`\u0014\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0015\u001a\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0007\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a+\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0007\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0017\u001a\u0015\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000bH\u0087\b\u001a+\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u0010\"\u0002H\u000b\u00a2\u0006\u0002\u0010\u0017\u001a\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\u0001H\u0000\u001a!\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b*\n\u0012\u0004\u0012\u0002H\u000b\u0018\u00010\u0001H\u0087\b\u00a8\u0006\u001b"}, d2={"buildSet", "", "E", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "emptySet", "T", "hashSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/HashSet;", "linkedSetOf", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "mutableSetOf", "([Ljava/lang/Object;)Ljava/util/Set;", "setOf", "optimizeReadOnlySet", "orEmpty", "kotlin-stdlib"}, xs="kotlin/collections/SetsKt")
class SetsKt__SetsKt
extends SetsKt__SetsJVMKt {
    @NotNull
    public static final <T> Set<T> emptySet() {
        return EmptySet.INSTANCE;
    }

    @NotNull
    public static final <T> Set<T> setOf(T ... elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return elements.length > 0 ? ArraysKt.toSet(elements) : SetsKt.emptySet();
    }

    @InlineOnly
    private static final <T> Set<T> setOf() {
        int $i$f$setOf = 0;
        return SetsKt.emptySet();
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> Set<T> mutableSetOf() {
        int $i$f$mutableSetOf = 0;
        return new LinkedHashSet();
    }

    @NotNull
    public static final <T> Set<T> mutableSetOf(T ... elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return (Set)ArraysKt.toCollection(elements, (Collection)new LinkedHashSet(MapsKt.mapCapacity(elements.length)));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> HashSet<T> hashSetOf() {
        int $i$f$hashSetOf = 0;
        return new HashSet();
    }

    @NotNull
    public static final <T> HashSet<T> hashSetOf(T ... elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return (HashSet)ArraysKt.toCollection(elements, (Collection)new HashSet(MapsKt.mapCapacity(elements.length)));
    }

    @SinceKotlin(version="1.1")
    @InlineOnly
    private static final <T> LinkedHashSet<T> linkedSetOf() {
        int $i$f$linkedSetOf = 0;
        return new LinkedHashSet();
    }

    @NotNull
    public static final <T> LinkedHashSet<T> linkedSetOf(T ... elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return (LinkedHashSet)ArraysKt.toCollection(elements, (Collection)new LinkedHashSet(MapsKt.mapCapacity(elements.length)));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> Set<E> buildSet(@BuilderInference Function1<? super Set<E>, Unit> builderAction) {
        int $i$f$buildSet = 0;
        boolean bl = false;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean bl2 = false;
        boolean bl3 = false;
        builderAction.invoke(linkedHashSet);
        return linkedHashSet;
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final <E> Set<E> buildSet(int capacity, @BuilderInference Function1<? super Set<E>, Unit> builderAction) {
        int $i$f$buildSet = 0;
        boolean bl = false;
        bl = false;
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt.mapCapacity(capacity));
        boolean bl2 = false;
        boolean bl3 = false;
        builderAction.invoke(linkedHashSet);
        return linkedHashSet;
    }

    @InlineOnly
    private static final <T> Set<T> orEmpty(@Nullable Set<? extends T> $this$orEmpty) {
        int $i$f$orEmpty = 0;
        Set<Object> set = $this$orEmpty;
        if (set == null) {
            set = SetsKt.emptySet();
        }
        return set;
    }

    @NotNull
    public static final <T> Set<T> optimizeReadOnlySet(@NotNull Set<? extends T> $this$optimizeReadOnlySet) {
        Set<T> set;
        Intrinsics.checkParameterIsNotNull($this$optimizeReadOnlySet, "$this$optimizeReadOnlySet");
        switch ($this$optimizeReadOnlySet.size()) {
            case 0: {
                set = SetsKt.emptySet();
                break;
            }
            case 1: {
                set = SetsKt.setOf($this$optimizeReadOnlySet.iterator().next());
                break;
            }
            default: {
                set = $this$optimizeReadOnlySet;
            }
        }
        return set;
    }
}

