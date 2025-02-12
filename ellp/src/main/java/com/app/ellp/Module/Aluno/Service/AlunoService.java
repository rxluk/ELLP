package com.app.ellp.Module.Aluno.Service;

import com.app.ellp.Module.Aluno.Domain.Aluno;
import com.app.ellp.Module.Aluno.DTOs.AlunoDTO;
import com.app.ellp.Module.Aluno.DTOs.CreateAlunoDTO;
import com.app.ellp.Module.Aluno.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    // Criar um novo aluno
    public AlunoDTO criarAluno(CreateAlunoDTO createAlunoDTO) {
        Aluno aluno = new Aluno(
                null, // O ID será gerado automaticamente
                createAlunoDTO.nome(),
                createAlunoDTO.bairro(),
                createAlunoDTO.rua(),
                createAlunoDTO.numero(),
                createAlunoDTO.serie(),
                createAlunoDTO.dataNascimento(),
                createAlunoDTO.necessitaTransporte(),
                createAlunoDTO.recebeAtendimentoMedico(),
                createAlunoDTO.familia(),
                createAlunoDTO.escola()
        );

        aluno = alunoRepository.save(aluno);

        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getBairro(),
                aluno.getRua(),
                aluno.getNumero(),
                aluno.getSerie(),
                aluno.getDataNascimento(),
                aluno.isNecessitaTransporte(),
                aluno.isRecebeAtendimentoMedico(),
                aluno.getFamilia(),
                aluno.getEscola()
        );
    }

    // Buscar todos os alunos
    public List<AlunoDTO> buscarTodosAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();

        return alunos.stream().map(aluno -> new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getBairro(),
                aluno.getRua(),
                aluno.getNumero(),
                aluno.getSerie(),
                aluno.getDataNascimento(),
                aluno.isNecessitaTransporte(),
                aluno.isRecebeAtendimentoMedico(),
                aluno.getFamilia(),
                aluno.getEscola()
        )).collect(Collectors.toList());
    }

    // Buscar aluno por ID
    public AlunoDTO buscarAlunoPorId(String id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isPresent()) {
            return new AlunoDTO(
                    aluno.get().getId(),
                    aluno.get().getNome(),
                    aluno.get().getBairro(),
                    aluno.get().getRua(),
                    aluno.get().getNumero(),
                    aluno.get().getSerie(),
                    aluno.get().getDataNascimento(),
                    aluno.get().isNecessitaTransporte(),
                    aluno.get().isRecebeAtendimentoMedico(),
                    aluno.get().getFamilia(),
                    aluno.get().getEscola()
            );
        }

        return null; // ou lançar exceção dependendo da necessidade
    }

    // Atualizar aluno
    public AlunoDTO atualizarAluno(String id, CreateAlunoDTO createAlunoDTO) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome(createAlunoDTO.nome());
            aluno.setBairro(createAlunoDTO.bairro());
            aluno.setRua(createAlunoDTO.rua());
            aluno.setNumero(createAlunoDTO.numero());
            aluno.setSerie(createAlunoDTO.serie());
            aluno.setDataNascimento(createAlunoDTO.dataNascimento());
            aluno.setNecessitaTransporte(createAlunoDTO.necessitaTransporte());
            aluno.setRecebeAtendimentoMedico(createAlunoDTO.recebeAtendimentoMedico());
            aluno.setFamilia(createAlunoDTO.familia());

            aluno = alunoRepository.save(aluno);

            return new AlunoDTO(
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getBairro(),
                    aluno.getRua(),
                    aluno.getNumero(),
                    aluno.getSerie(),
                    aluno.getDataNascimento(),
                    aluno.isNecessitaTransporte(),
                    aluno.isRecebeAtendimentoMedico(),
                    aluno.getFamilia(),
                    aluno.getEscola()
            );
        }

        return null;
    }

    // Deletar aluno
    public void deletarAluno(String id) {
        alunoRepository.deleteById(id);
    }
}