package br.com.zup.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

	@NotBlank @Email
	private String email;
	@NotBlank
	private String carteira;
	public CarteiraRequest(@NotBlank @Email String email, @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}
	public String getEmail() {
		return email;
	}
	public String getCarteira() {
		return carteira;
	}
	
	
}
