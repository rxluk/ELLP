package com.app.ellp.Module.Aluno.Controller;

import com.app.ellp.Module.Aluno.DTOs.CreateAlunoDTO;
import com.app.ellp.Module.Aluno.DTOs.AlunoDTO;
import com.app.ellp.Module.Aluno.Service.AlunoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Auth")
@RestController
@RequestMapping("/aluno")
public class AlunoController extends BaseAlunoController implements AlunoDocumentation {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> buscarTodosAlunos() {
        return ResponseEntity.ok(alunoService.buscarTodosAlunos());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable String id) {
        AlunoDTO alunoDTO = alunoService.buscarAlunoPorId(id);
        return ResponseEntity.ok(alunoDTO);
    }

    @Override
    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody CreateAlunoDTO createAlunoDTO) {
        AlunoDTO alunoDTO = alunoService.criarAluno(createAlunoDTO);
        return ResponseEntity.status(201).body(alunoDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable String id, @RequestBody CreateAlunoDTO createAlunoDTO) {
        AlunoDTO alunoDTO = alunoService.atualizarAluno(id, createAlunoDTO);
        return ResponseEntity.ok(alunoDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAluno(@PathVariable String id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.ok("Aluno deletado com sucesso");
    }
}