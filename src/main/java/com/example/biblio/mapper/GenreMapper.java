package com.example.biblio.mapper;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.dto.GenreDTO;
import com.example.biblio.model.Author;
import com.example.biblio.model.Genres;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {
    GenreMapper MAPPER = Mappers.getMapper(GenreMapper.class);
    Genres toGenre(Genres dto);
    @InheritInverseConfiguration
    List<GenreDTO> fromGenre(List<Genres> genres);
    List<GenreDTO> fromGenreList(List<Genres> genres);
}
