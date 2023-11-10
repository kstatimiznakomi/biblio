package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.PageType;
import com.example.biblio.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final BookService bookService;
    private final PageService pageService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;

    @GetMapping("")
    public String search(Model model, SearchParamsDTO dto, @RequestParam("page") int pageNumber){
        Page<Book> page = null;
        if (Objects.equals(dto.getSearchText(), "")) return "redirect:/catalog";
        if (!Objects.equals(dto.getSearchText(), "")) {
            page = bookService.getSearchBooks(pageNumber, dto.getSearchText());
        }
        if (!Objects.equals(dto.getAuthorId(), "")) {
            page = new PageImpl<>(authorService.getAuthor(Long.getLong(dto.getAuthorId())).getBooks());
        }

        model.addAttribute("search", new SearchParamsDTO());
        assert page != null;
        model.addAttribute("foundBooks", page.getContent());
        model.addAttribute("author", dto.getAuthorId());
        model.addAttribute("genre", dto.getGenreId());
        model.addAttribute("publisher", dto.getPublisherId());
        model.addAttribute("searchText", dto.getSearchText());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(pageNumber, page.getTotalPages())
        );
        model.addAttribute("nameType", PageType.Name.toString());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "search";
    }
    @GetMapping("/date/{date}/{pageNum}")
    public String allByDate(@PathVariable Integer date, Model model, @PathVariable int pageNum){
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(
                        pageNum,
                        bookService.getBooksByDate(pageNum, date).getTotalPages())
        );
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("date", date);
        model.addAttribute("nameType", PageType.Date.toString());
        model.addAttribute("foundBooks", bookService.getBooksByDate(pageNum, date).getContent());
        model.addAttribute("totalItems", bookService.getBooksByDate(pageNum, date).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByDate(pageNum, date).getTotalPages());
        return "search";
    }

    @GetMapping("/author/{authorId}/{pageNum}")
    public String allByAuthor(@PathVariable Long authorId, @PathVariable int pageNum, Model model){
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
        model.addAttribute("foundBooks", bookService.getBooksByAuthor(pageNum, authorId).getContent());
        model.addAttribute("totalItems", bookService.getBooksByAuthor(pageNum, authorId).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByAuthor(pageNum, authorId).getTotalPages());
        return "search";
    }

    @GetMapping("/publisher/{publisherId}/{pageNum}")
    public String allByPublisher(@PathVariable Long publisherId, @PathVariable int pageNum, Model model){
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
        model.addAttribute("author", publisherId);
        model.addAttribute("foundBooks", bookService.getBooksByPublisher(pageNum, publisherId).getContent());
        model.addAttribute("totalItems", bookService.getBooksByPublisher(pageNum, publisherId).getTotalElements());
        model.addAttribute("totalPages", bookService.getBooksByPublisher(pageNum, publisherId).getTotalPages());
        return "search";
    }
}
