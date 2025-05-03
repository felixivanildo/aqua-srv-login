package com.aquaone.srv.login.controllers;


import com.aquaone.srv.login.domain.user.User;
import com.aquaone.srv.login.domain.user.UserFindDTO;
import com.aquaone.srv.login.domain.user.UserRequestDTO;
import com.aquaone.srv.login.domain.user.UserUpdateDTO;
import com.aquaone.srv.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping()
    public ResponseEntity<Map<String, Object>> create(@RequestBody UserRequestDTO data) {
        List<User> users = userService.listUser();
 
        if (users.stream().anyMatch(user -> user.getNome().equals(data.nome().toUpperCase()))) {
            Map<String, Object> response = new HashMap<>();
            response.put("MESSAGE", "USER ALREADY EXISTS");

            return ResponseEntity.ok(response);
        }
        
        User newUser = userService.createUsr(data);

        Map<String, Object> response = new HashMap<>();
        response.put("MESSAGE", "USER CREATED");
        response.put("USER", newUser);
        // System.out.println("newUser");
        return ResponseEntity.ok(response);
    }


    @GetMapping()
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.listUser()
        .stream()
        .peek(user -> user.setPassword(null))
        .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{data}")
    public ResponseEntity<UserDetails> listByID(@PathVariable String data) {
        final UUID id = UUID.fromString(data);       
        
        UserDetails users =  userService.findUserById(id);
        return ResponseEntity.ok(users);
    }



    @PutMapping()
    public ResponseEntity updateUser(@RequestBody UserUpdateDTO data) {
        
        final UUID id = UUID.fromString(data.id());
        
        User updatUser = userService.updateUsr(id, data);

        updatUser.setPassword(null);

        return ResponseEntity.ok(updatUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity putMethodName(@PathVariable String data) {
        final UUID id = UUID.fromString(data);


        userService.deleteUserById(id);
        Map<String, String> response = new HashMap<>();
        response.put("MESSAGE", "DELETED");
        

        return  ResponseEntity.ok(response);
    }
}
