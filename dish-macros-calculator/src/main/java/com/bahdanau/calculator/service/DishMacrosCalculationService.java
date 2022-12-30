package com.bahdanau.calculator.service;

import com.bahdanau.calculator.dto.FoodItemDto;
import com.bahdanau.calculator.dto.MacrosDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishMacrosCalculationService {
    public MacrosDto calculateMacros(List<FoodItemDto> ingredients) {
        return MacrosDto.builder()
                .calories(calculateCalories(ingredients))
                .carbs(calculateCarbs(ingredients))
                .fats(calculateFats(ingredients))
                .proteins(calculateProteins(ingredients))
                .build();
    }

    private Integer calculateCalories(List<FoodItemDto> ingredients) {
        return ingredients.stream()
                .map(ingredient -> ingredient.getWeight() * ingredient.getCalories() / 100)
                .reduce(0f, Float::sum)
                .intValue();
    }

    private Integer calculateCarbs(List<FoodItemDto> ingredients) {
        return ingredients.stream()
                .map(ingredient -> ingredient.getWeight() * ingredient.getCarbs() / 100)
                .reduce(0f, Float::sum)
                .intValue();
    }

    private Integer calculateFats(List<FoodItemDto> ingredients) {
        return ingredients.stream()
                .map(ingredient -> ingredient.getWeight() * ingredient.getFats() / 100)
                .reduce(0f, Float::sum)
                .intValue();
    }

    private Integer calculateProteins(List<FoodItemDto> ingredients) {
        return ingredients.stream()
                .map(ingredient -> ingredient.getWeight() * ingredient.getProteins() / 100)
                .reduce(0f, Float::sum)
                .intValue();
    }
}
