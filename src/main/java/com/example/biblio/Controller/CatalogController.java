package com.example.biblio.Controller;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.dto.GenresDTO;
import com.example.biblio.model.Book;
import com.example.biblio.service.AuthorService;
import com.example.biblio.service.BookService;
import com.example.biblio.service.GenreService;
import com.example.biblio.service.PageService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CatalogController {
    @Lazy
    private final BookService bookService;
    @Lazy
    private final AuthorService authorService;
    @Lazy
    private final GenreService genreService;
    @Lazy
    private PageService pageService;

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
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("books", books);
        return "catalog";
    }
}
