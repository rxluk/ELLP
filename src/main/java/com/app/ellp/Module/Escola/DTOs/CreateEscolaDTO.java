package com.app.ellp.Module.Escola.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateEscolaDTO(
        @NotEmpty(message = "Nome não pode ser vazio")
        String nome,

        @NotEmpty(message = "Telefone não pode ser vazio")
        String telefone,

        @NotEmpty(message = "Bairro não pode ser vazio")
        String bairro,

        @NotEmpty(message = "Rua não pode ser vazio")
        String rua,

        @NotNull(message = "Número não pode ser nulo")
        int numero
) {}