package com.app.ellp.Web;

import com.app.ellp.Module.User.DTOs.RequestLoginDTO;
import com.app.ellp.Module.User.DTOs.ResponseLoginDTO;
import com.app.ellp.Module.User.DTOs.CreateUserDTO;
import com.app.ellp.Module.Colaborador.DTOs.CreateColaboradorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AuthenticationDocumentation {

    @Operation(summary = "Realiza o login de um usuário no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na autenticação")
    })
    ResponseEntity<ResponseLoginDTO> login(@RequestBody RequestLoginDTO json);

    @Operation(summary = "Registra um novo usuário no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<Object> register(@RequestBody CreateUserDTO json);

    @Operation(summary = "Registra um novo colaborador no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<Object> registerColaborador(@RequestBody CreateColaboradorDTO json);
}