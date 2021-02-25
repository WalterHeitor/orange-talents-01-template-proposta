package br.com.zup.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zup.proposta.cartao.Cartao;

@Entity
public class Carteira {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String idSistema;
    private String email;
    
    @ManyToOne
    private Cartao cartao;
    
    @Enumerated(EnumType.STRING)
    private StatusCarteira statusCarteira;

	public Carteira() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Carteira(String idSistema, String email, Cartao cartao, StatusCarteira statusCarteira) {
		super();
		this.idSistema = idSistema;
		this.email = email;
		this.cartao = cartao;
		this.statusCarteira = statusCarteira;
	}

	public Long getId() {
		return id;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public String getEmail() {
		return email;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public StatusCarteira getStatusCarteira() {
		return statusCarteira;
	}

    
    
}
