package com.example.biblio.service;

import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    List<PublisherDTO> getAllPublishers();

    Publisher getBookByPublisher(Long publisherId);
}
