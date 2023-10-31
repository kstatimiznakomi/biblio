package com.example.biblio.Controller;

import com.example.biblio.dto.*;
import com.example.biblio.model.Book;
import com.example.biblio.model.User;
import com.example.biblio.model.UserRole;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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
        Page<Book> page = bookService.getAllPage(pageNumber);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        int curPage = pageService.GetBiggerLower(pageNumber, totalPages);
        if (principal != null) {
            User user = userService.getUserByName(principal.getName());
            model.addAttribute("user", user);
            model.addAttribute("userStatus", rusToEngStatus.rusToEng(user));
            model.addAttribute("activeStat", UserStatus.Active);
        }
        List<Book> books = page.getContent();
        List<AuthorDTO> authors = authorService.getAllAuthors();
        List<GenresDTO> genres = genreService.getAllGenres();
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        model.addAttribute("search", new SearchParamsDTO());
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
