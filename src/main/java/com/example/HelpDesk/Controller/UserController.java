package com.example.HelpDesk.Controller;

import com.example.HelpDesk.Dto.CategoryDto;
import com.example.HelpDesk.Model.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.HelpDesk.Dto.UserDto;
import com.example.HelpDesk.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public UserDto createUser() {
        // Crée un UserDto fictif pour tester la route
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setFirst_name("Doe");
        userDto.setEmail("john@example.com");
        userDto.setPassword("1234");


        // Appelez la méthode createUser du service pour créer l'utilisateur
        return userService.createUser(userDto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Response<List<UserDto>>> findAll() {
        Response<List<UserDto>> response = userService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<Response<UserDto>> findById(@PathVariable Long id) {
        Response<UserDto> response = userService.findById(id);
        if (response.getData() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

/*
    @GetMapping("/findALl")
    public List<UserDto> findAllUsers() {
        return (List<UserDto>) userService.findAll();
    }
*/

    @GetMapping("byEmail/{email}")
    public ResponseEntity<Response<UserDto>> findByEmail(@PathVariable String email) {
        Response<UserDto> response = userService.findByEmail(email);
        if (response.getData() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}


