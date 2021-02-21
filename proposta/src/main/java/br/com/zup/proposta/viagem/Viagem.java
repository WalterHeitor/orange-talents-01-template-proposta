package br.com.zup.proposta.viagem;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Viagem {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private LocalDate instanteAviso;
	@NotBlank
	private String ip;
	@NotBlank
	private String userAgent;
	@Enumerated(EnumType.STRING)
	private StatusViagem status;
	
	@NotNull
    @ManyToOne
    private Cartao cartao;
	@Deprecated
	public Viagem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Viagem(@NotBlank String ip, @NotBlank String userAgent, @NotNull Cartao cartao) {
		super();
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.instanteAviso = LocalDate.now();
	}
	public Long getId() {
		return id;
	}
	public LocalDate getInstanteAviso() {
		return instanteAviso;
	}
	public String getIp() {
		return ip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public StatusViagem getStatus() {
		return status;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void atualizaStatus(StatusViagem status) {
		this.status = status;
	}

	
	
	
	
}
