package com.bahdanau.calculator.util;

import com.bahdanau.calculator.dto.FoodItemDto;
import com.bahdanau.calculator.dto.MacrosDto;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class DataProvisionUtil {
    public static Stream<Arguments> provideFoodWithNutrition() {
        return Stream.of(
                Arguments.of(
                        provideFoodItems(100.f, 150, 10, 10, 2),
                        provideMacros(450, 30, 30, 6)
                ),
                Arguments.of(
                        provideFoodItems(175.f, 400, 20, 20, 20),
                        provideMacros(2100, 105, 105, 105)
                ),
                Arguments.of(
                        provideFoodItems(10.f, 900, 90, 0, 0),
                        provideMacros(270, 27, 0, 0)
                )
        );
    }

    public static List<FoodItemDto> provideFoodItems(float weight, int calories, int fats, int proteins, int carbs) {
        return List.of(
                FoodItemDto
                        .builder()
                        .calories(calories)
                        .fats(fats)
                        .weight(weight)
                        .carbs(carbs)
                        .proteins(proteins)
                        .build(),
                FoodItemDto
                        .builder()
                        .calories(calories)
                        .fats(fats)
                        .weight(weight)
                        .carbs(carbs)
                        .proteins(proteins)
                        .build(),
                FoodItemDto
                        .builder()
                        .calories(calories)
                        .fats(fats)
                        .weight(weight)
                        .carbs(carbs)
                        .proteins(proteins)
                        .build()
        );
    }

    public static MacrosDto provideMacros(int calories, int fats, int proteins, int carbs) {
        return MacrosDto.builder()
                .calories(calories)
                .fats(fats)
                .carbs(carbs)
                .proteins(proteins)
                .build();
    }
}
