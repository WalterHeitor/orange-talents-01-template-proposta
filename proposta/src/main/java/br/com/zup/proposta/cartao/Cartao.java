package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.biometria.Biometria;

@Entity
public class Cartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
	private String numero;
    @NotNull
	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;
	@NotNull
	private BigDecimal limite;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)//,cascade = CascadeType.REFRESH
	private List<Biometria>biometrias = new ArrayList<>();
	
	@Deprecated
	public Cartao() {	}

	public Cartao(@NotBlank String numero, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
			@NotNull BigDecimal limite) {
		super();
		this.numero = numero;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
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

	public void incluiBiometria(byte[] digital) {
		biometrias.add(new Biometria(digital, this));
	}

	public void incluiBiometria(Biometria biometria) {
		biometrias.add(biometria);
		
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numero=" + numero + ", emitidoEm=" + emitidoEm + ", titular=" + titular
				+ ", limite=" + limite +  "]";
	}
	
		
	
}
