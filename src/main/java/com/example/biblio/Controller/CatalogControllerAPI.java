package com.example.biblio.Controller;

import com.example.biblio.dto.*;
import com.example.biblio.model.Book;
import com.example.biblio.model.User;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class  CatalogControllerAPI {
    @Lazy
    private final BookService bookService;
    @Lazy
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PageService pageService;
    private final PublisherService publisherService;
    @GetMapping("/catalog/api/{pageNumber}")
    @CrossOrigin
    public Page<Book> getBooks(@PathVariable int pageNumber){
        return bookService.getAllPage(pageNumber);
    }
    @GetMapping("/author/api")
    public List<AuthorDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/genres/api")
    public List<GenresDTO> getAllGenres(){
        return genreService.getAllGenres();
    }
    @GetMapping("/publishers/api")
    public List<PublisherDTO> getAllPublishers(){
        return publisherService.getAllPublishers();
    }
    @GetMapping("/signed-user")
    public Boolean userSigned(Principal principal){
        return principal != null;
    }
    @GetMapping("/catalog/api/search")
    public Page<Book> getBooksBySearch(SearchParamsDTO searchParamsDto) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("page " + searchParamsDto.getPage());
        return bookService.findAll(searchParamsDto, pageService.getPage(searchParamsDto.getPage()));
    }
    @GetMapping("/books/api")
    public List<BookDTO> getAllBooks() { return bookService.getAllBooks(); }
}
