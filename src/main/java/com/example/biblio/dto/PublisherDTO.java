package com.example.biblio.dto;

import com.example.biblio.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDTO {
    private Long id;
    private String publisherName;
    private List<Author> authors = new ArrayList<>();
}
