package com.app.ellp.Web;

import com.app.ellp.Module.Colaborador.DTOs.CreateColaboradorDTO;
import com.app.ellp.Module.Colaborador.Service.ColaboradorService;
import com.app.ellp.Module.User.DTOs.RequestLoginDTO;
import com.app.ellp.Module.User.DTOs.ResponseLoginDTO;
import com.app.ellp.Module.User.DTOs.CreateUserDTO;
import com.app.ellp.Module.User.Service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController implements AuthenticationDocumentation {

    @Autowired
    private UserService userService;
    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody @Valid RequestLoginDTO json) {
        try {
            ResponseLoginDTO response = userService.login(json.login(), json.password());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseLoginDTO("Falha na autenticação!"));
        }
    }

    @PostMapping("/register/user")
    public ResponseEntity<Object> register(@RequestBody @Valid CreateUserDTO json) {
        try {
            userService.register(json);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/colaborador")
    public ResponseEntity<Object> registerColaborador(@RequestBody @Valid CreateColaboradorDTO json) {
        try {
            colaboradorService.register(json);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}