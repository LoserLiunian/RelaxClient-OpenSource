/*
 * Decompiled with CFR 0.152.
 */
package kotlin.time;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.time.ExperimentalTime;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004*P\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004B\u0002\b\u0005B0\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\"\b\t\u0012\u001e\b\u000bB\u001a\b\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\f\u0012\b\b\r\u0012\u0004\b\b(\u000e*P\b\u0007\u0010\u000f\"\u00020\u00102\u00020\u0010B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004B\u0002\b\u0005B0\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0011\u0012\"\b\t\u0012\u001e\b\u000bB\u001a\b\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\u0012\u0012\b\b\r\u0012\u0004\b\b(\u0013*P\b\u0007\u0010\u0014\"\u00020\u00152\u00020\u0015B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004B\u0002\b\u0005B0\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0016\u0012\"\b\t\u0012\u001e\b\u000bB\u001a\b\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\u0017\u0012\b\b\r\u0012\u0004\b\b(\u0018*P\b\u0007\u0010\u0019\"\u00020\u001a2\u00020\u001aB\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004B\u0002\b\u0005B0\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u001b\u0012\"\b\t\u0012\u001e\b\u000bB\u001a\b\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\u001c\u0012\b\b\r\u0012\u0004\b\b(\u001d\u00a8\u0006\u001e"}, d2={"AbstractDoubleClock", "Lkotlin/time/AbstractDoubleTimeSource;", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/time/ExperimentalTime;", "Lkotlin/Deprecated;", "message", "Use AbstractDoubleTimeSource instead.", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "kotlin.time.AbstractDoubleTimeSource", "expression", "AbstractDoubleTimeSource", "AbstractLongClock", "Lkotlin/time/AbstractLongTimeSource;", "Use AbstractLongTimeSource instead.", "kotlin.time.AbstractLongTimeSource", "AbstractLongTimeSource", "MonoClock", "Lkotlin/time/TimeSource$Monotonic;", "Use TimeSource.Monotonic instead.", "kotlin.time.TimeSource", "TimeSource.Monotonic", "TestClock", "Lkotlin/time/TestTimeSource;", "Use TestTimeSource instead.", "kotlin.time.TestTimeSource", "TestTimeSource", "kotlin-stdlib"})
public final class TimeSourcesKt {
    @Deprecated(message="Use TimeSource.Monotonic instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.TimeSource"}, expression="TimeSource.Monotonic"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void MonoClock$annotations() {
    }

    @Deprecated(message="Use AbstractLongTimeSource instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.AbstractLongTimeSource"}, expression="AbstractLongTimeSource"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void AbstractLongClock$annotations() {
    }

    @Deprecated(message="Use AbstractDoubleTimeSource instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.AbstractDoubleTimeSource"}, expression="AbstractDoubleTimeSource"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void AbstractDoubleClock$annotations() {
    }

    @Deprecated(message="Use TestTimeSource instead.", replaceWith=@ReplaceWith(imports={"kotlin.time.TestTimeSource"}, expression="TestTimeSource"))
    @SinceKotlin(version="1.3")
    @ExperimentalTime
    public static /* synthetic */ void TestClock$annotations() {
    }
}

