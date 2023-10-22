package com.example.biblio.service;

import com.example.biblio.dao.JournalNotesDAO;
import com.example.biblio.model.Book;
import com.example.biblio.model.JournalNotes;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class JournalNotesServiceImpl implements JournalNotesService{
    private final JournalNotesDAO dao;
    private final UserService userService;
    private final ReaderTicketService ticketService;
    private final BookService bookService;

    @Override
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket) {
        Pageable page = PageRequest.of(pageNumber - 1,5);
        return dao.getJournalNotesByReaderTicket(ticket, page);
    }

    @Override
    public void Save(Principal principal, Long bookId){
        User user = userService.getUserByName(principal.getName());
        ReaderTicket ticket = ticketService.getTicketByUser(user);
        Book book = bookService.findBookByIdModel(bookId);
        JournalNotes noteExist = dao.getJournalNotesByReaderTicketAndBook(ticket, book);
        if (noteExist == null){
            JournalNotes note = JournalNotes.builder()
                    .book(book)
                    .readerTicket(ticket)
                    .dateTake(LocalDateTime.now())
                    .dateReturn(LocalDateTime.now().plusDays(30))
                    .build();
            dao.save(note);
        }
    }

    @Override
    public void Delete(Principal principal, Long bookId) {
        User user = userService.getUserByName(principal.getName());
        ReaderTicket ticket = ticketService.getTicketByUser(user);
        Book book = bookService.findBookByIdModel(bookId);
        JournalNotes noteExist = dao.getJournalNotesByReaderTicketAndBook(ticket, book);
        if (noteExist != null){
            dao.delete(noteExist);
        }
    }
}
