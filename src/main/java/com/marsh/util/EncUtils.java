package com.marsh.util;

import com.marsh.exception.error.enc.AesDecryptionFailedException;
import com.marsh.exception.error.enc.AesEncryptionFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class EncUtils {
    @Value("${marsh.aes-key}")
    private String aesKey;

    private final String ENC_ALGORITHM = "AES/CBC/PKCS5Padding";

    // AES 암호화 로직 (AES-256-CBC 사용)
    public String aesCbcEncode(String plainText) {
        try {
            // 랜덤 IV 생성
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            
            // 초기 세팅
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec IvParameterSpec = new IvParameterSpec(iv);
            Cipher c = Cipher.getInstance(ENC_ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec);

            // 암호화
            byte[] encryptedBytes = c.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // IV와 암호화 bytes 합침
            ByteBuffer byteBuffer = ByteBuffer.allocate(encryptedBytes.length + iv.length);
            byteBuffer.put(iv);
            byteBuffer.put(encryptedBytes);
            byte[] result = byteBuffer.array();

            // Base64 Encode
            return Base64.getEncoder().encodeToString(result);
        } catch(Exception e) {
            throw new AesEncryptionFailedException();
        }
    }

    // AES 복호화 로직 (AES-256-CBC 사용)
    public String aesCbcDecode(String cipherText) {
        try {
            // Base64 Decode
            byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText.getBytes(StandardCharsets.UTF_8));

            // IV와 암호화 bytes 분리
            byte[] iv = Arrays.copyOfRange(cipherTextBytes, 0, 16);
            byte[] encryptedBytes = Arrays.copyOfRange(cipherTextBytes, 16, cipherTextBytes.length);

            // 초기 세팅
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec IvParameterSpec = new IvParameterSpec(iv);
            Cipher c = Cipher.getInstance(ENC_ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec);

            // 복호화
            byte[] decryptedBytes = c.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch(Exception e) {
            throw new AesDecryptionFailedException();
        }
    }
}
