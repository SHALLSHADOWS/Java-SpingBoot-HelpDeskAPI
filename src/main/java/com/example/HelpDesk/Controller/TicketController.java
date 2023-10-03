package com.example.HelpDesk.Controller;

import com.example.HelpDesk.Dto.TicketDto;
import com.example.HelpDesk.Dto.UserDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Service.TicketService;
import com.example.HelpDesk.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";


    @PostMapping("tickets/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketCreateDto) {

        ticketCreateDto.setUser_id(Long.valueOf("3"));


        TicketDto ticketCreate = ticketService.createTicket(ticketCreateDto);

        return new ResponseEntity<>(ticketCreate, HttpStatus.CREATED);
    }
    @PostMapping("ticketsAttachments/upload")
    public List<String> upload(@RequestParam("attachments") List<MultipartFile> attachments) {
        List<String> fileLinks = new ArrayList<>();
        for (MultipartFile attachment : attachments) {
            Path path = Paths.get(UPLOAD_DIR + attachment.getOriginalFilename());
            try {
                // Créez le répertoire s'il n'existe pas
                File directory = new File(UPLOAD_DIR);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                Files.write(path, attachment.getBytes());
                fileLinks.add(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileLinks;
    }



    @GetMapping("tickets/findAll")
    public List<TicketDto> findAllTickets() {
        return ticketService.findAll();
    }


    @GetMapping("tickets/{id}")
    public ResponseEntity<Response<TicketDto>> findTicketById(@PathVariable Long id) {
        Response<TicketDto> response = ticketService.finById(id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("tickets/{id}")
    public ResponseEntity<Response<TicketDto>> updateTicket(@RequestBody TicketDto ticketDto, @PathVariable Long id) {
        Response<TicketDto> response = ticketService.updateTicket(ticketDto, id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("tickets/{id}")
    public ResponseEntity<Response> deleteTicket(@PathVariable Long id) {
        Response response = ticketService.deleteTicket(id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


}

