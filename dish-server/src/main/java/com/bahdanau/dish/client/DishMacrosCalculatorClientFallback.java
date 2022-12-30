package com.bahdanau.dish.client;

import com.bahdanau.dish.entity.FoodItem;
import com.bahdanau.dish.entity.Macros;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishMacrosCalculatorClientFallback implements DishMacrosCalculatorClient {
    @Override
    public Macros getMacrosForDish(List<FoodItem> ingredients) {
        return new Macros(180, 10, 10, 10);
    }
}
