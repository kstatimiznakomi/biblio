package com.example.biblio.service;

import com.example.biblio.dao.AuthorDAO;
import com.example.biblio.dto.AuthorDTO;
import com.example.biblio.mapper.AuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorMapper mapper = AuthorMapper.MAPPER;
    private final AuthorDAO authorDAO;

    @Override
    public List<AuthorDTO> getAllAuthors(){
        return mapper.fromAuthorList(authorDAO.findAll());
    }
}
