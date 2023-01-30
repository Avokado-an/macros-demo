package com.bahdanau.diet.service;

import com.bahdanau.diet.dto.DailyMacrosDto;
import com.bahdanau.diet.dto.DishDto;
import com.bahdanau.diet.dto.MacrosDto;
import com.bahdanau.diet.entity.DailyDiet;
import com.bahdanau.diet.repository.DailyDietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DailyDietService {
    private final DailyDietRepository dailyDietRepository;

    public DailyDiet getDailyDiet(String userEmail, LocalDate specifiedDate) {
        return dailyDietRepository.findAllByUserEmailAndDocumentedDate(userEmail, specifiedDate)
                .orElseThrow(() -> new IllegalArgumentException("This day is not documented for a diet"));
    }

    public void removeDiet(String userEmail, LocalDate specifiedDate) {
        DailyDiet diet = dailyDietRepository.findAllByUserEmailAndDocumentedDate(userEmail, specifiedDate)
                .orElseThrow(() -> new IllegalArgumentException("This day is not documented for a diet"));
        dailyDietRepository.delete(diet);
    }

    public DailyDiet addDishToDiet(String userEmail, LocalDate specifiedDate, DishDto dish) {
        DailyDiet diet = dailyDietRepository.findAllByUserEmailAndDocumentedDate(userEmail, specifiedDate)
                .orElseThrow(() -> new IllegalArgumentException("This day is not documented for a diet"));
        List<DishDto> dishes = diet.getDishesForDay();
        dishes.add(dish);
        diet.setDishesForDay(dishes);
        diet.setMacrosDtoForFullDay(getMacrosForDay(diet.getDishesForDay()));
        return dailyDietRepository.save(diet);
    }

    public DailyDiet createEmptyDailyDiet(String userEmail, LocalDate specifiedDate) {
        Optional<DailyDiet> diet = dailyDietRepository.findAllByUserEmailAndDocumentedDate(userEmail, specifiedDate);
        if (diet.isEmpty()) {
            diet = Optional.of(
                    DailyDiet.builder()
                            .dishesForDay(new ArrayList<>())
                            .macrosDtoForFullDay(new MacrosDto())
                            .userEmail(userEmail)
                            .documentedDate(specifiedDate)
                            .build()
            );
        }
        return dailyDietRepository.save(diet.get());
    }

    public DailyDiet removeDishFromDiet(String userEmail, LocalDate specifiedDate, String dishId) {
        DailyDiet diet = dailyDietRepository.findAllByUserEmailAndDocumentedDate(userEmail, specifiedDate)
                .orElseThrow(() -> new IllegalArgumentException("This day is not documented for a diet"));
        List<DishDto> dishes = diet.getDishesForDay();
        dishes = dishes.stream().filter(dish -> !Objects.equals(dish.getId(), dishId)).collect(Collectors.toList());
        diet.setDishesForDay(dishes);
        diet.setMacrosDtoForFullDay(getMacrosForDay(diet.getDishesForDay()));
        return dailyDietRepository.save(diet);
    }

    public MacrosDto calculateTotalMacrosForDay(String userEmail, LocalDate specifiedDate) {
        DailyDiet diet = dailyDietRepository.findAllByUserEmailAndDocumentedDate(userEmail, specifiedDate)
                .orElseThrow(() -> new IllegalArgumentException("This day is not documented for a diet"));
        return getMacrosForDay(diet.getDishesForDay());
    }

    public List<DailyMacrosDto> calculateTotalMacrosForPeriod(String userEmail, LocalDate startDate, LocalDate endDate) {
        List<DailyDiet> diets = dailyDietRepository.findAllInDateRangeForUser(startDate, endDate, userEmail);
        return diets.stream()
                .map(diet -> DailyMacrosDto.builder()
                        .macrosDto(getMacrosForDay(diet.getDishesForDay()))
                        .date(diet.getDocumentedDate())
                        .build()
                )
                .collect(Collectors.toList());
    }

    private MacrosDto getMacrosForDay(List<DishDto> dishesForTheDay) {
        return MacrosDto.builder()
                .calories(sumNutrients(dishesForTheDay, MacrosDto::getCalories))
                .carbs(sumNutrients(dishesForTheDay, MacrosDto::getCarbs))
                .fats(sumNutrients(dishesForTheDay, MacrosDto::getFats))
                .proteins(sumNutrients(dishesForTheDay, MacrosDto::getProteins))
                .build();
    }

    private Integer sumNutrients(List<DishDto> dishes, ToIntFunction<MacrosDto> methodReference) {
        Stream<MacrosDto> macrosDtoStream = dishes.stream().map(DishDto::getMacrosDto);
        return macrosDtoStream.mapToInt(methodReference).sum();
    }
}
