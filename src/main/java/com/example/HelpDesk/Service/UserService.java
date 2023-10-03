package com.example.HelpDesk.Service;

import com.example.HelpDesk.Dto.UserDto;
import com.example.HelpDesk.Model.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);
    Response<UserDto> findById(Long id);
    Response<UserDto> findByEmail(String email);
    Response<List<UserDto>> findAll();
}
