package com.example.biblio.Controller.admin;

import com.example.biblio.model.Genres;
import com.example.biblio.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminGenreAPIController {
    @Lazy
    private final GenreService genreService;

    @GetMapping("/admin/genres/api/{pageNumber}")
    @CrossOrigin
    public Page<Genres> getBooks(@PathVariable int pageNumber){
        return genreService.getAllPage(pageNumber);
    }
}
