package com.example.HelpDesk.Repository;

import com.example.HelpDesk.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
