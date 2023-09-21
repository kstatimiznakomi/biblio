package com.example.biblio.service;

import com.example.biblio.model.Book;
import com.example.biblio.model.User;

import java.util.List;

public interface ReaderTicketService {
    void SetBooksToTicket(User user, List<Book> books);
}
