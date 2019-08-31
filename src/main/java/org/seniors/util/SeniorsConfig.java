package org.seniors.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Injector of dependencies
 * @author <a hre="mailto:juliosugaya@gmail.com">Julio Sugaya</a>
 */
public class SeniorsConfig {

	private static PasswordEncoder passwordEncoder;

	public static PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public static void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		SeniorsConfig.passwordEncoder = passwordEncoder;
	}
}