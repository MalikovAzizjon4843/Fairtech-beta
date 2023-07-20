package com.example.fairtechbeta.repository.product;

import com.example.fairtechbeta.entity.product.AuthorizationPhoneSendCodes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author $(" Malikov Azizjon ")     Fairtech-Beta       20.07.2023       16:20
 */

@Repository
public interface AuthorizationPhoneSendCodesRepository extends CrudRepository<AuthorizationPhoneSendCodes, Long> {

    @Query("select a from AuthorizationPhoneSendCodes a where a.phoneNumber = ?1")
    AuthorizationPhoneSendCodes getByPhoneNumber(String phoneNumber);

}
