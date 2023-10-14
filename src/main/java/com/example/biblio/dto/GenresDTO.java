package com.example.biblio.dto;

import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenresDTO {
    private Long id;
    private String genreName;
    private List<Book> books;
}
