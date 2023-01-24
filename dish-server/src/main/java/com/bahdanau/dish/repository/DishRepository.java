package com.bahdanau.dish.repository;

import com.bahdanau.dish.entity.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String> {

    List<Dish> findAllByUserId(String userId);

    List<Dish> findAllByCookingMethodAndUserId(String cookingMethod, String userId);

    List<Dish> findAllByNameContainingIgnoreCaseAndUserId(String name, String userId);

    @org.springframework.data.mongodb.repository.Query("{ 'ingredients.name' : { $all : ?0}, 'userId' :  {$eq :  ?1}}")
    List<Dish> findAllContainingIngredients(List<String> ingredientName, String userId);
}
