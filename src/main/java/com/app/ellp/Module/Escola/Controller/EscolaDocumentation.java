package com.app.ellp.Module.Escola.Controller;

import com.app.ellp.Module.Escola.DTOs.CreateEscolaDTO;
import com.app.ellp.Module.Escola.DTOs.EscolaDTO;
import com.app.ellp.Module.Escola.Domain.Escola;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SecurityRequirement(name = "Bearer Auth")
public interface EscolaDocumentation {

    @Operation(summary = "Busca todas as escolas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de escolas retornada com sucesso")
    })
    ResponseEntity<Object> buscarTodasEscolas();

    @Operation(summary = "Busca uma escola pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes da escola retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Escola não encontrada")
    })
    ResponseEntity<Escola> buscarEscolaPorId(@PathVariable String id);

    @Operation(summary = "Cria uma nova escola", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Escola criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<EscolaDTO> criarEscola(@RequestBody @Valid CreateEscolaDTO createEscolaDTO);

    @Operation(summary = "Atualiza uma escola pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Escola não encontrada")
    })
    ResponseEntity<EscolaDTO> atualizarEscola(@PathVariable String id, @RequestBody @Valid CreateEscolaDTO createEscolaDTO);

    @Operation(summary = "Deleta uma escola pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Escola não encontrada")
    })
    ResponseEntity<Object> deletarEscola(@PathVariable String id);

    @Operation(summary = "Busca uma escola pelo nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de escolas com o nome fornecido retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma escola encontrada com o nome fornecido")
    })
    ResponseEntity<Object> buscarEscolaPorNome(@PathVariable String nome);
}