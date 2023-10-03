package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookDAO bookDAO;
    @Override
    public List<Book> getBooks() {

        return null;
    }
}
