package utils;

import javafx.collections.ObservableList;
import model.History;
import model.Users;
import repo.IRepo;
import repo.RepoFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {


    public static Users getCustomerFromEmail(String email) {
        Users u = null;
        IRepo repo = RepoFactory.getRepo();
        List<Users> users = repo.getAllUsers();
        for (Users user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return u;
    }

    public static String getTodaysDate() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static byte[] getNextSalt(){
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt);

        String s = Arrays.toString(pwdHash);
        System.out.println("Encryptor pwdHash: " + s);

        String s2 = Arrays.toString(expectedHash);
        System.out.println("Encryptor expectedHash: " + s2);

        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }

}
