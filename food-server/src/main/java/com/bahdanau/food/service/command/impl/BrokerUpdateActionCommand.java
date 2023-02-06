package com.bahdanau.food.service.command.impl;

import com.bahdanau.food.dto.FoodDto;
import com.bahdanau.food.dto.broker.ActionType;
import com.bahdanau.food.service.FoodService;
import com.bahdanau.food.service.command.BrokerActionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrokerUpdateActionCommand implements BrokerActionCommand {
    private final FoodService foodService;

    @Override
    public void process(FoodDto foodDto, String userEmail) {
        foodService.updateFoodItem(foodDto, userEmail);
    }

    @Override
    public String getActionName() {
        return ActionType.UPDATE.name();
    }
}
