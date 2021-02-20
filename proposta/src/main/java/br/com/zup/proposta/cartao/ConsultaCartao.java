package br.com.zup.proposta.cartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;
import br.com.zup.proposta.proposta.Status;



@Component
public class ConsultaCartao {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private  CartaoClient cartaoClient;
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Scheduled(fixedRateString = "${consulta.cartao}")
	public void consultarCartao() {
		
		List<Proposta> listPropostas = propostaRepository.listaPropostaSemCartao(Status.ELEGIVEL);//, paginacao
		if(!listPropostas.isEmpty()) {
			for(Proposta proposta: listPropostas) {
				try {
					NovoCartaoResponse cartaoRespose = cartaoClient.consultarCartao(proposta.getId().toString());
					proposta.atualizaCartao(cartaoRespose.toModel(proposta));
					propostaRepository.save(proposta);
					System.out.println(proposta.toString());	
				} catch (Exception e) {
					logger.info("Não foi possivel atribuir o caratão para={} com documento={}",
							proposta.getNome(), proposta.getDocumento());
				}
			}
		}
		
	}

	
}
