package com.example.HelpDesk.Service.Impl;

import com.example.HelpDesk.Dto.UserDto;
import com.example.HelpDesk.Model.Response;
import com.example.HelpDesk.Model.User;
import com.example.HelpDesk.Repository.UserRepository;
import com.example.HelpDesk.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        // Créez une instance de la classe User
        User user = new User();
        user.setName(userDto.getName()); // Assurez-vous que UserDto a une méthode getName()
        user.setFirst_name((userDto.getFirst_name())); // Assurez-vous que UserDto a une méthode getType()
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        // Enregistrez l'utilisateur dans la base de données (supposons que vous avez une méthode saveUser dans votre repository)
        User newUser = userRepository.save(user);

        // Créez un UserDto de la réponse en utilisant les valeurs du nouvel utilisateur
        UserDto userResponse = new UserDto();
        userResponse.setId(newUser.getId());
        userResponse.setName(newUser.getName());


        return userResponse;
    }

    @Override
    public Response<List<UserDto>> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(this::mapToDto).collect(Collectors.toList());
        return new Response<>("Success", "All users fetched", userDtos);
    }

    @Override
    public Response<UserDto> findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserDto userDto = mapToDto(optionalUser.get());
            return new Response<>("Success", "User found", userDto);
        }
        return new Response<>("Error", "User with id " + id + " not found", null);
    }

    @Override
    public Response<UserDto> findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            UserDto userDto = mapToDto(optionalUser.get());
            return new Response<>("Success", "User found", userDto);
        }
        return new Response<>("Error", "User with email " + email + " not found", null);
    }



    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
