/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.event.Listenable;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.injection.backend.Backend;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.NotifyType;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.Translate;
import net.ccbluex.liquidbounce.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0016\u0010K\u001a\b\u0012\u0002\b\u0003\u0018\u00010H2\u0006\u0010L\u001a\u00020\u001bH\u0016J\b\u0010M\u001a\u00020\u0005H\u0016J\b\u0010N\u001a\u00020OH\u0016J\b\u0010P\u001a\u00020OH\u0016J\u0010\u0010Q\u001a\u00020O2\u0006\u00109\u001a\u00020\u0005H\u0016J\u0006\u0010R\u001a\u00020OR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001d\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010\u000fR\u0011\u0010%\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\rR\u001a\u0010'\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0007\"\u0004\b(\u0010\tR$\u0010)\u001a\u00020*2\u0006\u0010)\u001a\u00020*@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001d\"\u0004\b1\u0010!R\u001a\u00102\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\r\"\u0004\b4\u0010\u000fR\u001a\u00105\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\r\"\u0004\b7\u0010\u000fR$\u00109\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0005@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0007\"\u0004\b;\u0010\tR\u0011\u0010<\u001a\u00020=\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0016\u0010@\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bA\u0010\u001dR\u0011\u0010B\u001a\u00020\u001b8F\u00a2\u0006\u0006\u001a\u0004\bC\u0010\u001dR\u0011\u0010D\u001a\u00020=\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010?R\u001e\u0010F\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030H0G8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bI\u0010J\u00a8\u0006S"}, d2={"Lnet/ccbluex/liquidbounce/features/module/Module;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "Lnet/ccbluex/liquidbounce/event/Listenable;", "()V", "BreakName", "", "getBreakName", "()Z", "setBreakName", "(Z)V", "animation", "", "getAnimation", "()F", "setAnimation", "(F)V", "array", "getArray", "setArray", "canEnable", "category", "Lnet/ccbluex/liquidbounce/features/module/ModuleCategory;", "getCategory", "()Lnet/ccbluex/liquidbounce/features/module/ModuleCategory;", "setCategory", "(Lnet/ccbluex/liquidbounce/features/module/ModuleCategory;)V", "colorlessTagName", "", "getColorlessTagName", "()Ljava/lang/String;", "description", "getDescription", "setDescription", "(Ljava/lang/String;)V", "higt", "getHigt", "setHigt", "hue", "getHue", "isSupported", "setSupported", "keyBind", "", "getKeyBind", "()I", "setKeyBind", "(I)V", "name", "getName", "setName", "slide", "getSlide", "setSlide", "slideStep", "getSlideStep", "setSlideStep", "value", "state", "getState", "setState", "tab", "Lnet/ccbluex/liquidbounce/utils/render/Translate;", "getTab", "()Lnet/ccbluex/liquidbounce/utils/render/Translate;", "tag", "getTag", "tagName", "getTagName", "translate", "getTranslate", "values", "", "Lnet/ccbluex/liquidbounce/value/Value;", "getValues", "()Ljava/util/List;", "getValue", "valueName", "handleEvents", "onDisable", "", "onEnable", "onToggle", "toggle", "Relaxed"})
public class Module
extends MinecraftInstance
implements Listenable {
    private boolean isSupported;
    private float animation;
    @NotNull
    private final Translate tab = new Translate(0.0f, 0.0f);
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private ModuleCategory category;
    private int keyBind;
    private boolean array = true;
    private final boolean canEnable;
    private float slideStep;
    private boolean state;
    private final float hue;
    private float slide;
    @NotNull
    private final Translate translate;
    private boolean BreakName;
    private float higt;

    public final boolean isSupported() {
        return this.isSupported;
    }

    public final void setSupported(boolean bl) {
        this.isSupported = bl;
    }

    public final float getAnimation() {
        return this.animation;
    }

    public final void setAnimation(float f) {
        this.animation = f;
    }

    @NotNull
    public final Translate getTab() {
        return this.tab;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "<set-?>");
        this.name = string;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "<set-?>");
        this.description = string;
    }

    @NotNull
    public final ModuleCategory getCategory() {
        return this.category;
    }

    public final void setCategory(@NotNull ModuleCategory moduleCategory) {
        Intrinsics.checkParameterIsNotNull((Object)moduleCategory, "<set-?>");
        this.category = moduleCategory;
    }

    public final int getKeyBind() {
        return this.keyBind;
    }

    public final void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
        if (!LiquidBounce.INSTANCE.isStarting()) {
            LiquidBounce.INSTANCE.getFileManager().saveConfig(LiquidBounce.INSTANCE.getFileManager().modulesConfig);
        }
    }

    public final boolean getArray() {
        return this.array;
    }

    public final void setArray(boolean array) {
        this.array = array;
        if (!LiquidBounce.INSTANCE.isStarting()) {
            LiquidBounce.INSTANCE.getFileManager().saveConfig(LiquidBounce.INSTANCE.getFileManager().modulesConfig);
        }
    }

    public final float getSlideStep() {
        return this.slideStep;
    }

    public final void setSlideStep(float f) {
        this.slideStep = f;
    }

    public final boolean getState() {
        return this.state;
    }

    public final void setState(boolean value) {
        if (this.state == value) {
            return;
        }
        this.onToggle(value);
        if (!LiquidBounce.INSTANCE.isStarting()) {
            switch (LiquidBounce.INSTANCE.getModuleManager().getToggleSoundMode()) {
                case 2: {
                    (value ? LiquidBounce.INSTANCE.getTipSoundManager().getEnableSound() : LiquidBounce.INSTANCE.getTipSoundManager().getDisableSound()).asyncPlay();
                    break;
                }
            }
            LiquidBounce.INSTANCE.getHud().addNotification(new Notification("Notification", (value ? "Enabled " : "Disabled ") + this.name, value ? NotifyType.SUCCESS : NotifyType.ERROR, 0, 0, 24, null));
        }
        if (value) {
            this.onEnable();
            if (this.canEnable) {
                this.state = true;
            }
        } else {
            this.onDisable();
            this.state = false;
        }
        LiquidBounce.INSTANCE.getFileManager().saveConfig(LiquidBounce.INSTANCE.getFileManager().modulesConfig);
    }

    public final float getHue() {
        return this.hue;
    }

    public final float getSlide() {
        return this.slide;
    }

    public final void setSlide(float f) {
        this.slide = f;
    }

    @NotNull
    public final Translate getTranslate() {
        return this.translate;
    }

    public final boolean getBreakName() {
        return this.BreakName;
    }

    public final void setBreakName(boolean bl) {
        this.BreakName = bl;
    }

    public final float getHigt() {
        return this.higt;
    }

    public final void setHigt(float f) {
        this.higt = f;
    }

    @Nullable
    public String getTag() {
        return null;
    }

    @NotNull
    public final String getTagName() {
        return this.name + (this.getTag() == null ? "" : " \u00a77" + this.getTag());
    }

    @NotNull
    public final String getColorlessTagName() {
        return this.name + (this.getTag() == null ? "" : " " + ColorUtils.stripColor(this.getTag()));
    }

    public final void toggle() {
        this.setState(!this.state);
    }

    public void onToggle(boolean state) {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    @Nullable
    public Value<?> getValue(@NotNull String valueName) {
        Object v0;
        block1: {
            Intrinsics.checkParameterIsNotNull(valueName, "valueName");
            Iterable iterable = this.getValues();
            boolean bl = false;
            Iterable iterable2 = iterable;
            boolean bl2 = false;
            for (Object t : iterable2) {
                Value it = (Value)t;
                boolean bl3 = false;
                if (!StringsKt.equals(it.getName(), valueName, true)) continue;
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
    @NotNull
    public List<Value<?>> getValues() {
        void $this$filterTo$iv$iv;
        Iterable $this$filterIsInstanceTo$iv$iv;
        Iterable $this$mapTo$iv$iv;
        Field[] fieldArray = this.getClass().getDeclaredFields();
        Intrinsics.checkExpressionValueIsNotNull(fieldArray, "javaClass.declaredFields");
        Field[] $this$map$iv = fieldArray;
        boolean $i$f$map = false;
        Field[] fieldArray2 = $this$map$iv;
        Collection destination$iv$iv = new ArrayList($this$map$iv.length);
        boolean $i$f$mapTo = false;
        Iterator iterator2 = $this$mapTo$iv$iv;
        int n = ((void)iterator2).length;
        for (int i = 0; i < n; ++i) {
            void valueField;
            void item$iv$iv;
            void var10_14 = item$iv$iv = iterator2[i];
            Collection collection = destination$iv$iv;
            boolean bl = false;
            void v1 = valueField;
            Intrinsics.checkExpressionValueIsNotNull(v1, "valueField");
            v1.setAccessible(true);
            Object object = valueField.get(this);
            collection.add(object);
        }
        Iterable $this$filterIsInstance$iv = (List)destination$iv$iv;
        boolean $i$f$filterIsInstance = false;
        $this$mapTo$iv$iv = $this$filterIsInstance$iv;
        destination$iv$iv = new ArrayList();
        boolean $i$f$filterIsInstanceTo = false;
        for (Object element$iv$iv : $this$filterIsInstanceTo$iv$iv) {
            if (!(element$iv$iv instanceof Value)) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        Iterable $this$filter$iv = (List)destination$iv$iv;
        boolean $i$f$filter = false;
        $this$filterIsInstanceTo$iv$iv = $this$filter$iv;
        destination$iv$iv = new ArrayList();
        boolean $i$f$filterTo = false;
        for (Object element$iv$iv : $this$filterTo$iv$iv) {
            Value it = (Value)element$iv$iv;
            boolean bl = false;
            if (!it.isSupported()) continue;
            destination$iv$iv.add(element$iv$iv);
        }
        return (List)destination$iv$iv;
    }

    @Override
    public boolean handleEvents() {
        return this.state;
    }

    public Module() {
        ModuleInfo moduleInfo = this.getClass().getAnnotation(ModuleInfo.class);
        if (moduleInfo == null) {
            Intrinsics.throwNpe();
        }
        ModuleInfo moduleInfo2 = moduleInfo;
        this.name = moduleInfo2.name();
        this.description = moduleInfo2.description();
        this.category = moduleInfo2.category();
        this.setKeyBind(moduleInfo2.keyBind());
        this.setArray(moduleInfo2.array());
        this.canEnable = moduleInfo2.canEnable();
        this.isSupported = ArraysKt.contains(moduleInfo2.supportedVersions(), Backend.INSTANCE.getREPRESENTED_BACKEND_VERSION());
        this.hue = (float)Math.random();
        this.translate = new Translate(0.0f, 0.0f);
    }
}

