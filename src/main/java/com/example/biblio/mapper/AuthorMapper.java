package com.example.biblio.mapper;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper MAPPER = Mappers.getMapper(AuthorMapper.class);
    Author toAuthor(Author dto);
    List<AuthorDTO> fromAuthorList(List<Author> author);
}
