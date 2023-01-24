/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.script;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.script.Script;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007J\u0006\u0010\u0011\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000bJ\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000bJ\u0006\u0010\u0017\u001a\u00020\u000fJ\u0006\u0010\u0018\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001a"}, d2={"Lnet/ccbluex/liquidbounce/script/ScriptManager;", "", "()V", "scriptFileExtension", "", "scripts", "", "Lnet/ccbluex/liquidbounce/script/Script;", "getScripts", "()Ljava/util/List;", "scriptsFolder", "Ljava/io/File;", "getScriptsFolder", "()Ljava/io/File;", "deleteScript", "", "script", "disableScripts", "enableScripts", "importScript", "file", "loadScript", "scriptFile", "loadScripts", "reloadScripts", "unloadScripts", "Relaxed"})
public final class ScriptManager {
    @NotNull
    private final List<Script> scripts;
    @NotNull
    private final File scriptsFolder;
    private final String scriptFileExtension = ".js";

    @NotNull
    public final List<Script> getScripts() {
        return this.scripts;
    }

    @NotNull
    public final File getScriptsFolder() {
        return this.scriptsFolder;
    }

    /*
     * WARNING - void declaration
     */
    public final void loadScripts() {
        if (!this.scriptsFolder.exists()) {
            this.scriptsFolder.mkdir();
        }
        File[] fileArray = this.scriptsFolder.listFiles(new FileFilter(this){
            final /* synthetic */ ScriptManager this$0;

            public final boolean accept(File it) {
                File file = it;
                Intrinsics.checkExpressionValueIsNotNull(file, "it");
                String string = file.getName();
                Intrinsics.checkExpressionValueIsNotNull(string, "it.name");
                return StringsKt.endsWith$default(string, ScriptManager.access$getScriptFileExtension$p(this.this$0), false, 2, null);
            }
            {
                this.this$0 = scriptManager;
            }
        });
        if (fileArray != null) {
            void $this$forEach$iv;
            File[] fileArray2 = fileArray;
            ScriptManager scriptManager = this;
            boolean $i$f$forEach = false;
            void var4_4 = $this$forEach$iv;
            int n = ((void)var4_4).length;
            for (int i = 0; i < n; ++i) {
                void element$iv;
                void p1 = element$iv = var4_4[i];
                boolean bl = false;
                scriptManager.loadScript((File)p1);
            }
        }
    }

    public final void unloadScripts() {
        this.scripts.clear();
    }

    public final void loadScript(@NotNull File scriptFile) {
        Intrinsics.checkParameterIsNotNull(scriptFile, "scriptFile");
        try {
            Script script = new Script(scriptFile);
            script.initScript();
            this.scripts.add(script);
        }
        catch (Throwable t) {
            ClientUtils.getLogger().error("[ScriptAPI] Failed to load script '" + scriptFile.getName() + "'.", t);
        }
    }

    public final void enableScripts() {
        Iterable $this$forEach$iv = this.scripts;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Script it = (Script)element$iv;
            boolean bl = false;
            it.onEnable();
        }
    }

    public final void disableScripts() {
        Iterable $this$forEach$iv = this.scripts;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Script it = (Script)element$iv;
            boolean bl = false;
            it.onDisable();
        }
    }

    public final void importScript(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        File scriptFile = new File(this.scriptsFolder, file.getName());
        FilesKt.copyTo$default(file, scriptFile, false, 0, 6, null);
        this.loadScript(scriptFile);
        ClientUtils.getLogger().info("[ScriptAPI] Successfully imported script '" + scriptFile.getName() + "'.");
    }

    public final void deleteScript(@NotNull Script script) {
        Intrinsics.checkParameterIsNotNull(script, "script");
        script.onDisable();
        this.scripts.remove(script);
        script.getScriptFile().delete();
        ClientUtils.getLogger().info("[ScriptAPI]  Successfully deleted script '" + script.getScriptFile().getName() + "'.");
    }

    public final void reloadScripts() {
        this.disableScripts();
        this.unloadScripts();
        this.loadScripts();
        this.enableScripts();
        ClientUtils.getLogger().info("[ScriptAPI]  Successfully reloaded scripts.");
    }

    public ScriptManager() {
        List list;
        ScriptManager scriptManager = this;
        boolean bl = false;
        scriptManager.scripts = list = (List)new ArrayList();
        this.scriptsFolder = new File(LiquidBounce.INSTANCE.getFileManager().dir, "scripts");
        this.scriptFileExtension = ".js";
    }

    public static final /* synthetic */ String access$getScriptFileExtension$p(ScriptManager $this) {
        return $this.scriptFileExtension;
    }
}

