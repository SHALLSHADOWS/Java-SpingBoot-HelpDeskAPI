package com.example.HelpDesk.Service.Impl;

import com.example.HelpDesk.Dto.SupportDto;
import com.example.HelpDesk.Dto.TicketAssignDto;
import com.example.HelpDesk.Model.Support;
import com.example.HelpDesk.Model.TicketAssign;
import com.example.HelpDesk.Repository.SupportRepository;
import com.example.HelpDesk.Repository.TicketAsignRepository;
import com.example.HelpDesk.Service.TicketAsignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketAsignServiceImpl implements TicketAsignService {
    private final TicketAsignRepository ticketAsignRepository;
    @Override
    public TicketAssignDto AssignTicketToSupport(TicketAssignDto ticketAssignDto) {
        TicketAssign ticketAssign = new TicketAssign();
        ticketAssign.setTicketId(ticketAssignDto.getTicketId());
        ticketAssign.setSupportId(ticketAssignDto.getSupportId());

        TicketAssignDto NewTicketAssignDto = mapToDto(ticketAsignRepository.save(ticketAssign));
        return NewTicketAssignDto;
    }
    private TicketAssignDto mapToDto(TicketAssign ticketAssign) {
        TicketAssignDto ticketAssignDto = new TicketAssignDto();
        ticketAssignDto.setId(ticketAssign.getId());
        ticketAssignDto.setSupportId(ticketAssign.getSupportId());
        ticketAssignDto.setTicketId(ticketAssign.getTicketId());
        return ticketAssignDto;
    }

}
