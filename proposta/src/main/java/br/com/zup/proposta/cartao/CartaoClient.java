package br.com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "${cartao..url}")
public interface CartaoClient {

	
	@GetMapping
	public void consultarCartao(@RequestParam Long idProposta);
		
}
