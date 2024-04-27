package com.example.biblio.dao;

import com.example.biblio.dto.SearchParamsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


public interface SearchParamsDAO /*extends JpaRepository<SearchParamsDTO, Long>, PagingAndSortingRepository<SearchParamsDTO, Long>*/ {
    //Page<Book> findAll(Specification<Book> specification, Pageable page);
}
