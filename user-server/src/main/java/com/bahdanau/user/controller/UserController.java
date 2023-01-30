package com.bahdanau.user.controller;

import com.bahdanau.user.dto.CreateUserDto;
import com.bahdanau.user.entity.Macros;
import com.bahdanau.user.entity.User;
import com.bahdanau.user.service.UserService;
import com.mongodb.MongoWriteException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final Logger logger = Logger.getLogger(UserController.class.getName());
    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        logger.info("retrieving users");
        return userService.getUsers();
    }

    @GetMapping("/current")
    public User getUser(JwtAuthenticationToken auth) {
        return userService.getUserByEmail(getEmailFromToken(auth));
    }

    @PostMapping
    public User addUser(@Valid @RequestBody CreateUserDto newUser) {
        logger.info("adding user");
        return userService.addUser(newUser);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        logger.info("deleting user");
        userService.deleteUser(username);
    }

    @GetMapping("/username/{username}")
    public List<User> getUsersByUsername(@PathVariable String username) {
        logger.info("retrieving filtered usernames. Filter - " + username);
        return userService.getFilteredByUsername(username);
    }

    @GetMapping("/macros")
    public Macros getUserMacrosByEmail(JwtAuthenticationToken auth) {
        logger.info("retrieving filtered id. Filter - " + getEmailFromToken(auth));
        return userService.getUSerMacrosByUserEmail(getEmailFromToken(auth));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.warning("received following errors on request - " + errors);
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<String> handleValidationExceptions(MongoWriteException ex) {
        logger.warning("mongodb write exception message - " + ex.getMessage());
        logger.warning("mongodb write exception stacktrace - " + Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>("This email is already taken, try another one", HttpStatus.BAD_REQUEST);
    }

    private String getEmailFromToken(JwtAuthenticationToken auth) {
        return (String) auth.getToken().getClaims().get("email");
    }
}
