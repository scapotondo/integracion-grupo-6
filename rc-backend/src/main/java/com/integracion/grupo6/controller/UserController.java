package com.integracion.grupo6.controller;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.dto.UserDTO;
import com.integracion.grupo6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/api/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody UserDTO user){
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody UserDTO user){
        return userService.update(user);
    }

    @DeleteMapping(path ={"/{id}"})
    public User delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

    @GetMapping(path = {"/{id}"})
    public User findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @GetMapping(path = {"/all"})
    public List findAll() {
        return userService.findAll();
    }

    @GetMapping(path = {"/{username}"})
    public User findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }
}
