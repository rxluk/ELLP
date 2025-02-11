package com.app.ellp.Module.Escola.Repository;

import com.app.ellp.Module.Escola.Domain.Escola;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscolaRepository extends MongoRepository<Escola, String> {

    // Método para buscar uma escola pelo nome
    Optional<Escola> findByNome(String nome);

    // Método para buscar uma escola pelo id
    Optional<Escola> findById(String id);
}