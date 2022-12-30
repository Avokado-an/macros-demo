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

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public List<Dish> getDishesByCookingMethod(String cookingMethod) {
        return dishRepository.findAllByCookingMethod(cookingMethod);
    }

    public List<Dish> getDishesByName(String name) {
        return dishRepository.findAllByNameContaining(name);
    }

    public List<Dish> getDishesByIngredient(List<String> ingredients) {
        return dishRepository.findAllContainingIngredients(ingredients);
    }

    public Dish addDishItem(DishDto dishDto) throws IllegalAccessException {
        if (dishDto.getId() == null) {
            throw new IllegalAccessException("You can not change recipe from common source. Change your personal ones");
        }
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Macros macros = macrosCalculatorClient.getMacrosForDish(dish.getIngredients());
        dish.setMacros(macros);
        return dishRepository.insert(dish);
    }

    public Dish updateDish(DishDto dishDto) throws IllegalAccessException {
        if (dishDto.getId() == null) {
            throw new IllegalAccessException("You can not change recipe from common source. Change your personal ones");
        }
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Macros macros = macrosCalculatorClient.getMacrosForDish(dish.getIngredients());
        dish.setMacros(macros);
        return dishRepository.save(dish);
    }

    public void removeDishItem(String id) throws IllegalAccessException {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isEmpty()) {
            throw new IllegalAccessException("There is no dish with such identification");
        } else if (dish.get().getCreatorId() == null) {
            throw new IllegalAccessException("You can't delete recipes from common source");
        }
        dishRepository.delete(dish.get());
    }
}
