package com.example.biblio.service;

import com.example.biblio.dto.PublisherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    List<PublisherDTO> getAllPublishers();
    void deletePublisherById(Long publisherId);
}
