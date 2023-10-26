package br.com.samueljunnior.module.viacep.controller.v1;

import br.com.samueljunnior.client.dto.ViaCepReponserDTO;
import br.com.samueljunnior.client.service.ViaCepService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/via-cep")
@RequiredArgsConstructor
@Tag(name = "Via Cep", description = "Operações sobre CEP", externalDocs = @ExternalDocumentation(description = "My Github", url = "https://github.com/samuelJunnior/"))
public class ViaCepController {

    private final ViaCepService viaCepService;

    @GetMapping("/find/{cep}")
    @Operation(
            summary = "Recupera a lista de entrevistas, paginada.",
            description = "Recupera uma lista de entrevistas, com paginação, utilizando filtro.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = ViaCepReponserDTO.class))}
                    )
            }
    )
    public ResponseEntity<ViaCepReponserDTO> findCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(viaCepService.findCep(cep));
    }

}
