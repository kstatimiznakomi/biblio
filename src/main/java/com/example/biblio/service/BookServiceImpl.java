package com.example.biblio.service;

import com.example.biblio.dao.BookDAO;
import com.example.biblio.dto.BookDTO;
import com.example.biblio.dto.BookParamsDTO;
import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.mapper.BookMapper;
import com.example.biblio.model.Book;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    @Lazy
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
        System.out.println(bookDAO.getBooksByBookNameContainsIgnoreCase(
                bookName,
                pageService.getPage(pageNumber)
        ));
        return bookDAO.getBooksByBookNameContainsIgnoreCase(
                bookName,
                pageService.getPage(pageNumber)
        );
    }

    @Override
    public Page<Book> findAll(SearchParamsDTO dto) {

        /*if (dto.getGenreId() != null){
            Root<Genres> genresRoot = query.from(Genres.class);
            Join<Genres, Book> genreJoin = genresRoot.join("books", JoinType.INNER);
            predicates.add(cb.equal(genresRoot.get("id"), dto.getGenreId()));
            query.select(genreJoin.get("id"));
        }
        if (dto.getPublisherId() != null){
            Root<Publisher> publisherRoot = query.from(Publisher.class);
            Join<Publisher, Book> publisherJoin = publisherRoot.join("books", JoinType.INNER);
            predicates.add(cb.equal(publisherJoin.get("id"), dto.getPublisherId()));
            query.select(publisherJoin.get("id"));
        }*/

        return bookDAO.getBooksByCriteries(dto);
                //bookDAO.getBooksByBookNameContainsIgnoreCase(dto.getSearchText(), pageService.getPage(dto.getPage()));
        /*return paramsDAO.findAll((Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("authorId").as(String.class), "%"+dto.getAuthorId() + "%"));
            predicates.add(cb.equal(root.get("genreId").as(String.class), "%"+dto.getGenreId() + "%"));
            predicates.add(cb.equal(root.get("publisherId").as(String.class), "%"+dto.getPublisherId() + "%"));
            predicates.add(cb.like(root.get("searchText").as(String.class), "%"+dto.getSearchText() + "%"));
            predicates.add(cb.equal(root.get("publishDate").as(String.class), "%"+dto.getPublishDate() + "%"));
            predicates.add(cb.equal(root.get("page").as(String.class), "%"+dto.getPage() + "%"));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageService.getPage(dto.getPage()));*/
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
}
