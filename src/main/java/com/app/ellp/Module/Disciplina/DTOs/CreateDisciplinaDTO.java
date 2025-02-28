package com.app.ellp.Module.Disciplina.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDisciplinaDTO(
        @NotBlank String nome,
        @NotNull String escolaId
) {}