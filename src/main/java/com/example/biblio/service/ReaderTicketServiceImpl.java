package com.example.biblio.service;

import com.example.biblio.dao.TicketDAO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReaderTicketServiceImpl implements ReaderTicketService{
    private final TicketDAO ticketDAO;

    @Override
    public ReaderTicket getTicketByUser(User user){
        return ticketDAO.getReaderTicketByUser(user);
    }

    @Override
    public void Save(ReaderTicket readerTicket) {
        ticketDAO.save(readerTicket);
    }
}
