/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.input.Keyboard
 */
package net.ccbluex.liquidbounce.ui.client.altmanager.sub.altgenerator;

import com.thealtening.AltService;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiTextField;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.MiscUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.vitox.mcleaks.Callback;
import net.vitox.mcleaks.MCLeaks;
import net.vitox.mcleaks.RedeemResponse;
import net.vitox.mcleaks.Session;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J \u0010\u0018\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\b\u0010\u001a\u001a\u00020\nH\u0016J\b\u0010\u001b\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/altmanager/sub/altgenerator/GuiMCLeaks;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;", "(Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;)V", "status", "", "tokenField", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiTextField;", "actionPerformed", "", "button", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "keyTyped", "typedChar", "", "keyCode", "mouseClicked", "mouseButton", "onGuiClosed", "updateScreen", "Relaxed"})
public final class GuiMCLeaks
extends WrappedGuiScreen {
    private IGuiTextField tokenField;
    private String status;
    private final GuiAltManager prevGui;

    @Override
    public void updateScreen() {
        IGuiTextField iGuiTextField = this.tokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField.updateCursorCounter();
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        if (MCLeaks.isAltActive()) {
            StringBuilder stringBuilder = new StringBuilder().append("\u00a7aToken active. Using \u00a79");
            Session session = MCLeaks.getSession();
            Intrinsics.checkExpressionValueIsNotNull(session, "MCLeaks.getSession()");
            this.status = stringBuilder.append(session.getUsername()).append("\u00a7a to login!").toString();
        }
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 65, 200, 20, "Login"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(2, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() - 54, 98, 20, "Get Token"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(3, this.getRepresentedScreen().getWidth() / 2 + 2, this.getRepresentedScreen().getHeight() - 54, 98, 20, "Back"));
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        IGuiTextField iGuiTextField = this.tokenField = MinecraftInstance.classProvider.createGuiTextField(0, iFontRenderer, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 40, 200, 20);
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField.setFocused(true);
        IGuiTextField iGuiTextField2 = this.tokenField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField2.setMaxStringLength(16);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void actionPerformed(@NotNull IGuiButton button) {
        Intrinsics.checkParameterIsNotNull(button, "button");
        if (!button.getEnabled()) {
            return;
        }
        switch (button.getId()) {
            case 1: {
                IGuiTextField iGuiTextField = this.tokenField;
                if (iGuiTextField == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tokenField");
                }
                if (iGuiTextField.getText().length() != 16) {
                    this.status = "\u00a7cThe token has to be 16 characters long!";
                    return;
                }
                button.setEnabled(false);
                button.setDisplayString("Please wait ...");
                IGuiTextField iGuiTextField2 = this.tokenField;
                if (iGuiTextField2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tokenField");
                }
                MCLeaks.redeem(iGuiTextField2.getText(), new Callback<Object>(this, button){
                    final /* synthetic */ GuiMCLeaks this$0;
                    final /* synthetic */ IGuiButton $button;

                    public final void done(Object it) {
                        if (it instanceof String) {
                            GuiMCLeaks.access$setStatus$p(this.this$0, "\u00a7c" + it);
                            this.$button.setEnabled(true);
                            this.$button.setDisplayString("Login");
                            return;
                        }
                        Object object = it;
                        if (object == null) {
                            throw new TypeCastException("null cannot be cast to non-null type net.vitox.mcleaks.RedeemResponse");
                        }
                        RedeemResponse redeemResponse = (RedeemResponse)object;
                        MCLeaks.refresh(new Session(redeemResponse.getUsername(), redeemResponse.getToken()));
                        try {
                            GuiAltManager.altService.switchService(AltService.EnumAltService.MOJANG);
                        }
                        catch (Exception e) {
                            ClientUtils.getLogger().error("Failed to change alt service to Mojang.", (Throwable)e);
                        }
                        GuiMCLeaks.access$setStatus$p(this.this$0, "\u00a7aYour token was redeemed successfully!");
                        this.$button.setEnabled(true);
                        this.$button.setDisplayString("Login");
                        GuiMCLeaks.access$getPrevGui$p((GuiMCLeaks)this.this$0).status = GuiMCLeaks.access$getStatus$p(this.this$0);
                        MinecraftInstance.mc.displayGuiScreen(GuiMCLeaks.access$getPrevGui$p(this.this$0).getRepresentedScreen());
                    }
                    {
                        this.this$0 = guiMCLeaks;
                        this.$button = iGuiButton;
                    }
                });
                break;
            }
            case 2: {
                MiscUtils.showURL("https://mcleaks.net/");
                break;
            }
            case 3: {
                MinecraftInstance.mc.displayGuiScreen(this.prevGui.getRepresentedScreen());
                break;
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        switch (keyCode) {
            case 1: {
                MinecraftInstance.mc.displayGuiScreen(this.prevGui.getRepresentedScreen());
                break;
            }
            case 15: {
                IGuiTextField iGuiTextField = this.tokenField;
                if (iGuiTextField == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tokenField");
                }
                IGuiTextField iGuiTextField2 = this.tokenField;
                if (iGuiTextField2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tokenField");
                }
                iGuiTextField.setFocused(!iGuiTextField2.isFocused());
                break;
            }
            case 28: 
            case 156: {
                this.actionPerformed(this.getRepresentedScreen().getButtonList().get(1));
                break;
            }
            default: {
                IGuiTextField iGuiTextField = this.tokenField;
                if (iGuiTextField == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tokenField");
                }
                iGuiTextField.textboxKeyTyped(typedChar, keyCode);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        IGuiTextField iGuiTextField = this.tokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        RenderUtils.drawRect(30.0f, 30.0f, (float)this.getRepresentedScreen().getWidth() - 30.0f, (float)this.getRepresentedScreen().getHeight() - 30.0f, Integer.MIN_VALUE);
        Fonts.font40.drawCenteredString("MCLeaks", (float)this.getRepresentedScreen().getWidth() / 2.0f, 6.0f, 0xFFFFFF);
        Fonts.font40.drawString("Token:", (float)this.getRepresentedScreen().getWidth() / 2.0f - (float)100, (float)this.getRepresentedScreen().getHeight() / 4.0f + (float)30, 0xA0A0A0);
        String status = this.status;
        if (status != null) {
            Fonts.font40.drawCenteredString(status, (float)this.getRepresentedScreen().getWidth() / 2.0f, 18.0f, 0xFFFFFF);
        }
        IGuiTextField iGuiTextField = this.tokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public GuiMCLeaks(@NotNull GuiAltManager prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
    }

    public static final /* synthetic */ String access$getStatus$p(GuiMCLeaks $this) {
        return $this.status;
    }

    public static final /* synthetic */ void access$setStatus$p(GuiMCLeaks $this, String string) {
        $this.status = string;
    }

    public static final /* synthetic */ GuiAltManager access$getPrevGui$p(GuiMCLeaks $this) {
        return $this.prevGui;
    }
}

