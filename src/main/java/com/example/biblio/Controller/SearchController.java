package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.service.AuthorService;
import com.example.biblio.service.BookService;
import com.example.biblio.service.PageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private final PageService pageService;
    private final AuthorService authorService;

    @GetMapping("")
    public String search(Model model, SearchParamsDTO dto, @RequestParam("page") int pageNumber){
        Page<Book> page = null;
        if (Objects.equals(dto.getSearchText(), "")) return "redirect:/catalog";
        if (!Objects.equals(dto.getSearchText(), "")) {
            page = bookService.getSearchBooks(pageNumber, dto.getSearchText());
        }
        if (!Objects.equals(dto.getAuthorId(), "")) {
            page = new PageImpl<>(authorService.getAuthor(dto.getAuthorId()).getBooks());
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
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "search";
    }
}
