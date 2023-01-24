package com.bahdanau.dish.client;

import com.bahdanau.dish.entity.FoodItem;
import com.bahdanau.dish.entity.Macros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "dish-macros-calculation-server", fallback = DishMacrosCalculatorClientFallback.class)
public interface DishMacrosCalculatorClient {
    @RequestMapping(method = RequestMethod.POST, value = "/calculate-dish")
    Macros getMacrosForDish(@RequestBody List<FoodItem> ingredients);
}
