package com.example.newproject.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.newproject.entity.Auditable;
import java.util.Date;

/**
 * @author Malikov Azizjon    Fairtech-Beta       20.07.2023       16:19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_users_sended_codes")
public class AuthorizationPhoneSendCodes extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_USERS_SEQUENCE")
    @SequenceGenerator(sequenceName = "PRODUCT_USERS_SEQUENCE", allocationSize = 1, name = "PRODUCT_USERS_SEQUENCE")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "sended_code")
    private String code;

    @Column(name = "sended_date")
    private Date sendedDate;



}
