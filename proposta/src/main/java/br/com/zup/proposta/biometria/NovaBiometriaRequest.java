package br.com.zup.proposta.biometria;



import java.util.Base64;

import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.cartao.Cartao;

public class NovaBiometriaRequest {

	@NotBlank
	private String digital;

	public String getDigital() {
		return digital;
	}

	public Biometria toModel(Cartao cartao) {
		byte[] digitalEncode = Base64.getEncoder().encode(digital.getBytes());
		return new Biometria(digitalEncode, cartao);
	}


	

	
	
	
}
