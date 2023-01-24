/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package net.ccbluex.liquidbounce.ui.client.hud.designer;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.client.IMinecraft;
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer;
import net.ccbluex.liquidbounce.features.module.modules.render.ClickGUI;
import net.ccbluex.liquidbounce.ui.client.hud.HUD;
import net.ccbluex.liquidbounce.ui.client.hud.designer.EditorPanel$WhenMappings;
import net.ccbluex.liquidbounce.ui.client.hud.designer.GuiHudDesigner;
import net.ccbluex.liquidbounce.ui.client.hud.element.Element;
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo;
import net.ccbluex.liquidbounce.ui.client.hud.element.Side;
import net.ccbluex.liquidbounce.ui.font.Fonts;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.ccbluex.liquidbounce.value.BoolValue;
import net.ccbluex.liquidbounce.value.FloatValue;
import net.ccbluex.liquidbounce.value.FontValue;
import net.ccbluex.liquidbounce.value.IntegerValue;
import net.ccbluex.liquidbounce.value.ListValue;
import net.ccbluex.liquidbounce.value.Value;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0002J\u0018\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0002J\u0018\u0010&\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0002J\u001e\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005J\u0018\u0010)\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0002R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u001f\u00a8\u0006*"}, d2={"Lnet/ccbluex/liquidbounce/ui/client/hud/designer/EditorPanel;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "hudDesigner", "Lnet/ccbluex/liquidbounce/ui/client/hud/designer/GuiHudDesigner;", "x", "", "y", "(Lnet/ccbluex/liquidbounce/ui/client/hud/designer/GuiHudDesigner;II)V", "create", "", "getCreate", "()Z", "setCreate", "(Z)V", "currentElement", "Lnet/ccbluex/liquidbounce/ui/client/hud/element/Element;", "drag", "dragX", "dragY", "<set-?>", "height", "getHeight", "()I", "mouseDown", "realHeight", "getRealHeight", "scroll", "width", "getWidth", "getX", "setX", "(I)V", "getY", "setY", "", "mouseX", "mouseY", "drawCreate", "drawEditor", "drawPanel", "wheel", "drawSelection", "Relaxed"})
public final class EditorPanel
extends MinecraftInstance {
    private int width;
    private int height;
    private int realHeight;
    private boolean drag;
    private int dragX;
    private int dragY;
    private boolean mouseDown;
    private int scroll;
    private boolean create;
    private Element currentElement;
    private final GuiHudDesigner hudDesigner;
    private int x;
    private int y;

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getRealHeight() {
        return this.realHeight;
    }

    public final boolean getCreate() {
        return this.create;
    }

    public final void setCreate(boolean bl) {
        this.create = bl;
    }

    public final void drawPanel(int mouseX, int mouseY, int wheel) {
        boolean shouldScroll;
        this.drag(mouseX, mouseY);
        if (Intrinsics.areEqual(this.currentElement, this.hudDesigner.getSelectedElement()) ^ true) {
            this.scroll = 0;
        }
        this.currentElement = this.hudDesigner.getSelectedElement();
        int currMouseY = mouseY;
        boolean bl = shouldScroll = this.realHeight > 200;
        if (shouldScroll) {
            GL11.glPushMatrix();
            RenderUtils.makeScissorBox(this.x, (float)this.y + 1.0f, (float)this.x + (float)this.width, (float)this.y + 200.0f);
            GL11.glEnable((int)3089);
            if (this.y + 200 < currMouseY) {
                currMouseY = -1;
            }
            if (mouseX >= this.x && mouseX <= this.x + this.width && currMouseY >= this.y && currMouseY <= this.y + 200 && Mouse.hasWheel()) {
                if (wheel < 0 && -this.scroll + 205 <= this.realHeight) {
                    this.scroll -= 12;
                } else if (wheel > 0) {
                    this.scroll += 12;
                    if (this.scroll > 0) {
                        this.scroll = 0;
                    }
                }
            }
        }
        RenderUtils.drawRect(this.x, this.y + 12, this.x + this.width, this.y + this.realHeight, new Color(27, 34, 40).getRGB());
        if (this.create) {
            this.drawCreate(mouseX, currMouseY);
        } else if (this.currentElement != null) {
            this.drawEditor(mouseX, currMouseY);
        } else {
            this.drawSelection(mouseX, currMouseY);
        }
        if (shouldScroll) {
            RenderUtils.drawRect(this.x + this.width - 5, this.y + 15, this.x + this.width - 2, this.y + 197, new Color(41, 41, 41).getRGB());
            float v = (float)197 * ((float)(-this.scroll) / ((float)this.realHeight - 170.0f));
            RenderUtils.drawRect((float)(this.x + this.width) - 5.0f, (float)(this.y + 15) + v, (float)(this.x + this.width) - 2.0f, (float)(this.y + 20) + v, new Color(37, 126, 255).getRGB());
            GL11.glDisable((int)3089);
            GL11.glPopMatrix();
        }
        this.mouseDown = Mouse.isButtonDown((int)0);
    }

    private final void drawCreate(int mouseX, int mouseY) {
        this.height = 15 + this.scroll;
        this.realHeight = 15;
        this.width = 90;
        for (Class<? extends Element> element : HUD.Companion.getElements()) {
            ElementInfo info;
            if (element.getAnnotation(ElementInfo.class) == null) {
                continue;
            }
            if (info.single()) {
                boolean bl;
                block12: {
                    Iterable $this$any$iv = LiquidBounce.INSTANCE.getHud().getElements();
                    boolean $i$f$any = false;
                    if ($this$any$iv instanceof Collection && ((Collection)$this$any$iv).isEmpty()) {
                        bl = false;
                    } else {
                        for (Object element$iv : $this$any$iv) {
                            Element it = (Element)element$iv;
                            boolean bl2 = false;
                            if (!Intrinsics.areEqual(it.getClass(), element)) continue;
                            bl = true;
                            break block12;
                        }
                        bl = false;
                    }
                }
                if (bl) continue;
            }
            String name = info.name();
            float f = (float)this.x + 2.0f;
            float f2 = (float)this.y + (float)this.height;
            Color color = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color, "Color.WHITE");
            Fonts.font35.drawString(name, f, f2, color.getRGB());
            int stringWidth = Fonts.font35.getStringWidth(name);
            if (this.width < stringWidth + 8) {
                this.width = stringWidth + 8;
            }
            if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
                try {
                    Element newElement = element.newInstance();
                    if (newElement.createElement()) {
                        HUD hUD = LiquidBounce.INSTANCE.getHud();
                        Element element2 = newElement;
                        Intrinsics.checkExpressionValueIsNotNull(element2, "newElement");
                        hUD.addElement(element2);
                    }
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                this.create = false;
            }
            this.height += 10;
            this.realHeight += 10;
        }
        Color color = ClickGUI.generateColor();
        Intrinsics.checkExpressionValueIsNotNull(color, "ClickGUI.generateColor()");
        RenderUtils.drawRect(this.x, this.y, this.x + this.width, this.y + 12, color.getRGB());
        float f = (float)this.x + 2.0f;
        float f3 = (float)this.y + 3.5f;
        Color color2 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color2, "Color.WHITE");
        Fonts.font35.drawString("\u00a7lCreate element", f, f3, color2.getRGB());
    }

    private final void drawSelection(int mouseX, int mouseY) {
        this.height = 15 + this.scroll;
        this.realHeight = 15;
        this.width = 120;
        float f = (float)this.x + 2.0f;
        float f2 = (float)this.y + (float)this.height;
        Color color = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color, "Color.WHITE");
        Fonts.font35.drawString("\u00a7lCreate element", f, f2, color.getRGB());
        if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
            this.create = true;
        }
        this.height += 10;
        this.realHeight += 10;
        float f3 = (float)this.x + (float)2;
        float f4 = (float)this.y + (float)this.height;
        Color color2 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color2, "Color.WHITE");
        Fonts.font35.drawString("\u00a7lReset", f3, f4, color2.getRGB());
        if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
            LiquidBounce.INSTANCE.setHud(HUD.Companion.createDefault());
        }
        this.height += 15;
        this.realHeight += 15;
        float f5 = (float)this.x + 2.0f;
        float f6 = (float)this.y + (float)this.height;
        Color color3 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color3, "Color.WHITE");
        Fonts.font35.drawString("\u00a7lAvailable Elements", f5, f6, color3.getRGB());
        this.height += 10;
        this.realHeight += 10;
        for (Element element : LiquidBounce.INSTANCE.getHud().getElements()) {
            String string = element.getName();
            Color color4 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color4, "Color.WHITE");
            Fonts.font35.drawString(string, this.x + 2, this.y + this.height, color4.getRGB());
            int stringWidth = Fonts.font35.getStringWidth(element.getName());
            if (this.width < stringWidth + 8) {
                this.width = stringWidth + 8;
            }
            if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
                this.hudDesigner.setSelectedElement(element);
            }
            this.height += 10;
            this.realHeight += 10;
        }
        Color color5 = ClickGUI.generateColor();
        Intrinsics.checkExpressionValueIsNotNull(color5, "ClickGUI.generateColor()");
        RenderUtils.drawRect(this.x, this.y, this.x + this.width, this.y + 12, color5.getRGB());
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float f7 = (float)this.x + 2.0f;
        float f8 = (float)this.y + 3.5f;
        Color color6 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color6, "Color.WHITE");
        Fonts.font35.drawString("\u00a7lEditor", f7, f8, color6.getRGB());
    }

    /*
     * WARNING - void declaration
     */
    private final void drawEditor(int mouseX, int mouseY) {
        Enum[] values2;
        this.height = this.scroll + 15;
        this.realHeight = 15;
        int prevWidth = this.width;
        this.width = 100;
        Element element = this.currentElement;
        if (element == null) {
            return;
        }
        Element element2 = element;
        String string = "%.2f";
        Object[] objectArray = new Object[]{element2.getRenderX()};
        CharSequence charSequence = new StringBuilder().append("X: ");
        Object object = Fonts.font35;
        boolean bl = false;
        String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkExpressionValueIsNotNull(string2, "java.lang.String.format(this, *args)");
        String string3 = string2;
        string = "%.2f";
        objectArray = new Object[]{element2.getX()};
        charSequence = charSequence.append(string3).append(" (");
        bl = false;
        String string4 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkExpressionValueIsNotNull(string4, "java.lang.String.format(this, *args)");
        string3 = string4;
        String string5 = charSequence.append(string3).append(')').toString();
        Color color = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color, "Color.WHITE");
        object.drawString(string5, this.x + 2, this.y + this.height, color.getRGB());
        this.height += 10;
        this.realHeight += 10;
        string = "%.2f";
        objectArray = new Object[]{element2.getRenderY()};
        charSequence = new StringBuilder().append("Y: ");
        object = Fonts.font35;
        bl = false;
        String string6 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkExpressionValueIsNotNull(string6, "java.lang.String.format(this, *args)");
        string3 = string6;
        string = "%.2f";
        objectArray = new Object[]{element2.getY()};
        charSequence = charSequence.append(string3).append(" (");
        bl = false;
        String string7 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkExpressionValueIsNotNull(string7, "java.lang.String.format(this, *args)");
        string3 = string7;
        String string8 = charSequence.append(string3).append(')').toString();
        Color color2 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color2, "Color.WHITE");
        object.drawString(string8, this.x + 2, this.y + this.height, color2.getRGB());
        this.height += 10;
        this.realHeight += 10;
        string = "%.2f";
        objectArray = new Object[]{Float.valueOf(element2.getScale())};
        charSequence = new StringBuilder().append("Scale: ");
        object = Fonts.font35;
        bl = false;
        String string9 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkExpressionValueIsNotNull(string9, "java.lang.String.format(this, *args)");
        string3 = string9;
        String string10 = charSequence.append(string3).toString();
        Color color3 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color3, "Color.WHITE");
        object.drawString(string10, this.x + 2, this.y + this.height, color3.getRGB());
        this.height += 10;
        this.realHeight += 10;
        Color color4 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color4, "Color.WHITE");
        Fonts.font35.drawString("H:", this.x + 2, this.y + this.height, color4.getRGB());
        String string11 = element2.getSide().getHorizontal().getSideName();
        Color color5 = Color.GRAY;
        Intrinsics.checkExpressionValueIsNotNull(color5, "Color.GRAY");
        Fonts.font35.drawString(string11, this.x + 12, this.y + this.height, color5.getRGB());
        if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
            double d;
            values2 = Side.Horizontal.values();
            int currIndex = ArraysKt.indexOf(values2, element2.getSide().getHorizontal());
            double x = element2.getRenderX();
            element2.getSide().setHorizontal(values2[currIndex + 1 >= values2.length ? 0 : currIndex + 1]);
            switch (EditorPanel$WhenMappings.$EnumSwitchMapping$0[element2.getSide().getHorizontal().ordinal()]) {
                case 1: {
                    d = x;
                    break;
                }
                case 2: {
                    IMinecraft iMinecraft = MinecraftInstance.mc;
                    Intrinsics.checkExpressionValueIsNotNull(iMinecraft, "mc");
                    d = (double)(MinecraftInstance.classProvider.createScaledResolution(iMinecraft).getScaledWidth() / 2) - x;
                    break;
                }
                case 3: {
                    IMinecraft iMinecraft = MinecraftInstance.mc;
                    Intrinsics.checkExpressionValueIsNotNull(iMinecraft, "mc");
                    d = (double)MinecraftInstance.classProvider.createScaledResolution(iMinecraft).getScaledWidth() - x;
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            element2.setX(d);
        }
        this.height += 10;
        this.realHeight += 10;
        Color color6 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color6, "Color.WHITE");
        Fonts.font35.drawString("V:", this.x + 2, this.y + this.height, color6.getRGB());
        String string12 = element2.getSide().getVertical().getSideName();
        Color color7 = Color.GRAY;
        Intrinsics.checkExpressionValueIsNotNull(color7, "Color.GRAY");
        Fonts.font35.drawString(string12, this.x + 12, this.y + this.height, color7.getRGB());
        if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
            double d;
            values2 = Side.Vertical.values();
            int currIndex = ArraysKt.indexOf(values2, element2.getSide().getVertical());
            double y = element2.getRenderY();
            element2.getSide().setVertical((Side.Vertical)values2[currIndex + 1 >= values2.length ? 0 : currIndex + 1]);
            switch (EditorPanel$WhenMappings.$EnumSwitchMapping$1[element2.getSide().getVertical().ordinal()]) {
                case 1: {
                    d = y;
                    break;
                }
                case 2: {
                    IMinecraft iMinecraft = MinecraftInstance.mc;
                    Intrinsics.checkExpressionValueIsNotNull(iMinecraft, "mc");
                    d = (double)(MinecraftInstance.classProvider.createScaledResolution(iMinecraft).getScaledHeight() / 2) - y;
                    break;
                }
                case 3: {
                    IMinecraft iMinecraft = MinecraftInstance.mc;
                    Intrinsics.checkExpressionValueIsNotNull(iMinecraft, "mc");
                    d = (double)MinecraftInstance.classProvider.createScaledResolution(iMinecraft).getScaledHeight() - y;
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            element2.setY(d);
        }
        this.height += 10;
        this.realHeight += 10;
        for (Value<?> value : element2.getValues()) {
            float min$iv;
            boolean $i$f$clamp_float;
            float max$iv;
            Value<?> value2 = value;
            if (value2 instanceof BoolValue) {
                int n;
                String string13 = value.getName();
                if (((Boolean)((BoolValue)value).get()).booleanValue()) {
                    Color color8 = Color.WHITE;
                    Intrinsics.checkExpressionValueIsNotNull(color8, "Color.WHITE");
                    n = color8.getRGB();
                } else {
                    Color color9 = Color.GRAY;
                    Intrinsics.checkExpressionValueIsNotNull(color9, "Color.GRAY");
                    n = color9.getRGB();
                }
                Fonts.font35.drawString(string13, this.x + 2, this.y + this.height, n);
                int stringWidth = Fonts.font35.getStringWidth(value.getName());
                if (this.width < stringWidth + 8) {
                    this.width = stringWidth + 8;
                }
                if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
                    ((BoolValue)value).set((Boolean)((BoolValue)value).get() == false);
                }
                this.height += 10;
                this.realHeight += 10;
                continue;
            }
            if (value2 instanceof FloatValue) {
                float current = ((Number)((FloatValue)value).get()).floatValue();
                float min = ((FloatValue)value).getMinimum();
                float max = ((FloatValue)value).getMaximum();
                String string14 = "%.2f";
                Object[] objectArray2 = new Object[]{Float.valueOf(current)};
                object = new StringBuilder().append(value.getName()).append(": \u00a7c");
                boolean bl2 = false;
                Intrinsics.checkExpressionValueIsNotNull(String.format(string14, Arrays.copyOf(objectArray2, objectArray2.length)), "java.lang.String.format(this, *args)");
                String text = ((StringBuilder)object).append((String)charSequence).toString();
                Color color10 = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color10, "Color.WHITE");
                Fonts.font35.drawString(text, this.x + 2, this.y + this.height, color10.getRGB());
                int stringWidth = Fonts.font35.getStringWidth(text);
                if (this.width < stringWidth + 8) {
                    this.width = stringWidth + 8;
                }
                RenderUtils.drawRect((float)this.x + 8.0f, (float)(this.y + this.height) + 12.0f, (float)(this.x + prevWidth) - 8.0f, (float)(this.y + this.height) + 13.0f, Color.WHITE);
                float sliderValue = (float)this.x + ((float)prevWidth - 18.0f) * (current - min) / (max - min);
                RenderUtils.drawRect(8.0f + sliderValue, (float)(this.y + this.height) + 9.0f, sliderValue + 11.0f, (float)(this.y + this.height) + 15.0f, new Color(37, 126, 255).getRGB());
                if (mouseX >= this.x + 8 && mouseX <= this.x + prevWidth && mouseY >= this.y + this.height + 9 && mouseY <= this.y + this.height + 15 && Mouse.isButtonDown((int)0)) {
                    void num$iv;
                    float f = ((float)(mouseX - this.x) - 8.0f) / ((float)prevWidth - 18.0f);
                    float f2 = 0.0f;
                    max$iv = 1.0f;
                    $i$f$clamp_float = false;
                    float curr = num$iv < min$iv ? min$iv : (num$iv > max$iv ? max$iv : num$iv);
                    ((FloatValue)value).set(Float.valueOf(min + (max - min) * curr));
                }
                this.height += 20;
                this.realHeight += 20;
                continue;
            }
            if (value2 instanceof IntegerValue) {
                int current = ((Number)((IntegerValue)value).get()).intValue();
                int min = ((IntegerValue)value).getMinimum();
                int max = ((IntegerValue)value).getMaximum();
                String text = value.getName() + ": \u00a7c" + current;
                Color color11 = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color11, "Color.WHITE");
                Fonts.font35.drawString(text, this.x + 2, this.y + this.height, color11.getRGB());
                int stringWidth = Fonts.font35.getStringWidth(text);
                if (this.width < stringWidth + 8) {
                    this.width = stringWidth + 8;
                }
                RenderUtils.drawRect((float)this.x + 8.0f, (float)(this.y + this.height) + 12.0f, (float)(this.x + prevWidth) - 8.0f, (float)(this.y + this.height) + 13.0f, Color.WHITE);
                float sliderValue = (float)this.x + ((float)prevWidth - 18.0f) * (float)(current - min) / (float)(max - min);
                RenderUtils.drawRect(8.0f + sliderValue, (float)(this.y + this.height) + 9.0f, sliderValue + 11.0f, (float)(this.y + this.height) + 15.0f, new Color(37, 126, 255).getRGB());
                if (mouseX >= this.x + 8 && mouseX <= this.x + prevWidth && mouseY >= this.y + this.height + 9 && mouseY <= this.y + this.height + 15 && Mouse.isButtonDown((int)0)) {
                    float num$iv = ((float)(mouseX - this.x) - 8.0f) / ((float)prevWidth - 18.0f);
                    min$iv = 0.0f;
                    max$iv = 1.0f;
                    $i$f$clamp_float = false;
                    float curr = num$iv < min$iv ? min$iv : (num$iv > max$iv ? max$iv : num$iv);
                    ((IntegerValue)value).set((int)((float)min + (float)(max - min) * curr));
                }
                this.height += 20;
                this.realHeight += 20;
                continue;
            }
            if (value2 instanceof ListValue) {
                String string15 = value.getName();
                Color color12 = Color.WHITE;
                Intrinsics.checkExpressionValueIsNotNull(color12, "Color.WHITE");
                Fonts.font35.drawString(string15, this.x + 2, this.y + this.height, color12.getRGB());
                this.height += 10;
                this.realHeight += 10;
                for (String s : ((ListValue)value).getValues()) {
                    int n;
                    String text = "\u00a7c> \u00a7r" + s;
                    if (Intrinsics.areEqual(s, (String)((ListValue)value).get())) {
                        Color color13 = Color.WHITE;
                        Intrinsics.checkExpressionValueIsNotNull(color13, "Color.WHITE");
                        n = color13.getRGB();
                    } else {
                        Color color14 = Color.GRAY;
                        Intrinsics.checkExpressionValueIsNotNull(color14, "Color.GRAY");
                        n = color14.getRGB();
                    }
                    Fonts.font35.drawString(text, this.x + 2, this.y + this.height, n);
                    int stringWidth = Fonts.font35.getStringWidth(text);
                    if (this.width < stringWidth + 8) {
                        this.width = stringWidth + 8;
                    }
                    if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
                        ((ListValue)value).set(s);
                    }
                    this.height += 10;
                    this.realHeight += 10;
                }
                continue;
            }
            if (!(value2 instanceof FontValue)) continue;
            IFontRenderer fontRenderer = (IFontRenderer)((FontValue)value).get();
            String text = fontRenderer.isGameFontRenderer() ? "Font: " + fontRenderer.getGameFontRenderer().getDefaultFont().getFont().getName() + " - " + fontRenderer.getGameFontRenderer().getDefaultFont().getFont().getSize() : (Intrinsics.areEqual(fontRenderer, Fonts.minecraftFont) ? "Font: Minecraft" : "Font: Unknown");
            Color color15 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color15, "Color.WHITE");
            Fonts.font35.drawString(text, this.x + 2, this.y + this.height, color15.getRGB());
            int stringWidth = Fonts.font35.getStringWidth(text);
            if (this.width < stringWidth + 8) {
                this.width = stringWidth + 8;
            }
            if (Mouse.isButtonDown((int)0) && !this.mouseDown && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y + this.height && mouseY <= this.y + this.height + 10) {
                List<IFontRenderer> fonts;
                List<IFontRenderer> list = fonts = Fonts.getFonts();
                Intrinsics.checkExpressionValueIsNotNull(list, "fonts");
                Iterable $this$forEachIndexed$iv = list;
                boolean $i$f$forEachIndexed = false;
                int index$iv = 0;
                for (Object item$iv : $this$forEachIndexed$iv) {
                    void font;
                    int n = index$iv++;
                    boolean bl3 = false;
                    if (n < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    int n2 = n;
                    IFontRenderer iFontRenderer = (IFontRenderer)item$iv;
                    int index = n2;
                    boolean bl4 = false;
                    if (!Intrinsics.areEqual(font, fontRenderer)) continue;
                    FontValue fontValue = (FontValue)value;
                    IFontRenderer iFontRenderer2 = fonts.get(index + 1 >= fonts.size() ? 0 : index + 1);
                    Intrinsics.checkExpressionValueIsNotNull(iFontRenderer2, "fonts[if (index + 1 >= f\u2026s.size) 0 else index + 1]");
                    fontValue.set(iFontRenderer2);
                }
            }
            this.height += 10;
            this.realHeight += 10;
        }
        Color color16 = ClickGUI.generateColor();
        Intrinsics.checkExpressionValueIsNotNull(color16, "ClickGUI.generateColor()");
        RenderUtils.drawRect(this.x, this.y, this.x + this.width, this.y + 12, color16.getRGB());
        String string16 = "\u00a7l" + element2.getName();
        float f = (float)this.x + 2.0f;
        float f3 = (float)this.y + 3.5f;
        Color color17 = Color.WHITE;
        Intrinsics.checkExpressionValueIsNotNull(color17, "Color.WHITE");
        Fonts.font35.drawString(string16, f, f3, color17.getRGB());
        if (!element2.getInfo().force()) {
            float deleteWidth = (float)(this.x + this.width - Fonts.font35.getStringWidth("\u00a7lDelete")) - 2.0f;
            float f4 = (float)this.y + 3.5f;
            Color color18 = Color.WHITE;
            Intrinsics.checkExpressionValueIsNotNull(color18, "Color.WHITE");
            Fonts.font35.drawString("\u00a7lDelete", deleteWidth, f4, color18.getRGB());
            if (Mouse.isButtonDown((int)0) && !this.mouseDown && (float)mouseX >= deleteWidth && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + 10) {
                LiquidBounce.INSTANCE.getHud().removeElement(element2);
            }
        }
    }

    private final void drag(int mouseX, int mouseY) {
        if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + 12 && Mouse.isButtonDown((int)0) && !this.mouseDown) {
            this.drag = true;
            this.dragX = mouseX - this.x;
            this.dragY = mouseY - this.y;
        }
        if (Mouse.isButtonDown((int)0) && this.drag) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        } else {
            this.drag = false;
        }
    }

    public final int getX() {
        return this.x;
    }

    public final void setX(int n) {
        this.x = n;
    }

    public final int getY() {
        return this.y;
    }

    public final void setY(int n) {
        this.y = n;
    }

    public EditorPanel(@NotNull GuiHudDesigner hudDesigner, int x, int y) {
        Intrinsics.checkParameterIsNotNull(hudDesigner, "hudDesigner");
        this.hudDesigner = hudDesigner;
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 20;
        this.realHeight = 20;
    }
}

