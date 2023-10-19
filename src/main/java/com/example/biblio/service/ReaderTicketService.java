package com.example.biblio.service;

import com.example.biblio.model.Book;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;

import java.util.List;

public interface ReaderTicketService {
    ReaderTicket getTicketByUser(User user);
    void Save(ReaderTicket readerTicket);
}
