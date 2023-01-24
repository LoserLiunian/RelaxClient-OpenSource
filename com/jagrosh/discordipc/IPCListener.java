/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONObject
 */
package com.jagrosh.discordipc;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.User;
import org.json.JSONObject;

public interface IPCListener {
    default public void onPacketSent(IPCClient client2, Packet packet) {
    }

    default public void onPacketReceived(IPCClient client2, Packet packet) {
    }

    default public void onActivityJoin(IPCClient client2, String secret) {
    }

    default public void onActivitySpectate(IPCClient client2, String secret) {
    }

    default public void onActivityJoinRequest(IPCClient client2, String secret, User user) {
    }

    default public void onReady(IPCClient client2) {
    }

    default public void onClose(IPCClient client2, JSONObject json) {
    }

    default public void onDisconnect(IPCClient client2, Throwable t) {
    }
}

