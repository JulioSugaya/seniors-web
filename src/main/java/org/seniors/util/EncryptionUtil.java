package org.seniors.util;

import java.security.MessageDigest;
//import java.util.Base64;
import java.util.UUID;

import org.springframework.security.crypto.codec.Base64;

/**
 * Encryption class to encrypt any String in a <code>Base64</code> String.
 * 
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public final class EncryptionUtil {

	public static String encrypt(String password) {

		String algorithm = "SHA1";
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);

			if (password != null) {
				md.update(password.getBytes());
				byte[] salt = md.digest();

				md.reset();
				md.update(password.getBytes());
				md.update(salt);
			}

			byte[] raw = md.digest();
			result = new String(Base64.encode(raw));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * @param userHashCode
	 * @return The hashCode used
	 */
	public static String generateUserHash(long userHashCode) {
		String userHash = "";

		try {
			UUID uuid = new UUID(System.currentTimeMillis(),
					System.currentTimeMillis() + userHashCode);
			userHash = uuid.toString() + "-" + System.currentTimeMillis();
		} catch (Exception e) {
			// TODO: Add logs
			e.printStackTrace();
		}
		return userHash;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			String encrypt = encrypt("123456");
			System.out.println(encrypt);
		}
	}
}
