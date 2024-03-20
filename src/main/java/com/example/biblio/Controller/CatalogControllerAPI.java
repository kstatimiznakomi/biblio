package com.example.biblio.Controller;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.dto.GenresDTO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.User;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class CatalogControllerAPI {
    @Lazy
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    @GetMapping("/catalog/api/{pageNumber}")
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
    public Page<Object> getBooksBySearch(@Lazy SearchParamsDTO searchParamsDto){
        System.out.println(searchParamsDto);
        return bookService.findAll(searchParamsDto);
    }
}
