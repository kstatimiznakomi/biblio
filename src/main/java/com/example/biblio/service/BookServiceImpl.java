package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.BookDTO;
import com.example.biblio.dto.BookParamsDTO;
import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.model.Book;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Lazy
    private final BookDAO bookDAO;
    private final BookMapper mapper = BookMapper.MAPPER;
    private final PageService pageService;
    @Lazy
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final GenreService genreService;

    public BookServiceImpl(BookDAO bookDAO, PageService pageService, @Lazy AuthorService authorService, PublisherService publisherService, GenreService genreService) {
        this.bookDAO = bookDAO;
        this.pageService = pageService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.genreService = genreService;
    }

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
    public Page<Book> findAll(SearchParamsDTO dto, Pageable pageable) {
        return bookDAO.getBooksByCriteries(dto, pageable);
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
    public Page<Book> getBooksByPublisher(int pageNumber, Long publisherId){
        return new PageImpl<>(publisherService.getBookByPublisher(publisherId).getBooks());
    }

    @Override
    public Page<Book> getBooksByGenre(int pageNumber, Long genreId){
        return new PageImpl<>(genreService.getGenre(genreId).getBooks());
    }

    @Override
    public void Create(BookParamsDTO dto){
        Book book = Book.builder()
                .bookName(dto.getBookName())
                .description(dto.getDescription())
                .img(dto.getImg())
                .count(dto.getCount())
                .publicDate(dto.getPublicDate())
                .isbn(dto.getIsbn())
                .build();
        bookDAO.save(book);
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

    @Override
    public List<BookDTO> getAllBooks() { return mapper.fromBookList(bookDAO.findAll()); }

    public void deleteBookById(Long bookId) { bookDAO.delete(bookDAO.getBookById(bookId)); }

    public void save(Book book) {
        if(book.getId() != null)
            bookDAO.save(book);
    }

    public void save(BookDTO bookDTO) {
        Book book = Book.builder()
                .bookName(bookDTO.getBookName())
                .description(bookDTO.getDescription())
                .img(bookDTO.getImg())
                .isbn(bookDTO.getIsbn())
                .publicDate(bookDTO.getPublicDate())
                .count(bookDTO.getCount())
                .build();
        bookDAO.save(book);
    }
}
