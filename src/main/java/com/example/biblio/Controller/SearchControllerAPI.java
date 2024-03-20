package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.service.BookService;
import com.example.biblio.service.SearchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SearchControllerAPI {
    private final BookService bookService;

    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> getBooksBySearch(SearchParamsDTO searchParamsDto){
        System.out.println(searchParamsDto);
        return bookService.getAllPage(searchParamsDto.getPage());
    }
}
