package com.app.ellp.Module.Responsavel.DTOs;

import com.app.ellp.Module.Responsavel.Enums.Escolaridade;

public record ResponsavelDTO(
        String nome,
        Escolaridade escolaridade,
        String emprego,
        String celular,
        boolean tipoResponsavel,
        String familiaId
) {
}
