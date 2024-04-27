package com.example.biblio.service;

import com.example.biblio.model.*;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;
public interface JournalNotesService {
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket);

    void extendJournalNote(ReaderTicket ticket, Book book);

    Boolean ifBookExistInCurrentReserve(User user, Book book);

    List<Book> getBooksUnclosedBooksByUser(User user);

    public List<JournalNotes> getAllByTicket(ReaderTicket ticket);
    List<Book> booksByUser(ReaderTicket ticket);
    void Create(Book book, User user);
    List<JournalNotes> getReadBooks(Principal principal);
    void Save(Principal principal, Long bookId);
    void CompletePrincipal(Principal principal, Long bookId);
    void CompleteNote(List<JournalNotes> notes);
    void CompleteNote(JournalNotes note);
    void bookIsUnread(JournalNotes note);
}
