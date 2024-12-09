package com.example.api_grupo_sura.controller;


import com.example.api_grupo_sura.dto.userDto.ResponseUserDTO;
import com.example.api_grupo_sura.entity.User;
import com.example.api_grupo_sura.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

    private final UserService userService;

    public ControllerUser(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUser(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.findAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.findUserById(id));
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        this.userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
