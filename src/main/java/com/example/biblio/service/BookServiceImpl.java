package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.BookDTO;
import com.example.biblio.mapper.AuthorMapper;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.model.Book;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Lazy
    private final BookDAO bookDAO;
    private final BookMapper mapper = BookMapper.MAPPER;
    public BookServiceImpl(@Lazy BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public Page<Book> getAllPage(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber - 1,5);
        return bookDAO.findAll(page);
    }

    @Override
    public BookDTO getBookPage(Long book){
        Book bookOrig = bookDAO.findBookById(book);
        return mapper.fromBook(bookOrig);
    }

    @Override
    public Book findBookByIdModel(Long bookId){
        return bookDAO.findBookById(bookId);
    }
}
