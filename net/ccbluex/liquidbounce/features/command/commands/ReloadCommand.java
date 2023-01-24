/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.features.command.commands;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.command.Command;
import net.ccbluex.liquidbounce.features.command.CommandManager;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleManager;
import net.ccbluex.liquidbounce.ui.client.clickgui.ClickGui;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2={"Lnet/ccbluex/liquidbounce/features/command/commands/ReloadCommand;", "Lnet/ccbluex/liquidbounce/features/command/Command;", "()V", "execute", "", "args", "", "", "([Ljava/lang/String;)V", "Relaxed"})
public final class ReloadCommand
extends Command {
    @Override
    public void execute(@NotNull String[] args2) {
        Intrinsics.checkParameterIsNotNull(args2, "args");
        this.chat("Reloading...");
        this.chat("\u00a7c\u00a7lReloading commands...");
        LiquidBounce.INSTANCE.setCommandManager(new CommandManager());
        LiquidBounce.INSTANCE.getCommandManager().registerCommands();
        LiquidBounce.INSTANCE.setStarting(true);
        LiquidBounce.INSTANCE.getScriptManager().disableScripts();
        LiquidBounce.INSTANCE.getScriptManager().unloadScripts();
        for (Module module : LiquidBounce.INSTANCE.getModuleManager().getModules()) {
            ModuleManager moduleManager = LiquidBounce.INSTANCE.getModuleManager();
            Module module2 = module;
            Intrinsics.checkExpressionValueIsNotNull(module2, "module");
            moduleManager.generateCommand$Relaxed(module2);
        }
        this.chat("\u00a7c\u00a7lReloading scripts...");
        LiquidBounce.INSTANCE.getScriptManager().reloadScripts();
        this.chat("\u00a7c\u00a7lReloading fonts...");
        Fonts.loadFonts();
        this.chat("\u00a7c\u00a7lReloading modules...");
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().modulesConfig);
        LiquidBounce.INSTANCE.setStarting(false);
        this.chat("\u00a7c\u00a7lReloading values...");
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().valuesConfig);
        this.chat("\u00a7c\u00a7lReloading accounts...");
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().accountsConfig);
        this.chat("\u00a7c\u00a7lReloading friends...");
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().friendsConfig);
        this.chat("\u00a7c\u00a7lReloading xray...");
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().xrayConfig);
        this.chat("\u00a7c\u00a7lReloading HUD...");
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().hudConfig);
        this.chat("\u00a7c\u00a7lReloading ClickGUI...");
        LiquidBounce.INSTANCE.setClickGui(new ClickGui());
        LiquidBounce.INSTANCE.getFileManager().loadConfig(LiquidBounce.INSTANCE.getFileManager().clickGuiConfig);
        LiquidBounce.INSTANCE.setStarting(false);
        this.chat("Reloaded.");
    }

    public ReloadCommand() {
        super("reload", "configreload");
    }
}

