package br.gov.prefeitura.almoxarifado.util.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GerarToken {

	public static byte[] gerarHash(String frase, String algoritmo) {
		// MD5, SHA-1 e SHA-256
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
