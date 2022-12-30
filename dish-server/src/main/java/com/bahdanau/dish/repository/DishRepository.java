package com.bahdanau.dish.repository;

import com.bahdanau.dish.entity.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String> {
    List<Dish> findAllByCookingMethod(String cookingMethod);
    List<Dish> findAllByNameContaining(String name);

    @Query("db.dish.find({\"ingredients.name\" : {$regex : :ingredientName}});")//todo add a bit later
    List<Dish> findAllContainingIngredients(List<String> ingredientName);
}
