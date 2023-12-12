package com.example.biblio.service;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.dto.BookParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    Page<Book> getAllPage(int pageNumber);
    BookDTO getBookPage(Long book);
    Page<Book> getSearchBooks(int pageNumber, String bookName);

    Book getBook(String bookName);
    Page<Book> getBooksByAuthor(int pageNumber, Long authorId);

    void increaseCountOfBook(Long bookId, int count);

    void decreaseCountOfBook(Long bookId, int count);

    Page<Book> getBooksByPublisher(int pageNumber, Long publisherId);

    Page<Book> getBooksByGenre(int pageNumber, Long genreId);

    void Create(BookParamsDTO dto);

    Page<Book> getBooksByDate(int pageNumber, Integer date);

    Book findBookByIdModel(Long bookId);
}
