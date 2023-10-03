package com.example.HelpDesk.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class TicketAssignDto {

    private Long id;
    private int supportId;
    private Long ticketId;
}
