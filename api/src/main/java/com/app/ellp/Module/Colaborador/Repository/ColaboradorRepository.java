package com.app.ellp.Module.Colaborador.Repository;

import com.app.ellp.Module.Colaborador.Domain.Colaborador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository extends MongoRepository<Colaborador, String> {
    Optional<Colaborador> findByEmail(String email);
    Optional<Colaborador> findByRegistro(String registro);
    Optional<Colaborador> findById(String id);
    List<Colaborador> findByNome(String nome);
}