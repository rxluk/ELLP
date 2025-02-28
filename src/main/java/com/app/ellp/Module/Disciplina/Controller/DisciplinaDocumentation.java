package com.app.ellp.Module.Disciplina.Controller;

import com.app.ellp.Module.Disciplina.DTOs.CreateDisciplinaDTO;
import com.app.ellp.Module.Disciplina.DTOs.DisciplinaDTO;
import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Auth")
public interface DisciplinaDocumentation {

    @Operation(summary = "Busca todas as disciplinas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de disciplinas retornada com sucesso")
    })
    ResponseEntity<Object> buscarTodasDisciplinas();

    @Operation(summary = "Busca uma disciplina pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes da disciplina retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    ResponseEntity<Disciplina> buscarDisciplinaPorId(@PathVariable String id);

    @Operation(summary = "Cria uma nova disciplina", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Disciplina criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<DisciplinaDTO> criarDisciplina(@RequestBody CreateDisciplinaDTO createDisciplinaDTO);

    @Operation(summary = "Atualiza uma disciplina pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<DisciplinaDTO> atualizarDisciplina(@PathVariable String id, @RequestBody CreateDisciplinaDTO createDisciplinaDTO);

    @Operation(summary = "Deleta uma disciplina pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplina deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    })
    ResponseEntity<Object> deletarDisciplina(@PathVariable String id);

    @Operation(summary = "Busca disciplinas pelo nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disciplinas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma disciplina encontrada com o nome fornecido")
    })
    ResponseEntity<Object> buscarDisciplinaPorNome(@PathVariable String nome);
}