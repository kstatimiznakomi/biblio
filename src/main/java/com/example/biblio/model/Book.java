package com.example.biblio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    private static final String SEQ_NAME = "user_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String bookName;
    private String img;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String description;
    private Integer count;
    private Date publicDate;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    private Long isbn;
}
