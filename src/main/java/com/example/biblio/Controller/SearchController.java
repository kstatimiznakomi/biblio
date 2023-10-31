package com.example.biblio.Controller;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.dto.UserDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.service.BookService;
import com.example.biblio.service.PageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final BookService bookService;
    private final PageService pageService;

    @GetMapping("")
    public String search(Model model, SearchParamsDTO dto, @RequestParam("page") int pageNumber){
        if (Objects.equals(dto.getSearchText(), "")) return "redirect:/catalog";
        Page<Book> page = bookService.getSearchBooks(pageNumber, dto.getSearchText());
        model.addAttribute("search", new SearchParamsDTO());
        model.addAttribute("foundBooks", page.getContent());
        model.addAttribute(
                "currentPage", pageService.GetBiggerLower(pageNumber, page.getTotalPages())
        );
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "search";
    }
}
