package com.bahdanau.dish.controller;

import com.bahdanau.dish.dto.DishDto;
import com.bahdanau.dish.entity.Dish;
import com.bahdanau.dish.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("dish")
@RequiredArgsConstructor
public class DishController {
    private final Logger logger = Logger.getLogger(DishController.class.getName());
    private final DishService dishService;

    @GetMapping
    public List<Dish> getAllDishes() {
        logger.info("retrieving all food items");
        return dishService.getAllDishes();
    }

    @GetMapping("/category/{cookingMethod}")
    public List<Dish> getDishByCookingMethod(@PathVariable String cookingMethod) {
        logger.info("retrieving dishes of cooking method - " + cookingMethod);
        return dishService.getDishesByCookingMethod(cookingMethod);
    }

    @GetMapping("/{dishName}")
    public List<Dish> getDishByName(@PathVariable String dishName) {
        logger.info("retrieving dishes with name containing - " + dishName);
        return dishService.getDishesByName(dishName);
    }

    @GetMapping("/ingredient/")
    public List<Dish> getDishByIngredients(@RequestParam(value = "ingredient") List<String> ingredientsNames) {
        logger.info("retrieving dishes which contain - " + ingredientsNames);
        return dishService.getDishesByIngredient(ingredientsNames);
    }

    @PutMapping
    public Dish updateDish(@RequestBody @Valid DishDto updatedFoodItem) throws IllegalAccessException {
        logger.info("updating food item - " + updatedFoodItem.toString());
        return dishService.updateDish(updatedFoodItem);
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable String id) throws IllegalAccessException {
        logger.info("deleting food item with id - " + id);
        dishService.removeDishItem(id);
    }

    @PostMapping
    public Dish createDish(@RequestBody @Valid DishDto newDishItem) throws IllegalAccessException {
        logger.info("creating food item - " + newDishItem.toString());
        return dishService.addDishItem(newDishItem);
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> handleIllegalAccessExceptions(IllegalAccessException ex) {
        logger.warning("received illegal access exception on request - " + ex.getMessage());
        logger.warning("received illegal access exception on request - " + Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
