package com.example.biblio.service;

import com.example.biblio.dao.AuthorDAO;
import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.mapper.AuthorMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper mapper = AuthorMapper.MAPPER;
    private final AuthorDAO authorDAO;

    public AuthorServiceImpl(@Lazy AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public List<AuthorDTO> getAllAuthors(){
        return mapper.fromAuthorList(authorDAO.findAll());
    }
}
