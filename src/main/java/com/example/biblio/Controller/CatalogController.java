package com.example.biblio.Controller;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.dto.GenresDTO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.model.Book;
import com.example.biblio.service.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/catalog"})
public class CatalogController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final PageService pageService;

    public CatalogController(@Lazy BookService bookService, @Lazy AuthorService authorService, @Lazy GenreService genreService, @Lazy PublisherService publisherService, @Lazy PageService pageService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.publisherService = publisherService;
        this.pageService = pageService;
    }

    @GetMapping("")
    public String catalog(){
        return "redirect:/catalog/1";
    }
    @GetMapping("/{pageNumber}")
    public String getPage(Model model, @PathVariable int pageNumber){
        Page<Book> page = bookService.getAllPage(pageNumber);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        int curPage = pageService.GetBiggerLower(pageNumber, totalPages);
        List<Book> books = page.getContent();
        List<AuthorDTO> authors = authorService.getAllAuthors();
        List<GenresDTO> genres = genreService.getAllGenres();
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("publishers", publishers);
        model.addAttribute("books", books);
        return "catalog";
    }
}
