package net.juanxxiii.womb.security;

import net.juanxxiii.womb.exceptions.PasswordMalFormedException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypter {
    public final static String algorithm = "Blowfish";

    public static String encryptPassword(String pass) throws PasswordMalFormedException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(pass.getBytes(StandardCharsets.UTF_8), algorithm);
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(pass.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printBase64Binary(encrypted);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            throw new PasswordMalFormedException("password is incorrect");
        }
        return null;
    }

    public static String decodePassword(String key, String encrypt) throws PasswordMalFormedException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            byte[] encrypted = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypt));
            return new String(encrypted);
         } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            throw  new PasswordMalFormedException("password is not the same");
        }
        return null;
    }

    public static String hashingPassword(String pass) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    pass.getBytes(StandardCharsets.UTF_8));
            result = Base64.getEncoder().encodeToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
