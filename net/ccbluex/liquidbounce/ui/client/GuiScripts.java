/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.ui.client;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.IMinecraft;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiButton;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IGuiScreen;
import net.ccbluex.liquidbounce.api.util.WrappedGuiScreen;
import net.ccbluex.liquidbounce.api.util.WrappedGuiSlot;
import net.ccbluex.liquidbounce.script.Script;
import net.ccbluex.liquidbounce.ui.client.clickgui.ClickGui;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.MiscUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0016R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/GuiScripts;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiScreen;", "prevGui", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;)V", "list", "Lnet/ccbluex/liquidbounce/ui/client/GuiScripts$GuiList;", "actionPerformed", "", "button", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiButton;", "drawScreen", "mouseX", "", "mouseY", "partialTicks", "", "handleMouseInput", "initGui", "keyTyped", "typedChar", "", "keyCode", "GuiList", "Relaxed"})
public final class GuiScripts
extends WrappedGuiScreen {
    private GuiList list;
    private final IGuiScreen prevGui;

    @Override
    public void initGui() {
        GuiList guiList = this.list = new GuiList(this.getRepresentedScreen());
        if (guiList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        }
        guiList.getRepresented().registerScrollButtons(7, 8);
        GuiList guiList2 = this.list;
        if (guiList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        }
        guiList2.elementClicked(-1, false, 0, 0);
        int j = 22;
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(0, this.getRepresentedScreen().getWidth() - 80, this.getRepresentedScreen().getHeight() - 65, 70, 20, "Back"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(1, this.getRepresentedScreen().getWidth() - 80, j + 24, 70, 20, "Import"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(2, this.getRepresentedScreen().getWidth() - 80, j + 48, 70, 20, "Delete"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(3, this.getRepresentedScreen().getWidth() - 80, j + 72, 70, 20, "Reload"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(4, this.getRepresentedScreen().getWidth() - 80, j + 96, 70, 20, "Folder"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(5, this.getRepresentedScreen().getWidth() - 80, j + 120, 70, 20, "Docs"));
        this.getRepresentedScreen().getButtonList().add(MinecraftInstance.classProvider.createGuiButton(6, this.getRepresentedScreen().getWidth() - 80, j + 144, 70, 20, "Find Scripts"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.getRepresentedScreen().drawBackground(0);
        GuiList guiList = this.list;
        if (guiList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        }
        guiList.getRepresented().drawScreen(mouseX, mouseY, partialTicks);
        Fonts.font40.drawCenteredString("\u00a79\u00a7lScripts", (float)this.getRepresentedScreen().getWidth() / 2.0f, 28.0f, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(@NotNull IGuiButton button) {
        Intrinsics.checkParameterIsNotNull(button, "button");
        switch (button.getId()) {
            case 0: {
                MinecraftInstance.mc.displayGuiScreen(this.prevGui);
                break;
            }
            case 1: {
                try {
                    String fileName;
                    File file = MiscUtils.openFileChooser();
                    if (file == null) {
                        return;
                    }
                    File file2 = file;
                    String string = fileName = file2.getName();
                    Intrinsics.checkExpressionValueIsNotNull(string, "fileName");
                    if (StringsKt.endsWith$default(string, ".js", false, 2, null)) {
                        LiquidBounce.INSTANCE.getScriptManager().importScript(file2);
                        LiquidBounce.INSTANCE.setClickGui(new ClickGui());
                        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().clickGuiConfig);
                        return;
                    }
                    if (StringsKt.endsWith$default(fileName, ".zip", false, 2, null)) {
                        ZipFile zipFile = new ZipFile(file2);
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        ArrayList<File> scriptFiles = new ArrayList<File>();
                        while (entries.hasMoreElements()) {
                            ZipEntry entry;
                            ZipEntry zipEntry = entry = entries.nextElement();
                            Intrinsics.checkExpressionValueIsNotNull(zipEntry, "entry");
                            String entryName = zipEntry.getName();
                            File entryFile = new File(LiquidBounce.INSTANCE.getScriptManager().getScriptsFolder(), entryName);
                            if (entry.isDirectory()) {
                                entryFile.mkdir();
                                continue;
                            }
                            InputStream fileStream = zipFile.getInputStream(entry);
                            FileOutputStream fileOutputStream = new FileOutputStream(entryFile);
                            IOUtils.copy((InputStream)fileStream, (OutputStream)fileOutputStream);
                            fileOutputStream.close();
                            fileStream.close();
                            String string2 = entryName;
                            Intrinsics.checkExpressionValueIsNotNull(string2, "entryName");
                            if (StringsKt.contains$default((CharSequence)string2, "/", false, 2, null)) continue;
                            scriptFiles.add(entryFile);
                        }
                        Iterable $this$forEach$iv = scriptFiles;
                        boolean $i$f$forEach = false;
                        for (Object element$iv : $this$forEach$iv) {
                            File scriptFile = (File)element$iv;
                            boolean bl = false;
                            LiquidBounce.INSTANCE.getScriptManager().loadScript(scriptFile);
                        }
                        LiquidBounce.INSTANCE.setClickGui(new ClickGui());
                        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().clickGuiConfig);
                        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().hudConfig);
                        return;
                    }
                    MiscUtils.showErrorPopup("Wrong file extension.", "The file extension has to be .js or .zip");
                }
                catch (Throwable t) {
                    ClientUtils.getLogger().error("Something went wrong while importing a script.", t);
                    MiscUtils.showErrorPopup(t.getClass().getName(), t.getMessage());
                }
                break;
            }
            case 2: {
                try {
                    GuiList guiList = this.list;
                    if (guiList == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("list");
                    }
                    if (guiList.getSelectedSlot$Relaxed() == -1) break;
                    List<Script> list = LiquidBounce.INSTANCE.getScriptManager().getScripts();
                    GuiList guiList2 = this.list;
                    if (guiList2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("list");
                    }
                    Script script = list.get(guiList2.getSelectedSlot$Relaxed());
                    LiquidBounce.INSTANCE.getScriptManager().deleteScript(script);
                    LiquidBounce.INSTANCE.setClickGui(new ClickGui());
                    LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().clickGuiConfig);
                    LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().hudConfig);
                }
                catch (Throwable t) {
                    ClientUtils.getLogger().error("Something went wrong while deleting a script.", t);
                    MiscUtils.showErrorPopup(t.getClass().getName(), t.getMessage());
                }
                break;
            }
            case 3: {
                try {
                    LiquidBounce.INSTANCE.getScriptManager().reloadScripts();
                }
                catch (Throwable t) {
                    ClientUtils.getLogger().error("Something went wrong while reloading all scripts.", t);
                    MiscUtils.showErrorPopup(t.getClass().getName(), t.getMessage());
                }
                break;
            }
            case 4: {
                try {
                    Desktop.getDesktop().open(LiquidBounce.INSTANCE.getScriptManager().getScriptsFolder());
                }
                catch (Throwable t) {
                    ClientUtils.getLogger().error("Something went wrong while trying to open your scripts folder.", t);
                    MiscUtils.showErrorPopup(t.getClass().getName(), t.getMessage());
                }
                break;
            }
            case 5: {
                try {
                    Desktop.getDesktop().browse(new URL("https://liquidbounce.net/docs/ScriptAPI/Getting%20Started").toURI());
                }
                catch (Exception exception) {}
                break;
            }
            case 6: {
                try {
                    Desktop.getDesktop().browse(new URL("https://forum.ccbluex.net/viewforum.php?id=16").toURI());
                }
                catch (Exception exception) {}
                break;
            }
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

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        GuiList guiList = this.list;
        if (guiList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("list");
        }
        guiList.getRepresented().handleMouseInput();
    }

    public GuiScripts(@NotNull IGuiScreen prevGui) {
        Intrinsics.checkParameterIsNotNull(prevGui, "prevGui");
        this.prevGui = prevGui;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J8\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J(\u0010\u0010\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\r\u0010\u0014\u001a\u00020\u0006H\u0000\u00a2\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/GuiScripts$GuiList;", "Lnet/ccbluex/liquidbounce/api/util/WrappedGuiSlot;", "gui", "Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;", "(Lnet/ccbluex/liquidbounce/ui/client/GuiScripts;Lnet/ccbluex/liquidbounce/api/minecraft/client/gui/IGuiScreen;)V", "selectedSlot", "", "drawBackground", "", "drawSlot", "id", "x", "y", "var4", "var5", "var6", "elementClicked", "doubleClick", "", "var3", "getSelectedSlot", "getSelectedSlot$Relaxed", "getSize", "isSelected", "Relaxed"})
    private final class GuiList
    extends WrappedGuiSlot {
        private int selectedSlot;

        @Override
        public boolean isSelected(int id) {
            return this.selectedSlot == id;
        }

        public final int getSelectedSlot$Relaxed() {
            return this.selectedSlot > LiquidBounce.INSTANCE.getScriptManager().getScripts().size() ? -1 : this.selectedSlot;
        }

        @Override
        public int getSize() {
            return LiquidBounce.INSTANCE.getScriptManager().getScripts().size();
        }

        @Override
        public void elementClicked(int id, boolean doubleClick, int var3, int var4) {
            this.selectedSlot = id;
        }

        @Override
        public void drawSlot(int id, int x, int y, int var4, int var5, int var6) {
            Script script = LiquidBounce.INSTANCE.getScriptManager().getScripts().get(id);
            String string = "\u00a79" + script.getScriptName() + " \u00a77v" + script.getScriptVersion();
            float f = (float)GuiScripts.this.getRepresentedScreen().getWidth() / 2.0f;
            float f2 = (float)y + 2.0f;
            Color color = Color.LIGHT_GRAY;
            Intrinsics.checkExpressionValueIsNotNull(color, "Color.LIGHT_GRAY");
            Fonts.font40.drawCenteredString(string, f, f2, color.getRGB());
            String string2 = "by \u00a7c" + ArraysKt.joinToString$default(script.getScriptAuthors(), (CharSequence)", ", null, null, 0, null, null, 62, null);
            float f3 = (float)GuiScripts.this.getRepresentedScreen().getWidth() / 2.0f;
            float f4 = (float)y + 15.0f;
            Color color2 = Color.LIGHT_GRAY;
            Intrinsics.checkExpressionValueIsNotNull(color2, "Color.LIGHT_GRAY");
            Fonts.font40.drawCenteredString(string2, f3, f4, color2.getRGB());
        }

        @Override
        public void drawBackground() {
        }

        public GuiList(IGuiScreen gui) {
            Intrinsics.checkParameterIsNotNull(gui, "gui");
            IMinecraft iMinecraft = MinecraftInstance.mc;
            Intrinsics.checkExpressionValueIsNotNull(iMinecraft, "mc");
            super(iMinecraft, gui.getWidth(), gui.getHeight(), 40, gui.getHeight() - 40, 30);
        }
    }
}

