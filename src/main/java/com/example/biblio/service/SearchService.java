package com.example.biblio.service;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;

public interface SearchService {
    public Page<Book> findAll(SearchParamsDTO dto);
}
