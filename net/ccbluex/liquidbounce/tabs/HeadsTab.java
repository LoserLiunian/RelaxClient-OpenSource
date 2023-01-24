/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.tabs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.enums.ItemType;
import net.ccbluex.liquidbounce.api.minecraft.item.IItem;
import net.ccbluex.liquidbounce.api.minecraft.item.IItemStack;
import net.ccbluex.liquidbounce.api.util.WrappedCreativeTabs;
import net.ccbluex.liquidbounce.injection.backend.WrapperImpl;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.item.ItemUtils;
import net.ccbluex.liquidbounce.utils.misc.HttpUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lnet/ccbluex/liquidbounce/tabs/HeadsTab;", "Lnet/ccbluex/liquidbounce/api/util/WrappedCreativeTabs;", "()V", "heads", "Ljava/util/ArrayList;", "Lnet/ccbluex/liquidbounce/api/minecraft/item/IItemStack;", "displayAllReleventItems", "", "itemList", "", "getTabIconItem", "Lnet/ccbluex/liquidbounce/api/minecraft/item/IItem;", "getTranslatedTabLabel", "", "hasSearchBar", "", "loadHeads", "Relaxed"})
public final class HeadsTab
extends WrappedCreativeTabs {
    private final ArrayList<IItemStack> heads = new ArrayList();

    private final void loadHeads() {
        try {
            JsonElement headsConfiguration;
            ClientUtils.getLogger().info("Loading heads...");
            JsonElement jsonElement = headsConfiguration = new JsonParser().parse(HttpUtils.get("https://cloud.liquidbounce.net/LiquidBounce/heads.json"));
            Intrinsics.checkExpressionValueIsNotNull(jsonElement, "headsConfiguration");
            if (!jsonElement.isJsonObject()) {
                return;
            }
            JsonObject headsConf = headsConfiguration.getAsJsonObject();
            JsonElement jsonElement2 = headsConf.get("enabled");
            Intrinsics.checkExpressionValueIsNotNull(jsonElement2, "headsConf.get(\"enabled\")");
            if (jsonElement2.getAsBoolean()) {
                JsonElement headsElement;
                JsonElement jsonElement3 = headsConf.get("url");
                Intrinsics.checkExpressionValueIsNotNull(jsonElement3, "headsConf.get(\"url\")");
                String url = jsonElement3.getAsString();
                ClientUtils.getLogger().info("Loading heads from " + url + "...");
                JsonParser jsonParser = new JsonParser();
                String string = url;
                Intrinsics.checkExpressionValueIsNotNull(string, "url");
                JsonElement jsonElement4 = headsElement = jsonParser.parse(HttpUtils.get(string));
                Intrinsics.checkExpressionValueIsNotNull(jsonElement4, "headsElement");
                if (!jsonElement4.isJsonObject()) {
                    ClientUtils.getLogger().error("Something is wrong, the heads json is not a JsonObject!");
                    return;
                }
                JsonObject headsObject = headsElement.getAsJsonObject();
                Iterator iterator2 = headsObject.entrySet().iterator();
                while (iterator2.hasNext()) {
                    JsonElement value;
                    Map.Entry entry;
                    Map.Entry entry2 = entry = (Map.Entry)iterator2.next();
                    boolean bl = false;
                    JsonElement jsonElement5 = value = (JsonElement)entry2.getValue();
                    Intrinsics.checkExpressionValueIsNotNull(jsonElement5, "value");
                    JsonObject headElement = jsonElement5.getAsJsonObject();
                    StringBuilder stringBuilder = new StringBuilder().append("skull 1 3 {display:{Name:\"");
                    JsonElement jsonElement6 = headElement.get("name");
                    Intrinsics.checkExpressionValueIsNotNull(jsonElement6, "headElement.get(\"name\")");
                    StringBuilder stringBuilder2 = stringBuilder.append(jsonElement6.getAsString()).append("\"},SkullOwner:{Id:\"");
                    JsonElement jsonElement7 = headElement.get("uuid");
                    Intrinsics.checkExpressionValueIsNotNull(jsonElement7, "headElement.get(\"uuid\")");
                    StringBuilder stringBuilder3 = stringBuilder2.append(jsonElement7.getAsString()).append("\",Properties:{textures:[{Value:\"");
                    JsonElement jsonElement8 = headElement.get("value");
                    Intrinsics.checkExpressionValueIsNotNull(jsonElement8, "headElement.get(\"value\")");
                    this.heads.add(ItemUtils.createItem(stringBuilder3.append(jsonElement8.getAsString()).append("\"}]}}}").toString()));
                }
                ClientUtils.getLogger().info("Loaded " + this.heads.size() + " heads from HeadDB.");
            } else {
                ClientUtils.getLogger().info("Heads are disabled.");
            }
        }
        catch (Exception e) {
            ClientUtils.getLogger().error("Error while reading heads.", (Throwable)e);
        }
    }

    @Override
    public void displayAllReleventItems(@NotNull List<IItemStack> itemList) {
        Intrinsics.checkParameterIsNotNull(itemList, "itemList");
        itemList.addAll((Collection<IItemStack>)this.heads);
    }

    @Override
    @NotNull
    public IItem getTabIconItem() {
        return WrapperImpl.INSTANCE.getClassProvider().getItemEnum(ItemType.SKULL);
    }

    @Override
    @NotNull
    public String getTranslatedTabLabel() {
        return "Heads";
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    public HeadsTab() {
        super("Heads");
        this.getRepresentedType().setBackgroundImageName("item_search.png");
        this.loadHeads();
    }
}

