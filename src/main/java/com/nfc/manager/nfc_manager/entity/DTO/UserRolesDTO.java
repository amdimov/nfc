package com.nfc.manager.nfc_manager.entity.DTO;

import com.nfc.manager.nfc_manager.entity.UserRoleEnum;

public class UserRolesDTO {
    private UserRoleEnum userRoleEnum;

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public UserRolesDTO setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
        return this;
    }
}
