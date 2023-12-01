package com.example.biblio.service;


import com.example.biblio.dto.GenresDTO;
import com.example.biblio.model.Genres;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GenreService {
    List<GenresDTO> getAllGenres();

    Genres getGenre(Long genreId);
}
