package com.example.biblio.service;

import com.example.biblio.model.Reserve;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;



@AllArgsConstructor
public class DateOfTermCheckImpl implements DateOfTermCheck{
    private final ReserveService reserveService;

    @Override
    public void checkForBlock() {
        for (Reserve reserve : reserveService.getReservesWithOpenStatus()){
            if (Objects.equals(reserve.getDateReturn(), LocalDateTime.now()) ||
                    LocalDateTime.now().getDayOfMonth() < reserve.getDateReturn().getDayOfMonth()){
                reserveService.Close(reserve);
            }
        }
    }
}
