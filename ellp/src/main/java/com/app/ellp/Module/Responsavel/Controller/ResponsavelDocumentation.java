package com.app.ellp.Module.Responsavel.Controller;

import com.app.ellp.Module.Responsavel.DTOs.ResponsavelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ResponsavelDocumentation {

    @Operation(summary = "Busca todos os responsáveis", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de responsáveis retornada com sucesso")
    })
    ResponseEntity<Object> buscarTodosResponsaveis();

    @Operation(summary = "Busca um responsável pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes do responsável retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Responsável não encontrado")
    })
    ResponseEntity<Object> buscarResponsavelPorId(@PathVariable String id);

    @Operation(summary = "Cria um novo responsável", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Responsável criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<Object> criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO);

    @Operation(summary = "Atualiza um responsável pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsável atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Responsável não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<Object> atualizarResponsavel(@PathVariable String id, @RequestBody ResponsavelDTO responsavelDTO);

    @Operation(summary = "Deleta um responsável pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsável deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Responsável não encontrado")
    })
    ResponseEntity<Object> deletarResponsavel(@PathVariable String id);
}