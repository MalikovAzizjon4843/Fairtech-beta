package com.example.newproject.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       9:45
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String nameUz, nameEn, nameLt, nameRu;
    private int count;
    private LocalDateTime date;
}
