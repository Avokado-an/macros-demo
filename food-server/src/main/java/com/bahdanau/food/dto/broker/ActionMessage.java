package com.bahdanau.food.dto.broker;

import com.bahdanau.food.dto.FoodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionMessage {
    private ActionType actionType;
    private FoodDto foodDto;

    private String userEmail;
}
