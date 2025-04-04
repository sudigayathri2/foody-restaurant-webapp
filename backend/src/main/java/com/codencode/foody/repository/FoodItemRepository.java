package com.codencode.foody.repository;

import com.codencode.foody.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, UUID> {
}
