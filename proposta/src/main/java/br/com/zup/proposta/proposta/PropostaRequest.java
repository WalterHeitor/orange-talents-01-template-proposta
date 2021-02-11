package br.com.zup.proposta.proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal salario;
    
    private EnderecoRequest endereco;

    public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email,
                           @NotBlank String nome,  @NotNull BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
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
    
    public EnderecoRequest getEndereco() {
		return endereco;
	}

    public Proposta toModel() {
        return new Proposta(documento, email, nome, salario, endereco.toModel());
    }

	
}
