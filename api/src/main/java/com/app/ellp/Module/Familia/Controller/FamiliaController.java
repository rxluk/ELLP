package com.app.ellp.Module.Familia.Controller;

import com.app.ellp.Module.Familia.DTOs.CreateFamiliaDTO;
import com.app.ellp.Module.Familia.DTOs.FamiliaDTO;
import com.app.ellp.Module.Familia.Domain.Familia;
import com.app.ellp.Module.Familia.Service.FamiliaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/familia")
@SecurityRequirement(name = "Bearer Auth")
public class FamiliaController extends BaseFamiliaController implements FamiliaDocumentation {

    private final FamiliaService familiaService;

    @Autowired
    public FamiliaController(FamiliaService familiaService) {
        this.familiaService = familiaService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FamiliaDTO> buscarFamiliaPorId(@PathVariable String id) {
        FamiliaDTO familiaDTO = familiaService.buscarFamiliaPorId(id);
        return ResponseEntity.ok(familiaDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> buscarTodasFamilias() {
        return ResponseEntity.ok(familiaService.buscarTodasFamilias());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFamilia(@PathVariable String id) {
        familiaService.deletarFamilia(id);
        return ResponseEntity.ok("Família deletada com sucesso");
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<FamiliaDTO> atualizarFamilia(@PathVariable String id, @RequestBody @Valid CreateFamiliaDTO createFamiliaDTO) {
        FamiliaDTO familiaDTO = familiaService.atualizarFamilia(id, createFamiliaDTO);
        return ResponseEntity.ok(familiaDTO);
    }

    @Override
    @PostMapping
    public ResponseEntity<FamiliaDTO> criarFamilia(@RequestBody @Valid CreateFamiliaDTO createFamiliaDTO) {
        Familia familia = familiaService.criarFamilia(createFamiliaDTO); // Criação da família no serviço

        // Convertendo Familia em FamiliaDTO
        FamiliaDTO familiaDTO = new FamiliaDTO(
                familia.getId(),
                familia.getGrupoFamiliar(),
                familia.isAcessoInternet(),
                familia.isPossuiComputador(),
                familia.isCasaPropria(),
                familia.isPossuiCarro(),
                familia.getPessoasEmpregadas(),
                familia.getRenda()
        );

        return ResponseEntity.status(201).body(familiaDTO);  // Retorna o FamiliaDTO
    }
}