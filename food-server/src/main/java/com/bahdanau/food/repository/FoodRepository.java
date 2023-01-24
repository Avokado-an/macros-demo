package com.bahdanau.food.repository;

import com.bahdanau.food.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findAllByUserId(String userId);

    List<Food> findAllByFoodCategoryAndUserId(String foodCategory, String userId);

    List<Food> findAllByNameContainingAndUserId(String foodCategory, String userId);
}
