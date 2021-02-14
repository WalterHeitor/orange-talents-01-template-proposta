package br.com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "${cartao.uri}")
public interface CartaoClient {

	
	@GetMapping
	NovoCartaoResponse consultarCartao(@RequestParam String idProposta);
		
}
