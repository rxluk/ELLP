package com.app.ellp.Module.Colaborador.Controller;

import com.app.ellp.Module.Colaborador.DTOs.CreateColaboradorDTO;
import com.app.ellp.Module.Colaborador.DTOs.DetailColaboradorDTO;
import com.app.ellp.Module.Colaborador.Domain.Colaborador;
import com.app.ellp.Module.Colaborador.Service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ColaboradorController extends BaseColaboradorController implements ColaboradorDocumentation {
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable String email) {
        Colaborador colaborador = colaboradorService.findByEmail(email);

        if (colaborador == null)
            return ResponseEntity.notFound().build();

        DetailColaboradorDTO json = new DetailColaboradorDTO(
                colaborador.getId(),
                colaborador.getNome(),
                colaborador.getEmail(),
                colaborador.getRegistro(),
                colaborador.getUser().getLogin(),
                colaborador.getUser().getRole()
        );
        return ResponseEntity.ok(json);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Colaborador colaborador = colaboradorService.findById(id);

        if(colaborador == null)
            return ResponseEntity.notFound().build();

        DetailColaboradorDTO json = new DetailColaboradorDTO(
                colaborador.getId(),
                colaborador.getNome(),
                colaborador.getEmail(),
                colaborador.getRegistro(),
                colaborador.getUser().getLogin(),
                colaborador.getUser().getRole()
        );
        return ResponseEntity.ok(json);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        try {
            List<Colaborador> colaboradores = colaboradorService.findAll();
            List<DetailColaboradorDTO> json = new ArrayList<>();

            if(!colaboradores.isEmpty()) {
                for(Colaborador colaborador : colaboradores) {
                    json.add(new DetailColaboradorDTO(
                            colaborador.getId(),
                            colaborador.getNome(),
                            colaborador.getEmail(),
                            colaborador.getRegistro(),
                            colaborador.getUser().getLogin(),
                            colaborador.getUser().getRole()
                    ));
                }
                return ResponseEntity.ok(json);
            }
            return ResponseEntity.noContent().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        Colaborador colaborador = colaboradorService.findById(id);
        if(colaborador == null)
            return ResponseEntity.notFound().build();
        colaboradorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody CreateColaboradorDTO json) {
        if(colaboradorService.findById(id) == null)
            return ResponseEntity.notFound().build();
        Colaborador colaborador = colaboradorService.updateById(id, json);
        if(colaborador == null)
            return ResponseEntity.badRequest().build();

        DetailColaboradorDTO response = new DetailColaboradorDTO(
                colaborador.getId(), colaborador.getNome(), colaborador.getEmail(), colaborador.getRegistro(),
                colaborador.getUser().getLogin(), colaborador.getUser().getRole()
        );
        return ResponseEntity.ok(response);
    }
}
