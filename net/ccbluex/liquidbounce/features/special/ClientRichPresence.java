/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.jetbrains.annotations.Nullable
 *  org.json.JSONObject
 */
package net.ccbluex.liquidbounce.features.special;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IServerData;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.special.ClientRichPresence;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.HttpUtils;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0017\u001a\u00020\u0015J\u0006\u0010\u0018\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2={"Lnet/ccbluex/liquidbounce/features/special/ClientRichPresence;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "()V", "appID", "", "assets", "", "", "ipcClient", "Lcom/jagrosh/discordipc/IPCClient;", "running", "", "showRichPresenceValue", "getShowRichPresenceValue", "()Z", "setShowRichPresenceValue", "(Z)V", "timestamp", "Ljava/time/OffsetDateTime;", "kotlin.jvm.PlatformType", "loadConfiguration", "", "setup", "shutdown", "update", "Relaxed"})
public final class ClientRichPresence
extends MinecraftInstance {
    private boolean showRichPresenceValue = true;
    private IPCClient ipcClient;
    private long appID;
    private final Map<String, String> assets;
    private final OffsetDateTime timestamp;
    private boolean running;

    public final boolean getShowRichPresenceValue() {
        return this.showRichPresenceValue;
    }

    public final void setShowRichPresenceValue(boolean bl) {
        this.showRichPresenceValue = bl;
    }

    public final void setup() {
        try {
            this.running = true;
            this.loadConfiguration();
            IPCClient iPCClient = this.ipcClient = new IPCClient(this.appID);
            if (iPCClient != null) {
                iPCClient.setListener(new IPCListener(this){
                    final /* synthetic */ ClientRichPresence this$0;

                    public void onReady(@Nullable IPCClient client2) {
                        ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>(this){
                            final /* synthetic */ setup.1 this$0;

                            public final void invoke() {
                                while (ClientRichPresence.access$getRunning$p(this.this$0.this$0)) {
                                    this.this$0.this$0.update();
                                    try {
                                        Thread.sleep(1000L);
                                    }
                                    catch (InterruptedException interruptedException) {}
                                }
                            }
                            {
                                this.this$0 = var1_1;
                                super(0);
                            }
                        }, 31, null);
                    }

                    public void onClose(@Nullable IPCClient client2, @Nullable JSONObject json) {
                        ClientRichPresence.access$setRunning$p(this.this$0, false);
                    }
                    {
                        this.this$0 = $outer;
                    }
                });
            }
            IPCClient iPCClient2 = this.ipcClient;
            if (iPCClient2 != null) {
                iPCClient2.connect(new DiscordBuild[0]);
            }
        }
        catch (Throwable e) {
            ClientUtils.getLogger().error("Failed to setup Discord RPC.", e);
        }
    }

    /*
     * WARNING - void declaration
     */
    public final void update() {
        block6: {
            RichPresence.Builder builder = new RichPresence.Builder();
            builder.setStartTimestamp(this.timestamp);
            if (this.assets.containsKey("logo")) {
                builder.setLargeImage(this.assets.get("logo"), "MC 1.12.2 - Relaxed b1");
            }
            if (MinecraftInstance.mc.getThePlayer() != null) {
                int n;
                void $this$count$iv;
                IServerData serverData = MinecraftInstance.mc.getCurrentServerData();
                builder.setDetails("Server: " + (MinecraftInstance.mc.isIntegratedServerRunning() || serverData == null ? "Singleplayer" : serverData.getServerIP()));
                Iterable iterable = LiquidBounce.INSTANCE.getModuleManager().getModules();
                StringBuilder stringBuilder = new StringBuilder().append("Enabled ");
                RichPresence.Builder builder2 = builder;
                boolean $i$f$count = false;
                if ($this$count$iv instanceof Collection && ((Collection)$this$count$iv).isEmpty()) {
                    n = 0;
                } else {
                    int count$iv = 0;
                    for (Object element$iv : $this$count$iv) {
                        Module it = (Module)element$iv;
                        boolean bl = false;
                        if (!it.getState()) continue;
                        int n2 = ++count$iv;
                        boolean bl2 = false;
                        if (n2 >= 0) continue;
                        CollectionsKt.throwCountOverflow();
                    }
                    n = count$iv;
                }
                int n3 = n;
                builder2.setState(stringBuilder.append(n3).append(" of ").append(LiquidBounce.INSTANCE.getModuleManager().getModules().size()).append(" modules").toString());
            }
            IPCClient iPCClient = this.ipcClient;
            if ((iPCClient != null ? iPCClient.getStatus() : null) != PipeStatus.CONNECTED) break block6;
            IPCClient iPCClient2 = this.ipcClient;
            if (iPCClient2 != null) {
                iPCClient2.sendRichPresence(builder.build());
            }
        }
    }

    public final void shutdown() {
        IPCClient iPCClient = this.ipcClient;
        if ((iPCClient != null ? iPCClient.getStatus() : null) != PipeStatus.CONNECTED) {
            return;
        }
        try {
            IPCClient iPCClient2 = this.ipcClient;
            if (iPCClient2 != null) {
                iPCClient2.close();
            }
        }
        catch (Throwable e) {
            ClientUtils.getLogger().error("Failed to close Discord RPC.", e);
        }
    }

    /*
     * WARNING - void declaration
     */
    private final void loadConfiguration() {
        JsonElement json = new JsonParser().parse(HttpUtils.get("https://cloud.liquidbounce.net/LiquidBounce/discord.json"));
        if (!(json instanceof JsonObject)) {
            return;
        }
        if (((JsonObject)json).has("appID")) {
            JsonElement jsonElement = ((JsonObject)json).get("appID");
            Intrinsics.checkExpressionValueIsNotNull(jsonElement, "json.get(\"appID\")");
            this.appID = jsonElement.getAsLong();
        }
        JsonElement jsonElement = ((JsonObject)json).get("assets");
        Intrinsics.checkExpressionValueIsNotNull(jsonElement, "json.get(\"assets\")");
        Iterator iterator2 = jsonElement.getAsJsonObject().entrySet().iterator();
        while (iterator2.hasNext()) {
            void key;
            Map.Entry entry;
            Map.Entry entry2 = entry = (Map.Entry)iterator2.next();
            boolean bl = false;
            String string = (String)entry2.getKey();
            entry2 = entry;
            bl = false;
            JsonElement value = (JsonElement)entry2.getValue();
            void v2 = key;
            Intrinsics.checkExpressionValueIsNotNull(v2, "key");
            JsonElement jsonElement2 = value;
            Intrinsics.checkExpressionValueIsNotNull(jsonElement2, "value");
            String string2 = jsonElement2.getAsString();
            Intrinsics.checkExpressionValueIsNotNull(string2, "value.asString");
            this.assets.put((String)v2, string2);
        }
    }

    public ClientRichPresence() {
        Map map;
        ClientRichPresence clientRichPresence = this;
        boolean bl = false;
        clientRichPresence.assets = map = (Map)new LinkedHashMap();
        this.timestamp = OffsetDateTime.now();
    }

    public static final /* synthetic */ boolean access$getRunning$p(ClientRichPresence $this) {
        return $this.running;
    }

    public static final /* synthetic */ void access$setRunning$p(ClientRichPresence $this, boolean bl) {
        $this.running = bl;
    }
}

