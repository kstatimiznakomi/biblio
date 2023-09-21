package com.example.biblio.dao;

import com.example.biblio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    User findFirstByUsername(String name);
    User getUserByUsername(String userNames);
}
