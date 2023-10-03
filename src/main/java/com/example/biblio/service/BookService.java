package com.example.biblio.service;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    public Page<Book> getAllPage(int pageNumber);
}
