package com.example.biblio.service;


import com.example.biblio.dto.GenresDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.Genres;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GenreService {
    List<GenresDTO> getAllGenres();

    Genres getGenre(Long genreId);

    void deleteGenreById(Long genreId);
    void save(Genres genres);
    void save(GenresDTO genresDTO);

    Page<Genres> getAllPage(int pageNumber);
}
