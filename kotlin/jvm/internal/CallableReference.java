/*
 * Decompiled with CFR 0.152.
 */
package kotlin.jvm.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.SinceKotlin;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;

public abstract class CallableReference
implements KCallable,
Serializable {
    private transient KCallable reflected;
    @SinceKotlin(version="1.1")
    protected final Object receiver;
    @SinceKotlin(version="1.1")
    public static final Object NO_RECEIVER = NoReceiver.access$000();

    public CallableReference() {
        this(NO_RECEIVER);
    }

    @SinceKotlin(version="1.1")
    protected CallableReference(Object receiver) {
        this.receiver = receiver;
    }

    protected abstract KCallable computeReflected();

    @SinceKotlin(version="1.1")
    public Object getBoundReceiver() {
        return this.receiver;
    }

    @SinceKotlin(version="1.1")
    public KCallable compute() {
        KCallable result = this.reflected;
        if (result == null) {
            this.reflected = result = this.computeReflected();
        }
        return result;
    }

    @SinceKotlin(version="1.1")
    protected KCallable getReflected() {
        KCallable result = this.compute();
        if (result == this) {
            throw new KotlinReflectionNotSupportedError();
        }
        return result;
    }

    public KDeclarationContainer getOwner() {
        throw new AbstractMethodError();
    }

    @Override
    public String getName() {
        throw new AbstractMethodError();
    }

    public String getSignature() {
        throw new AbstractMethodError();
    }

    @Override
    public List<KParameter> getParameters() {
        return this.getReflected().getParameters();
    }

    @Override
    public KType getReturnType() {
        return this.getReflected().getReturnType();
    }

    @Override
    public List<Annotation> getAnnotations() {
        return this.getReflected().getAnnotations();
    }

    @Override
    @SinceKotlin(version="1.1")
    public List<KTypeParameter> getTypeParameters() {
        return this.getReflected().getTypeParameters();
    }

    public Object call(Object ... args2) {
        return this.getReflected().call(args2);
    }

    public Object callBy(Map args2) {
        return this.getReflected().callBy(args2);
    }

    @Override
    @SinceKotlin(version="1.1")
    public KVisibility getVisibility() {
        return this.getReflected().getVisibility();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isFinal() {
        return this.getReflected().isFinal();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isOpen() {
        return this.getReflected().isOpen();
    }

    @Override
    @SinceKotlin(version="1.1")
    public boolean isAbstract() {
        return this.getReflected().isAbstract();
    }

    @Override
    @SinceKotlin(version="1.3")
    public boolean isSuspend() {
        return this.getReflected().isSuspend();
    }

    @SinceKotlin(version="1.2")
    private static class NoReceiver
    implements Serializable {
        private static final NoReceiver INSTANCE = new NoReceiver();

        private NoReceiver() {
        }

        private Object readResolve() throws ObjectStreamException {
            return INSTANCE;
        }

        static /* synthetic */ NoReceiver access$000() {
            return INSTANCE;
        }
    }
}

