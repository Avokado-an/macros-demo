package com.bahdanau.food.repository;

import com.bahdanau.food.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
}
