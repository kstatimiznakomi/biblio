package com.example.biblio.mapper;

import com.example.biblio.dto.GenresDTO;
import com.example.biblio.model.Genres;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {
    GenreMapper MAPPER = Mappers.getMapper(GenreMapper.class);
    Genres toGenre(GenresDTO dto);
    List<GenresDTO> fromGenre(List<Genres> genres);
}
