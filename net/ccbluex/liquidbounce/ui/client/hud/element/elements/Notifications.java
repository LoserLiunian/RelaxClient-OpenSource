/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.FadeState;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.value.ListValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="Notifications", single=true)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Notifications;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "exampleNotification", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/Notification;", "mode", "Lnet/ccbluex/liquidbounce/value/ListValue;", "getMode", "()Lnet/ccbluex/liquidbounce/value/ListValue;", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "Relaxed"})
public final class Notifications
extends Element {
    private final Notification exampleNotification;
    @NotNull
    private final ListValue mode;

    @NotNull
    public final ListValue getMode() {
        return this.mode;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    @Nullable
    public Border drawElement() {
        boolean bl = false;
        List notifications = new ArrayList();
        bl = false;
        for (Notification notify : (Iterable)LiquidBounce.INSTANCE.getHud().getNotifications()) {
            void index;
            GL11.glPushMatrix();
            if (notify.drawNotification((int)index, this)) {
                notifications.add(notify);
            }
            GL11.glPopMatrix();
            ++index;
        }
        for (Notification notify : notifications) {
            LiquidBounce.INSTANCE.getHud().getNotifications().remove(notify);
        }
        if (MinecraftInstance.classProvider.isGuiHudDesigner(MinecraftInstance.mc.getCurrentScreen())) {
            if (!LiquidBounce.INSTANCE.getHud().getNotifications().contains(this.exampleNotification)) {
                LiquidBounce.INSTANCE.getHud().addNotification(this.exampleNotification);
            }
            this.exampleNotification.setFadeState(FadeState.STAY);
            this.exampleNotification.setDisplayTime(System.currentTimeMillis());
            return new Border(-((float)this.exampleNotification.getWidth()) + (float)80, -((float)this.exampleNotification.getHeight()) - 24.5f, 80.0f, -24.5f);
        }
        return null;
    }

    public Notifications(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.exampleNotification = new Notification("Notification", "This is an example notification.", NotifyType.INFO, 0, 0, 24, null);
        this.mode = new ListValue("Mode", new String[]{"Novoline"}, "Novoline");
    }

    public /* synthetic */ Notifications(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 143.0;
        }
        if ((n & 2) != 0) {
            d2 = 30.0;
        }
        if ((n & 4) != 0) {
            f = 1.05f;
        }
        if ((n & 8) != 0) {
            side = new Side(Side.Horizontal.RIGHT, Side.Vertical.DOWN);
        }
        this(d, d2, f, side);
    }

    public Notifications() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }
}

