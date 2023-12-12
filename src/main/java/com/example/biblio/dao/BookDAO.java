package com.example.biblio.dao;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDAO extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long>{
    Page<Book> getBooksByBookNameContainsIgnoreCase(String bookName, Pageable page);
    Page<Book> getBooksByBookNameAndPublicDate(String bookName, Integer publicDate, Pageable page);
    Book getBookById(Long bookId);
    Book getBookByBookName(String bookName);
    Book findBookById(Long bookId);
    Page<Book> getBooksByPublicDate(Integer publishDate, Pageable pageable);
}
