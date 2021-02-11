package br.com.zup.proposta.proposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

	@NotBlank
	private String logadouro;
	@NotBlank
	private String cep;
	@NotBlank
	private String numero;
	@NotBlank
	private String complemento;
	
	public String getLogadouro() {
		return logadouro;
	}
	public String getCep() {
		return cep;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	
	public Endereco toModel() {
		return new Endereco(logadouro, cep, numero, complemento);
	}
}
