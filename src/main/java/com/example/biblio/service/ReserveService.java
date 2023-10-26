package com.example.biblio.service;

import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import org.springframework.stereotype.Service;

@Service
public interface ReserveService {
    Reserve getReserveWithStatus(ReserveStatus status);

    Reserve getReserveWithStatusAndTicket(ReserveStatus status, ReaderTicket ticket);

    void Save(Reserve reserve);

    void Create(ReaderTicket ticket);
}
