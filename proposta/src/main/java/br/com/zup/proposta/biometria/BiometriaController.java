package br.com.zup.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {
	
	@Autowired
	CartaoRepository cartaoRepository;
	@Autowired
	BiometriaRepository biometriaRepository;
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?>criar(@PathVariable("id") Long idCartao,
								@RequestBody @Valid NovaBiometriaRequest request,
								UriComponentsBuilder builder){
		Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
		if(possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		if(!Base64.isBase64(request.getDigital())) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("A digital nao e uma string em formato Base64 valida. ");
		}
		Biometria biometria = request.toModel(possivelCartao.get());
		possivelCartao.get().incluiBiometria(biometria);
		cartaoRepository.save(possivelCartao.get());
		URI location = builder.path("/biometrias/{id}")
				.buildAndExpand(biometria.getId()).toUri();		
		return ResponseEntity.created(location).body(request);
	}

}
