package com.example.biblio.service;

import com.example.biblio.dao.PublisherDAO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.mapper.PublisherMapper;
import com.example.biblio.model.Publisher;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherMapper mapper = PublisherMapper.MAPPER;
    private final PublisherDAO publisherDAO;

    @Override
    public List<PublisherDTO> getAllPublishers(){
        return mapper.fromPublisherList(publisherDAO.findAll());
    }

    public void deletePublisherById(Long publisherId)
    {
        Publisher publisher = mapper.toPublisherById(publisherId);
        publisherDAO.delete(publisher);
    }
}
