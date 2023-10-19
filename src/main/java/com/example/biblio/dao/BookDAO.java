package com.example.biblio.dao;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Long> {
    Book findBookByBookName(String bookName);
    BookDTO getBookById(Long bookId);
    Book findBookById(Long bookId);
}
