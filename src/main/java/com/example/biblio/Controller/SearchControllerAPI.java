package com.example.biblio.Controller;

import com.example.biblio.service.SearchServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchControllerAPI {
    private final SearchServiceImpl searchService;


}
