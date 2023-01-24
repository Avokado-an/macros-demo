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
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DailyDietService {
    private final DailyDietRepository dailyDietRepository;

    public DailyDiet getDailyDiet(String userId, LocalDate specifiedDate) {
        return dailyDietRepository.findAllByUserIdAndDocumentedDate(userId, specifiedDate);
    }

    public void removeDiet(String userId, LocalDate specifiedDate) {
        dailyDietRepository.delete(dailyDietRepository.findAllByUserIdAndDocumentedDate(userId, specifiedDate));
    }

    public DailyDiet addDishToDiet(String userId, LocalDate specifiedDate, DishDto dish) {
        DailyDiet diet = dailyDietRepository.findAllByUserIdAndDocumentedDate(userId, specifiedDate);
        List<DishDto> dishes = diet.getDishesForDay();
        dishes.add(dish);
        diet.setDishesForDay(dishes);
        diet.setMacrosDtoForFullDay(getMacrosForDay(diet.getDishesForDay()));
        return dailyDietRepository.save(diet);
    }

    public DailyDiet createEmptyDailyDiet(String userId, LocalDate specifiedDate) {
        DailyDiet diet = dailyDietRepository.findAllByUserIdAndDocumentedDate(userId, specifiedDate);
        if (diet == null) {
            diet = DailyDiet.builder()
                    .dishesForDay(new ArrayList<>())
                    .macrosDtoForFullDay(new MacrosDto())
                    .userId(userId)
                    .documentedDate(specifiedDate)
                    .build();
        }
        return dailyDietRepository.save(diet);
    }

    public DailyDiet removeDishFromDiet(String userId, LocalDate specifiedDate, String dishId) {
        DailyDiet diet = dailyDietRepository.findAllByUserIdAndDocumentedDate(userId, specifiedDate);
        List<DishDto> dishes = diet.getDishesForDay();
        dishes = dishes.stream().filter(dish -> !Objects.equals(dish.getId(), dishId)).collect(Collectors.toList());
        diet.setDishesForDay(dishes);
        diet.setMacrosDtoForFullDay(getMacrosForDay(diet.getDishesForDay()));
        return dailyDietRepository.save(diet);
    }

    public MacrosDto calculateTotalMacrosForDay(String userId, LocalDate specifiedDate) {
        DailyDiet diet = dailyDietRepository.findAllByUserIdAndDocumentedDate(userId, specifiedDate);
        return getMacrosForDay(diet.getDishesForDay());
    }

    public List<DailyMacrosDto> calculateTotalMacrosForPeriod(String userId, LocalDate startDate, LocalDate endDate) {
        List<DailyDiet> diets = dailyDietRepository.findAllInDateRangeForUser(startDate, endDate, userId);
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
