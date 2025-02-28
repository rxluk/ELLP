package com.app.ellp.Module.Disciplina.Service;

import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import com.app.ellp.Module.Disciplina.DTOs.CreateDisciplinaDTO;
import com.app.ellp.Module.Disciplina.DTOs.DisciplinaDTO;
import com.app.ellp.Module.Disciplina.Repository.DisciplinaRepository;
import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Escola.Repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final EscolaRepository escolaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository, EscolaRepository escolaRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.escolaRepository = escolaRepository;
    }

    // Criar uma nova disciplina
    public DisciplinaDTO criarDisciplina(CreateDisciplinaDTO createDisciplinaDTO) {
        // Buscar a escola pelo ID
        Optional<Escola> escolaOptional = escolaRepository.findById(createDisciplinaDTO.escolaId());

        if (escolaOptional.isEmpty()) {
            throw new RuntimeException("Escola não encontrada com o ID fornecido");
        }

        Escola escola = escolaOptional.get();

        // Criar a disciplina
        Disciplina disciplina = new Disciplina(
                createDisciplinaDTO.nome(),
                escola
        );

        disciplina = disciplinaRepository.save(disciplina);

        return new DisciplinaDTO(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getEscolaId()
        );
    }

    // Buscar todas as disciplinas
    public List<DisciplinaDTO> buscarTodasDisciplinas() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();

        return disciplinas.stream()
                .map(disciplina -> new DisciplinaDTO(
                        disciplina.getId(),
                        disciplina.getNome(),
                        disciplina.getEscolaId()
                ))
                .collect(Collectors.toList());
    }

    // Buscar disciplina por ID
    public Disciplina buscarDisciplinaPorId(String id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);

        return disciplina.get();
    }

    // Buscar disciplinas por nome
    public List<DisciplinaDTO> buscarDisciplinasPorNome(String nome) {
        List<Disciplina> disciplinas = disciplinaRepository.findByNome(nome);

        return disciplinas.stream()
                .map(disciplina -> new DisciplinaDTO(
                        disciplina.getId(),
                        disciplina.getNome(),
                        disciplina.getEscolaId()
                ))
                .collect(Collectors.toList());
    }

    // Buscar disciplinas por escola
    public List<DisciplinaDTO> buscarDisciplinasPorEscola(String escolaId) {
        List<Disciplina> disciplinas = disciplinaRepository.findByEscolaId(escolaId);

        return disciplinas.stream()
                .map(disciplina -> new DisciplinaDTO(
                        disciplina.getId(),
                        disciplina.getNome(),
                        disciplina.getEscolaId()
                ))
                .collect(Collectors.toList());
    }

    // Atualizar disciplina
    public DisciplinaDTO atualizarDisciplina(String id, CreateDisciplinaDTO createDisciplinaDTO) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);

        return disciplinaOptional.map(disciplina -> {
            // Buscar a escola pelo ID
            Optional<Escola> escolaOptional = escolaRepository.findById(createDisciplinaDTO.escolaId());
            escolaOptional.ifPresent(disciplina::setEscola);

            disciplina.setNome(createDisciplinaDTO.nome());

            disciplina = disciplinaRepository.save(disciplina);

            return new DisciplinaDTO(
                    disciplina.getId(),
                    disciplina.getNome(),
                    disciplina.getEscolaId()
            );
        }).orElse(null);
    }

    // Deletar uma disciplina
    public void deletarDisciplina(String id) {
        disciplinaRepository.deleteById(id);
    }
}