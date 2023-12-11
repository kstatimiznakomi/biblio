package com.example.biblio.dao;

import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.NoteStatus;
import com.example.biblio.model.ReaderTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalNotesDAO extends JpaRepository<JournalNotes, Long>, PagingAndSortingRepository<JournalNotes, Long> {
    Page<JournalNotes> getJournalNotesByReaderTicket(ReaderTicket ticket, Pageable page);
    JournalNotes getJournalNotesByReaderTicketAndBook(ReaderTicket ticket, Book book);
    List<JournalNotes> getJournalNotesByReaderTicket(ReaderTicket ticket);
    List<JournalNotes> getJournalNotesByReaderTicketAndStatus(ReaderTicket ticket, NoteStatus status);
}
