package com.app.ellp.Module.Familia.Controller;

import com.app.ellp.Module.Familia.DTOs.CreateFamiliaDTO;
import com.app.ellp.Module.Familia.DTOs.FamiliaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SecurityRequirement(name = "Bearer Auth")
public interface FamiliaDocumentation {

    @Operation(summary = "Busca uma família pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes da família retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada")
    })
    ResponseEntity<FamiliaDTO> buscarFamiliaPorId(@PathVariable String id);

    @Operation(summary = "Busca todas as famílias", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de famílias retornada com sucesso")
    })
    ResponseEntity<Object> buscarTodasFamilias();

    @Operation(summary = "Deleta uma família pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Família deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada")
    })
    ResponseEntity<Object> deletarFamilia(@PathVariable String id);

    @Operation(summary = "Atualiza uma família pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Família atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Família não encontrada")
    })
    ResponseEntity<FamiliaDTO> atualizarFamilia(@PathVariable String id, @RequestBody @Valid CreateFamiliaDTO createFamiliaDTO);

    @Operation(summary = "Cria uma nova família", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Família criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<FamiliaDTO> criarFamilia(@RequestBody @Valid CreateFamiliaDTO createFamiliaDTO);
}