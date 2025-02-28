package com.app.ellp.Module.User.Enums;

import java.util.List;

public enum UserRole {
    ADMIN(List.of("ROLE_ADMIN", "ROLE_USER", "ROLE_VOLUNTARIO")),
    VOLUNTARIO(List.of("ROLE_VOLUNTARIO", "ROLE_USER")),
    USER(List.of("ROLE_USER"));

    private final List<String> permissions;

    UserRole(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}

