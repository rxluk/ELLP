package com.app.ellp.Module.Colaborador.Repository;

import com.app.ellp.Module.Colaborador.Domain.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    Optional<Colaborador> findById(Long id);
    Optional<Colaborador> findByEmail(String email);
    List<Colaborador> findByNome(String nome);
    Optional<Colaborador> findByRegistro(String registro);
}
