package com.app.ellp.Module.Familia.DTOs;

public record CreateFamiliaDTO(
        int grupoFamiliar,
        boolean acessoInternet,
        boolean possuiComputador,
        boolean casaPropria,
        boolean possuiCarro,
        int pessoasEmpregadas,
        double renda
) {
}
