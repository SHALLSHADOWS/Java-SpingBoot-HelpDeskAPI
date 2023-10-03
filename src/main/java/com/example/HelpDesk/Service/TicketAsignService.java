package com.example.HelpDesk.Service;

import com.example.HelpDesk.Dto.SupportDto;
import com.example.HelpDesk.Dto.TicketAssignDto;

public interface TicketAsignService {
    TicketAssignDto AssignTicketToSupport(TicketAssignDto ticketAssignDto);
}
