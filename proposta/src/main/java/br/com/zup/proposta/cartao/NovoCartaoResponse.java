package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zup.proposta.proposta.Proposta;

public class NovoCartaoResponse {

	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	private String idProposta;
	
	public String getId() {
		return id;
	}
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	public String getTitular() {
		return titular;
	}
	public BigDecimal getLimite() {
		return limite;
	}
	public String getIdProposta() {
		return idProposta;
	}
	public Cartao toModel(Proposta proposta) {
		return new Cartao(id, emitidoEm, titular, limite);
		
	}
	
	
	
}
