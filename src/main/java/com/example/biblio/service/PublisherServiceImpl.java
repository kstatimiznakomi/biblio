package com.example.biblio.service;

import com.example.biblio.dao.PublisherDAO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.mapper.PublisherMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    @Lazy
    private final PublisherMapper mapper = PublisherMapper.MAPPER;
    @Lazy
    private final PublisherDAO publisherDAO;
    @Override
    public List<PublisherDTO> getAllPublishers(){
        return mapper.fromPublisherList(publisherDAO.findAll());
    }
}
