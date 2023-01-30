package com.bahdanau.dish.service;

import com.bahdanau.dish.client.DishMacrosCalculatorClient;
import com.bahdanau.dish.dto.DishDto;
import com.bahdanau.dish.entity.Dish;
import com.bahdanau.dish.entity.Macros;
import com.bahdanau.dish.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishMacrosCalculatorClient macrosCalculatorClient;
    private final ModelMapper modelMapper;
    private final DishRepository dishRepository;

    public List<Dish> getAllDishes(String userEmail) {
        return dishRepository.findAllByUserEmail(userEmail);
    }

    public List<Dish> getDishesByCookingMethod(String cookingMethod, String userEmail) {
        return dishRepository.findAllByCookingMethodAndUserEmail(cookingMethod.toUpperCase(), userEmail);
    }

    public List<Dish> getDishesByName(String name, String userEmail) {
        return dishRepository.findAllByNameContainingIgnoreCaseAndUserEmail(name, userEmail);
    }

    public List<Dish> getDishesByIngredient(List<String> ingredients, String userEmail) {
        return dishRepository.findAllContainingIngredients(ingredients, userEmail);
    }

    public Dish addDishItem(DishDto dishDto, String userEmail) {
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Macros macros = macrosCalculatorClient.getMacrosForDish(dish.getIngredients());
        dish.setMacros(macros);
        dish.setUserEmail(userEmail);
        return dishRepository.insert(dish);
    }

    public Dish updateDish(DishDto dishDto, String userEmail) throws IllegalAccessException {
        throwExceptionOnIllegalDataAccess(dishDto.getId(), userEmail);

        Dish dish = modelMapper.map(dishDto, Dish.class);
        Macros macros = macrosCalculatorClient.getMacrosForDish(dish.getIngredients());
        dish.setUserEmail(userEmail);
        dish.setMacros(macros);
        return dishRepository.save(dish);
    }

    public void removeDishItem(String id, String userEmail) throws IllegalAccessException {
        throwExceptionOnIllegalDataAccess(id, userEmail);
        dishRepository.deleteById(id);
    }

    private void throwExceptionOnIllegalDataAccess(String dishId, String userEmail) throws IllegalAccessException {
        boolean isDishOwnedByAnotherUser = dishRepository.findById(dishId)
                .stream()
                .noneMatch(dish -> userEmail.equals(dish.getUserEmail()));
        if (isDishOwnedByAnotherUser) {
            throw new IllegalAccessException("This dish is attached to another user");
        }
    }
}
