package com.app.ellp.Module.Colaborador.DTOs;

import com.app.ellp.Module.User.Enums.UserRole;

public record CreateColaboradorDTO(String nome, String email, String registro, String login, String password, UserRole role) {
}
