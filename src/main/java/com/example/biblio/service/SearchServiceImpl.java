package com.example.biblio.service;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final BookService bookService;

    private Page<Book> byAuthor(SearchParamsDTO dto){
        return bookService.getBooksByAuthor(dto.getPage(), dto.getAuthorId());
    }
    private Page<Book> byGenre(SearchParamsDTO dto){
        return bookService.getBooksByGenre(dto.getPage(), dto.getAuthorId());
    }
    private Page<Book> byPublisher(SearchParamsDTO dto){
        return bookService.getBooksByPublisher(dto.getPage(), dto.getAuthorId());
    }
}
