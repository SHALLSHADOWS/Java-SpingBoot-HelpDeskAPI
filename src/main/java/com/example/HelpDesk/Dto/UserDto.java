package com.example.HelpDesk.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;


@Data
public class UserDto {
    private Long id;
    private String name;
    private String first_name;
    private String email;
    private String password;

    // Getters and setters (Lombok's @Data generates them)
}
