package com.example.biblio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "journal_notes")
public class JournalNotes {
    private static final String SEQ_NAME = "journal_notes_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ReaderTicket readerTicket;
    @OneToOne(fetch = FetchType.EAGER)
    private Book book;
    @Enumerated(EnumType.STRING)
    private NoteStatus status;
    private LocalDateTime dateTake;
    private LocalDateTime dateReturn;
}
