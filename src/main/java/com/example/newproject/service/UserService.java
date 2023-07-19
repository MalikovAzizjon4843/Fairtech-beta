package com.example.newproject.service;

import com.example.newproject.dto.ApiResponse;
import com.example.newproject.dto.UserDto;
import com.example.newproject.entity.User;
import com.example.newproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       9:52
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public ApiResponse<List<User>> getAll(){
        List<User> users = repository.findAll();
        return ApiResponse.<List<User>>builder()
                .data(users)
                .message("Success")
                .status(200)
                .success(true)
                .build();
    }

    public ApiResponse<User> getOne(Long id) {

        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ApiResponse.<User>builder().
                    success(true).
                    status(200).
                    message("User here").
                    data(user).
                    build();
        }
        return ApiResponse.<User>builder().
                success(false).
                status(400).
                message("User is not found").
                build();
    }




    public ApiResponse<User> add(UserDto dto){
        User user = User.builder()
                .nameUz(dto.getNameUz())
                .nameEn(dto.getNameEn())
                .nameLt(dto.getNameLt())
                .nameRu(dto.getNameRu())
                .count(dto.getCount())
                .date(dto.getDate())
                .build();
        User newUser = repository.save(user);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Success")
                .status(200)
                .data(newUser)
                .build();

    }



    public ApiResponse<User> edit(Long id, UserDto dto){
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()){
            return ApiResponse.<User>builder()
                    .status(400)
                    .message("Not found")
                    .build();
        }
        User user = optionalUser.get();
        user = user.builder()
                .nameUz(dto.getNameUz())
                .nameEn(dto.getNameEn())
                .nameLt(dto.getNameLt())
                .nameRu(dto.getNameRu())
                .count(dto.getCount())
                .date(dto.getDate())
                .build();
        User newUser = repository.save(user);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Success")
                .status(200)
                .data(newUser)
                .build();
    }


    public ApiResponse<User> delete(Long id){
        if (repository.findById(id).isEmpty()){
            return ApiResponse.<User>builder()
                    .status(400)
                    .message("Not found")
                    .build();
        }
        repository.deleteById(id);
        return ApiResponse.<User>builder()
                .status(200)
                .success(true)
                .message("Deleted")
                .build();
    }

}
