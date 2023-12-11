package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.BookDTO;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.ReaderTicket;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookDAO bookDAO;
    private final BookMapper mapper = BookMapper.MAPPER;
    private final PageService pageService;
    @Lazy
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final GenreService genreService;

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
    public Book getBook(String bookName){
        return bookDAO.getBookByBookName(bookName);
    }

    @Override
    public Page<Book> getBooksByAuthor(int pageNumber, Long authorId){
        return new PageImpl<>(authorService.getAuthor(authorId).getBooks());
    }

    @Override
    public void increaseCountOfBook(Long bookId, int count){
        Book book = bookDAO.findBookById(bookId);
        book.setCount(book.getCount() + count);
        bookDAO.save(book);
    }

    @Override
    public void decreaseCountOfBook(Long bookId, int count){
        Book book = bookDAO.findBookById(bookId);
        if (book.getCount() - count >= 0){
            book.setCount(book.getCount() - count);
            bookDAO.save(book);
        }
    }

    @Override
    public Boolean ifAvailable(Long bookId){
        return bookDAO.findBookById(bookId).getCount() != 0;
    }

    @Override
    public Page<Book> getBooksByPublisher(int pageNumber, Long publisherId){
        return new PageImpl<>(publisherService.getBookByPublisher(publisherId).getBooks());
    }

    @Override
    public Page<Book> getBooksByGenre(int pageNumber, Long genreId){
        return new PageImpl<>(genreService.getGenre(genreId).getBooks());
    }

    @Override
    public Page<Book> getBooksByDate(int pageNumber, Integer date){
        return bookDAO.getBooksByPublicDate(
                date,
                pageService.getPage(pageNumber)
        );
    }

    @Override
    public Book findBookByIdModel(Long bookId){
        return bookDAO.findBookById(bookId);
    }
}
