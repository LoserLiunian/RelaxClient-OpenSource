/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketAnimation
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.injection.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.network.play.server.ISPacketAnimation;
import net.ccbluex.liquidbounce.injection.backend.PacketImpl;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketAnimation;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\n\u00a8\u0006\r"}, d2={"Lnet/ccbluex/liquidbounce/injection/backend/SPacketAnimationImpl;", "T", "Lnet/minecraft/network/play/server/SPacketAnimation;", "Lnet/ccbluex/liquidbounce/injection/backend/PacketImpl;", "Lnet/ccbluex/liquidbounce/api/minecraft/network/play/server/ISPacketAnimation;", "wrapped", "(Lnet/minecraft/network/play/server/SPacketAnimation;)V", "animationType", "", "getAnimationType", "()I", "entityID", "getEntityID", "Relaxed"})
public final class SPacketAnimationImpl<T extends SPacketAnimation>
extends PacketImpl<T>
implements ISPacketAnimation {
    @Override
    public int getAnimationType() {
        return ((SPacketAnimation)this.getWrapped()).func_148977_d();
    }

    @Override
    public int getEntityID() {
        return ((SPacketAnimation)this.getWrapped()).func_148978_c();
    }

    public SPacketAnimationImpl(@NotNull T wrapped) {
        Intrinsics.checkParameterIsNotNull(wrapped, "wrapped");
        super((Packet)wrapped);
    }
}

