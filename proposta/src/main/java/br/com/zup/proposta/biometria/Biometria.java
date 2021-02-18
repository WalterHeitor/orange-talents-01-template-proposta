package br.com.zup.proposta.biometria;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private byte[] digital;
    
    @NotNull
    private LocalDate dateCriacao;
    
    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
	public Biometria() {	}

	public Biometria(@NotBlank byte[] digital, @NotNull Cartao cartao) {
		super();
		this.digital = digital;
		this.cartao = cartao;
		this.dateCriacao = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public byte[] getDigital() {
		return digital;
	}

	@Override
	public String toString() {
		return "Biometria [id=" + id + ", digital=" + Arrays.toString(digital) + ", dateCriacao=" + dateCriacao
				+ ", cartao=" + cartao + "]";
	}
	
    
    
    
}
