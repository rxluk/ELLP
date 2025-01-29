package com.app.ellp.Module.User.Repository;

import com.app.ellp.Module.User.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByLogin(String login);
    void deleteByLogin(String login);
}
