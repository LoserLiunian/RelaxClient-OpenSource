/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.manager.CombatManager;
import me.utils.AnimationHandler;
import native0.Loader;
import net.ccbluex.liquidbounce.api.Wrapper;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.event.ClientShutdownEvent;
import net.ccbluex.liquidbounce.event.EventManager;
import net.ccbluex.liquidbounce.features.command.CommandManager;
import net.ccbluex.liquidbounce.features.module.ModuleManager;
import net.ccbluex.liquidbounce.file.FileManager;
import net.ccbluex.liquidbounce.script.ScriptManager;
import net.ccbluex.liquidbounce.ui.client.clickgui.ClickGui;
import net.ccbluex.liquidbounce.ui.client.hud.HUD;
import net.ccbluex.liquidbounce.utils.misc.sound.TipSoundManager;
import oh.yalan.NativeMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010W\u001a\u00020XH\u0007J\u0006\u0010Y\u001a\u00020XR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u000205X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020;X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020@X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001a\u0010E\u001a\u00020FX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010K\u001a\u00020LX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010Q\u001a\u00020RX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010V\u00a8\u0006Z"}, d2={"Lnet/ccbluex/liquidbounce/LiquidBounce;", "", "()V", "CLIENT_CLOUD", "", "CLIENT_CREATOR", "CLIENT_NAME", "CLIENT_VERSION", "", "MINECRAFT_VERSION", "animationHandler", "Lme/utils/AnimationHandler;", "getAnimationHandler", "()Lme/utils/AnimationHandler;", "setAnimationHandler", "(Lme/utils/AnimationHandler;)V", "background", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IResourceLocation;", "getBackground", "()Lnet/ccbluex/liquidbounce/api/minecraft/util/IResourceLocation;", "setBackground", "(Lnet/ccbluex/liquidbounce/api/minecraft/util/IResourceLocation;)V", "clickGui", "Lnet/ccbluex/liquidbounce/ui/client/clickgui/ClickGui;", "getClickGui", "()Lnet/ccbluex/liquidbounce/ui/client/clickgui/ClickGui;", "setClickGui", "(Lnet/ccbluex/liquidbounce/ui/client/clickgui/ClickGui;)V", "combatManager", "Lme/manager/CombatManager;", "getCombatManager", "()Lme/manager/CombatManager;", "setCombatManager", "(Lme/manager/CombatManager;)V", "commandManager", "Lnet/ccbluex/liquidbounce/features/command/CommandManager;", "getCommandManager", "()Lnet/ccbluex/liquidbounce/features/command/CommandManager;", "setCommandManager", "(Lnet/ccbluex/liquidbounce/features/command/CommandManager;)V", "eventManager", "Lnet/ccbluex/liquidbounce/event/EventManager;", "getEventManager", "()Lnet/ccbluex/liquidbounce/event/EventManager;", "setEventManager", "(Lnet/ccbluex/liquidbounce/event/EventManager;)V", "fileManager", "Lnet/ccbluex/liquidbounce/file/FileManager;", "getFileManager", "()Lnet/ccbluex/liquidbounce/file/FileManager;", "setFileManager", "(Lnet/ccbluex/liquidbounce/file/FileManager;)V", "hud", "Lnet/ccbluex/liquidbounce/ui/client/hud/HUD;", "getHud", "()Lnet/ccbluex/liquidbounce/ui/client/hud/HUD;", "setHud", "(Lnet/ccbluex/liquidbounce/ui/client/hud/HUD;)V", "isStarting", "", "()Z", "setStarting", "(Z)V", "moduleManager", "Lnet/ccbluex/liquidbounce/features/module/ModuleManager;", "getModuleManager", "()Lnet/ccbluex/liquidbounce/features/module/ModuleManager;", "setModuleManager", "(Lnet/ccbluex/liquidbounce/features/module/ModuleManager;)V", "scriptManager", "Lnet/ccbluex/liquidbounce/script/ScriptManager;", "getScriptManager", "()Lnet/ccbluex/liquidbounce/script/ScriptManager;", "setScriptManager", "(Lnet/ccbluex/liquidbounce/script/ScriptManager;)V", "tipSoundManager", "Lnet/ccbluex/liquidbounce/utils/misc/sound/TipSoundManager;", "getTipSoundManager", "()Lnet/ccbluex/liquidbounce/utils/misc/sound/TipSoundManager;", "setTipSoundManager", "(Lnet/ccbluex/liquidbounce/utils/misc/sound/TipSoundManager;)V", "wrapper", "Lnet/ccbluex/liquidbounce/api/Wrapper;", "getWrapper", "()Lnet/ccbluex/liquidbounce/api/Wrapper;", "setWrapper", "(Lnet/ccbluex/liquidbounce/api/Wrapper;)V", "startClient", "", "stopClient", "Relaxed"})
public final class LiquidBounce {
    @NotNull
    public static final String CLIENT_NAME = "Relaxed";
    public static final int CLIENT_VERSION = 1;
    @NotNull
    public static final String CLIENT_CREATOR = "CCBlueX, P1ayerLk_";
    @NotNull
    public static final String MINECRAFT_VERSION = "1.12.2";
    @NotNull
    public static final String CLIENT_CLOUD = "https://cloud.liquidbounce.net/LiquidBounce";
    private static boolean isStarting;
    @NotNull
    public static ModuleManager moduleManager;
    @NotNull
    public static CommandManager commandManager;
    @NotNull
    public static EventManager eventManager;
    @NotNull
    public static FileManager fileManager;
    @NotNull
    public static ScriptManager scriptManager;
    @NotNull
    public static CombatManager combatManager;
    @NotNull
    public static TipSoundManager tipSoundManager;
    @NotNull
    public static HUD hud;
    @NotNull
    public static AnimationHandler animationHandler;
    @NotNull
    public static ClickGui clickGui;
    @Nullable
    private static IResourceLocation background;
    @NotNull
    public static Wrapper wrapper;
    public static final LiquidBounce INSTANCE;

