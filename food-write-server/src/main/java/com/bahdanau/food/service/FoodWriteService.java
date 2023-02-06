package com.bahdanau.food.service;

import com.bahdanau.food.broker.KafkaMessageBroker;
import com.bahdanau.food.dto.FoodDto;
import com.bahdanau.food.dto.broker.ActionMessage;
import com.bahdanau.food.dto.broker.ActionType;
import com.bahdanau.food.entity.Food;
import com.bahdanau.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodWriteService {
    private final KafkaMessageBroker messageBroker;
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;

    public Food addFoodItem(FoodDto foodDto, String userEmail) {
        Food foodItem = modelMapper.map(foodDto, Food.class);
        foodItem.setUserEmail(userEmail);
        Food newFoodItem = foodRepository.insert(foodItem);
        foodDto.setId(newFoodItem.getId());
        messageBroker.sendMessage(new ActionMessage(ActionType.ADD, foodDto, userEmail));
        return newFoodItem;
    }

    public Food updateFoodItem(FoodDto foodDto, String userEmail) throws IllegalAccessException {
        validateWriteOperationAccessRights(foodDto.getId(), userEmail);
        Food updatedFoodItem = modelMapper.map(foodDto, Food.class);
        updatedFoodItem.setUserEmail(userEmail);
        messageBroker.sendMessage(new ActionMessage(ActionType.UPDATE, foodDto, userEmail));
        return foodRepository.save(updatedFoodItem);
    }

    public void removeFoodItem(String id, String userEmail) throws IllegalAccessException {
        validateWriteOperationAccessRights(id, userEmail);
        Optional<Food> food = foodRepository.findById(id);
        messageBroker.sendMessage(new ActionMessage(ActionType.DELETE, modelMapper.map(food, FoodDto.class), userEmail));
        foodRepository.deleteById(id);
    }

    private void validateWriteOperationAccessRights(String id, String userEmail) throws IllegalAccessException {
        Optional<Food> food = foodRepository.findById(id);
        boolean foodItemOwnedByAnotherUser = food.stream()
                .noneMatch(foodItem -> foodItem.getUserEmail().equals(userEmail));
        if (foodItemOwnedByAnotherUser) {
            throw new IllegalAccessException("This food item is attached to another user!");
        }
    }
}
