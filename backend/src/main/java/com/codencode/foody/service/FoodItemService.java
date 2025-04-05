package com.codencode.foody.service;

import com.codencode.foody.repository.FoodItemRepository;
import com.codencode.foody.entity.FoodItem;
import com.codencode.foody.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodItemService {
    @Autowired
    FoodItemRepository repository;

    public UUID save(FoodItem foodItem) {
        foodItem.setCreateDate(LocalDate.now());
        FoodItem savedItem = repository.save(foodItem);
        return savedItem.getId();
    }

    public List<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    public FoodItem updateFoodItem(FoodItem foodItem) throws InvalidIdException {
        FoodItem item = getFoodItemById(foodItem.getId());
        foodItem.setUpdateDate(LocalDate.now());
        repository.save(foodItem);
        return foodItem;
    }

    public FoodItem getFoodItemById(UUID id) throws InvalidIdException {
        Optional<FoodItem> foodItem = repository.findById(id);
        if(foodItem.isEmpty()) throw new InvalidIdException("Invalid Id " + id);
        return foodItem.get();
    }

    public void deleteFoodItem(FoodItem foodItem) {
        repository.delete(foodItem);
    }
}
