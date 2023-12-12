package com.example.biblio.Controller;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.dto.BookParamsDTO;
import com.example.biblio.service.BookService;
import com.example.biblio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/book")
@AllArgsConstructor
@Lazy
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/{bookId}")
    public String aboutBook(Model model, @PathVariable Long bookId, Principal principal){
        if(principal != null) model.addAttribute("user", userService.getUserByName(principal.getName()));
        BookDTO book = bookService.getBookPage(bookId);
        model.addAttribute("book", book);
        return "about-book";
    }

    @GetMapping("/add")
    public String addBookPage(Model model, Principal principal){
        if(principal != null) model.addAttribute("user", userService.getUserByName(principal.getName()));
        model.addAttribute("book", new BookParamsDTO());
        return "";
    }
}
