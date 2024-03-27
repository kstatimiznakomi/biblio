package com.example.biblio.dto;

import com.example.biblio.model.Author;
import com.example.biblio.model.Book;
import com.example.biblio.model.Publisher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String bookName;
    private String description;
    private String img;
    private Integer count;
    private String isbn;
    private Integer publicDate;
    private List<Author> authors;
}
