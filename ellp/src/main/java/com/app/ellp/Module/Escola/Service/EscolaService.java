package com.app.ellp.Module.Escola.Service;

import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Escola.DTOs.CreateEscolaDTO;
import com.app.ellp.Module.Escola.DTOs.EscolaDTO;
import com.app.ellp.Module.Escola.Repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

    // Criar uma nova escola
    public EscolaDTO criarEscola(CreateEscolaDTO createEscolaDTO) {
        Escola escola = new Escola(
                null, // ID será gerado automaticamente
                createEscolaDTO.nome(),
                createEscolaDTO.telefone(),
                createEscolaDTO.bairro(),
                createEscolaDTO.rua(),
                createEscolaDTO.numero()
        );

        escola = escolaRepository.save(escola);

        return new EscolaDTO(
                escola.getId(),
                escola.getNome(),
                escola.getTelefone(),
                escola.getBairro(),
                escola.getRua(),
                escola.getNumero()
        );
    }

    // Buscar todas as escolas
    public List<EscolaDTO> buscarTodasEscolas() {
        List<Escola> escolas = escolaRepository.findAll();

        return escolas.stream().map(escola -> new EscolaDTO(
                escola.getId(),
                escola.getNome(),
                escola.getTelefone(),
                escola.getBairro(),
                escola.getRua(),
                escola.getNumero()
        )).collect(Collectors.toList());
    }

    // Buscar escola por ID
    public EscolaDTO buscarEscolaPorId(String id) {
        Optional<Escola> escola = escolaRepository.findById(id);

        if (escola.isPresent()) {
            return new EscolaDTO(
                    escola.get().getId(),
                    escola.get().getNome(),
                    escola.get().getTelefone(),
                    escola.get().getBairro(),
                    escola.get().getRua(),
                    escola.get().getNumero()
            );
        }

        return null; // ou lançar exceção dependendo da necessidade
    }

    // Buscar escola pelo nome
    public List<EscolaDTO> buscarEscolaPorNome(String nome) {
        Optional<Escola> escolas = escolaRepository.findByNome(nome);

        return escolas.stream().map(escola -> new EscolaDTO(
                escola.getId(),
                escola.getNome(),
                escola.getTelefone(),
                escola.getBairro(),
                escola.getRua(),
                escola.getNumero()
        )).collect(Collectors.toList());
    }

    // Atualizar dados de uma escola
    public EscolaDTO atualizarEscola(String id, CreateEscolaDTO createEscolaDTO) {
        Optional<Escola> escolaOptional = escolaRepository.findById(id);

        if (escolaOptional.isPresent()) {
            Escola escola = escolaOptional.get();
            escola.setNome(createEscolaDTO.nome());
            escola.setTelefone(createEscolaDTO.telefone());
            escola.setBairro(createEscolaDTO.bairro());
            escola.setRua(createEscolaDTO.rua());
            escola.setNumero(createEscolaDTO.numero());

            escola = escolaRepository.save(escola);

            return new EscolaDTO(
                    escola.getId(),
                    escola.getNome(),
                    escola.getTelefone(),
                    escola.getBairro(),
                    escola.getRua(),
                    escola.getNumero()
            );
        }

        return null;
    }

    // Deletar uma escola
    public void deletarEscola(String id) {
        escolaRepository.deleteById(id);
    }
}