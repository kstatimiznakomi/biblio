package com.example.biblio.Controller;

import com.example.biblio.dto.BookDTO;
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

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final BookService bookService;
    private final PageService pageService;

    @GetMapping("/{bookName}/{pageNumber}")
    public String search(Model model, @PathVariable String bookName, @PathVariable int pageNumber){
        Page<Book> page = bookService.getSearchBooks(pageNumber, bookName);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        int curPage = pageService.GetBiggerLower(pageNumber, totalPages);
        List<Book> books = page.getContent();
        model.addAttribute("books", books);
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        return "search";
    }
}
