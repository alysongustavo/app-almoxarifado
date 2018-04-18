package br.gov.prefeitura.almoxarifado.util.utilitarios;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class GeneratedHashPasswordUtil {

	private static Object salt;

	public static String generateHash(String password) {

		MessageDigestPasswordEncoder digestPasswordEncoder = getInstanceMessageDisterPassword();

		String encodePassword = digestPasswordEncoder.encodePassword(password,
				salt);

		return encodePassword;

	}

	private static MessageDigestPasswordEncoder getInstanceMessageDisterPassword() {

		// informo tipo de enconding que desejo

		MessageDigestPasswordEncoder digestPasswordEncoder = new MessageDigestPasswordEncoder(
				"MD5");

		return digestPasswordEncoder;

	}

}
