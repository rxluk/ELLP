package com.app.ellp.Module.Disciplina.Repository;

import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {

    // Buscar disciplina pelo nome
    List<Disciplina> findByNome(String nome);

    // Buscar disciplinas por escola
    List<Disciplina> findByEscolaId(String escolaId);

}