package com.codencode.foody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    FoodItemRepository repository;

    @GetMapping("/hello")
    String hello() {
        return "Hello World";
    }

    @GetMapping("/add")
    void  add() {
        FoodItem foodItem = new FoodItem();
        foodItem.itemName = "Food Item";
        foodItem.quantity = 1;
        foodItem.price = 100;
        repository.save(foodItem);
    }
}
