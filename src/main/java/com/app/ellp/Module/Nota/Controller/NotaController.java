package com.app.ellp.Module.Nota.Controller;

import com.app.ellp.Module.Nota.DTOs.CreateNotaDTO;
import com.app.ellp.Module.Nota.DTOs.NotaDTO;
import com.app.ellp.Module.Nota.Service.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota") // A rota base será "/nota"
public class NotaController extends BaseNotaController implements NotaDocumentation {

    @Autowired
    private NotaService notaService;

    @Override
    @PostMapping
    public ResponseEntity<NotaDTO> criarNota(@RequestBody @Valid CreateNotaDTO createNotaDTO) {
        NotaDTO notaDTO = notaService.criarNota(createNotaDTO);
        if (notaDTO != null) {
            return ResponseEntity.status(201).body(notaDTO); // Retorna com status 201 se a nota foi criada com sucesso
        }
        return ResponseEntity.status(400).build(); // Retorna 400 se houver algum erro na criação
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> buscarTodasNotas() {
        List<NotaDTO> notas = notaService.buscarTodasNotas();
        return ResponseEntity.ok(notas);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> buscarNotaPorId(@PathVariable String id) {
        NotaDTO notaDTO = notaService.buscarNotaPorId(id);
        return notaDTO != null ? ResponseEntity.ok(notaDTO) : ResponseEntity.status(404).build(); // Retorna 404 se a nota não for encontrada
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> atualizarNota(@PathVariable String id, @RequestBody @Valid CreateNotaDTO createNotaDTO) {
        NotaDTO notaDTO = notaService.atualizarNota(id, createNotaDTO);
        return notaDTO != null ? ResponseEntity.ok(notaDTO) : ResponseEntity.status(404).build(); // Retorna 404 se a nota não for encontrada
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarNota(@PathVariable String id) {
        try {
            notaService.deletarNota(id);
            return ResponseEntity.ok().build(); // Retorna 200 se a nota for deletada com sucesso
        } catch (Exception e) {
            return ResponseEntity.status(404).build(); // Retorna 404 se a nota não for encontrada
        }
    }
}