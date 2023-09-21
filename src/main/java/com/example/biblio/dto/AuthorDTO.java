package com.example.biblio.dto;

import com.example.biblio.model.Book;
import com.example.biblio.model.Publisher;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
    private String authorName;
    private List<Book> books;
    private List<Publisher> publishers = new ArrayList<>();
}
