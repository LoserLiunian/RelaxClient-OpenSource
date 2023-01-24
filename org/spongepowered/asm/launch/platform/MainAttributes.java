/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.ByteSource
 *  com.google.common.io.Files
 */
package org.spongepowered.asm.launch.platform;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public final class MainAttributes {
    private static final Map<URI, MainAttributes> instances = new HashMap<URI, MainAttributes>();
    protected final Attributes attributes;

    private MainAttributes() {
        this.attributes = new Attributes();
    }

    private MainAttributes(File jar) {
        this.attributes = MainAttributes.getAttributes(jar);
    }

    public final String get(String name) {
        if (this.attributes != null) {
            return this.attributes.getValue(name);
        }
        return null;
    }

    private static Attributes getAttributes(File codeSource) {
        Attributes attributes;
        if (codeSource == null) {
            return null;
        }
        if (codeSource.isFile() && (attributes = MainAttributes.getJarAttributes(codeSource)) != null) {
            return attributes;
        }
        if (codeSource.isDirectory() && (attributes = MainAttributes.getDirAttributes(codeSource)) != null) {
            return attributes;
        }
        return new Attributes();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Attributes getJarAttributes(File jar) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jar);
            Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                Attributes attributes = manifest.getMainAttributes();
                return attributes;
            }
        }
        catch (IOException iOException) {
        }
        finally {
            try {
                if (jarFile != null) {
                    jarFile.close();
                }
            }
            catch (IOException iOException) {}
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Attributes getDirAttributes(File dir) {
        File manifestFile = new File(dir, "META-INF/MANIFEST.MF");
        if (manifestFile.isFile()) {
            ByteSource source = Files.asByteSource((File)manifestFile);
            InputStream inputStream = null;
            try {
                inputStream = source.openBufferedStream();
                Manifest manifest = new Manifest(inputStream);
                Attributes attributes = manifest.getMainAttributes();
                return attributes;
            }
            catch (IOException iOException) {
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException iOException) {}
            }
        }
        return null;
    }

    public static MainAttributes of(File jar) {
        return MainAttributes.of(jar.toURI());
    }

    public static MainAttributes of(URI uri) {
        MainAttributes attributes = instances.get(uri);
        if (attributes == null) {
            attributes = new MainAttributes(new File(uri));
            instances.put(uri, attributes);
        }
        return attributes;
    }
}

