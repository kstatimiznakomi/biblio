package com.example.biblio.service;

import com.example.biblio.dao.AuthorDAO;
import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.mapper.AuthorMapper;
import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Lazy
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper mapper = AuthorMapper.MAPPER;
    private final AuthorDAO authorDAO;

    @Override
    public List<AuthorDTO> getAllAuthors(){
        return mapper.fromAuthorList(authorDAO.findAll());
    }

    @Override
    public Author getAuthor(Long authorId) {
        return authorDAO.getAuthorById(authorId);
    }

    public void deleteAuthorById(Long authorId) {
        authorDAO.delete(authorDAO.getAuthorById(authorId));
    }

    public void save(Author author) {
        if (author.getId() != null)
            authorDAO.save(author);
    }

    public void save(AuthorDTO authorDTO) {
        Author author = Author.builder()
                .authorName(authorDTO.getAuthorName())
                .authorLastName(authorDTO.getAuthorLastName())
                .authorSurname((authorDTO.getAuthorSurname()))
                .build();
        authorDAO.save(author);
    }
}
