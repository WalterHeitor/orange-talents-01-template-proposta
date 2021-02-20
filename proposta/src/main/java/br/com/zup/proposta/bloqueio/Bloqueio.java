package br.com.zup.proposta.bloqueio;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.proposta.cartao.Cartao;
@Entity
public class Bloqueio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotNull
	private LocalDate bloqueadoEm;
	@NotBlank
	private String ip;
	@NotBlank
	private String userAgent;
	@NotNull
	private Boolean status;
	
	@NotNull
    @ManyToOne
    private Cartao cartao;

	@Deprecated
	public Bloqueio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bloqueio(@NotBlank String ip, @NotBlank String userAgent,
			 @NotNull Cartao cartao) {
		super();
		this.bloqueadoEm = LocalDate.now();
		this.ip = ip;
		this.userAgent = userAgent;
		this.status = true;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Boolean getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
}
