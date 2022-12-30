package com.bahdanau.food.controller;

import com.bahdanau.food.dto.FoodDto;
import com.bahdanau.food.entity.Food;
import com.bahdanau.food.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public List<Food> getAllFood() {
        logger.info("retrieving all food items");
        return foodService.getAllFood();
    }

    @GetMapping("/category/{categoryName}")
    public List<Food> getFoodByCategory(@PathVariable String categoryName) {
        logger.info("retrieving food items of group - " + categoryName);
        return foodService.getFoodByCategory(categoryName);
    }

    @GetMapping("/{foodName}")
    public List<Food> getFoodByName(@PathVariable String foodName) {
        logger.info("retrieving food items with name containing - " + foodName);
        return foodService.getAllByName(foodName);
    }

    @PutMapping
    public List<Food> updateFoodItem(@Valid FoodDto updatedFoodItem) {
        logger.info("updating food item - " + updatedFoodItem.toString());
        return foodService.getAllFood();
    }

    @DeleteMapping("/{id}")
    public void deleteFoodItem(@PathVariable String id) {
        logger.info("deleting food item with id - " + id);
        foodService.removeFoodItem(id);
    }

    @PostMapping
    public Food createFoodItem(@Valid FoodDto newFoodItem) {
        logger.info("creating food item - " + newFoodItem.toString());
        return foodService.addFoodItem(newFoodItem);
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
