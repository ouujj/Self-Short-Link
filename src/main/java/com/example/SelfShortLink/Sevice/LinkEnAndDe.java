package com.example.SelfShortLink.Sevice;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LinkEnAndDe {

    private final static String key = "Bar12345Bar12345";

    public static String enCrypLink(String painString) {
        try {

            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(painString.getBytes());
            // System.err.println(new String(encrypted));

            return new String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String edCrypLink(String chiperString){
         try
        {

            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(chiperString.getBytes()));
            System.err.println(decrypted);
            return decrypted;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
