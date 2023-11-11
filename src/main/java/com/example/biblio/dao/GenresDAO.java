package com.example.biblio.dao;

import com.example.biblio.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresDAO extends JpaRepository<Genres, Long> {
    Genres getGenresById(Long genreId);
}
