/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package net.ccbluex.liquidbounce.ui.font;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.ui.font.FontDetails;
import net.ccbluex.liquidbounce.ui.font.GameFontRenderer;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.misc.HttpUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Fonts
extends MinecraftInstance {
    @FontDetails(fontName="Minecraft Font")
    public static final IFontRenderer minecraftFont = mc.getFontRendererObj();
    private static final HashMap<FontInfo, IFontRenderer> CUSTOM_FONT_RENDERERS = new HashMap();
    @FontDetails(fontName="flux", fontSize=35)
    public static IFontRenderer flux;
    @FontDetails(fontName="flux", fontSize=45)
    public static IFontRenderer flux45;
    @FontDetails(fontName="icomoon", fontSize=40)
    public static IFontRenderer icomoon;
    @FontDetails(fontName="Bold", fontSize=30)
    public static IFontRenderer bold30;
    @FontDetails(fontName="Bold", fontSize=40)
    public static IFontRenderer bold40;
    @FontDetails(fontName="Bold", fontSize=35)
    public static IFontRenderer bold35;
    @FontDetails(fontName="regular", fontSize=30)
    public static IFontRenderer regular30;
    @FontDetails(fontName="regular", fontSize=40)
    public static IFontRenderer regular40;
    @FontDetails(fontName="regular", fontSize=35)
    public static IFontRenderer regular35;
    @FontDetails(fontName="Roboto Medium", fontSize=35)
    public static IFontRenderer font35;
    @FontDetails(fontName="Roboto Medium", fontSize=30)
    public static IFontRenderer font30;
    @FontDetails(fontName="Roboto Medium", fontSize=25)
    public static IFontRenderer font25;
    @FontDetails(fontName="Roboto Medium", fontSize=40)
    public static IFontRenderer font40;
    @FontDetails(fontName="Roboto Medium", fontSize=80)
    public static IFontRenderer font80;
    @FontDetails(fontName="Poppins35", fontSize=35)
    public static IFontRenderer Poppins35;
    @FontDetails(fontName="Poppins40", fontSize=24)
    public static IFontRenderer Poppins40;
    @FontDetails(fontName="Poppins30", fontSize=30)
    public static IFontRenderer Poppins30;
    @FontDetails(fontName="SFUI Regular", fontSize=35)
    public static IFontRenderer fontSFUI35;
    @FontDetails(fontName="SFUI Regular", fontSize=40)
    public static IFontRenderer fontSFUI40;
    @FontDetails(fontName="SFUI Regular", fontSize=120)
    public static IFontRenderer fontSFUI120;
    @FontDetails(fontName="AlibabaSansLight35", fontSize=35)
    public static IFontRenderer AlibabaSansLight35;
    @FontDetails(fontName="AlibabaSansLight45", fontSize=45)
    public static IFontRenderer AlibabaSansLight45;
    @FontDetails(fontName="ComfortaaRegular35", fontSize=35)
    public static IFontRenderer ComfortaaRegular35;
    @FontDetails(fontName="ComfortaaRegular45", fontSize=45)
    public static IFontRenderer ComfortaaRegular45;
    @FontDetails(fontName="CasanovaScotia35", fontSize=35)
    public static IFontRenderer CasanovaScotia35;
    @FontDetails(fontName="CasanovaScotia45", fontSize=45)
    public static IFontRenderer CasanovaScotia45;
    @FontDetails(fontName="notiIcon", fontSize=80)
    public static IFontRenderer n80;

    public static void loadFonts() {
        long l = System.currentTimeMillis();
        ClientUtils.getLogger().info("Loading Fonts.");
        flux = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getFlux(30)));
        flux45 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getFlux(45)));
        font35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(35)));
        font25 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(25)));
        font40 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(40)));
        font30 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(30)));
        font80 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(80)));
        fontSFUI35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(35)));
        fontSFUI40 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(40)));
        icomoon = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getIcomoon(40)));
        fontSFUI120 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getSFUI(120)));
        Poppins35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getPoppins(35)));
        Poppins30 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getPoppins(30)));
        Poppins40 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getPoppins(40)));
        regular30 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getregular(30)));
        regular35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getregular(35)));
        regular40 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getregular(40)));
        bold35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getBold(35)));
        bold40 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getBold(40)));
        bold30 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getBold(30)));
        AlibabaSansLight35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getAlibabaSansLight(35)));
        AlibabaSansLight45 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getAlibabaSansLight(45)));
        ComfortaaRegular35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getComfortaaRegular(35)));
        ComfortaaRegular45 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getComfortaaRegular(45)));
        CasanovaScotia35 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getCasanovaScotia(35)));
        CasanovaScotia45 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getCasanovaScotia(45)));
        n80 = classProvider.wrapFontRenderer(new GameFontRenderer(Fonts.getIcon(80)));
        try {
            CUSTOM_FONT_RENDERERS.clear();
            File fontsFile = new File(LiquidBounce.fileManager.fontsDir, "fonts.json");
            if (fontsFile.exists()) {
                JsonElement jsonElement = new JsonParser().parse((Reader)new BufferedReader(new FileReader(fontsFile)));
                if (jsonElement instanceof JsonNull) {
                    return;
                }
                JsonArray jsonArray = (JsonArray)jsonElement;
                for (JsonElement element : jsonArray) {
                    if (element instanceof JsonNull) {
                        return;
                    }
                    JsonObject fontObject = (JsonObject)element;
                    Font font = Fonts.getFont(fontObject.get("fontFile").getAsString(), fontObject.get("fontSize").getAsInt());
                    CUSTOM_FONT_RENDERERS.put(new FontInfo(font), classProvider.wrapFontRenderer(new GameFontRenderer(font)));
                }
            } else {
                fontsFile.createNewFile();
                PrintWriter printWriter = new PrintWriter(new FileWriter(fontsFile));
                printWriter.println(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)new JsonArray()));
                printWriter.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ClientUtils.getLogger().info("Loaded Fonts. (" + (System.currentTimeMillis() - l) + "ms)");
    }

    private static void downloadFonts() {
        try {
            File outputFile = new File(LiquidBounce.fileManager.fontsDir, "roboto.zip");
            if (!outputFile.exists()) {
                ClientUtils.getLogger().info("Downloading fonts...");
                HttpUtils.download("https://cloud.liquidbounce.net/LiquidBounce/fonts/Roboto.zip", outputFile);
                ClientUtils.getLogger().info("Extract fonts...");
                Fonts.extractZip(outputFile.getPath(), LiquidBounce.fileManager.fontsDir.getPath());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Font getJello(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/jello.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getIcon(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/notiicon.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static IFontRenderer getFontRenderer(String name, int size) {
        for (Field field : Fonts.class.getDeclaredFields()) {
            try {
                FontDetails fontDetails;
                field.setAccessible(true);
                Object o = field.get(null);
                if (!(o instanceof IFontRenderer) || !(fontDetails = field.getAnnotation(FontDetails.class)).fontName().equals(name) || fontDetails.fontSize() != size) continue;
                return (IFontRenderer)o;
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return CUSTOM_FONT_RENDERERS.getOrDefault(new FontInfo(name, size), minecraftFont);
    }

    public static FontInfo getFontDetails(IFontRenderer fontRenderer) {
        for (Field field : Fonts.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object o = field.get(null);
                if (!o.equals(fontRenderer)) continue;
                FontDetails fontDetails = field.getAnnotation(FontDetails.class);
                return new FontInfo(fontDetails.fontName(), fontDetails.fontSize());
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry entry : CUSTOM_FONT_RENDERERS.entrySet()) {
            if (entry.getValue() != fontRenderer) continue;
            return (FontInfo)entry.getKey();
        }
        return null;
    }

    private static Font getPoppins(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/poppins.otf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getregular(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/regular.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getBold(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/bold.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public float getCharWidth(char c) {
        return fontSFUI35.getStringWidth(String.valueOf(c));
    }

    private static Font getSFUI(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/sfuidisplayregular.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getIcomoon(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/icomoon.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getComfortaaRegular(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/ComfortaaRegular.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getCasanovaScotia(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/CasanovaScotia.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getAlibabaSansLight(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/AlibabaSansLight.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getFlux(int size) {
        Font font;
        try {
            InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("relaxed/font/fluxicon.ttf")).func_110527_b();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public static List<IFontRenderer> getFonts() {
        ArrayList<IFontRenderer> fonts = new ArrayList<IFontRenderer>();
        for (Field fontField : Fonts.class.getDeclaredFields()) {
            try {
                fontField.setAccessible(true);
                Object fontObj = fontField.get(null);
                if (!(fontObj instanceof IFontRenderer)) continue;
                fonts.add((IFontRenderer)fontObj);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fonts.addAll(CUSTOM_FONT_RENDERERS.values());
        return fonts;
    }

    private static Font getFont(String fontName, int size) {
        try {
            FileInputStream inputStream = new FileInputStream(new File(LiquidBounce.fileManager.fontsDir, fontName));
            Font awtClientFont = Font.createFont(0, inputStream);
            awtClientFont = awtClientFont.deriveFont(0, size);
            ((InputStream)inputStream).close();
            return awtClientFont;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Font("default", 0, size);
        }
    }

    private static void extractZip(String zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];
        try {
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                int i;
                File newFile = new File(outputFolder + File.separator + zipEntry.getName());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                while ((i = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, i);
                }
                fileOutputStream.close();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class FontInfo {
        private final String name;
        private final int fontSize;

        public FontInfo(String name, int fontSize) {
            this.name = name;
            this.fontSize = fontSize;
        }

        public FontInfo(Font font) {
            this(font.getName(), font.getSize());
        }

        public String getName() {
            return this.name;
        }

        public int getFontSize() {
            return this.fontSize;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            FontInfo fontInfo = (FontInfo)o;
            if (this.fontSize != fontInfo.fontSize) {
                return false;
            }
            return Objects.equals(this.name, fontInfo.name);
        }

        public int hashCode() {
            int result = this.name != null ? this.name.hashCode() : 0;
            result = 31 * result + this.fontSize;
            return result;
        }
    }
}

