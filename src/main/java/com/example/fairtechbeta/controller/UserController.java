package com.example.fairtechbeta.controller;

import com.example.fairtechbeta.dto.ApiResponse;
import com.example.fairtechbeta.dto.UserDto;
import com.example.fairtechbeta.entity.Pagination;
import com.example.fairtechbeta.entity.User;
import com.example.fairtechbeta.service.UserService;
import com.example.fairtechbeta.utility.PageWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

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


    // list searching with keyword
    @PostMapping("/list-search")
//    @PreAuthorize("hasAuthority('list pharm medication')")
    public ResponseEntity<PageWrapper> search(@RequestBody Pagination<User> pagination, @RequestParam(name = "keyword", required = false) Optional<String> keyword) throws ParseException {
        return service.search(pagination, keyword);
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
        ApiResponse<User> response = service.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
