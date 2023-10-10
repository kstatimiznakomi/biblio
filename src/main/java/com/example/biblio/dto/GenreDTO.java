package com.example.biblio.dto;

import com.example.biblio.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {
    private Long id;
    private String name;
    private List<Book> books;
}
