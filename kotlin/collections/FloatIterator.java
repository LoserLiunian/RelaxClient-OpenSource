/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\u0007\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0002H\u0086\u0002\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H&\u00a8\u0006\u0007"}, d2={"Lkotlin/collections/FloatIterator;", "", "", "()V", "next", "()Ljava/lang/Float;", "nextFloat", "kotlin-stdlib"})
public abstract class FloatIterator
implements Iterator<Float>,
KMappedMarker {
    @Override
    @NotNull
    public final Float next() {
        return Float.valueOf(this.nextFloat());
    }

    public abstract float nextFloat();

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
