package com.example.biblio.service;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final BookService bookService;
    @Override
    public Page<Book> findAll(SearchParamsDTO dto) {
        Page<Book> books = bookService.getAllPage(dto.getPage());
        Field[] fields = dto.getClass().getDeclaredFields();
        //List<Long> notNulls = new ArrayList<>(Arrays.asList(Arrays.stream(fields).toList()));
        return null;
    }

    private Page<Book> byAuthor(SearchParamsDTO dto){
        return bookService.getBooksByAuthor(dto.getPage(), dto.getAuthorId());
    }
}
