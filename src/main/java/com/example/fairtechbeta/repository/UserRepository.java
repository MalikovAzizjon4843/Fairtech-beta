package com.example.fairtechbeta.repository;

import com.example.fairtechbeta.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author   Malikov Azizjon     newProject       19.07.2023       9:51
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//    List<User> findAllByNameRuLikeOrNameUzLikeOrNameLtLikeOrNameEnLike(String nameRu, String nameUz, String nameLt, String nameEn);


    // searching with keyword
    @Query("select m from User m order by m.nameEn ")
    Page<User> findAll1(Pageable paging);

    @Query("select m from User m where (lower(m.nameEn) like %?1% or lower(m.nameUz) like %?1%)")
    Page<User> findAll(String keyword, Pageable paging);
}
