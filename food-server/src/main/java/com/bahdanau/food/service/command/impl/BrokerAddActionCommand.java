package com.bahdanau.food.service.command.impl;

import com.bahdanau.food.dto.FoodDto;
import com.bahdanau.food.dto.broker.ActionType;
import com.bahdanau.food.service.FoodService;
import com.bahdanau.food.service.command.BrokerActionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrokerAddActionCommand implements BrokerActionCommand {
    private final FoodService foodService;

    @Override
    public void process(FoodDto foodDto, String userEmail) {
        foodService.addFoodItem(foodDto, userEmail);
    }

    @Override
    public String getActionName() {
        return ActionType.ADD.name();
    }
}
