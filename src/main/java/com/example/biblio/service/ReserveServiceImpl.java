package com.example.biblio.service;

import com.example.biblio.dao.ReserveDAO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService{
    private final ReserveDAO reserveDAO;

    @Override
    public Reserve getReserveWithStatusAndTicket(ReserveStatus status, ReaderTicket ticket){
        return reserveDAO.getReserveByStatusAndReaderTicket(status, ticket);
    }

    @Override
    public void Save(Reserve reserve){
        reserveDAO.save(reserve);
    }

    @Override
    public List<Reserve> getReservesWithOpenStatus(){
        return reserveDAO.getReservesByStatus(ReserveStatus.Открыт);
    }

    @Override
    public void Close(Reserve reserve, ReserveStatus status){
        reserve.setStatus(status);
        reserveDAO.save(reserve);
    }

    @Override
    public void Create(ReaderTicket ticket){
        Reserve reserve = Reserve.builder()
                .dateTake(LocalDateTime.now())
                .dateReturn(LocalDateTime.now().plusDays(30))
                .status(ReserveStatus.Открыт)
                .readerTicket(ticket)
                .build();
        Save(reserve);
    }
}
