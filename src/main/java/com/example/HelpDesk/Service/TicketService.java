package com.example.HelpDesk.Service;

import com.example.HelpDesk.Dto.TicketDto;
import com.example.HelpDesk.Model.Response;

import java.util.List;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto);

    List<TicketDto> findAll();

    Response<TicketDto> finById(Long id);

    Response<TicketDto> updateTicket(TicketDto ticketDto, Long id);

    Response deleteTicket(Long id);
}
