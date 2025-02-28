package com.app.ellp.Module.Nota.DTOs;

public record CreateNotaDTO(
        String alunoId,
        String disciplinaId,
        Double primeiroBimestre,
        Double segundoBimestre,
        Double terceiroBimestre,
        Double quartoBimestre,
        Integer ano
) {}