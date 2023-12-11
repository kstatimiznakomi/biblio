package com.example.biblio.service;

import com.example.biblio.dao.PublisherDAO;
import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.mapper.PublisherMapper;
import com.example.biblio.model.Publisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherMapper mapper = PublisherMapper.MAPPER;
    private final PublisherDAO publisherDAO;

    public void deletePublisherById(Long publisherId) {
        publisherDAO.delete(publisherDAO.getPublisherById(publisherId));
    }

    public void save(Publisher publisher) {
        if (publisher.getId() != null)
            publisherDAO.save(publisher);
    }

    @Override
    public List<PublisherDTO> getAllPublishers(){
        return mapper.fromPublisherList(publisherDAO.findAll());
    }

    @Override
    public Publisher getBookByPublisher(Long publisherId){
        return publisherDAO.getPublisherById(publisherId);
    }
}
