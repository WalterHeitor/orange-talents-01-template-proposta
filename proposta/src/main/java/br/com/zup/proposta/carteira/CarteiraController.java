package br.com.zup.proposta.carteira;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("/carteiras")
@Transactional
public class CarteiraController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private CartaoClient cartaoClient;
	
	@PostMapping("/{id}")
	public ResponseEntity<?>cadastrar(@PathVariable("id") Long idCartao,
								@RequestBody CarteiraRequest carteiraRequest,
		UriComponentsBuilder builder){
			
		Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
		if(possivelCartao.isEmpty()) {
			logger.info("Cartao não encontrado ");
			return ResponseEntity.notFound().build();
		}
		if(!existe(carteiraRequest)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A carteira informada não  pertence ao grupo PAYPAL ou "
					+ "SAMSUNG_PLAY insira corretamente!");
		}
		
		try {
			CarteiraResponse carteiraResponse = cartaoClient.cadastrarCarteira(possivelCartao.get().getNumero(), carteiraRequest);
			Carteira carteira = new Carteira(carteiraResponse.getId(), carteiraRequest.getEmail(), possivelCartao.get(), tipoDeCarteira(carteiraRequest));
			possivelCartao.get().incluirCarteira(carteira);
			cartaoRepository.save(possivelCartao.get());	
			URI location = builder.path("/cartoes/{id}/carteiras/{id}")
					.buildAndExpand(possivelCartao.get().getId(), carteira.getId()).toUri();
			return ResponseEntity.created(location).body(carteiraResponse);
		} catch (FeignException.UnprocessableEntity e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Esta Carteira ja esta associada a este cartao!");
		}
		
	}
	
	

	private StatusCarteira tipoDeCarteira(CarteiraRequest carteiraRequest) {
		if(carteiraRequest.getCarteira().equals(StatusCarteira.PAYPAL.toString())) {
			return StatusCarteira.PAYPAL;			
		}
		return StatusCarteira.SAMSUNG_PLAY;
	}


	/**
	 * saber se usuario esta inserindo a carteira com a escrita correta
	 * @param carteiraRequest
	 * @return
	 */
	private boolean existe(CarteiraRequest carteiraRequest) {
		return (
				(carteiraRequest.getCarteira().equalsIgnoreCase(StatusCarteira.PAYPAL.toString()))
			 || (carteiraRequest.getCarteira().equalsIgnoreCase(StatusCarteira.SAMSUNG_PLAY.toString())));
	}
}
