package br.com.zup.proposta.criptografia;

import javax.validation.constraints.NotBlank;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class CriptografarTexto {
	
	static StandardPBEStringEncryptor criptografia;
	
	public @NotBlank
	static String encode(String texto) {
		criptografia = new StandardPBEStringEncryptor();
	    criptografia.setAlgorithm("PBEWithMD5AndDES");
	    criptografia.setSaltGenerator(new ZeroSaltGenerator());
	    criptografia.setPassword("pass");
	    return criptografia.encrypt(texto);
	}
	
	public static String dencode(String texto) {
		criptografia = new StandardPBEStringEncryptor();
	    criptografia.setAlgorithm("PBEWithMD5AndDES");
	    criptografia.setSaltGenerator(new ZeroSaltGenerator());
	    criptografia.setPassword("pass");
	    return criptografia.decrypt(texto);
	}
	
	
}
