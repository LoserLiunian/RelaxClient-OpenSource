/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.api.minecraft.network.play.server;

import kotlin.Metadata;
import net.ccbluex.liquidbounce.api.minecraft.network.IPacket;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X\u00a6\u000e\u00a2\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007\u00a8\u0006\u000b"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/network/play/server/ISPacketPosLook;", "Lnet/ccbluex/liquidbounce/api/minecraft/network/IPacket;", "pitch", "", "getPitch", "()F", "setPitch", "(F)V", "yaw", "getYaw", "setYaw", "Relaxed"})
public interface ISPacketPosLook
extends IPacket {
    public float getYaw();

    public void setYaw(float var1);

    public float getPitch();

    public void setPitch(float var1);
}

