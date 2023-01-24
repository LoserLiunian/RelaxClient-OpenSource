/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package me.betterfps;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.utils.MinecraftInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u0004\u0018\u00010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000f\u00a8\u0006\u0013"}, d2={"Lme/betterfps/MiscUtils;", "Lnet/ccbluex/liquidbounce/utils/MinecraftInstance;", "()V", "make", "T", "object", "consumer", "Ljava/util/function/Consumer;", "(Ljava/lang/Object;Ljava/util/function/Consumer;)Ljava/lang/Object;", "openFileChooser", "Ljava/io/File;", "saveFileChooser", "showErrorPopup", "", "message", "", "title", "showURL", "url", "Relaxed"})
public final class MiscUtils
extends MinecraftInstance {
    public static final MiscUtils INSTANCE;

    public final void showErrorPopup(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        JOptionPane.showMessageDialog(null, message, "Alert", 0);
    }

    public final void showErrorPopup(@NotNull String title, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(message, "message");
        JOptionPane.showMessageDialog(null, message, title, 0);
    }

    public final void showURL(@NotNull String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        try {
            Desktop.getDesktop().browse(new URI(url));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public final File openFileChooser() {
        if (MinecraftInstance.mc.isFullScreen()) {
            MinecraftInstance.mc.toggleFullscreen();
        }
        JFileChooser fileChooser = new JFileChooser();
        JFrame frame = new JFrame();
        fileChooser.setFileSelectionMode(0);
        frame.setVisible(true);
        frame.toFront();
        frame.setVisible(false);
        int action = fileChooser.showOpenDialog(frame);
        frame.dispose();
        return action == 0 ? fileChooser.getSelectedFile() : null;
    }

    @Nullable
    public final File saveFileChooser() {
        if (MinecraftInstance.mc.isFullScreen()) {
            MinecraftInstance.mc.toggleFullscreen();
        }
        JFileChooser fileChooser = new JFileChooser();
        JFrame frame = new JFrame();
        fileChooser.setFileSelectionMode(0);
        frame.setVisible(true);
        frame.toFront();
        frame.setVisible(false);
        int action = fileChooser.showSaveDialog(frame);
        frame.dispose();
        return action == 0 ? fileChooser.getSelectedFile() : null;
    }

    public final <T> T make(T object, @NotNull Consumer<T> consumer) {
        Intrinsics.checkParameterIsNotNull(consumer, "consumer");
        consumer.accept(object);
        return object;
    }

    private MiscUtils() {
    }

    static {
        MiscUtils miscUtils;
        INSTANCE = miscUtils = new MiscUtils();
    }
}

