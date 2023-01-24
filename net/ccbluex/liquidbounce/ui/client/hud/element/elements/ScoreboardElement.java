/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.element.elements;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.ccbluex.liquidbounce.api.minecraft.client.entity.IEntityPlayerSP;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.api.minecraft.client.multiplayer.IWorldClient;
import net.ccbluex.liquidbounce.api.minecraft.scoreboard.IScore;
import net.ccbluex.liquidbounce.api.minecraft.scoreboard.IScoreObjective;
import net.ccbluex.liquidbounce.api.minecraft.scoreboard.IScoreboard;
import net.ccbluex.liquidbounce.api.minecraft.scoreboard.ITeam;
import net.ccbluex.liquidbounce.api.minecraft.util.WEnumChatFormatting;
import net.ccbluex.liquidbounce.ui.client.hud.element.Border;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.ScoreboardElement;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.blur.BlurBuffer;
import net.ccbluex.liquidbounce.utils.render.ColorUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@ElementInfo(name="Scoreboard")
@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010$\u001a\u00020%H\u0002J\n\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010(\u001a\u00020%H\u0002R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/element/elements/ScoreboardElement;", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "x", "", "y", "scale", "", "side", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Side;", "(DDFLnet/ccbluex/liquidbounce/ui/client/hud/element/Side;)V", "allowedDomains", "", "", "[Ljava/lang/String;", "backgroundColorAlphaValue", "Lnet/ccbluex/liquidbounce/value/IntegerValue;", "backgroundColorBlueValue", "backgroundColorGreenValue", "backgroundColorRedValue", "blur", "Lnet/ccbluex/liquidbounce/value/BoolValue;", "fontValue", "Lnet/ccbluex/liquidbounce/value/FontValue;", "noPointValue", "rectColorBlueAlpha", "rectColorBlueValue", "rectColorGreenValue", "rectColorModeValue", "Lnet/ccbluex/liquidbounce/value/ListValue;", "rectColorRedValue", "rectValue", "serverValue", "shadowValue", "textBlueValue", "textGreenValue", "textRedValue", "backgroundColor", "Ljava/awt/Color;", "drawElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Border;", "textColor", "Relaxed"})
public final class ScoreboardElement
extends Element {
    private final IntegerValue textRedValue;
    private final IntegerValue textGreenValue;
    private final IntegerValue textBlueValue;
    private final IntegerValue backgroundColorRedValue;
    private final IntegerValue backgroundColorGreenValue;
    private final IntegerValue backgroundColorBlueValue;
    private final IntegerValue backgroundColorAlphaValue;
    private final BoolValue blur;
    private final BoolValue rectValue;
    private final ListValue rectColorModeValue;
    private final IntegerValue rectColorRedValue;
    private final IntegerValue rectColorGreenValue;
    private final IntegerValue rectColorBlueValue;
    private final IntegerValue rectColorBlueAlpha;
    private final BoolValue shadowValue;
    private final ListValue serverValue;
    private final BoolValue noPointValue;
    private final FontValue fontValue;
    private final String[] allowedDomains;

    /*
     * WARNING - void declaration
     */
    @Override
    @Nullable
    public Border drawElement() {
        Collection collection;
        IScoreObjective iScoreObjective;
        int colorIndex;
        ITeam playerTeam;
        IFontRenderer fontRenderer = (IFontRenderer)this.fontValue.get();
        int textColor = this.textColor().getRGB();
        int backColor = this.backgroundColor().getRGB();
        String rectColorMode = (String)this.rectColorModeValue.get();
        int rectCustomColor = new Color(((Number)this.rectColorRedValue.get()).intValue(), ((Number)this.rectColorGreenValue.get()).intValue(), ((Number)this.rectColorBlueValue.get()).intValue(), ((Number)this.rectColorBlueAlpha.get()).intValue()).getRGB();
        IWorldClient iWorldClient = MinecraftInstance.mc.getTheWorld();
        if (iWorldClient == null) {
            Intrinsics.throwNpe();
        }
        IScoreboard worldScoreboard = iWorldClient.getScoreboard();
        IScoreObjective currObjective = null;
        IEntityPlayerSP iEntityPlayerSP = MinecraftInstance.mc.getThePlayer();
        if (iEntityPlayerSP == null) {
            Intrinsics.throwNpe();
        }
        if ((playerTeam = worldScoreboard.getPlayersTeam(iEntityPlayerSP.getName())) != null && (colorIndex = playerTeam.getChatFormat().getColorIndex()) >= 0) {
            currObjective = worldScoreboard.getObjectiveInDisplaySlot(3 + colorIndex);
        }
        if ((iScoreObjective = currObjective) == null) {
            iScoreObjective = worldScoreboard.getObjectiveInDisplaySlot(1);
        }
        if (iScoreObjective == null) {
            return null;
        }
        IScoreObjective objective = iScoreObjective;
        IScoreboard scoreboard = objective.getScoreboard();
        Collection scoreCollection = scoreboard.getSortedScores(objective);
        ArrayList scores2 = Lists.newArrayList((Iterable)Iterables.filter((Iterable)scoreCollection, (Predicate)drawElement.scores.1.INSTANCE));
        if (scores2.size() > 15) {
            ArrayList arrayList = Lists.newArrayList((Iterable)Iterables.skip((Iterable)scores2, (int)(scoreCollection.size() - 15)));
            Intrinsics.checkExpressionValueIsNotNull(arrayList, "Lists.newArrayList(Itera\u2026oreCollection.size - 15))");
            collection = arrayList;
        } else {
            ArrayList arrayList = scores2;
            Intrinsics.checkExpressionValueIsNotNull(arrayList, "scores");
            collection = arrayList;
        }
        scoreCollection = collection;
        int maxWidth = fontRenderer.getStringWidth(objective.getDisplayName());
        for (IScore score : (ArrayList)scoreCollection) {
            ITeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
            String width = MinecraftInstance.functions.scoreboardFormatPlayerName(scorePlayerTeam, score.getPlayerName()) + ": " + (Object)((Object)WEnumChatFormatting.RED) + score.getScorePoints();
            maxWidth = RangesKt.coerceAtLeast(maxWidth, fontRenderer.getStringWidth(width));
        }
        int maxHeight = scoreCollection.size() * fontRenderer.getFontHeight();
        int l1 = -maxWidth - 3 - ((Boolean)this.rectValue.get() != false ? 3 : 0);
        if (((Boolean)this.blur.get()).booleanValue()) {
            GL11.glTranslated((double)(-this.getRenderX()), (double)(-this.getRenderY()), (double)0.0);
            BlurBuffer.blurArea((float)this.getRenderX() + (float)l1 - 7.0f, (float)this.getRenderY() - 5.0f, (float)(-l1) + 16.0f, (float)(maxHeight + fontRenderer.getFontHeight()) + 10.0f);
            GL11.glTranslated((double)this.getRenderX(), (double)this.getRenderY(), (double)0.0);
        }
        Gui.func_73734_a((int)(l1 - 7), (int)-5, (int)9, (int)(maxHeight + fontRenderer.getFontHeight() + 5), (int)backColor);
        RenderUtils.drawShadowWithCustomAlpha((float)l1 - 7.0f, -5.0f, (float)(-l1) + 16.0f, (float)(maxHeight + fontRenderer.getFontHeight()) + 10.0f, 255.0f);
        Iterable $this$forEachIndexed$iv = scoreCollection;
        boolean $i$f$forEachIndexed = false;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            void score;
            int n = index$iv++;
            boolean bl = false;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int n2 = n;
            IScore iScore = (IScore)item$iv;
            int index = n2;
            boolean bl2 = false;
            ITeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            String name = MinecraftInstance.functions.scoreboardFormatPlayerName(team, score.getPlayerName());
            String scorePoints = "" + (Object)((Object)WEnumChatFormatting.RED) + score.getScorePoints();
            int width = 5 - ((Boolean)this.rectValue.get() != false ? 4 : 0);
            int height = maxHeight - index * fontRenderer.getFontHeight();
            GlStateManager.func_179117_G();
            int listColor = textColor;
            if (!this.serverValue.equals("none")) {
                for (String domain : this.allowedDomains) {
                    String string;
                    if (!StringsKt.contains((CharSequence)name, domain, true)) continue;
                    String string2 = (String)this.serverValue.get();
                    boolean bl3 = false;
                    String string3 = string2;
                    if (string3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String string4 = string3.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(string4, "(this as java.lang.String).toLowerCase()");
                    string2 = string4;
                    switch (string2.hashCode()) {
                        case 1103204566: {
                            if (string2.equals("clientname")) {
                                string = "Relaxed.wtf";
                                break;
                            }
                        }
                        default: {
                            string = "null";
                        }
                    }
                    name = string;
                    listColor = ColorUtils.rainbow().getRGB();
                    break;
                }
            }
            fontRenderer.drawString(name, l1, height, listColor, (Boolean)this.shadowValue.get());
            if (!((Boolean)this.noPointValue.get()).booleanValue()) {
                fontRenderer.drawString(scorePoints, width - fontRenderer.getStringWidth(scorePoints), height, textColor, (Boolean)this.shadowValue.get());
            }
            if (index == scoreCollection.size() - 1) {
                String displayName = objective.getDisplayName();
                GlStateManager.func_179117_G();
                fontRenderer.drawString(displayName, l1 + maxWidth / 2 - fontRenderer.getStringWidth(displayName) / 2, height - fontRenderer.getFontHeight(), textColor, (Boolean)this.shadowValue.get());
            }
            if (!((Boolean)this.rectValue.get()).booleanValue()) continue;
            int rectColor = StringsKt.equals(rectColorMode, "Rainbow", true) ? ColorUtils.rainbow(index).getRGB() : rectCustomColor;
            RenderUtils.drawRect(2.0f, index == scoreCollection.size() - 1 ? -2.0f : (float)height, 5.0f, index == 0 ? (float)fontRenderer.getFontHeight() : (float)height + (float)fontRenderer.getFontHeight() * 2.0f, rectColor);
        }
        return new Border(-((float)maxWidth) - 10.0f - (float)((Boolean)this.rectValue.get() != false ? 3 : 0), -5.0f, 9.0f, (float)maxHeight + (float)fontRenderer.getFontHeight() + (float)5);
    }

    private final Color backgroundColor() {
        return new Color(((Number)this.backgroundColorRedValue.get()).intValue(), ((Number)this.backgroundColorGreenValue.get()).intValue(), ((Number)this.backgroundColorBlueValue.get()).intValue(), ((Number)this.backgroundColorAlphaValue.get()).intValue());
    }

    private final Color textColor() {
        return new Color(((Number)this.textRedValue.get()).intValue(), ((Number)this.textGreenValue.get()).intValue(), ((Number)this.textBlueValue.get()).intValue());
    }

    public ScoreboardElement(double x, double y, float scale, @NotNull Side side) {
        Intrinsics.checkParameterIsNotNull(side, "side");
        super(x, y, scale, side);
        this.textRedValue = new IntegerValue("Text-R", 255, 0, 255);
        this.textGreenValue = new IntegerValue("Text-G", 255, 0, 255);
        this.textBlueValue = new IntegerValue("Text-B", 255, 0, 255);
        this.backgroundColorRedValue = new IntegerValue("Background-R", 0, 0, 255);
        this.backgroundColorGreenValue = new IntegerValue("Background-G", 0, 0, 255);
        this.backgroundColorBlueValue = new IntegerValue("Background-B", 0, 0, 255);
        this.backgroundColorAlphaValue = new IntegerValue("Background-Alpha", 95, 0, 255);
        this.blur = new BoolValue("Blur", true);
        this.rectValue = new BoolValue("Rect", false);
        this.rectColorModeValue = new ListValue("Rect-Color", new String[]{"Custom", "Rainbow"}, "Custom");
        this.rectColorRedValue = new IntegerValue("Rect-R", 0, 0, 255);
        this.rectColorGreenValue = new IntegerValue("Rect-G", 111, 0, 255);
        this.rectColorBlueValue = new IntegerValue("Rect-B", 255, 0, 255);
        this.rectColorBlueAlpha = new IntegerValue("Rect-Alpha", 255, 0, 255);
        this.shadowValue = new BoolValue("Shadow", false);
        this.serverValue = new ListValue("ServerIp", new String[]{"None", "ClientName"}, "ClientName");
        this.noPointValue = new BoolValue("NoPoints", false);
        IFontRenderer iFontRenderer = Fonts.minecraftFont;
        Intrinsics.checkExpressionValueIsNotNull(iFontRenderer, "Fonts.minecraftFont");
        this.fontValue = new FontValue("Font", iFontRenderer);
        this.allowedDomains = new String[]{".ac", ".academy", ".accountant", ".accountants", ".actor", ".adult", ".ag", ".agency", ".ai", ".airforce", ".am", ".amsterdam", ".apartments", ".app", ".archi", ".army", ".art", ".asia", ".associates", ".at", ".attorney", ".au", ".auction", ".auto", ".autos", ".baby", ".band", ".bar", ".barcelona", ".bargains", ".bayern", ".be", ".beauty", ".beer", ".berlin", ".best", ".bet", ".bid", ".bike", ".bingo", ".bio", ".biz", ".biz.pl", ".black", ".blog", ".blue", ".boats", ".boston", ".boutique", ".build", ".builders", ".business", ".buzz", ".bz", ".ca", ".cab", ".cafe", ".camera", ".camp", ".capital", ".car", ".cards", ".care", ".careers", ".cars", ".casa", ".cash", ".casino", ".catering", ".cc", ".center", ".ceo", ".ch", ".charity", ".chat", ".cheap", ".church", ".city", ".cl", ".claims", ".cleaning", ".clinic", ".clothing", ".cloud", ".club", ".cn", ".co", ".co.in", ".co.jp", ".co.kr", ".co.nz", ".co.uk", ".co.za", ".coach", ".codes", ".coffee", ".college", ".com", ".com.ag", ".com.au", ".com.br", ".com.bz", ".com.cn", ".com.co", ".com.es", ".com.mx", ".com.pe", ".com.ph", ".com.pl", ".com.ru", ".com.tw", ".community", ".company", ".computer", ".condos", ".construction", ".consulting", ".contact", ".contractors", ".cooking", ".cool", ".country", ".coupons", ".courses", ".credit", ".creditcard", ".cricket", ".cruises", ".cymru", ".cz", ".dance", ".date", ".dating", ".de", ".deals", ".degree", ".delivery", ".democrat", ".dental", ".dentist", ".design", ".dev", ".diamonds", ".digital", ".direct", ".directory", ".discount", ".dk", ".doctor", ".dog", ".domains", ".download", ".earth", ".education", ".email", ".energy", ".engineer", ".engineering", ".enterprises", ".equipment", ".es", ".estate", ".eu", ".events", ".exchange", ".expert", ".exposed", ".express", ".fail", ".faith", ".family", ".fan", ".fans", ".farm", ".fashion", ".film", ".finance", ".financial", ".firm.in", ".fish", ".fishing", ".fit", ".fitness", ".flights", ".florist", ".fm", ".football", ".forsale", ".foundation", ".fr", ".fun", ".fund", ".furniture", ".futbol", ".fyi", ".gallery", ".games", ".garden", ".gay", ".gen.in", ".gg", ".gifts", ".gives", ".glass", ".global", ".gmbh", ".gold", ".golf", ".graphics", ".gratis", ".green", ".gripe", ".group", ".gs", ".guide", ".guru", ".hair", ".haus", ".health", ".healthcare", ".hockey", ".holdings", ".holiday", ".homes", ".horse", ".hospital", ".host", ".house", ".idv.tw", ".immo", ".immobilien", ".in", ".inc", ".ind.in", ".industries", ".info", ".info.pl", ".ink", ".institute", ".insure", ".international", ".investments", ".io", ".irish", ".ist", ".istanbul", ".it", ".jetzt", ".jewelry", ".jobs", ".jp", ".kaufen", ".kim", ".kitchen", ".kiwi", ".kr", ".la", ".land", ".law", ".lawyer", ".lease", ".legal", ".lgbt", ".life", ".lighting", ".limited", ".limo", ".live", ".llc", ".loan", ".loans", ".london", ".love", ".ltd", ".ltda", ".luxury", ".maison", ".makeup", ".management", ".market", ".marketing", ".mba", ".me", ".me.uk", ".media", ".melbourne", ".memorial", ".men", ".menu", ".miami", ".mobi", ".moda", ".moe", ".money", ".monster", ".mortgage", ".motorcycles", ".movie", ".ms", ".mx", ".nagoya", ".name", ".navy", ".ne.kr", ".net", ".net.ag", ".net.au", ".net.br", ".net.bz", ".net.cn", ".net.co", ".net.in", ".net.nz", ".net.pe", ".net.ph", ".net.pl", ".net.ru", ".network", ".news", ".ninja", ".nl", ".no", ".nom.co", ".nom.es", ".nom.pe", ".nrw", ".nyc", ".okinawa", ".one", ".onl", ".online", ".org", ".org.ag", ".org.au", ".org.cn", ".org.es", ".org.in", ".org.nz", ".org.pe", ".org.ph", ".org.pl", ".org.ru", ".org.uk", ".page", ".paris", ".partners", ".parts", ".party", ".pe", ".pet", ".ph", ".photography", ".photos", ".pictures", ".pink", ".pizza", ".pl", ".place", ".plumbing", ".plus", ".poker", ".porn", ".press", ".pro", ".productions", ".promo", ".properties", ".protection", ".pub", ".pw", ".quebec", ".quest", ".racing", ".re.kr", ".realestate", ".recipes", ".red", ".rehab", ".reise", ".reisen", ".rent", ".rentals", ".repair", ".report", ".republican", ".rest", ".restaurant", ".review", ".reviews", ".rich", ".rip", ".rocks", ".rodeo", ".ru", ".run", ".ryukyu", ".sale", ".salon", ".sarl", ".school", ".schule", ".science", ".se", ".security", ".services", ".sex", ".sg", ".sh", ".shiksha", ".shoes", ".shop", ".shopping", ".show", ".singles", ".site", ".ski", ".skin", ".soccer", ".social", ".software", ".solar", ".solutions", ".space", ".storage", ".store", ".stream", ".studio", ".study", ".style", ".supplies", ".supply", ".support", ".surf", ".surgery", ".sydney", ".systems", ".tax", ".taxi", ".team", ".tech", ".technology", ".tel", ".tennis", ".theater", ".theatre", ".tienda", ".tips", ".tires", ".today", ".tokyo", ".tools", ".tours", ".town", ".toys", ".top", ".trade", ".training", ".travel", ".tube", ".tv", ".tw", ".uk", ".university", ".uno", ".us", ".vacations", ".vegas", ".ventures", ".vet", ".viajes", ".video", ".villas", ".vin", ".vip", ".vision", ".vodka", ".vote", ".voto", ".voyage", ".wales", ".watch", ".webcam", ".website", ".wedding", ".wiki", ".win", ".wine", ".work", ".works", ".world", ".ws", ".wtf", ".xxx", ".xyz", ".yachts", ".yoga", ".yokohama", ".zone", "\u82b1\u96e8\u5ead", "855712180"};
    }

    public /* synthetic */ ScoreboardElement(double d, double d2, float f, Side side, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            d = 5.0;
        }
        if ((n & 2) != 0) {
            d2 = 0.0;
        }
        if ((n & 4) != 0) {
            f = 1.0f;
        }
        if ((n & 8) != 0) {
            side = new Side(Side.Horizontal.RIGHT, Side.Vertical.MIDDLE);
        }
        this(d, d2, f, side);
    }

    public ScoreboardElement() {
        this(0.0, 0.0, 0.0f, null, 15, null);
    }
}

