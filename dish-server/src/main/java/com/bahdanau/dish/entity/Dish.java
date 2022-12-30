package com.bahdanau.dish.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Dish {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String creatorId;
    private List<CookingStep> cookingSteps;
    private List<FoodItem> ingredients;
    private CookingMethod cookingMethod;
    private Macros macros;
}
