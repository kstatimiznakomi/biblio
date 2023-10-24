package com.example.biblio.service;

import com.example.biblio.dto.JournalNotesDTO;
import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.ReaderTicket;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface JournalNotesService {
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket);

    List<JournalNotes> getAllNotesWithOpenStatus();

    void Save(Principal principal, Long bookId);
    void Delete(Principal principal, Long bookId);
}
