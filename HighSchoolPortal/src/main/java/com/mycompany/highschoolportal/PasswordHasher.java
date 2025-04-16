/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.highschoolportal;

/**
 *
 * @author ASUS
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
//extra feature(Password Hashing)
public class PasswordHasher {

    // Prefix for hashed passwords
    private static final String HASH_PREFIX = "HASH:";

    // Hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return HASH_PREFIX + Base64.getEncoder().encodeToString(hash); // Add prefix
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    // Check if a password is already hashed
    public static boolean isHashed(String password) {
        return password.startsWith(HASH_PREFIX);
    }

    // Verify a password against a stored password (hashed or plaintext)
    public static boolean verifyPassword(String password, String storedPassword) {
        if (isHashed(storedPassword)) {
            // Compare the hashed input with the stored hashed password
            String hashedInput = hashPassword(password);
            return hashedInput.equals(storedPassword);
        } else {
            // Compare the plaintext input with the stored plaintext password
            return password.equals(storedPassword);
        }
    }
}
