package com.example.biblio.dao;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.ReaderTicket;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {
    Page<Book> getBooksByBookNameContainsIgnoreCase(String bookName, Pageable page);
    BookDTO getBookById(Long bookId);
    Book findBookById(Long bookId);
    Page<Book> getBooksByPublicDate(Integer publishDate, Pageable pageable);
}
