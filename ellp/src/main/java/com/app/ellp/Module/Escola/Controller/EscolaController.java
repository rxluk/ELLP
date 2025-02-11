package com.app.ellp.Module.Escola.Controller;

import com.app.ellp.Module.Escola.DTOs.CreateEscolaDTO;
import com.app.ellp.Module.Escola.DTOs.EscolaDTO;
import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Escola.Service.EscolaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/escola")
public class EscolaController extends BaseEscolaController implements EscolaDocumentation {

    private final EscolaService escolaService;

    @Autowired
    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> buscarTodasEscolas() {
        return ResponseEntity.ok(escolaService.buscarTodasEscolas());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Escola> buscarEscolaPorId(@PathVariable String id) {
        Escola escola = escolaService.buscarEscolaPorId(id);
        if (escola != null) {
            return ResponseEntity.ok(escola);
        }
        return ResponseEntity.notFound().build();  // Retorna 404 se a escola não for encontrada
    }

    @Override
    @PostMapping
    public ResponseEntity<EscolaDTO> criarEscola(@RequestBody @Valid CreateEscolaDTO createEscolaDTO) {
        EscolaDTO escolaDTO = escolaService.criarEscola(createEscolaDTO);
        return ResponseEntity.status(201).body(escolaDTO);  // Retorna 201 (Criado) ao criar uma escola
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EscolaDTO> atualizarEscola(@PathVariable String id, @RequestBody @Valid CreateEscolaDTO createEscolaDTO) {
        EscolaDTO escolaDTO = escolaService.atualizarEscola(id, createEscolaDTO);
        if (escolaDTO != null) {
            return ResponseEntity.ok(escolaDTO);
        }
        return ResponseEntity.notFound().build();  // Retorna 404 se a escola não for encontrada
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarEscola(@PathVariable String id) {
        escolaService.deletarEscola(id);
        return ResponseEntity.ok("Escola deletada com sucesso");
    }

    // Novo endpoint para buscar escola pelo nome
    @Override
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> buscarEscolaPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(escolaService.buscarEscolaPorNome(nome));
    }
}