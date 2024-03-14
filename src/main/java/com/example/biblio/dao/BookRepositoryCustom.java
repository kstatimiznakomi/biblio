package com.example.biblio.dao;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> getBooksByCriteries(SearchParamsDTO dto);
}
