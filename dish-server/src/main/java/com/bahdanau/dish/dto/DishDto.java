package com.bahdanau.dish.dto;

import com.bahdanau.dish.entity.CookingMethod;
import com.bahdanau.dish.entity.FoodItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class DishDto {
    private String id;
    @NotBlank
    private String name;
    @NotEmpty
    private List<FoodItem> ingredients;
    @NotBlank
    private String userId;
    private CookingMethod cookingMethod;
}
