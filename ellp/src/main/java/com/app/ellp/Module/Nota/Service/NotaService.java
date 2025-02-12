package com.app.ellp.Module.Nota.Service;

import com.app.ellp.Module.Aluno.Domain.Aluno;
import com.app.ellp.Module.Aluno.Service.AlunoService;
import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import com.app.ellp.Module.Disciplina.Mapper.DisciplinaMapper;
import com.app.ellp.Module.Disciplina.Service.DisciplinaService;
import com.app.ellp.Module.Escola.Mapper.EscolaMapper;
import com.app.ellp.Module.Nota.Domain.Nota;
import com.app.ellp.Module.Nota.DTOs.CreateNotaDTO;
import com.app.ellp.Module.Nota.DTOs.NotaDTO;
import com.app.ellp.Module.Nota.Repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    @Autowired
    private EscolaMapper escolaMapper;

    // Método auxiliar para obter o aluno de forma eficiente
    private Aluno getAlunoById(String alunoId) {
        return alunoService.buscarAlunoPorId(alunoId).toDomain(); // Converte e retorna o aluno
    }

    // Método auxiliar para obter a disciplina de forma eficiente
    private Disciplina getDisciplinaById(String disciplinaId) {
        return disciplinaService.buscarDisciplinaPorId(disciplinaId); // Retorna a disciplina diretamente
    }

    // Método para criar uma nova Nota
    public NotaDTO criarNota(CreateNotaDTO createNotaDTO) {
        // Obtém aluno e disciplina
        Aluno aluno = getAlunoById(createNotaDTO.alunoId());
        Disciplina disciplina = getDisciplinaById(createNotaDTO.disciplinaId());

        // Se aluno ou disciplina não forem encontrados, retorna null
        if (aluno == null || disciplina == null) {
            return null;
        }

        // Cria a entidade Nota
        Nota nota = new Nota(
                null, // ID gerado automaticamente
                aluno,
                disciplina,
                createNotaDTO.primeiroBimestre(),
                createNotaDTO.segundoBimestre(),
                createNotaDTO.terceiroBimestre(),
                createNotaDTO.quartoBimestre(),
                createNotaDTO.ano()
        );

        // Salva a nota no banco e cria o DTO de retorno
        nota = notaRepository.save(nota);
        return new NotaDTO(
                nota.getId(),
                aluno.getId(),
                disciplina.getId(),
                nota.getPrimeiroBimestre(),
                nota.getSegundoBimestre(),
                nota.getTerceiroBimestre(),
                nota.getQuartoBimestre(),
                nota.getAno()
        );
    }

    // Método para buscar todas as Notas
    public List<NotaDTO> buscarTodasNotas() {
        List<Nota> notas = notaRepository.findAll();

        return notas.stream()
                .map(nota -> {
                    Aluno aluno = alunoService.buscarAlunoPorId(nota.getAluno().getId()).toDomain();
                    Disciplina disciplina = getDisciplinaById(nota.getDisciplina().getId());

                    return new NotaDTO(
                            nota.getId(),
                            aluno.getId(),
                            disciplina.getId(),
                            nota.getPrimeiroBimestre(),
                            nota.getSegundoBimestre(),
                            nota.getTerceiroBimestre(),
                            nota.getQuartoBimestre(),
                            nota.getAno()
                    );
                })
                .collect(Collectors.toList());
    }

    // Método para buscar uma Nota por ID
    public NotaDTO buscarNotaPorId(String id) {
        Optional<Nota> nota = notaRepository.findById(id);

        return nota.map(n -> {
            Aluno aluno = alunoService.buscarAlunoPorId(n.getAluno().getId()).toDomain();
            Disciplina disciplina = getDisciplinaById(n.getDisciplina().getId());

            return new NotaDTO(
                    n.getId(),
                    aluno.getId(),
                    disciplina.getId(),
                    n.getPrimeiroBimestre(),
                    n.getSegundoBimestre(),
                    n.getTerceiroBimestre(),
                    n.getQuartoBimestre(),
                    n.getAno()
            );
        }).orElse(null);
    }

    // Método para atualizar uma Nota
    public NotaDTO atualizarNota(String id, CreateNotaDTO createNotaDTO) {
        Optional<Nota> notaOptional = notaRepository.findById(id);

        return notaOptional.map(nota -> {
            Aluno aluno = getAlunoById(createNotaDTO.alunoId());
            Disciplina disciplina = getDisciplinaById(createNotaDTO.disciplinaId());

            if (aluno == null || disciplina == null) {
                return null;
            }

            // Atualiza os campos da nota com os dados novos
            nota.setAluno(aluno);
            nota.setDisciplina(disciplina);
            nota.setPrimeiroBimestre(createNotaDTO.primeiroBimestre());
            nota.setSegundoBimestre(createNotaDTO.segundoBimestre());
            nota.setTerceiroBimestre(createNotaDTO.terceiroBimestre());
            nota.setQuartoBimestre(createNotaDTO.quartoBimestre());
            nota.setAno(createNotaDTO.ano());

            // Salva a nota atualizada
            nota = notaRepository.save(nota);

            return new NotaDTO(
                    nota.getId(),
                    aluno.getId(),
                    disciplina.getId(),
                    nota.getPrimeiroBimestre(),
                    nota.getSegundoBimestre(),
                    nota.getTerceiroBimestre(),
                    nota.getQuartoBimestre(),
                    nota.getAno()
            );
        }).orElse(null);
    }

    // Método para deletar uma Nota
    public void deletarNota(String id) {
        notaRepository.deleteById(id);
    }
}