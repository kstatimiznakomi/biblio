package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping({"/catalog"})
@Lazy
public class CatalogController {
    @Lazy
    private final BookService bookService;
    @Lazy
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final ReaderTicketService ticketService;
    private final PageService pageService;
    private final UserService userService;
    private final StatusManager statusManager;
    private final JournalNotesService notesService;

    @GetMapping("")
    public String catalog(){
        return "redirect:/catalog/1";
    }
    @GetMapping("/{pageNumber}")
    public String getPage(Model model, @PathVariable int pageNumber, Principal principal){
        if (principal != null) {
            model.addAttribute("user", userService.getUserByName(principal.getName()));
            model.addAttribute("isActiveStat", statusManager.ifUserMatchesStatus(
                    userService.getUserByName(principal.getName()), UserStatus.Активный)
            );
            model.addAttribute(
                    "booksByUser",
                    notesService.booksByUser(
                            ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
                    )
            );

        }
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute("currentPage",
                pageService.GetBiggerLower(pageNumber, bookService.getAllPage(pageNumber).getTotalPages())
        );
        model.addAttribute("totalItems", bookService.getAllPage(pageNumber).getTotalElements());
        model.addAttribute("totalPages", bookService.getAllPage(pageNumber).getTotalPages());
        model.addAttribute("maxPage",
                pageService.Max(pageNumber, bookService.getAllPage(pageNumber).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(pageNumber));
        model.addAttribute("toDraw", pageService.toDraw(bookService.getAllPage(pageNumber).getTotalElements()));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("books", bookService.getAllPage(pageNumber).getContent());

        return "catalog";
    }
}
