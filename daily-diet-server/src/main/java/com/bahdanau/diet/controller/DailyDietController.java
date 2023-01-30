package com.bahdanau.diet.controller;

import com.bahdanau.diet.dto.DailyMacrosDto;
import com.bahdanau.diet.dto.DishDto;
import com.bahdanau.diet.dto.MacrosDto;
import com.bahdanau.diet.entity.DailyDiet;
import com.bahdanau.diet.service.DailyDietService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("daily-diet")
@RequiredArgsConstructor
public class DailyDietController {
    private final Logger logger = Logger.getLogger(DailyDietController.class.getName());

    private final DailyDietService dailyDietService;

    @GetMapping("{date}")
    public DailyDiet getDailyDiet(JwtAuthenticationToken auth,
                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return dailyDietService.getDailyDiet(getEmailFromToken(auth), date);
    }

    @DeleteMapping("{date}")
    public void removeDiet(JwtAuthenticationToken auth,
                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        dailyDietService.removeDiet(getEmailFromToken(auth), date);
    }

    @PostMapping("{date}")
    public DailyDiet createEmptyDiet(JwtAuthenticationToken auth,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return dailyDietService.createEmptyDailyDiet(getEmailFromToken(auth), date);
    }

    @PutMapping("{date}/add-dish")
    public DailyDiet addDishToDiet(JwtAuthenticationToken auth,
                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestBody DishDto dish) {
        return dailyDietService.addDishToDiet(getEmailFromToken(auth), date, dish);
    }

    @PutMapping("{date}/remove-dish/{dishId}")
    public DailyDiet removeDishFromDiet(JwtAuthenticationToken auth,
                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                        @PathVariable String dishId) {
        return dailyDietService.removeDishFromDiet(getEmailFromToken(auth), date, dishId);
    }

    @GetMapping("{date}/macros")
    public MacrosDto getMacrosForDay(JwtAuthenticationToken auth,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return dailyDietService.calculateTotalMacrosForDay(getEmailFromToken(auth), date);
    }

    @GetMapping("{startDate}/{endDate}/macros")
    public List<DailyMacrosDto> getMacrosForPeriod(JwtAuthenticationToken auth,
                                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return dailyDietService.calculateTotalMacrosForPeriod(getEmailFromToken(auth), startDate, endDate);
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
        logger.warning("received validation errors on request - " + errors);
        return errors;
    }

    private String getEmailFromToken(JwtAuthenticationToken auth) {
        return (String) auth.getToken().getClaims().get("email");
    }
}
