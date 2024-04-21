package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Class that return a hash of passwrod 
public class HashProps {

    public static String generateHash( String password) {
        String algorithm = "SHA-256";
        String salt = "iitn-23/24";
        String data =  password + salt;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(data.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error generating hash: " + e.getMessage());
            return null;
        }
    }
}