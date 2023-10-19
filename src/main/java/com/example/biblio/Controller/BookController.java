package com.example.biblio.Controller;

import com.example.biblio.dto.BookDTO;
import com.example.biblio.service.BookService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/{bookId}")
public class BookController {
    private final BookService bookService;

    public BookController(@Lazy BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String aboutBook(Model model, @PathVariable Long bookId){
        BookDTO book = bookService.findBookById(bookId);
        model.addAttribute("bookName", book);
        return "about-book";
    }
}
