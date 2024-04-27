package com.example.biblio.service;

import com.example.biblio.dao.GenresDAO;
import com.example.biblio.dto.GenresDTO;
import com.example.biblio.mapper.GenreMapper;
import com.example.biblio.model.Genres;
import lombok.AllArgsConstructor;
import org.apache.catalina.users.GenericUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    @Lazy
    private final GenreMapper mapper = GenreMapper.MAPPER;
    @Lazy
    private final GenresDAO genresDAO;

    @Override
    public List<GenresDTO> getAllGenres() {
        return mapper.fromGenreList(genresDAO.findAll());
    }

    @Override
    public Genres getGenre(Long genreId){
        return genresDAO.getGenresById(genreId);
    }


    public void deleteGenreById(Long genreId) { genresDAO.delete(genresDAO.getGenresById(genreId)); }
    public void save(Genres genres) {
        if(genres.getId() != null)
            genresDAO.save(genres);
    }
    public void save(GenresDTO genresDTO) {
        Genres genre = Genres.builder()
                .genreName(genresDTO.getGenreName())
                .build();
        genresDAO.save(genre);
    }
}
