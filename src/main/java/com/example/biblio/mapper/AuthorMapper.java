package com.example.biblio.mapper;

import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.model.Author;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper MAPPER = Mappers.getMapper(AuthorMapper.class);
    Author toAuthor(Author dto);
    @InheritInverseConfiguration
    List<AuthorDTO> fromAuthor(List<Author> author);
    List<AuthorDTO> fromAuthorList(List<Author> authors);
}
