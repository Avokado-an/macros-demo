package com.bahdanau.calculator.service;

import com.bahdanau.calculator.dto.FoodItemDto;
import com.bahdanau.calculator.dto.MacrosDto;
import com.bahdanau.calculator.util.DataProvisionUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor
public class DishMacrosCalculationServiceTest {
    @Autowired
    private DishMacrosCalculationService calculationService;

    @ParameterizedTest
    @MethodSource("provideFoodWithNutrition")
    public void calculateMacros(List<FoodItemDto> input, MacrosDto expected) {
        assertEquals(expected, calculationService.calculateMacros(input));
    }

    private static Stream<Arguments> provideFoodWithNutrition() {
        return DataProvisionUtil.provideFoodWithNutrition();
    }
}
