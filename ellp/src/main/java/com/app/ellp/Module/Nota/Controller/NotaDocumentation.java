package com.app.ellp.Module.Nota.Controller;

import com.app.ellp.Module.Nota.DTOs.CreateNotaDTO;
import com.app.ellp.Module.Nota.DTOs.NotaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SecurityRequirement(name = "Bearer Auth")
public interface NotaDocumentation {

    @Operation(summary = "Cria uma nova Nota", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<NotaDTO> criarNota(@RequestBody @Valid CreateNotaDTO createNotaDTO);

    @Operation(summary = "Busca todas as Notas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notas retornada com sucesso")
    })
    ResponseEntity<Object> buscarTodasNotas();

    @Operation(summary = "Busca uma Nota por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes da nota retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    ResponseEntity<NotaDTO> buscarNotaPorId(@PathVariable String id);

    @Operation(summary = "Atualiza uma Nota pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    ResponseEntity<NotaDTO> atualizarNota(@PathVariable String id, @RequestBody @Valid CreateNotaDTO createNotaDTO);

    @Operation(summary = "Deleta uma Nota pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada")
    })
    ResponseEntity<Object> deletarNota(@PathVariable String id);
}