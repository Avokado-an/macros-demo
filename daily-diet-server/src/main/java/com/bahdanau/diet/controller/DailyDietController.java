package com.bahdanau.diet.controller;

import com.bahdanau.diet.dto.DailyMacrosDto;
import com.bahdanau.diet.dto.DishDto;
import com.bahdanau.diet.dto.MacrosDto;
import com.bahdanau.diet.entity.DailyDiet;
import com.bahdanau.diet.service.DailyDietService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{userId}/{date}")
    public DailyDiet getDailyDiet(@PathVariable String userId,
                                  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return dailyDietService.getDailyDiet(userId, date);
    }

    @DeleteMapping("{userId}/{date}")
    public void removeDiet(@PathVariable String userId,
                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        dailyDietService.removeDiet(userId, date);
    }

    @PostMapping("{userId}/{date}")
    public DailyDiet createEmptyDiet(@PathVariable String userId,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return dailyDietService.createEmptyDailyDiet(userId, date);
    }

    @PutMapping("{userId}/{date}/add-dish")
    public DailyDiet addDishToDiet(@PathVariable String userId,
                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                   @RequestBody DishDto dish) {
        return dailyDietService.addDishToDiet(userId, date, dish);
    }

    @PutMapping("{userId}/{date}/remove-dish/{dishId}")
    public DailyDiet removeDishFromDiet(@PathVariable String userId,
                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                        @PathVariable String dishId) {
        return dailyDietService.removeDishFromDiet(userId, date, dishId);
    }

    @GetMapping("{userId}/{date}/macros")
    public MacrosDto getMacrosForDay(@PathVariable String userId,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return dailyDietService.calculateTotalMacrosForDay(userId, date);
    }

    @GetMapping("{userId}/{startDate}/{endDate}/macros")
    public List<DailyMacrosDto> getMacrosForPeriod(@PathVariable String userId,
                                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return dailyDietService.calculateTotalMacrosForPeriod(userId, startDate, endDate);
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
}
