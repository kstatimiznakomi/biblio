package com.example.biblio.service;

import com.example.biblio.dto.JournalNotesDTO;
import com.example.biblio.model.*;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface JournalNotesService {
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket);
    void Create(Book book, User user, Reserve reserve);
    void Save(Principal principal, Long bookId);
    void Delete(Principal principal, Long bookId);
}
