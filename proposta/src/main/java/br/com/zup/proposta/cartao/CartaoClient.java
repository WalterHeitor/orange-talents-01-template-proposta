package br.com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.proposta.bloqueio.BloqueioRequest;
import br.com.zup.proposta.bloqueio.BloqueioResponse;
import br.com.zup.proposta.viagem.ViagemRequest;
import br.com.zup.proposta.viagem.ViagemResponse;

@FeignClient(name = "cartao", url = "${cartao.uri}")
public interface CartaoClient {

	
	@GetMapping("/api/cartoes")
	NovoCartaoResponse consultarCartao(@RequestParam String idProposta);
	
	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
	BloqueioResponse bloqueiaCartao(@PathVariable("id") String numero, @RequestBody BloqueioRequest request);
	
	@PostMapping(value= "/api/cartoes/{id}/avisos")
	ViagemResponse avizarViagem(@PathVariable("id") String numero,@RequestBody ViagemRequest request);
		
}
