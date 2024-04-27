package com.example.biblio.service;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    Author getAuthor(Long authorId);
    void deleteAuthorById(Long authorId);

    void save(Author author);
    void save(AuthorDTO authorDTO);
}
