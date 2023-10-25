package com.example.biblio.service;

import com.example.biblio.dao.JournalNotesDAO;
import com.example.biblio.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class JournalNotesServiceImpl implements JournalNotesService{
    private final JournalNotesDAO dao;
    private final UserService userService;
    private final ReaderTicketService ticketService;
    private final BookService bookService;
    private final ReserveService reserveService;

    @Override
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket) {
        Pageable page = PageRequest.of(pageNumber - 1,10);
        return dao.getJournalNotesByReaderTicket(ticket, page);
    }

    @Override
    public List<JournalNotes> getAllNotesWithOpenStatus(){
        return dao.getJournalNotesByStatus(NoteStatus.Открытый);
    }

    @Override
    public void Save(Principal principal, Long bookId){
        if (reserveService.getReserveWithStatus(ReserveStatus.Открыт) != null){
            JournalNotes noteExist = dao.getJournalNotesByReaderTicketAndBook(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                    bookService.findBookByIdModel(bookId)
            );
            if (noteExist == null){
                JournalNotes note = JournalNotes.builder()
                        .book(bookService.findBookByIdModel(bookId))
                        .readerTicket(
                                ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
                        )
                        .status(NoteStatus.Открытый)
                        .reserve(reserveService.getReserveWithStatus(ReserveStatus.Открыт))
                        .build();
                dao.save(note);
            }
        }
        else {
            reserveService.Create();
            JournalNotes note = JournalNotes.builder()
                    .book(bookService.findBookByIdModel(bookId))
                    .readerTicket(
                            ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
                    )
                    .reserve(reserveService.getReserveWithStatus(ReserveStatus.Открыт))
                    .build();
            dao.save(note);
        }
    }

    @Override
    public void Delete(Principal principal, Long bookId) {
        JournalNotes noteExist = dao.getJournalNotesByReaderTicketAndBook(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        );
        if (noteExist != null){
            dao.delete(noteExist);
        }
    }
}
