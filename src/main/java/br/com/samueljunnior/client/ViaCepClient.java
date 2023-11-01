package br.com.samueljunnior.client;

import br.com.samueljunnior.client.dto.ViaCepReponserDTO;
import br.com.samueljunnior.core.feign.FeignClientDefaultConfig;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Via Cep",
        description = "Operações sobre CEP",
        externalDocs = @ExternalDocumentation(
                description = "Developer Website",
                url = "https://samueljunnior.github.io/about-me/"
        )
)
@FeignClient(value = "viaCep", url = "${integrations.viacep.base-url}", configuration = FeignClientDefaultConfig.class)
public interface ViaCepClient {


    @GetMapping("/ws/{cep}/json")
    @Operation(
            summary = "Recupera as informações a partir do CEP informado.",
            description = "Recupera as informações a partir do CEP informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = ViaCepReponserDTO.class))}
                    )
            }
    )
    ResponseEntity<ViaCepReponserDTO> getJson(@PathVariable(name = "cep") String cep);
}
