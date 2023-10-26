package com.example.biblio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

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
    private ReaderTicket readerTicket;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reserve reserve;
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @Enumerated(EnumType.STRING)
    private NoteStatus status;
}
