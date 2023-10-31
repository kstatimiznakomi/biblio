package com.example.biblio.service;

import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReserveService {
    Reserve getReserveWithStatusAndTicket(ReserveStatus status, ReaderTicket ticket);

    void Save(Reserve reserve);

    List<Reserve> getReservesWithOpenStatus();

    void Create(ReaderTicket ticket);
}
