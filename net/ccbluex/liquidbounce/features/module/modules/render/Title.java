/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.opengl.Display
 */
package net.ccbluex.liquidbounce.features.module.modules.render;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.value.TextValue;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.Display;

@ModuleInfo(name="Title", description="Title", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/render/Title;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "CopywritingValue", "Lnet/ccbluex/liquidbounce/value/TextValue;", "H", "", "HM", "M", "S", "fakeNameValue", "onUpdate", "", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class Title
extends Module {
    private final TextValue fakeNameValue = new TextValue("SetTitle", "\ud835\udcdf\ud835\udcfb\ud835\udcf2\ud835\udced\ud835\udcee");
    private final TextValue CopywritingValue = new TextValue("Copywriting", "\ud835\udcd2\ud835\udcea\ud835\udcf7 \ud835\udcd2\ud835\udcfe\ud835\udcfc\ud835\udcfd\ud835\udcf8\ud835\udcf6");
    private int S;
    private int HM;
    private int M;
    private int H;

    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        ++this.HM;
        if (this.HM == 20) {
            ++this.S;
            this.HM = 0;
        }
        if (this.S == 60) {
            ++this.M;
            this.S = 0;
        }
        if (this.M == 60) {
            ++this.H;
            this.M = 0;
        }
        Display.setTitle((String)((String)this.fakeNameValue.get() + " |  " + (String)this.CopywritingValue.get() + " | \u4f60\u5df2\u6e38\u73a9" + this.H + "  \u65f6  " + this.M + "  \u5206  " + this.S + "  \u79d2  "));
    }
}

