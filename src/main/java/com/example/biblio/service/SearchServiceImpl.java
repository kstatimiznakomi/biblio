package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final BookDAO bookDAO;
    @Override
    public Page<Book> findAll(SearchParamsDTO dto) {
        return null;
    }
}
