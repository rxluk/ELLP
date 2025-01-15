package com.app.ellp.Module.User.Repository;

import com.app.ellp.Module.User.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByLogin(String login);
    void deleteByLogin(String login);
}
