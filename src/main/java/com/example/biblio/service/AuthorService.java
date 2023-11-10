package com.example.biblio.service;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    Author getAuthor(Long authorId);
}
