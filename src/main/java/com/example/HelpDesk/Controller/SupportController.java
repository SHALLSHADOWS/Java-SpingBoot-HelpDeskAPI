package com.example.HelpDesk.Controller;

import com.example.HelpDesk.Dto.SupportDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Model.Support;
import com.example.HelpDesk.Service.SupportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/supports")
public class SupportController {

    private final SupportService supportService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SupportDto> createSupport(@RequestBody SupportDto supportDto) {
        SupportDto supportCreate = supportService.createSupport(supportDto);
        return new ResponseEntity<>(supportCreate, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public List<SupportDto> findAllSupports() {
        return supportService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<SupportDto>> findSupportById(@PathVariable int id) {
        Response<SupportDto> response = supportService.finById(id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<SupportDto>> updateSupport(@RequestBody SupportDto supportDto, @PathVariable int id) {
        Response<SupportDto> response = supportService.updateSupport(supportDto, id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteSupport(@PathVariable int id) {
        Response response = supportService.deleteSupport(id);
        if ("Error".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


}

