package com.example.fairtechbeta.service;

import com.example.fairtechbeta.dto.ApiResponse;
import com.example.fairtechbeta.dto.UserDto;
import com.example.fairtechbeta.entity.Pagination;
import com.example.fairtechbeta.entity.User;
import com.example.fairtechbeta.repository.UserRepository;
import com.example.fairtechbeta.utility.PageWrapper;
import com.example.fairtechbeta.utility.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       9:52
 */

@Service
@Slf4j
public class UserService {

//    @Autowired
    public UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ApiResponse<List<User>> getAll(){
        List<User> users = (List<User>) repository.findAll();
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



    // search service with keyword
    public ResponseEntity<PageWrapper> search(Pagination<User> pagination, Optional<String> keyword) throws ParseException {
        Page<User> userPage;
        if (keyword.isPresent() && !keyword.get().equals("")) {
            userPage = search(keyword.get().toLowerCase(), pagination);
        } else {
            userPage = search("", pagination);
        }
        List<User> users = userPage.getContent();
        List<UserDto> userDtos = new ArrayList<>(users.size());
        for (User user : users) {
            userDtos.add(new UserDto(user));
        }
        return ResponseEntity.ok(PageWrapper.builder().list(userDtos).
                total(userPage.getTotalElements()).build());
    }

    public Page<User> search(String keyword, Pagination pagination) {
        Pageable paging = Utils.getPageableFromPagination(pagination);
        Page<User> pagedResult;
        if (!keyword.equals("")) {
            pagedResult = repository.findAll(keyword, paging);
        } else {
            pagedResult = repository.findAll1(paging);
        }
        return pagedResult;
    }


    public ApiResponse<User> add(UserDto dto){
        User user = dto.convertToUser();
//                .nameUz(dto.getNameUz())
//                .nameEn(dto.getNameEn())
//                .nameLt(dto.getNameLt())
//                .nameRu(dto.getNameRu())
//                .count(dto.getCount())
//                .date(dto.getDate())
//                .build();
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
        user = dto.convertToUser();
//                .nameUz(dto.getNameUz())
//                .nameEn(dto.getNameEn())
//                .nameLt(dto.getNameLt())
//                .nameRu(dto.getNameRu())
//                .count(dto.getCount())
//                .date(dto.getDate())
//                .build();
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
