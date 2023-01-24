/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.player.EnumPlayerModelParts
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.injection.backend;

import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.WEnumPlayerModelParts;
import net.ccbluex.liquidbounce.api.minecraft.client.settings.IGameSettings;
import net.ccbluex.liquidbounce.api.minecraft.client.settings.IKeyBinding;
import net.ccbluex.liquidbounce.api.util.WrappedSet;
import net.ccbluex.liquidbounce.injection.backend.GameSettingsImpl;
import net.ccbluex.liquidbounce.injection.backend.KeyBindingImpl;
import net.ccbluex.liquidbounce.injection.backend.utils.BackendExtentionsKt$WhenMappings;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EnumPlayerModelParts;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0013\u00105\u001a\u00020\f2\b\u00106\u001a\u0004\u0018\u000107H\u0096\u0002J\u0010\u00108\u001a\u00020\f2\u0006\u00109\u001a\u00020\u0019H\u0016J\u0018\u0010:\u001a\u00020;2\u0006\u0010,\u001a\u00020.2\u0006\u0010<\u001a\u00020\fH\u0016R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00128V@VX\u0096\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0014\u0010 \u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\u001bR\u0014\u0010\"\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u001bR\u0014\u0010$\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u001bR\u0014\u0010&\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b'\u0010\u001bR\u0014\u0010(\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u001bR\u0014\u0010*\u001a\u00020\u00198VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b+\u0010\u001bR\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00128VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b2\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104\u00a8\u0006="}, d2={"Lnet/ccbluex/liquidbounce/injection/backend/GameSettingsImpl;", "Lnet/ccbluex/liquidbounce/api/minecraft/client/settings/IGameSettings;", "wrapped", "Lnet/minecraft/client/settings/GameSettings;", "(Lnet/minecraft/client/settings/GameSettings;)V", "value", "Lnet/minecraft/client/util/ITooltipFlag;", "advancedItemTooltips", "getAdvancedItemTooltips", "()Lnet/minecraft/client/util/ITooltipFlag;", "setAdvancedItemTooltips", "(Lnet/minecraft/client/util/ITooltipFlag;)V", "", "entityShadows", "getEntityShadows", "()Z", "setEntityShadows", "(Z)V", "", "gammaSetting", "getGammaSetting", "()F", "setGammaSetting", "(F)V", "keyBindAttack", "Lnet/ccbluex/liquidbounce/api/minecraft/client/settings/IKeyBinding;", "getKeyBindAttack", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/settings/IKeyBinding;", "keyBindBack", "getKeyBindBack", "keyBindForward", "getKeyBindForward", "keyBindJump", "getKeyBindJump", "keyBindLeft", "getKeyBindLeft", "keyBindRight", "getKeyBindRight", "keyBindSneak", "getKeyBindSneak", "keyBindSprint", "getKeyBindSprint", "keyBindUseItem", "getKeyBindUseItem", "modelParts", "", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/player/WEnumPlayerModelParts;", "getModelParts", "()Ljava/util/Set;", "mouseSensitivity", "getMouseSensitivity", "getWrapped", "()Lnet/minecraft/client/settings/GameSettings;", "equals", "other", "", "isKeyDown", "key", "setModelPartEnabled", "", "enabled", "Relaxed"})
public final class GameSettingsImpl
implements IGameSettings {
    @NotNull
    private final GameSettings wrapped;

    @Override
    public boolean getEntityShadows() {
        return this.wrapped.field_181151_V;
    }

    @Override
    public void setEntityShadows(boolean value) {
        this.wrapped.field_181151_V = value;
    }

    @Override
    @NotNull
    public ITooltipFlag getAdvancedItemTooltips() {
        return this.getAdvancedItemTooltips();
    }

    public void setAdvancedItemTooltips(@NotNull ITooltipFlag value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.getAdvancedItemTooltips();
    }

    @Override
    public float getGammaSetting() {
        return this.wrapped.field_74333_Y;
    }

    @Override
    public void setGammaSetting(float value) {
        this.wrapped.field_74333_Y = value;
    }

    @Override
    @NotNull
    public Set<WEnumPlayerModelParts> getModelParts() {
        Set set = this.wrapped.func_178876_d();
        Intrinsics.checkExpressionValueIsNotNull(set, "wrapped.modelParts");
        return new WrappedSet(set, (Function1)modelParts.1.INSTANCE, (Function1)modelParts.2.INSTANCE);
    }

    @Override
    public float getMouseSensitivity() {
        return this.wrapped.field_74341_c;
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindAttack() {
        KeyBinding keyBinding = this.wrapped.field_74312_F;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindAttack");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindUseItem() {
        KeyBinding keyBinding = this.wrapped.field_74313_G;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindUseItem");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindJump() {
        KeyBinding keyBinding = this.wrapped.field_74314_A;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindJump");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindSneak() {
        KeyBinding keyBinding = this.wrapped.field_74311_E;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindSneak");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindForward() {
        KeyBinding keyBinding = this.wrapped.field_74351_w;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindForward");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindBack() {
        KeyBinding keyBinding = this.wrapped.field_74368_y;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindBack");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindRight() {
        KeyBinding keyBinding = this.wrapped.field_74366_z;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindRight");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindLeft() {
        KeyBinding keyBinding = this.wrapped.field_74370_x;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindLeft");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    @NotNull
    public IKeyBinding getKeyBindSprint() {
        KeyBinding keyBinding = this.wrapped.field_151444_V;
        Intrinsics.checkExpressionValueIsNotNull(keyBinding, "wrapped.keyBindSprint");
        KeyBinding $this$wrap$iv = keyBinding;
        boolean $i$f$wrap = false;
        return new KeyBindingImpl($this$wrap$iv);
    }

    @Override
    public boolean isKeyDown(@NotNull IKeyBinding key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        IKeyBinding $this$unwrap$iv = key;
        boolean $i$f$unwrap = false;
        return GameSettings.func_100015_a((KeyBinding)((KeyBindingImpl)$this$unwrap$iv).getWrapped());
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void setModelPartEnabled(@NotNull WEnumPlayerModelParts modelParts2, boolean enabled) {
        EnumPlayerModelParts enumPlayerModelParts;
        void $this$unwrap$iv;
        Intrinsics.checkParameterIsNotNull((Object)modelParts2, "modelParts");
        WEnumPlayerModelParts wEnumPlayerModelParts = modelParts2;
        GameSettings gameSettings = this.wrapped;
        boolean $i$f$unwrap = false;
        switch (BackendExtentionsKt$WhenMappings.$EnumSwitchMapping$1[$this$unwrap$iv.ordinal()]) {
            case 1: {
                enumPlayerModelParts = EnumPlayerModelParts.CAPE;
                break;
            }
            case 2: {
                enumPlayerModelParts = EnumPlayerModelParts.JACKET;
                break;
            }
            case 3: {
                enumPlayerModelParts = EnumPlayerModelParts.LEFT_SLEEVE;
                break;
            }
            case 4: {
                enumPlayerModelParts = EnumPlayerModelParts.RIGHT_SLEEVE;
                break;
            }
            case 5: {
                enumPlayerModelParts = EnumPlayerModelParts.LEFT_PANTS_LEG;
                break;
            }
            case 6: {
                enumPlayerModelParts = EnumPlayerModelParts.RIGHT_PANTS_LEG;
                break;
            }
            case 7: {
                enumPlayerModelParts = EnumPlayerModelParts.HAT;
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        EnumPlayerModelParts enumPlayerModelParts2 = enumPlayerModelParts;
        gameSettings.func_178878_a(enumPlayerModelParts2, enabled);
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof GameSettingsImpl && Intrinsics.areEqual(((GameSettingsImpl)other).wrapped, this.wrapped);
    }

    @NotNull
    public final GameSettings getWrapped() {
        return this.wrapped;
    }

    public GameSettingsImpl(@NotNull GameSettings wrapped) {
        Intrinsics.checkParameterIsNotNull(wrapped, "wrapped");
        this.wrapped = wrapped;
    }
}

