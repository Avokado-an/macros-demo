package com.bahdanau.aim.service;

import com.bahdanau.aim.dto.ClientMonthlyResultDto;
import com.bahdanau.aim.dto.DailyMacrosDto;
import com.bahdanau.aim.dto.DailyResultDto;
import com.bahdanau.aim.dto.MacrosDto;
import com.bahdanau.aim.feign.DailyDietFeignClient;
import com.bahdanau.aim.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.ToIntFunction;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
@RequiredArgsConstructor
public class ClientAimService {
    private final DailyDietFeignClient dietFeignClient;
    private final UserFeignClient userFeignClient;

    public ClientMonthlyResultDto getClientResultsForMonth(String userId, LocalDate date) {
        LocalDate startDate = date.with(firstDayOfMonth());
        LocalDate endDate = date.with(lastDayOfMonth());

        MacrosDto userMacros = getClientAimedMacros(userId);
        List<DailyResultDto> dailyResults = getDailyResults(userId, startDate, endDate, userMacros);
        MacrosDto totalMonthlyDifference = calculateTotalMonthlyMacros(dailyResults);
        float totalWeightDifference = calculateWeightFromCalories(totalMonthlyDifference.getCalories());

        return ClientMonthlyResultDto.builder()
                .clientMacrosForTheDay(userMacros)
                .startDate(startDate)
                .userId(userId)
                .dailyResults(dailyResults)
                .weightTotalDifference(totalWeightDifference)
                .totalMonthlyDifference(totalMonthlyDifference)
                .build();
    }

    private List<DailyResultDto> getDailyResults(String userId, LocalDate startDate, LocalDate endDate, MacrosDto userAimedMacros) {
        List<DailyMacrosDto> macrosForMonth = dietFeignClient.getDishesMacrosForPeriod(userId, startDate, endDate);
        return macrosForMonth.stream()
                .map(macrosForDay -> DailyResultDto.builder()
                        .macrosDifferenceFromExpected(calculateMacrosDifference(userAimedMacros, macrosForDay.getMacrosDto()))
                        .date(macrosForDay.getDate())
                        .build()
                )
                .toList();
    }

    private MacrosDto calculateTotalMonthlyMacros(List<DailyResultDto> dailyMacros) {
        int calories = getNutrientsSum(dailyMacros, MacrosDto::getCalories);
        int fats = getNutrientsSum(dailyMacros, MacrosDto::getFats);
        int carbs = getNutrientsSum(dailyMacros, MacrosDto::getCarbs);
        int proteins = getNutrientsSum(dailyMacros, MacrosDto::getProteins);
        return MacrosDto.builder()
                .fats(fats)
                .proteins(proteins)
                .carbs(carbs)
                .calories(calories)
                .build();
    }

    private static int getNutrientsSum(List<DailyResultDto> dailyMacros, ToIntFunction<MacrosDto> getNutrient) {
        return dailyMacros.stream().map(DailyResultDto::getMacrosDifferenceFromExpected).mapToInt(getNutrient).sum();
    }

    private float calculateWeightFromCalories(int calories) {
        return calories / 8.f;
    }

    private MacrosDto calculateMacrosDifference(MacrosDto userMacros, MacrosDto dailyMacros) {
        return MacrosDto.builder()
                .calories(userMacros.getCalories() - dailyMacros.getCalories())
                .carbs(userMacros.getCarbs() - dailyMacros.getCarbs())
                .proteins(userMacros.getProteins() - dailyMacros.getProteins())
                .fats(userMacros.getFats() - dailyMacros.getFats())
                .build();
    }

    private MacrosDto getClientAimedMacros(String userId) {
        return userFeignClient.getUserAimMacros(userId);
    }
}
