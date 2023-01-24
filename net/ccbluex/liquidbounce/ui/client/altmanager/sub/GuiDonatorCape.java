/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.StatusLine
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.HttpDelete
 *  org.apache.http.client.methods.HttpPut
 *  org.apache.http.client.methods.HttpRequestBase
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClients
 *  org.apache.http.message.BasicHeader
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.input.Keyboard
 */
package net.ccbluex.liquidbounce.ui.client.altmanager.sub;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiTextField;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.MiscUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J \u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J \u0010\u0019\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010\u001c\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/altmanager/sub/GuiDonatorCape;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;", "(Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;)V", "stateButton", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "status", "", "transferCodeField", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiTextField;", "actionPerformed", "", "button", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "keyTyped", "typedChar", "", "keyCode", "mouseClicked", "mouseButton", "onGuiClosed", "updateScreen", "Companion", "Relaxed"})
public final class GuiDonatorCape
extends WrappedGuiScreen {
    private IGuiButton stateButton;
    private IGuiTextField transferCodeField;
    private String status;
    private final GuiAltManager prevGui;
    @NotNull
    private static String transferCode;
    private static boolean capeEnabled;
    public static final Companion Companion;

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.stateButton = MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 100, 105, "Disable Cape");
        List<IGuiButton> list = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton = this.stateButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stateButton");
        }
        list.add(iGuiButton);
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(2, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 96, "Donate to get Cape"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(0, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 120, "Back"));
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        IGuiTextField iGuiTextField = this.transferCodeField = MinecraftInstance.classProvider.createGuiPasswordField(666, iFontRenderer, this.getRepresentedScreen().getWidth() / 2 - 100, 80, 200, 20);
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        iGuiTextField.setFocused(true);
        IGuiTextField iGuiTextField2 = this.transferCodeField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        iGuiTextField2.setMaxStringLength(Integer.MAX_VALUE);
        IGuiTextField iGuiTextField3 = this.transferCodeField;
        if (iGuiTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        iGuiTextField3.setText(transferCode);
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        RenderUtils.drawRect(30.0f, 30.0f, (float)this.getRepresentedScreen().getWidth() - 30.0f, (float)this.getRepresentedScreen().getHeight() - 30.0f, Integer.MIN_VALUE);
        Fonts.font35.drawCenteredString("Donator Cape", (float)this.getRepresentedScreen().getWidth() / 2.0f, 36.0f, 0xFFFFFF);
        Fonts.font35.drawCenteredString(this.status, (float)this.getRepresentedScreen().getWidth() / 2.0f, (float)this.getRepresentedScreen().getHeight() / 4.0f + (float)80, 0xFFFFFF);
        IGuiTextField iGuiTextField = this.transferCodeField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        iGuiTextField.drawTextBox();
        Fonts.font40.drawCenteredString("\u00a77Transfer Code:", (float)this.getRepresentedScreen().getWidth() / 2.0f - (float)65, 66.0f, 0xFFFFFF);
        IGuiButton iGuiButton = this.stateButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stateButton");
        }
        iGuiButton.setDisplayString(capeEnabled ? "Disable Cape" : "Enable Cape");
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(@NotNull IGuiButton button) {
        Intrinsics.checkParameterIsNotNull(button, "button");
        if (!button.getEnabled()) {
            return;
        }
        switch (button.getId()) {
            case 0: {
                MinecraftInstance.mc.displayGuiScreen(this.prevGui.getRepresentedScreen());
                break;
            }
            case 1: {
                IGuiButton iGuiButton = this.stateButton;
                if (iGuiButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("stateButton");
                }
                iGuiButton.setEnabled(false);
                ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>(this){
                    final /* synthetic */ GuiDonatorCape this$0;

                    public final void invoke() {
                        String string;
                        CloseableHttpResponse response;
                        CloseableHttpClient httpClient = HttpClients.createDefault();
                        BasicHeader[] headers = new BasicHeader[]{new BasicHeader("Content-Type", "application/json"), new BasicHeader("Authorization", GuiDonatorCape.access$getTransferCodeField$p(this.this$0).getText())};
                        HttpRequestBase request = GuiDonatorCape.Companion.getCapeEnabled() ? (HttpRequestBase)new HttpDelete("http://capes.liquidbounce.net/api/v1/cape/self") : (HttpRequestBase)new HttpPut("http://capes.liquidbounce.net/api/v1/cape/self");
                        request.setHeaders((Header[])headers);
                        CloseableHttpResponse closeableHttpResponse = response = httpClient.execute((HttpUriRequest)request);
                        Intrinsics.checkExpressionValueIsNotNull(closeableHttpResponse, "response");
                        StatusLine statusLine = closeableHttpResponse.getStatusLine();
                        Intrinsics.checkExpressionValueIsNotNull(statusLine, "response.statusLine");
                        int statusCode = statusLine.getStatusCode();
                        if (statusCode == 204) {
                            GuiDonatorCape.Companion.setCapeEnabled(!GuiDonatorCape.Companion.getCapeEnabled());
                            string = GuiDonatorCape.Companion.getCapeEnabled() ? "\u00a7aSuccessfully enabled cape" : "\u00a7aSuccessfully disabled cape";
                        } else {
                            string = "\u00a7cFailed to toggle cape (" + statusCode + ')';
                        }
                        GuiDonatorCape.access$setStatus$p(this.this$0, string);
                        GuiDonatorCape.access$getStateButton$p(this.this$0).setEnabled(true);
                    }
                    {
                        this.this$0 = guiDonatorCape;
                        super(0);
                    }
                }, 31, null);
                break;
            }
            case 2: {
                MiscUtils.showURL("https://donate.liquidbounce.net");
                break;
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (1 == keyCode) {
            MinecraftInstance.mc.displayGuiScreen(this.prevGui.getRepresentedScreen());
            return;
        }
        IGuiTextField iGuiTextField = this.transferCodeField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        if (iGuiTextField.isFocused()) {
            IGuiTextField iGuiTextField2 = this.transferCodeField;
            if (iGuiTextField2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
            }
            iGuiTextField2.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        IGuiTextField iGuiTextField = this.transferCodeField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        iGuiTextField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        IGuiTextField iGuiTextField = this.transferCodeField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        iGuiTextField.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
        IGuiTextField iGuiTextField = this.transferCodeField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        transferCode = iGuiTextField.getText();
        super.onGuiClosed();
    }

    public GuiDonatorCape(@NotNull GuiAltManager prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
        this.status = "";
    }

    static {
        Companion = new Companion(null);
        transferCode = "";
        capeEnabled = true;
    }

    public static final /* synthetic */ IGuiTextField access$getTransferCodeField$p(GuiDonatorCape $this) {
        IGuiTextField iGuiTextField = $this.transferCodeField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transferCodeField");
        }
        return iGuiTextField;
    }

    public static final /* synthetic */ void access$setTransferCodeField$p(GuiDonatorCape $this, IGuiTextField iGuiTextField) {
        $this.transferCodeField = iGuiTextField;
    }

    public static final /* synthetic */ String access$getStatus$p(GuiDonatorCape $this) {
        return $this.status;
    }

    public static final /* synthetic */ void access$setStatus$p(GuiDonatorCape $this, String string) {
        $this.status = string;
    }

    public static final /* synthetic */ IGuiButton access$getStateButton$p(GuiDonatorCape $this) {
        IGuiButton iGuiButton = $this.stateButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stateButton");
        }
        return iGuiButton;
    }

    public static final /* synthetic */ void access$setStateButton$p(GuiDonatorCape $this, IGuiButton iGuiButton) {
        $this.stateButton = iGuiButton;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/altmanager/sub/GuiDonatorCape$Companion;", "", "()V", "capeEnabled", "", "getCapeEnabled", "()Z", "setCapeEnabled", "(Z)V", "transferCode", "", "getTransferCode", "()Ljava/lang/String;", "setTransferCode", "(Ljava/lang/String;)V", "Relaxed"})
    public static final class Companion {
        @NotNull
        public final String getTransferCode() {
            return transferCode;
        }

        public final void setTransferCode(@NotNull String string) {
            Intrinsics.checkParameterIsNotNull(string, "<set-?>");
            transferCode = string;
        }

        public final boolean getCapeEnabled() {
            return capeEnabled;
        }

        public final void setCapeEnabled(boolean bl) {
            capeEnabled = bl;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

