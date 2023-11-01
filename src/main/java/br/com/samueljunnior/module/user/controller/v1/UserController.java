package br.com.samueljunnior.module.user.controller.v1;

import br.com.samueljunnior.module.user.dto.UserCreateDTO;
import br.com.samueljunnior.module.user.dto.UserDTO;
import br.com.samueljunnior.module.user.servie.UserService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Tag(
        name = "Usuário",
        description = "Operações sobre usuário.",
        externalDocs = @ExternalDocumentation(
                description = "Developer Website",
                url = "https://samueljunnior.github.io/about-me/"
        )
)
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(
            summary = "Busca os usuários.",
            description = "Busca todos os usuários cadastrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = UserDTO.class))}
                    )
            }
    )
    public ResponseEntity<List<UserDTO>> findUsers() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca usuário.",
            description = "Busca o usuário a partir do id informado..",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = UserDTO.class))}
                    )
            }
    )
    public ResponseEntity<UserDTO> findUser(@PathVariable(name = "id") Long idUser) {
        return ResponseEntity.ok(service.findUser(idUser));
    }

    @PostMapping
    @Operation(
            summary = "Cria um novo usuário",
            description = "Cria um novo usuário no banco de dados.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO dto) {
        final var user = service.createUser(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza o usuário.",
            description = "Atualiza todas as informações de um usuário.",
            responses = {
                    @ApiResponse(
                            responseCode = "204"
                    )
            }
    )
    public void updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDTO dto) {
        service.updateUser(id, dto);
    }

    @PatchMapping("/{id}/update-address")
    @Operation(
            summary = "Atualiza o endereço dp usuário.",
            description = "Atualiza o endereço do usuário a partir de um novo CEP.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @Parameters({
            @Parameter(
                    name = "cep",
                    description = "Novo CEP do usuário.",
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "string")
            )
    })
    public void updateAddress(
            @PathVariable(name = "id") Long id,
            @RequestParam(defaultValue = "0") String cep
    ){
        ResponseEntity.ok(service.updateAddresByCep(id, cep));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Exclui usuário",
            description = "Exclui usuário a partir do id informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "204"
                    )
            }
    )
    public void deleteUser(@PathVariable(name = "id") Long id){
        service.deleteUser(id);
    }


}
