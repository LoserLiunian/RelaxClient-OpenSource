/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.server.SPacketSpawnGlobalEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Cylinder
 */
package net.ccbluex.liquidbounce.features.module.modules.combat;

import java.awt.Color;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import me.utils.player.PlayerUtil;
import me.utils.render.ColorUtils2;
import me.utils.render.GLUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.enums.EnumFacingType;
import net.ccbluex.liquidbounce.api.enums.WEnumHand;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntity;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityLivingBase;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.player.IEntityPlayer;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.client.network.IINetHandlerPlayClient;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketEntityAction;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketPlayerDigging;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketUseEntity;
import net.ccbluex.liquidbounce.api.minecraft.potion.PotionType;
import net.ccbluex.liquidbounce.api.minecraft.util.IAxisAlignedBB;
import net.ccbluex.liquidbounce.api.minecraft.util.IIChatComponent;
import net.ccbluex.liquidbounce.api.minecraft.util.IMovingObjectPosition;
import net.ccbluex.liquidbounce.api.minecraft.util.WBlockPos;
import net.ccbluex.liquidbounce.api.minecraft.util.WVec3;
import net.ccbluex.liquidbounce.api.minecraft.world.IWorldSettings;
import net.ccbluex.liquidbounce.event.AttackEvent;
import net.ccbluex.liquidbounce.event.EntityMovementEvent;
import net.ccbluex.liquidbounce.event.EventState;
import net.ccbluex.liquidbounce.event.EventTarget;
import net.ccbluex.liquidbounce.event.MotionEvent;
import net.ccbluex.liquidbounce.event.Render3DEvent;
import net.ccbluex.liquidbounce.event.StrafeEvent;
import net.ccbluex.liquidbounce.event.TickEvent;
import net.ccbluex.liquidbounce.event.UpdateEvent;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import net.ccbluex.liquidbounce.features.module.modules.color.Gident;
import net.ccbluex.liquidbounce.features.module.modules.color.Rainbow;
import net.ccbluex.liquidbounce.features.module.modules.combat.Criticals;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.combat.NoFriends;
import net.ccbluex.liquidbounce.features.module.modules.misc.AntiBot;
import net.ccbluex.liquidbounce.features.module.modules.misc.Teams;
import net.ccbluex.liquidbounce.features.module.modules.player.Blink;
import net.ccbluex.liquidbounce.features.module.modules.render.FreeCam;
import net.ccbluex.liquidbounce.features.module.modules.render.OldHitting;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.ui.font.GameFontRenderer;
import net.ccbluex.liquidbounce.utils.AnimationUtils;
import net.ccbluex.liquidbounce.utils.EntityUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.RaycastUtils;
import net.ccbluex.liquidbounce.utils.Rotation;
import net.ccbluex.liquidbounce.utils.RotationUtils;
import net.ccbluex.liquidbounce.utils.VecRotation;
import net.ccbluex.liquidbounce.utils.extensions.PlayerExtensionKt;
import net.ccbluex.liquidbounce.utils.misc.RandomUtils;
import net.ccbluex.liquidbounce.utils.render.BlendUtils;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.EaseUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.utils.timer.MSTimer;
import net.ccbluex.liquidbounce.utils.timer.TimeUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;

