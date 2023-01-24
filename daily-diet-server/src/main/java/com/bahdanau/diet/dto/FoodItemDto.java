package com.bahdanau.diet.dto;

import com.bahdanau.diet.entity.FoodCategory;
import lombok.Data;

//macros are specified for 100 gram portion
@Data
public class FoodItemDto {
    private String name;
    private int calories;
    private int fats;
    private int carbs;
    private int proteins;
    private FoodCategory foodCategory;
    private float weight;
}
