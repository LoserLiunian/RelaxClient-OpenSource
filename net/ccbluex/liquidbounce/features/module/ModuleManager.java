/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import native0.Loader;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.KeyEvent;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCommand;
import net.ccbluex.liquidbounce.features.module.ModuleManager;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.value.Value;
import oh.yalan.NativeMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006H\u0000\u00a2\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020\u00062\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0086\u0002J\u0012\u0010$\u001a\u00020\u00062\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0012\u0010$\u001a\u0004\u0018\u00010\u00062\b\u0010&\u001a\u0004\u0018\u00010'J\b\u0010(\u001a\u00020\rH\u0016J\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020+H\u0003J\u0018\u0010,\u001a\u00020\u001f2\u000e\u0010%\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0002J\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006J1\u0010-\u001a\u00020\u001f2\"\u0010\b\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00050.\"\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005H\u0007\u00a2\u0006\u0002\u0010/J\u0010\u0010-\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\u0013H\u0007J\u000e\u00101\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006R2\u0010\u0003\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u00062"}, d2={"Lnet/ccbluex/liquidbounce/features/module/ModuleManager;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "()V", "moduleClassMap", "Ljava/util/HashMap;", "Ljava/lang/Class;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "Lkotlin/collections/HashMap;", "modules", "Ljava/util/TreeSet;", "getModules", "()Ljava/util/TreeSet;", "shouldNotify", "", "getShouldNotify", "()Z", "setShouldNotify", "(Z)V", "toggleSoundMode", "", "getToggleSoundMode", "()I", "setToggleSoundMode", "(I)V", "toggleVolume", "", "getToggleVolume", "()F", "setToggleVolume", "(F)V", "generateCommand", "", "module", "generateCommand$Relaxed", "get", "clazz", "getModule", "moduleClass", "moduleName", "", "handleEvents", "onKey", "event", "Lnet/ccbluex/liquidbounce/event/KeyEvent;", "registerModule", "registerModules", "", "([Ljava/lang/Class;)V", "abc", "unregisterModule", "Relaxed"})
public final class ModuleManager
implements Listenable {
    @NotNull
    private final TreeSet<Module> modules = new TreeSet(modules.1.INSTANCE);
    private final HashMap<Class<?>, Module> moduleClassMap;
    private boolean shouldNotify;
    private int toggleSoundMode;
    private float toggleVolume;

    @NotNull
    public final TreeSet<Module> getModules() {
        return this.modules;
    }

    public final boolean getShouldNotify() {
        return this.shouldNotify;
    }

    public final void setShouldNotify(boolean bl) {
        this.shouldNotify = bl;
    }

    public final int getToggleSoundMode() {
        return this.toggleSoundMode;
    }

    public final void setToggleSoundMode(int n) {
        this.toggleSoundMode = n;
    }

    public final float getToggleVolume() {
        return this.toggleVolume;
    }

    public final void setToggleVolume(float f) {
        this.toggleVolume = f;
    }

    @NativeMethod
    public final native void registerModules(int var1);

    public final void registerModule(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        if (!module.isSupported()) {
            return;
        }
        Collection collection = this.modules;
        boolean bl = false;
        collection.add(module);
        ((Map)this.moduleClassMap).put(module.getClass(), module);
        this.generateCommand$Relaxed(module);
        LiquidBounce.INSTANCE.getEventManager().registerListener(module);
    }

    private final void registerModule(Class<? extends Module> moduleClass) {
        try {
            Module module = moduleClass.newInstance();
            Intrinsics.checkExpressionValueIsNotNull(module, "moduleClass.newInstance()");
            this.registerModule(module);
        }
        catch (Throwable e) {
            ClientUtils.getLogger().error("Failed to load module: " + moduleClass.getName() + " (" + e.getClass().getName() + ": " + e.getMessage() + ')');
        }
    }

    /*
     * WARNING - void declaration
     */
    @SafeVarargs
    public final void registerModules(Class<? extends Module> ... modules2) {
        void $this$forEach$iv;
        Intrinsics.checkParameterIsNotNull(modules2, "modules");
        Class<? extends Module>[] classArray = modules2;
        ModuleManager moduleManager = this;
        boolean $i$f$forEach = false;
        void var5_5 = $this$forEach$iv;
        int n = ((void)var5_5).length;
        for (int i = 0; i < n; ++i) {
            void element$iv;
            void p1 = element$iv = var5_5[i];
            boolean bl = false;
            moduleManager.registerModule((Class<? extends Module>)p1);
        }
    }

    public final void unregisterModule(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        this.modules.remove(module);
        this.moduleClassMap.remove(module.getClass());
        LiquidBounce.INSTANCE.getEventManager().unregisterListener(module);
    }

    public final void generateCommand$Relaxed(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        List<Value<?>> values2 = module.getValues();
        if (values2.isEmpty()) {
            return;
        }
        LiquidBounce.INSTANCE.getCommandManager().registerCommand(new ModuleCommand(module, values2));
    }

    @NotNull
    public final Module getModule(@NotNull Class<?> moduleClass) {
        Intrinsics.checkParameterIsNotNull(moduleClass, "moduleClass");
        Module module = this.moduleClassMap.get(moduleClass);
        if (module == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(module, "moduleClassMap[moduleClass]!!");
        return module;
    }

    @NotNull
    public final Module get(@NotNull Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        return this.getModule(clazz);
    }

    @Nullable
    public final Module getModule(@Nullable String moduleName) {
        Object v0;
        block1: {
            Iterable iterable = this.modules;
            boolean bl = false;
            Iterable iterable2 = iterable;
            boolean bl2 = false;
            for (Object t : iterable2) {
                Module it = (Module)t;
                boolean bl3 = false;
                if (!StringsKt.equals(it.getName(), moduleName, true)) continue;
                v0 = t;
                break block1;
            }
            v0 = null;
        }
        return v0;
    }

    /*
     * WARNING - void declaration
     */
    @EventTarget
    private final void onKey(KeyEvent event) {
        void $this$filterTo$iv$iv;
        Iterable $this$filter$iv = this.modules;
        boolean $i$f$filter = false;
        Iterable iterable = $this$filter$iv;
        Collection destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object element$iv$iv : $this$filterTo$iv$iv) {
            Module it = (Module)element$iv$iv;
            boolean bl = false;
            if (!(it.getKeyBind() == event.getKey())) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        Iterable $this$forEach$iv = (List)destination$iv$iv;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Module it = (Module)element$iv;
            boolean bl = false;
            it.toggle();
        }
    }

    @Override
    public boolean handleEvents() {
        return true;
    }

    public ModuleManager() {
        ModuleManager moduleManager = this;
        boolean bl = false;
        HashMap hashMap = new HashMap();
        moduleManager.moduleClassMap = hashMap;
        LiquidBounce.INSTANCE.getEventManager().registerListener(this);
    }

    static {
        Loader.registerNativesForClass(6, ModuleManager.class);
    }
}

