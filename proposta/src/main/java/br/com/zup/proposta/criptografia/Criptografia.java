package br.com.zup.proposta.criptografia;

import java.util.stream.IntStream;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class Criptografia {
	
	public static void main(String[] args) {
        String textoParaEsconder = "Walter";

        StandardPBEStringEncryptor criptografia = new StandardPBEStringEncryptor();
        criptografia.setAlgorithm("PBEWithMD5AndDES");
        criptografia.setSaltGenerator(new ZeroSaltGenerator());
        criptografia.setPassword("pass");


        IntStream.range(0, 10).forEach(number -> {
            System.out.printf("Tentativa %d "
            		+ "com o valor %s "
            		+ "e o resultado criptografado eh: %s descriptografado eh: %s \n",
                    number,
                    textoParaEsconder,
                    criptografia.encrypt(textoParaEsconder),
                    criptografia.decrypt(criptografia.encrypt(textoParaEsconder)));
        });
    }

}
