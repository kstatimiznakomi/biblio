package com.example.biblio.service;

import com.example.biblio.model.*;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;
public interface JournalNotesService {
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket);
    public List<JournalNotes> getAllByTicket(ReaderTicket ticket);

    List<Book> booksByUser(ReaderTicket ticket);

    void Create(Book book, User user, Reserve reserve);


    void Save(Principal principal, Long bookId);
    void ReturnToRead(Principal principal, Long bookId);
    void Complete(Principal principal, Long bookId);
    void Delete(Principal principal, Long bookId);
}
