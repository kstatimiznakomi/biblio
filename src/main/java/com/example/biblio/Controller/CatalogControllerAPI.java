package com.example.biblio.Controller;

import com.example.biblio.model.Book;
import com.example.biblio.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

@AllArgsConstructor
public class CatalogControllerAPI {
    @Lazy
    private final BookService bookService;
    @GetMapping("/catalog/api/{pageNumber}")
    public Page<Book> getBooks(@PathVariable int pageNumber){
        return bookService.getAllPage(pageNumber);
    }
}
