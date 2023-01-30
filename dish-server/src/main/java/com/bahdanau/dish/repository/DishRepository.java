package com.bahdanau.dish.repository;

import com.bahdanau.dish.entity.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String> {

    List<Dish> findAllByUserEmail(String userEmail);

    List<Dish> findAllByCookingMethodAndUserEmail(String cookingMethod, String userEmail);

    List<Dish> findAllByNameContainingIgnoreCaseAndUserEmail(String name, String userEmail);

    @org.springframework.data.mongodb.repository.Query("{ 'ingredients.name' : { $all : ?0}, 'userEmail' :  {$eq :  ?1}}")
    List<Dish> findAllContainingIngredients(List<String> ingredientName, String userEmail);
}
