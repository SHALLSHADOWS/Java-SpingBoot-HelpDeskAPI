package com.example.HelpDesk.Service.Impl;

import com.example.HelpDesk.Dto.TicketDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Model.Ticket;
import com.example.HelpDesk.Repository.TicketRepository;
import com.example.HelpDesk.Service.TicketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TicketServiceImpl implements TicketService {
    private final TicketRepository  ticketRepository;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setAttachmentsPath(ticketDto.getAttachmentsPath());
        ticket.setCreate_date(ticketDto.getCreate_date());
        ticket.setCategory_id(ticketDto.getCategory_id());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setUser_id(ticketDto.getUser_id());
        ticket.setPriorityLevel(ticketDto.getPriorityLevel());
        ticket.setNumber(getNextTicketNumber());
        ticket.setSubject(ticketDto.getSubject());
        ticket.setUserEmail(ticketDto.getUserEmail());

        Ticket newTicket = ticketRepository.save(ticket);

        TicketDto ticketResponse = new TicketDto();

        ticketResponse.setId(newTicket.getId());
        ticketResponse.setNumber(newTicket.getNumber());
        ticketResponse.setSubject(newTicket.getSubject());
        ticketResponse.setPriorityLevel(newTicket.getPriorityLevel());
        ticketResponse.setUser_id(newTicket.getUser_id());
        ticketResponse.setCreate_date(newTicket.getCreate_date());
        ticketResponse.setDescription(newTicket.getDescription());
        ticketResponse.setCategory_id(newTicket.getCategory_id());
        ticketResponse.setAttachmentsPath(newTicket.getAttachmentsPath());

        return ticketResponse;



    }

    @Override
    public List<TicketDto> findAll() {
        List<Ticket> allTickets = ticketRepository.findAll();
        return allTickets.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Response<TicketDto> finById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            TicketDto ticketDto = mapToDto(optionalTicket.get());
            return new Response<>("Success", "Ticket found", ticketDto);
        }
        return new Response<>("Error", "Ticket with id " + id + " not found", null);
    }


    @Override
    public Response<TicketDto> updateTicket(TicketDto ticketDto, Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();

            // Mettez à jour les propriétés du ticket existant avec les valeurs de ticketDto

            existingTicket.setCreate_date(ticketDto.getCreate_date());
            existingTicket.setCategory_id(ticketDto.getCategory_id());
            existingTicket.setDescription(ticketDto.getDescription());
            existingTicket.setUser_id(ticketDto.getUser_id());
            existingTicket.setPriorityLevel(ticketDto.getPriorityLevel());
            existingTicket.setSubject(ticketDto.getSubject());
            existingTicket.setStatus(ticketDto.getStatus());
            // Sauvegardez la mise à jour dans le référentiel
            ticketRepository.save(existingTicket);

            return new Response<>("Success", "Ticket updated", mapToDto(existingTicket));
        }
        return new Response<>("Error", "Ticket with id " + id + " not found", null);
    }

    @Override
    public Response deleteTicket(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            // Supprimez le ticket s'il existe dans le référentiel
            ticketRepository.delete(optionalTicket.get());
            return new Response("Success", "Ticket deleted",null);
        }
        return new Response("Error", "Ticket with the id "+ id +" does not exist",null);
    }

    private TicketDto mapToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();

        ticketDto.setId(ticket.getId());
        ticketDto.setNumber(ticket.getNumber());
        ticketDto.setUser_id(ticket.getUser_id());
        ticketDto.setCreate_date(ticket.getCreate_date());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setCategory_id(ticket.getCategory_id());
        ticketDto.setPriorityLevel(ticket.getPriorityLevel());
        ticketDto.setAttachmentsPath(ticket.getAttachmentsPath());
        ticketDto.setUserEmail(ticket.getUserEmail());
        ticketDto.setSubject(ticket.getSubject());

        ticketDto.setStatus(ticket.getStatus());



        return ticketDto;
    }

    private Ticket mapToEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();

        ticket.setId(ticketDto.getId());
        ticket.setNumber(ticketDto.getNumber());
        ticket.setUser_id(ticketDto.getUser_id());
        ticket.setCreate_date(ticketDto.getCreate_date());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setCategory_id(ticketDto.getCategory_id());
        ticket.setPriorityLevel(ticketDto.getPriorityLevel());
        ticket.setAttachmentsPath(ticketDto.getAttachmentsPath());

        return ticket;
    }


    public String getNextTicketNumber() {
        List<TicketDto> allTickets = findAll();

        if (!allTickets.isEmpty()) {
            // Trouver le numéro du dernier ticket
            String lastTicketNumber = allTickets.get(allTickets.size() - 1).getNumber();

            // Extraire la partie numérique du dernier ticket
            int lastNumber = Integer.parseInt(lastTicketNumber.substring("TICKET00-".length()));

            // Incrémenter le numéro
            int nextNumber = lastNumber + 1;

            // Créer le nouveau numéro de ticket sous la forme "TICKET00-xxx"
            String nextTicketNumber = "TICKET-" + String.format("%03d", nextNumber);

            return nextTicketNumber;
        } else {
            // S'il n'y a aucun ticket, renvoyez le premier numéro
            return "TICKET00-1";
        }
    }


}
