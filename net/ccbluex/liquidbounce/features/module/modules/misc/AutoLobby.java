/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package net.ccbluex.liquidbounce.features.module.modules.misc;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IPlayerControllerMP;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketEntityAction;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.combat.Velocity;
import net.ccbluex.liquidbounce.features.module.modules.movement.Speed;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.TextValue;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ModuleInfo(name="AutoLobby", description="skid", category=ModuleCategory.MISC)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020)2\u0006\u0010/\u001a\u000200H\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u0016\u0010$\u001a\u0004\u0018\u00010%8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010'\u00a8\u00061"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/misc/AutoLobby;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "canhubchat", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "getCanhubchat", "()Lnet/ccbluex/liquidbounce/value/BoolValue;", "setCanhubchat", "(Lnet/ccbluex/liquidbounce/value/BoolValue;)V", "disabler", "getDisabler", "setDisabler", "health", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "getHealth", "()Lnet/ccbluex/liquidbounce/value/FloatValue;", "setHealth", "(Lnet/ccbluex/liquidbounce/value/FloatValue;)V", "hubDelayTime", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "getHubDelayTime", "()Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "setHubDelayTime", "(Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;)V", "hubchattext", "Lnet/ccbluex/liquidbounce/value/TextValue;", "getHubchattext", "()Lnet/ccbluex/liquidbounce/value/TextValue;", "setHubchattext", "(Lnet/ccbluex/liquidbounce/value/TextValue;)V", "keepArmor", "getKeepArmor", "setKeepArmor", "randomhub", "getRandomhub", "setRandomhub", "tag", "", "getTag", "()Ljava/lang/String;", "move", "", "item", "", "isArmorSlot", "", "onUpdate", "event", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "Relaxed"})
public final class AutoLobby
extends Module {
    @NotNull
    private FloatValue health = new FloatValue("Health", 5.0f, 0.0f, 20.0f);
    @NotNull
    private BoolValue canhubchat = new BoolValue("CanHubChat", false);
    @NotNull
    private BoolValue randomhub = new BoolValue("RandomHub", false);
    @NotNull
    private TextValue hubchattext = new TextValue("HubChat", "[Relaxed1.12.2] Bypass");
    @NotNull
    private BoolValue disabler = new BoolValue("AutoDisable-KillAura-Velocity-Speed", true);
    @NotNull
    private BoolValue keepArmor = new BoolValue("KeepArmor", true);
    @NotNull
    private MSTimer hubDelayTime = new MSTimer();

    @NotNull
    public final FloatValue getHealth() {
        return this.health;
    }

    public final void setHealth(@NotNull FloatValue floatValue) {
        Intrinsics.checkParameterIsNotNull(floatValue, "<set-?>");
        this.health = floatValue;
    }

    @NotNull
    public final BoolValue getCanhubchat() {
        return this.canhubchat;
    }

    public final void setCanhubchat(@NotNull BoolValue boolValue) {
        Intrinsics.checkParameterIsNotNull(boolValue, "<set-?>");
        this.canhubchat = boolValue;
    }

    @NotNull
    public final BoolValue getRandomhub() {
        return this.randomhub;
    }

    public final void setRandomhub(@NotNull BoolValue boolValue) {
        Intrinsics.checkParameterIsNotNull(boolValue, "<set-?>");
        this.randomhub = boolValue;
    }

    @NotNull
    public final TextValue getHubchattext() {
        return this.hubchattext;
    }

    public final void setHubchattext(@NotNull TextValue textValue) {
        Intrinsics.checkParameterIsNotNull(textValue, "<set-?>");
        this.hubchattext = textValue;
    }

    @NotNull
    public final BoolValue getDisabler() {
        return this.disabler;
    }

    public final void setDisabler(@NotNull BoolValue boolValue) {
        Intrinsics.checkParameterIsNotNull(boolValue, "<set-?>");
        this.disabler = boolValue;
    }

    @NotNull
    public final BoolValue getKeepArmor() {
        return this.keepArmor;
    }

    public final void setKeepArmor(@NotNull BoolValue boolValue) {
        Intrinsics.checkParameterIsNotNull(boolValue, "<set-?>");
        this.keepArmor = boolValue;
    }

    @NotNull
    public final MSTimer getHubDelayTime() {
        return this.hubDelayTime;
    }

    public final void setHubDelayTime(@NotNull MSTimer mSTimer) {
        Intrinsics.checkParameterIsNotNull(mSTimer, "<set-?>");
        this.hubDelayTime = mSTimer;
    }

    /*
     * WARNING - void declaration
     */
    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Module module = LiquidBounce.INSTANCE.getModuleManager().get(KillAura.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.KillAura");
        }
        KillAura killAura = (KillAura)module;
        Module module2 = LiquidBounce.INSTANCE.getModuleManager().get(Velocity.class);
        if (module2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.Velocity");
        }
        Velocity velocity = (Velocity)module2;
        Module module3 = LiquidBounce.INSTANCE.getModuleManager().get(Speed.class);
        if (module3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.movement.Speed");
        }
        Speed speed = (Speed)module3;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP.getHealth() < ((Number)this.health.get()).floatValue()) {
            if (((Boolean)this.keepArmor.get()).booleanValue()) {
                int n = 0;
                int n2 = 3;
                while (n <= n2) {
                    void i;
                    int armorSlot = 3 - i;
                    this.move(8 - armorSlot, true);
                    ++i;
                }
            }
            if (((Boolean)this.canhubchat.get()).booleanValue()) {
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP2.sendChatMessage((String)this.hubchattext.get());
            }
            if (((Boolean)this.randomhub.get()).booleanValue()) {
                if (this.hubDelayTime.hasTimePassed(300L)) {
                    IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP3 == null) {
                        Intrinsics.throwNpe();
                    }
                    iEntityPlayerSP3.sendChatMessage("/hub " + (int)(Math.random() * (double)100 + 1.0));
                    this.hubDelayTime.reset();
                }
            } else if (this.hubDelayTime.hasTimePassed(300L)) {
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                iEntityPlayerSP4.sendChatMessage("/hub");
                this.hubDelayTime.reset();
            }
            if (((Boolean)this.disabler.get()).booleanValue()) {
                killAura.setState(false);
                velocity.setState(false);
                speed.setState(false);
            }
        }
    }

    private final void move(int item, boolean isArmorSlot) {
        if (item != -1) {
            boolean openInventory;
            boolean bl = openInventory = !(MinecraftInstance.mc.getCurrentScreen() instanceof GuiInventory);
            if (openInventory) {
                IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                iINetHandlerPlayClient.addToSendQueue(MinecraftInstance.classProvider.createCPacketEntityAction(iEntityPlayerSP, ICPacketEntityAction.WAction.OPEN_INVENTORY));
            }
            IPlayerControllerMP iPlayerControllerMP = MinecraftInstance.mc.getPlayerController();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            int n = iEntityPlayerSP.getInventoryContainer().getWindowId();
            int n2 = isArmorSlot ? item : (item < 9 ? item + 36 : item);
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            iPlayerControllerMP.windowClick(n, n2, 0, 1, iEntityPlayerSP2);
            if (openInventory) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketCloseWindow());
            }
        }
    }

    @Override
    @Nullable
    public String getTag() {
        return "HuaYuTing";
    }
}

