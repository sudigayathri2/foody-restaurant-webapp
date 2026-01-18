package com.gayathri.foody.service;

import com.gayathri.foody.entity.Role;
import com.gayathri.foody.entity.User;
import com.gayathri.foody.exception.InvalidIdException;
import com.gayathri.foody.exception.InvalidOperationException;
import com.gayathri.foody.repository.RoleRepository;
import com.gayathri.foody.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UUID saveUser(User user) throws InvalidOperationException {
        user.setCreated(LocalDateTime.now());
        List<Role> roles = new ArrayList<>();

        for (Role role: user.getRoles()) {
            Optional<Role> fetchedRole = roleRepository.findByRole(role.getRole());
            if (fetchedRole.isPresent()) {
                roles.add(fetchedRole.get());
            } else {
                throw new InvalidOperationException("Role " + role.getRole() + " not found.");
            }
        }
        user.setRoles(roles);
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
