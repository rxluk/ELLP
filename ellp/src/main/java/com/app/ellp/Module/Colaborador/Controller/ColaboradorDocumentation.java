package com.app.ellp.Module.Colaborador.Controller;

import com.app.ellp.Module.Colaborador.DTOs.CreateColaboradorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ColaboradorDocumentation {

    @Operation(summary = "Busca um colaborador pelo e-mail", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<Object> findByEmail(@PathVariable String email);

    @Operation(summary = "Busca um colaborador pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<Object> getById(@PathVariable String id);

    @Operation(summary = "Busca todos os colaboradores", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de colaboradores retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum colaborador encontrado")
    })
    ResponseEntity<Object> getAll();

    @Operation(summary = "Deleta um colaborador pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Colaborador deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<Object> deleteById(@PathVariable String id);

    @Operation(summary = "Atualiza um colaborador pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado")
    })
    ResponseEntity<Object> updateById(@PathVariable String id, @RequestBody CreateColaboradorDTO json);
}