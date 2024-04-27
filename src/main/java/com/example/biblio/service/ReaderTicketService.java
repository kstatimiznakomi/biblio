package com.example.biblio.service;

import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import org.springframework.stereotype.Service;

public interface ReaderTicketService {
    ReaderTicket getTicketByUser(User user);

    void Create(User user);
}
