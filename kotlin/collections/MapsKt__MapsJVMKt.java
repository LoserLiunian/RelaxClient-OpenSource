/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapWithDefaultKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000R\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0081\b\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0001\u001a2\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f\u001aY\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000e\"\u000e\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u000f\"\u0004\b\u0001\u0010\n2*\u0010\u0010\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f0\u0011\"\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f\u00a2\u0006\u0002\u0010\u0012\u001a@\u0010\u0013\u001a\u0002H\n\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u00142\u0006\u0010\u0015\u001a\u0002H\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\n0\u0017H\u0086\b\u00a2\u0006\u0002\u0010\u0018\u001a\u0019\u0010\u0019\u001a\u00020\u001a*\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\bH\u0087\b\u001a2\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\bH\u0000\u001a1\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\bH\u0081\b\u001a:\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000e\"\u000e\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u000f\"\u0004\b\u0001\u0010\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b\u001a@\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u000e\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\b2\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\t0 \"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2={"INT_MAX_POWER_OF_TWO", "", "checkBuilderCapacity", "", "capacity", "mapCapacity", "expectedSize", "mapOf", "", "K", "V", "pair", "Lkotlin/Pair;", "sortedMapOf", "Ljava/util/SortedMap;", "", "pairs", "", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "getOrPut", "Ljava/util/concurrent/ConcurrentMap;", "key", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toProperties", "Ljava/util/Properties;", "", "toSingletonMap", "toSingletonMapOrSelf", "toSortedMap", "comparator", "Ljava/util/Comparator;", "kotlin-stdlib"}, xs="kotlin/collections/MapsKt")
class MapsKt__MapsJVMKt
extends MapsKt__MapWithDefaultKt {
    private static final int INT_MAX_POWER_OF_TWO = 0x40000000;

    @NotNull
    public static final <K, V> Map<K, V> mapOf(@NotNull Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "pair");
        Map<K, V> map = Collections.singletonMap(pair.getFirst(), pair.getSecond());
        Intrinsics.checkExpressionValueIsNotNull(map, "java.util.Collections.si\u2026(pair.first, pair.second)");
        return map;
    }

    public static final <K, V> V getOrPut(@NotNull ConcurrentMap<K, V> $this$getOrPut, K key, @NotNull Function0<? extends V> defaultValue) {
        int $i$f$getOrPut = 0;
        Intrinsics.checkParameterIsNotNull($this$getOrPut, "$this$getOrPut");
        Intrinsics.checkParameterIsNotNull(defaultValue, "defaultValue");
        Object v = $this$getOrPut.get(key);
        if (v == null) {
            V v2 = defaultValue.invoke();
            boolean bl = false;
            boolean bl2 = false;
            V v3 = v2;
            boolean bl3 = false;
            v = $this$getOrPut.putIfAbsent(key, v3);
            if (v == null) {
                v = v3;
            }
        }
        return v;
    }

    @NotNull
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> $this$toSortedMap) {
        Intrinsics.checkParameterIsNotNull($this$toSortedMap, "$this$toSortedMap");
        return new TreeMap<K, V>($this$toSortedMap);
    }

    @NotNull
    public static final <K, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> $this$toSortedMap, @NotNull Comparator<? super K> comparator) {
        Intrinsics.checkParameterIsNotNull($this$toSortedMap, "$this$toSortedMap");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        TreeMap<? extends K, ? extends V> treeMap = new TreeMap<K, V>(comparator);
        boolean bl = false;
        boolean bl2 = false;
        TreeMap<? extends K, ? extends V> $this$apply = treeMap;
        boolean bl3 = false;
        $this$apply.putAll($this$toSortedMap);
        return treeMap;
    }

    @NotNull
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> sortedMapOf(Pair<? extends K, ? extends V> ... pairs) {
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        TreeMap treeMap = new TreeMap();
        boolean bl = false;
        boolean bl2 = false;
        TreeMap $this$apply = treeMap;
        boolean bl3 = false;
        MapsKt.putAll((Map)$this$apply, pairs);
        return treeMap;
    }

    @InlineOnly
    private static final Properties toProperties(@NotNull Map<String, String> $this$toProperties) {
        int $i$f$toProperties = 0;
        Properties properties = new Properties();
        boolean bl = false;
        boolean bl2 = false;
        Properties $this$apply = properties;
        boolean bl3 = false;
        $this$apply.putAll($this$toProperties);
        return properties;
    }

    @InlineOnly
    private static final <K, V> Map<K, V> toSingletonMapOrSelf(@NotNull Map<K, ? extends V> $this$toSingletonMapOrSelf) {
        int $i$f$toSingletonMapOrSelf = 0;
        return MapsKt.toSingletonMap($this$toSingletonMapOrSelf);
    }

    @NotNull
    public static final <K, V> Map<K, V> toSingletonMap(@NotNull Map<? extends K, ? extends V> $this$toSingletonMap) {
        Intrinsics.checkParameterIsNotNull($this$toSingletonMap, "$this$toSingletonMap");
        Map.Entry<K, V> entry = $this$toSingletonMap.entrySet().iterator().next();
        boolean bl = false;
        boolean bl2 = false;
        Map.Entry<K, V> $this$with = entry;
        boolean bl3 = false;
        Map<K, V> map = Collections.singletonMap($this$with.getKey(), $this$with.getValue());
        Intrinsics.checkExpressionValueIsNotNull(map, "java.util.Collections.singletonMap(key, value)");
        Intrinsics.checkExpressionValueIsNotNull(map, "with(entries.iterator().\u2026ingletonMap(key, value) }");
        return map;
    }

    @PublishedApi
    public static final int mapCapacity(int expectedSize) {
        return expectedSize < 0 ? expectedSize : (expectedSize < 3 ? expectedSize + 1 : (expectedSize < 0x40000000 ? (int)((float)expectedSize / 0.75f + 1.0f) : Integer.MAX_VALUE));
    }

    @SinceKotlin(version="1.3")
    @ExperimentalStdlibApi
    @PublishedApi
    @InlineOnly
    private static final void checkBuilderCapacity(int capacity) {
        int $i$f$checkBuilderCapacity = 0;
    }
}

