package br.com.zup.proposta.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.compartilhada.ExemploLogs;
import feign.FeignException;


@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(ExemploLogs.class);
	
    private final PropostaRepository propostaRepository;

	private final AnaliseClient analiseClient;

	public PropostaController(PropostaRepository propostaRepository, AnaliseClient analiseClient) {
		super();
		this.propostaRepository = propostaRepository;
		this.analiseClient = analiseClient;
	}

	@PostMapping
//    @Transactional
    public ResponseEntity<?>salvar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
    	
    	Optional<Proposta> possivelProprosta = propostaRepository.findByDocumento(request.getDocumento());

    	if(!possivelProprosta.isEmpty()) {//resposta do 422 erro de regras de negocio.
    		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma proposta co este documento "
    				+ "informado");
    	}
    	
        Proposta proposta = request.toModel();
                
        propostaRepository.save(proposta);
        
        AnaliseClient.ConsultaStatusRequest requisicaoAnalise = new AnaliseClient.ConsultaStatusRequest(proposta);        
        try {
        	AnaliseClient.ConsultaStatusResponse resposta = analiseClient.consulta(requisicaoAnalise);  //esta parte tem que ficar dentro try cath            	
        	proposta.atualizaStatus(resposta.getResultadoSolicitacao());
        	System.out.println("salvou 2 cath");
		} catch (FeignException.UnprocessableEntity e) {//so deu certo com FeignClientExceptiono 
			proposta.setStatus(Status.NAO_ELEGIVEL);
		}
        URI location = builder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        propostaRepository.save(proposta);
        
        //msg para desenvolvedor!
        logger.info("Proposta do nome={} com documento={} e salario={} e Status={} criada com sucesso!",
        		proposta.getNome(), proposta.getDocumento(), proposta.getSalario(), proposta.getStatus());
        
        return ResponseEntity.created(location).body(request);
    }
}
