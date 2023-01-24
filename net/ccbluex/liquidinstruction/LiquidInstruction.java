/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidinstruction;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class LiquidInstruction {
    public static void main(String[] args2) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JOptionPane.showMessageDialog(null, "\u5df2\u5c06\u60a8\u7684hwid\u81ea\u52a8\u590d\u5236\u5230\u526a\u8d34\u677f");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection transferable = new StringSelection(LiquidInstruction.getHWID());
        clipboard.setContents(transferable, null);
    }

    public static String getHWID() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] md5;
        String input = System.getProperty("user.name") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.home") + System.getenv("COMPUTERNAME");
        StringBuilder s = new StringBuilder();
        byte[] bytes = input.getBytes("UTF-8");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        for (byte b : md5 = messageDigest.digest(bytes)) {
            s.append(Integer.toHexString(b & 0xFF | 0x300), 0, 3);
        }
        return s.toString();
    }
}

