package com.example.biblio.service;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    public Page<Book> getAllPage(int pageNumber);

    BookDTO findBookById(Long bookId);

    Book findBookByIdModel(Long bookId);
}
