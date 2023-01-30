package com.bahdanau.food.controller;

import com.bahdanau.food.dto.FoodDto;
import com.bahdanau.food.entity.Food;
import com.bahdanau.food.service.FoodService;
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

@RestController
@RequestMapping("food")
@RequiredArgsConstructor
public class FoodController {
    private final Logger logger = Logger.getLogger(FoodController.class.getName());
    private final FoodService foodService;

    @GetMapping
    public List<Food> getAllFood(JwtAuthenticationToken auth) {
        logger.info("retrieving all food items");
        return foodService.getAllFood(getEmailFromToken(auth));
    }

    @GetMapping("/category/{categoryName}")
    public List<Food> getFoodByCategory(@PathVariable String categoryName, JwtAuthenticationToken auth) {
        logger.info("retrieving food items of group - " + categoryName);
        return foodService.getFoodByCategory(categoryName, getEmailFromToken(auth));
    }

    @GetMapping("/{foodName}")
    public List<Food> getFoodByName(@PathVariable String foodName, JwtAuthenticationToken auth) {
        logger.info("retrieving food items with name containing - " + foodName);
        return foodService.getAllByName(foodName, getEmailFromToken(auth));
    }

    @PutMapping
    public Food updateFoodItem(@RequestBody @Valid FoodDto updatedFoodItem, JwtAuthenticationToken auth) throws IllegalAccessException {
        logger.info("updating food item - " + updatedFoodItem.toString());
        return foodService.updateFoodItem(updatedFoodItem, getEmailFromToken(auth));
    }

    @DeleteMapping("/{id}")
    public void deleteFoodItem(@PathVariable String id, JwtAuthenticationToken auth) throws IllegalAccessException {
        logger.info("deleting food item with id - " + id);
        foodService.removeFoodItem(id, getEmailFromToken(auth));
    }

    @PostMapping
    public Food createFoodItem(@RequestBody @Valid FoodDto newFoodItem, JwtAuthenticationToken auth) {
        logger.info("creating food item - " + newFoodItem.toString());
        return foodService.addFoodItem(newFoodItem, getEmailFromToken(auth));
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
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> handleIllegalAccessExceptions(IllegalAccessException ex) {
        logger.warning("received illegal access exception on request - " + ex.getMessage());
        logger.warning("received illegal access exception on request - " + Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private String getEmailFromToken(JwtAuthenticationToken auth) {
        return (String) auth.getToken().getClaims().get("email");
    }
}
