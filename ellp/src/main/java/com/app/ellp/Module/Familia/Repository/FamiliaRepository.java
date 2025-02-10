package com.app.ellp.Module.Familia.Repository;

import com.app.ellp.Module.Familia.Domain.Familia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepository extends MongoRepository<Familia, String> {
}
