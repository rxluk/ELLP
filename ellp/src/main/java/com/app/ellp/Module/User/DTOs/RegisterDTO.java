package com.app.ellp.Module.User.DTOs;

import com.app.ellp.Module.User.Enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
