package com.example.biblio.Controller;

import com.example.biblio.model.Book;
import com.example.biblio.service.BookService;
import com.example.biblio.service.PageService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"", "/"})
@AllArgsConstructor
public class MainController {
    private final UserService userService;
    private final BookService bookService;
    private PageService pageService;
    @GetMapping("")
    public String index(){
        return "redirect:/catalog?p=1";
    }
    @GetMapping("/catalog?p={pageNumber}")
    public String getPage(Model model, @PathVariable("pageNumber") int pageNumber){
        Page<Book> page = bookService.getAllPage(pageNumber);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        int curPage = pageService.GetBiggerLower(pageNumber, totalPages);
        List<Book> books = page.getContent();
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listProducts", books);
        return "catalog";
    }
}
