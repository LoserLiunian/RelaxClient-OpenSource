/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.ui.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.IClassProvider;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiScreen;
import net.ccbluex.liquidbounce.api.minecraft.client.render.texture.ITextureManager;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.MiscUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J \u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/GuiBackground;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;)V", "enabledButton", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "particlesButton", "getPrevGui", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;", "shaderButton", "actionPerformed", "", "button", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "initGui", "keyTyped", "typedChar", "", "keyCode", "Companion", "Relaxed"})
public final class GuiBackground
extends WrappedGuiScreen {
    private IGuiButton enabledButton;
    private IGuiButton particlesButton;
    private IGuiButton shaderButton;
    @NotNull
    private final IGuiScreen prevGui;
    private static boolean enabled;
    private static boolean particles;
    private static boolean shader;
    public static final Companion Companion;

    @Override
    public void initGui() {
        this.enabledButton = MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() / 2 - 140, this.getRepresentedScreen().getHeight() / 4 + 35, "Enabled (" + (enabled ? "On" : "Off") + ')');
        List<IGuiButton> list = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton = this.enabledButton;
        if (iGuiButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("enabledButton");
        }
        list.add(iGuiButton);
        this.particlesButton = MinecraftInstance.classProvider.createGuiButton(2, this.getRepresentedScreen().getWidth() / 2 - 140, this.getRepresentedScreen().getHeight() / 4 + 50 + 25, "Particles (" + (particles ? "On" : "Off") + ')');
        List<IGuiButton> list2 = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton2 = this.particlesButton;
        if (iGuiButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("particlesButton");
        }
        list2.add(iGuiButton2);
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(3, this.getRepresentedScreen().getWidth() / 2 - 140, this.getRepresentedScreen().getHeight() / 4 + 50 + 50, 98, 20, "Change wallpaper"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(4, this.getRepresentedScreen().getWidth() / 2 + 2, this.getRepresentedScreen().getHeight() / 4 + 50 + 50, 98, 20, "Reset wallpaper"));
        this.shaderButton = MinecraftInstance.classProvider.createGuiButton(5, this.getRepresentedScreen().getWidth() / 2 - 140, this.getRepresentedScreen().getHeight() / 4 + 50 + 75, "Shader (" + (shader ? "Flux" : "Rain") + ')');
        List<IGuiButton> list3 = this.getRepresentedScreen().getButtonList();
        IGuiButton iGuiButton3 = this.shaderButton;
        if (iGuiButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shaderButton");
        }
        list3.add(iGuiButton3);
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(0, this.getRepresentedScreen().getWidth() / 2 - 140, this.getRepresentedScreen().getHeight() / 4 + 55 + 100 + 5, "Back"));
    }

    @Override
    public void actionPerformed(@NotNull IGuiButton button) {
        Intrinsics.checkParameterIsNotNull(button, "button");
        switch (button.getId()) {
            case 1: {
                enabled = !enabled;
                IGuiButton iGuiButton = this.enabledButton;
                if (iGuiButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("enabledButton");
                }
                iGuiButton.setDisplayString("Enabled (" + (enabled ? "On" : "Off") + ')');
                break;
            }
            case 2: {
                particles = !particles;
                IGuiButton iGuiButton = this.particlesButton;
                if (iGuiButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("particlesButton");
                }
                iGuiButton.setDisplayString("Particles (" + (particles ? "On" : "Off") + ')');
                break;
            }
            case 3: {
                File file = MiscUtils.openFileChooser();
                if (file == null) {
                    return;
                }
                File file2 = file;
                if (file2.isDirectory()) {
                    return;
                }
                try {
                    Files.copy(file2.toPath(), (OutputStream)new FileOutputStream(LiquidBounce.INSTANCE.getFileManager().backgroundFile));
                    BufferedImage image2 = ImageIO.read(new FileInputStream(LiquidBounce.INSTANCE.getFileManager().backgroundFile));
                    String string = "Relaxed";
                    StringBuilder stringBuilder = new StringBuilder();
                    IClassProvider iClassProvider = MinecraftInstance.classProvider;
                    boolean bl = false;
                    String string2 = string.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string2, "(this as java.lang.String).toLowerCase()");
                    String string3 = string2;
                    IResourceLocation location = iClassProvider.createResourceLocation(stringBuilder.append(string3).append("/background.png").toString());
                    LiquidBounce.INSTANCE.setBackground(location);
                    ITextureManager iTextureManager = MinecraftInstance.mc.getTextureManager();
                    BufferedImage bufferedImage = image2;
                    Intrinsics.checkExpressionValueIsNotNull(bufferedImage, "image");
                    iTextureManager.loadTexture(location, MinecraftInstance.classProvider.createDynamicTexture(bufferedImage));
                }
                catch (Exception e) {
                    e.printStackTrace();
                    MiscUtils.showErrorPopup("Error", "Exception class: " + e.getClass().getName() + "\nMessage: " + e.getMessage());
                    LiquidBounce.INSTANCE.getFileManager().backgroundFile.delete();
                }
                break;
            }
            case 4: {
                LiquidBounce.INSTANCE.setBackground(null);
                LiquidBounce.INSTANCE.getFileManager().backgroundFile.delete();
                break;
            }
            case 5: {
                shader = !shader;
                IGuiButton iGuiButton = this.shaderButton;
                if (iGuiButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("shaderButton");
                }
                iGuiButton.setDisplayString("Shader (" + (shader ? "Flux" : "Rain") + ')');
                break;
            }
            case 0: {
                MinecraftInstance.mc.displayGuiScreen(this.prevGui);
                break;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        Fonts.bold40.drawCenteredString("Background", (float)this.getRepresentedScreen().getWidth() / 2.0f, (float)this.getRepresentedScreen().getHeight() / 8.0f + 5.0f, 4673984, true);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (1 == keyCode) {
            MinecraftInstance.mc.displayGuiScreen(this.prevGui);
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @NotNull
    public final IGuiScreen getPrevGui() {
        return this.prevGui;
    }

    public GuiBackground(@NotNull IGuiScreen prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
    }

    static {
        Companion = new Companion(null);
        enabled = true;
        shader = true;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b\u00a8\u0006\u000f"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/GuiBackground$Companion;", "", "()V", "enabled", "", "getEnabled", "()Z", "setEnabled", "(Z)V", "particles", "getParticles", "setParticles", "shader", "getShader", "setShader", "Relaxed"})
    public static final class Companion {
        public final boolean getEnabled() {
            return enabled;
        }

        public final void setEnabled(boolean bl) {
            enabled = bl;
        }

        public final boolean getParticles() {
            return particles;
        }

        public final void setParticles(boolean bl) {
            particles = bl;
        }

        public final boolean getShader() {
            return shader;
        }

        public final void setShader(boolean bl) {
            shader = bl;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

