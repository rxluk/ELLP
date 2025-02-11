package com.app.ellp.Module.Familia.Service;

import com.app.ellp.Module.Familia.Domain.Familia;
import com.app.ellp.Module.Familia.Repository.FamiliaRepository;
import com.app.ellp.Module.Familia.DTOs.CreateFamiliaDTO;
import com.app.ellp.Module.Familia.DTOs.FamiliaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FamiliaService {

    private final FamiliaRepository familiaRepository;

    @Autowired
    public FamiliaService(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    // Criar nova família
    public Familia criarFamilia(CreateFamiliaDTO createFamiliaDTO) {
        Familia familia = new Familia(
                null, // ID será gerado automaticamente pelo MongoDB
                createFamiliaDTO.grupoFamiliar(),
                createFamiliaDTO.acessoInternet(),
                createFamiliaDTO.possuiComputador(),
                createFamiliaDTO.casaPropria(),
                createFamiliaDTO.possuiCarro(),
                createFamiliaDTO.pessoasEmpregadas(),
                createFamiliaDTO.renda()
        );
        return familiaRepository.save(familia);
    }

    // Buscar todas as famílias
    public List<FamiliaDTO> buscarTodasFamilias() {
        List<Familia> familias = familiaRepository.findAll();
        return familias.stream()
                .map(familia -> new FamiliaDTO(
                        familia.getId(),
                        familia.getGrupoFamiliar(),
                        familia.isAcessoInternet(),
                        familia.isPossuiComputador(),
                        familia.isCasaPropria(),
                        familia.isPossuiCarro(),
                        familia.getPessoasEmpregadas(),
                        familia.getRenda()
                ))
                .collect(Collectors.toList());
    }

    // Buscar família por ID
    public FamiliaDTO buscarFamiliaPorId(String id) {
        Familia familia = familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Família não encontrada!"));
        return new FamiliaDTO(
                familia.getId(),
                familia.getGrupoFamiliar(),
                familia.isAcessoInternet(),
                familia.isPossuiComputador(),
                familia.isCasaPropria(),
                familia.isPossuiCarro(),
                familia.getPessoasEmpregadas(),
                familia.getRenda()
        );
    }

    // Atualizar família
    public FamiliaDTO atualizarFamilia(String id, CreateFamiliaDTO createFamiliaDTO) {
        Familia familia = familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Família não encontrada!"));

        familia.setGrupoFamiliar(createFamiliaDTO.grupoFamiliar());
        familia.setAcessoInternet(createFamiliaDTO.acessoInternet());
        familia.setPossuiComputador(createFamiliaDTO.possuiComputador());
        familia.setCasaPropria(createFamiliaDTO.casaPropria());
        familia.setPossuiCarro(createFamiliaDTO.possuiCarro());
        familia.setPessoasEmpregadas(createFamiliaDTO.pessoasEmpregadas());
        familia.setRenda(createFamiliaDTO.renda());

        familiaRepository.save(familia);

        return new FamiliaDTO(
                familia.getId(),
                familia.getGrupoFamiliar(),
                familia.isAcessoInternet(),
                familia.isPossuiComputador(),
                familia.isCasaPropria(),
                familia.isPossuiCarro(),
                familia.getPessoasEmpregadas(),
                familia.getRenda()
        );
    }

    // Deletar família
    public void deletarFamilia(String id) {
        familiaRepository.deleteById(id);
    }
}