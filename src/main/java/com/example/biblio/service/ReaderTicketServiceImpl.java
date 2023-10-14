package com.example.biblio.service;

import com.example.biblio.dao.TicketDAO;
import com.example.biblio.model.Book;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderTicketServiceImpl implements ReaderTicketService{
    @Lazy
    private final TicketDAO ticketDAO;
    @Override
    public void SetBooksToTicket(User user, List<Book> books) {
        /*User foundUser = userService.GetUserByUserName(user.getUsername());
        ReaderTicket ticket = ticketDAO.getReaderTicketByUser(foundUser);
        ticketDAO.save(ticket);*/
    }

    @Override
    public void Save(ReaderTicket readerTicket) {
        ticketDAO.save(readerTicket);
    }
}
