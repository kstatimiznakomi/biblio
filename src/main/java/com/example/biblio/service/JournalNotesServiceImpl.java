package com.example.biblio.service;

import com.example.biblio.dao.JournalNotesDAO;
import com.example.biblio.model.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Lazy
public class JournalNotesServiceImpl implements JournalNotesService{
    private final JournalNotesDAO dao;
    private final UserService userService;
    private final ReaderTicketService ticketService;
    private final BookService bookService;
    private final ReserveService reserveService;
    private final PageService pageService;

    @Override
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket) {
        return dao.getJournalNotesByReaderTicket(
                ticket,
                pageService.getPage(pageNumber)
        );
    }

    @Override
    public List<JournalNotes> getAllByTicket(ReaderTicket ticket) {
        return dao.getJournalNotesByReaderTicket(ticket);
    }

    @Override
    public List<Book> booksByUser(ReaderTicket ticket){
        List<Book> books = new ArrayList<>();
        List<JournalNotes> notes = getAllByTicket(ticket);
        for (JournalNotes notes1 : notes){
            books.add(notes1.getBook());
        }
        return books;
    }

    @Override
    public void Create(Book book, User user, Reserve reserve){
        JournalNotes note = JournalNotes.builder()
                .book(book)
                .readerTicket(
                        ticketService.getTicketByUser(user)
                )
                .reserve(reserve)
                .status(NoteStatus.Открытый)
                .build();
        dao.save(note);
    }

    public JournalNotes ifExist(ReaderTicket ticket, Book book){
        return dao.getJournalNotesByReaderTicketAndBook(ticket, book);
    }

    @Override
    public List<JournalNotes> getReadBooks(Principal principal){
        return dao.getJournalNotesByReaderTicketAndStatusLike(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                NoteStatus.Открытый
        );
    }

    @Override
    public void Save(Principal principal, Long bookId){
        if (reserveService.getReserveWithStatusAndTicket(
                ReserveStatus.Открыт,
                ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
        ) != null){
            if (ifExist(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                    bookService.findBookByIdModel(bookId)
            ) == null){
                Create(
                        bookService.findBookByIdModel(bookId),
                        userService.getUserByName(principal.getName()),
                        reserveService.getReserveWithStatusAndTicket(
                                ReserveStatus.Открыт,
                                ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
                        )
                );
                bookService.decreaseCountOfBook(bookId, 1);
            }
        }
        else {
            reserveService.Create(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
            );
            Create(
                    bookService.findBookByIdModel(bookId),
                    userService.getUserByName(principal.getName()),
                    reserveService.getReserveWithStatusAndTicket(
                            ReserveStatus.Открыт,
                            ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
                    )
            );
            bookService.decreaseCountOfBook(bookId, 1);
        }
    }

    @Override
    public void CompletePrincipal(Principal principal, Long bookId){
        if (ifExist(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        ) != null){
            closeAndSave(principal, bookId);
            bookService.increaseCountOfBook(bookId, 1);
        }
    }

    @Override
    public void CompleteNote(List<JournalNotes> notes){
        for (JournalNotes noteIn : notes){
            noteIn.setStatus(NoteStatus.Закрытый);

        }
    }

    @Override
    public void CompleteNote(JournalNotes note){
        note.setStatus(NoteStatus.Закрытый);
    }

    @Override
    public void bookIsUnread(JournalNotes note){
        note.setStatus(NoteStatus.Недочитанный);
        dao.save(note);
    }

    public void closeAndSave(Principal principal, Long bookId){
        dao.getJournalNotesByReaderTicketAndBook(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        ).setStatus(NoteStatus.Закрытый);
        dao.save(
                dao.getJournalNotesByReaderTicketAndBook(
                        ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                        bookService.findBookByIdModel(bookId))
        );
        if(getReadBooks(principal) == null) {
            reserveService.Close(principal);
        }
    }

    @Override
    public void UnreadByPrincipal(Principal principal, Long bookId) {
        if (ifExist(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        ) != null){
            bookIsUnread(ifExist(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                    bookService.findBookByIdModel(bookId)));
        }
        if(getReadBooks(principal) == null) {
            reserveService.Close(principal);
        }
    }
}
