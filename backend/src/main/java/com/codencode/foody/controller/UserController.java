package com.codencode.foody.controller;

import com.codencode.foody.entity.User;
import com.codencode.foody.exception.InvalidIdException;
import com.codencode.foody.exception.InvalidOperationException;
import com.codencode.foody.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    ResponseEntity<UUID> signUp(@Valid @RequestBody User user) {
        UUID id = userService.saveUser(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<User> getById(@PathVariable UUID id) throws InvalidIdException {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/update")
    ResponseEntity<User> updateUser(@RequestBody User updatedUser) throws InvalidIdException, InvalidOperationException {
        User user = userService.updateUser(updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    ResponseEntity<Void> removeUser(@RequestBody User user) throws InvalidIdException {
        userService.removeUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
