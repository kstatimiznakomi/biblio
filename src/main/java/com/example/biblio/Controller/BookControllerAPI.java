package com.example.biblio.Controller;

import com.example.biblio.model.Book;
import com.example.biblio.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookControllerAPI {
    private final BookService bookService;
    @GetMapping("/catalog/book/{id}")
    public Book getBookDetails(@PathVariable Long id){
        return bookService.findBookByIdModel(id);
    }
}
