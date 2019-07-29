package com.jarvis.restwebservices.controller;

import com.jarvis.restwebservices.entities.User;
import com.jarvis.restwebservices.exception.UserNotFoundException;
import com.jarvis.restwebservices.service.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }


    @GetMapping("/users/{id}")
    public User findOneUser(@PathVariable Integer id) {
        User user =  userDaoService.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id: " + id);

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        user = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }



}