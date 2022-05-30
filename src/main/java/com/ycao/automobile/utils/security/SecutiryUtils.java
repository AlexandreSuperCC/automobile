package com.ycao.automobile.utils.security;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Type SecutiryUtils.java
 * @Desc core code
 * @author yuan.cao@utbm.fr
 * @date 29/05/2022 12:28
 * @version 1.0
 */
public class SecutiryUtils {
    /**
     * md5 encryption
     *
     * @param source data source
     * @return 加密字符串
     */
    public static String MD5encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] encode = messageDigest.digest(source.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte anEncode : encode) {
                String hex = Integer.toHexString(0xff & anEncode);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return "";
    }


    public static String enAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(encryptedBytes);
        return encode;
    }

    public static String deAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(data);
        //byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(data);
        byte[] decValue = cipher.doFinal(buffer);
        return new String(decValue);
    }
}
