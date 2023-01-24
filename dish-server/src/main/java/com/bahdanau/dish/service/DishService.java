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

    public List<Dish> getAllDishes(String userId) {
        return dishRepository.findAllByUserId(userId);
    }

    public List<Dish> getDishesByCookingMethod(String cookingMethod, String userId) {
        return dishRepository.findAllByCookingMethodAndUserId(cookingMethod.toUpperCase(), userId);
    }

    public List<Dish> getDishesByName(String name, String userId) {
        return dishRepository.findAllByNameContainingIgnoreCaseAndUserId(name, userId);
    }

    public List<Dish> getDishesByIngredient(List<String> ingredients, String userId) {
        return dishRepository.findAllContainingIngredients(ingredients, userId);
    }

    public Dish addDishItem(DishDto dishDto) {
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Macros macros = macrosCalculatorClient.getMacrosForDish(dish.getIngredients());
        dish.setMacros(macros);
        return dishRepository.insert(dish);
    }

    public Dish updateDish(DishDto dishDto) {
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Macros macros = macrosCalculatorClient.getMacrosForDish(dish.getIngredients());
        dish.setMacros(macros);
        return dishRepository.save(dish);
    }

    public void removeDishItem(String id) throws IllegalAccessException {
        Optional<Dish> dish = dishRepository.findById(id);
        dishRepository.delete(dish.orElseThrow(() -> new IllegalAccessException("No dish with following Id")));
    }
}
