package com.app.ellp.Module.Responsavel.Repository;

import com.app.ellp.Module.Responsavel.Domain.Responsavel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ResponsavelRepository extends MongoRepository<Responsavel, String> {
    Optional<Responsavel> findByCelular(String celular);

    List<Responsavel> findByNome(String nome);
}
