package br.com.zup.proposta.bloqueio;

public class BloqueioResponse {

	private String resultado;
	
	@Deprecated
	public BloqueioResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BloqueioResponse(String resultado) {
		super();
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
}
