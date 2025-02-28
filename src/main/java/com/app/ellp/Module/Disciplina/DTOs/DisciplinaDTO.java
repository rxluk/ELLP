package com.app.ellp.Module.Disciplina.DTOs;

import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import com.app.ellp.Module.Escola.Domain.Escola; // Importar a classe Escola

public record DisciplinaDTO(
        String id,
        String nome,
        String escolaId // Aqui é apenas o ID da escola
)
{}