package br.com.zup.proposta.proposta;

public enum Status {
	NAO_ELEGIVEL, ELEGIVEL;

	static Status resultadoPara(String solicitacao) {
		 if(solicitacao.equals("SEM_RESTRICAO")){
			return ELEGIVEL;
		}
		 return NAO_ELEGIVEL;
		
	}
}
