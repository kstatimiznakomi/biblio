package com.example.biblio.service;


import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenres();
}
