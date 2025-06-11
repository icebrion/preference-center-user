package com.vw.infrastructure.user.entity;

import com.vw.domain.entity.Consent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;

    private String email;

    private List<Consent> consents;

}
