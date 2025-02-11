package com.app.ellp.Module.Escola.Mapper;

import com.app.ellp.Module.Escola.DTOs.CreateEscolaDTO;
import com.app.ellp.Module.Escola.DTOs.EscolaDTO;
import com.app.ellp.Module.Escola.Domain.Escola;
import org.springframework.stereotype.Component;

@Component
public class EscolaMapper {

    // Método para converter CreateEscolaDTO para Escola (entidade)
    public Escola toDomain(CreateEscolaDTO createEscolaDTO) {
        return new Escola(
                null, // O ID da escola será gerado automaticamente
                createEscolaDTO.nome(), // Nome da escola
                createEscolaDTO.telefone(), // Telefone da escola
                createEscolaDTO.bairro(), // Bairro da escola
                createEscolaDTO.rua(), // Rua da escola
                createEscolaDTO.numero() // Número da escola
        );
    }

    // Método para converter Escola (entidade) para EscolaDTO
    public EscolaDTO toDTO(Escola escola) {
        return new EscolaDTO(
                escola.getId(), // ID da escola
                escola.getNome(), // Nome da escola
                escola.getTelefone(), // Telefone da escola
                escola.getBairro(), // Bairro da escola
                escola.getRua(), // Rua da escola
                escola.getNumero() // Número da escola
        );
    }
}
