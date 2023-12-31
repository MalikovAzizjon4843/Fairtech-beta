package com.example.fairtechbeta.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Malikov Azizjon    newProject       19.07.2023       9:33
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameUz, nameEn, nameLt, nameRu;

    private long count;

    private String date;
}
