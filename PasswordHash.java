package newzooauthenticationsystem;

import javax.crypto.SecretKeyFactory;
import newzooauthenticationsystem.NewZooAuthenticationSystem;
import newzooauthenticationsystem.NewAuthenticator;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class PasswordHash {
	
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException 
    {
    	new NewAuthenticator();
    	// Get user input password to hash
		String readPassword = NewAuthenticator.userPassword;
        String  originalPassword = readPassword;
        String successfulHash = generateHash(originalPassword);
        System.out.println(successfulHash);
    }
    public static String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

}