package com.example.biblio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "author")
public class Author {
    private static final String SEQ_NAME = "user_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String authorLastName;
    private String authorName;
    private String authorSurname;
    @OneToMany()
    private List<Book> books;
}
