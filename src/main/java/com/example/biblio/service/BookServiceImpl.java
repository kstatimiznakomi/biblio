package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.BookDTO;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookDAO bookDAO;
    private final BookMapper mapper = BookMapper.MAPPER;
    private final PageService pageService;

    @Override
    public Page<Book> getAllPage(int pageNumber) {
        return bookDAO.findAll(
                pageService.getPage(pageNumber)
        );
    }

    @Override
    public BookDTO getBookPage(Long book){
        return mapper.fromBook(bookDAO.findBookById(book));
    }

    @Override
    public Page<Book> getSearchBooks(int pageNumber, String bookName){
        return bookDAO.getBooksByBookNameContainsIgnoreCase(
                bookName,
                pageService.getPage(pageNumber)
        );
    }

    @Override
    public Book findBookByIdModel(Long bookId){
        return bookDAO.findBookById(bookId);
    }
}
