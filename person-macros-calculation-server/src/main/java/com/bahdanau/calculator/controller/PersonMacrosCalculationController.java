package com.bahdanau.calculator.controller;

import com.bahdanau.calculator.dto.MacrosDto;
import com.bahdanau.calculator.dto.PersonMacrosCalculationDto;
import com.bahdanau.calculator.service.PersonMacrosCalculationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class PersonMacrosCalculationController {
    private final Logger logger = Logger.getLogger(PersonMacrosCalculationController.class.getName());
    private final PersonMacrosCalculationService calculationService;

    @PostMapping
    public MacrosDto calculateMacrosForPerson(@Valid @RequestBody PersonMacrosCalculationDto calculationDto) {
        logger.info("calculating macros for user: " + calculationDto.toString());
        return calculationService.calculateMacros(calculationDto);
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
}
