/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.api.minecraft.client.renderer;

import kotlin.Metadata;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&J\b\u0010\u0012\u001a\u00020\u0003H&J\b\u0010\u0013\u001a\u00020\u0003H&J(\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H&\u00a8\u0006\u0019"}, d2={"Lnet/ccbluex/liquidbounce/api/minecraft/client/renderer/IGlStateManager;", "", "bindTexture", "", "textureID", "", "disableBlend", "disableCull", "disableLighting", "disableRescaleNormal", "disableTexture2D", "enableAlpha", "enableBlend", "enableColorMaterial", "enableTexture2D", "popAttrib", "popMatrix", "pushAttrib", "pushMatrix", "resetColor", "tryBlendFuncSeparate", "glSrcAlpha", "glOneMinusSrcAlpha", "glOne", "glZero", "Relaxed"})
public interface IGlStateManager {
    public void bindTexture(int var1);

    public void resetColor();

    public void enableTexture2D();

    public void enableBlend();

    public void tryBlendFuncSeparate(int var1, int var2, int var3, int var4);

    public void disableTexture2D();

    public void disableBlend();

    public void enableAlpha();

    public void disableLighting();

    public void disableCull();

    public void enableColorMaterial();

    public void disableRescaleNormal();

    public void pushMatrix();

    public void pushAttrib();

    public void popMatrix();

    public void popAttrib();
}

