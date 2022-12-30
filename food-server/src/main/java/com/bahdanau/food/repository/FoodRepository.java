package com.bahdanau.food.repository;

import com.bahdanau.food.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findAllByFoodCategory(String foodCategory);
    List<Food> findAllByNameContaining(String foodCategory);
}
