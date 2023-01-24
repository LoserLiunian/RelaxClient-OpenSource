/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.ui.client;

import com.google.gson.Gson;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiScreen;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.HttpUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016J\u0018\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\b\u0010\u0017\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/GuiServerStatus;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;)V", "status", "Ljava/util/HashMap;", "", "actionPerformed", "", "button", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "keyTyped", "typedChar", "", "keyCode", "loadInformation", "Relaxed"})
public final class GuiServerStatus
extends WrappedGuiScreen {
    private final HashMap<String, String> status;
    private final IGuiScreen prevGui;

    @Override
    public void initGui() {
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 145, "Back"));
        ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>(this){
            final /* synthetic */ GuiServerStatus this$0;

            public final void invoke() {
                GuiServerStatus.access$loadInformation(this.this$0);
            }
            {
                this.this$0 = guiServerStatus;
                super(0);
            }
        }, 31, null);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        int i = this.getRepresentedScreen().getHeight() / 4 + 40;
        RenderUtils.drawRect((float)this.getRepresentedScreen().getWidth() / 2.0f - (float)115, (float)i - 5.0f, (float)this.getRepresentedScreen().getWidth() / 2.0f + (float)115, (float)this.getRepresentedScreen().getHeight() / 4.0f + (float)43 + (float)(this.status.keySet().isEmpty() ? 10 : this.status.keySet().size() * Fonts.font40.getFontHeight()), Integer.MIN_VALUE);
        if (this.status.isEmpty()) {
            float f = (float)this.getRepresentedScreen().getWidth() / 2.0f;
            float f2 = (float)this.getRepresentedScreen().getHeight() / 4.0f + (float)40;
            Color color = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color, "Color.WHITE");
            Fonts.font40.drawCenteredString("Loading...", f, f2, color.getRGB());
        } else {
            for (String server : this.status.keySet()) {
                String color = this.status.get(server);
                String string = server + ": " + (StringsKt.equals(color, "red", true) ? "\u00a7c" : (StringsKt.equals(color, "yellow", true) ? "\u00a7e" : "\u00a7a")) + (StringsKt.equals(color, "red", true) ? "Offline" : (StringsKt.equals(color, "yellow", true) ? "Slow" : "Online"));
                float f = (float)this.getRepresentedScreen().getWidth() / 2.0f;
                float f3 = i;
                Color color2 = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color2, "Color.WHITE");
                Fonts.font40.drawCenteredString(string, f, f3, color2.getRGB());
                i += Fonts.font40.getFontHeight();
            }
        }
        Fonts.bold40.drawCenteredString("Server Status", (float)this.getRepresentedScreen().getWidth() / 2.0f, (float)this.getRepresentedScreen().getHeight() / 8.0f + 5.0f, 4673984, true);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private final void loadInformation() {
        this.status.clear();
        try {
            Object object = new Gson().fromJson(HttpUtils.get("https://status.mojang.com/check"), List.class);
            if (object == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.collections.Map<kotlin.String, kotlin.String>>");
            }
            List linkedTreeMaps = (List)object;
            Iterator iterator2 = linkedTreeMaps.iterator();
            while (iterator2.hasNext()) {
                Map linkedTreeMap;
                Map map = linkedTreeMap = (Map)iterator2.next();
                boolean bl = false;
                for (Map.Entry entry : map.entrySet()) {
                    ((Map)this.status).put(entry.getKey(), entry.getValue());
                }
            }
        }
        catch (IOException e) {
            ((Map)this.status).put("status.mojang.com/check", "red");
        }
    }

    @Override
    public void actionPerformed(@NotNull IGuiButton button) {
        Intrinsics.checkParameterIsNotNull(button, "button");
        if (button.getId() == 1) {
            MinecraftInstance.mc.displayGuiScreen(this.prevGui);
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (1 == keyCode) {
            MinecraftInstance.mc.displayGuiScreen(this.prevGui);
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    public GuiServerStatus(@NotNull IGuiScreen prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
        this.status = new HashMap();
    }

    public static final /* synthetic */ void access$loadInformation(GuiServerStatus $this) {
        $this.loadInformation();
    }
}

