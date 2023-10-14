package com.example.biblio.dao;

import com.example.biblio.model.Book;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.User;
import com.example.biblio.service.ReaderTicketService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDAO extends JpaRepository<ReaderTicket, Long> {
    public ReaderTicket getReaderTicketByUser(User user);
}
