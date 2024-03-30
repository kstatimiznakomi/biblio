package com.example.biblio.dao;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.service.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryCustom{
    Page<Book> getBooksByCriteries(SearchParamsDTO dto);
}
