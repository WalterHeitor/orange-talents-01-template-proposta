package br.com.zup.proposta.proposta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
	@NotBlank
	private String logadouro;
	@NotBlank
	private String cep;
	@NotBlank
	private String numero;
	@NotBlank
	private String complemento;
	
	/**
	 * @deprecated apenas para uso do framwork.
	 */
	@Deprecated
	public Endereco() {	}

	

	public Endereco(@NotBlank String logadouro, @NotBlank String cep, @NotBlank String numero,
			@NotBlank String complemento) {
		super();
		this.logadouro = logadouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}



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
	
	

}
