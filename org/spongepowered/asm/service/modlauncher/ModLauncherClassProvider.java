/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.gross.Java9ClassLoaderUtil
 *  cpw.mods.modlauncher.Launcher
 */
package org.spongepowered.asm.service.modlauncher;

import cpw.mods.gross.Java9ClassLoaderUtil;
import cpw.mods.modlauncher.Launcher;
import java.net.URL;
import org.spongepowered.asm.service.IClassProvider;

class ModLauncherClassProvider
implements IClassProvider {
    ModLauncherClassProvider() {
    }

    @Override
    @Deprecated
    public URL[] getClassPath() {
        return Java9ClassLoaderUtil.getSystemClassPathURLs();
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return Class.forName(name, true, Thread.currentThread().getContextClassLoader());
    }

    @Override
    public Class<?> findClass(String name, boolean initialize) throws ClassNotFoundException {
        return Class.forName(name, initialize, Thread.currentThread().getContextClassLoader());
    }

    @Override
    public Class<?> findAgentClass(String name, boolean initialize) throws ClassNotFoundException {
        return Class.forName(name, initialize, Launcher.class.getClassLoader());
    }
}

