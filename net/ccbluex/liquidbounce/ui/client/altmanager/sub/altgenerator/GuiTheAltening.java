/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.input.Keyboard
 */
package net.ccbluex.liquidbounce.ui.client.altmanager.sub.altgenerator;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.thealtening.AltService;
import com.thealtening.api.TheAltening;
import com.thealtening.api.data.AccountData;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiTextField;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.event.SessionEvent;
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.MiscUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.vitox.mcleaks.MCLeaks;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J \u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J \u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0012H\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2={"Lnet/ccbluex/liquidbounce/ui/client/altmanager/sub/altgenerator/GuiTheAltening;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;", "(Lnet/ccbluex/liquidbounce/ui/client/altmanager/GuiAltManager;)V", "apiKeyField", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiTextField;", "generateButton", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "loginButton", "status", "", "tokenField", "actionPerformed", "", "button", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "keyTyped", "typedChar", "", "keyCode", "mouseClicked", "mouseButton", "onGuiClosed", "updateScreen", "Companion", "Relaxed"})
public final class GuiTheAltening
extends WrappedGuiScreen {
    private IGuiButton loginButton;
    private IGuiButton generateButton;
    private IGuiTextField apiKeyField;
    private IGuiTextField tokenField;
    private String status;
    private final GuiAltManager prevGui;
    @NotNull
    private static String apiKey;
    public static final Companion Companion;

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.loginButton = MinecraftInstance.classProvider.createGuiButton(2, this.getRepresentedScreen().getWidth() / 2 - 100, 75, "Login");
        List<IGuiButton> list = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton = this.loginButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loginButton");
        }
        list.add(iGuiButton);
        this.generateButton = MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 100, 140, "Generate");
        List<IGuiButton> list2 = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton2 = this.generateButton;
        if (iGuiButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generateButton");
        }
        list2.add(iGuiButton2);
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(3, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() - 54, 98, 20, "Buy"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(0, this.getRepresentedScreen().getWidth() / 2 + 2, this.getRepresentedScreen().getHeight() - 54, 98, 20, "Back"));
        IFontRenderer iFontRenderer = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.font40");
        IGuiTextField iGuiTextField = this.tokenField = MinecraftInstance.classProvider.createGuiTextField(666, iFontRenderer, this.getRepresentedScreen().getWidth() / 2 - 100, 50, 200, 20);
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField.setFocused(true);
        IGuiTextField iGuiTextField2 = this.tokenField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField2.setMaxStringLength(Integer.MAX_VALUE);
        IFontRenderer iFontRenderer2 = Fonts.font40;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer2, "Fonts.font40");
        IGuiTextField iGuiTextField3 = this.apiKeyField = MinecraftInstance.classProvider.createGuiPasswordField(1337, iFontRenderer2, this.getRepresentedScreen().getWidth() / 2 - 100, 115, 200, 20);
        if (iGuiTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        iGuiTextField3.setMaxStringLength(18);
        IGuiTextField iGuiTextField4 = this.apiKeyField;
        if (iGuiTextField4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        iGuiTextField4.setText(apiKey);
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        RenderUtils.drawRect(30.0f, 30.0f, (float)this.getRepresentedScreen().getWidth() - 30.0f, (float)this.getRepresentedScreen().getHeight() - 30.0f, Integer.MIN_VALUE);
        Fonts.font35.drawCenteredString("TheAltening", (float)this.getRepresentedScreen().getWidth() / 2.0f, 6.0f, 0xFFFFFF);
        Fonts.font35.drawCenteredString(this.status, (float)this.getRepresentedScreen().getWidth() / 2.0f, 18.0f, 0xFFFFFF);
        IGuiTextField iGuiTextField = this.apiKeyField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        iGuiTextField.drawTextBox();
        IGuiTextField iGuiTextField2 = this.tokenField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField2.drawTextBox();
        Fonts.font40.drawCenteredString("\u00a77Token:", (float)this.getRepresentedScreen().getWidth() / 2.0f - (float)84, 40.0f, 0xFFFFFF);
        Fonts.font40.drawCenteredString("\u00a77API-Key:", (float)this.getRepresentedScreen().getWidth() / 2.0f - (float)78, 105.0f, 0xFFFFFF);
        Fonts.font40.drawCenteredString("\u00a77Use coupon code 'liquidbounce' for 20% off!", (float)this.getRepresentedScreen().getWidth() / 2.0f, (float)this.getRepresentedScreen().getHeight() - 65.0f, 0xFFFFFF);
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
                IGuiButton iGuiButton2 = this.generateButton;
                if (iGuiButton2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("generateButton");
                }
                iGuiButton2.setEnabled(false);
                IGuiTextField iGuiTextField = this.apiKeyField;
                if (iGuiTextField == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
                }
                apiKey = iGuiTextField.getText();
                TheAltening altening = new TheAltening(apiKey);
                TheAltening.Asynchronous asynchronous = new TheAltening.Asynchronous(altening);
                this.status = "\u00a7cGenerating account...";
                ((CompletableFuture)((CompletableFuture)asynchronous.getAccountData().thenAccept((Consumer)new Consumer<AccountData>(this){
                    final /* synthetic */ GuiTheAltening this$0;

                    /*
                     * WARNING - void declaration
                     */
                    public final void accept(AccountData account) {
                        StringBuilder stringBuilder = new StringBuilder().append("\u00a7aGenerated account: \u00a7b\u00a7l");
                        AccountData accountData = account;
                        Intrinsics.checkExpressionValueIsNotNull(accountData, "account");
                        GuiTheAltening.access$setStatus$p(this.this$0, stringBuilder.append(accountData.getUsername()).toString());
                        try {
                            String string;
                            GuiTheAltening guiTheAltening;
                            GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cSwitching Alt Service...");
                            GuiAltManager.altService.switchService(AltService.EnumAltService.THEALTENING);
                            GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cLogging in...");
                            YggdrasilUserAuthentication yggdrasilUserAuthentication = new YggdrasilUserAuthentication(new YggdrasilAuthenticationService(Proxy.NO_PROXY, ""), Agent.MINECRAFT);
                            yggdrasilUserAuthentication.setUsername(account.getToken());
                            yggdrasilUserAuthentication.setPassword("Relaxed");
                            GuiTheAltening guiTheAltening2 = this.this$0;
                            try {
                                guiTheAltening = guiTheAltening2;
                                yggdrasilUserAuthentication.logIn();
                                GameProfile gameProfile = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile, "yggdrasilUserAuthentication.selectedProfile");
                                String string2 = gameProfile.getName();
                                Intrinsics.checkExpressionValueIsNotNull(string2, "yggdrasilUserAuthentication.selectedProfile.name");
                                GameProfile gameProfile2 = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile2, "yggdrasilUserAuthenticat\u2026         .selectedProfile");
                                String string3 = gameProfile2.getId().toString();
                                Intrinsics.checkExpressionValueIsNotNull(string3, "yggdrasilUserAuthenticat\u2026ctedProfile.id.toString()");
                                String string4 = yggdrasilUserAuthentication.getAuthenticatedToken();
                                Intrinsics.checkExpressionValueIsNotNull(string4, "yggdrasilUserAuthentication.authenticatedToken");
                                MinecraftInstance.mc.setSession(MinecraftInstance.classProvider.createSession(string2, string3, string4, "mojang"));
                                LiquidBounce.INSTANCE.getEventManager().callEvent(new SessionEvent());
                                MCLeaks.remove();
                                GuiAltManager guiAltManager = GuiTheAltening.access$getPrevGui$p(this.this$0);
                                StringBuilder stringBuilder2 = new StringBuilder().append("\u00a7aYour name is now \u00a7b\u00a7l");
                                GameProfile gameProfile3 = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile3, "yggdrasilUserAuthentication.selectedProfile");
                                guiAltManager.status = stringBuilder2.append(gameProfile3.getName()).append("\u00a7c.").toString();
                                MinecraftInstance.mc.displayGuiScreen(GuiTheAltening.access$getPrevGui$p(this.this$0).getRepresentedScreen());
                                string = "";
                            }
                            catch (AuthenticationException authenticationException) {
                                void e;
                                guiTheAltening = guiTheAltening2;
                                GuiAltManager.altService.switchService(AltService.EnumAltService.MOJANG);
                                ClientUtils.getLogger().error("Failed to login.", (Throwable)e);
                                string = "\u00a7cFailed to login: " + e.getMessage();
                            }
                            GuiTheAltening.access$setStatus$p(guiTheAltening, string);
                        }
                        catch (Throwable throwable) {
                            GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cFailed to login. Unknown error.");
                            ClientUtils.getLogger().error("Failed to login.", throwable);
                        }
                        GuiTheAltening.access$getLoginButton$p(this.this$0).setEnabled(true);
                        GuiTheAltening.access$getGenerateButton$p(this.this$0).setEnabled(true);
                    }
                    {
                        this.this$0 = guiTheAltening;
                    }
                })).handle(new BiFunction<T, Throwable, U>(this){
                    final /* synthetic */ GuiTheAltening this$0;

                    public final void apply(Void $noName_0, Throwable err) {
                        GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cFailed to generate account.");
                        ClientUtils.getLogger().error("Failed to generate account.", err);
                    }
                    {
                        this.this$0 = guiTheAltening;
                    }
                })).whenComplete(new BiConsumer<Unit, Throwable>(this){
                    final /* synthetic */ GuiTheAltening this$0;

                    public final void accept(Unit $noName_0, Throwable $noName_1) {
                        GuiTheAltening.access$getLoginButton$p(this.this$0).setEnabled(true);
                        GuiTheAltening.access$getGenerateButton$p(this.this$0).setEnabled(true);
                    }
                    {
                        this.this$0 = guiTheAltening;
                    }
                });
                break;
            }
            case 2: {
                IGuiButton iGuiButton = this.loginButton;
                if (iGuiButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("loginButton");
                }
                iGuiButton.setEnabled(false);
                IGuiButton iGuiButton3 = this.generateButton;
                if (iGuiButton3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("generateButton");
                }
                iGuiButton3.setEnabled(false);
                new Thread(new Runnable(this){
                    final /* synthetic */ GuiTheAltening this$0;

                    /*
                     * WARNING - void declaration
                     */
                    public final void run() {
                        try {
                            String string;
                            GuiTheAltening guiTheAltening;
                            GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cSwitching Alt Service...");
                            GuiAltManager.altService.switchService(AltService.EnumAltService.THEALTENING);
                            GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cLogging in...");
                            YggdrasilUserAuthentication yggdrasilUserAuthentication = new YggdrasilUserAuthentication(new YggdrasilAuthenticationService(Proxy.NO_PROXY, ""), Agent.MINECRAFT);
                            yggdrasilUserAuthentication.setUsername(GuiTheAltening.access$getTokenField$p(this.this$0).getText());
                            yggdrasilUserAuthentication.setPassword("Relaxed");
                            GuiTheAltening guiTheAltening2 = this.this$0;
                            try {
                                guiTheAltening = guiTheAltening2;
                                yggdrasilUserAuthentication.logIn();
                                GameProfile gameProfile = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile, "yggdrasilUserAuthentication.selectedProfile");
                                String string2 = gameProfile.getName();
                                Intrinsics.checkExpressionValueIsNotNull(string2, "yggdrasilUserAuthentication.selectedProfile.name");
                                GameProfile gameProfile2 = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile2, "yggdrasilUserAuthenticat\u2026         .selectedProfile");
                                String string3 = gameProfile2.getId().toString();
                                Intrinsics.checkExpressionValueIsNotNull(string3, "yggdrasilUserAuthenticat\u2026ctedProfile.id.toString()");
                                String string4 = yggdrasilUserAuthentication.getAuthenticatedToken();
                                Intrinsics.checkExpressionValueIsNotNull(string4, "yggdrasilUserAuthentication.authenticatedToken");
                                MinecraftInstance.mc.setSession(MinecraftInstance.classProvider.createSession(string2, string3, string4, "mojang"));
                                LiquidBounce.INSTANCE.getEventManager().callEvent(new SessionEvent());
                                MCLeaks.remove();
                                GuiAltManager guiAltManager = GuiTheAltening.access$getPrevGui$p(this.this$0);
                                StringBuilder stringBuilder = new StringBuilder().append("\u00a7aYour name is now \u00a7b\u00a7l");
                                GameProfile gameProfile3 = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile3, "yggdrasilUserAuthentication.selectedProfile");
                                guiAltManager.status = stringBuilder.append(gameProfile3.getName()).append("\u00a7c.").toString();
                                MinecraftInstance.mc.displayGuiScreen(GuiTheAltening.access$getPrevGui$p(this.this$0).getRepresentedScreen());
                                StringBuilder stringBuilder2 = new StringBuilder().append("\u00a7aYour name is now \u00a7b\u00a7l");
                                GameProfile gameProfile4 = yggdrasilUserAuthentication.getSelectedProfile();
                                Intrinsics.checkExpressionValueIsNotNull(gameProfile4, "yggdrasilUserAuthentication.selectedProfile");
                                string = stringBuilder2.append(gameProfile4.getName()).append("\u00a7c.").toString();
                            }
                            catch (AuthenticationException authenticationException) {
                                void e;
                                guiTheAltening = guiTheAltening2;
                                GuiAltManager.altService.switchService(AltService.EnumAltService.MOJANG);
                                ClientUtils.getLogger().error("Failed to login.", (Throwable)e);
                                string = "\u00a7cFailed to login: " + e.getMessage();
                            }
                            GuiTheAltening.access$setStatus$p(guiTheAltening, string);
                        }
                        catch (Throwable throwable) {
                            ClientUtils.getLogger().error("Failed to login.", throwable);
                            GuiTheAltening.access$setStatus$p(this.this$0, "\u00a7cFailed to login. Unknown error.");
                        }
                        GuiTheAltening.access$getLoginButton$p(this.this$0).setEnabled(true);
                        GuiTheAltening.access$getGenerateButton$p(this.this$0).setEnabled(true);
                    }
                    {
                        this.this$0 = guiTheAltening;
                    }
                }).start();
                break;
            }
            case 3: {
                MiscUtils.showURL("https://thealtening.com/?ref=liquidbounce");
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
        IGuiTextField iGuiTextField = this.apiKeyField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        if (iGuiTextField.isFocused()) {
            IGuiTextField iGuiTextField2 = this.apiKeyField;
            if (iGuiTextField2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
            }
            iGuiTextField2.textboxKeyTyped(typedChar, keyCode);
        }
        IGuiTextField iGuiTextField3 = this.tokenField;
        if (iGuiTextField3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        if (iGuiTextField3.isFocused()) {
            IGuiTextField iGuiTextField4 = this.tokenField;
            if (iGuiTextField4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tokenField");
            }
            iGuiTextField4.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        IGuiTextField iGuiTextField = this.apiKeyField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        iGuiTextField.mouseClicked(mouseX, mouseY, mouseButton);
        IGuiTextField iGuiTextField2 = this.tokenField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField2.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        IGuiTextField iGuiTextField = this.apiKeyField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        iGuiTextField.updateCursorCounter();
        IGuiTextField iGuiTextField2 = this.tokenField;
        if (iGuiTextField2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        iGuiTextField2.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
        IGuiTextField iGuiTextField = this.apiKeyField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("apiKeyField");
        }
        apiKey = iGuiTextField.getText();
        super.onGuiClosed();
    }

    public GuiTheAltening(@NotNull GuiAltManager prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
        this.status = "";
    }

    static {
        Companion = new Companion(null);
        apiKey = "";
    }

    public static final /* synthetic */ String access$getStatus$p(GuiTheAltening $this) {
        return $this.status;
    }

    public static final /* synthetic */ void access$setStatus$p(GuiTheAltening $this, String string) {
        $this.status = string;
    }

    public static final /* synthetic */ GuiAltManager access$getPrevGui$p(GuiTheAltening $this) {
        return $this.prevGui;
    }

    public static final /* synthetic */ IGuiButton access$getLoginButton$p(GuiTheAltening $this) {
        IGuiButton iGuiButton = $this.loginButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loginButton");
        }
        return iGuiButton;
    }

    public static final /* synthetic */ void access$setLoginButton$p(GuiTheAltening $this, IGuiButton iGuiButton) {
        $this.loginButton = iGuiButton;
    }

    public static final /* synthetic */ IGuiButton access$getGenerateButton$p(GuiTheAltening $this) {
        IGuiButton iGuiButton = $this.generateButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generateButton");
        }
        return iGuiButton;
    }

    public static final /* synthetic */ void access$setGenerateButton$p(GuiTheAltening $this, IGuiButton iGuiButton) {
        $this.generateButton = iGuiButton;
    }

    public static final /* synthetic */ IGuiTextField access$getTokenField$p(GuiTheAltening $this) {
        IGuiTextField iGuiTextField = $this.tokenField;
        if (iGuiTextField == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tokenField");
        }
        return iGuiTextField;
    }

    public static final /* synthetic */ void access$setTokenField$p(GuiTheAltening $this, IGuiTextField iGuiTextField) {
        $this.tokenField = iGuiTextField;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/altmanager/sub/altgenerator/GuiTheAltening$Companion;", "", "()V", "apiKey", "", "getApiKey", "()Ljava/lang/String;", "setApiKey", "(Ljava/lang/String;)V", "Relaxed"})
    public static final class Companion {
        @NotNull
        public final String getApiKey() {
            return apiKey;
        }

        public final void setApiKey(@NotNull String string) {
            Intrinsics.checkParameterIsNotNull(string, "<set-?>");
            apiKey = string;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

