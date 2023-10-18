package com.nfc.manager.nfc_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class UserRoles extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserRoles setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }
}
