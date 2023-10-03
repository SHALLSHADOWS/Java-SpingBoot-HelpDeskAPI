package com.example.HelpDesk.Controller;

import com.example.HelpDesk.Dto.SupportDto;
import com.example.HelpDesk.Dto.TicketAssignDto;
import com.example.HelpDesk.Dto.TicketDto;
import com.example.HelpDesk.Service.SupportService;
import com.example.HelpDesk.Service.TicketAsignService;
import com.example.HelpDesk.Service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/TicketAssign")
public class TicketAssignController
{
    private final TicketAsignService ticketAsignService;
    private final TicketService ticketService;
    private final SupportService supportService;

    @PostMapping("/assign-ticket")
    public ResponseEntity<TicketAssignDto> assignTicketToSupport(@RequestBody TicketAssignDto ticketAssignDto) {
        SupportDto support = supportService.finById(ticketAssignDto.getSupportId()).getData();
        TicketDto ticketDto =ticketService.finById(ticketAssignDto.getTicketId()).getData();

        TicketAssignDto newTicketAssignDto = ticketAsignService.AssignTicketToSupport(ticketAssignDto);

        if (newTicketAssignDto != null) {
            ticketDto.setStatus("Assign");

            ticketService.updateTicket(ticketDto,ticketDto.getId());
            return new ResponseEntity<>(newTicketAssignDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
