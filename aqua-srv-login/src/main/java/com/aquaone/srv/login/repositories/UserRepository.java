package com.aquaone.srv.login.repositories;


import com.aquaone.srv.login.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> {

    UserDetails findByNome(String login);

    UserDetails findAllById(UUID id);

    // UserDetails deleteAllById (UUID id);
}
