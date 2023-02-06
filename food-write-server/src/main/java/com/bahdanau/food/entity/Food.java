package com.bahdanau.food.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Food {
    @Id
    private String id;

    private String name;
    private Integer calories;
    private Integer fats;
    private Integer carbs;
    private Integer proteins;
    private FoodCategory foodCategory;

    private String userEmail;
}
