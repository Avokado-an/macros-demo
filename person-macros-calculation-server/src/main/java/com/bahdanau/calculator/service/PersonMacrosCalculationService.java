package com.bahdanau.calculator.service;

import com.bahdanau.calculator.dto.MacrosDto;
import com.bahdanau.calculator.dto.PersonMacrosCalculationDto;
import com.bahdanau.calculator.util.Gender;
import org.springframework.stereotype.Service;

@Service
public class PersonMacrosCalculationService {
    public MacrosDto calculateMacros(PersonMacrosCalculationDto calculationDto) {
        return MacrosDto.builder()
                .calories(calculateCalories(calculationDto))
                .carbs(calculateCarbs(calculationDto))
                .fats(calculateFats(calculationDto))
                .proteins(calculateProteins(calculationDto))
                .build();
    }

    private Integer calculateCarbs(PersonMacrosCalculationDto calculationDto) {
        double carbs;
        if(Gender.MALE == calculationDto.getGender()) {
            carbs = calculationDto.getWeight() * 2.4;
        } else {
            carbs = calculationDto.getWeight() * 2;
        }
        return (int) carbs;
    }

    private Integer calculateFats(PersonMacrosCalculationDto calculationDto) {
        double fats;
        if(Gender.MALE == calculationDto.getGender()) {
            fats = calculationDto.getWeight() / 3;
        } else {
            fats = calculationDto.getWeight() / 4;
        }
        return (int) fats;
    }

    private Integer calculateProteins(PersonMacrosCalculationDto calculationDto) {
        double proteins;
        if(Gender.MALE == calculationDto.getGender()) {
            proteins = calculationDto.getWeight() * 2.2;
        } else {
            proteins = calculationDto.getWeight() * 1.5;
        }
        return (int) proteins;
    }

    private Integer calculateCalories(PersonMacrosCalculationDto calculationDto) {
        double base;
        if(Gender.MALE == calculationDto.getGender()) {
            base = calculateBaseCaloriesForMan(
                    calculationDto.getWeight(), calculationDto.getHeight(), calculationDto.getAge()
            );
        } else {
            base = calculateBaseCaloriesForWoman(
                    calculationDto.getWeight(), calculationDto.getHeight(), calculationDto.getAge()
            );
        }
        return (int) (base * calculationDto.getActivityType().getFactor() + calculationDto.getAimType().getFactor());
    }

    private double calculateBaseCaloriesForWoman(double wightInKilos, double heightInCentimeters, int age) {
        return 480 + 9.25 * wightInKilos + 3.1 * heightInCentimeters - 4.33 * age;
    }

    private double calculateBaseCaloriesForMan(double wightInKilos, double heightInCentimeters, int age) {
        return 90 + 13.4 * wightInKilos + 4.8 * heightInCentimeters - 5.6 * age;
    }
}
