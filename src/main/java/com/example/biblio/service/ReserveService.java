package com.example.biblio.service;

import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

public interface ReserveService {
    Reserve getReserveWithStatusAndTicket(ReserveStatus status, ReaderTicket ticket);

    void Save(Reserve reserve);

    List<Reserve> getReservesWithOpenStatus();

    void Close(Principal principal);

    void Close(Reserve reserve);

    void ForceClose(Reserve reserve);

    void Create(ReaderTicket ticket);
}
