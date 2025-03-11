package com.codencode.foody;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FoodItem {

    @Id
    String itemName;
    Integer quantity;
    Integer price;
}
