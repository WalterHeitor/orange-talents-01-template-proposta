package br.com.zup.proposta.cartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Pageable paginacao = PageRequest.of(0, 5);
	
	private final CartaoClient cartaoClient;
	private final PropostaRepository propostaRepository;
	
	public ConsultaCartao(CartaoClient cartaoClient, PropostaRepository propostaRepository) {
		this.cartaoClient = cartaoClient;
		this.propostaRepository = propostaRepository;
	}
	
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
