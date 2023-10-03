package com.example.HelpDesk.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String UserEmail;
    private String number;
    private String Subject;
    private String Status="Unassign";
    private Long user_id;
    private String create_date;
    private String description;
    private Long category_id;
    private String priorityLevel = "Medium";

    private  List<String> attachmentsPath;
}
