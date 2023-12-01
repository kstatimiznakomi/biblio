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
public class JournalNotesServiceImpl implements JournalNotesService{
    private final JournalNotesDAO dao;
    private final UserService userService;
    private final ReaderTicketService ticketService;
    @Lazy
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


    public Reserve getReserve(Principal principal){
        return reserveService.getReserveWithStatusAndTicket(
                ReserveStatus.Открыт,
                ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
        );
    }

    public List<JournalNotes> getReadBooks(Principal principal){
        return dao.getJournalNotesByReaderTicketAndStatus(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                NoteStatus.Открытый
        );
    }

    public void CloseReserve(Principal principal){
        reserveService.Close(
                reserveService.getReserveWithStatusAndTicket(
                        ReserveStatus.Открыт,
                        ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
                )
        );
    }

    @Override
    public void Save(Principal principal, Long bookId){
        if (getReserve(principal) != null){
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
            if(getReadBooks(principal) == null) {
                CloseReserve(principal);
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
    public void ReturnToRead(Principal principal, Long bookId){
        if (ifExist(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        ) != null){
            dao.getJournalNotesByReaderTicketAndBook(
                    ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                    bookService.findBookByIdModel(bookId)
            ).setStatus(NoteStatus.Открытый);
            dao.save(
                    dao.getJournalNotesByReaderTicketAndBook(
                            ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                            bookService.findBookByIdModel(bookId))
            );
        }
    }

    @Override
    public void Complete(Principal principal, Long bookId){
        if (ifExist(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId)
        ) != null){
            closeAndSave(principal, bookId);
            if(getReadBooks(principal) == null) {
                CloseReserve(principal);
            }
            bookService.increaseCountOfBook(bookId, 1);
        }
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
