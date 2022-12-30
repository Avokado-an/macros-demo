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

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public List<Food> getFoodByCategory(String foodCategory) {
        return foodRepository.findAllByFoodCategory(foodCategory);
    }

    public List<Food> getAllByName(String name) {
        return foodRepository.findAllByNameContaining(name);
    }

    public Food addFoodItem(FoodDto foodDto) {
        return foodRepository.insert(modelMapper.map(foodDto, Food.class));
    }

    public Food updateFoodItem(FoodDto foodDto) {
        return foodRepository.save(modelMapper.map(foodDto, Food.class));
    }

    public void removeFoodItem(String id) {
        foodRepository.deleteById(id);
    }
}