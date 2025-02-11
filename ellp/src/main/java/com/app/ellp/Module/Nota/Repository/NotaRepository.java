package com.app.ellp.Module.Nota.Repository;

import com.app.ellp.Module.Nota.Domain.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends MongoRepository<Nota, String> {

    // Buscar todas as notas de um aluno
    List<Nota> findByAlunoId(String alunoId);

    // Buscar todas as notas de uma disciplina
    List<Nota> findByDisciplinaId(String disciplinaId);

    // Buscar todas as notas de um aluno em uma disciplina específica
    List<Nota> findByAlunoIdAndDisciplinaId(String alunoId, String disciplinaId);

    // Buscar todas as notas por ano
    List<Nota> findByAno(Integer ano);

    // Buscar todas as notas de um aluno por ano
    List<Nota> findByAlunoIdAndAno(String alunoId, Integer ano);
}