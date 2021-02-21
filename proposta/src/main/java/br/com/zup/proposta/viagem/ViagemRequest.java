package br.com.zup.proposta.viagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ViagemRequest {

	@NotBlank
	private String destino;
	@NotNull @Future
	private LocalDate validoAte;
	public ViagemRequest(@NotBlank String destino, @NotNull @Future LocalDate validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}
	public String getDestino() {
		return destino;
	}
	public LocalDate getValidoAte() {
		return validoAte;
	}
	
	
}
