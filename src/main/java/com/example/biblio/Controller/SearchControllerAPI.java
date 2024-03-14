package com.example.biblio.Controller;

import com.example.biblio.dto.SearchParamsDTO;
import com.example.biblio.model.Book;
import com.example.biblio.service.SearchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchControllerAPI {
    private final SearchServiceImpl searchService;

    @PostMapping(value = "/search/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> getBooksBySearch(@RequestBody SearchParamsDTO paramsDTO){
        System.out.println(paramsDTO);
        return searchService.findAll(paramsDTO);
    }
}
