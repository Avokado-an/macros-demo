package com.bahdanau.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class FoodItemDto {
    private String name;
    private Integer calories;
    private Integer fats;
    private Integer carbs;
    private Integer proteins;
    private FoodCategory foodCategory;
    private Float weight;
}