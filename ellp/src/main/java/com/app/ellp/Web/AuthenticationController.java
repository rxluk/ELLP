package com.app.ellp.Web;

import com.app.ellp.Module.User.DTOs.LoginRequestDTO;
import com.app.ellp.Module.User.DTOs.LoginResponseDTO;
import com.app.ellp.Module.User.DTOs.RegisterDTO;
import com.app.ellp.Module.User.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO json) {
        try {
            LoginResponseDTO response = userService.login(json.login(), json.password());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO("Falha na autenticação!"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO json) {
        try {
            userService.register(json);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}