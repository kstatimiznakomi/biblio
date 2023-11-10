package com.example.biblio.service;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    Page<Book> getAllPage(int pageNumber);
    BookDTO getBookPage(Long book);
    Page<Book> getSearchBooks(int pageNumber, String bookName);

    Page<Book> getBooksByAuthor(int pageNumber, Long authorId);

    Page<Book> getBooksByPublisher(int pageNumber, Long publisherId);

    Page<Book> getBooksByDate(int pageNumber, Integer date);

    Book findBookByIdModel(Long bookId);
}
