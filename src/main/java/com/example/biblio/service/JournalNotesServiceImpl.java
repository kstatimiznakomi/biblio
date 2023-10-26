package com.example.biblio.service;

import com.example.biblio.dao.JournalNotesDAO;
import com.example.biblio.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    public void Create(Book book, User user){
        JournalNotes note = JournalNotes.builder()
                .book(book)
                .readerTicket(
                        ticketService.getTicketByUser(user)
                )
                .status(NoteStatus.Открытый)
                .build();
        dao.save(note);
    }

    public JournalNotes ifExist(ReaderTicket ticket, Book book){
        return dao.getJournalNotesByReaderTicketAndBook(ticket, book);
    }

    @Override
    public void Save(Principal principal, Long bookId){
        if (reserveService.getReserveWithStatusAndTicket(
                ReserveStatus.Открыт,
                ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
        ) != null){
            JournalNotes noteExist = dao.getJournalNotesByReaderTicketAndBook(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                    bookService.findBookByIdModel(bookId)
            );
            if (noteExist == null){
                Create(
                        bookService.findBookByIdModel(bookId),
                        userService.getUserByName(principal.getName())
                );
            }
        }
        else {
            reserveService.Create(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
            );
            Create(
                    bookService.findBookByIdModel(bookId),
                    userService.getUserByName(principal.getName())
            );
        }
    }

    @Override
    public void Delete(Principal principal, Long bookId) {
        if (ifExist(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        ) != null){
            dao.delete(ifExist(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                    bookService.findBookByIdModel(bookId)
            ));
        }
    }
}
