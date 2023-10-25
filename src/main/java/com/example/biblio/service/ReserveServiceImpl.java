package com.example.biblio.service;

import com.example.biblio.dao.ReserveDAO;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService{
    private final ReserveDAO reserveDAO;
    @Override
    public Reserve getReserveWithStatus(ReserveStatus status){
        return reserveDAO.getReserveByStatus(status);
    }

    @Override
    public void Save(Reserve reserve){
        reserveDAO.save(reserve);
    }

    @Override
    public void Create(){
        Reserve reserve = Reserve.builder()
                .dateTake(LocalDateTime.now())
                .dateReturn(LocalDateTime.now().plusDays(30))
                .status(ReserveStatus.Открыт)
                .build();
        Save(reserve);
    }
}
