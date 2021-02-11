package br.com.zup.proposta.proposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.compartilhada.ExemploLogs;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(ExemploLogs.class);
	
    PropostaRepository propostaRepository;
    

    public PropostaController(PropostaRepository propostaRepository) {
		super();
		this.propostaRepository = propostaRepository;
	}


	@PostMapping
    @Transactional
    public ResponseEntity<?>salvar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
    	
    	Optional<Proposta> possivelProprosta = propostaRepository.findByDocumento(request.getDocumento());

    	if(!possivelProprosta.isEmpty()) {//resposta do 422 erro de regras de negocio.
    		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma proposta co este documento "
    				+ "informado");
    	}
    	
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);
        URI location = builder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        //msg para desenvolvedor!
        logger.info("Proposta do nome={} com documento={} e salario={} criada com sucesso!",
        		proposta.getNome(), proposta.getDocumento(), proposta.getSalario());
        
        return ResponseEntity.created(location).body(request);
    }
}
