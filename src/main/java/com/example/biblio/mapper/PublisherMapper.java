package com.example.biblio.mapper;

import com.example.biblio.dto.PublisherDTO;
import com.example.biblio.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PublisherMapper {
    PublisherMapper MAPPER = Mappers.getMapper(PublisherMapper.class);
    Publisher toPublisher(PublisherDTO dto);
    List<PublisherDTO> fromPublisherList(List<Publisher> publishers);
}
