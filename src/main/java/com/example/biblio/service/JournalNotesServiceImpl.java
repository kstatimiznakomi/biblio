package com.example.biblio.service;

import com.example.biblio.dao.JournalNotesDAO;
import com.example.biblio.model.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Lazy
public class JournalNotesServiceImpl implements JournalNotesService{
    @Lazy
    private final JournalNotesDAO dao;
    @Lazy
    private final UserService userService;
    private final ReaderTicketService ticketService;
    @Lazy
    private final BookService bookService;
    private final PageService pageService;

    @Override
    public Page<JournalNotes> getAllPageByTicket(int pageNumber, ReaderTicket ticket) {
        return dao.getJournalNotesByReaderTicket(
                ticket,
                pageService.getPage(pageNumber)
        );
    }

    @Override
    public void extendJournalNote(ReaderTicket ticket, Book book){
        JournalNotes note = dao.getJournalNotesByReaderTicketAndBook(ticket, book);
        note.setDateReturn(note.getDateReturn().plusMonths(1));
        dao.save(note);
    }

    @Override
    public Boolean ifBookExistInCurrentReserve(User user, Book book){
        List<JournalNotes> notes = dao.getJournalNotesByReaderTicketAndStatusLike(
                ticketService.getTicketByUser(user),
                NoteStatus.Открытый
        );
        List<Book> books = new ArrayList<>();
        for (JournalNotes note : notes){
            books.add(note.getBook());
        }
        return books.contains(book);
    }

    @Override
    public List<Book> getBooksUnclosedBooksByUser(User user){
        List<JournalNotes> notes = dao.getJournalNotesByReaderTicketAndStatusLike(
                ticketService.getTicketByUser(user),
                NoteStatus.Открытый
        );
        List<Book> books = new ArrayList<>();
        for (JournalNotes notes1 : notes){
            books.add(notes1.getBook());
        }
        return books;
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

    private void Close(JournalNotes notes){
        notes.setStatus(NoteStatus.Закрытый);
    }

    @Override
    public void Create(Book book, User user){
        JournalNotes note = JournalNotes.builder()
                .book(book)
                .dateTake(LocalDateTime.now())
                .dateReturn(LocalDateTime.now().plusMonths(1))
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
    public List<JournalNotes> getReadBooks(Principal principal){
        return dao.getJournalNotesByReaderTicketAndStatusLike(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                NoteStatus.Открытый
        );
    }

    @Override
    public void Save(Principal principal, Long bookId){
        if (!ifBookExistInCurrentReserve(
                userService.getUserByName(principal.getName()),
                bookService.findBookByIdModel(bookId)
        )) {
            Create(
                    bookService.findBookByIdModel(bookId),
                    userService.getUserByName(principal.getName())
            );
            bookService.decreaseCountOfBook(bookId, 1);
        }
    }

    @Override
    public void CompletePrincipal(Principal principal, Long[] bookIds){
        for (Long item : bookIds){
            if (ifBookExistInCurrentReserve(
                    userService.getUserByName(principal.getName()),
                    bookService.findBookByIdModel(item)
            )){
                closeAndSave(principal, item);
                bookService.increaseCountOfBook(item, 1);
            }
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

    public void closeAndSave(Principal principal, Long bookId) {
        JournalNotes note = dao.getJournalNotesByReaderTicketAndBookAndStatus(
                ticketService.getTicketByUser(userService.getUserByName(principal.getName())),
                bookService.findBookByIdModel(bookId),
                NoteStatus.Открытый
        );
        note.setStatus(NoteStatus.Закрытый);
        dao.save(note);
    }
}