    public final boolean isStarting() {
        return isStarting;
    }

    public final void setStarting(boolean bl) {
        isStarting = bl;
    }

    @NotNull
    public final ModuleManager getModuleManager() {
        ModuleManager moduleManager = LiquidBounce.moduleManager;
        if (moduleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moduleManager");
        }
        return moduleManager;
    }

    public final void setModuleManager(@NotNull ModuleManager moduleManager) {
        Intrinsics.checkParameterIsNotNull(moduleManager, "<set-?>");
        LiquidBounce.moduleManager = moduleManager;
    }

    @NotNull
    public final CommandManager getCommandManager() {
        CommandManager commandManager = LiquidBounce.commandManager;
        if (commandManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commandManager");
        }
        return commandManager;
    }

    public final void setCommandManager(@NotNull CommandManager commandManager) {
        Intrinsics.checkParameterIsNotNull(commandManager, "<set-?>");
        LiquidBounce.commandManager = commandManager;
    }

    @NotNull
    public final EventManager getEventManager() {
        EventManager eventManager = LiquidBounce.eventManager;
        if (eventManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventManager");
        }
        return eventManager;
    }

    public final void setEventManager(@NotNull EventManager eventManager) {
        Intrinsics.checkParameterIsNotNull(eventManager, "<set-?>");
        LiquidBounce.eventManager = eventManager;
    }

    @NotNull
    public final FileManager getFileManager() {
        FileManager fileManager = LiquidBounce.fileManager;
        if (fileManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileManager");
        }
        return fileManager;
    }

    public final void setFileManager(@NotNull FileManager fileManager) {
        Intrinsics.checkParameterIsNotNull(fileManager, "<set-?>");
        LiquidBounce.fileManager = fileManager;
    }

    @NotNull
    public final ScriptManager getScriptManager() {
        ScriptManager scriptManager = LiquidBounce.scriptManager;
        if (scriptManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scriptManager");
        }
        return scriptManager;
    }

    public final void setScriptManager(@NotNull ScriptManager scriptManager) {
        Intrinsics.checkParameterIsNotNull(scriptManager, "<set-?>");
        LiquidBounce.scriptManager = scriptManager;
    }

    @NotNull
    public final CombatManager getCombatManager() {
        CombatManager combatManager = LiquidBounce.combatManager;
        if (combatManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("combatManager");
        }
        return combatManager;
    }

    public final void setCombatManager(@NotNull CombatManager combatManager) {
        Intrinsics.checkParameterIsNotNull(combatManager, "<set-?>");
        LiquidBounce.combatManager = combatManager;
    }

    @NotNull
    public final TipSoundManager getTipSoundManager() {
        TipSoundManager tipSoundManager = LiquidBounce.tipSoundManager;
        if (tipSoundManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tipSoundManager");
        }
        return tipSoundManager;
    }

    public final void setTipSoundManager(@NotNull TipSoundManager tipSoundManager) {
        Intrinsics.checkParameterIsNotNull(tipSoundManager, "<set-?>");
        LiquidBounce.tipSoundManager = tipSoundManager;
    }

    @NotNull
    public final HUD getHud() {
        HUD hUD = hud;
        if (hUD == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hud");
        }
        return hUD;
    }

    public final void setHud(@NotNull HUD hUD) {
        Intrinsics.checkParameterIsNotNull(hUD, "<set-?>");
        hud = hUD;
    }

    @NotNull
    public final AnimationHandler getAnimationHandler() {
        AnimationHandler animationHandler = LiquidBounce.animationHandler;
        if (animationHandler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animationHandler");
        }
        return animationHandler;
    }

    public final void setAnimationHandler(@NotNull AnimationHandler animationHandler) {
        Intrinsics.checkParameterIsNotNull(animationHandler, "<set-?>");
        LiquidBounce.animationHandler = animationHandler;
    }

    @NotNull
    public final ClickGui getClickGui() {
        ClickGui clickGui = LiquidBounce.clickGui;
        if (clickGui == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clickGui");
        }
        return clickGui;
    }

    public final void setClickGui(@NotNull ClickGui clickGui) {
        Intrinsics.checkParameterIsNotNull(clickGui, "<set-?>");
        LiquidBounce.clickGui = clickGui;
    }

    @Nullable
    public final IResourceLocation getBackground() {
        return background;
    }

    public final void setBackground(@Nullable IResourceLocation iResourceLocation) {
        background = iResourceLocation;
    }

    @NotNull
    public final Wrapper getWrapper() {
        Wrapper wrapper = LiquidBounce.wrapper;
        if (wrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wrapper");
        }
        return wrapper;
    }

    public final void setWrapper(@NotNull Wrapper wrapper) {
        Intrinsics.checkParameterIsNotNull(wrapper, "<set-?>");
        LiquidBounce.wrapper = wrapper;
    }

    @NativeMethod
    public final native void startClient();

    public final void stopClient() {
        EventManager eventManager = LiquidBounce.eventManager;
        if (eventManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventManager");
        }
        eventManager.callEvent(new ClientShutdownEvent());
        FileManager fileManager = LiquidBounce.fileManager;
        if (fileManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileManager");
        }
        fileManager.saveAllConfigs();
    }

    private LiquidBounce() {
    }

    static {
        LiquidBounce liquidBounce;
        Loader.registerNativesForClass(7, LiquidBounce.class);
        INSTANCE = liquidBounce = new LiquidBounce();
    }
}

