package com.app.ellp.Module.User.Controller;

import com.app.ellp.Module.User.DTOs.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Auth")
public interface UserDocumentation {

    @Operation(summary = "Busca um usuário pelo login", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes do usuário retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Object> BuscarUserPorLogin(@PathVariable String login);

    @Operation(summary = "Busca todos os usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    ResponseEntity<Object> BuscarTodosUsuarios();

    @Operation(summary = "Deleta um usuário pelo login", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Object> DeletarUsuario(@PathVariable String login);

    @Operation(summary = "Atualiza um usuário pelo login", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<Object> atualizarUsuario(@PathVariable String login, @RequestBody @Valid CreateUserDTO createUserDTO);

    @Operation(summary = "Busca todos os usuários com senha inclusa", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários com senha retornada com sucesso")
    })
    ResponseEntity<Object> BuscarTodosUsuariosWithPassword();
}
