package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.PageType;
import com.example.biblio.model.UserStatus;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
@Lazy
public class SearchController {
    private final BookService bookService;
    private final PageService pageService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final StatusManager statusManager;
    private final UserService userService;
    private final SearchServiceImpl searchService;

    @ModelAttribute("SearchParamsDTO")
    public SearchParamsDTO params(){
        return new SearchParamsDTO();
    }

    @GetMapping("")
    public String search(Principal principal, Model model, @ModelAttribute SearchParamsDTO dto, @RequestParam("page") int pageNumber){
        if (Objects.equals(dto.getSearchText(), "")) return "redirect:/catalog";
        Page<Book> page = searchService.searchByCriteria(dto);
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute("foundBooks", page.getContent());
        model.addAttribute("author", dto.getAuthorId());
        model.addAttribute("genre", dto.getGenreId());
        model.addAttribute("publisher", dto.getPublisherId());
        model.addAttribute("searchText", dto.getSearchText());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(pageNumber, page.getTotalPages())
        );
        model.addAttribute("nameType", PageType.Name.toString());
        model.addAttribute("maxPage",
                pageService.Max(pageNumber, bookService.getAllPage(pageNumber).getTotalPages())
        );
        model.addAttribute("isActiveStat", statusManager.ifUserMatchesStatus(
                userService.getUserByName(principal.getName()), UserStatus.Активный)
        );
        model.addAttribute("maxPage",
                pageService.Max(pageNumber, bookService.getAllPage(pageNumber).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(pageNumber));
        model.addAttribute("ifUserSigned", userService.ifUserSigned(principal));
        model.addAttribute("toDraw", pageService.toDraw(bookService.getSearchBooks(pageNumber, dto.getSearchText()).getTotalElements()));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "search";
    }

    /*@GetMapping("/date/{date}/{pageNum}")
    public String allByDate(@PathVariable Integer date, Model model){
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(
                        pageNum,
                        bookService.getBooksByDate(pageNum, date).getTotalPages())
        );
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("maxPage",
                pageService.Max(pageNum, bookService.getBooksByDate(pageNum, date).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(pageNum));
        model.addAttribute("toDraw", pageService.toDraw(bookService.getBooksByDate(pageNum, date).getTotalElements()));
        model.addAttribute("date", date);
        model.addAttribute("nameType", PageType.Date.toString());
        model.addAttribute("foundBooks", bookService.getBooksByDate(pageNum, date).getContent());
        model.addAttribute("totalItems", bookService.getBooksByDate(pageNum, date).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByDate(pageNum, date).getTotalPages());
        return "search";
    }

    @GetMapping("/author/{authorId}/{pageNum}")
    public String allByAuthor(@PathVariable Long authorId, @PathVariable int pageNum, Model model){
        if (Objects.equals(authorId, 0L)) return "redirect:/catalog";
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(
                        pageNum,
                        bookService.getBooksByAuthor(pageNum, authorId).getTotalPages())
        );
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("nameType", PageType.Author.toString());
        model.addAttribute("author", authorId);
        model.addAttribute("maxPage",
                pageService.Max(pageNum, bookService.getBooksByAuthor(pageNum, authorId).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(pageNum));
        model.addAttribute("toDraw", pageService.toDraw(bookService.getBooksByAuthor(pageNum, authorId).getTotalElements()));
        model.addAttribute("foundBooks", bookService.getBooksByAuthor(pageNum, authorId).getContent());
        model.addAttribute("totalItems", bookService.getBooksByAuthor(pageNum, authorId).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByAuthor(pageNum, authorId).getTotalPages());
        return "search";
    }

    @GetMapping("/publisher/{publisherId}/{pageNum}")
    public String allByPublisher(@PathVariable Long publisherId, @PathVariable int pageNum, Model model){
        if (Objects.equals(publisherId, 0L)) return "redirect:/catalog";
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(
                        pageNum,
                        bookService.getBooksByPublisher(pageNum, publisherId).getTotalPages())
        );
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("nameType", PageType.Author.toString());
        model.addAttribute("publisher", publisherId);
        model.addAttribute("maxPage",
                pageService.Max(pageNum, bookService.getBooksByPublisher(pageNum, publisherId).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(pageNum));
        model.addAttribute("toDraw", pageService.toDraw(bookService.getBooksByPublisher(pageNum, publisherId).getTotalElements()));
        model.addAttribute("foundBooks", bookService.getBooksByPublisher(pageNum, publisherId).getContent());
        model.addAttribute("totalItems", bookService.getBooksByPublisher(pageNum, publisherId).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByPublisher(pageNum, publisherId).getTotalPages());
        return "search";
    }

    @GetMapping("/genre/{genreId}/{pageNum}")
    public String allByGenre(@PathVariable Long genreId, @PathVariable int pageNum, Model model){
        if (Objects.equals(genreId, 0L)) return "redirect:/catalog";
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(
                        pageNum,
                        bookService.getBooksByGenre(pageNum, genreId).getTotalPages())
        );
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("nameType", PageType.Genre.toString());
        model.addAttribute("genre", genreId);
        model.addAttribute("maxPage",
                pageService.Max(pageNum, bookService.getBooksByGenre(pageNum, genreId).getTotalPages())
        );
        model.addAttribute("minPage", pageService.Min(pageNum));
        model.addAttribute("toDraw", pageService.toDraw(bookService.getBooksByGenre(pageNum, genreId).getTotalElements()));
        model.addAttribute("foundBooks", bookService.getBooksByGenre(pageNum, genreId).getContent());
        model.addAttribute("totalItems", bookService.getBooksByGenre(pageNum, genreId).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByGenre(pageNum, genreId).getTotalPages());
        return "search";
    }*/
}
