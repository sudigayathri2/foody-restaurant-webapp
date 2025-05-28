package com.codencode.foody.service;

import com.codencode.foody.entity.CartItem;
import com.codencode.foody.entity.FoodItem;
import com.codencode.foody.entity.User;
import com.codencode.foody.exception.InvalidIdException;
import com.codencode.foody.exception.InvalidOperationException;
import com.codencode.foody.repository.CartRepository;
import com.codencode.foody.repository.FoodItemRepository;
import com.codencode.foody.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final FoodItemRepository foodItemRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, FoodItemRepository foodItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public CartItem addCart(CartItem cartItem) throws InvalidIdException {
        User user = userRepository.findById(cartItem.getUserId()).orElseThrow(
                ()-> new InvalidIdException("User not found with id: " + cartItem.getUserId())
        );

        FoodItem foodItem = foodItemRepository.findById(cartItem.getItemId()).orElseThrow(
                ()-> new InvalidIdException("Food item not found with id: " + cartItem.getItemId())
        );
        cartItem.setItemName(foodItem.getName());
        cartItem.setPrice(foodItem.getPrice());
        return cartRepository.save(cartItem);
    }

    public CartItem updateCartItem(CartItem cartItem) throws InvalidIdException, InvalidOperationException {
        CartItem item = cartRepository.findById(cartItem.getId()).orElseThrow(
                () -> new InvalidIdException("Invalid cart item id " + cartItem.getId())
        );

        if (!cartItem.getUserId().equals(item.getUserId()) || !cartItem.getItemId().equals(item.getItemId())) {
            throw new InvalidIdException("Invalid user or item id.");
        }

        if (item.getQuantity() == cartItem.getQuantity()) {
            throw new InvalidOperationException("Can't update as item quantity is already " + item.getQuantity());
        }

        item.setQuantity(cartItem.getQuantity());
        return cartRepository.save(item);
    }

    public List<CartItem> fetchUserCartItems(UUID userId) {
        return cartRepository.findByUserId(userId);
    }

    public CartItem removeCartItem(Long cartItemId, UUID userId) throws InvalidIdException, InvalidOperationException {
        CartItem cartItem = cartRepository.findById(cartItemId).orElseThrow(
                () -> new InvalidIdException("Invalid cart item id " + cartItemId)
        );

        if (!cartItem.getUserId().equals(userId)) {
            throw new InvalidOperationException("User doesn't have this item in their cart.");
        }

        cartRepository.deleteById(cartItemId);
        return cartItem;
    }

}
