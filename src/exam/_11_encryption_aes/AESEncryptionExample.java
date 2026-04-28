package exam._11_encryption_aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESEncryptionExample {
    public static void main(String[] args) {
        String input = "Hello, World!";
        String key = "SecretKey1234567";

        String encrypted = encryptAES(input, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decryptAES(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }

    public static String encryptAES(String input, String key) {
        try {
            SecretKeySpec secretKey = createAesKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new IllegalStateException("AES 암호화 실패", e);
        }
    }

    public static String decryptAES(String encrypted, String key) {
        try {
            SecretKeySpec secretKey = createAesKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("AES 복호화 실패", e);
        }
    }

    private static SecretKeySpec createAesKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
            throw new IllegalArgumentException("AES key는 16/24/32 byte여야 합니다.");
        }
        return new SecretKeySpec(keyBytes, "AES");
    }
}
