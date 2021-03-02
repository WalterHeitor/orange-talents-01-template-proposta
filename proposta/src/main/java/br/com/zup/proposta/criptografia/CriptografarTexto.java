package br.com.zup.proposta.criptografia;

import javax.validation.constraints.NotBlank;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class CriptografarTexto {
	
	static StandardPBEStringEncryptor criptografia;
	
	public static void novo() {
		criptografia = new StandardPBEStringEncryptor();
	    criptografia.setAlgorithm("PBEWithMD5AndDES");
	    criptografia.setSaltGenerator(new ZeroSaltGenerator());
	    criptografia.setPassword("pass");
	}
	
	public @NotBlank
	static String encode(String texto) {
		novo();
	    return criptografia.encrypt(texto);
	}
	
	public static String dencode(String texto) {
		novo();
	    return criptografia.decrypt(texto);
	}
	
	
}
