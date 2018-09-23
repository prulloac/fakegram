package com.example.fakegram.dao;

import com.example.fakegram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmailAndPassword(String username, String email, String password);
}
