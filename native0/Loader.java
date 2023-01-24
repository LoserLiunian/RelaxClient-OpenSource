/*
 * Decompiled with CFR 0.152.
 */
package native0;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Loader {
    public static native void registerNativesForClass(int var0, Class<?> var1);

    private static int getOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            return 0;
        }
        if (osName.contains("linux")) {
            return 1;
        }
        if (osName.contains("mac")) {
            return 2;
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void load(String name) throws IOException {
        InputStream stream = Loader.class.getResourceAsStream("/" + name);
        if (stream == null) {
            throw new RuntimeException("Lib not found " + name);
        }
        File dir = new File("NativeDepends/");
        dir.mkdir();
        File file = new File("NativeDepends/" + name);
        FileOutputStream fileOutputStream = null;
        try {
            int length;
            fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[512];
            while ((length = stream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }
        }
        finally {
            try {
                stream.close();
            }
            catch (IOException iOException) {}
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (IOException iOException) {}
            }
        }
        System.load(file.getAbsolutePath());
    }

    static {
        int os = Loader.getOS();
        String name = null;
        switch (os) {
            case -1: {
                new RuntimeException("Unsupported os : " + System.getProperty("os.name")).printStackTrace();
                break;
            }
            case 0: {
                name = "libnative_library.vmp.dll";
                break;
            }
            case 1: {
                name = "load64.so";
                break;
            }
            case 2: {
                name = "load64.dylib";
            }
        }
        if (name != null) {
            try {
                Loader.load(name);
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}

