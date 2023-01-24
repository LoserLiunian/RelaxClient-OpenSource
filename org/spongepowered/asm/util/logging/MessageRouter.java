/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.spongepowered.asm.util.logging;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MessageRouter {
    private static Messager messager;

    private MessageRouter() {
    }

    public static Messager getMessager() {
        if (messager == null) {
            messager = new LoggingMessager();
        }
        return messager;
    }

    public static void setMessager(Messager messager) {
        MessageRouter.messager = messager == null ? null : new DebugInterceptingMessager(messager);
    }

    static class DebugInterceptingMessager
    implements Messager {
        private final Messager wrapped;

        DebugInterceptingMessager(Messager messager) {
            this.wrapped = messager;
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg) {
            if (kind != Diagnostic.Kind.OTHER) {
                this.wrapped.printMessage(kind, msg);
            }
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg, Element e) {
            if (kind != Diagnostic.Kind.OTHER) {
                this.wrapped.printMessage(kind, msg, e);
            }
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg, Element e, AnnotationMirror a) {
            if (kind != Diagnostic.Kind.OTHER) {
                this.wrapped.printMessage(kind, msg, e, a);
            }
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg, Element e, AnnotationMirror a, AnnotationValue v) {
            if (kind != Diagnostic.Kind.OTHER) {
                this.wrapped.printMessage(kind, msg, e, a, v);
            }
        }
    }

    static class LoggingMessager
    implements Messager {
        private static final Logger logger = LogManager.getLogger((String)"mixin");

        LoggingMessager() {
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg) {
            logger.log(LoggingMessager.messageKindToLoggingLevel(kind), (Object)msg);
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg, Element e) {
            logger.log(LoggingMessager.messageKindToLoggingLevel(kind), (Object)msg);
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg, Element e, AnnotationMirror a) {
            logger.log(LoggingMessager.messageKindToLoggingLevel(kind), (Object)msg);
        }

        @Override
        public void printMessage(Diagnostic.Kind kind, CharSequence msg, Element e, AnnotationMirror a, AnnotationValue v) {
            logger.log(LoggingMessager.messageKindToLoggingLevel(kind), (Object)msg);
        }

        private static Level messageKindToLoggingLevel(Diagnostic.Kind kind) {
            switch (kind) {
                case ERROR: {
                    return Level.ERROR;
                }
                case WARNING: 
                case MANDATORY_WARNING: {
                    return Level.WARN;
                }
                case NOTE: {
                    return Level.INFO;
                }
            }
            return Level.DEBUG;
        }
    }
}

