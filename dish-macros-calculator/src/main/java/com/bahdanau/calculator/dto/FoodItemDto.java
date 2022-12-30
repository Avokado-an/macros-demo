package com.bahdanau.calculator.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class FoodItemDto {
    private String name;
    private Float weight;
    private Integer calories;
    private Integer fats;
    private Integer proteins;
    private Integer carbs;
    private FoodCategory foodCategory;
}