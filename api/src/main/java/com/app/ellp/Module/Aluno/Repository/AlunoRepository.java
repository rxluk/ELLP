package com.app.ellp.Module.Aluno.Repository;

import com.app.ellp.Module.Aluno.Domain.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {
}