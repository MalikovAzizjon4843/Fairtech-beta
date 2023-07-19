package com.example.newproject.controller;

import com.example.newproject.dto.ApiResponse;
import com.example.newproject.dto.UserDto;
import com.example.newproject.entity.User;
import com.example.newproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       9:55
 */

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        ApiResponse<?> response = service.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<User> response = service.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid UserDto dto){
        ApiResponse<User> response = service.add(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody @Valid UserDto dto){
        ApiResponse<User> response = service.edit(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse<User> response = service.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
