package br.com.samueljunnior.client;

import br.com.samueljunnior.client.dto.ViaCepReponserDTO;
import br.com.samueljunnior.core.feign.FeignClientDefaultConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viaCep", url = "${integrations.viacep.base-url}", configuration = FeignClientDefaultConfig.class)
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json")
    ResponseEntity<ViaCepReponserDTO> getJson(@PathVariable(name = "cep") String cep);
}
