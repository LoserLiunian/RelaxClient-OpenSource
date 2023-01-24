/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package net.ccbluex.liquidbounce.ui.client.altmanager.sub;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiScreen;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiTextField;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.ui.client.altmanager.GuiAltManager;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.TabUtils;
import net.ccbluex.liquidbounce.utils.login.MinecraftAccount;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import org.lwjgl.input.Keyboard;

public class GuiDirectLogin
extends WrappedGuiScreen {
    private final IGuiScreen prevGui;
    private IGuiButton loginButton;
    private IGuiButton clipboardLoginButton;
    private IGuiTextField username;
    private IGuiTextField password;
    private String status = "\u00a77Idle...";

    public GuiDirectLogin(GuiAltManager gui) {
        this.prevGui = gui.representedScreen;
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.loginButton = classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 72, "Login");
        this.getRepresentedScreen().getButtonList().add(this.loginButton);
        this.clipboardLoginButton = classProvider.createGuiButton(2, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 96, "Clipboard Login");
        this.getRepresentedScreen().getButtonList().add(this.clipboardLoginButton);
        this.getRepresentedScreen().getButtonList().add(classProvider.createGuiButton(0, this.getRepresentedScreen().getWidth() / 2 - 100, this.getRepresentedScreen().getHeight() / 4 + 120, "Back"));
        this.username = classProvider.createGuiTextField(2, Fonts.font40, this.getRepresentedScreen().getWidth() / 2 - 100, 60, 200, 20);
        this.username.setFocused(true);
        this.username.setMaxStringLength(Integer.MAX_VALUE);
        this.password = classProvider.createGuiPasswordField(3, Fonts.font40, this.getRepresentedScreen().getWidth() / 2 - 100, 85, 200, 20);
        this.password.setMaxStringLength(Integer.MAX_VALUE);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        RenderUtils.drawRect(30, 30, this.getRepresentedScreen().getWidth() - 30, this.getRepresentedScreen().getHeight() - 30, Integer.MIN_VALUE);
        Fonts.font40.drawCenteredString("Direct Login", (float)this.getRepresentedScreen().getWidth() / 2.0f, 34.0f, 0xFFFFFF);
        Fonts.font35.drawCenteredString(this.status == null ? "" : this.status, (float)this.getRepresentedScreen().getWidth() / 2.0f, (float)this.getRepresentedScreen().getHeight() / 4.0f + 60.0f, 0xFFFFFF);
        this.username.drawTextBox();
        this.password.drawTextBox();
        if (this.username.getText().isEmpty() && !this.username.isFocused()) {
            Fonts.font40.drawCenteredString("\u00a77Username / E-Mail", (float)this.getRepresentedScreen().getWidth() / 2.0f - 55.0f, 66.0f, 0xFFFFFF);
        }
        if (this.password.getText().isEmpty() && !this.password.isFocused()) {
            Fonts.font40.drawCenteredString("\u00a77Password", (float)this.getRepresentedScreen().getWidth() / 2.0f - 74.0f, 91.0f, 0xFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(IGuiButton button) throws IOException {
        if (!button.getEnabled()) {
            return;
        }
        switch (button.getId()) {
            case 0: {
                mc.displayGuiScreen(this.prevGui);
                break;
            }
            case 1: {
                if (this.username.getText().isEmpty()) {
                    this.status = "\u00a7cYou have to fill in both fields!";
                    return;
                }
                this.loginButton.setEnabled(false);
                this.clipboardLoginButton.setEnabled(false);
                new Thread(() -> {
                    this.status = "\u00a7aLogging in...";
                    this.status = this.password.getText().isEmpty() ? GuiAltManager.login(new MinecraftAccount(ColorUtils.translateAlternateColorCodes(this.username.getText()))) : GuiAltManager.login(new MinecraftAccount(this.username.getText(), this.password.getText()));
                    this.loginButton.setEnabled(true);
                    this.clipboardLoginButton.setEnabled(true);
                }).start();
                break;
            }
            case 2: {
                try {
                    String clipboardData = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                    String[] args2 = clipboardData.split(":", 2);
                    if (!clipboardData.contains(":") || args2.length != 2) {
                        this.status = "\u00a7cInvalid clipboard data. (Use: E-Mail:Password)";
                        return;
                    }
                    this.loginButton.setEnabled(false);
                    this.clipboardLoginButton.setEnabled(false);
                    new Thread(() -> {
                        this.status = "\u00a7aLogging in...";
                        this.status = GuiAltManager.login(new MinecraftAccount(args2[0], args2[1]));
                        this.loginButton.setEnabled(true);
                        this.clipboardLoginButton.setEnabled(true);
                    }).start();
                    break;
                }
                catch (UnsupportedFlavorException e) {
                    this.status = "\u00a7cClipboard flavor unsupported!";
                    ClientUtils.getLogger().error("Failed to read data from clipboard.", (Throwable)e);
                    break;
                }
                catch (IOException e) {
                    this.status = "\u00a7cUnknown error! (See log)";
                    ClientUtils.getLogger().error((Object)e);
                }
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        switch (keyCode) {
            case 1: {
                mc.displayGuiScreen(this.prevGui);
                return;
            }
            case 15: {
                TabUtils.tab(this.username, this.password);
                return;
            }
            case 28: {
                this.actionPerformed(this.loginButton);
                return;
            }
        }
        if (this.username.isFocused()) {
            this.username.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.password.isFocused()) {
            this.password.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.username.mouseClicked(mouseX, mouseY, mouseButton);
        this.password.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
        super.onGuiClosed();
    }
}

