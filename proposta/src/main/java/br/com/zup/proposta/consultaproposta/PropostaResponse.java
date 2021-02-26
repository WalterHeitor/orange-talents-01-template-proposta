package br.com.zup.proposta.consultaproposta;

import java.math.BigDecimal;

import br.com.zup.proposta.criptografia.CriptografarTexto;
import br.com.zup.proposta.proposta.Proposta;

public class PropostaResponse {

	private String documento;
	private String email;
	private String nome;
	private BigDecimal salario;
	private String status;
	
	
	public PropostaResponse(String documento, String email, String nome, BigDecimal salario, String status) {
		super();
		this.documento = CriptografarTexto.dencode(documento);
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.status = status;
	}
	public String getDocumento() {
		return documento;
	}
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public String getStatus() {
		return status;
	}
	public static PropostaResponse transformaDTO(Proposta proposta) {

		return new PropostaResponse(proposta.getDocumento(), proposta.getEmail(), proposta.getNome(), proposta.getSalario(), proposta.getStatus().toString());
	}
	
	

}
