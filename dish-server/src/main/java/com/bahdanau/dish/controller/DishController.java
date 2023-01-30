package com.bahdanau.dish.controller;

import com.bahdanau.dish.dto.DishDto;
import com.bahdanau.dish.entity.Dish;
import com.bahdanau.dish.service.DishService;
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
@RequestMapping("dish")
@RequiredArgsConstructor
public class DishController {
    private final Logger logger = Logger.getLogger(DishController.class.getName());
    private final DishService dishService;

    @GetMapping()
    public List<Dish> getAllDishes(JwtAuthenticationToken auth) {
        logger.info("retrieving all food items for user - " + getEmailFromToken(auth));
        return dishService.getAllDishes(getEmailFromToken(auth));
    }

    @GetMapping("/category/{cookingMethod}")
    public List<Dish> getDishByCookingMethod(JwtAuthenticationToken auth, @PathVariable String cookingMethod) {
        logger.info("retrieving dishes of cooking method - " + cookingMethod);
        return dishService.getDishesByCookingMethod(cookingMethod, getEmailFromToken(auth));
    }

    @GetMapping("{dishName}")
    public List<Dish> getDishByName(JwtAuthenticationToken auth, @PathVariable String dishName) {
        logger.info("retrieving dishes with name containing - " + dishName);
        return dishService.getDishesByName(dishName, getEmailFromToken(auth));
    }

    @GetMapping("ingredient")
    public List<Dish> getDishByIngredients(JwtAuthenticationToken auth,
                                           @RequestParam(value = "ingredients") List<String> ingredientsNames) {
        logger.info("retrieving dishes which contain - " + ingredientsNames);
        return dishService.getDishesByIngredient(ingredientsNames, getEmailFromToken(auth));
    }

    @PutMapping
    public Dish updateDish(JwtAuthenticationToken auth, @RequestBody @Valid DishDto updatedFoodItem) throws IllegalAccessException {
        logger.info("updating food item - " + updatedFoodItem.toString());
        return dishService.updateDish(updatedFoodItem, getEmailFromToken(auth));
    }

    @DeleteMapping("/{id}")
    public void deleteDish(JwtAuthenticationToken auth, @PathVariable String id) throws IllegalAccessException {
        logger.info("deleting food item with id - " + id);
        dishService.removeDishItem(id, getEmailFromToken(auth));
    }

    @PostMapping
    public Dish createDish(JwtAuthenticationToken auth, @RequestBody @Valid DishDto newDishItem) {
        logger.info("creating food item - " + newDishItem.toString());
        return dishService.addDishItem(newDishItem, getEmailFromToken(auth));
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

    private String getEmailFromToken(JwtAuthenticationToken auth) {
        return (String) auth.getToken().getClaims().get("email");
    }
}
