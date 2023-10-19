package com.example.biblio.service;

import com.example.biblio.dao.TicketDAO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ReaderTicketServiceImpl implements ReaderTicketService{
    private final TicketDAO ticketDAO;

    public ReaderTicketServiceImpl(@Lazy TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public ReaderTicket getTicketByUser(User user){
        return ticketDAO.getReaderTicketByUser(user);
    }

    @Override
    public void Save(ReaderTicket readerTicket) {
        ticketDAO.save(readerTicket);
    }
}
