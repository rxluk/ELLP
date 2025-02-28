package com.app.ellp.Module.Aluno.DTOs;

import com.app.ellp.Module.Aluno.Domain.Aluno;
import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Familia.Domain.Familia;

import java.util.Date;

public record AlunoDTO(
        String id,
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
) {

    // Método toDomain para converter AlunoDTO para Aluno (entidade)
    public Aluno toDomain() {
        Aluno aluno = new Aluno(
                this.id,
                this.nome,
                this.bairro,
                this.rua,
                this.numero,
                this.serie,
                this.dataNascimento,
                this.necessitaTransporte,
                this.recebeAtendimentoMedico,
                this.familia,
                this.escola
        );
        return aluno;
    }
}