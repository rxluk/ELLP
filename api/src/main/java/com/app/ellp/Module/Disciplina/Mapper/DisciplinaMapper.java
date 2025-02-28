package com.app.ellp.Module.Disciplina.Mapper;

import com.app.ellp.Module.Disciplina.DTOs.CreateDisciplinaDTO;
import com.app.ellp.Module.Disciplina.DTOs.DisciplinaDTO;
import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Escola.Service.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {

    @Autowired
    private EscolaService escolaService; // Serviço para buscar Escola pelo ID

    // Método para converter CreateDisciplinaDTO para Disciplina
    public Disciplina toDomain(CreateDisciplinaDTO createDisciplinaDTO) {
        Escola escola = escolaService.buscarEscolaPorId(createDisciplinaDTO.escolaId());
        if (escola == null) {
            return null; // Retorna null se a escola não for encontrada
        }
        return new Disciplina(
                createDisciplinaDTO.nome(), // Nome da disciplina
                escola // A escola associada à disciplina
        );
    }

    // Método para converter Disciplina para DisciplinaDTO
    public DisciplinaDTO toDTO(Disciplina disciplina) {
        return new DisciplinaDTO(
                disciplina.getId(), // ID da disciplina
                disciplina.getNome(), // Nome da disciplina
                disciplina.getEscolaId() // ID da escola associada
        );
    }
    // Método para converter DisciplinaDTO para Disciplina
    public Disciplina toDomain(DisciplinaDTO disciplinaDTO) {
        // Buscar a Escola pelo ID contido no DisciplinaDTO
        Escola escola = escolaService.buscarEscolaPorId(disciplinaDTO.escolaId());

        if (escola == null) {
            return null; // Se a escola não for encontrada, retorna null
        }

        return new Disciplina(
                disciplinaDTO.nome(), // Nome da disciplina
                escola // A escola associada à disciplina
        );
    }
}
