package com.example.HelpDesk.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;


@Data
public class TicketDto {


    private Long id;
    private String Subject;
    private String UserEmail;
    private String number;
    private Long user_id;
    private String create_date;
    private String description;
    private String Status="UNASSIGN";
    private Long category_id;
    private String priorityLevel = "Medium";
    private  List<String> attachmentsPath;


}
