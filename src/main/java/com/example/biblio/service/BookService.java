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

    Book findBookByIdModel(Long bookId);
}
