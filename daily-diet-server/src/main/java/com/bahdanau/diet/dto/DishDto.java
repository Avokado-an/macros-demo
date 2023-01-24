package com.bahdanau.diet.dto;

import com.bahdanau.diet.entity.CookingMethod;
import lombok.Data;

import java.util.List;

@Data
public class DishDto {
    private String id;
    private String name;
    private List<FoodItemDto> ingredients;
    private CookingMethod cookingMethod;
    private MacrosDto macrosDto;
}
