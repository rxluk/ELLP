package com.app.ellp.Module.Aluno.DTOs;

import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Familia.Domain.Familia;

import java.util.Date;

public record CreateAlunoDTO(
        String nome,
        String bairro,
        String rua,
        int numero,
        String serie,
        Date dataNascimento,
        boolean necessitaTransporte,
        boolean recebeAtendimentoMedico,
        Familia familia,
        Escola escola
) {}