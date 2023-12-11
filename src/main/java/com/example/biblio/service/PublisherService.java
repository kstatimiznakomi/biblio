package com.example.biblio.service;

import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    List<PublisherDTO> getAllPublishers();

    void deletePublisherById(Long publisherId);

    void save(Publisher publisher);

    Publisher getBookByPublisher(Long publisherId);
}
