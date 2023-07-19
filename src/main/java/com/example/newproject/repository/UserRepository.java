package com.example.newproject.repository;

import com.example.newproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author   Malikov Azizjon     newProject       19.07.2023       9:51
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//    List<User> findAllByNameRuLikeOrNameUzLikeOrNameLtLikeOrNameEnLike(String nameRu, String nameUz, String nameLt, String nameEn);

    @Query("select m from User m order by m.name")
    Page<User> findAll(Pageable paging);

    @Query("select m from User m where (lower(m.name) like %?1% or lower(m.mxikCode) like %?1%)")
    Page<User> findAll(String keyword, Pageable paging);
}
