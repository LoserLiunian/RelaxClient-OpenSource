/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.input.Keyboard
 */
package net.ccbluex.liquidbounce.ui.client.altmanager.sub;

import com.thealtening.AltService;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiTextField;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager;
import net.ccbluex.liquidbounce.ui.client.altmanager.sub.GuiSessionLogin$WhenMappings;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.login.LoginUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.vitox.mcleaks.MCLeaks;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J \u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J \u0010\u0019\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010\u001c\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/altmanager/sub/GuiSessionLogin;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;", "(Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;)V", "loginButton", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "sessionTokenField", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiTextField;", "status", "", "actionPerformed", "", "button", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "keyTyped", "typedChar", "", "keyCode", "mouseClicked", "mouseButton", "onGuiClosed", "updateScreen", "Relaxed"})
public final class GuiSessionLogin
extends WrappedGuiScreen {
    private IGuiButton loginButton;
    private IGuiTextField sessionTokenField;
    private String status;
    private final GuiAltManager prevGui;

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.loginButton = MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 96, "Login");
        List<IGuiButton> list = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton = this.loginButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loginButton");
        }
        list.add(iGuiButton);
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(0, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 120, "Back"));
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        IGuiTextField iGuiTextField = this.sessionTokenField = MinecraftInstance.classProvider.createGuiTextField(666, iFontRenderer, this.getRepresentedScreen().getWidth() / 2 - 100, 80, 200, 20);
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        iGuiTextField.setFocused(true);
        IGuiTextField iGuiTextField2 = this.sessionTokenField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        iGuiTextField2.setMaxStringLength(Integer.MAX_VALUE);
        if (this.sessionTokenField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        RenderUtils.drawRect(30.0f, 30.0f, (float)this.getRepresentedScreen().getWidth() - 30.0f, (float)this.getRepresentedScreen().getHeight() - 30.0f, Integer.MIN_VALUE);
        Fonts.font35.drawCenteredString("Session Login", (float)this.getRepresentedScreen().getWidth() / 2.0f, 36.0f, 0xFFFFFF);
        Fonts.font35.drawCenteredString(this.status, (float)this.getRepresentedScreen().getWidth() / 2.0f, (float)this.getRepresentedScreen().getHeight() / 4.0f + 80.0f, 0xFFFFFF);
        IGuiTextField iGuiTextField = this.sessionTokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        iGuiTextField.drawTextBox();
        Fonts.font40.drawCenteredString("\u00a77Session Token:", (float)this.getRepresentedScreen().getWidth() / 2.0f - 65.0f, 66.0f, 0xFFFFFF);
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
                IGuiButton iGuiButton = this.loginButton;
                if (iGuiButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("loginButton");
                }
                iGuiButton.setEnabled(false);
                this.status = "\u00a7aLogging in...";
                ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>(this){
                    final /* synthetic */ GuiSessionLogin this$0;

                    public final void invoke() {
                        String string;
                        LoginUtils.LoginResult loginResult = LoginUtils.loginSessionId(GuiSessionLogin.access$getSessionTokenField$p(this.this$0).getText());
                        GuiSessionLogin guiSessionLogin = this.this$0;
                        switch (GuiSessionLogin$WhenMappings.$EnumSwitchMapping$0[loginResult.ordinal()]) {
                            case 1: {
                                AltService altService = GuiAltManager.altService;
                                Intrinsics.checkExpressionValueIsNotNull(altService, "GuiAltManager.altService");
                                if (altService.getCurrentService() != AltService.EnumAltService.MOJANG) {
                                    GuiSessionLogin guiSessionLogin2 = guiSessionLogin;
                                    try {
                                        guiSessionLogin = guiSessionLogin2;
                                        GuiAltManager.altService.switchService(AltService.EnumAltService.MOJANG);
                                    }
                                    catch (NoSuchFieldException e) {
                                        guiSessionLogin = guiSessionLogin2;
                                        ClientUtils.getLogger().error("Something went wrong while trying to switch alt service.", (Throwable)e);
                                    }
                                    catch (IllegalAccessException e) {
                                        guiSessionLogin = guiSessionLogin2;
                                        ClientUtils.getLogger().error("Something went wrong while trying to switch alt service.", (Throwable)e);
                                    }
                                }
                                MCLeaks.remove();
                                string = "\u00a7cYour name is now \u00a7f\u00a7l" + MinecraftInstance.mc.getSession().getUsername() + "\u00a7c";
                                break;
                            }
                            case 2: {
                                string = "\u00a7cFailed to parse Session ID!";
                                break;
                            }
                            case 3: {
                                string = "\u00a7cInvalid Session ID!";
                                break;
                            }
                            default: {
                                string = "";
                            }
                        }
                        GuiSessionLogin.access$setStatus$p(guiSessionLogin, string);
                        GuiSessionLogin.access$getLoginButton$p(this.this$0).setEnabled(true);
                    }
                    {
                        this.this$0 = guiSessionLogin;
                        super(0);
                    }
                }, 31, null);
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
        IGuiTextField iGuiTextField = this.sessionTokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        if (iGuiTextField.isFocused()) {
            IGuiTextField iGuiTextField2 = this.sessionTokenField;
            if (iGuiTextField2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
            }
            iGuiTextField2.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        IGuiTextField iGuiTextField = this.sessionTokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        iGuiTextField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        IGuiTextField iGuiTextField = this.sessionTokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        iGuiTextField.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
        super.onGuiClosed();
    }

    public GuiSessionLogin(@NotNull GuiAltManager prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
        this.status = "";
    }

    public static final /* synthetic */ IGuiTextField access$getSessionTokenField$p(GuiSessionLogin $this) {
        IGuiTextField iGuiTextField = $this.sessionTokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionTokenField");
        }
        return iGuiTextField;
    }

    public static final /* synthetic */ void access$setSessionTokenField$p(GuiSessionLogin $this, IGuiTextField iGuiTextField) {
        $this.sessionTokenField = iGuiTextField;
    }

    public static final /* synthetic */ String access$getStatus$p(GuiSessionLogin $this) {
        return $this.status;
    }

    public static final /* synthetic */ void access$setStatus$p(GuiSessionLogin $this, String string) {
        $this.status = string;
    }

    public static final /* synthetic */ IGuiButton access$getLoginButton$p(GuiSessionLogin $this) {
        IGuiButton iGuiButton = $this.loginButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loginButton");
        }
        return iGuiButton;
    }

    public static final /* synthetic */ void access$setLoginButton$p(GuiSessionLogin $this, IGuiButton iGuiButton) {
        $this.loginButton = iGuiButton;
    }
}

