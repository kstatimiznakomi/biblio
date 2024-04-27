package com.example.biblio.dao;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryCustom{
    Page<Book> getBooksByCriteries(SearchParamsDTO dto, Pageable pageable);
}
