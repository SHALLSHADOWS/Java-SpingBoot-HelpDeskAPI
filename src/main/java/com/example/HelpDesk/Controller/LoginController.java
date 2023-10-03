package com.example.HelpDesk.Controller;

import com.example.HelpDesk.Dto.LoginDto;
import com.example.HelpDesk.Dto.UserDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto login) {
        Response<UserDto> response = userService.findByEmail(login.getEmail());

        if(response == null || "Error".equals(response.getStatus())){
            return ResponseEntity.ok("User does not Exist");
        }

        UserDto user = response.getData();

        // Vérification des informations de connexion
        if (user.getEmail().equals(login.getEmail()) && login.getPassword().equals(user.getPassword()) ) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("Failure");
        }
    }


}
