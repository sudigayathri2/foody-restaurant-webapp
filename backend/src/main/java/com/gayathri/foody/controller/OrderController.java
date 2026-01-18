package com.gayathri.foody.controller;


import com.gayathri.foody.entity.Order;
import com.gayathri.foody.exception.InvalidIdException;
import com.gayathri.foody.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) throws InvalidIdException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(order));
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<Order>> findOrderById(@PathVariable UUID userId) throws InvalidIdException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.findByUserId(userId));
    }

}
