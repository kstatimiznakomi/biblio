package com.example.biblio.dao;

import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveDAO extends JpaRepository<Reserve, Long> {
    Reserve getReserveByStatus(ReserveStatus status);
    Reserve getReserveByStatusAndReaderTicket(ReserveStatus status, ReaderTicket ticket);
}
