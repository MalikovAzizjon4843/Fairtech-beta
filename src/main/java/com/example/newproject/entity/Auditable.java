package com.example.newproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @author Malikov Azizjon    Fairtech-Beta       20.07.2023       16:37
 */


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable <U>{

    @CreatedBy
    @Column(name = "created_by")
    private U createdBy;

    @CreatedDate
    @Column(name = "created")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date created = new Date();

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updated;

}
