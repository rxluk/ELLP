package com.app.ellp.Module.Disciplina.Controller;

import com.app.ellp.Module.Disciplina.DTOs.CreateDisciplinaDTO;
import com.app.ellp.Module.Disciplina.DTOs.DisciplinaDTO;
import com.app.ellp.Module.Disciplina.Service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DisciplinaController extends BaseDisciplinaController implements DisciplinaDocumentation {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> buscarTodasDisciplinas() {
        return ResponseEntity.ok(disciplinaService.buscarTodasDisciplinas());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> buscarDisciplinaPorId(@PathVariable String id) {
        DisciplinaDTO disciplinaDTO = disciplinaService.buscarDisciplinaPorId(id);
        if (disciplinaDTO != null) {
            return ResponseEntity.ok(disciplinaDTO);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se não encontrar
    }

    @Override
    @PostMapping
    public ResponseEntity<DisciplinaDTO> criarDisciplina(@RequestBody CreateDisciplinaDTO createDisciplinaDTO) {
        DisciplinaDTO disciplinaDTO = disciplinaService.criarDisciplina(createDisciplinaDTO);
        return ResponseEntity.status(201).body(disciplinaDTO); // Retorna 201 (Criado) ao criar a disciplina
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> atualizarDisciplina(@PathVariable String id, @RequestBody CreateDisciplinaDTO createDisciplinaDTO) {
        DisciplinaDTO disciplinaDTO = disciplinaService.atualizarDisciplina(id, createDisciplinaDTO);
        if (disciplinaDTO != null) {
            return ResponseEntity.ok(disciplinaDTO);
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se não encontrar
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDisciplina(@PathVariable String id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.ok("Disciplina deletada com sucesso");
    }

    // Novo endpoint para buscar disciplina pelo nome
    @Override
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> buscarDisciplinaPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(disciplinaService.buscarDisciplinasPorNome(nome));
    }
}