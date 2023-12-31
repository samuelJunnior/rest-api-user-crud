package br.com.samueljunnior.module.user.controller.v1;

import br.com.samueljunnior.core.pagination.PageableReponser;
import br.com.samueljunnior.module.user.dto.UserCreateDTO;
import br.com.samueljunnior.module.user.dto.UserDTO;
import br.com.samueljunnior.module.user.dto.UserFilter;
import br.com.samueljunnior.module.user.service.UserService;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/filter")
    @Operation(
            summary = "Busca os usuários pelo filtro.",
            description = "Busca todos os usuários cadastrados conforme o filtro informado de forma paginado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = UserDTO.class))}
                    )
            }
    )
//    Caso deseje alterar para no Swagger ficar por RequestParam.
//    @Parameters({
//        @Parameter(name = "name", description = "Filtro por nome", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
//        @Parameter(name = "cpf", description = "Filtro por CPF", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
//        @Parameter(name = "email", description = "Filtro por e-mail", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
//        @Parameter(name = "filter", hidden = true)
//    })
    public ResponseEntity<PageableReponser<UserDTO>> findUserByFilter(
        @RequestParam(defaultValue = "0") Integer pageIndex,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "name") String sortField,
        @RequestParam(defaultValue = "ASC") Sort.Direction sortType,
        UserFilter filter
    ) {
        final var paging = PageRequest.of(
                pageIndex,
                pageSize,
                Sort.by(sortType, sortField)
        );
        return ResponseEntity.ok(service.findUserByFilter(paging, filter));
    }

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
    ) {
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
    public void deleteUser(@PathVariable(name = "id") Long id) {
        service.deleteUser(id);
    }

    @PostMapping("/{id}/resend-email-subscription")
    @Operation(
            summary = "Reenvia o e-mail de inscrição.",
            description = "Reenvia o e-mail de inscrição para o usuário.",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    public ResponseEntity<Void> resendEmailSubscription(@PathVariable(name = "id") Long id) {
        service.resendEmailSubscription(id);
        return ResponseEntity.ok().build();
    }

}
