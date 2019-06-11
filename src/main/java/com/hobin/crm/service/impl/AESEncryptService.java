package com.hobin.crm.service.impl;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hobin.crm.service.EncryptService;
import com.hobin.crm.service.exception.EncryptException;

/**
 * AES encrypt/decrypt class.
 * @author xuxx
 */
@Service
public class AESEncryptService implements EncryptService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${server.phone-encrypt-key}")
    private String key;
    @Value("${server.phone-encrypt-iv}")
    private String initVector;

    public AESEncryptService() {}

    public AESEncryptService(String key, String initVector) {
        this.key = key;
        this.initVector = initVector;
    }

    @Override
    public String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            logger.error("Encrypt the given value failed", ex);
            throw new EncryptException();
        }
    }

    @Override
    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (Exception ex) {
            logger.error("Decrypt '{}' failed", encrypted, ex);
            throw new EncryptException();
        }
    }
}
