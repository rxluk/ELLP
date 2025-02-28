package com.app.ellp.Module.Nota.DTOs;

public record NotaDTO(
        String id,
        String idAluno, // Agora armazena o ID do Aluno
        String idDisciplina, // Agora armazena o ID da Disciplina
        Double primeiroBimestre,
        Double segundoBimestre,
        Double terceiroBimestre,
        Double quartoBimestre,
        Integer ano
) {}