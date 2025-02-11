package com.app.ellp.Module.Responsavel.Service;

import com.app.ellp.Module.Responsavel.DTOs.ResponsavelDTO;
import com.app.ellp.Module.Responsavel.Domain.Responsavel;
import com.app.ellp.Module.Responsavel.Repository.ResponsavelRepository;
import com.app.ellp.Module.Familia.Domain.Familia;
import com.app.ellp.Module.Familia.Repository.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private FamiliaRepository familiaRepository;

    // Criar novo responsável
    public Responsavel criarResponsavel(ResponsavelDTO responsavelDTO) {
        // Buscando a família
        Optional<Familia> familiaOpt = familiaRepository.findById(responsavelDTO.familiaId());
        if (familiaOpt.isEmpty()) {
            throw new RuntimeException("Família não encontrada!");
        }

        // Criando o objeto Responsavel
        Responsavel responsavel = new Responsavel(
                responsavelDTO.nome(),
                responsavelDTO.escolaridade(),
                responsavelDTO.emprego(),
                responsavelDTO.celular(),
                responsavelDTO.tipoResponsavel()
        );

        responsavel.setFamilia(familiaOpt.get());

        // Salvando no banco
        return responsavelRepository.save(responsavel);
    }

    // Buscar todos os responsáveis
    public List<Responsavel> buscarTodosResponsaveis() {
        return responsavelRepository.findAll();
    }

    // Buscar responsável por ID
    public Responsavel buscarResponsavelPorId(String id) {
        return responsavelRepository.findById(id).orElseThrow(() -> new RuntimeException("Responsável não encontrado!"));
    }

    // Atualizar responsável
    public Responsavel atualizarResponsavel(String id, ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = buscarResponsavelPorId(id);

        responsavel.setNome(responsavelDTO.nome());
        responsavel.setEscolaridade(responsavelDTO.escolaridade());
        responsavel.setEmprego(responsavelDTO.emprego());
        responsavel.setCelular(responsavelDTO.celular());
        responsavel.setTipoResponsavel(responsavelDTO.tipoResponsavel());

        Optional<Familia> familiaOpt = familiaRepository.findById(responsavelDTO.familiaId());
        if (familiaOpt.isPresent()) {
            responsavel.setFamilia(familiaOpt.get());
        }

        return responsavelRepository.save(responsavel);
    }

    // Deletar responsável
    public void deletarResponsavel(String id) {
        responsavelRepository.deleteById(id);
    }
}