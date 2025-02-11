package com.app.ellp.Module.Escola.DTOs;

public record EscolaDTO(
        String id,
        String nome,
        String telefone,
        String bairro,
        String rua,
        Integer numero
) {}