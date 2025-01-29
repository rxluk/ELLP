package com.app.ellp.Module.Colaborador.DTOs;

import com.app.ellp.Module.User.Enums.UserRole;

public record DetailColaboradorDTO(
    String id,  
    String nome,
    String email,
    String registro,
    String login,
    UserRole role
) {}