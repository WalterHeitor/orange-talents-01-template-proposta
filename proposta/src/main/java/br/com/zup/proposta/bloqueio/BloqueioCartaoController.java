package br.com.zup.proposta.bloqueio;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.CartaoStatus;
import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioCartaoController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private CartaoClient cartaoClient;

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?>bloquearCartao(@PathVariable("id") Long idCartao, HttpServletRequest servletRequest,
			UriComponentsBuilder builder){
		
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
		BloqueioResponse bloqueioResponse;
		Bloqueio bloqueio;
		try {
			bloqueioResponse = cartaoClient.bloqueiaCartao(possivelCartao.get().getNumero(), new BloqueioRequest("walter-proposta"));
			bloqueio = new Bloqueio(ipAddress, userAgent, possivelCartao.get());
			possivelCartao.get().incluiBloqueios(bloqueio);
			possivelCartao.get().atualizaCartaoStatus(CartaoStatus.BLOQUEADO);;
			cartaoRepository.save(possivelCartao.get());			
		} catch (FeignException.UnprocessableEntity e) {
			System.out.println("entrou feign");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Este Cartao ja esta bloquado");
		}
		URI location = builder.path("/bloqueios/{id}").buildAndExpand(bloqueio.getId()).toUri();
		return ResponseEntity.ok(bloqueioResponse);
	}

}
