package br.com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.biometria.Biometria;
import br.com.zup.proposta.bloqueio.Bloqueio;
import br.com.zup.proposta.carteira.Carteira;
import br.com.zup.proposta.viagem.StatusViagem;
import br.com.zup.proposta.viagem.Viagem;

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
	
	@Enumerated(EnumType.STRING)
	private CartaoStatus cartaoStatus = CartaoStatus.DESBLOQUEADO;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)//,cascade = CascadeType.REFRESH
	private List<Biometria>biometrias = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Bloqueio>bloqueios = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Viagem>viagens = new ArrayList<>();
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Carteira>carteiras = new ArrayList<>();
	
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
	
	public CartaoStatus getCartaoStatus() {
		return cartaoStatus;
	}
	public void atualizaCartaoStatus(CartaoStatus cartaoStatus) {
		this.cartaoStatus = cartaoStatus;
	}

	public void incluiBiometria(Biometria biometria) {
		biometrias.add(biometria);
		
	}
	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}
	public void incluiBloqueios(Bloqueio bloqueio) {
		bloqueios.add(bloqueio);
		this.cartaoStatus = CartaoStatus.BLOQUEADO;
	}

	public void incluiViajem(Viagem viagem) {
		viagem.atualizaStatus(StatusViagem.CRIADO);
		viagens.add(viagem);
		
	}

	public void incluirCarteira(Carteira carteira) {
		carteiras.add(carteira);
		
	}
	
		
	
}
