package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final PageService pageService;

    @GetMapping("")
    public String search(Model model, SearchParamsDTO dto, @RequestParam("page") int pageNumber){
        if (Objects.equals(dto.getSearchText(), "")) return "redirect:/catalog";
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute(
                "foundBooks",
                bookService.getSearchBooks(pageNumber, dto.getSearchText()).getContent()
        );
        model.addAttribute(
                "currentPage",
                pageService.GetBiggerLower(
                        pageNumber,
                        bookService.getSearchBooks(pageNumber, dto.getSearchText()).getTotalPages()
                )
        );
        model.addAttribute(
                "totalItems",
                bookService.getSearchBooks(pageNumber, dto.getSearchText()).getTotalElements()
        );
        model.addAttribute(
                "totalPages",
                bookService.getSearchBooks(pageNumber, dto.getSearchText()).getTotalPages()
        );
        return "search";
    }
}
