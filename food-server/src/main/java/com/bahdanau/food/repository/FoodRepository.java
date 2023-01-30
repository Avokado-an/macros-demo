package com.bahdanau.food.repository;

import com.bahdanau.food.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findAllByUserEmail(String userEmail);

    List<Food> findAllByFoodCategoryAndUserEmail(String foodCategory, String userEmail);

    List<Food> findAllByNameContainingAndUserEmail(String foodCategory, String userEmail);
}
