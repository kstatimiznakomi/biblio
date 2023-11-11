package com.example.biblio.service;


import com.example.biblio.dto.GenresDTO;
import com.example.biblio.model.Genres;

import java.util.List;

public interface GenreService {
    List<GenresDTO> getAllGenres();

    Genres getGenre(Long genreId);
}
