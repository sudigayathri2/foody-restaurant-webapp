package com.codencode.foody.service;

import com.codencode.foody.entity.FoodItem;
import com.codencode.foody.entity.Order;
import com.codencode.foody.entity.OrderItem;
import com.codencode.foody.entity.User;
import com.codencode.foody.exception.InvalidIdException;
import com.codencode.foody.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final FoodItemService foodItemService;

    OrderService(OrderRepository orderRepository,  UserService userService, FoodItemService foodItemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.foodItemService = foodItemService;
    }

    public Order placeOrder(Order order) throws InvalidIdException {
        User user = userService.getUserById(order.getUser().getId());
        double amount = 0.0;

        for(OrderItem orderItem : order.getOrderItems()) {
            FoodItem item = foodItemService.getFoodItemById(orderItem.getFoodItem().getId());
            amount += item.getPrice() * orderItem.getQuantity();

            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
        }

        order.setAmount(amount);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> findByUserId(UUID userId) {
        return orderRepository.findOrdersByUserId(userId);
    }
}
