package org.alexandre.helper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptographyManager {

    private String symmetricKey;
    private SecretKey secretKey;
    private byte[] encrypted;
    private byte[] decrypted;
    private Cipher cipher;

    public CryptographyManager() {}

    public String getEncryptedString(String symmetricKey, String decryptedString) {
        this.symmetricKey = symmetricKey;
        this.secretKey = new SecretKeySpec(this.stringToByte(symmetricKey), "AES");
        this.decrypted = decryptedString.getBytes();

        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            this.encrypted = cipher.doFinal(decrypted);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.out.println("Erro ao criptografar String: (CryptographyManager)001\n" + e);

            return null;
        }
    }
    public String getEncryptedLong(String symmetricKey, Long decryptedLong) {
        this.symmetricKey = symmetricKey;
        this.secretKey = new SecretKeySpec(this.stringToByte(symmetricKey), "AES");
        this.decrypted = String.valueOf(decryptedLong).getBytes();

        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            this.encrypted = cipher.doFinal(decrypted);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.out.println("Erro ao criptografar Long: (CryptographyManager)002\n" + e);

            return null;
        }
    }

    public String getDecryptedString(String symmetricKey, String encryptedString) {
        this.symmetricKey = symmetricKey;
        this.secretKey = new SecretKeySpec(this.stringToByte(symmetricKey), "AES");
        this.encrypted = Base64.getDecoder().decode(encryptedString);

        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            this.decrypted = cipher.doFinal(encrypted);

            return new String(decrypted);
        } catch (Exception e) {
            System.out.println("Erro ao decriptografar String: (CryptographyManager)003\n" + e);

            return null;
        }
    }
    public Long getDecryptedLong(String symmetricKey, String encryptedLong) {
        this.symmetricKey = symmetricKey;
        this.secretKey = new SecretKeySpec(this.stringToByte(symmetricKey), "AES");
        this.encrypted = Base64.getDecoder().decode(encryptedLong);

        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            this.decrypted = cipher.doFinal(encrypted);

            return Long.valueOf(new String(decrypted));
        } catch (Exception e) {
            System.out.println("Erro ao decriptografar Long: (CryptographyManager)004\n" + e);

            return null;
        }
    }

    private byte[] stringToByte(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            return messageDigest.digest(string.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao converter String: (CryptographyManager)005\n" + e);

            return null;
        }
    }
    private String byteToString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
