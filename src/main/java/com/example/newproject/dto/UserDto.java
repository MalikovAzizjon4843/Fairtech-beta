package com.example.newproject.dto;

import com.example.newproject.entity.User;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       9:45
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@Table(name = "users")
public class UserDto {
    private Long id;
    private String nameUz, nameEn, nameLt, nameRu;

    private long count;

    private String date;


    //  dto cast to entity
    public UserDto(User user) {
        if (user.getId() != null) {
            setId(user.getId());
        }
        setNameUz(user.getNameUz());
        setNameEn(user.getNameEn());
        setNameLt(user.getNameLt());
        setNameRu(user.getNameRu());
    }




    // data cast to dto
    public User convertToUser() {
        User user = new User();
        return convertToUser(user);
    }

    private User convertToUser(User user) {
        if (id != null)
            user.setId(id);
        user.setNameRu(nameRu);
        user.setNameUz(nameUz);
        user.setNameEn(nameEn);
        user.setNameLt(nameLt);

        return user;
    }
}
