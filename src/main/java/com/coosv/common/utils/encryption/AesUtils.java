package com.coosv.common.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年6月6日
 */
public class AesUtils {
	private static final String KEY_AES = "AES";  
	private static final int KEY_LENG = 16;  
	private static final int SCALE = 2;  
	  
    public static String encrypt(String src, String key) throws Exception {  
        if (key == null || key.length() != KEY_LENG) {  
            throw new Exception("key不满足条件");  
        }   
        byte[] raw = key.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);  
        Cipher cipher = Cipher.getInstance(KEY_AES);  
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);  
        byte[] encrypted = cipher.doFinal(src.getBytes());  
        return byte2hex(encrypted);  
    }  
  
    public static String decrypt(String src, String key) throws Exception {  
        if (key == null || key.length() != KEY_LENG) {  
            throw new Exception("key不满足条件");  
        }  
        byte[] raw = key.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);  
        Cipher cipher = Cipher.getInstance(KEY_AES);  
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);  
        byte[] encrypted1 = hex2byte(src);  
        byte[] original = cipher.doFinal(encrypted1);  
        String originalString = new String(original);  
        return originalString;  
    }  
  
    public static byte[] hex2byte(String strhex) {  
        if (strhex == null) {  
            return null;  
        }  
        int l = strhex.length();  
        if (l % SCALE == 1) {  
            return null;  
        }  
        byte[] b = new byte[l / 2];  
        for (int i = 0; i != l / SCALE; i++) {  
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),  
                    KEY_LENG);  
        }  
        return b;  
    }  
  
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = new StringBuilder().append(hs).append("0").append(stmp).toString();  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }  
}
