package com.example.biblio.service;

import com.example.biblio.model.Reserve;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class DateOfTermCheckImpl implements DateOfTermCheck{
    private final ReserveService reserveService;
    private final UserService userService;

    @Override
    public void checkForBlock() {
        for (Reserve reserve : reserveService.getReservesWithOpenStatus()){
            if (Objects.equals(reserve.getDateReturn(), LocalDateTime.now()) ||
                    LocalDateTime.now().getDayOfMonth() < reserve.getDateReturn().getDayOfMonth()){
                userService.block(reserve.getReaderTicket().getUser());
            }
        }
    }
}
