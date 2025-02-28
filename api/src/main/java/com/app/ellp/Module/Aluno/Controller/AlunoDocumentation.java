package com.app.ellp.Module.Aluno.Controller;

import com.app.ellp.Module.Aluno.DTOs.CreateAlunoDTO;
import com.app.ellp.Module.Aluno.DTOs.AlunoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "Bearer Auth")
public interface AlunoDocumentation {

    @Operation(summary = "Busca todos os alunos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
    })
    ResponseEntity<Object> buscarTodosAlunos();

    @Operation(summary = "Busca um aluno pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable String id);

    @Operation(summary = "Cria um novo aluno", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<AlunoDTO> criarAluno(@RequestBody CreateAlunoDTO createAlunoDTO);

    @Operation(summary = "Atualiza um aluno pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable String id, @RequestBody CreateAlunoDTO createAlunoDTO);

    @Operation(summary = "Deleta um aluno pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    ResponseEntity<Object> deletarAluno(@PathVariable String id);
}