package application;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Cipher {
	
	public static String encrypt(String s) {
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PRIVATE_DETAILS.seed);
		String encrypted= encryptor.encrypt(s);

		return encrypted;
		
	}
	
	public static String decrypt(String s) {
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PRIVATE_DETAILS.seed);
		String decrypted = encryptor.decrypt(s);
		
		return decrypted;
		
	}
}
