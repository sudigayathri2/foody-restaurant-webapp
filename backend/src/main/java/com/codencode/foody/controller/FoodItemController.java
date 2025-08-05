package com.codencode.foody.controller;

import com.codencode.foody.entity.FoodItem;
import com.codencode.foody.exception.InvalidIdException;
import com.codencode.foody.service.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/food-item")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UUID> addFoodItem(@Valid @RequestBody FoodItem foodItem) {
        UUID id = foodItemService.save(foodItem);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @GetMapping("/get/{id}")
    ResponseEntity<FoodItem> getFoodItemById(@PathVariable UUID id) throws InvalidIdException {
        FoodItem item = foodItemService.getFoodItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteFoodItem(@RequestBody FoodItem foodItem) {
        foodItemService.deleteFoodItem(foodItem);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    ResponseEntity<FoodItem> updateFoodItem(@Valid @RequestBody FoodItem foodItem) throws InvalidIdException {
        FoodItem updateFoodItem = foodItemService.updateFoodItem(foodItem);
        return new ResponseEntity<>(updateFoodItem, HttpStatus.OK);
    }

}
