package com.example.biblio.dao;

import com.example.biblio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public interface FilterDAO extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {

}
