package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.BookDTO;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    @Lazy
    private final BookDAO bookDAO;
    @Lazy
    private final BookMapper mapper = BookMapper.MAPPER;
    @Override
    public Page<Book> getAllPage(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber - 1,10);
        return bookDAO.findAll(page);
    }

    @Override
    public BookDTO findBookById(Long bookId){
        return bookDAO.getBookById(bookId);
    }
}
