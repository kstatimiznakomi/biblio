package com.example.biblio.service;

import com.example.biblio.dao.GenresDAO;
import com.example.biblio.dto.GenreDTO;
import com.example.biblio.mapper.AuthorMapper;
import com.example.biblio.mapper.GenreMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreMapper mapper = GenreMapper.MAPPER;
    private final GenresDAO genresDAO;
    @Override
    public List<GenreDTO> getAllGenres() {
        return mapper.fromGenre(genresDAO.findAll());
    }
}
