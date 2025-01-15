package com.app.ellp.Module.User.DTOs;

import com.app.ellp.Module.User.Enums.UserRole;

public record DetailUserDTO(String login, UserRole role) {
}
