package br.com.zup.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String documento;
    @NotBlank
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal salario;
    @NotNull
    @Embedded
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Deprecated
    public Proposta() { }

	public Proposta(@NotBlank String documento, @NotBlank String email, @NotBlank String nome,
			@Positive @NotNull BigDecimal salario, @NotNull Endereco endereco) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
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
	public Endereco getEndereco() {
		return endereco;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public void atualizaStatus(String solicitacao) {
		this.status = Status.resultadoPara(solicitacao);
		
	}
	
}
