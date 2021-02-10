package br.com.zup.proposta.proposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.compartilhada.ExemploLogs;

import java.net.URI;

import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(ExemploLogs.class);
	
    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?>salvar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);
        URI location = builder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        //msg para desenvolvedor!
        logger.info("Proposta do nome={} com documento={} e salario={} criada com sucesso!",
        		proposta.getNome(),
        		proposta.getDocumento(),
        		proposta.getSalario());
        
        return ResponseEntity.created(location).body(request);
    }
}
