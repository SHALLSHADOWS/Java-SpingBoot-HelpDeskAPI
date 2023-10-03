package com.example.HelpDesk.Repository;

import com.example.HelpDesk.Model.TicketAssign;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketAsignRepository extends JpaRepository<TicketAssign,Integer> {
}
