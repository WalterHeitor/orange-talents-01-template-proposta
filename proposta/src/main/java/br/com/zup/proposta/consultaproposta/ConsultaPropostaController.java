package br.com.zup.proposta.consultaproposta;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;

@RestController
@RequestMapping("/propostas")
public class ConsultaPropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/{id}")
	public ResponseEntity<?>buscar(@PathVariable("id") @NotNull Long id ){
		Optional<Proposta> proposta = propostaRepository.findById(id);
		if(!proposta.isPresent()) {
			logger.error("Não foi encontrada nenhuma proposta");
			return ResponseEntity.notFound().build();
		}
		logger.info("Encontrou a proposta dedesjada com id={} e nome={} ",
				proposta.get().getId(), proposta.get().getNome());
		return ResponseEntity.ok(PropostaResponse.transformaDTO(proposta.get()));
		//return ResponseEntity.ok(proposta);
	}
}
