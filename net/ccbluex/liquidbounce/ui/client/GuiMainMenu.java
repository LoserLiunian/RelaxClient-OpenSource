/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.ui.client;

import java.util.ArrayList;
import kotlin.Metadata;
import me.ui.IGuiButton;
import native0.Loader;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.minecraft.client.gui.GuiScreen;
import oh.yalan.NativeClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Shadow;

@NativeClass
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0016J \u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0016R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001e"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/GuiMainMenu;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "()V", "arrayList", "Ljava/util/ArrayList;", "Lme/ui/IGuiButton;", "Lkotlin/collections/ArrayList;", "getArrayList", "()Ljava/util/ArrayList;", "setArrayList", "(Ljava/util/ArrayList;)V", "currentScreen", "Lnet/minecraft/client/gui/GuiScreen;", "getCurrentScreen", "()Lnet/minecraft/client/gui/GuiScreen;", "setCurrentScreen", "(Lnet/minecraft/client/gui/GuiScreen;)V", "actionPerformed", "", "button", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "mouseClicked", "mouseButton", "Relaxed"})
public final class GuiMainMenu
extends WrappedGuiScreen {
    @NotNull
    private ArrayList<IGuiButton> arrayList = new ArrayList();
    @Shadow
    @Nullable
    private GuiScreen currentScreen;

    @NotNull
    public final native ArrayList<IGuiButton> getArrayList();

    public final native void setArrayList(@NotNull ArrayList<IGuiButton> var1);

    @Override
    public native void initGui();

    @Override
    public native void drawScreen(int var1, int var2, float var3);

    @Nullable
    public final native GuiScreen getCurrentScreen();

    public final native void setCurrentScreen(@Nullable GuiScreen var1);

    @Override
    public native void mouseClicked(int var1, int var2, int var3);

    @Override
    public native void actionPerformed(@NotNull net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton var1);

    static {
        Loader.registerNativesForClass(8, GuiMainMenu.class);
    }
}

