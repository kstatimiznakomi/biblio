package com.example.biblio.dao;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorDAO extends JpaRepository<Author, Long> {
    
}
