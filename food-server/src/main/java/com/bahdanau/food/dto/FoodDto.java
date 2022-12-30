package com.bahdanau.food.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class FoodDto {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private Integer calories;
    @NotBlank
    private Integer fats;
    @NotBlank
    private Integer carbs;
    @NotBlank
    private Integer proteins;
}
