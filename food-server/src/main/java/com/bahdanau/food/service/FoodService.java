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

    public Food updateFoodItem(FoodDto foodDto, String userEmail) {
        Food updatedFoodItem = modelMapper.map(foodDto, Food.class);
        updatedFoodItem.setUserEmail(userEmail);
        return foodRepository.save(updatedFoodItem);
    }

    public void removeFoodItem(FoodDto foodDto) {
        foodRepository.deleteById(foodDto.getId());
    }
}
