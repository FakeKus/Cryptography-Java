package org.alexandre.helper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;

public class CryptographyManager {

    private SecretKey secretKey;    //Objeto que encapsula a chave para o AES
    private byte[] encrypted;       //Dados que foram criptografados
    private byte[] decrypted;       //Dados que foram descriptografados
    private Cipher cipher;          //Objeto que realiza operações de criptografia/descriptografia

    //Construtor vazio
    public CryptographyManager(){}

    /**
     * Método para criptografar uma String com uma chave simétrica.
     * @param symmetricKey Chave simétrica usada na criptografia.
     * @param decryptedString String que será criptografada.
     * @return String criptografada em Base64.
     */
    public String getEncryptedString(String symmetricKey, String decryptedString) {
        //Geração da chave simétrica a partir da String fornecida
        this.secretKey = new SecretKeySpec(Objects.requireNonNull(this.stringToByte(symmetricKey)), "AES");
        //Converte a String a ser criptografada para bytes
        this.decrypted = decryptedString.getBytes();

        try {
            cipher = Cipher.getInstance("AES"); //Inicializa o Cipher para AES
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);    //Configura o Cipher para modo de criptografia
            this.encrypted = cipher.doFinal(decrypted);     //Realiza a criptografia

            //Resultado criptografado codificado em Base64
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.out.println("Erro ao criptografar String: (CryptographyManager)001\n" + e);

            return null;
        }
    }

    /**
     * Método para descriptografar uma String.
     * @param symmetricKey Chave usada na criptografia/descriptografia.
     * @param encryptedString Base64 contendo a String criptografada.
     * @return String original descriptografada.
     */
    public String getDecryptedString(String symmetricKey, String encryptedString) {
        //Geração da chave simétrica a partir da String fornecida
        this.secretKey = new SecretKeySpec(Objects.requireNonNull(this.stringToByte(symmetricKey)), "AES");
        //Decodifica o Base64 para obter os bytes originais criptografados
        this.encrypted = Base64.getDecoder().decode(encryptedString);

        try {
            cipher = Cipher.getInstance("AES"); //Inicializa o Cipher para AES
            cipher.init(Cipher.DECRYPT_MODE, secretKey);    //Configura o Cipher para modo de descriptografia
            this.decrypted = cipher.doFinal(encrypted);     //Realiza descriptografia

            //Retorna a String descriptografada
            return new String(decrypted);
        } catch (Exception e) {
            System.out.println("Erro ao descriptografar String: (CryptographyManager)003\n" + e);

            return null;
        }
    }

    /**
     * Método privado para converter uma String em bytes, utilizando SHA-256.
     * @param string String que será convertida.
     * @return Array de bytes gerado pela digestão da String.
     */
    private byte[] stringToByte(String string) {
        try {
            //Uso do algoritmo SHA-256 para converter a String em uma hash
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            return messageDigest.digest(string.getBytes());
        } catch (Exception e) {
            System.out.println("Erro ao converter String: (CryptographyManager)005\n" + e);

            return null;
        }
    }

    /**
     * Método para converter um array de bytes para uma String hexadecimal.
     * Útil para representar hashes ou dados binários de forma legível.
     * @param bytes Array de bytes.
     * @return Representação em String hexadecimal dos bytes.
     */
    private String byteToString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            //Cada byte é convertido para sua representação hexadecimal
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                //Garante que números menores tenham formato consistente
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
