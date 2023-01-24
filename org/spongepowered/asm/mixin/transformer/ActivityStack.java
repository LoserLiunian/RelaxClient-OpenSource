/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.asm.mixin.transformer;

public class ActivityStack {
    public static final String GLUE_STRING = " -> ";
    private final Activity head;
    private Activity tail;
    private String glue;

    public ActivityStack() {
        this(null, GLUE_STRING);
    }

    public ActivityStack(String root) {
        this(root, GLUE_STRING);
    }

    public ActivityStack(String root, String glue) {
        this.head = this.tail = new Activity(null, root);
        this.glue = glue;
    }

    public void clear() {
        this.tail = this.head;
        this.head.next = null;
    }

    public Activity begin(String description) {
        this.tail = new Activity(this.tail, description != null ? description : "null");
        return this.tail;
    }

    public Activity begin(String descriptionFormat, Object ... args2) {
        if (descriptionFormat == null) {
            descriptionFormat = "null";
        }
        this.tail = new Activity(this.tail, String.format(descriptionFormat, args2));
        return this.tail;
    }

    void end(Activity activity) {
        this.tail = activity.last;
        this.tail.next = null;
    }

    public String toString() {
        return this.toString(this.glue);
    }

    public String toString(String glue) {
        if (this.head.description == null && this.head.next == null) {
            return "Unknown";
        }
        StringBuilder sb = new StringBuilder();
        Activity activity = this.head;
        while (activity != null) {
            if (activity.description != null) {
                sb.append(activity.description);
                if (activity.next != null) {
                    sb.append(glue);
                }
            }
            activity = activity.next;
        }
        return sb.toString();
    }

    public class Activity {
        public String description;
        Activity last;
        Activity next;

        Activity(Activity last, String description) {
            if (last != null) {
                last.next = this;
            }
            this.last = last;
            this.description = description;
        }

        public void append(String text) {
            this.description = this.description != null ? this.description + text : text;
        }

        public void append(String textFormat, Object ... args2) {
            this.append(String.format(textFormat, args2));
        }

        public void end() {
            if (this.last != null) {
                ActivityStack.this.end(this);
                this.last = null;
            }
        }

        public void next(String description) {
            if (this.next != null) {
                this.next.end();
            }
            this.description = description;
        }

        public void next(String descriptionFormat, Object ... args2) {
            if (descriptionFormat == null) {
                descriptionFormat = "null";
            }
            this.next(String.format(descriptionFormat, args2));
        }
    }
}

