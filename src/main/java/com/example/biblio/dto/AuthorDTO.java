package com.example.biblio.dto;

import com.example.biblio.model.Book;
import com.example.biblio.model.Publisher;
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
public class AuthorDTO {
    private Long id;
    private String authorLastName;
    private String authorName;
    private String authorSurname;
    private List<Book> books;
    private List<Publisher> publishers = new ArrayList<>();
}
