package com.example.HelpDesk.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
public class Response<T> {

    private String status;
    private String message;
    private T data;

}
