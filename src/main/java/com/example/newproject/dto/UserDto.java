package com.example.newproject.dto;

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
@Getter
@Setter
@ToString
//@Table(name = "users")
public class UserDto {

    private String nameUz, nameEn, nameLt, nameRu;

    private int count;
//
    private String date;
}
