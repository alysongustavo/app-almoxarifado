package br.gov.prefeitura.almoxarifado.util.utilitarios;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

public class GeneratedHashPasswordUtil {

	private static Object salt;

	public static String generateHash(String password) {

		MessageDigestPasswordEncoder digestPasswordEncoder = getInstanceMessageDisterPassword();

		String encodePassword = digestPasswordEncoder.encode(password);

		return encodePassword;

	}

	private static MessageDigestPasswordEncoder getInstanceMessageDisterPassword() {

		// informo tipo de enconding que desejo

		MessageDigestPasswordEncoder digestPasswordEncoder = new MessageDigestPasswordEncoder(
				"MD5");

		return digestPasswordEncoder;

	}

}
