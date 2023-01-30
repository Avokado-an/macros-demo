package com.bahdanau.user.service;

import com.bahdanau.user.client.PersonMacrosCalculatorClient;
import com.bahdanau.user.dto.CreateUserDto;
import com.bahdanau.user.dto.UserParametersDto;
import com.bahdanau.user.entity.Macros;
import com.bahdanau.user.entity.User;
import com.bahdanau.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PersonMacrosCalculatorClient personMacrosCalculatorClient;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public List<User> getFilteredByUsername(String filterWord) {
        return userRepository.findAllByUsernameContainingIgnoreCase(filterWord);
    }

    public Macros getUSerMacrosByUserEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(RuntimeException::new).getMacros();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(CreateUserDto newUser) {
        User userToInsert = modelMapper.map(newUser, User.class);
        UserParametersDto userParams = modelMapper.map(userToInsert, UserParametersDto.class);
        Macros userMacros = personMacrosCalculatorClient.getMacrosForPerson(userParams);
        userToInsert.setMacros(userMacros);
        return userRepository.insert(userToInsert);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
