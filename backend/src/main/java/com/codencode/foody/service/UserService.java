package com.codencode.foody.service;

import com.codencode.foody.entity.User;
import com.codencode.foody.exception.InvalidIdException;
import com.codencode.foody.exception.InvalidOperationException;
import com.codencode.foody.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UUID saveUser(User user) {
        user.setCreated(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(User user) throws InvalidIdException {
        User savedUser = getUserById(user.getId());
        userRepository.delete(savedUser);
    }

    public User updateUser(User user) throws InvalidIdException, InvalidOperationException {
        User savedUser = getUserById(user.getId());
        if(!savedUser.getEmail().equals(user.getEmail())) {
            throw new InvalidOperationException("Email id can not be modified.");
        }
        user.setModified(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUserById(UUID id) throws InvalidIdException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new InvalidIdException("There is no user with id " + id);
        return user.get();
    }
}
