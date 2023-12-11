package com.example.biblio.service;

import com.example.biblio.dao.ReserveDAO;
import com.example.biblio.model.ReaderTicket;
import com.example.biblio.model.Reserve;
import com.example.biblio.model.ReserveStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service

@Lazy
public class ReserveServiceImpl implements ReserveService{
    private final ReserveDAO reserveDAO;
    private final JournalNotesService notesService;
    private final ReaderTicketService ticketService;
    private final UserService userService;

    public ReserveServiceImpl(ReserveDAO reserveDAO, @Lazy JournalNotesService notesService, ReaderTicketService ticketService, UserService userService) {
        this.reserveDAO = reserveDAO;
        this.notesService = notesService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

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
    public void Close(Principal principal){
        Reserve reserve = getReserveWithStatusAndTicket(
                ReserveStatus.Открыт,
                ticketService.getTicketByUser(userService.getUserByName(principal.getName()))
        );
        reserve.setStatus(ReserveStatus.Закрыт);
        reserveDAO.save(reserve);
    }

    @Override
    public void Close(Reserve reserve){
        reserve.setStatus(ReserveStatus.Закрыт);
        reserveDAO.save(reserve);
    }

    @Override
    public void ForceClose(Reserve reserve){
        notesService.CompleteNote(notesService.getAllByTicket(reserve.getReaderTicket()));
        reserve.setStatus(ReserveStatus.Закрыт);
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
