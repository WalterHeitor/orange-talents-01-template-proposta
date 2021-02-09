package br.com.zup.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?>salvar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);
        return ResponseEntity.created(null).body(request);
    }
}
