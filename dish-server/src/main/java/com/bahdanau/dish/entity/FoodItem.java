package com.bahdanau.dish.entity;

import lombok.Data;

//macros are specified for 100 gram portion
@Data
public class FoodItem {
    private String name;
    private Integer calories;
    private Integer fats;
    private Integer carbs;
    private Integer proteins;
    private FoodCategory foodCategory;
    private Float weight;
}
