package com.bahdanau.food.dto;

import com.bahdanau.food.entity.FoodCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ToString
public class FoodDto {
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private Integer calories;
    @NotNull
    private Integer fats;
    @NotNull
    private Integer carbs;
    @NotNull
    private Integer proteins;
    @NotNull
    private FoodCategory foodCategory;
}
