package br.com.zup.proposta.viagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("/avisos")
public class ViagemController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private CartaoClient cartaoClient;
	
	@PostMapping("/{id}")
	public ResponseEntity<?>cadastrar(@PathVariable("id") Long idCartao, HttpServletRequest servletRequest,
								@RequestBody ViagemRequest viagemRequest){
		Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
		if(possivelCartao.isEmpty()) {
			logger.info("Cartao não encontrado ");
			return ResponseEntity.notFound().build();
		}
		
		String ipAddress = servletRequest.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = servletRequest.getRemoteAddr();  
		}
		String userAgent = servletRequest.getHeader("User-Agent");
		
		ViagemResponse viagemResponse;
		Viagem viagem;
		try {
			viagemResponse = cartaoClient.avizarViagem(possivelCartao.get().getNumero(), viagemRequest);
			viagem = new Viagem(ipAddress, userAgent, possivelCartao.get());	
			viagem.atualizaStatus(StatusViagem.CRIADO);
			possivelCartao.get().incluiViajem(viagem);
			cartaoRepository.save(possivelCartao.get());
		} catch (FeignException.UnprocessableEntity e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Este Cartao ja esta bloquado");
		}
		
		return ResponseEntity.ok(viagemResponse);
	}

}
