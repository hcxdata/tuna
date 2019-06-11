package com.hobin.crm.config;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.springframework.security.crypto.util.EncodingUtils.concatenate;
import static org.springframework.security.crypto.util.EncodingUtils.subArray;

/**
 * Password encoder using SHA-256 algorithm only one iteration with a prefix 8 bytes random salt.
 * The generated salt is prefix to the encrypted password in hex string format.
 * <p>
 *     This password encoder is different from {@code StandardPasswordEncoder}.
 *     The StandardPasswordEncoder use SHA-256 algorithm too, but iterates for 1024 times.
 * </p>
 * @author xuxx
 */
public class Sha256PasswordEncoder implements PasswordEncoder {
    private final MessageDigest digester;
    private final BytesKeyGenerator saltGenerator;

    /**
     * Constructs a Sha256PasswordEncoder.
     */
    public Sha256PasswordEncoder() {
        try {
            digester = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such hashing algorithm", e);
        }
        saltGenerator = KeyGenerators.secureRandom();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] salt = saltGenerator.generateKey();
        byte[] digest = digest(salt, rawPassword);
        return new String(Hex.encode(digest));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] digest = Hex.decode(encodedPassword);
        byte[] salt = subArray(digest, 0, saltGenerator.getKeyLength());
        return matches(digest, digest(salt, rawPassword));
    }

    private byte[] digest(byte[] salt, CharSequence rawPassword) {
        byte[] passwordBytes = Utf8.encode(rawPassword);
        byte[] digest = digester.digest(concatenate(salt, passwordBytes));
        return concatenate(salt, digest);
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     */
    private boolean matches(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }
}
