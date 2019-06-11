package com.hobin.crm.service;

/**
 * Define the interface for encrypt.
 * @author xuxx
 */
public interface EncryptService {
    String encrypt(String value);
    String decrypt(String encrypted);
}
