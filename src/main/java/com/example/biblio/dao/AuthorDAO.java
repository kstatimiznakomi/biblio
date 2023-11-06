package com.example.biblio.dao;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {
    Author getAuthorById(Long authorId);
}