@ModuleInfo(name="KillAura", description="Automatically attacks targets around you.", category=ModuleCategory.COMBAT, keyBind=19)
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u00b2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\b\u001f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 \u00a8\u00012\u00020\u0001:\u0002\u00a8\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0006\u00109\u001a\u000203H\u0002J\t\u0010\u0085\u0001\u001a\u00020\u001bH\u0002J%\u0010\u0086\u0001\u001a\u00030\u0084\u00012\u0006\u00109\u001a\u0002032\u0007\u0010\u0087\u0001\u001a\u00020\u00152\b\u0010\u0088\u0001\u001a\u00030\u0089\u0001H\u0002J$\u0010\u008a\u0001\u001a\u00030\u0084\u00012\u0006\u00109\u001a\u0002032\u0007\u0010\u008b\u0001\u001a\u00020\t2\u0007\u0010\u008c\u0001\u001a\u00020\tH\u0002J\u0012\u0010\u008d\u0001\u001a\u00020\t2\u0007\u00109\u001a\u00030\u008e\u0001H\u0002J\u0011\u0010\u008f\u0001\u001a\u00020\u001b2\u0006\u00109\u001a\u000203H\u0002J\u0014\u0010\u0090\u0001\u001a\u00020\u001b2\t\u00109\u001a\u0005\u0018\u00010\u008e\u0001H\u0002J\n\u0010\u0091\u0001\u001a\u00030\u0084\u0001H\u0016J\n\u0010\u0092\u0001\u001a\u00030\u0084\u0001H\u0016J\u0014\u0010\u0093\u0001\u001a\u00030\u0084\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0007J\u0014\u0010\u0096\u0001\u001a\u00030\u0084\u00012\b\u0010\u0094\u0001\u001a\u00030\u0097\u0001H\u0007J\u0014\u0010\u0098\u0001\u001a\u00030\u0084\u00012\b\u0010\u0094\u0001\u001a\u00030\u0089\u0001H\u0007J\u0014\u0010\u0099\u0001\u001a\u00030\u0084\u00012\b\u0010\u0094\u0001\u001a\u00030\u009a\u0001H\u0007J\u0016\u0010\u009b\u0001\u001a\u00030\u0084\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u009c\u0001H\u0007J\u0014\u0010\u009d\u0001\u001a\u00030\u0084\u00012\b\u0010\u0094\u0001\u001a\u00030\u009e\u0001H\u0007J\n\u0010\u009f\u0001\u001a\u00030\u0084\u0001H\u0002J\u001d\u0010\u00a0\u0001\u001a\u00030\u0084\u00012\b\u0010\u00a1\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u00a2\u0001\u001a\u00020\u001bH\u0002J\n\u0010\u00a3\u0001\u001a\u00030\u0084\u0001H\u0002J\b\u0010\u00a4\u0001\u001a\u00030\u0084\u0001J\n\u0010\u00a5\u0001\u001a\u00030\u0084\u0001H\u0002J\u0012\u0010\u00a6\u0001\u001a\u00020\u001b2\u0007\u00109\u001a\u00030\u008e\u0001H\u0002J\n\u0010\u00a7\u0001\u001a\u00030\u0084\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010!\u001a\u00020\u001b8\u00c2\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001dR\u000e\u0010#\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u000103X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000206X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010C\u001a\u00020\u001b8F\u00a2\u0006\u0006\u001a\u0004\bC\u0010\u001dR\u000e\u0010D\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u000103X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010SX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bY\u0010ZR\u000e\u0010[\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010c\u001a\b\u0012\u0004\u0012\u00020\u00150dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u000206X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010t\u001a\u0004\u0018\u000103X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR\u0014\u0010y\u001a\u0002088VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bz\u0010{R\u001c\u0010|\u001a\u0004\u0018\u000103X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b}\u0010v\"\u0004\b~\u0010xR\u000e\u0010\u007f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000f\u0010\u0080\u0001\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u000206X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u00a9\u0001"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/KillAura;", "Lnet/ccbluex/liquidbounce/features/module/Module;", "()V", "BlockRangeValue", "Lnet/ccbluex/liquidbounce/value/FloatValue;", "aacValue", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "afterAttackValue", "al", "", "attackDelay", "", "attackTimer", "Lnet/ccbluex/liquidbounce/utils/timer/MSTimer;", "autoBlockFacing", "autoBlockPacketValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "autoBlockValue", "bb", "Lnet/ccbluex/liquidbounce/api/minecraft/util/IAxisAlignedBB;", "blockKey", "", "getBlockKey", "()I", "blockRate", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "blockingStatus", "", "getBlockingStatus", "()Z", "setBlockingStatus", "(Z)V", "brightnessValue", "cancelRun", "getCancelRun", "circleAccuracy", "circleAlpha", "circleBlue", "circleGreen", "circleRed", "circleValue", "clicks", "colorAlphaValue", "colorBlueValue", "colorGreenValue", "colorModeValue", "colorRedValue", "colorTeam", "containerOpen", "cooldownValue", "currentTarget", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "delayedBlockValue", "direction", "", "displayText", "", "entity", "espAnimation", "failRateValue", "fakeSharpValue", "fakeSwingValue", "fovValue", "hitable", "hitableValue", "hurtTimeValue", "interactAutoBlockValue", "isBlockingChestAura", "isUp", "jelloAlphaValue", "jelloFadeSpeedValue", "jelloGradientHeightValue", "jelloWidthValue", "keepSprintValue", "lastDeltaMS", "lastMS", "lastTarget", "lightingModeValue", "lightingSoundValue", "lightingValue", "limitedMultiTargetsValue", "livingRaycastValue", "markEntity", "Lnet/minecraft/entity/EntityLivingBase;", "markTimer", "markValue", "maxCPS", "maxPredictSize", "maxRange", "getMaxRange", "()F", "maxTurnSpeed", "minCPS", "minPredictSize", "minTurnSpeed", "noInventoryAttackValue", "noInventoryDelayValue", "outborderValue", "predictValue", "prevTargetEntities", "", "priorityValue", "progress", "randomCenterValue", "rangeSprintReducementValue", "rangeValue", "raycastIgnoredValue", "raycastValue", "rotationStrafeValue", "rotations", "saturationValue", "silentRotationValue", "stopSprintAir", "swingValue", "switchDelayValue", "switchTimer", "syncEntity", "getSyncEntity", "()Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;", "setSyncEntity", "(Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntityLivingBase;)V", "tag", "getTag", "()Ljava/lang/String;", "target", "getTarget", "setTarget", "targetModeValue", "throughWallsRangeValue", "vanillamode", "yPos", "attackEntity", "", "canBlock", "drawESP", "color", "e", "Lnet/ccbluex/liquidbounce/event/Render3DEvent;", "esp", "partialTicks", "radius", "getRange", "Lnet/ccbluex/liquidbounce/api/minecraft/client/entity/IEntity;", "isAlive", "isEnemy", "onDisable", "onEnable", "onEntityMove", "event", "Lnet/ccbluex/liquidbounce/event/EntityMovementEvent;", "onMotion", "Lnet/ccbluex/liquidbounce/event/MotionEvent;", "onRender3D", "onStrafe", "Lnet/ccbluex/liquidbounce/event/StrafeEvent;", "onTick", "Lnet/ccbluex/liquidbounce/event/TickEvent;", "onUpdate", "Lnet/ccbluex/liquidbounce/event/UpdateEvent;", "runAttack", "startBlocking", "interactEntity", "interact", "stopBlocking", "update", "updateHitable", "updateRotations", "updateTarget", "Companion", "Relaxed"})
public final class KillAura
extends Module {
    private final IntegerValue maxCPS = new IntegerValue(this, "MaxCPS", 8, 1, 20){
        final /* synthetic */ KillAura this$0;

        protected void onChanged(int oldValue, int newValue) {
            int i = ((Number)KillAura.access$getMinCPS$p(this.this$0).get()).intValue();
            if (i > newValue) {
                this.set(i);
            }
            KillAura.access$setAttackDelay$p(this.this$0, TimeUtils.randomClickDelay(((Number)KillAura.access$getMinCPS$p(this.this$0).get()).intValue(), ((Number)this.get()).intValue()));
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final IntegerValue minCPS = new IntegerValue(this, "MinCPS", 5, 1, 20){
        final /* synthetic */ KillAura this$0;

        protected void onChanged(int oldValue, int newValue) {
            int i = ((Number)KillAura.access$getMaxCPS$p(this.this$0).get()).intValue();
            if (i < newValue) {
                this.set(i);
            }
            KillAura.access$setAttackDelay$p(this.this$0, TimeUtils.randomClickDelay(((Number)this.get()).intValue(), ((Number)KillAura.access$getMaxCPS$p(this.this$0).get()).intValue()));
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final IntegerValue hurtTimeValue = new IntegerValue("HurtTime", 10, 0, 10);
    private final FloatValue cooldownValue = new FloatValue("Cooldown", 1.0f, 0.0f, 1.0f);
    private final FloatValue rangeValue = new FloatValue("Range", 3.7f, 1.0f, 8.0f);
    private final FloatValue throughWallsRangeValue = new FloatValue("ThroughWallsRange", 3.0f, 0.0f, 8.0f);
    private final FloatValue rangeSprintReducementValue = new FloatValue("RangeSprintReducement", 0.0f, 0.0f, 0.4f);
    private final ListValue priorityValue = new ListValue("Priority", new String[]{"Health", "Distance", "Direction", "LivingTime", "HurtResitanTime"}, "Distance");
    private final ListValue targetModeValue = new ListValue("TargetMode", new String[]{"Single", "Switch", "Multi"}, "Switch");
    private final BoolValue swingValue = new BoolValue("Swing", true);
    private final BoolValue keepSprintValue = new BoolValue("KeepSprint", true);
    private final BoolValue stopSprintAir = new BoolValue("StopSprintOnAir", true);
    private final ListValue autoBlockValue = new ListValue("AutoBlock", new String[]{"AllTime", "Range", "Off"}, "Off");
    private final FloatValue BlockRangeValue = new FloatValue("BlockRange", 3.0f, 0.0f, 8.0f);
    private final ListValue autoBlockPacketValue = new ListValue("AutoBlockPacket", new String[]{"Vanilla", "Fake", "Mouse", "GameSettings", "UseItem"}, "Simple");
    private final ListValue vanillamode = new ListValue("VanillaMode", new String[]{"TryUseItem", "UseItem", "C08", "OldC08"}, "TryUseItem");
    private final BoolValue interactAutoBlockValue = new BoolValue("InteractAutoBlock", true);
    private final BoolValue delayedBlockValue = new BoolValue("AutoBlock-AfterTck", false);
    private final BoolValue afterAttackValue = new BoolValue("AutoBlock-AfterAttack", false);
    private final BoolValue autoBlockFacing = new BoolValue("AutoBlockFacing", false);
    private final IntegerValue blockRate = new IntegerValue("BlockRate", 100, 1, 100);
    private final BoolValue raycastValue = new BoolValue("RayCast", true);
    private final BoolValue raycastIgnoredValue = new BoolValue("RayCastIgnored", false);
    private final BoolValue livingRaycastValue = new BoolValue("LivingRayCast", true);
    private final BoolValue aacValue = new BoolValue("AAC", false);
    private final FloatValue maxTurnSpeed = new FloatValue(this, "MaxTurnSpeed", 180.0f, 0.0f, 180.0f){
        final /* synthetic */ KillAura this$0;

        protected void onChanged(float oldValue, float newValue) {
            float v = ((Number)KillAura.access$getMinTurnSpeed$p(this.this$0).get()).floatValue();
            if (v > newValue) {
                this.set(Float.valueOf(v));
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final FloatValue minTurnSpeed = new FloatValue(this, "MinTurnSpeed", 180.0f, 0.0f, 180.0f){
        final /* synthetic */ KillAura this$0;

        protected void onChanged(float oldValue, float newValue) {
            float v = ((Number)KillAura.access$getMaxTurnSpeed$p(this.this$0).get()).floatValue();
            if (v < newValue) {
                this.set(Float.valueOf(v));
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final BoolValue lightingValue = new BoolValue("Lighting", false);
    private final ListValue lightingModeValue = new ListValue("Lighting-Mode", new String[]{"Dead", "Attack"}, "Dead");
    private final BoolValue lightingSoundValue = new BoolValue("Lighting-Sound", true);
    private final BoolValue randomCenterValue = new BoolValue("RandomCenter", true);
    private final ListValue rotations = new ListValue("RotationMode", new String[]{"None", "New", "Liquidbounce", "BackTrack", "Test1", "Test2", "HytRotation"}, "New");
    private final BoolValue outborderValue = new BoolValue("Outborder", false);
    private final BoolValue silentRotationValue = new BoolValue("SilentRotation", true);
    private final ListValue rotationStrafeValue = new ListValue("Strafe", new String[]{"Off", "Strict", "Silent"}, "Off");
    private final FloatValue fovValue = new FloatValue("FOV", 180.0f, 0.0f, 180.0f);
    private final BoolValue hitableValue = new BoolValue("AlwaysHitable", true);
    private final IntegerValue switchDelayValue = new IntegerValue("SwitchDelay", 300, 1, 2000);
    private final BoolValue predictValue = new BoolValue("Predict", true);
    private final FloatValue maxPredictSize = new FloatValue(this, "MaxPredictSize", 1.0f, 0.1f, 5.0f){
        final /* synthetic */ KillAura this$0;

        protected void onChanged(float oldValue, float newValue) {
            float v = ((Number)KillAura.access$getMinPredictSize$p(this.this$0).get()).floatValue();
            if (v > newValue) {
                this.set(Float.valueOf(v));
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final FloatValue minPredictSize = new FloatValue(this, "MinPredictSize", 1.0f, 0.1f, 5.0f){
        final /* synthetic */ KillAura this$0;

        protected void onChanged(float oldValue, float newValue) {
            float v = ((Number)KillAura.access$getMaxPredictSize$p(this.this$0).get()).floatValue();
            if (v < newValue) {
                this.set(Float.valueOf(v));
            }
        }
        {
            this.this$0 = $outer;
            super($super_call_param$1, $super_call_param$2, $super_call_param$3, $super_call_param$4);
        }
    };
    private final FloatValue failRateValue = new FloatValue("FailRate", 0.0f, 0.0f, 100.0f);
    private final BoolValue fakeSwingValue = new BoolValue("FakeSwing", true);
    private final BoolValue noInventoryAttackValue = new BoolValue("NoInvAttack", false);
    private final IntegerValue noInventoryDelayValue = new IntegerValue("NoInvDelay", 200, 0, 500);
    private final IntegerValue limitedMultiTargetsValue = new IntegerValue("LimitedMultiTargets", 0, 0, 50);
    private final ListValue markValue = new ListValue("Mark", new String[]{"Liquid", "FDP", "Block", "Jello", "Plat", "Red", "Sims", "None"}, "FDP");
    private final ListValue colorModeValue = new ListValue("JelloColor", new String[]{"Custom", "Rainbow", "Sky", "LiquidSlowly", "Fade", "Health", "Gident"}, "Custom");
    private final IntegerValue colorRedValue = new IntegerValue("JelloRed", 255, 0, 255);
    private final IntegerValue colorGreenValue = new IntegerValue("JelloGreen", 255, 0, 255);
    private final IntegerValue colorBlueValue = new IntegerValue("JelloBlue", 255, 0, 255);
    private final IntegerValue colorAlphaValue = new IntegerValue("JelloAlpha", 255, 0, 255);
    private final FloatValue saturationValue = new FloatValue("Saturation", 1.0f, 0.0f, 1.0f);
    private final FloatValue brightnessValue = new FloatValue("Brightness", 1.0f, 0.0f, 1.0f);
    private final BoolValue colorTeam = new BoolValue("JelloTeam", false);
    private final FloatValue jelloAlphaValue = new FloatValue("JelloEndAlphaPercent", 0.4f, 0.0f, 1.0f);
    private final FloatValue jelloWidthValue = new FloatValue("JelloCircleWidth", 3.0f, 0.01f, 5.0f);
    private final FloatValue jelloGradientHeightValue = new FloatValue("JelloGradientHeight", 3.0f, 1.0f, 8.0f);
    private final FloatValue jelloFadeSpeedValue = new FloatValue("JelloFadeSpeed", 0.1f, 0.01f, 0.5f);
    private final BoolValue fakeSharpValue = new BoolValue("FakeSharp", true);
    private final BoolValue circleValue = new BoolValue("Circle", true);
    private final IntegerValue circleRed = new IntegerValue("CircleRed", 255, 0, 255);
    private final IntegerValue circleGreen = new IntegerValue("CircleGreen", 255, 0, 255);
    private final IntegerValue circleBlue = new IntegerValue("CircleBlue", 255, 0, 255);
    private final IntegerValue circleAlpha = new IntegerValue("CircleAlpha", 255, 0, 255);
    private final IntegerValue circleAccuracy = new IntegerValue("CircleAccuracy", 15, 0, 60);
    private final int blockKey = MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().getKeyCode();
    private final MSTimer switchTimer = new MSTimer();
    @Nullable
    private IEntityLivingBase target;
    private IEntityLivingBase currentTarget;
    private boolean hitable;
    private final List<Integer> prevTargetEntities;
    private IEntityLivingBase lastTarget;
    private double direction;
    private double yPos;
    private double progress;
    private long lastMS;
    private long lastDeltaMS;
    private float al;
    private final MSTimer attackTimer;
    private long attackDelay;
    private int clicks;
    private EntityLivingBase markEntity;
    private final MSTimer markTimer;
    private long containerOpen;
    private String displayText;
    private IAxisAlignedBB bb;
    private IEntityLivingBase entity;
    private boolean blockingStatus;
    private double espAnimation;
    private boolean isUp;
    @Nullable
    private IEntityLivingBase syncEntity;
    private static int killCounts;
    public static final Companion Companion;

    public final int getBlockKey() {
        return this.blockKey;
    }

    @Nullable
    public final IEntityLivingBase getTarget() {
        return this.target;
    }

    public final void setTarget(@Nullable IEntityLivingBase iEntityLivingBase) {
        this.target = iEntityLivingBase;
    }

    public final boolean getBlockingStatus() {
        return this.blockingStatus;
    }

    public final void setBlockingStatus(boolean bl) {
        this.blockingStatus = bl;
    }

    @Nullable
    public final IEntityLivingBase getSyncEntity() {
        return this.syncEntity;
    }

    public final void setSyncEntity(@Nullable IEntityLivingBase iEntityLivingBase) {
        this.syncEntity = iEntityLivingBase;
    }

    @Override
    public void onEnable() {
        if (MinecraftInstance.mc.getThePlayer() == null) {
            return;
        }
        if (MinecraftInstance.mc.getTheWorld() == null) {
            return;
        }
        this.updateTarget();
    }

    @Override
    public void onDisable() {
        this.target = null;
        this.currentTarget = null;
        this.lastTarget = null;
        this.hitable = false;
        this.prevTargetEntities.clear();
        this.attackTimer.reset();
        this.clicks = 0;
        this.stopBlocking();
    }

    @EventTarget
    public final void onMotion(@NotNull MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (((Boolean)this.stopSprintAir.get()).booleanValue()) {
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP.getOnGround()) {
                this.keepSprintValue.set(true);
            } else {
                this.keepSprintValue.set(false);
            }
        }
        if (event.getEventState() == EventState.POST) {
            if (this.target == null) {
                return;
            }
            if (this.currentTarget == null) {
                return;
            }
            this.updateHitable();
            if (!StringsKt.equals((String)this.autoBlockValue.get(), "off", true) && ((Boolean)this.delayedBlockValue.get()).booleanValue() && this.canBlock()) {
                IEntityLivingBase iEntityLivingBase = this.currentTarget;
                if (iEntityLivingBase == null) {
                    Intrinsics.throwNpe();
                }
                this.startBlocking(iEntityLivingBase, (Boolean)this.interactAutoBlockValue.get());
            }
            return;
        }
        if (StringsKt.equals((String)this.rotationStrafeValue.get(), "Off", true)) {
            this.update();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @EventTarget
    public final void onStrafe(@NotNull StrafeEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (StringsKt.equals((String)this.rotationStrafeValue.get(), "Off", true)) {
            return;
        }
        this.update();
        if (this.currentTarget == null || RotationUtils.targetRotation == null) return;
        String string = (String)this.rotationStrafeValue.get();
        boolean bl = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        string = string3;
        switch (string.hashCode()) {
            case -902327211: {
                if (!string.equals("silent")) return;
                break;
            }
            case -891986231: {
                if (!string.equals("strict")) return;
                Rotation rotation = RotationUtils.targetRotation;
                if (rotation == null) {
                    return;
                }
                Rotation rotation2 = rotation;
                float yaw = rotation2.component1();
                float strafe = event.getStrafe();
                float forward = event.getForward();
                float friction = event.getFriction();
                float f = strafe * strafe + forward * forward;
                if (f >= 1.0E-4f) {
                    IEntityPlayerSP player;
                    boolean bl2 = false;
                    if ((f = (float)Math.sqrt(f)) < 1.0f) {
                        f = 1.0f;
                    }
                    f = friction / f;
                    strafe *= f;
                    forward *= f;
                    float f2 = (float)((double)yaw * Math.PI / (double)180.0f);
                    boolean bl3 = false;
                    float yawSin = (float)Math.sin(f2);
                    float f3 = (float)((double)yaw * Math.PI / (double)180.0f);
                    boolean bl4 = false;
                    float yawCos = (float)Math.cos(f3);
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        Intrinsics.throwNpe();
                    }
                    IEntityPlayerSP iEntityPlayerSP2 = player = iEntityPlayerSP;
                    iEntityPlayerSP2.setMotionX(iEntityPlayerSP2.getMotionX() + (double)(strafe * yawCos - forward * yawSin));
                    IEntityPlayerSP iEntityPlayerSP3 = player;
                    iEntityPlayerSP3.setMotionZ(iEntityPlayerSP3.getMotionZ() + (double)(forward * yawCos + strafe * yawSin));
                }
                event.cancelEvent();
                return;
            }
        }
        this.update();
        RotationUtils.targetRotation.applyStrafeToPlayer(event);
        event.cancelEvent();
        return;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void update() {
        KillAura this_$iv = this;
        boolean $i$f$getCancelRun = false;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP.isSpectator()) return;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        if (!this_$iv.isAlive(iEntityPlayerSP2)) return;
        if (LiquidBounce.INSTANCE.getModuleManager().get(Blink.class).getState()) return;
        if (LiquidBounce.INSTANCE.getModuleManager().get(FreeCam.class).getState()) {
            return;
        }
        boolean bl = false;
        if (bl) return;
        if (((Boolean)this.noInventoryAttackValue.get()).booleanValue()) {
            if (MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen())) return;
            if (System.currentTimeMillis() - this.containerOpen < ((Number)this.noInventoryDelayValue.get()).longValue()) {
                return;
            }
        }
        this.updateTarget();
        if (this.target == null) {
            this.stopBlocking();
            return;
        }
        this.currentTarget = this.target;
        if (StringsKt.equals((String)this.targetModeValue.get(), "Switch", true)) return;
        if (!this.isEnemy(this.currentTarget)) return;
        this.target = this.currentTarget;
    }

    @EventTarget
    public final void onTick(@Nullable TickEvent event) {
        if (StringsKt.equals((String)this.markValue.get(), "jello", true)) {
            this.al = AnimationUtils.changer(this.al, this.target != null ? ((Number)this.jelloFadeSpeedValue.get()).floatValue() : -((Number)this.jelloFadeSpeedValue.get()).floatValue(), 0.0f, ((Number)this.colorAlphaValue.get()).floatValue() / 255.0f);
        }
    }

    /*
     * Unable to fully structure code
     */
    @EventTarget
    public final void onUpdate(@NotNull UpdateEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (((Boolean)this.lightingValue.get()).booleanValue()) {
            var2_2 = (String)this.lightingModeValue.get();
            var3_4 = false;
            v0 = var2_2;
            if (v0 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            v1 = v0.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(v1, "(this as java.lang.String).toLowerCase()");
            var2_2 = v1;
            switch (var2_2.hashCode()) {
                case -1407259064: {
                    if (!var2_2.equals("attack")) ** break;
                    break;
                }
                case 3079268: {
                    if (!var2_2.equals("dead")) ** break;
                    if (this.target != null) {
                        if (this.lastTarget == null) {
                            v2 = this.target;
                        } else {
                            v3 = this.lastTarget;
                            if (v3 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (v3.getHealth() <= (float)false) {
                                v4 = MinecraftInstance.mc.getNetHandler2();
                                v5 = (World)MinecraftInstance.mc2.field_71441_e;
                                v6 = this.lastTarget;
                                if (v6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                v7 = v6.getPosX();
                                v8 = this.lastTarget;
                                if (v8 == null) {
                                    Intrinsics.throwNpe();
                                }
                                v9 = v8.getPosY();
                                v10 = this.lastTarget;
                                if (v10 == null) {
                                    Intrinsics.throwNpe();
                                }
                                v4.func_147292_a(new SPacketSpawnGlobalEntity((Entity)new EntityLightningBolt(v5, v7, v9, v10.getPosZ(), true)));
                                if (((Boolean)this.lightingSoundValue.get()).booleanValue()) {
                                    MinecraftInstance.mc.getSoundHandler().playSound("entity.lightning.impact", 0.5f);
                                }
                            }
                            v2 = this.target;
                        }
                        this.lastTarget = v2;
                        ** break;
                    }
                    if (this.lastTarget == null) ** break;
                    v11 = this.lastTarget;
                    if (v11 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!(v11.getHealth() <= (float)false)) ** break;
                    v12 = MinecraftInstance.mc.getNetHandler2();
                    v13 = (World)MinecraftInstance.mc2.field_71441_e;
                    v14 = this.lastTarget;
                    if (v14 == null) {
                        Intrinsics.throwNpe();
                    }
                    v15 = v14.getPosX();
                    v16 = this.lastTarget;
                    if (v16 == null) {
                        Intrinsics.throwNpe();
                    }
                    v17 = v16.getPosY();
                    v18 = this.lastTarget;
                    if (v18 == null) {
                        Intrinsics.throwNpe();
                    }
                    v12.func_147292_a(new SPacketSpawnGlobalEntity((Entity)new EntityLightningBolt(v13, v15, v17, v18.getPosZ(), true)));
                    if (((Boolean)this.lightingSoundValue.get()).booleanValue()) {
                        MinecraftInstance.mc.getSoundHandler().playSound("entity.lightning.impact", 0.5f);
                    }
                    this.lastTarget = this.target;
                    ** break;
                }
            }
            v19 = MinecraftInstance.mc.getNetHandler2();
            v20 = (World)MinecraftInstance.mc2.field_71441_e;
            v21 = this.target;
            if (v21 == null) {
                Intrinsics.throwNpe();
            }
            v22 = v21.getPosX();
            v23 = this.target;
            if (v23 == null) {
                Intrinsics.throwNpe();
            }
            v24 = v23.getPosY();
            v25 = this.target;
            if (v25 == null) {
                Intrinsics.throwNpe();
            }
            v19.func_147292_a(new SPacketSpawnGlobalEntity((Entity)new EntityLightningBolt(v20, v22, v24, v25.getPosZ(), true)));
            if (!((Boolean)this.lightingSoundValue.get()).booleanValue()) ** break;
            MinecraftInstance.mc.getSoundHandler().playSound("entity.lightning.impact", 0.5f);
            ** break;
        }
lbl84:
        // 11 sources

        if (this.syncEntity != null) {
            v26 = this.syncEntity;
            if (v26 == null) {
                Intrinsics.throwNpe();
            }
            if (v26.isDead()) {
                ++KillAura.killCounts;
                this.syncEntity = null;
            }
        }
        this_$iv = this;
        $i$f$getCancelRun = false;
        v27 = MinecraftInstance.mc.getThePlayer();
        if (v27 == null) {
            Intrinsics.throwNpe();
        }
        if (v27.isSpectator()) ** GOTO lbl-1000
        v28 = MinecraftInstance.mc.getThePlayer();
        if (v28 == null) {
            Intrinsics.throwNpe();
        }
        if (!KillAura.access$isAlive(this_$iv, v28) || LiquidBounce.INSTANCE.getModuleManager().get(Blink.class).getState() || LiquidBounce.INSTANCE.getModuleManager().get(FreeCam.class).getState()) lbl-1000:
        // 2 sources

        {
            v29 = true;
        } else {
            v29 = false;
        }
        if (v29) {
            this.target = null;
            this.currentTarget = null;
            this.hitable = false;
            this.stopBlocking();
            return;
        }
        if (((Boolean)this.noInventoryAttackValue.get()).booleanValue() && (MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen()) || System.currentTimeMillis() - this.containerOpen < ((Number)this.noInventoryDelayValue.get()).longValue())) {
            this.target = null;
            this.currentTarget = null;
            this.hitable = false;
            if (MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen())) {
                this.containerOpen = System.currentTimeMillis();
            }
            return;
        }
        if (this.target != null && this.currentTarget != null) {
            v30 = MinecraftInstance.mc.getThePlayer();
            if (v30 == null) {
                Intrinsics.throwNpe();
            }
            if (v30.getCooledAttackStrength(0.0f) >= ((Number)this.cooldownValue.get()).floatValue()) {
                while (this.clicks > 0) {
                    this.runAttack();
                    var2_3 = this.clicks;
                    this.clicks = var2_3 + -1;
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    private final void esp(IEntityLivingBase entity, float partialTicks, float radius) {
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GLUtils.startSmooth();
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glLineWidth((float)1.0f);
        GL11.glBegin((int)3);
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)partialTicks - MinecraftInstance.mc.getRenderManager().getViewerPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)partialTicks - MinecraftInstance.mc.getRenderManager().getViewerPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)partialTicks - MinecraftInstance.mc.getRenderManager().getViewerPosZ();
        int n = 0;
        int n2 = 360;
        while (n <= n2) {
            void i;
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            double d = (double)i / 50.0 * 1.75;
            double d2 = (double)iEntityPlayerSP.getTicksExisted() / 70.0;
            boolean bl = false;
            double d3 = Math.sin(d);
            int n3 = Color.HSBtoRGB((float)(d2 + d3) % 1.0f, 0.7f, 1.0f);
            Color rainbow = new Color(n3);
            GL11.glColor3f((float)((float)rainbow.getRed() / 255.0f), (float)((float)rainbow.getGreen() / 255.0f), (float)((float)rainbow.getBlue() / 255.0f));
            d = (double)i * (Math.PI * 2) / 45.0;
            d2 = radius;
            double d4 = x;
            bl = false;
            d3 = Math.cos(d);
            double d5 = d4 + d2 * d3;
            d = (double)i * (Math.PI * 2) / 45.0;
            double d6 = radius;
            d3 = z;
            d2 = y + this.espAnimation;
            d4 = d5;
            bl = false;
            double d7 = Math.sin(d);
            GL11.glVertex3d((double)d4, (double)d2, (double)(d3 + d6 * d7));
            ++i;
        }
        GL11.glEnd();
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GLUtils.endSmooth();
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }

    private final void drawESP(IEntityLivingBase entity, int color, Render3DEvent e) {
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)e.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)e.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)e.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosZ();
        float radius = 0.15f;
        int side = 4;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)(y + (double)2), (double)z);
        GL11.glRotatef((float)(-entity.getWidth()), (float)0.0f, (float)1.0f, (float)0.0f);
        RenderUtils.glColor1(color);
        RenderUtils.enableSmoothLine(1.5f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        c.setDrawStyle(100012);
        RenderUtils.glColor(new Color(80, 255, 80, 200));
        c.draw(0.0f, radius, 0.3f, side, 1);
        c.setDrawStyle(100012);
        GL11.glTranslated((double)0.0, (double)0.0, (double)0.3);
        c.draw(radius, 0.0f, 0.3f, side, 1);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        c.setDrawStyle(100011);
        GL11.glTranslated((double)0.0, (double)0.0, (double)-0.3);
        RenderUtils.glColor1(color);
        c.draw(0.0f, radius, 0.3f, side, 1);
        c.setDrawStyle(100011);
        GL11.glTranslated((double)0.0, (double)0.0, (double)0.3);
        c.draw(radius, 0.0f, 0.3f, side, 1);
        RenderUtils.disableSmoothLine();
        GL11.glPopMatrix();
    }

    /*
     * Unable to fully structure code
     */
    @EventTarget
    public final void onRender3D(@NotNull Render3DEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        $fun$post3D$1 = onRender3D.1.INSTANCE;
        $fun$drawCircle$2 = onRender3D.2.INSTANCE;
        $fun$pre3D$3 = onRender3D.3.INSTANCE;
        $fun$getColor$4 = new Function1<IEntityLivingBase, Color>(this){
            final /* synthetic */ KillAura this$0;

            /*
             * WARNING - void declaration
             */
            @Nullable
            public final Color invoke(@Nullable IEntityLivingBase ent) {
                Color color;
                int[] counter = new int[]{0};
                if (ent instanceof EntityLivingBase) {
                    IEntityLivingBase entityLivingBase = ent;
                    if (StringsKt.equals((String)KillAura.access$getColorModeValue$p(this.this$0).get(), "Health", true)) {
                        return BlendUtils.getHealthColor(entityLivingBase.getHealth(), entityLivingBase.getMaxHealth());
                    }
                    if (((Boolean)KillAura.access$getColorTeam$p(this.this$0).get()).booleanValue()) {
                        IIChatComponent iIChatComponent = entityLivingBase.getDisplayName();
                        if (iIChatComponent == null) {
                            Intrinsics.throwNpe();
                        }
                        String string = iIChatComponent.getFormattedText();
                        int n = 0;
                        String string2 = string;
                        if (string2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        char[] cArray = string2.toCharArray();
                        Intrinsics.checkExpressionValueIsNotNull(cArray, "(this as java.lang.String).toCharArray()");
                        char[] chars = cArray;
                        int color2 = Integer.MAX_VALUE;
                        n = 0;
                        int n2 = chars.length;
                        while (n < n2) {
                            int index;
                            void i;
                            if (chars[i] == '\u00a7' && i + true < chars.length && (index = GameFontRenderer.Companion.getColorIndex(chars[i + true])) >= 0 && index <= 15) {
                                color2 = ColorUtils.hexColors[index];
                                break;
                            }
                            ++i;
                        }
                        return new Color(color2);
                    }
                }
                switch ((String)KillAura.access$getColorModeValue$p(this.this$0).get()) {
                    case "Gident": {
                        color = RenderUtils.getGradientOffset(new Color(((Number)Gident.redValue.get()).intValue(), ((Number)Gident.greenValue.get()).intValue(), ((Number)Gident.blueValue.get()).intValue()), new Color(((Number)Gident.redValue2.get()).intValue(), ((Number)Gident.greenValue2.get()).intValue(), ((Number)Gident.blueValue2.get()).intValue()), Math.abs((double)System.currentTimeMillis() / (double)((Number)Gident.gidentspeed.get()).intValue()));
                        break;
                    }
                    case "Custom": {
                        color = new Color(((Number)KillAura.access$getColorRedValue$p(this.this$0).get()).intValue(), ((Number)KillAura.access$getColorGreenValue$p(this.this$0).get()).intValue(), ((Number)KillAura.access$getColorBlueValue$p(this.this$0).get()).intValue());
                        break;
                    }
                    case "Rainbow": {
                        color = ColorUtils2.hslRainbow$default(counter[0] * 100 + 1, 0.0f, 0.0f, 100 * ((Number)Rainbow.rainbowSpeed.get()).intValue(), 0, 0.0f, 0.0f, 118, null);
                        break;
                    }
                    case "Sky": {
                        color = RenderUtils.skyRainbow(0, ((Number)KillAura.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)KillAura.access$getBrightnessValue$p(this.this$0).get()).floatValue());
                        break;
                    }
                    case "LiquidSlowly": {
                        color = ColorUtils.LiquidSlowly(System.nanoTime(), 0, ((Number)KillAura.access$getSaturationValue$p(this.this$0).get()).floatValue(), ((Number)KillAura.access$getBrightnessValue$p(this.this$0).get()).floatValue());
                        break;
                    }
                    default: {
                        color = ColorUtils.fade(new Color(((Number)KillAura.access$getColorRedValue$p(this.this$0).get()).intValue(), ((Number)KillAura.access$getColorGreenValue$p(this.this$0).get()).intValue(), ((Number)KillAura.access$getColorBlueValue$p(this.this$0).get()).intValue()), 0, 100);
                    }
                }
                return color;
            }
            {
                this.this$0 = killAura;
                super(1);
            }
        };
        if (((Boolean)this.circleValue.get()).booleanValue()) {
            GL11.glPushMatrix();
            v0 = MinecraftInstance.mc.getThePlayer();
            if (v0 == null) {
                Intrinsics.throwNpe();
            }
            v1 = v0.getLastTickPosX();
            v2 = MinecraftInstance.mc.getThePlayer();
            if (v2 == null) {
                Intrinsics.throwNpe();
            }
            v3 = v2.getPosX();
            v4 = MinecraftInstance.mc.getThePlayer();
            if (v4 == null) {
                Intrinsics.throwNpe();
            }
            v5 = v1 + (v3 - v4.getLastTickPosX()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosX();
            v6 = MinecraftInstance.mc.getThePlayer();
            if (v6 == null) {
                Intrinsics.throwNpe();
            }
            v7 = v6.getLastTickPosY();
            v8 = MinecraftInstance.mc.getThePlayer();
            if (v8 == null) {
                Intrinsics.throwNpe();
            }
            v9 = v8.getPosY();
            v10 = MinecraftInstance.mc.getThePlayer();
            if (v10 == null) {
                Intrinsics.throwNpe();
            }
            v11 = v7 + (v9 - v10.getLastTickPosY()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosY();
            v12 = MinecraftInstance.mc.getThePlayer();
            if (v12 == null) {
                Intrinsics.throwNpe();
            }
            v13 = v12.getLastTickPosZ();
            v14 = MinecraftInstance.mc.getThePlayer();
            if (v14 == null) {
                Intrinsics.throwNpe();
            }
            v15 = v14.getPosZ();
            v16 = MinecraftInstance.mc.getThePlayer();
            if (v16 == null) {
                Intrinsics.throwNpe();
            }
            GL11.glTranslated((double)v5, (double)v11, (double)(v13 + (v15 - v16.getLastTickPosZ()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks() - MinecraftInstance.mc.getRenderManager().getRenderPosZ()));
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2848);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2929);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)1.0f);
            GL11.glColor4f((float)((float)((Number)this.circleRed.get()).intValue() / 255.0f), (float)((float)((Number)this.circleGreen.get()).intValue() / 255.0f), (float)((float)((Number)this.circleBlue.get()).intValue() / 255.0f), (float)((float)((Number)this.circleAlpha.get()).intValue() / 255.0f));
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glBegin((int)3);
            var9_6 = 0;
            v17 = RangesKt.step(new IntRange(var9_6, 360), 61 - ((Number)this.circleAccuracy.get()).intValue());
            var6_11 = v17.getFirst();
            var7_15 = v17.getLast();
            var8_19 = v17.getStep();
            v18 = var6_11;
            v19 = var7_15;
            if (var8_19 >= 0 ? v18 <= v19 : v18 >= v19) {
                while (true) {
                    var9_7 = (double)i * 3.141592653589793 / 180.0;
                    var11_23 = false;
                    v20 = (float)Math.cos(var9_7) * ((Number)this.rangeValue.get()).floatValue();
                    var9_7 = (double)i * 3.141592653589793 / 180.0;
                    var34_20 = v20;
                    var11_23 = false;
                    var35_22 = Math.sin(var9_7);
                    GL11.glVertex2f((float)var34_20, (float)((float)var35_22 * ((Number)this.rangeValue.get()).floatValue()));
                    if (i == var7_15) break;
                    i += var8_19;
                }
            }
            i = 6.283185307179586;
            var8_19 = 0;
            v21 = (float)Math.cos(i) * ((Number)this.rangeValue.get()).floatValue();
            i = 6.283185307179586;
            var34_20 = v21;
            var8_19 = 0;
            var35_22 = Math.sin(i);
            GL11.glVertex2f((float)var34_20, (float)((float)var35_22 * ((Number)this.rangeValue.get()).floatValue()));
            GL11.glEnd();
            GL11.glDisable((int)3042);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2929);
            GL11.glDisable((int)2848);
            GL11.glPopMatrix();
        }
        this_$iv = this;
        $i$f$getCancelRun = false;
        v22 = MinecraftInstance.mc.getThePlayer();
        if (v22 == null) {
            Intrinsics.throwNpe();
        }
        if (v22.isSpectator()) ** GOTO lbl-1000
        v23 = MinecraftInstance.mc.getThePlayer();
        if (v23 == null) {
            Intrinsics.throwNpe();
        }
        if (!KillAura.access$isAlive(this_$iv, v23) || LiquidBounce.INSTANCE.getModuleManager().get(Blink.class).getState() || LiquidBounce.INSTANCE.getModuleManager().get(FreeCam.class).getState()) lbl-1000:
        // 2 sources

        {
            v24 = true;
        } else {
            v24 = false;
        }
        if (v24) {
            this.target = null;
            this.currentTarget = null;
            this.hitable = false;
            this.stopBlocking();
            return;
        }
        if (((Boolean)this.noInventoryAttackValue.get()).booleanValue() && (MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen()) || System.currentTimeMillis() - this.containerOpen < ((Number)this.noInventoryDelayValue.get()).longValue())) {
            this.target = null;
            this.currentTarget = null;
            this.hitable = false;
            if (MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen())) {
                this.containerOpen = System.currentTimeMillis();
            }
            return;
        }
        if (this.target == null) {
            return;
        }
        var6_13 = (String)this.markValue.get();
        $i$f$getCancelRun = false;
        v25 = var6_13;
        if (v25 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        v26 = v25.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(v26, "(this as java.lang.String).toLowerCase()");
        var6_13 = v26;
        tmp = -1;
        switch (var6_13.hashCode()) {
            case 112785: {
                if (!var6_13.equals("red")) break;
                tmp = 1;
                break;
            }
            case 101009364: {
                if (!var6_13.equals("jello")) break;
                tmp = 2;
                break;
            }
            case 101234: {
                if (!var6_13.equals("fdp")) break;
                tmp = 3;
                break;
            }
            case -1102567108: {
                if (!var6_13.equals("liquid")) break;
                tmp = 4;
                break;
            }
            case 3530364: {
                if (!var6_13.equals("sims")) break;
                tmp = 5;
                break;
            }
            case 93832333: {
                if (!var6_13.equals("block")) break;
                tmp = 6;
                break;
            }
            case 3443503: {
                if (!var6_13.equals("plat")) break;
                tmp = 7;
                break;
            }
        }
        switch (tmp) {
            case 4: {
                v27 = this.target;
                if (v27 == null) {
                    Intrinsics.throwNpe();
                }
                v28 = v27;
                v29 = this.target;
                if (v29 == null) {
                    Intrinsics.throwNpe();
                }
                RenderUtils.drawPlatform(v28, v29.getHurtTime() <= 0 ? new Color(37, 126, 255, 170) : new Color(255, 0, 0, 170));
                break;
            }
            case 7: {
                v30 = this.target;
                if (v30 == null) {
                    Intrinsics.throwNpe();
                }
                RenderUtils.drawPlatform(v30, this.hitable != false ? new Color(37, 126, 255, 70) : new Color(255, 0, 0, 70));
                break;
            }
            case 6: {
                v31 = this.target;
                if (v31 == null) {
                    Intrinsics.throwNpe();
                }
                bb = v31.getEntityBoundingBox();
                v32 = this.target;
                if (v32 == null) {
                    Intrinsics.throwNpe();
                }
                v32.setEntityBoundingBox(bb.expand(0.2, 0.2, 0.2));
                v33 = this.target;
                if (v33 == null) {
                    Intrinsics.throwNpe();
                }
                v34 = v33;
                v35 = this.target;
                if (v35 == null) {
                    Intrinsics.throwNpe();
                }
                RenderUtils.drawEntityBox(v34, v35.getHurtTime() <= 0 ? Color.GREEN : Color.RED, true);
                v36 = this.target;
                if (v36 == null) {
                    Intrinsics.throwNpe();
                }
                v36.setEntityBoundingBox(bb);
                break;
            }
            case 1: {
                v37 = this.target;
                if (v37 == null) {
                    Intrinsics.throwNpe();
                }
                v38 = v37;
                v39 = this.target;
                if (v39 == null) {
                    Intrinsics.throwNpe();
                }
                RenderUtils.drawPlatform(v38, v39.getHurtTime() <= 0 ? new Color(255, 255, 255, 255) : new Color(124, 215, 255, 255));
                break;
            }
            case 5: {
                radius = 0.15f;
                side = 4;
                GL11.glPushMatrix();
                v40 = this.target;
                if (v40 == null) {
                    Intrinsics.throwNpe();
                }
                v41 = v40.getLastTickPosX();
                v42 = this.target;
                if (v42 == null) {
                    Intrinsics.throwNpe();
                }
                v43 = v42.getPosX();
                v44 = this.target;
                if (v44 == null) {
                    Intrinsics.throwNpe();
                }
                v45 = v41 + (v43 - v44.getLastTickPosX()) * (double)event.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getViewerPosX();
                v46 = this.target;
                if (v46 == null) {
                    Intrinsics.throwNpe();
                }
                v47 = v46.getLastTickPosY();
                v48 = this.target;
                if (v48 == null) {
                    Intrinsics.throwNpe();
                }
                v49 = v48.getPosY();
                v50 = this.target;
                if (v50 == null) {
                    Intrinsics.throwNpe();
                }
                v51 = v47 + (v49 - v50.getLastTickPosY()) * (double)event.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getViewerPosY();
                v52 = this.target;
                if (v52 == null) {
                    Intrinsics.throwNpe();
                }
                v53 = v51 + (double)v52.getHeight() * 1.1;
                v54 = this.target;
                if (v54 == null) {
                    Intrinsics.throwNpe();
                }
                v55 = v54.getLastTickPosZ();
                v56 = this.target;
                if (v56 == null) {
                    Intrinsics.throwNpe();
                }
                v57 = v56.getPosZ();
                v58 = this.target;
                if (v58 == null) {
                    Intrinsics.throwNpe();
                }
                GL11.glTranslated((double)v45, (double)v53, (double)(v55 + (v57 - v58.getLastTickPosZ()) * (double)event.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getViewerPosZ()));
                v59 = this.target;
                if (v59 == null) {
                    Intrinsics.throwNpe();
                }
                GL11.glRotatef((float)(-v59.getWidth()), (float)0.0f, (float)1.0f, (float)0.0f);
                v60 = MinecraftInstance.mc.getThePlayer();
                if (v60 == null) {
                    Intrinsics.throwNpe();
                }
                GL11.glRotatef((float)(((float)v60.getTicksExisted() + MinecraftInstance.mc.getTimer().getRenderPartialTicks()) * (float)5), (float)0.0f, (float)1.0f, (float)0.0f);
                v61 = this.target;
                if (v61 == null) {
                    Intrinsics.throwNpe();
                }
                RenderUtils.glColor(v61.getHurtTime() <= 0 ? new Color(80, 255, 80) : new Color(255, 0, 0));
                RenderUtils.enableSmoothLine(1.5f);
                c = new Cylinder();
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                c.draw(0.0f, radius, 0.3f, side, 1);
                c.setDrawStyle(100012);
                GL11.glTranslated((double)0.0, (double)0.0, (double)0.3);
                c.draw(radius, 0.0f, 0.3f, side, 1);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslated((double)0.0, (double)0.0, (double)-0.3);
                c.draw(0.0f, radius, 0.3f, side, 1);
                GL11.glTranslated((double)0.0, (double)0.0, (double)0.3);
                c.draw(radius, 0.0f, 0.3f, side, 1);
                RenderUtils.disableSmoothLine();
                GL11.glPopMatrix();
                break;
            }
            case 3: {
                drawTime = (int)(System.currentTimeMillis() % (long)1500);
                drawMode = drawTime > 750;
                drawPercent = (double)drawTime / 750.0;
                drawPercent = !drawMode ? (double)true - drawPercent : (drawPercent -= (double)true);
                drawPercent = EaseUtils.easeInOutQuad(drawPercent);
                GL11.glPushMatrix();
                GL11.glDisable((int)3553);
                GL11.glEnable((int)2848);
                GL11.glEnable((int)2881);
                GL11.glEnable((int)2832);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glHint((int)3154, (int)4354);
                GL11.glHint((int)3155, (int)4354);
                GL11.glHint((int)3153, (int)4354);
                GL11.glDisable((int)2929);
                GL11.glDepthMask((boolean)false);
                v62 = this.target;
                if (v62 == null) {
                    Intrinsics.throwNpe();
                }
                bb = v62.getEntityBoundingBox();
                radius = bb.getMaxX() - bb.getMinX() + 0.3;
                height = bb.getMaxY() - bb.getMinY();
                v63 = this.target;
                if (v63 == null) {
                    Intrinsics.throwNpe();
                }
                v64 = v63.getLastTickPosX();
                v65 = this.target;
                if (v65 == null) {
                    Intrinsics.throwNpe();
                }
                v66 = v65.getPosX();
                v67 = this.target;
                if (v67 == null) {
                    Intrinsics.throwNpe();
                }
                x = v64 + (v66 - v67.getLastTickPosX()) * (double)event.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getViewerPosX();
                v68 = this.target;
                if (v68 == null) {
                    Intrinsics.throwNpe();
                }
                v69 = v68.getLastTickPosY();
                v70 = this.target;
                if (v70 == null) {
                    Intrinsics.throwNpe();
                }
                v71 = v70.getPosY();
                v72 = this.target;
                if (v72 == null) {
                    Intrinsics.throwNpe();
                }
                y = v69 + (v71 - v72.getLastTickPosY()) * (double)event.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getViewerPosY() + height * drawPercent;
                v73 = this.target;
                if (v73 == null) {
                    Intrinsics.throwNpe();
                }
                v74 = v73.getLastTickPosZ();
                v75 = this.target;
                if (v75 == null) {
                    Intrinsics.throwNpe();
                }
                v76 = v75.getPosZ();
                v77 = this.target;
                if (v77 == null) {
                    Intrinsics.throwNpe();
                }
                z = v74 + (v76 - v77.getLastTickPosZ()) * (double)event.getPartialTicks() - MinecraftInstance.mc.getRenderManager().getViewerPosZ();
                GL11.glLineWidth((float)((float)(radius * (double)5.0f)));
                GL11.glBegin((int)3);
                var22_35 = 0;
                var23_37 = 360;
                while (var22_35 <= var23_37) {
                    v78 = MinecraftInstance.mc.getThePlayer();
                    if (v78 == null) {
                        Intrinsics.throwNpe();
                    }
                    var25_41 = (double)i / 50.0 * 1.75;
                    var36_45 = (double)v78.getTicksExisted() / 70.0;
                    var27_43 = false;
                    var38_46 = Math.sin(var25_41);
                    var44_49 = Color.HSBtoRGB((float)(var36_45 + var38_46) % 1.0f, 0.7f, 1.0f);
                    rainbow = new Color(var44_49);
                    GL11.glColor3f((float)((float)rainbow.getRed() / 255.0f), (float)((float)rainbow.getGreen() / 255.0f), (float)((float)rainbow.getBlue() / 255.0f));
                    var25_41 = (double)i * 6.283185307179586 / 45.0;
                    var36_45 = radius;
                    var34_21 = x;
                    var27_43 = false;
                    var38_46 = Math.cos(var25_41);
                    v79 = var34_21 + var36_45 * var38_46;
                    var25_41 = (double)i * 6.283185307179586 / 45.0;
                    var40_47 = radius;
                    var38_46 = z;
                    var36_45 = y;
                    var34_21 = v79;
                    var27_43 = false;
                    var42_48 = Math.sin(var25_41);
                    GL11.glVertex3d((double)var34_21, (double)var36_45, (double)(var38_46 + var40_47 * var42_48));
                    ++i;
                }
                GL11.glEnd();
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2929);
                GL11.glDisable((int)2848);
                GL11.glDisable((int)2881);
                GL11.glEnable((int)2832);
                GL11.glEnable((int)3553);
                GL11.glPopMatrix();
                break;
            }
            case 2: {
                lastY = this.yPos;
                $fun$easeInOutQuart$5 = onRender3D.5.INSTANCE;
                if (this.al > 0.0f) {
                    if (System.currentTimeMillis() - this.lastMS >= 1000L) {
                        this.direction = -this.direction;
                        this.lastMS = System.currentTimeMillis();
                    }
                    weird = this.direction > (double)false ? System.currentTimeMillis() - this.lastMS : 1000L - (System.currentTimeMillis() - this.lastMS);
                    this.progress = (double)weird / 1000.0;
                    this.lastDeltaMS = System.currentTimeMillis() - this.lastMS;
                } else {
                    this.lastMS = System.currentTimeMillis() - this.lastDeltaMS;
                }
                if (this.target != null) {
                    v80 = this.entity = this.target;
                    if (v80 == null) {
                        Intrinsics.throwNpe();
                    }
                    this.bb = v80.getEntityBoundingBox();
                }
                if (this.bb == null || this.entity == null) {
                    return;
                }
                v81 = this.bb;
                if (v81 == null) {
                    Intrinsics.throwNpe();
                }
                v82 = v81.getMaxX();
                v83 = this.bb;
                if (v83 == null) {
                    Intrinsics.throwNpe();
                }
                radius = v82 - v83.getMinX();
                v84 = this.bb;
                if (v84 == null) {
                    Intrinsics.throwNpe();
                }
                v85 = v84.getMaxY();
                v86 = this.bb;
                if (v86 == null) {
                    Intrinsics.throwNpe();
                }
                height = v85 - v86.getMinY();
                v87 = this.entity;
                if (v87 == null) {
                    Intrinsics.throwNpe();
                }
                v88 = v87.getLastTickPosX();
                v89 = this.entity;
                if (v89 == null) {
                    Intrinsics.throwNpe();
                }
                v90 = v89.getPosX();
                v91 = this.entity;
                if (v91 == null) {
                    Intrinsics.throwNpe();
                }
                posX = v88 + (v90 - v91.getLastTickPosX()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks();
                v92 = this.entity;
                if (v92 == null) {
                    Intrinsics.throwNpe();
                }
                v93 = v92.getLastTickPosY();
                v94 = this.entity;
                if (v94 == null) {
                    Intrinsics.throwNpe();
                }
                v95 = v94.getPosY();
                v96 = this.entity;
                if (v96 == null) {
                    Intrinsics.throwNpe();
                }
                posY = v93 + (v95 - v96.getLastTickPosY()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks();
                v97 = this.entity;
                if (v97 == null) {
                    Intrinsics.throwNpe();
                }
                v98 = v97.getLastTickPosZ();
                v99 = this.entity;
                if (v99 == null) {
                    Intrinsics.throwNpe();
                }
                v100 = v99.getPosZ();
                v101 = this.entity;
                if (v101 == null) {
                    Intrinsics.throwNpe();
                }
                posZ = v98 + (v100 - v101.getLastTickPosZ()) * (double)MinecraftInstance.mc.getTimer().getRenderPartialTicks();
                this.yPos = $fun$easeInOutQuart$5.invoke(this.progress) * height;
                deltaY = (this.direction > (double)false ? this.yPos - lastY : lastY - this.yPos) * -this.direction * ((Number)this.jelloGradientHeightValue.get()).doubleValue();
                if (this.al <= (float)false && this.entity != null) {
                    this.entity = null;
                    return;
                }
                v102 = colour = $fun$getColor$4.invoke(this.entity);
                if (v102 == null) {
                    Intrinsics.throwNpe();
                }
                r = (float)v102.getRed() / 255.0f;
                g = (float)colour.getGreen() / 255.0f;
                b = (float)colour.getBlue() / 255.0f;
                $fun$pre3D$3.invoke();
                GL11.glTranslated((double)(-MinecraftInstance.mc.getRenderManager().getViewerPosX()), (double)(-MinecraftInstance.mc.getRenderManager().getViewerPosY()), (double)(-MinecraftInstance.mc.getRenderManager().getViewerPosZ()));
                GL11.glBegin((int)8);
                var26_52 = 0;
                var27_44 = 360;
                while (var26_52 <= var27_44) {
                    calc = (double)i * 3.141592653589793 / (double)180;
                    posX2 = posX - Math.sin(calc) * radius;
                    posZ2 = posZ + Math.cos(calc) * radius;
                    GL11.glColor4f((float)r, (float)g, (float)b, (float)0.0f);
                    GL11.glVertex3d((double)posX2, (double)(posY + this.yPos + deltaY), (double)posZ2);
                    GL11.glColor4f((float)r, (float)g, (float)b, (float)(this.al * ((Number)this.jelloAlphaValue.get()).floatValue()));
                    GL11.glVertex3d((double)posX2, (double)(posY + this.yPos), (double)posZ2);
                    ++i;
                }
                GL11.glEnd();
                $fun$drawCircle$2.invoke(posX, posY + this.yPos, posZ, ((Number)this.jelloWidthValue.get()).floatValue(), radius, r, g, b, this.al);
                $fun$post3D$1.invoke();
                break;
            }
        }
        if (this.currentTarget != null && this.attackTimer.hasTimePassed(this.attackDelay)) {
            v103 = this.currentTarget;
            if (v103 == null) {
                Intrinsics.throwNpe();
            }
            if (v103.getHurtTime() <= ((Number)this.hurtTimeValue.get()).intValue()) {
                var6_14 = this.clicks;
                this.clicks = var6_14 + 1;
                this.attackTimer.reset();
                this.attackDelay = TimeUtils.randomClickDelay(((Number)this.minCPS.get()).intValue(), ((Number)this.maxCPS.get()).intValue());
            }
        }
    }

    @EventTarget
    public final void onEntityMove(@NotNull EntityMovementEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        IEntity movedEntity = event.getMovedEntity();
        if (this.target == null || Intrinsics.areEqual(movedEntity, this.currentTarget) ^ true) {
            return;
        }
        this.updateHitable();
    }

    /*
     * Unable to fully structure code
     */
    private final void runAttack() {
        if (this.target == null) {
            return;
        }
        if (this.currentTarget == null) {
            return;
        }
        v0 = MinecraftInstance.mc.getThePlayer();
        if (v0 == null) {
            return;
        }
        thePlayer = v0;
        v1 = MinecraftInstance.mc.getTheWorld();
        if (v1 == null) {
            return;
        }
        theWorld = v1;
        failRate = ((Number)this.failRateValue.get()).floatValue();
        swing = (Boolean)this.swingValue.get();
        multi = StringsKt.equals((String)this.targetModeValue.get(), "Multi", true);
        v2 = openInventory = (Boolean)this.aacValue.get() != false && MinecraftInstance.classProvider.isGuiContainer(MinecraftInstance.mc.getCurrentScreen()) != false;
        if (!(failRate > (float)false)) ** GOTO lbl-1000
        v3 = new Random();
        if ((float)v3.nextInt(100) <= failRate) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = failHit = false;
        }
        if (openInventory) {
            MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketCloseWindow());
        }
        if (!this.hitable || failHit) {
            if (swing && (((Boolean)this.fakeSwingValue.get()).booleanValue() || failHit)) {
                thePlayer.swingItem();
            }
        } else {
            if (!multi) {
                v5 = this.currentTarget;
                if (v5 == null) {
                    Intrinsics.throwNpe();
                }
                this.attackEntity(v5);
            } else {
                targets = 0;
                for (IEntity entity : theWorld.getLoadedEntityList()) {
                    distance = PlayerExtensionKt.getDistanceToEntityBox(thePlayer, entity);
                    if (!MinecraftInstance.classProvider.isEntityLivingBase(entity) || !this.isEnemy(entity) || !(distance <= (double)this.getRange(entity))) continue;
                    this.attackEntity(entity.asEntityLivingBase());
                    if (((Number)this.limitedMultiTargetsValue.get()).intValue() == 0 || ((Number)this.limitedMultiTargetsValue.get()).intValue() > ++targets) continue;
                    break;
                }
            }
            if (StringsKt.equals((String)this.targetModeValue.get(), "Switch", true)) {
                if (this.switchTimer.hasTimePassed(((Number)this.switchDelayValue.get()).intValue())) {
                    if (((Boolean)this.aacValue.get()).booleanValue()) {
                        v6 = this.target;
                        if (v6 == null) {
                            Intrinsics.throwNpe();
                        }
                        v7 = v6.getEntityId();
                    } else {
                        v8 = this.currentTarget;
                        if (v8 == null) {
                            Intrinsics.throwNpe();
                        }
                        v7 = v8.getEntityId();
                    }
                    this.prevTargetEntities.add(v7);
                    this.switchTimer.reset();
                }
            } else {
                if (((Boolean)this.aacValue.get()).booleanValue()) {
                    v9 = this.target;
                    if (v9 == null) {
                        Intrinsics.throwNpe();
                    }
                    v10 = v9.getEntityId();
                } else {
                    v11 = this.currentTarget;
                    if (v11 == null) {
                        Intrinsics.throwNpe();
                    }
                    v10 = v11.getEntityId();
                }
                this.prevTargetEntities.add(v10);
            }
            if (Intrinsics.areEqual(this.target, this.currentTarget)) {
                this.target = null;
            }
        }
        if (openInventory) {
            var13_12 = MinecraftInstance.mc.getNetHandler();
            $i$f$createOpenInventoryPacket = false;
            v12 = WrapperImpl.INSTANCE.getClassProvider();
            v13 = LiquidBounce.INSTANCE.getWrapper().getMinecraft().getThePlayer();
            if (v13 == null) {
                Intrinsics.throwNpe();
            }
            var14_13 = v12.createCPacketEntityAction(v13, ICPacketEntityAction.WAction.OPEN_INVENTORY);
            var13_12.addToSendQueue(var14_13);
        }
    }

    private final void updateTarget() {
        this.target = null;
        int hurtTime = ((Number)this.hurtTimeValue.get()).intValue();
        float fov = ((Number)this.fovValue.get()).floatValue();
        boolean switchMode = StringsKt.equals((String)this.targetModeValue.get(), "Switch", true);
        boolean bl = false;
        List targets = new ArrayList();
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        IWorldClient theWorld = iWorldClient;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        for (IEntity collection : theWorld.getLoadedEntityList()) {
            if (!MinecraftInstance.classProvider.isEntityLivingBase(collection) || !this.isEnemy(collection) || switchMode && this.prevTargetEntities.contains(collection.getEntityId())) continue;
            double distance = PlayerExtensionKt.getDistanceToEntityBox(thePlayer, collection);
            double entityFov = RotationUtils.getRotationDifference(collection);
            if (!(distance <= (double)this.getMaxRange()) || fov != 180.0f && !(entityFov <= (double)fov) || collection.asEntityLivingBase().getHurtTime() > hurtTime) continue;
            targets.add(collection.asEntityLivingBase());
        }
        String string = (String)this.priorityValue.get();
        boolean bl2 = false;
        String string2 = string;
        if (string2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String string3 = string2.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(string3, "(this as java.lang.String).toLowerCase()");
        switch (string3) {
            case "distance": {
                List $this$sortBy$iv = targets;
                boolean $i$f$sortBy = false;
                if ($this$sortBy$iv.size() <= 1) break;
                List list = $this$sortBy$iv;
                boolean bl3 = false;
                Comparator comparator = new Comparator<T>(thePlayer){
                    final /* synthetic */ IEntityPlayerSP $thePlayer$inlined;
                    {
                        this.$thePlayer$inlined = iEntityPlayerSP;
                    }

                    public final int compare(T a, T b) {
                        boolean bl = false;
                        IEntityLivingBase it = (IEntityLivingBase)a;
                        boolean bl2 = false;
                        Comparable comparable = Double.valueOf(PlayerExtensionKt.getDistanceToEntityBox(this.$thePlayer$inlined, it));
                        it = (IEntityLivingBase)b;
                        Comparable comparable2 = comparable;
                        bl2 = false;
                        Double d = PlayerExtensionKt.getDistanceToEntityBox(this.$thePlayer$inlined, it);
                        return ComparisonsKt.compareValues(comparable2, (Comparable)d);
                    }
                };
                CollectionsKt.sortWith(list, comparator);
                break;
            }
            case "health": {
                List $this$sortBy$iv = targets;
                boolean $i$f$sortBy = false;
                if ($this$sortBy$iv.size() <= 1) break;
                List list = $this$sortBy$iv;
                boolean bl4 = false;
                Comparator comparator = new Comparator<T>(){

                    public final int compare(T a, T b) {
                        boolean bl = false;
                        IEntityLivingBase it = (IEntityLivingBase)a;
                        boolean bl2 = false;
                        Comparable comparable = Float.valueOf(it.getHealth());
                        it = (IEntityLivingBase)b;
                        Comparable comparable2 = comparable;
                        bl2 = false;
                        Float f = Float.valueOf(it.getHealth());
                        return ComparisonsKt.compareValues(comparable2, (Comparable)f);
                    }
                };
                CollectionsKt.sortWith(list, comparator);
                break;
            }
            case "direction": {
                List $this$sortBy$iv = targets;
                boolean $i$f$sortBy = false;
                if ($this$sortBy$iv.size() <= 1) break;
                List list = $this$sortBy$iv;
                boolean bl5 = false;
                Comparator comparator = new Comparator<T>(){

                    public final int compare(T a, T b) {
                        boolean bl = false;
                        IEntityLivingBase it = (IEntityLivingBase)a;
                        boolean bl2 = false;
                        Comparable comparable = Double.valueOf(RotationUtils.getRotationDifference(it));
                        it = (IEntityLivingBase)b;
                        Comparable comparable2 = comparable;
                        bl2 = false;
                        Double d = RotationUtils.getRotationDifference(it);
                        return ComparisonsKt.compareValues(comparable2, (Comparable)d);
                    }
                };
                CollectionsKt.sortWith(list, comparator);
                break;
            }
            case "livingtime": {
                List $this$sortBy$iv = targets;
                boolean $i$f$sortBy = false;
                if ($this$sortBy$iv.size() <= 1) break;
                List list = $this$sortBy$iv;
                boolean bl6 = false;
                Comparator comparator = new Comparator<T>(){

                    public final int compare(T a, T b) {
                        boolean bl = false;
                        IEntityLivingBase it = (IEntityLivingBase)a;
                        boolean bl2 = false;
                        Comparable comparable = Integer.valueOf(-it.getTicksExisted());
                        it = (IEntityLivingBase)b;
                        Comparable comparable2 = comparable;
                        bl2 = false;
                        Integer n = -it.getTicksExisted();
                        return ComparisonsKt.compareValues(comparable2, (Comparable)n);
                    }
                };
                CollectionsKt.sortWith(list, comparator);
                break;
            }
            case "HurtResitanTime": {
                List $this$sortBy$iv = targets;
                boolean $i$f$sortBy = false;
                if ($this$sortBy$iv.size() <= 1) break;
                List list = $this$sortBy$iv;
                boolean bl7 = false;
                Comparator comparator = new Comparator<T>(){

                    public final int compare(T a, T b) {
                        boolean bl = false;
                        IEntityLivingBase it = (IEntityLivingBase)a;
                        boolean bl2 = false;
                        Comparable comparable = Integer.valueOf(it.getHurtResistantTime());
                        it = (IEntityLivingBase)b;
                        Comparable comparable2 = comparable;
                        bl2 = false;
                        Integer n = it.getHurtResistantTime();
                        return ComparisonsKt.compareValues(comparable2, (Comparable)n);
                    }
                };
                CollectionsKt.sortWith(list, comparator);
                break;
            }
        }
        for (IEntityLivingBase iEntityLivingBase : targets) {
            if (!this.updateRotations(iEntityLivingBase)) continue;
            this.target = iEntityLivingBase;
            return;
        }
        Collection collection = this.prevTargetEntities;
        boolean bl8 = false;
        if (!collection.isEmpty()) {
            this.prevTargetEntities.clear();
            this.updateTarget();
        }
    }

    private final boolean isEnemy(IEntity entity) {
        if (MinecraftInstance.classProvider.isEntityLivingBase(entity) && entity != null && (EntityUtils.targetDead || this.isAlive(entity.asEntityLivingBase())) && Intrinsics.areEqual(entity, MinecraftInstance.mc.getThePlayer()) ^ true) {
            if (!EntityUtils.targetInvisible && entity.isInvisible()) {
                return false;
            }
            if (EntityUtils.targetPlayer && MinecraftInstance.classProvider.isEntityPlayer(entity)) {
                IEntityPlayer player = entity.asEntityPlayer();
                if (player.isSpectator() || AntiBot.isBot(player)) {
                    return false;
                }
                if (PlayerExtensionKt.isClientFriend(player) && !LiquidBounce.INSTANCE.getModuleManager().get(NoFriends.class).getState()) {
                    return false;
                }
                Module module = LiquidBounce.INSTANCE.getModuleManager().get(Teams.class);
                if (module == null) {
                    throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.misc.Teams");
                }
                Teams teams = (Teams)module;
                return !teams.getState() || !teams.isInYourTeam(entity.asEntityLivingBase());
            }
            return EntityUtils.targetMobs && PlayerExtensionKt.isMob(entity) || EntityUtils.targetAnimals && PlayerExtensionKt.isAnimal(entity);
        }
        return false;
    }

    /*
     * WARNING - void declaration
     */
    private final void attackEntity(IEntityLivingBase entity) {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntityPlayerSP thePlayer = iEntityPlayerSP;
        if (!StringsKt.equals((String)this.autoBlockPacketValue.get(), "Vanilla", true)) {
            IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP2 == null) {
                Intrinsics.throwNpe();
            }
            if (iEntityPlayerSP2.isBlocking() || this.blockingStatus) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.RELEASE_USE_ITEM, WBlockPos.Companion.getORIGIN(), MinecraftInstance.classProvider.getEnumFacing(EnumFacingType.DOWN)));
                if (((Boolean)this.afterAttackValue.get()).booleanValue()) {
                    this.blockingStatus = false;
                }
            }
        }
        LiquidBounce.INSTANCE.getEventManager().callEvent(new AttackEvent(entity));
        if (((Boolean)this.swingValue.get()).booleanValue()) {
            // empty if block
        }
        MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketUseEntity((IEntity)entity, ICPacketUseEntity.WAction.ATTACK));
        if (((Boolean)this.swingValue.get()).booleanValue()) {
            thePlayer.swingItem();
        }
        if (((Boolean)this.keepSprintValue.get()).booleanValue()) {
            if (!(!(thePlayer.getFallDistance() > 0.0f) || thePlayer.getOnGround() || thePlayer.isOnLadder() || thePlayer.isInWater() || thePlayer.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.BLINDNESS)) || thePlayer.isRiding())) {
                thePlayer.onCriticalHit(entity);
            }
            if (MinecraftInstance.functions.getModifierForCreature(thePlayer.getHeldItem(), entity.getCreatureAttribute()) > 0.0f) {
                thePlayer.onEnchantmentCritical(entity);
            }
        } else if (MinecraftInstance.mc.getPlayerController().getCurrentGameType() != IWorldSettings.WGameType.SPECTATOR) {
            thePlayer.attackTargetEntityWithCurrentItem(entity);
        }
        Module module = LiquidBounce.INSTANCE.getModuleManager().get(Criticals.class);
        if (module == null) {
            throw new TypeCastException("null cannot be cast to non-null type net.ccbluex.liquidbounce.features.module.modules.combat.Criticals");
        }
        Criticals criticals = (Criticals)module;
        int n = 0;
        int n2 = 2;
        while (n <= n2) {
            void i;
            if (thePlayer.getFallDistance() > 0.0f && !thePlayer.getOnGround() && !thePlayer.isOnLadder() && !thePlayer.isInWater() && !thePlayer.isPotionActive(MinecraftInstance.classProvider.getPotionEnum(PotionType.BLINDNESS)) && thePlayer.getRidingEntity() == null || criticals.getState() && criticals.getMsTimer().hasTimePassed(((Number)criticals.getDelayValue().get()).intValue()) && !thePlayer.isInWater() && !thePlayer.isInLava() && !thePlayer.isInWeb()) {
                IEntityLivingBase iEntityLivingBase = this.target;
                if (iEntityLivingBase == null) {
                    Intrinsics.throwNpe();
                }
                thePlayer.onCriticalHit(iEntityLivingBase);
            }
            IItemStack iItemStack = thePlayer.getHeldItem();
            IEntityLivingBase iEntityLivingBase = this.target;
            if (iEntityLivingBase == null) {
                Intrinsics.throwNpe();
            }
            if (MinecraftInstance.functions.getModifierForCreature(iItemStack, iEntityLivingBase.getCreatureAttribute()) > 0.0f || ((Boolean)this.fakeSharpValue.get()).booleanValue()) {
                IEntityLivingBase iEntityLivingBase2 = this.target;
                if (iEntityLivingBase2 == null) {
                    Intrinsics.throwNpe();
                }
                thePlayer.onEnchantmentCritical(iEntityLivingBase2);
            }
            ++i;
        }
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP3.isBlocking() || !StringsKt.equals((String)this.autoBlockValue.get(), "off", true) && this.canBlock()) {
            if (((Boolean)this.delayedBlockValue.get()).booleanValue()) {
                return;
            }
            if (((Number)this.blockRate.get()).intValue() <= 0 || new Random().nextInt(100) > ((Number)this.blockRate.get()).intValue()) {
                return;
            }
            this.startBlocking(entity, (Boolean)this.interactAutoBlockValue.get());
        }
        thePlayer.resetCooldown();
    }

    private final boolean updateRotations(IEntity entity) {
        IAxisAlignedBB boundingBox = entity.getEntityBoundingBox();
        if (StringsKt.equals((String)this.rotations.get(), "test1", true)) {
            if (((Number)this.maxTurnSpeed.get()).floatValue() <= 0.0f) {
                return true;
            }
            if (((Boolean)this.predictValue.get()).booleanValue()) {
                boundingBox = boundingBox.offset((entity.getPosX() - entity.getPrevPosX()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosY() - entity.getPrevPosY()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosZ() - entity.getPrevPosZ()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()));
            }
            boolean bl = (Boolean)this.outborderValue.get() != false && !this.attackTimer.hasTimePassed(this.attackDelay / (long)2);
            boolean bl2 = (Boolean)this.randomCenterValue.get();
            boolean bl3 = (Boolean)this.predictValue.get();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            VecRotation vecRotation = RotationUtils.searchCenter(boundingBox, bl, bl2, bl3, PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) < ((Number)this.throughWallsRangeValue.get()).doubleValue(), this.getMaxRange());
            if (vecRotation == null) {
                return false;
            }
            VecRotation vecRotation2 = vecRotation;
            WVec3 wVec3 = vecRotation2.component1();
            Rotation rotation = vecRotation2.component2();
            Rotation rotation2 = RotationUtils.limitAngleChange(RotationUtils.serverRotation, RotationUtils.getNCPRotations(RotationUtils.getCenter(entity.getEntityBoundingBox()), false), (float)(Math.random() * (double)(((Number)this.maxTurnSpeed.get()).floatValue() - ((Number)this.minTurnSpeed.get()).floatValue()) + ((Number)this.minTurnSpeed.get()).doubleValue()));
            Intrinsics.checkExpressionValueIsNotNull(rotation2, "RotationUtils.limitAngle\u2026rnSpeed.get()).toFloat())");
            Rotation limitedRotation = rotation2;
            if (((Boolean)this.silentRotationValue.get()).booleanValue()) {
                RotationUtils.setTargetRotation(limitedRotation, (Boolean)this.aacValue.get() != false ? 15 : 0);
            } else {
                IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP2 == null) {
                    Intrinsics.throwNpe();
                }
                limitedRotation.toPlayer(iEntityPlayerSP2);
            }
            return true;
        }
        if (StringsKt.equals((String)this.rotations.get(), "test2", true)) {
            if (((Number)this.maxTurnSpeed.get()).floatValue() <= 0.0f) {
                return true;
            }
            if (((Boolean)this.predictValue.get()).booleanValue()) {
                boundingBox = boundingBox.offset((entity.getPosX() - entity.getPrevPosX()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosY() - entity.getPrevPosY()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosZ() - entity.getPrevPosZ()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()));
            }
            boolean bl = (Boolean)this.outborderValue.get() != false && !this.attackTimer.hasTimePassed(this.attackDelay / (long)2);
            boolean bl4 = (Boolean)this.randomCenterValue.get();
            boolean bl5 = (Boolean)this.predictValue.get();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            VecRotation vecRotation = RotationUtils.searchCenter(boundingBox, bl, bl4, bl5, PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) < ((Number)this.throughWallsRangeValue.get()).doubleValue(), this.getMaxRange());
            if (vecRotation == null) {
                return false;
            }
            Object limitedRotation = vecRotation;
            WVec3 vec = ((VecRotation)limitedRotation).component1();
            Rotation rotation = ((VecRotation)limitedRotation).component2();
            Rotation rotation3 = RotationUtils.limitAngleChange(RotationUtils.serverRotation, RotationUtils.toRotation(RotationUtils.getCenter(entity.getEntityBoundingBox()), false), (float)(Math.random() * (double)(((Number)this.maxTurnSpeed.get()).floatValue() - ((Number)this.minTurnSpeed.get()).floatValue()) + ((Number)this.minTurnSpeed.get()).doubleValue()));
            Intrinsics.checkExpressionValueIsNotNull(rotation3, "RotationUtils.limitAngle\u2026rnSpeed.get()).toFloat())");
            limitedRotation = rotation3;
            if (((Boolean)this.silentRotationValue.get()).booleanValue()) {
                RotationUtils.setTargetRotation((Rotation)limitedRotation, (Boolean)this.aacValue.get() != false ? 15 : 0);
            } else {
                IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP3 == null) {
                    Intrinsics.throwNpe();
                }
                ((Rotation)limitedRotation).toPlayer(iEntityPlayerSP3);
            }
            return true;
        }
        if (StringsKt.equals((String)this.rotations.get(), "HytRotation", true)) {
            if (((Boolean)this.predictValue.get()).booleanValue()) {
                boundingBox = boundingBox.offset((entity.getPosX() - entity.getPrevPosX()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosY() - entity.getPrevPosY()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosZ() - entity.getPrevPosZ()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()));
            }
            boolean bl = (Boolean)this.outborderValue.get() != false && !this.attackTimer.hasTimePassed(this.attackDelay / (long)2);
            boolean bl6 = (Boolean)this.randomCenterValue.get();
            boolean bl7 = (Boolean)this.predictValue.get();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            VecRotation vecRotation = RotationUtils.lockView(boundingBox, bl, bl6, bl7, PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) < ((Number)this.throughWallsRangeValue.get()).doubleValue(), this.getMaxRange());
            if (vecRotation == null) {
                return false;
            }
            VecRotation rotation = vecRotation;
            Rotation rotation4 = rotation.component2();
            Rotation rotation5 = RotationUtils.limitAngleChange(RotationUtils.serverRotation, rotation4, (float)(Math.random() * (double)(((Number)this.maxTurnSpeed.get()).floatValue() - ((Number)this.minTurnSpeed.get()).floatValue()) + ((Number)this.minTurnSpeed.get()).doubleValue()));
            Intrinsics.checkExpressionValueIsNotNull(rotation5, "RotationUtils.limitAngle\u2026rnSpeed.get()).toFloat())");
            Rotation limitedRotation = rotation5;
            if (((Boolean)this.silentRotationValue.get()).booleanValue()) {
                RotationUtils.setTargetRotation(limitedRotation, (Boolean)this.aacValue.get() != false ? 15 : 0);
            } else {
                IEntityPlayerSP iEntityPlayerSP4 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP4 == null) {
                    Intrinsics.throwNpe();
                }
                limitedRotation.toPlayer(iEntityPlayerSP4);
            }
            return true;
        }
        if (StringsKt.equals((String)this.rotations.get(), "New", true)) {
            if (((Number)this.maxTurnSpeed.get()).floatValue() <= 0.0f) {
                return true;
            }
            if (((Boolean)this.predictValue.get()).booleanValue()) {
                boundingBox = boundingBox.offset((entity.getPosX() - entity.getPrevPosX()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosY() - entity.getPrevPosY()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosZ() - entity.getPrevPosZ()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()));
            }
            boolean bl = (Boolean)this.outborderValue.get() != false && !this.attackTimer.hasTimePassed(this.attackDelay / (long)2);
            boolean bl8 = (Boolean)this.randomCenterValue.get();
            boolean bl9 = (Boolean)this.predictValue.get();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            VecRotation vecRotation = RotationUtils.searchCenter(boundingBox, bl, bl8, bl9, PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) < ((Number)this.throughWallsRangeValue.get()).doubleValue(), this.getMaxRange());
            if (vecRotation == null) {
                return false;
            }
            Object limitedRotation = vecRotation;
            WVec3 rotation4 = ((VecRotation)limitedRotation).component1();
            Rotation rotation = ((VecRotation)limitedRotation).component2();
            Rotation rotation6 = RotationUtils.limitAngleChange(RotationUtils.serverRotation, RotationUtils.getNewRotations(RotationUtils.getCenter(entity.getEntityBoundingBox()), false), (float)(Math.random() * (double)(((Number)this.maxTurnSpeed.get()).floatValue() - ((Number)this.minTurnSpeed.get()).floatValue()) + ((Number)this.minTurnSpeed.get()).doubleValue()));
            Intrinsics.checkExpressionValueIsNotNull(rotation6, "RotationUtils.limitAngle\u2026rnSpeed.get()).toFloat())");
            limitedRotation = rotation6;
            if (((Boolean)this.silentRotationValue.get()).booleanValue()) {
                RotationUtils.setTargetRotation((Rotation)limitedRotation, (Boolean)this.aacValue.get() != false ? 15 : 0);
            } else {
                IEntityPlayerSP iEntityPlayerSP5 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP5 == null) {
                    Intrinsics.throwNpe();
                }
                ((Rotation)limitedRotation).toPlayer(iEntityPlayerSP5);
            }
            return true;
        }
        if (StringsKt.equals((String)this.rotations.get(), "LiquidBounce", true)) {
            if (((Number)this.maxTurnSpeed.get()).floatValue() <= 0.0f) {
                return true;
            }
            if (((Boolean)this.predictValue.get()).booleanValue()) {
                boundingBox = boundingBox.offset((entity.getPosX() - entity.getPrevPosX()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosY() - entity.getPrevPosY()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosZ() - entity.getPrevPosZ()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()));
            }
            boolean bl = (Boolean)this.outborderValue.get() != false && !this.attackTimer.hasTimePassed(this.attackDelay / (long)2);
            boolean bl10 = (Boolean)this.randomCenterValue.get();
            boolean bl11 = (Boolean)this.predictValue.get();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            VecRotation vecRotation = RotationUtils.searchCenter(boundingBox, bl, bl10, bl11, PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) < ((Number)this.throughWallsRangeValue.get()).doubleValue(), this.getMaxRange());
            if (vecRotation == null) {
                return false;
            }
            Object limitedRotation = vecRotation;
            WVec3 vec = ((VecRotation)limitedRotation).component1();
            Rotation rotation = ((VecRotation)limitedRotation).component2();
            Rotation rotation7 = RotationUtils.limitAngleChange(RotationUtils.serverRotation, rotation, (float)(Math.random() * (double)(((Number)this.maxTurnSpeed.get()).floatValue() - ((Number)this.minTurnSpeed.get()).floatValue()) + ((Number)this.minTurnSpeed.get()).doubleValue()));
            Intrinsics.checkExpressionValueIsNotNull(rotation7, "RotationUtils.limitAngle\u2026rnSpeed.get()).toFloat())");
            limitedRotation = rotation7;
            if (((Boolean)this.silentRotationValue.get()).booleanValue()) {
                RotationUtils.setTargetRotation((Rotation)limitedRotation, (Boolean)this.aacValue.get() != false ? 15 : 0);
            } else {
                IEntityPlayerSP iEntityPlayerSP6 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP6 == null) {
                    Intrinsics.throwNpe();
                }
                ((Rotation)limitedRotation).toPlayer(iEntityPlayerSP6);
            }
            return true;
        }
        if (StringsKt.equals((String)this.rotations.get(), "BackTrack", true)) {
            if (((Boolean)this.predictValue.get()).booleanValue()) {
                boundingBox = boundingBox.offset((entity.getPosX() - entity.getPrevPosX()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosY() - entity.getPrevPosY()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()), (entity.getPosZ() - entity.getPrevPosZ()) * (double)RandomUtils.INSTANCE.nextFloat(((Number)this.minPredictSize.get()).floatValue(), ((Number)this.maxPredictSize.get()).floatValue()));
            }
            WVec3 wVec3 = RotationUtils.getCenter(entity.getEntityBoundingBox());
            boolean bl = (Boolean)this.predictValue.get();
            IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
            if (iEntityPlayerSP == null) {
                Intrinsics.throwNpe();
            }
            Rotation rotation = RotationUtils.limitAngleChange(RotationUtils.serverRotation, RotationUtils.OtherRotation(boundingBox, wVec3, bl, PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) < ((Number)this.throughWallsRangeValue.get()).doubleValue(), this.getMaxRange()), (float)(Math.random() * (double)(((Number)this.maxTurnSpeed.get()).floatValue() - ((Number)this.minTurnSpeed.get()).floatValue()) + ((Number)this.minTurnSpeed.get()).doubleValue()));
            Intrinsics.checkExpressionValueIsNotNull(rotation, "RotationUtils.limitAngle\u2026rnSpeed.get()).toFloat())");
            Rotation limitedRotation = rotation;
            if (((Boolean)this.silentRotationValue.get()).booleanValue()) {
                RotationUtils.setTargetRotation(limitedRotation, (Boolean)this.aacValue.get() != false ? 15 : 0);
            } else {
                IEntityPlayerSP iEntityPlayerSP7 = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP7 == null) {
                    Intrinsics.throwNpe();
                }
                limitedRotation.toPlayer(iEntityPlayerSP7);
                return true;
            }
        }
        return true;
    }

    private final void updateHitable() {
        if (((Boolean)this.hitableValue.get()).booleanValue()) {
            this.hitable = true;
            return;
        }
        if (((Number)this.maxTurnSpeed.get()).floatValue() <= 0.0f) {
            this.hitable = true;
            return;
        }
        double d = this.getMaxRange();
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        IEntity iEntity = iEntityPlayerSP;
        IEntityLivingBase iEntityLivingBase = this.target;
        if (iEntityLivingBase == null) {
            Intrinsics.throwNpe();
        }
        double d2 = PlayerExtensionKt.getDistanceToEntityBox(iEntity, iEntityLivingBase);
        boolean bl = false;
        double reach = Math.min(d, d2) + 1.0;
        if (((Boolean)this.raycastValue.get()).booleanValue()) {
            IEntity raycastedEntity2 = RaycastUtils.raycastEntity(reach, new RaycastUtils.EntityFilter(this){
                final /* synthetic */ KillAura this$0;

                /*
                 * Enabled force condition propagation
                 * Lifted jumps to return sites
                 */
                public boolean canRaycast(@Nullable IEntity entity) {
                    if (((Boolean)KillAura.access$getLivingRaycastValue$p(this.this$0).get()).booleanValue()) {
                        if (!MinecraftInstance.classProvider.isEntityLivingBase(entity)) return false;
                        if (MinecraftInstance.classProvider.isEntityArmorStand(entity)) return false;
                    }
                    if (KillAura.access$isEnemy(this.this$0, entity)) return true;
                    if ((Boolean)KillAura.access$getRaycastIgnoredValue$p(this.this$0).get() != false) return true;
                    if ((Boolean)KillAura.access$getAacValue$p(this.this$0).get() == false) return false;
                    IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
                    if (iWorldClient == null) {
                        Intrinsics.throwNpe();
                    }
                    IEntity iEntity = entity;
                    if (iEntity == null) {
                        Intrinsics.throwNpe();
                    }
                    Collection<IEntity> collection = iWorldClient.getEntitiesWithinAABBExcludingEntity(entity, iEntity.getEntityBoundingBox());
                    boolean bl = false;
                    if (collection.isEmpty()) return false;
                    return true;
                }
                {
                    this.this$0 = $outer;
                }
            });
            if (((Boolean)this.raycastValue.get()).booleanValue() && raycastedEntity2 != null && MinecraftInstance.classProvider.isEntityLivingBase(raycastedEntity2) && (LiquidBounce.INSTANCE.getModuleManager().get(NoFriends.class).getState() || !MinecraftInstance.classProvider.isEntityPlayer(raycastedEntity2) || !PlayerExtensionKt.isClientFriend(raycastedEntity2.asEntityPlayer()))) {
                this.currentTarget = raycastedEntity2.asEntityLivingBase();
            }
            this.hitable = ((Number)this.maxTurnSpeed.get()).floatValue() > 0.0f ? Intrinsics.areEqual(this.currentTarget, raycastedEntity2) : true;
        } else {
            this.hitable = RotationUtils.isFaced(this.currentTarget, reach);
        }
    }

    private final void startBlocking(IEntity interactEntity, boolean interact) {
        if (LiquidBounce.INSTANCE.getModuleManager().get(OldHitting.class).getState()) {
            if (this.autoBlockValue.equals("Range")) {
                IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                if (iEntityPlayerSP == null) {
                    Intrinsics.throwNpe();
                }
                if (PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, interactEntity) > ((Number)this.BlockRangeValue.get()).doubleValue()) {
                    return;
                }
            }
            if (this.blockingStatus) {
                return;
            }
            if (interact) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketUseEntity(interactEntity, interactEntity.getPositionVector()));
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketUseEntity(interactEntity, ICPacketUseEntity.WAction.INTERACT));
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "UseItem", true)) {
                KeyBinding.func_74510_a((int)MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().getKeyCode(), (boolean)true);
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "GameSettings", true)) {
                MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().setPressed(true);
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "Mouse", true)) {
                new Robot().mousePress(4096);
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "Vanilla", true)) {
                if (StringsKt.equals((String)this.vanillamode.get(), "TryUseItem", true)) {
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketTryUseItem(WEnumHand.MAIN_HAND));
                    MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketTryUseItem(WEnumHand.OFF_HAND));
                }
                if (StringsKt.equals((String)this.vanillamode.get(), "UseItem", true)) {
                    WEnumHand hand$iv;
                    IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        Intrinsics.throwNpe();
                    }
                    IItemStack iItemStack = iEntityPlayerSP.getInventory().getCurrentItemInHand();
                    WEnumHand wEnumHand = WEnumHand.MAIN_HAND;
                    IINetHandlerPlayClient iINetHandlerPlayClient2 = iINetHandlerPlayClient;
                    boolean $i$f$createUseItemPacket = false;
                    IPacket iPacket = WrapperImpl.INSTANCE.getClassProvider().createCPacketTryUseItem(hand$iv);
                    iINetHandlerPlayClient2.addToSendQueue(iPacket);
                    IINetHandlerPlayClient iINetHandlerPlayClient3 = MinecraftInstance.mc.getNetHandler();
                    IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP2 == null) {
                        Intrinsics.throwNpe();
                    }
                    IItemStack itemStack$iv = iEntityPlayerSP2.getInventory().getCurrentItemInHand();
                    hand$iv = WEnumHand.OFF_HAND;
                    iINetHandlerPlayClient2 = iINetHandlerPlayClient3;
                    $i$f$createUseItemPacket = false;
                    iPacket = WrapperImpl.INSTANCE.getClassProvider().createCPacketTryUseItem(hand$iv);
                    iINetHandlerPlayClient2.addToSendQueue(iPacket);
                }
                if (StringsKt.equals((String)this.vanillamode.get(), "OldC08", true)) {
                    IINetHandlerPlayClient iINetHandlerPlayClient = MinecraftInstance.mc.getNetHandler();
                    WBlockPos wBlockPos = new WBlockPos(-0.5534147541, -0.5534147541, -0.5534147541);
                    IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
                    if (iEntityPlayerSP == null) {
                        Intrinsics.throwNpe();
                    }
                    iINetHandlerPlayClient.addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerBlockPlacement(wBlockPos, 255, iEntityPlayerSP.getInventory().getCurrentItemInHand(), 0.0f, 0.0f, 0.0f));
                }
            }
            if (StringsKt.equals((String)this.vanillamode.get(), "C08", true)) {
                Minecraft minecraft = MinecraftInstance.mc2;
                Intrinsics.checkExpressionValueIsNotNull(minecraft, "mc2");
                NetHandlerPlayClient netHandlerPlayClient = minecraft.func_147114_u();
                if (netHandlerPlayClient == null) {
                    Intrinsics.throwNpe();
                }
                netHandlerPlayClient.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(new BlockPos(-0.5534147541, -0.5534147541, -0.5534147541), EnumFacing.WEST, EnumHand.OFF_HAND, 0.0f, 0.0f, 0.0f));
                Minecraft minecraft2 = MinecraftInstance.mc2;
                Intrinsics.checkExpressionValueIsNotNull(minecraft2, "mc2");
                NetHandlerPlayClient netHandlerPlayClient2 = minecraft2.func_147114_u();
                if (netHandlerPlayClient2 == null) {
                    Intrinsics.throwNpe();
                }
                netHandlerPlayClient2.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(new BlockPos(-0.5534147541, -0.5534147541, -0.5534147541), EnumFacing.WEST, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
        }
        this.blockingStatus = true;
    }

    private final void stopBlocking() {
        if (this.blockingStatus) {
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "Vanilla", true)) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.RELEASE_USE_ITEM, PlayerUtil.isMoving() ? new WBlockPos(-1, -1, -1) : WBlockPos.Companion.getORIGIN(), MinecraftInstance.classProvider.getEnumFacing(EnumFacingType.DOWN)));
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "UseItem", true)) {
                KeyBinding.func_74510_a((int)MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().getKeyCode(), (boolean)false);
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "GameSettings", true)) {
                MinecraftInstance.mc.getGameSettings().getKeyBindUseItem().setPressed(false);
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "Mouse", true)) {
                new Robot().mouseRelease(4096);
            }
            if (StringsKt.equals((String)this.autoBlockPacketValue.get(), "Vanilla", true)) {
                MinecraftInstance.mc.getNetHandler().addToSendQueue(MinecraftInstance.classProvider.createCPacketPlayerDigging(ICPacketPlayerDigging.WAction.RELEASE_USE_ITEM, WBlockPos.Companion.getORIGIN(), MinecraftInstance.classProvider.getEnumFacing(EnumFacingType.DOWN)));
            }
            this.blockingStatus = false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean getCancelRun() {
        int $i$f$getCancelRun = 0;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP.isSpectator()) return true;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        if (!this.isAlive(iEntityPlayerSP2)) return true;
        if (LiquidBounce.INSTANCE.getModuleManager().get(Blink.class).getState()) return true;
        if (!LiquidBounce.INSTANCE.getModuleManager().get(FreeCam.class).getState()) return false;
        return true;
    }

    private final boolean isAlive(IEntityLivingBase entity) {
        return entity.isEntityAlive() && entity.getHealth() > 0.0f || (Boolean)this.aacValue.get() != false && entity.getHurtTime() > 5;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean canBlock() {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if (iEntityPlayerSP.getHeldItem() == null) return false;
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        IItemStack iItemStack = iEntityPlayerSP2.getHeldItem();
        if (iItemStack == null) {
            Intrinsics.throwNpe();
        }
        if (!MinecraftInstance.classProvider.isItemSword(iItemStack.getItem())) return false;
        if ((Boolean)this.autoBlockFacing.get() == false) return true;
        IEntityLivingBase iEntityLivingBase = this.target;
        if (iEntityLivingBase == null) {
            Intrinsics.throwNpe();
        }
        IEntity iEntity = iEntityLivingBase;
        IEntityPlayerSP iEntityPlayerSP3 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP3 == null) {
            Intrinsics.throwNpe();
        }
        if (!(PlayerExtensionKt.getDistanceToEntityBox(iEntity, iEntityPlayerSP3) < (double)this.getMaxRange())) return true;
        IEntityLivingBase iEntityLivingBase2 = this.target;
        if (iEntityLivingBase2 == null) {
            Intrinsics.throwNpe();
        }
        IMovingObjectPosition iMovingObjectPosition = iEntityLivingBase2.rayTrace(this.getMaxRange(), 1.0f);
        if (iMovingObjectPosition == null) {
            Intrinsics.throwNpe();
        }
        if (iMovingObjectPosition.getTypeOfHit() == IMovingObjectPosition.WMovingObjectType.MISS) return false;
        return true;
    }

    private final float getMaxRange() {
        float f = ((Number)this.rangeValue.get()).floatValue();
        float f2 = ((Number)this.throughWallsRangeValue.get()).floatValue();
        boolean bl = false;
        return Math.max(f, f2);
    }

    private final float getRange(IEntity entity) {
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        float f = PlayerExtensionKt.getDistanceToEntityBox(iEntityPlayerSP, entity) >= ((Number)this.throughWallsRangeValue.get()).doubleValue() ? ((Number)this.rangeValue.get()).floatValue() : ((Number)this.rangeValue.get()).floatValue();
        IEntityPlayerSP iEntityPlayerSP2 = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP2 == null) {
            Intrinsics.throwNpe();
        }
        return f - (iEntityPlayerSP2.getSprinting() ? ((Number)this.rangeSprintReducementValue.get()).floatValue() : 0.0f);
    }

    @Override
    @NotNull
    public String getTag() {
        return (String)this.targetModeValue.get();
    }

    public final boolean isBlockingChestAura() {
        return this.getState() && this.target != null;
    }

    public KillAura() {
        List list;
        KillAura killAura = this;
        boolean bl = false;
        killAura.prevTargetEntities = list = (List)new ArrayList();
        this.direction = 1.0;
        this.lastMS = System.currentTimeMillis();
        this.attackTimer = new MSTimer();
        this.markTimer = new MSTimer();
        this.containerOpen = -1L;
        this.displayText = "";
        this.isUp = true;
    }

    static {
        Companion = new Companion(null);
    }

    public static final /* synthetic */ ListValue access$getColorModeValue$p(KillAura $this) {
        return $this.colorModeValue;
    }

    public static final /* synthetic */ BoolValue access$getColorTeam$p(KillAura $this) {
        return $this.colorTeam;
    }

    public static final /* synthetic */ IntegerValue access$getColorRedValue$p(KillAura $this) {
        return $this.colorRedValue;
    }

    public static final /* synthetic */ IntegerValue access$getColorGreenValue$p(KillAura $this) {
        return $this.colorGreenValue;
    }

    public static final /* synthetic */ IntegerValue access$getColorBlueValue$p(KillAura $this) {
        return $this.colorBlueValue;
    }

    public static final /* synthetic */ FloatValue access$getSaturationValue$p(KillAura $this) {
        return $this.saturationValue;
    }

    public static final /* synthetic */ FloatValue access$getBrightnessValue$p(KillAura $this) {
        return $this.brightnessValue;
    }

    public static final /* synthetic */ boolean access$isEnemy(KillAura $this, IEntity entity) {
        return $this.isEnemy(entity);
    }

    public static final /* synthetic */ BoolValue access$getLivingRaycastValue$p(KillAura $this) {
        return $this.livingRaycastValue;
    }

    public static final /* synthetic */ BoolValue access$getRaycastIgnoredValue$p(KillAura $this) {
        return $this.raycastIgnoredValue;
    }

    public static final /* synthetic */ BoolValue access$getAacValue$p(KillAura $this) {
        return $this.aacValue;
    }

    public static final /* synthetic */ IntegerValue access$getMinCPS$p(KillAura $this) {
        return $this.minCPS;
    }

    public static final /* synthetic */ long access$getAttackDelay$p(KillAura $this) {
        return $this.attackDelay;
    }

    public static final /* synthetic */ void access$setAttackDelay$p(KillAura $this, long l) {
        $this.attackDelay = l;
    }

    public static final /* synthetic */ IntegerValue access$getMaxCPS$p(KillAura $this) {
        return $this.maxCPS;
    }

    public static final /* synthetic */ FloatValue access$getMinTurnSpeed$p(KillAura $this) {
        return $this.minTurnSpeed;
    }

    public static final /* synthetic */ FloatValue access$getMaxTurnSpeed$p(KillAura $this) {
        return $this.maxTurnSpeed;
    }

    public static final /* synthetic */ FloatValue access$getMinPredictSize$p(KillAura $this) {
        return $this.minPredictSize;
    }

    public static final /* synthetic */ FloatValue access$getMaxPredictSize$p(KillAura $this) {
        return $this.maxPredictSize;
    }

    public static final int getKillCounts() {
        Companion companion = Companion;
        return killCounts;
    }

    public static final void setKillCounts(int n) {
        Companion companion = Companion;
        killCounts = n;
    }

    @Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lnet/ccbluex/liquidbounce/features/module/modules/combat/KillAura$Companion;", "", "()V", "killCounts", "", "killCounts$annotations", "getKillCounts", "()I", "setKillCounts", "(I)V", "Relaxed"})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void killCounts$annotations() {
        }

        public final int getKillCounts() {
            return killCounts;
        }

        public final void setKillCounts(int n) {
            killCounts = n;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

