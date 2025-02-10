package com.app.ellp.Module.Responsavel.Controller;

import com.app.ellp.Module.Responsavel.DTOs.ResponsavelDTO;
import com.app.ellp.Module.Responsavel.Service.ResponsavelService;
import com.app.ellp.Module.Responsavel.Domain.Responsavel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Auth")
@RestController
@RequestMapping("/responsavel")
public class ResponsavelController extends BaseResponsavelController implements ResponsavelDocumentation {

    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> buscarTodosResponsaveis() {
        List<Responsavel> responsaveis = responsavelService.buscarTodosResponsaveis();
        return ResponseEntity.ok(responsaveis);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarResponsavelPorId(@PathVariable String id) {
        Responsavel responsavel = responsavelService.buscarResponsavelPorId(id);
        return ResponseEntity.ok(responsavel);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> criarResponsavel(@RequestBody ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelService.criarResponsavel(responsavelDTO);
        return ResponseEntity.status(201).body(responsavel);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarResponsavel(@PathVariable String id, @RequestBody ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelService.atualizarResponsavel(id, responsavelDTO);
        return ResponseEntity.ok(responsavel);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarResponsavel(@PathVariable String id) {
        responsavelService.deletarResponsavel(id);
        return ResponseEntity.ok().build();
    }
}