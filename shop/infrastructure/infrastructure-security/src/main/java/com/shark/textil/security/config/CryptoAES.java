package com.shark.textil.security.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
@Component
public class CryptoAES {

    private static final String CIPHER_CONFIG_TRANSFORMER = "AES/ECB/PKCS5Padding";
    private static final String SHA_1 = "SHA-1";
    private static final String ALGORITHM = "AES";
//
//    @Value("${credentials.secret}")
//    private String credentialSecret;
//
//    public String encryptCredentials(String strToEncrypt) {
//        if (strToEncrypt == null){
//            return null;
//        }
//        return encrypt(strToEncrypt, credentialSecret);
//    }

    public String encrypt(String strToEncrypt, String secret) {
        log.info("Encrypting : {}", strToEncrypt);
        try {
            secret = encodeSecret(secret);
            SecretKeySpec secretKey = getSecretKeySpec(secret);
            Cipher cipher = Cipher.getInstance(CIPHER_CONFIG_TRANSFORMER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(UTF_8)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

//    public String decryptCredentials(String strToDecrypt) {
//        return decrypt(strToDecrypt, credentialSecret);
//    }

    public String decrypt(String strToDecrypt, String secret) {
        log.info("Decrypting : {}", strToDecrypt);
        try {
            secret = encodeSecret(secret);
            SecretKeySpec secretKey = getSecretKeySpec(secret);
            Cipher cipher = Cipher.getInstance(CIPHER_CONFIG_TRANSFORMER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private SecretKeySpec getSecretKeySpec(String myKey) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SHA_1);
            byte[] key = Arrays.copyOf(sha.digest(myKey.getBytes(UTF_8)), 16);
            return new SecretKeySpec(key, ALGORITHM);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private String encodeSecret(String secret) {
        return Base64.getEncoder()
                .encodeToString(secret.getBytes());
    }

    public Key getSigningKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
