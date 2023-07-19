package com.example.newproject.repository;

import com.example.newproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author $(" Malikov Azizjon ")     newProject       19.07.2023       9:51
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
