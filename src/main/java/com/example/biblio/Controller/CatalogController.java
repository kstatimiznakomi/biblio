package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping({"/catalog"})
public class CatalogController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final PageService pageService;
    private final UserService userService;
    private final RusToEngStatus rusToEngStatus;

    @GetMapping("")
    public String catalog(){
        return "redirect:/catalog/1";
    }
    @GetMapping("/{pageNumber}")
    public String getPage(Model model, @PathVariable int pageNumber, Principal principal){
        if (principal != null) {
            model.addAttribute("user", userService.getUserByName(principal.getName()));
            model.addAttribute("userStatus", rusToEngStatus.rusToEng(
                    userService.getUserByName(principal.getName()))
            );
            model.addAttribute("activeStat", UserStatus.Active);
        }
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute(
                "currentPage",
                pageService.GetBiggerLower(pageNumber, bookService.getAllPage(pageNumber).getTotalPages())
        );
        model.addAttribute("totalItems", bookService.getAllPage(pageNumber).getTotalElements());
        model.addAttribute("totalPages", bookService.getAllPage(pageNumber).getTotalPages());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("books", bookService.getAllPage(pageNumber).getContent());
        return "catalog";
    }
}
