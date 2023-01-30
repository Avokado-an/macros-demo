package com.bahdanau.food.service;

import com.bahdanau.food.dto.FoodDto;
import com.bahdanau.food.entity.Food;
import com.bahdanau.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;

    public List<Food> getAllFood(String userEmail) {
        return foodRepository.findAllByUserEmail(userEmail);
    }

    public List<Food> getFoodByCategory(String foodCategory, String userEmail) {
        return foodRepository.findAllByFoodCategoryAndUserEmail(foodCategory.toUpperCase(), userEmail);
    }

    public List<Food> getAllByName(String name, String userEmail) {
        return foodRepository.findAllByNameContainingAndUserEmail(name, userEmail);
    }

    public Food addFoodItem(FoodDto foodDto, String userEmail) {
        Food foodItem = modelMapper.map(foodDto, Food.class);
        foodItem.setUserEmail(userEmail);
        return foodRepository.insert(foodItem);
    }

    public Food updateFoodItem(FoodDto foodDto, String userEmail) throws IllegalAccessException {
        boolean foodItemOwnedByAnotherUser = foodRepository.findById(foodDto.getId()).stream()
                .noneMatch(food -> food.getUserEmail().equals(userEmail));
        if(foodItemOwnedByAnotherUser) {
            throw new IllegalAccessException("This food item is attached to another user!");
        }
        return foodRepository.save(modelMapper.map(foodDto, Food.class));
    }

    public void removeFoodItem(String id, String userEmail) throws IllegalAccessException {
        boolean foodItemOwnedByAnotherUser = foodRepository.findById(id).stream()
                .noneMatch(food -> food.getUserEmail().equals(userEmail));
        if(foodItemOwnedByAnotherUser) {
            throw new IllegalAccessException("This food item is attached to another user!");
        }
        foodRepository.deleteById(id);
    }
}
