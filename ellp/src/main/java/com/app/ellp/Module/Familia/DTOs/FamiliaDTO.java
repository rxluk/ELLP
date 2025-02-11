package com.app.ellp.Module.Familia.DTOs;

public record FamiliaDTO(
        String id,
        int grupoFamiliar,
        boolean acessoInternet,
        boolean possuiComputador,
        boolean casaPropria,
        boolean possuiCarro,
        int pessoasEmpregadas,
        double renda
) {
}
